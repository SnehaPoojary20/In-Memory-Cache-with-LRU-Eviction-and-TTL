package lru;

public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);

        System.out.println("Put (1, 100) with TTL 2s");
        cache.put(1, 100, 2000);

        System.out.println("Put (2, 200) with TTL 2s");
        cache.put(2, 200, 2000);

        System.out.println("Get 1 → " + cache.get(1)); 

        Thread.sleep(2100);

        System.out.println("Get 1 after TTL → " + cache.get(1)); 

        System.out.println("Put (3, 300)");
        cache.put(3, 300, 2000);

        System.out.println("Get 2 → " + cache.get(2)); 
        System.out.println("Get 3 → " + cache.get(3)); 
    }
}


