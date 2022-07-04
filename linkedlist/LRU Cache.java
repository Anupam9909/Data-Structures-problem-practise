// I WAY (short way)
// DO THIS WAY [LRU CACHE] for online exam
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
        
        nn.prev = null;
        nn.next = null;
    }
    
    public void add(Node nn){
        if(nn == null) return;
        size++;
        hm.put(nn.key, nn);
        
        Node forw = head.next;
        head.next = null;
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










//==========================================================================================













// II WAY - list ki ek predefined class bana ke we can also solve 
// do this for interview because issi ke basis pe LFU banega 
// and also iss solution me oops lagi ha bhot
class Node{
    int key, value;
    Node next, prev;
    Node(int k, int v){
        this.key = k;
        this.value = v;
        this.next = null;
        this.prev = null;
    }
}

class MyList{
    Node head, tail;
    MyList(){
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public void add(Node node){
        if(node == null) return;
        
        Node forw = head.next;
        head.next = null;
        forw.prev = null;
        
        head.next = node;
        node.prev = head;
        
        node.next = forw;
        forw.prev = node;
    } 
    
    public void remove(Node node){
        Node back = node.prev;
        Node front = node.next;
        
        back.next = front;
        front.prev = back;
        
        node.prev = null;
        node.next = null;
    }
}

class LRUCache {
    MyList list;
    int size = 0, capacity = 0;
    HashMap<Integer, Node> hm;
    public LRUCache(int capacity){
        this.size = 0;
        this.capacity = capacity;
        list = new MyList();
        hm = new HashMap<>();
    }
    
    public int get(int key) {
        if(hm.containsKey(key)){
            Node tnode = hm.get(key);
            // update cache
            list.remove(tnode);
            list.add(tnode);
            return tnode.value;
        }else{
            return -1;
        }
    }
    
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node tnode = hm.get(key);
            tnode.value = value;  // update node value
            // update LRU cache
            list.remove(tnode);
            list.add(tnode);
        }else{
            if(size >= capacity){  // if capacity is full : just remove the LRU node
                Node lastnode = list.tail.prev;
                hm.remove(lastnode.key);  // remove from hm also
                list.remove(lastnode);
            }
            
            Node nn = new Node(key,value);
            list.add(nn);
            size++;
            hm.put(key, nn);
        }
    }
}


