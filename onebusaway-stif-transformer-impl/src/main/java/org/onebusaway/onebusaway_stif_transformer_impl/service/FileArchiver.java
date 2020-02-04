package org.onebusaway.onebusaway_stif_transformer_impl.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * A thin wrapper abstracting S3.  In case files need to be pushed to alternate storage mechanisms.
 */
public class FileArchiver {

    private final Logger _log = LoggerFactory.getLogger(FileArchiver.class);
    private S3Services s3 = null;
    private FileUtil fu = new FileUtil();
    public boolean put(String urlBase, String fileName) {
        if (urlBase.startsWith(("s3://"))) {
            File fileCheck = new File(fileName);
            if (!fileCheck.exists() || !fileCheck.isFile())
                throw new RuntimeException("Missing expected file " + fileName);
            String url = urlBase + File.separator + fu.getFileNameFromUrl(fileName);
            _log.debug("made url {} out of {} and {}", url, urlBase, fileName);
            return getS3().put(url, fileName);
        } else {
            _log.info("unsupported protocol for archiving: " + urlBase);
            return false;
        }
    }

    public boolean putRecursively(String url, String directoryName) {
        return getS3().putRecursively(url, directoryName);
    }

    public boolean putDirectory(String url, String directoryName) {
        return getS3().putDirectory(url, directoryName);
    }

    private S3Services getS3() {
        if (s3 == null) {
            s3 = new S3Services();
        }
        return s3;
    }
}
