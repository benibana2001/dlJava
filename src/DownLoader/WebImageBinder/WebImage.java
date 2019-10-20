package DownLoader.WebImageBinder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebImage {
    private String url;
    private String fileName;
    private String newDir;
    private HttpURLConnection urlConn;

    public WebImage(String url) {
        this.url = url;
        this.fileName = (new File(url).getName());
    }

    private void setNewDir(String newDir) {
        this.newDir = newDir;
    }

    private String getImageUrl() {
        return this.url;
    }

    private void fetch() throws Exception{
        URL urlImg = new URL(this.getImageUrl());
        //
        urlConn = (HttpURLConnection) urlImg.openConnection();
        urlConn.connect();
        //
        int httpStatusCode = urlConn.getResponseCode();
        if (httpStatusCode == HttpURLConnection.HTTP_NOT_FOUND) {
            // todo: 取得に失敗した場合は別の拡張子を探す
            throw new HttpStatusDead("画像が存在しないようです。: " + url);
        }
        //
        if (httpStatusCode != HttpURLConnection.HTTP_OK) {
            throw new HttpStatusDead("画像の取得に失敗しました。: " + url);
        }
    }

    private void writeImage() throws Exception{
        // Input Stream / Output Stream
        DataInputStream dataInStream = new DataInputStream(urlConn.getInputStream());
        DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(newDir + fileName)));
        // Read Data
        byte[] b = new byte[4096];
        int readByte = 0;
        //
        while (-1 != (readByte = dataInStream.read(b))) {
            dataOutStream.write(b, 0, readByte);
        }
        // Close Stream
        dataInStream.close();
        dataOutStream.close();

        waitMilli(100, "downloading: " + fileName + "...");
    }

    public void download(String newDir) throws Exception {
        setNewDir(newDir);
        fetch();
        writeImage();
    }

    void waitMilli(int m, String message) throws Exception {
        System.out.println(message);
        Thread.sleep(m);
    }
}
