// LinkedList class badiya bani ha ye bhi (see for the revision)

public class linkedlist{
    private class Node{
        private int data;
        private Node next;
        
        Node(int d,){
            this.data = d;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;

    private int size = 0;

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        if(size == 0) return true;
        return false;
    }

    private Node createNewNode(int data){
        Node nn = new Node(data);
        this.size++;
        return nn;
    }

  
    public void addLast(int data){
        Node nn = createNewNode(data);

        if(this.head == null){
            this.head = this.tail = nn;
        }else{
            this.tail.next = nn;
            this.tail = nn;
        }
    }

    public void addFirst(int data){
        Node nn = createNewNode(data);

        if(this.head == null){
            this.head = this.tail = nn;
        }else{
            nn.next = this.head;
            this.head = nn;
        }
    }

    public void addAt(int idx, int data)throws Exception{
        if(idx < 0 || idx > this.size()){
            throw new Exception("Invalid Index");
        } 
        if(idx == 0) addFirst(data);
        if(idx == this.size()) addLast(data);

        Node nn = createNewNode(data);
        Node prevnode = NodeAt(idx-1);
        Node forvnode = NodeAt(idx);

        prevnode.next = nn;
        nn.next = forvnode;
    }

    private Node NodeAt(int idx)throws Exception{
        if(idx < 0 || idx >= this.size()){
            throw new Exception("Invalid Index");
        }
        if(isEmpty()){
            throw new Exception("ListIsEmpty");
        }

        Node ptr = this.head;
        while(idx-- > 0){
            ptr = ptr.next;
        }
        return ptr;
    }

   
    public int removeLast()throws Exception{
        if(isEmpty()){
            throw new Exception("ListIsEmpty");
        }

        Node temp = this.tail ;
        int removedata = temp.data;
        
        Node node = NodeAt(this.size()-2);
        node.next = null;
        this.tail = node;

        this.size--;
        return removedata;
    }

    public int removeFirst()throws Exception{
        if(isEmpty()){
            throw new Exception("ListIsEmpty");
        }

        if(this.size() == 1){
            int rdata = this.data;
            this.head = this.tail = null;
            return rdata;
        }
        Node temp = this.head;
        int removedata = temp.data;

        this.head = this.head.next;
        temp.next = null;
        this.size--;

        return removedata;
    }

    //since jo bhi fn exception dega write (throws Exception) after them 
    public int removeAt(int dat , int idx)throws Exception { 
        if(idx < 0 || idx >= this.size()){
            throw new Exception("Invalid Index");
            // System.out.println("Invalid Index");   // jaha jaha ye do line likhi ha vaha vaha ye
            // return -1;                             // exception vali line chep do aur kuch ni 
        } 

        if(idx == 0) return removeFirst();
        if(idx == this.size()-1 ) return removeLast();

        Node removenode = NodeAt(idx);
        Node prevnode = NodeAt(idx-1);
        Node forvnode = NodeAt(idx+1);

        int removedata = removenode.data;
        prevnode.next = forvnode;

        removenode.next = null;
        return removedata;
    }

    public int getAt(int idx){
        Node temp = NodeAt(idx);

        if(temp == null) return -1;
        return temp.data;
    }

}