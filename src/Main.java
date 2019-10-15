public class Main {
    public static void main(String[] args) {
        Downloader t = new Downloader();
        try {
            t.download();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class HttpStatusDead extends Exception {
    public HttpStatusDead(String message) {
        super(message);
    }
}

class FailedMkDir extends Exception {
    public FailedMkDir(String message) {
        super(message);
    }
}

