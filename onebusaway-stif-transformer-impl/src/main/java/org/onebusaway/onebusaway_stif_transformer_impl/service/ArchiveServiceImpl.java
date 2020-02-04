package org.onebusaway.onebusaway_stif_transformer_impl.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Calendar;

public class ArchiveServiceImpl {

    private final Logger _log = LoggerFactory.getLogger(ArchiveServiceImpl.class);

    private String archiveUrl = null;
    public void setArchiveUrl(String url) {
        this.archiveUrl = url;
    }
    private FileFetcher ff = new FileFetcher();
    private FileArchiver fa = new FileArchiver();
    private FileUtil fu = new FileUtil();

    /**
     * push files to staged locations for use, they have been validated.
     * @param inputDirectory
     * @return
     */
    public boolean stageGraph(String inputDirectory) {
        String graph = inputDirectory + File.separator +"Graph.obj";
        String md5 = graph + ".md5";
        String version = inputDirectory + File.separator + "version.json";
        fa.put(archiveUrl, graph);
        fa.put(archiveUrl, md5);
        fa.put(archiveUrl, version);
        return true;
    }

    public boolean stageOsm(String inputFile) {
        fa.put(archiveUrl, inputFile);
        return true;
    }

    public boolean stageGtfs(String inputFile) {
        fa.put(archiveUrl + File.separator + "final", inputFile);
        return true;
    }

    public boolean archiveMergedGtfs(String inputFile) {
        fa.put(archiveUrl + File.separator + "modified", inputFile);
        return true;
    }

    /**
     * push files to archive locations.
     * @param inputDirectory
     * @return
     */
    public boolean archive(String inputDirectory, String version) {
        String archivePath = getDatedUrl(version);
        if (archivePath.startsWith("/")) {
            _log.error("unsupported archive path {}", archivePath);
            return false;
        }
        // url, directoryName
        return fa.putRecursively(archivePath, inputDirectory);
    }

    public boolean archiveControl(String inputDirectory, String version) {
        String archivePath = getDatedUrl(version);
        if (archivePath.startsWith("/")) {
            _log.error("unsupported archive path {}", archivePath);
            return false;
        }
        archivePath += "/control";
        // url, directoryName
        return fa.putDirectory(archivePath, inputDirectory);
    }

    public boolean archiveLastKnownGoodInput(String file) {
        String lastKnownGood = getLastKnownGoodInputUrl();
        return fa.put(lastKnownGood, file);
    }

    public boolean archiveInput(String file, String version) {
        String archivePath = getDatedInputUrl(version);
        return fa.put(archivePath, file);
    }

    public boolean archiveModified(String file, String version) {
        String archivePath = getDatedModifiedUrl(version);
        return fa.put(archivePath, file);
    }

    public boolean archiveFinal(String file, String version) {
        String archivePath = getDatedFinalUrl(version);
        return fa.put(archivePath, file);
    }

    public boolean saveOsm(String file){
        String path = archiveUrl + File.separator + "build-config";
        return fa.put(path, file);
    }

    public boolean archiveOsm(String file) {
        Calendar now = Calendar.getInstance();
        String datedUrl =
                archiveUrl +
                        File.separator
                        + "build-config"
                        + File.separator
                        + now.get(Calendar.YEAR)
                        + File.separator
                        + leftPad((now.get(Calendar.MONTH)+1), 2)
                        + File.separator
                        + leftPad(now.get(Calendar.DAY_OF_MONTH), 2);
        return fa.put(datedUrl, file);
    }

    private String getLastKnownGoodInputUrl() {
        String url = archiveUrl + File.separator + "input";
        return url;
    }

    private String getDatedInputUrl(String version) {
        return getDatedUrl(version) + File.separator + "input";
    }
    private String getDatedModifiedUrl(String version) {
        return getDatedUrl(version) + File.separator + "modified";
    }
    private String getDatedFinalUrl(String version) {
        return getDatedUrl(version) + File.separator + "final";
    }

    private String getDatedUrl(String version) {
        Calendar now = Calendar.getInstance();
        String datedUrl =
                archiveUrl +
                        File.separator
                        + "archive"
                        + File.separator
                        + now.get(Calendar.YEAR)
                        + File.separator
                        + leftPad((now.get(Calendar.MONTH)+1), 2)
                        + File.separator
                        + leftPad(now.get(Calendar.DAY_OF_MONTH), 2);

        if (version != null) {
            datedUrl += File.separator + version;
        }
        return datedUrl;
    }

    private String leftPad(int number, int wantedDigits) {
        StringBuffer sb = new StringBuffer();
        sb.append(number);
        int size = sb.length();
        while (size < wantedDigits) {
            sb.insert(0, "0");
            size = sb.length();
        }
        return sb.toString();
    }
}

