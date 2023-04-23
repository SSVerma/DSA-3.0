package linkedlist.lru;

public abstract class LruCache {
    protected final int capacity;

    protected LruCache(int capacity) {
        this.capacity = capacity;
    }

    public abstract int get(int key);

    public abstract void put(int key, int value);
}
