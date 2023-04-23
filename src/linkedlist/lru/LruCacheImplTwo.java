package linkedlist.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LruCacheImplTwo extends LruCache {
    private final Map<Integer, Node> map = new HashMap<>();
    private final PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.accessTime - b.accessTime);
    private int counter = 0;

    protected LruCacheImplTwo(int capacity) {
        super(capacity);
    }

    @Override
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // update access time
        Node updatedNode = createNode(key, node.value);
        map.put(key, updatedNode);
        minHeap.remove();
        minHeap.add(updatedNode);
        return node.value;
    }

    @Override
    public void put(int key, int value) {
        Node node = createNode(key, value);

        if (map.containsKey(key)) {
            map.put(key, node);
            minHeap.remove();
            minHeap.add(node);
            return;
        }

        if (map.size() == capacity) {
            // capacity full, need to remove LRU entry
            Node lruNode = minHeap.remove();
            map.remove(lruNode.key);

            map.put(key, node);
            minHeap.add(node);
        } else {
            map.put(key, node);
            minHeap.add(node);
        }
    }

    private Node createNode(int key, int value) {
        return new Node(key, value, counter++);
    }

    private static class Node {
        final int key;
        final int value;
        final int accessTime;

        public Node(int key, int value, int accessTime) {
            this.key = key;
            this.value = value;
            this.accessTime = accessTime;
        }
    }
}
