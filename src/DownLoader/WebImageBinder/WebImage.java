package DownLoader.WebImageBinder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebImage {
    private String url;
    private String fileName;
    private String n;
    private String ext;
    private String newDir;
    private HttpURLConnection urlConn;
    private final String[] extAry = {"jpg", "png", "gif"};

    public WebImage(String url) {
        setUrl(url);
        setFileName(new File(url).getName());
        setN(fileName.substring(0, fileName.length()-3));
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void setN(String n) {
        this.n = n;
    }

    private void setExt(String ext) {
        this.ext = ext;
    }

    private void setNewDir(String newDir) {
        this.newDir = newDir;
    }

    private String getN() {
        return this.n;
    }

    private String getExt() {
        return this.ext;
    }

    private String getNewDir() {
        return this.newDir;
    }

    private String getUrl() {
        return this.url;
    }

    private void fetch() throws Exception {
        URL urlImg = new URL(this.getUrl());
        //
        urlConn = (HttpURLConnection) urlImg.openConnection();
        urlConn.connect();
        //
        int httpStatusCode = urlConn.getResponseCode();
        if (httpStatusCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new HttpStatusDead("画像が存在しないようです。: " + url);
        }
        //
        if (httpStatusCode != HttpURLConnection.HTTP_OK) {
            throw new HttpStatusDead("画像の取得に失敗しました。: " + url);
        }
    }

    private void multiFetch() {
        int i = 0;
        while (i < extAry.length + 1) {
            try {
                fetch();
                // 拡張子をセット
                setExt(getUrl().substring(getUrl().length() - 3));
                break;
            } catch (Exception e) {
                if (i >= extAry.length) {
                    System.out.printf(
                            "全ての試行が終わりました。" +
                                    "画像 %s は見つかりませんでした。" +
                                    "以降の画像は存在しないものとして終了します。\n",
                            getUrl().substring(0, getUrl().length() - 3)
                    );
//                    System.exit(1);
                }
                // 別のURLをセット
                System.out.printf("拡張子を %s として再度接続を試行します。\n", extAry[i]);
                String newUrl = getUrl().substring(0, getUrl().length() - 3) + extAry[i];
                setUrl(newUrl);
                i++;
            }
        }
    }

    private void writeImage() throws Exception {
        // Input Stream / Output Stream
        DataInputStream dataInStream = new DataInputStream(urlConn.getInputStream());
        DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(getNewDir() + getN() + "." + getExt())));
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

        waitMilli(100, "downloading: " + getUrl() + "...");
    }

    public void download(String newDir) throws Exception {
        setNewDir(newDir);
        multiFetch();
        writeImage();
    }

    void waitMilli(int m, String message) throws Exception {
        System.out.println(message);
        Thread.sleep(m);
    }
}
