package DownLoader.WebImageBinder;

import java.io.File;

public class Downloader{
    private DownloaderCreator dc;
    Downloader(DownloaderCreator dc) {
        this.dc = dc;
    }
    void download() throws Exception {
        WebImageBinder webImageBinder = new WebImageBinder();

        for (int i = 1; i < dc.getMaxPage(); i++) {
            webImageBinder.appendWebImage(new WebImage(
                    dc.getHost() + dc.getPrefix() + paddingZero(dc.getZeroPad(), i) + dc.getExt()
            ));
        }

        Iterator it = webImageBinder.iterator();

        // Create Directory
        createDir(dc.getNewDir());

        // Fetch
        while (it.hasNext()) {
            WebImage webImage = (WebImage) it.next();
            webImage.fetch(dc.getNewDir());
        }
        System.out.println("#FINISH");
    }

    private void createDir(String dirname) throws Exception{
        File newFile = new File(dirname);

        if (newFile.mkdir()) {
            System.out.println(" ディレクトリを作成します。" + dirname);
        } else {
            throw new FailedMkDir("ディレクトリの作成に失敗しました。 " + dirname);
        }
    }

    private String paddingZero(int digit, int i) {
            return pad(digit, i, "0");
    }

    private String pad(int digit, int n, String pre) {
        String s = String.valueOf(n);
        while (s.length() < digit) {
            s = pre + s;
        }
        return s;
    }

}
