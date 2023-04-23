package linkedlist.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCacheImplThree extends LruCache {
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    protected LruCacheImplThree(int capacity) {
        super(capacity);
        this.head.next = tail;
        this.tail.prev = head;
    }

    @Override
    public int get(int key) {
        Node node = map.get(key);

        if (node == null) {
            return -1;
        }

        // mark as recently accessed
        remove(node);
        insertAtFront(node);

        return node.value;
    }

    @Override
    public void put(int key, int value) {
        Node existingNode = map.get(key);
        if (existingNode != null) {
            // mark as recently accessed
            // remove node first
            remove(existingNode);
            map.remove(key);

            // update with new value and insert at first
            createAndInsertNode(key, value);
            return;
        }

        if (map.size() == capacity) {
            // capacity full
            // remove LRU node first
            Node lruNode = tail.prev;
            remove(lruNode);
            map.remove(lruNode.key);

            // now insert
            createAndInsertNode(key, value);
        } else {
            // still has capacity, just insert
            createAndInsertNode(key, value);
        }
    }

    private void createAndInsertNode(int key, int value) {
        Node node = new Node(key, value);
        insertAtFront(node);
        map.put(key, node);
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }

    private static class Node {
        final int key;
        final int value;
        Node next;
        Node prev;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
