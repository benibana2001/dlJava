import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Downloader {
    private HttpURLConnection urlConn = null;

    private String host = "";
    private int maxPage = 100;
    private int zeroPad = 0;
    private  String preFix = null;

    public Downloader(String host, int maxPage, int zeroPad, String prefix){
        // todo:
    }

    private void setHost(String host) {
        this.host = host;
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

    void download() throws Exception {
        System.out.println("#START");

        WebImageBinder webImageBinder = new WebImageBinder();

        for (int i = 1; i < 100; i++) {
            String name = "x" + String.valueOf(i) + ".png";
            webImageBinder.appendWebImage(new WebImage(this.host + name));
        }

        Iterator it = webImageBinder.iterator();

        String newDir = "img/";
        // Create Directory
        File newFile = new File(newDir);

        if (newFile.mkdir()) {
            System.out.println(newDir + " ディレクトリを作成します。");
        } else {
            throw new FailedMkDir("ディレクトリの作成に失敗しました。");
        }

        // Fetch
        while (it.hasNext()) {
            WebImage webImage = (WebImage) it.next();
            webImage.fetch(newDir);
        }
        System.out.println("#FINISH");
    }
}
