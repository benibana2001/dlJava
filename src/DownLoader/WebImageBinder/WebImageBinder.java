package DownLoader.WebImageBinder;

import java.util.ArrayList;

public class WebImageBinder implements Aggregate{
    public ArrayList<WebImage> webImages;

    public WebImageBinder() {
        this.webImages = new ArrayList<>();
    }

    public WebImage getWebImageAt(int index) {
        return webImages.get(index);
//        return webImages[index];
    }

    public void appendWebImage(WebImage webImage) {
        this.webImages.add(webImage);
    }

    public int getLength() {
        return this.webImages.size();
    }

    public Iterator iterator() {
        return new WebImageBinderIterator(this);
    }
}

class HttpStatusDead extends Exception {
    HttpStatusDead(String message) {
        super(message);
    }
}

class FailedMkDir extends Exception {
    public FailedMkDir(String message) {
        super(message);
    }
}
