class LRUCache {
    private static class Node {
        int key, value;     // store key to remove from map on eviction
        Node prev, next;    // DLL pointers
        Node(int k, int v) { this.key = k; this.value = v; }
    }

    private final int capacity;          // max items allowed
    private final Map<Integer, Node> map; // key -> node in DLL
    private final Node head, tail;        // sentinel nodes: head <-> ... <-> tail
    private int size;                     // current number of nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;          // store capacity
        this.map = new HashMap<>();        // O(1) average lookup
        this.head = new Node(0, 0);        // dummy head sentinel
        this.tail = new Node(0, 0);        // dummy tail sentinel
        head.next = tail;                  // connect sentinels
        tail.prev = head;                  // (list is empty between them)
        this.size = 0;                     // start empty
    }

    public int get(int key) {
        Node node = map.get(key);          // O(1) lookup
        if (node == null) return -1;       // not found
        moveToHead(node);                  // mark as most-recently used (MRU)
        return node.value;                 // return value
    }

    public void put(int key, int value) {
        Node node = map.get(key);          // check if key exists
        if (node != null) {                // Update case
            node.value = value;            // update value
            moveToHead(node);              // mark as MRU
            return;
        }
        // Insert case: new key
        Node fresh = new Node(key, value); // allocate node
        map.put(key, fresh);               // record in map
        addAfterHead(fresh);               // insert as MRU just after head
        size++;                            // increase size

        if (size > capacity) {             // eviction needed
            Node lru = popTail();          // remove LRU node (just before tail)
            map.remove(lru.key);           // remove from map
            size--;                        // decrease size
        }
    }

    // ----- Doubly Linked List helpers (all O(1)) -----

    // Insert node right after head (MRU position)
    private void addAfterHead(Node node) {
        node.prev = head;                  // set prev to head
        node.next = head.next;             // set next to the current first
        head.next.prev = node;             // link current first back to node
        head.next = node;                  // link head forward to node
    }

    // Remove node from its current position
    private void removeNode(Node node) {
        Node p = node.prev;                // previous node
        Node n = node.next;                // next node
        p.next = n;                        // bypass node forward
        n.prev = p;                        // bypass node backward
        // (node.prev/node.next needn't be nulled for correctness here)
    }

    // Move an existing node to MRU position
    private void moveToHead(Node node) {
        removeNode(node);                  // detach from current position
        addAfterHead(node);                // re-insert as MRU
    }

    // Remove and return the current LRU node (right before tail)
    private Node popTail() {
        Node lru = tail.prev;              // last real node
        removeNode(lru);                   // detach it
        return lru;                        // return for map removal
    }
}