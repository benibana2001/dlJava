public class WebImageBinderIterator implements Iterator{
    private WebImageBinder webImageBinder;
    private int index;

    WebImageBinderIterator(WebImageBinder webImageBinder) {
        this.webImageBinder = webImageBinder;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < webImageBinder.getLength()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        WebImage webImage = webImageBinder.getWebImageAt(index);
        index++;
        return webImage;
    }
}
