import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        Downloader t = new Downloader();
        t.download();
    }
}

class HttpStatusDead extends Exception {
    public HttpStatusDead(String message) {
        super(message);
    }
}

class Downloader {
    private HttpURLConnection urlConn = null;
    private String url1 = "x1.png";
    private String url2 = "x2.png";

    void download() {
        System.out.println("#START");

        WebImageBinder webImageBinder = new WebImageBinder(2);

        webImageBinder.appendWebImage(new WebImage(url1));
        webImageBinder.appendWebImage(new WebImage(url2));

        Iterator it = webImageBinder.iterator();

        try {
            while (it.hasNext()) {
                WebImage webImage = (WebImage)it.next();
//                getImage(webImage.getImageUrl());
                webImage.fetch();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }

        System.out.println("#FINISH");
    }
}

