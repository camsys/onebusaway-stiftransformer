package org.onebusaway.onebusaway_stif_transformer_impl.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class FileFetcher {

    private static Logger _log = LoggerFactory.getLogger(FileFetcher.class);
    private static final int CHUNK_SIZE = 1024;
    private S3Services s3 = null;


    public String fetchUrl(String url, String destinationPath, CredentialContainer profile) {
        return fetchUrl(url, new FileUtil().getFileNameFromUrl(url), destinationPath, profile);
    }

    public String fetchUrl(String url, String toFileName, String destinationPath, CredentialContainer profile) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            // supported
            return httpFetch(url, toFileName, destinationPath);
        } else if (url.startsWith("s3://")) {
            return getS3().fetch(url, toFileName, destinationPath, profile);
        } else if (url.startsWith("/") || url.startsWith("file://")) {
            FileUtil fu = new FileUtil();
            try {
                String destFileName = destinationPath + File.separator + toFileName;
                String srcFileName = url.replaceFirst("^file://", "");
                if (srcFileName.charAt(0) != '/') {
                    // we have a relative path
                    srcFileName = destinationPath + File.separator + srcFileName;
                }
                _log.info("writing file {} to {}", srcFileName, destFileName);
                File fileCheck = new File(srcFileName);
                if (!fileCheck.exists() || !fileCheck.isFile()) {
                    _log.error("file check failed for expected src file {}", srcFileName);
                    throw new RuntimeException("No such file " + srcFileName);
                }
                File destFile = new File(destFileName);
                if (!destFile.exists()) {
                    fu.copyFile(srcFileName, destFileName);
                    _log.info("preserving " + destFileName + " lastModifiedTime of " + Files.getAttribute(new File(srcFileName).toPath(), "lastModifiedTime"));
                    Files.setAttribute(new File(destFileName).toPath(),
                            "lastModifiedTime",
                            Files.getAttribute(new File(srcFileName).toPath(), "lastModifiedTime"));

                } else {
                    _log.warn("file " + destFile + " already exists, lastModifiedTime not changed, file not copied");
                }
                return destFileName;
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        throw new UnsupportedOperationException("Protocol specified in url not support: " + url);
    }

    public String httpFetch(String urlString, String destinationPath) {
        return httpFetch(urlString, new FileUtil().getFileNameFromUrl(urlString), destinationPath);
    }
    public String httpFetch(String urlString, String toFileName, String destinationPath) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException mue) {
            _log.error("invalid url {}", urlString, mue);
            return null;
        }
        URLConnection urlConnection = null;

        BufferedInputStream reader = null;
        BufferedOutputStream writer = null;
        String destinationFileName = null;
        try {
            urlConnection = url.openConnection();

            reader = new BufferedInputStream(new DataInputStream(urlConnection.getInputStream()));
            destinationFileName = destinationPath + File.separator + toFileName;
            writer = new BufferedOutputStream(new FileOutputStream(destinationFileName));
            // do the normal chunking
            byte[] buffer = new byte[CHUNK_SIZE];
            int len;
            while (( len = reader.read(buffer)) >= 0) {
                writer.write(buffer, 0, len);
            }

        } catch (IOException ioe) {
            _log.error("unable to retreive file at {}", url, ioe);
            destinationFileName = null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    // bury
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    // bury
                }
            }
            try {
                Files.setAttribute(new File(destinationFileName).toPath(),
                        "lastModifiedTime",
                        FileTime.fromMillis(urlConnection.getLastModified()));
                _log.info(" setting lastModifiedTime of " + destinationFileName + " to " + new Date(urlConnection.getLastModified()));
            } catch (IOException bury) {
                // bury
            }
        }

        return destinationFileName;
    }

    public String fileFetch(String urlString, String destinationPath) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException mue) {
            _log.error("invalid url {}", urlString, mue);
        }
        URLConnection urlConnection = null;

        BufferedInputStream reader = null;
        BufferedOutputStream writer = null;
        String destinationFileName = null;
        try {
            urlConnection = url.openConnection();

            reader = new BufferedInputStream(new DataInputStream(urlConnection.getInputStream()));
            destinationFileName = new FileUtil().formatFileNameFromUrl(destinationPath, urlString);
            writer = new BufferedOutputStream(new FileOutputStream(destinationFileName));
            // do the normal chunking
            byte[] buffer = new byte[CHUNK_SIZE];
            int len;
            while (( len = reader.read(buffer)) >= 0) {
                writer.write(buffer, 0, len);
            }

        } catch (IOException ioe) {
            _log.error("unable to retreive file at {}", url, ioe);
            destinationFileName = null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    // bury
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    // bury
                }
            }
        }

        return destinationFileName;
    }

    public Result fetchResult(String urlString) {
        if (urlString.startsWith("http://") || urlString.startsWith("https://")) {
            return fetchUrlResult(urlString);
        } else if (urlString.startsWith("/")) {
            return fetchFileResult(urlString);
        } else {
            return new Result(-1, null);
        }
    }

    public Result fetchUrlResult(String urlString) {

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException mue) {
            _log.error("invalid url {}", urlString, mue);
        }
        HttpURLConnection urlConnection = null;

        BufferedInputStream reader = null;
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        String destinationFileName = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();
            reader = new BufferedInputStream(new DataInputStream(urlConnection.getInputStream()));

            // do the normal chunking
            byte[] buffer = new byte[CHUNK_SIZE];
            int len;
            while (( len = reader.read(buffer)) >= 0) {
                writer.write(buffer, 0, len);
            }
            _log.trace("response={}", writer.toString("UTF-8"));

            return new Result(urlConnection.getResponseCode(), writer.toString("UTF-8"));
        } catch (IOException ioe) {
            if (urlConnection !=  null) {
                try {
                    return new Result(urlConnection.getResponseCode(), null);
                } catch (Exception e) {
                    // bury
                }
            }
        }
        _log.error("fall through on fetch {}", urlString);
        return new Result(503, null);
    }

    public Result fetchFileResult(String urlString) {


        BufferedInputStream reader = null;
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        String destinationFileName = null;
        try {
            reader = new BufferedInputStream(new FileInputStream(new File(urlString)));

            // do the normal chunking
            byte[] buffer = new byte[CHUNK_SIZE];
            int len;
            while (( len = reader.read(buffer)) >= 0) {
                writer.write(buffer, 0, len);
            }
            _log.info("response={}", writer.toString("UTF-8"));

            return new Result(200, writer.toString("UTF-8"));
        } catch (IOException ioe) {
            if (reader !=  null) {
                try {
                    return new Result(404, null);
                } catch (Exception e) {
                    // bury
                }
            }
        }
        _log.error("fall through on fetch {}", urlString);
        return new Result(503, null);
    }

    private S3Services getS3() {
        if (s3 == null) {
            s3 = new S3Services();
        }
        return s3;
    }


    public class Result {
        private int statusCode = 0;
        private String contents = null;
        public Result(int statusCode, String contents) {
            this.statusCode = statusCode;
            this.contents = contents;
        }

        public int getStatusCode() {
            return statusCode;
        }
        public String getContents() {
            return contents;
        }
    }
}

