import WebImageBinder.*;

import java.net.HttpURLConnection;

public class DownloaderCreator {
    private HttpURLConnection urlConn = null;

    private String host = "";
    private String ext = ".jpg";
    private String newDir = "img";
    private int zeroPad = 0;
    private int maxPage = 100;
    private String preFix = "";

    // FullQualifiedName
    private String fqn = "";

    public DownloaderCreator(String host){
        setHost(host);
        setFqn();
    }

    public DownloaderCreator(String host, String ext){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setFqn();
    }

    public DownloaderCreator(String host, String ext, String newDir){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setFqn();
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
        setFqn();
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad, int maxPage){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
        setMaxPage(maxPage);
        setFqn();
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad, int maxPage, String prefix){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setMaxPage(maxPage);
        setZeroPad(zeroPad);
        setPreFix(prefix);
        setFqn();
    }

    private void setHost(String host) {
        this.host = host;
    }

    private void setExt(String ext) {
        this.ext = ext;
    }

    private void setNewDir(String newDir) {
        this.newDir = newDir;
    }

    private void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    private void setZeroPad(int zeroPad) {
        this.zeroPad = zeroPad;
    }

    private void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    private void setFqn() {
        this.fqn = getHost() + getPrefix();
    }

    private String getHost() {
        return this.host;
    }

    public String getExt() {
        if (this.ext.substring(0, 1).equals(".")){
            return this.ext;
        } else {
            return "." + this.ext;
        }
    }

    public String getNewDir() {
        if(this.newDir.substring(this.newDir.length() - 1).equals("/")) {
            return this.newDir;
        } else {
            return this.newDir + "/";
        }
    }

    public int getMaxPage() {
        return this.maxPage;
    }

//    private getZeroPad() {
        // todo:
//    }

    private String getPrefix() {
        return this.preFix;
    }

    public String getFqn() {
        return this.fqn;
    }

    public void downloadFile() throws Exception {
        Downloader d = new Downloader(this);
        d.download();
    }
}
