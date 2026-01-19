package lru;

/**
 * Represents a single cache entry node in the doubly linked list
 */
public class CacheNode {
    int key;
    int value;
    long expiryTime;

    CacheNode prev;
    CacheNode next;

    public CacheNode(int key, int value, long ttlMillis) {
        this.key = key;
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }
}
