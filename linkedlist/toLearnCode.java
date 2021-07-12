// GET NODE (0 INDEXED) ->  yaha i = 0 se start hoga bass
public Node getNode(Node head, int idx){
    Node h1 = head; 
    for(int i = 0 ; i < idx; i++){
        h1 = h1.next;
    }
    return h1;   // h1 points exactly at idx index of LL
}


// GET NODE (1 INDEXED) ->  yaha i = 1 se start hoga bass
public Node getNode(Node head, int idx){
    Node h1 = head;
    for(int i = 1 ; i < idx; i++){
        h1 = h1.next;
    }
    return h1;   // h1 points exactly at idx index of LL
}

// =======================================================================================================================

// SLOW-FAST CONCEPT METHOD

// i.  MIDDLE ELEMENT 
public int middleElement(Node head){
    Node slow = head;
    Node fast = head;

    while(fast.next != null && fast.next.next != null){
        slow = slow.next;
        fast = fast.next.next;        
    }
    return slow;
}

// ii.  Kth element from last
public Node kthElementfromLast(Node head, int k){
    Node slow = head;
    Node fast = head;
    
    for(int i = 0; i < k; i++)  fast = fast.next;      // if (0 indexing) ha LL ki to i = 0 se start hoga,           // ye question me dia hoga ki 1 indexing ha ya 0 indexing ha
                                                       // if (1 indexing) ha LL ki to i = 1 se start hoga
    while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next;
    }

    return slow;
}

//===================================================================================================================

// REVERSE OF LINKEDLIST
// 1nd way - yahi yaad kar lo sahi rahega (koi null ki condition tension ni)
public Node reverseLinkedlist(Node head){
    Node prev = null;
    Node curr = head, forw = head;
    
    while(curr != null){
        forw = curr.next;
        
        curr.next = prev;
        prev = curr;
        curr = forw;
    }
    return prev;
}


// 2st way ( yaha forw ke null ki tension hogi !! )
public Node reverseLinkedlist(Node head){
    Node prev = null;
    Node curr = head;
    Node forw = curr.next;

    while(curr != null){
        curr.next = prev;

        prev = curr;
        curr = forw;
        if(forw != null) forw = forw.next;
    }

    return prev;
}

