package org.onebusaway.onebusaway_stif_transformer.service;


import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class S3Services {

    private final Logger _log = LoggerFactory.getLogger(S3Services.class);

    // this expects config files present in certain directory
    final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    final TransferManager tm = TransferManagerBuilder.standard().build();

    private AmazonS3 getS3Provider(CredentialContainer cc) {
        String profile = cc.getProfile();
        if (profile == null || "default".equalsIgnoreCase(profile)) {
            return s3;
        }
        AmazonS3 provider = AmazonS3ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider(profile)).build();
        return provider;
    }

    public String fetch(String url, String toFileName, String destinationPath, CredentialContainer profile) {
        _log.info("fetching {} to {} with profile {}", url, destinationPath, profile.getProfile());
        AmazonS3URI uri = new AmazonS3URI(url);

        S3Object o = getS3Provider(profile).getObject(uri.getBucket(), uri.getKey());
        S3ObjectInputStream s3is = o.getObjectContent();
        File mkdir = new File(destinationPath);
        if (!mkdir.exists() || !mkdir.isDirectory()) {
            mkdir.mkdirs();
        }
        String destinationFileName = destinationPath + File.separator + toFileName;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(destinationFileName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();

            /*
            Files.setAttribute(new File(destinationFileName).toPath(),
                    "lastModifiedTime",
                    FileTime.fromMillis(o.getObjectMetadata().getLastModified().getTime()));
            FileTime timeCheck = (FileTime)Files.readAttributes(new File(destinationFileName).toPath(), "lastModifiedTime").get("lastModifiedTime");
            _log.info("set file lastModifiedTime of "
                    + new Date(o.getObjectMetadata().getLastModified().getTime())
                    + ", read back file lastModifiedTime of " + new Date(timeCheck.toMillis()) + " on file " + destinationFileName);
            */
        } catch (IOException ioe) {
            destinationFileName = null;
            _log.error("unable to write {} to {}", url, destinationFileName, ioe);

        }
        return destinationFileName;
    }

    public boolean put(String url, String fileName) {
        _log.info("uploading {} to {}", fileName, url);
        AmazonS3URI uri = new AmazonS3URI(url);
        s3.putObject(uri.getBucket(), uri.getKey(), new File(fileName));
        return true;
    }

    public boolean putRecursively(String url, String directoryName) {
        _log.info("uploading (recursively) {} to {}", directoryName, url);
        try {
            AmazonS3URI uri = new AmazonS3URI(url);
            MultipleFileUpload upload = tm.uploadDirectory(uri.getBucket(), uri.getKey(), new File(directoryName), true);
            upload.waitForCompletion();
            _log.info("upload complete!");
        } catch (Exception any) {
            _log.error("upload failed: ", any);
            return false;
        }
        return true;
    }

    public boolean putDirectory(String url, String directoryName) {
        _log.info("uploading (recursively) {} to {}", directoryName, url);
        try {
            AmazonS3URI uri = new AmazonS3URI(url);
            MultipleFileUpload upload = tm.uploadDirectory(uri.getBucket(), uri.getKey(), new File(directoryName), false);
            upload.waitForCompletion();
            _log.info("upload complete!");
        } catch (Exception any) {
            _log.error("upload failed: ", any);
            return false;
        }
        return true;
    }
}