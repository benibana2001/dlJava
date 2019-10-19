import WebImageBinder.*;
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
                    dc.getFqn() + i + dc.getExt()
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

}
