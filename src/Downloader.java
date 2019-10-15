import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Downloader {
    private HttpURLConnection urlConn = null;

    private String host = "";
    private String ext = ".jpg";
    private String newDir = "img";
    private int zeroPad = 0;
    private int maxPage = 100;
    private String preFix = null;

    public Downloader(String host){
        setHost(host);
    }

    public Downloader(String host, String ext){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
    }

    public Downloader(String host, String ext, String newDir){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
    }

    public Downloader(String host, String ext, String newDir, int zeroPad){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
    }

    public Downloader(String host, String ext, String newDir, int zeroPad, int maxPage){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setZeroPad(zeroPad);
        setMaxPage(maxPage);
    }

    public Downloader(String host, String ext, String newDir, int maxPage, int zeroPad, String prefix){
        setHost(host);
        setExt(ext);
        setNewDir(newDir);
        setMaxPage(maxPage);
        setZeroPad(zeroPad);
        setPreFix(prefix);
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

    private String getHost() {
        return this.host;
    }

    private String getExt() {
        if (this.ext.substring(0, 1).equals(".")){
            return this.ext;
        } else {
            return "." + this.ext;
        }
    }

    private String getNewDir() {
        if(this.newDir.substring(this.newDir.length() - 1).equals("/")) {
            return this.newDir;
        } else {
            return this.newDir + "/";
        }
    }

    private int getMaxPage() {
        return this.maxPage;
    }

//    private getZeroPad() {
        // todo:
//    }

    private String getPrefix() {
        return this.preFix;
    }

    private void createDir(String dirname) throws Exception{
        File newFile = new File(dirname);

        if (newFile.mkdir()) {
            System.out.println(getNewDir() + " ディレクトリを作成します。");
        } else {
            throw new FailedMkDir("ディレクトリの作成に失敗しました。 " + getNewDir());
        }
    }

    private String imgURL(int i) {
        return getHost() + getPrefix() + String.valueOf(i) + getExt();
    }

    void download() throws Exception {
        System.out.println("#START");

        WebImageBinder webImageBinder = new WebImageBinder();

        for (int i = 1; i < getMaxPage(); i++) {
            webImageBinder.appendWebImage(new WebImage(imgURL(i)));
        }

        Iterator it = webImageBinder.iterator();

        // Create Directory
        createDir(getNewDir());

        // Fetch
        while (it.hasNext()) {
            WebImage webImage = (WebImage) it.next();
            webImage.fetch(getNewDir());
        }
        System.out.println("#FINISH");
    }
}
