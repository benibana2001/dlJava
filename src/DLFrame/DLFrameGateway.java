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
        System.out.println(dc.getFqn());
    }

    public String getText() {
        return this.downloaderCreator.getFqn();
    }

    public void setText(String text) {
        this.downloaderCreator.setHost(text);
    }
}
