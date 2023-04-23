package linkedlist.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCacheImplOne extends LruCache {
    private Map<Integer, Node> map = new HashMap<>();
    private int counter = 0;

    protected LruCacheImplOne(int capacity) {
        super(capacity);
    }

    @Override
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // update access time
        map.put(key, createNode(node.value));
        return node.value;
    }

    @Override
    public void put(int key, int value) {
        Node node = createNode(value);

        if (map.containsKey(key)) {
            map.put(key, node);
            return;
        }

        if (map.size() == capacity) {
            // capacity full, need to remove entry
            int lruNodeKey = findLeastRecentUsedNodeKey();
            map.remove(lruNodeKey);
            map.put(key, node);
        } else {
            map.put(key, node);
        }
    }

    private Node createNode(int value) {
        return new Node(value, counter++);
    }

    // TC: O(capacity)
    private int findLeastRecentUsedNodeKey() {
        int min = Integer.MAX_VALUE;
        int key = -1;

        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            if (entry.getValue().accessTime < min) {
                min = entry.getValue().accessTime;
                key = entry.getKey();
            }
        }

        return key;
    }

    private static class Node {
        final int value;
        final int accessTime;

        public Node(int value, int accessTime) {
            this.value = value;
            this.accessTime = accessTime;
        }
    }
}
