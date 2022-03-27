//LRU cache is a kind of cache that stores the most recently used items in the cache.
//=>we use linkedlist to store the most recently used items and hashmap to store the rest of the items in the cache.
//remove the least recently used item from the cache when the cache is full.
//=>we use a doubly linkedlist to store the items in the cache.
//=>we use a hashmap to store the items in the cache.
//to remove the least recently used item, we need to remove the first item in the linkedlist.

class LRUCache {
    class Node{
        int key;
        int value;
        Node prev = null;
        Node next = null;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head = null;
    Node tail = null;
    int size = 0;
    int maxSize = 0;

    void addLast(Node node){//add the node to the tail of the linkedlist
        if(this.size== 0){//if the linkedlist is empty, the node is the head and the tail
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;//remove dangling pointers
        }
        size++;
    }

    void removeNode(Node node){
        if(size == 1){//if the linkedlist only has one node, the head and the tail are the same node
            head = null;
            tail = null;
    }else if(node == this.head){//if the node is the head, we need to change the head to the next node
            head = head.next;
            head.prev = null;
            node.next = null;
        }else if(node == tail){//if the node is the tail, we need to change the tail to the previous node
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }else{//if the node is in the middle of the linkedlist, we need to change the previous node and the next node
            Node cnode_next = node.next;
            Node cnode_prev = node.prev;
            cnode_prev.next = cnode_next;
            cnode_next.prev = cnode_prev;
        }
        size--;
    }
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        maxSize = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
      if(map.containsKey(key) == false) return -1;
        Node node = map.get(key);
        removeNode(node);
        addLast(node);
        return node.value;

          
      }
    
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){//if the key is not in the cache, we need to add the key and value to the cache
            
            Node nn = new Node(key, value);
            map.put(key, nn);
            addLast(nn);
            if(size > maxSize){//if the cache is full, we need to remove the least recently used item
                Node node_remove = head;
                removeNode(node_remove);
                map.remove(node_remove.key);
            }
        }else{//if the key is in the cache, we need to update the value of the key
            int val = get(key);

            if(val != value){
                Node nn = map.get(key);
                nn.value = value;
                
            }
        }
    }
            // Node node_update = map.get(key);
            // node_update.value = value;
            // removeNode(node_update);//remove the node from the linkedlist
            // addLast(node_update);//add the node to the tail of the linkedlist
        
    
}
















/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
