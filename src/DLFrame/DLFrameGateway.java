package DLFrame;
import DownLoader.WebImageBinder.*;

public class DLFrameGateway {
    private DownloaderCreator downloaderCreator;

    public DLFrameGateway(){
        downloaderCreator = new DownloaderCreator();
    }

    public static void hello() {
        System.out.println("hello DLFrame.DLFrameGateway...");
        DownloaderCreator dc = new DownloaderCreator("http://www.test.jp");
        System.out.println(dc.getHost());
    }
    public void download() throws Exception {
        downloaderCreator.downloadFile();
    }

    public void setText(String text) {
        this.downloaderCreator.setHost(text);
    }

    public void setExt(String ext) {
        this.downloaderCreator.setExt(ext);
    }

    public void setZeroPad(int digit) {
        this.downloaderCreator.setZeroPad(digit);
    }

    public void setNewDir(String name) {
        this.downloaderCreator.setNewDir(name);
    }

    public void setStartPage(int startPage) {
        this.downloaderCreator.setStartPage(startPage);
    }

    public void setMaxPage(int max) {
        this.downloaderCreator.setMaxPage(max);
    }

    public void setPrefix(String prefix) {
        this.downloaderCreator.setPreFix(prefix);
    }

    public String getExt() {
        return this.downloaderCreator.getExt();
    }

    public int getStartPage() {
        return this.downloaderCreator.getStartPage();
    }

    public String getPrefix() {
        return this.downloaderCreator.getPrefix();
    }

    public int getZeroPad() {
        return this.downloaderCreator.getZeroPad();
    }

    public String getHost() {
        return this.downloaderCreator.getHost();
    }

}
