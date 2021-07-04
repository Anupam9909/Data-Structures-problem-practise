public class LL3function{

    public void functionAskedByQuest(int inputs){
        // used this 3 function i.e 
        // addFirst();
        // addLast();
        // removeFirst();
        // to solve the any problem in Linkedlist

        Node th = null;  // th : temporary head 
        Node tt = null;  // tt : temporary tail


    }

    
    public class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }
     
    public void addLast(int data){
        Node nn = new Node(data);
        if(head == null) head = tail  = nn;

        tail.next = nn;
        tail = nn;
    }

    public void addFirst(int data){
        Node nn = new Node(data);
        if(head == null) head = tail = nn;

        nn.next = head;
        head = nn;
    }

    public int removeFirst(){
        if(head == null){
            System.out.println("Empty List");
            return (int)1e8;
        }
        int rm = head.data;
        head = head.next;
        return rm;
    }
}
