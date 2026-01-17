##  In-Memory Cache with LRU Eviction & TTL

## Overview
This project implements a **high-performance in-memory cache** that supports:

- **O(1)** get and put operations
- **LRU (Least Recently Used)** eviction policy
- **TTL (Time-To-Live)** based expiration

The cache is designed to simulate how real-world backend systems reduce latency by storing frequently accessed data in memory while efficiently managing limited memory resources.

---

## Why This Project?
Modern backend systems rely heavily on caching to:
- Reduce database load
- Improve response times
- Handle high traffic efficiently

This project demonstrates **core system design concepts** using fundamental data structures rather than relying on external libraries or databases.

---

## Key Features
- Constant-time `get(key)` and `put(key, value, ttl)`
- Automatic eviction of least recently used entries when capacity is reached
- Automatic removal of expired entries using TTL
- Lightweight and dependency-free implementation
- Designed for extensibility and testing

---

## Core Concepts Demonstrated
- Hashing for fast lookups
- Doubly Linked List for usage tracking
- Cache eviction strategies
- Time-based expiration logic
- Space vs latency tradeoffs

---

## Project Structure
```

lru-cache/
├── CacheNode.java
├── LRUCache.java
├── CacheTest.java
└── README.md

````

---

## How to Run
1. Clone the repository
2. Open Java files in your IDE
3. Compile and run:
```bash
javac *.java
java CacheTest
````

---

## Tech Stack

* **Language:** Java
* **Data Structures:** HashMap, Doubly Linked List
* **Tools:** Git, GitHub

---

## Author

**Sneha Poojary**
GitHub: [https://github.com/SnehaPoojary20](https://github.com/SnehaPoojary20)

