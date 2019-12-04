package org.onebusaway.onebusaway_stif_transformer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {

    private final Logger _log = LoggerFactory.getLogger(FileUtil.class);

    public void writeToFile(String filename, String contents) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(contents, 0, contents.length());
        writer.close();
    }

    // deliberately not using nio for older java support
    public void copyWithExtension(String srcDirectory, String extension, String dest) throws IOException {

        File[] listOfFiles = new File(srcDirectory).listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            String shortName = listOfFiles[i].getName();
            if (shortName.endsWith(extension)) {
                String srcFile = srcDirectory + File.separator + shortName;
                String destFile = dest + File.separator + shortName;
                copyFile(srcFile, destFile);
            }
        }
    }

    public void copyFile(String srcFile, String destFile) throws IOException {
        FileChannel src = new FileInputStream(srcFile).getChannel();
        FileChannel dest = new FileOutputStream(destFile).getChannel();
        try {
            dest.transferFrom(src, 0, src.size());
            _log.info("copied {} to {} ", srcFile, destFile);
        } finally {
            src.close();
            dest.close();
        }
    }

    public String formatFileNameFromUrl(String basePath, String url) {
        if (url == null || basePath == null) return null;
        int lastSlash = url.lastIndexOf('/');
        if (lastSlash < 0) return basePath + File.separator + url;
        if (lastSlash == url.length()-1)
            throw new UnsupportedOperationException("Directories not supported!  Trailing slash found in URL " + url);
        return basePath + File.separator + url.substring(lastSlash+1);
    }

    public String getFileNameFromUrl(String url) {
        if (url == null) { return null; }
        int lastSlash = url.lastIndexOf('/');
        if (lastSlash < 0) return url;
        return url.substring(lastSlash+1);
    }



}
