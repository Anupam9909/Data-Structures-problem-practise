// DO THIS WAY [LRU CACHE]-> SIMPLE ELEGANT AND EASY -> LEARN THIS
// Note 1 :- do dummy node bana lo ek head ka and ek tail ka
// and hamesha inn dono dummy nodes ke ander add karege koi bhi new node

// NOTE 2 :-
// ->  get() =>      if(present in hm){
//                        -> make node MFU
//                        -> return node.val;
//                    }else{
//                        return -1;
//                    }

// ->  put() =>      if(present in hm){
//                        -> make node MFU
//                        -> update the value
//                    }else{
//                        -> if(siz >= capacity) remove(tail.prev); [LFU] ie lastnode which is tail.prev
//                        -> add(nn)
//                    }

class LRUCache {
    private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
    }
    private HashMap<Integer, Node> hm;
    private Node head, tail;
    private int size, cap;
    public LRUCache(int capacity){
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail; // connecting the list ie.(head & tail)
        tail.prev = head;
        hm = new HashMap<>();
        this.size = 0;
        this.cap = capacity;
    }
    
    public int get(int key) {
        if(hm.containsKey(key)){
            Node nn = hm.get(key);
            // make this node MFU node
            // remove and then add in first position
            remove(nn);
            add(nn);
            
            return nn.value;
        }else{
            return -1;
        }
    }
    
    private void remove(Node nn){
        if(nn == null) return;
        size--;
        hm.remove(nn.key);
        
        Node prev = nn.prev;
        Node forw = nn.next;
        prev.next = forw;
        forw.prev = prev;
        
        nn.prev = null;  // null karna bhi important ha
        nn.next = null;
    }
    
    public void add(Node nn){
        if(nn == null) return;
        size++;
        hm.put(nn.key, nn);
        
        Node forw = head.next;
        head.next = null;  // null karna bhi important ha
        forw.prev = null;
        
        head.next = nn;
        nn.prev = head;
        nn.next = forw;
        forw.prev = nn;
            
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node nn = hm.get(key);
            nn.value = value;  // updated the value
            // make this node MFU
            remove(nn);
            add(nn);
        }else{
            Node nn = new Node(key, value);
            if(size >= cap){
                remove(tail.prev);  // removing the LFU node
            }
            add(nn);
        }
    }
}

