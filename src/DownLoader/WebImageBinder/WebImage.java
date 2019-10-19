package DownLoader.WebImageBinder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebImage {
    private String url;

    public WebImage(String url) {
        this.url = url;
    }

    private String getImageUrl() {
        return this.url;
    }

    public void fetch(String newDir) throws Exception {
        URL urlImg = new URL(this.getImageUrl());
        String fileName = (new File(url).getName());

        HttpURLConnection urlConn = (HttpURLConnection) urlImg.openConnection();
        urlConn.connect();

        int httpStatusCode = urlConn.getResponseCode();

        if (httpStatusCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new HttpStatusDead("画像が存在しないようです。: " + url);
        }

        if (httpStatusCode != HttpURLConnection.HTTP_OK) {
            throw new HttpStatusDead("画像の取得に失敗しました。: " + url);
        }

        // Input Stream
        DataInputStream dataInStream = new DataInputStream(urlConn.getInputStream());
        // Output Stream
        DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(newDir + fileName)));


        // Read Data
        byte[] b = new byte[4096];
        int readByte = 0;

        while (-1 != (readByte = dataInStream.read(b))) {
            dataOutStream.write(b, 0, readByte);
        }

        // Close Stream
        dataInStream.close();
        dataOutStream.close();

        waitMilli(100, "downloading: " + fileName + "...");
    }

    void waitMilli(int m, String message) throws Exception {
        System.out.println(message);
        Thread.sleep(m);
    }
}
