package lru;

import java.util.HashMap;

public class LRUCache {

    private final int capacity;
    private final HashMap<Integer, CacheNode> map;

    private CacheNode head; // Most Recently Used
    private CacheNode tail; // Least Recently Used

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    //  Helper Methods 

    private void addToFront(CacheNode node) {
        node.prev = null;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
    }

    private void removeNode(CacheNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void moveToFront(CacheNode node) {
        removeNode(node);
        addToFront(node);
    }

    private boolean isExpired(CacheNode node) {
        return System.currentTimeMillis() > node.expiryTime;
    }

    private void removeExpiredEntries() {
        CacheNode current = head;
        while (current != null) {
            CacheNode next = current.next;
            if (isExpired(current)) {
                removeNode(current);
                map.remove(current.key);
            }
            current = next;
        }
    }

    // Public API 

    public int get(int key) {
        CacheNode node = map.get(key);
        if (node == null) return -1;

        if (isExpired(node)) {
            removeNode(node);
            map.remove(node.key);
            return -1;
        }

        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value, long ttlMillis) {
        removeExpiredEntries();

        if (map.containsKey(key)) {
            CacheNode existing = map.get(key);
            existing.value = value;
            existing.expiryTime = System.currentTimeMillis() + ttlMillis;
            moveToFront(existing);
            return;
        }

        if (map.size() >= capacity && tail != null) {
            map.remove(tail.key);
            removeNode(tail);
        }

        CacheNode node = new CacheNode(key, value, ttlMillis);
        addToFront(node);
        map.put(key, node);
    }
}

