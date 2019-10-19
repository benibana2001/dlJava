package DownLoader.WebImageBinder;

import java.net.HttpURLConnection;

public class DownloaderCreator {
    private HttpURLConnection urlConn = null;

    private String host = "http://www.example.com";
    private String ext = ".jpg";
    private String newDir = "img";
    private int zeroPad = 1;
    private int maxPage = 100;
    private String preFix = "";

    public DownloaderCreator(){}

    public DownloaderCreator(String host){
        setHost(host);
    }

    public DownloaderCreator(String host, String ext){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
    }

    public DownloaderCreator(String host, String ext, String newDir){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad, int maxPage){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
        setMaxPage(maxPage);
    }

    public DownloaderCreator(String host, String ext, String newDir, int zeroPad, int maxPage, String prefix){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setMaxPage(maxPage);
        setZeroPad(zeroPad);
        setPreFix(prefix);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setNewDir(String newDir) {
        this.newDir = newDir;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setZeroPad(int zeroPad) {
        this.zeroPad = zeroPad;
    }

    public void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    public String getHost() {
        return this.host;
    }

    public String getExt() {
        if (this.ext.substring(0, 1).equals(".")){
            return this.ext;
        } else {
            return "." + this.ext;
        }
    }

    public int getZeroPad() {
        return this.zeroPad;
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

    public String getPrefix() {
        return this.preFix;
    }

    public void downloadFile() throws Exception {
        Downloader d = new Downloader(this);
        d.download();
    }
}
