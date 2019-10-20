import DownLoader.WebImageBinder.DownloaderCreator;

public class Main {
    public static void main(String[] args) {
        try {
            DownloaderCreator dc = makeDLCreator(args);
            dc.downloadFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static DownloaderCreator makeDLCreator(String[] args) throws Exception {
        /*
        System.out.println("右記の数の引数で初期化を行います: " + Integer.toString(args.length));
        for (int i = 0; i < args.length; i++){
            System.out.println(args[i]);
        }
        */

        // 0: host, 1: extension, 2: newDirName, 3: zeroPadding, 4: maxPage, 5: prefix

        switch (args.length){
            case 0:
                throw new ArgsWrong("コマンド引数を設定してください。");
            case 1:
                return new DownloaderCreator(args[0]);
            case 2:
                return new DownloaderCreator(args[0], args[1]);
            case 3:
                return new DownloaderCreator(args[0], args[1], args[2]);
            case 4:
                return new DownloaderCreator(args[0], args[1], args[2], Integer.parseInt(args[3]));
            case 5:
                return new DownloaderCreator(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            case 6:
                return new DownloaderCreator(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
            case 7:
                return new DownloaderCreator(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5], Integer.parseInt(args[6]));
            default:
                throw new ArgsWrong("コマンド引数の数に誤りがあります。");
        }
    }
}

class ArgsWrong extends Exception {
    public ArgsWrong(String message) {
        super(message);
    }
}

