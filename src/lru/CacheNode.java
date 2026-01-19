package lru;

public class CacheNode<K, V> {
    K key;
    V value;
    long expiryTime;
    CacheNode<K, V> prev;
    CacheNode<K, V> next;

    public CacheNode(K key, V value, long ttlMillis) {
        this.key = key;
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }
}

