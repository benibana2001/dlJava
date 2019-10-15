public interface Iterator {
    // ループの終了条件
    public abstract boolean hasNext();
    // 集合体の要素を一つ返し、インデックスを一つ進める
    public abstract Object next();
}
