public class WebImageBinder implements Aggregate{
    public WebImage[] webImages;
    public int last = 0;

    public WebImageBinder(int maxsize) {
        this.webImages = new WebImage[maxsize];
    }

    public WebImage getWebImageAt(int index) {
        return webImages[index];
    }

    public void appendWebImage(WebImage webImage) {
        webImages[last] = webImage;
        last++;
    }

    public int getLength() {
        return this.last;
    }

    public Iterator iterator() {
        return new WebImageBinderIterator(this);
    }
}
