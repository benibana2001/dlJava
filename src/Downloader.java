import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class HttpStatusDead extends Exception {
    public HttpStatusDead(String message) {
        super(message);
    }
}

class Downloader {
    private HttpURLConnection urlConn = null;
    private String url1 = "1.png";
    private String url2 = "2.png";

    void download() {
        System.out.println("#START");

        try {
            getImage(url1);
            getImage(url2);
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

        }

        System.out.println("#FINISH");
    }

    void getImage(String url) throws Exception {
        URL urlImg = new URL(url);
        String fileName = (new File(url).getName());

        urlConn = (HttpURLConnection) urlImg.openConnection();
        urlConn.connect();

        int httpStatusCode = urlConn.getResponseCode();

        if (httpStatusCode != HttpURLConnection.HTTP_OK) {
            String message = "画像の取得に失敗しました。: " + url;
            throw new HttpStatusDead(message);
        }

        waitMilli(1100);

        // Input Stream
        DataInputStream dataInStream = new DataInputStream(urlConn.getInputStream());
        // Output Stream
        DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));


        // Read Data
        byte[] b = new byte[4096];
        int readByte = 0;

        while (-1 != (readByte = dataInStream.read(b))) {
            dataOutStream.write(b, 0, readByte);
        }

        // Close Stream
        dataInStream.close();
        dataOutStream.close();
    }

    void waitMilli(int m) throws Exception {
        System.out.println("checking...");
        Thread.sleep(m);
        System.out.println("ok!");
    }
}
