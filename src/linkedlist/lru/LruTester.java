package linkedlist.lru;

public class LruTester {
    public static void main(String[] args) {
        LruCache lruCache = new LruCacheImplThree(3);
        display(lruCache.get(10));
        lruCache.put(1, 1);
        lruCache.put(2, 2);

        display(lruCache.get(1));
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        display(lruCache.get(4));

        lruCache.put(3, 10);
        display(lruCache.get(3));
    }

    private static void display(int value) {
        System.out.println(value);
    }
}
