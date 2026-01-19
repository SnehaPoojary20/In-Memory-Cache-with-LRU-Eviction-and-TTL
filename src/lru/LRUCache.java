package lru;

import java.util.HashMap;

public class LRUCache<K, V> {

    private final HashMap<K, CacheNode<K, V>> map;
    private final int capacity;
    private CacheNode<K, V> head;
    private CacheNode<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    private void addToFront(CacheNode<K, V> node) {
        node.prev = null;
        node.next = head;
        if (head != null) head.prev = node;
        head = node;
        if (tail == null) tail = node;
    }

    private void removeNode(CacheNode<K, V> node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
    }

    private void moveToFront(CacheNode<K, V> node) {
        if (node != head) {
            removeNode(node);
            addToFront(node);
        }
    }

    private boolean isExpired(CacheNode<K, V> node) {
        return System.currentTimeMillis() > node.expiryTime;
    }

    private void removeExpiredEntries() {
        CacheNode<K, V> current = head;
        while (current != null) {
            CacheNode<K, V> next = current.next;
            if (isExpired(current)) {
                removeNode(current);
                map.remove(current.key);
            }
            current = next;
        }
    }

    public V get(K key) {
        CacheNode<K, V> node = map.get(key);
        if (node == null) return null;

        if (isExpired(node)) {
            removeNode(node);
            map.remove(node.key);
            return null;
        }

        moveToFront(node);
        return node.value;
    }

    public void put(K key, V value, long ttlMillis) {
        removeExpiredEntries();

        if (map.containsKey(key)) {
            CacheNode<K, V> existing = map.get(key);
            existing.value = value;
            existing.expiryTime = System.currentTimeMillis() + ttlMillis;
            moveToFront(existing);
            return;
        }

        if (map.size() >= capacity && tail != null) {
            map.remove(tail.key);
            removeNode(tail);
        }

        CacheNode<K, V> node = new CacheNode<>(key, value, ttlMillis);
        addToFront(node);
        map.put(key, node);
    }
}


