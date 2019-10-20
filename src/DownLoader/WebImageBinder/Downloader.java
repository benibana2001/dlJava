package DownLoader.WebImageBinder;

import java.io.File;
import Util.*;

public class Downloader{
    private DownloaderCreator dc;
    Downloader(DownloaderCreator dc) {
        this.dc = dc;
    }
    void download() throws Exception {
        WebImageBinder webImageBinder = new WebImageBinder();

        //
//        System.out.println(dc.getHost());
//        System.out.println(dc.getExt());
//        System.out.println(dc.getNewDir());
//        System.out.println(dc.getZeroPad());
//        System.out.println(dc.getMaxPage());
//        System.out.println(dc.getPrefix());
        //

        for (int i = dc.getStartPage(); i < dc.getMaxPage(); i++) {
            webImageBinder.appendWebImage(new WebImage(
                    dc.getHost() + dc.getPrefix() + Util.paddingZero(dc.getZeroPad(), i) + dc.getExt()
            ));
        }

        Iterator it = webImageBinder.iterator();

        // Create Directory
        createDir(dc.getNewDir());

        // Fetch
        while (it.hasNext()) {
            WebImage webImage = (WebImage) it.next();
            webImage.download(dc.getNewDir());
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
}
