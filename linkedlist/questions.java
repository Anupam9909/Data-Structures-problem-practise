 import java.util.io;

public class questions{
    public class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){this.val = val;}
        ListNode(int val, ListNode next)  {this.val = val; this.next = next;}
    }


    public ListNode middleNode(ListNode head){
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode slow = head;  // slow pointer
        ListNode fast = head;  // fast pointer

        while(fast != null || fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //LC 83
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)  return head;
    
        Node ptr = head;

        while(ptr != null && ptr.next != null){
            
            while(ptr.next != null && ptr.data == ptr.next.data){
                ptr.next = ptr.next.next;
            }

            ptr = ptr.next;
        }
        return head;
    }


    //LC 82
    ListNode th = null;
    ListNode tt = null;

    public void addLast(int val){
        ListNode nn = new ListNode(val);
        if(th == null) th = tt = nn;

        tt.next = nn;
        tt = nn;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode ptr = head;
        int temp = -111;
        
        while(ptr != null && ptr.next != null){
            if(ptr.data == temp || ptr.val == ptr.next.val){
                temp = ptr.val;
            }else{
                addLast(ptr.val);
            }

            ptr = ptr.next;
        }

        if(ptr.val != temp){
            addLast(ptr.val);
        }

        return th;
    }


    // LC-234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode nhead  = mid(head);
        
        ListNode si = head;
        ListNode ei = reverse(nhead);
       
        while(ei.next != si && si != ei){
            if(si.val != ei.val){
                return false;
            }
            si = si.next;
            ei = ei.next;
        }
        
        return (ei.val == si.val);
    }
    
    // ye dono chote chote function(reverse + middle) bahut kaam ate ha and they act as a helper for outher questions
    // bhot hi mast tarika ha easy way me complicated problem ko solve kar dega 
    public ListNode reverse(ListNode head){
        ListNode prev = null, curr = head, forv = curr.next;
        while(curr != null){
            curr.next = prev;
            prev = curr;
            curr = forv;
            if(forv != null) forv = forv.next;
        }
        return prev;
    }
    
    public ListNode mid(ListNode head){
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    // cycle detection 
    public ListNode cycledetection(ListNode head){
        HashMap<ListNode, Integer> hm = new HashMap<>();
        ListNode temp = head;

        while(true){
            if(hm.getOrDefault(temp, 0) == 0){
                hm.put(temp, 1);
            }else{
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // REORDER LIST
    public ListNode mid(ListNode head){
        ListNode slow = head, fast = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode reverse(ListNode head){ // ye code 0,1,2,3,even,odd ke liye bindas chalega
        ListNode prev = null, curr = head, forw = curr.next;
        
        while(curr != null){
            curr.next = prev;
            
            prev = curr;
            curr = forw;
            if(forw != null) forw = forw.next;
        }
        return prev;
    }
    
    
    public void reorderList(ListNode head){
        if(head == null || head.next == null) return ;
        
        ListNode nhead = mid(head);
        ListNode si = head;
        ListNode ei = reverse(nhead);
        
        while(si != null && si != ei){
            ListNode nsi = si.next;
            si.next = ei;
            si = nsi;
            
            ListNode nei = ei.next;
            ei.next = si;
            ei = nei;
        }
        
        
        // FOLLOW UP QUESTION - again reorder list , so that the list becomes original list
        
        again_reorderlist(head);
        
        return;
    }

    // Follow Up Solution.
    public void again_reorderlist(ListNode head){
        if(head == null || head.next == null) return;
        
        ListNode p = head, q = head.next;
        ListNode np = p, nq = q;
        
        while(nq != null && nq.next != null && np != null && np.next != null){
            np.next = np.next.next;
            np = np.next;
            
            nq.next = nq.next.next;
            nq = nq.next;
        }
        
        if(nq != null) nq.next = null;
        if(np != null) np.next = null;
        
        ListNode nhead = reverse(q);
        while(p.next != null) p = p.next;
        
        p.next = nhead;
        
    }


    //LC-21. MERGE TWO SORTED LIST (YE GENERAL CODE HA AGAR QUESTION ME USE HOGA BAHUT)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1 == null && l2 == null) return null;
        
        ListNode dummy = new ListNode(0);
        
        ListNode prev = dummy;
        ListNode curr1 = l1, curr2 = l2;
        
        while(curr1 != null && curr2 != null){
            if(curr1.val < curr2.val){
                prev.next = curr1;
                prev = curr1;
                curr1 = curr1.next;
            }else{
                prev.next = curr2;
                prev = curr2;
                curr2 = curr2.next;
            }
        }
        
        if(curr1 == null){
            prev.next = curr2;
        }else if(curr2 == null){
            prev.next = curr1;
        }// third vala case nahi ayega as esa ni ho sakta ki curr1 == null ha and at the same time curr2 == null
          // agar hota to while loop se compiler niche hi nahi ata   
        
        return dummy.next;
    }
    
     // LC - 148 

    // BRUTE FORCE  (O(n2) time , O(1) space)
    public void swap(ListNode i, ListNode j){
        int value = i.val;
        i.val = j.val;
        j.val = value;
    }
    
    public ListNode sortList(ListNode head) {
        ListNode temp1 = head;
        ListNode temp2 = head;
        
        while(temp1 != null){
            temp2 = temp1;
            while(temp2 != null){
                if(temp1.val > temp2.val){
                    swap(temp1,temp2);
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return head;
    }
     

    // O(nlogn) time and O(1) space  (using mergeTwoSortedList() concept)
    public ListNode sortList(ListNode head){
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode midnode = mid(head);
        ListNode nhead = midnode.next;
        
        midnode.next = null;
        
        
        // call recurssion function
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(nhead);
        
        ListNode anshead = mergeTwoSortedList(h1, h2);
        
        return anshead;
        
    }
    
    public ListNode mid(ListNode head){
        if(head == null) return null;
        
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    public ListNode mergeTwoSortedList(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1 == null && l2 == null) return null;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode curr1 = l1, curr2 = l2;
        
        while(curr1 != null && curr2 != null){
            if(curr1.val < curr2.val){
                prev.next = curr1;
                prev = curr1;
                curr1 = curr1.next;
            }else{
                prev.next = curr2;
                prev = curr2;
                curr2 = curr2.next;
            }
        }
        
        if(curr1 == null) prev.next = curr2;
        else   prev.next = curr1;
        
        return dummy.next;
    }

    // LC-23  MERGE K SORTED LISTS (IMPORTANT) - yaha bhi mergeTwoSortedLists() ka use hoga
    // 1st way (iski complexity thodi jada hogi as more time tranverse kar rahe ha haam)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        ListNode si = lists[0], ei = lists[1];
        
        for(int i = 1; i < lists.length; i++){
            si = lists[0];
            ei = lists[i];
            ListNode nhead = mergeTwoSortedList(si, ei);
            lists[0] = nhead;
        }
        
        return lists[0];
    }
    
    
    //2nd way(iski complexity thodi kaam hogi 1st way ke comparison me, as traverse kam kar rahe ha haam yaha )
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        ListNode ans = mergeKlists(lists, 0, lists.length-1);
        
        return ans;
    }
    
    public ListNode mergeKlists(ListNode[] lists, int si, int ei){
        if(si + 1 == ei){
            return mergeTwoSortedList(lists[si], lists[ei]);
        }
        if(si == ei){
            return lists[si];
        }
        int mid = (si+ei)/2;
        ListNode recans1 = mergeKlists(lists, si, mid);
        ListNode recans2 = mergeKlists(lists, mid+1, ei);
        
        return mergeTwoSortedList(recans1, recans2);
    }
    
    public ListNode mergeTwoSortedList(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1 == null && l2 == null) return null;
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode curr1 = l1, curr2 = l2;
        
        while(curr1 != null && curr2 != null){
            if(curr1.val < curr2.val){
                prev.next = curr1;
                prev = curr1;
                curr1 = curr1.next;
            }else{
                prev.next = curr2;
                prev = curr2;
                curr2 = curr2.next;
            }
        }
        
        if(curr1 == null) prev.next = curr2;
        else   prev.next = curr1;
        
        return dummy.next;
    }


    // LC- 141  (CYCLE DETECTION)
    // SIMPLE APPROACH (USING HASHMAP)
    public boolean hasCycle(ListNode head){
        HashMap<ListNode, Integer> hm = new HashMap<>();
        ListNode temp = head;

        while(temp != null){
            if(hm.getOrDefault(temp, 0) == 0){
                hm.put(temp, 1);
            }else{
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    

    // SMART APPROACH (USING SLOW & FAST POINTER) - (CYCLE DETECTION)
    public boolean hasCycle(ListNode head){
        if(head == null) return false;
        
        boolean ans = false;
        
        ListNode slow = head, fast = head.next;
        
        while(slow != null && fast != null && fast.next != null && fast != slow){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        if(slow == fast) ans = true; 
        return ans;
    }


//====================================================================================

// LC- 160. Intersection of Two Linked Lists

    public int size(ListNode head){
        int count = 0;
        ListNode curr = head;
        while(curr != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }
    public ListNode getIntersectionNode(ListNode h1, ListNode h2){
        int size1 = size(h1);
        int size2 = size(h2);
        
        if(size1 < size2) return getIntersectionNode(h2,h1);
        
        ListNode c1 = h1, c2 = h2;
        int diff = size1-size2;
        for(int i = 0; i < diff; i++)   c1 = c1.next;
        
        while(c1 != null && c2 != null && c1 != c2){
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return c1;      
    }
     



//====================================================================================


// LC- 25. Reverse Nodes in k-Group

    
    static ListNode th = null, tt = null ; // th : temporary head, tt : temporary tail
    static ListNode fh = null, ft = null;  // fh : final head, ft : final tail
    
    public ListNode reverseKGroup(ListNode head, int k){ 
        if(head == null) return null;
        
        th = null; tt = null;
        fh = null; ft = null;
        return  solve(head, k);
    }
    
    public static ListNode solve(ListNode root, int k){
        ListNode curr = root;
        ListNode forw = root;
        int size = size(root);

        for(int count = 0; (count < size) && (size-count >= k);  ){
            tt = null; th = null;
            
            for(int i = 0; i < k; i++){
                forw = curr.next;
                addNodeFirst(curr);
                curr = forw;
                count++;
            }
            
            if(ft == null && fh == null){
                ft = tt;
                fh = th;
            }else{
                fh.next = tt;
                fh = th;
            }
        }
        
        
        fh.next = curr;
        
        
        return ft;
        
        
    }
    
    public static void addNodeFirst(ListNode root){
        if(tt == null && th == null){
            tt = th = root;
        }else{
            root.next = tt;
            tt = root;
        }
    }
    
    public static int size(ListNode root){
        ListNode curr = root;
        int size = 0;
        while(curr != null){
            size++;
            curr = curr.next;
        }
        return size;
    }
    


//====================================================================================



    // LC - 92 : REVERSE LINKEDLIST (tassali se space le ke karo har imp node ko store karo h1,h2,h3,h4 etc..(space lo bharr bhaar ke))
    public ListNode reverseBetween(ListNode head, int left, int right){
       if(head.next == null) return head;
       ListNode dummy = new ListNode(0);         
       dummy.next = head;
       
       ListNode h1 = dummy, h2 = null, h3 = null , h4 = null;
       
       ListNode temp = head;
       for(int i = 0; temp != null; i++){
           if(i <= left-2) h1 = temp;
           if(i <= left-1) h2 = temp;
           if(i <= right-1) h3 = temp;
           if(i <= right) h4 = temp;
           temp = temp.next;
       }
       h4 = h3.next;
       h3.next = null;
       reverse(h2, h3);
       
       // connect list
       h1.next = h3;
       h2.next = h4;
       
       return dummy.next;
    }

    public void reverse(ListNode sp, ListNode ep){ // si : starting pointer, ei : ending pointer
        ListNode prev = null, curr = sp, forv = curr.next;
        while(curr != null){
            curr.next = prev;
            prev = curr;
            curr = forv;
            if(forv != null) forv = forv.next;
        }
    }
    
    




    // LC- 138 COPY LIST WITH RANDOM POINTER 
    //- FUNCTION ME THOD KE KARO(VARIABLE KE NAAM LIKHNE ME BHI SAHULIYAT RAHEGI(imp))
    HashMap<Node, Node> hm = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head == null) return head;
        
        // making a duplicate linkedlist
        Node newhead = makeduplicatell(head);
        
        // alocating random pointer
        alocatingRandomPointer(head, newhead);
        
        return newhead;
    }
    
    public Node makeduplicatell(Node parenthead){
        Node dummy = new Node(-1);
        Node nhead = dummy;
        Node head = parenthead;
        while(head != null){
            Node np = new Node(head.val);
            
            hm.put(head, np);    // hashmap.put(key, value);
            nhead.next = np;
            nhead = nhead.next;
            head = head.next;
        }
        return dummy.next;
    }
    
    public void alocatingRandomPointer(Node oldhead, Node newhead){
        Node h1 = oldhead;
        Node h2 = newhead;
        while(h1 != null){
            Node rptr = h1.random;  // rptr : random pointer
            Node cptr = null;       // cptr : corresponding pointer
            if(rptr != null) cptr = hm.get(rptr);
            h2.random = cptr;
            
            h1 = h1.next;
            h2 = h2.next;
        }
    }























    








    
    
    
    
    
    
    
    
    // LC-445. Add Two Numbers II 
    // i.  vohi (addFirst/addLast/removeFirst) vala tarika use kiya ha (YE INTERVIEW KE LIYE SAHI HA)
    ListNode anshead = null, anstail = null;   

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = reverseList(l1);
        ListNode h2 = reverseList(l2);
        int carry = 0;
        while(h1 != null || h2 != null || carry != 0){
            int a = h1 == null ? 0 : h1.val;
            int b = h2 == null ? 0 : h2.val;
            
            int sum = a + b + carry;
            int rem = sum%10;
            carry = sum/10;
            
            addFirst(rem);
            
            if(h1 != null) h1 = h1.next;
            if(h2 != null) h2 = h2.next;
            
            // ya to ye theen line likh lo ya simply while loop me ek aur condition daal do ->  carry != 0
            // if(h1 == null && h2 == null){       
            //     if(carry != 0)  addFirst(carry);
            // }
        }
        
        return anshead;
    }
    
    public void addFirst(int val){
        ListNode node = new ListNode(val);
        if(anshead == null){
            anshead = anstail = node;
        }else{
            node.next = anshead;
            anshead = node;
        }
    }
    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head, forw = head;
        
        while(curr != null){
            forw = curr.next ;
            curr.next = prev;
            
            prev = curr;
            curr = forw;
        }
        
        return prev;
    }



    // but ese chote chote kaam ke liye addfirst ye pura function likne ki jarurat ni ha, Direct dummy node se kar lo
    // (EXAM TEST KE LIYE SAHI HA- JALDI HOGA CODE KARNA)

    // ii. BY DUMMY WAY
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = reverseList(l1);
        ListNode h2 = reverseList(l2);
        int carry = 0;

        ListNode dummy = new ListNode(-1);
        ListNode  tp = dummy;  // tp : temporary pointer     (tp link -to learn)
        while(h1 != null || h2 != null || carry != 0){
            int a = h1 == null ? 0 : h1.val;
            int b = h2 == null ? 0 : h2.val;
            
            int sum = a + b + carry;
            int rem = sum%10;
            carry = sum/10;
            
            ListNode node = new ListNode(rem);
            tp.next = node;
            tp = node;
            
            if(h1 != null) h1 = h1.next;
            if(h2 != null) h2 = h2.next;
        }
        
        return dummy.next;
    }

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head, forw = head;
        
        while(curr != null){
            forw = curr.next ;
            curr.next = prev;
            
            prev = curr;
            curr = forw;
        }
        
        return prev;
    }



    // ADD TWO LINKED LIST
    public static ListNode addTwoLinkedList(ListNode l1, ListNode l2){
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l1 == null) return l1;

        ListNode h1 = reverse(l1);
        ListNode h2 = reverse(l2);

        ListNode t1 = h1, t2 = h2;
        ListNode anshead = null;

        while(t1 != null || t2 != null || carry != 0){  //NOTE : || ayega yaha pe na ki &&
            int a = t1 == null ? 0 : t1.val;
            int b = t2 == null ? 0 : t2.val;

            int sum = a + b + carry;
            int rem = sum%10;
            carry = sum/10;

            ListNode nn = new ListNode(rem);
            nn.next = anshead;
            anshead = nn;

            if(t1 != null) t1 = t1.next;
            if(t2 != null) t2 = t2.next;
        }

        return anshead;
    }


    //-------------------------------------------


    // SUBTRACT TWO LINKEDLIST  
    // (ye SUM TWO LINKEDLIST se jada important ha agar edge cases deal karne hue to)

    // saare edge cases handle karne ha (v.imp)
    // given two numbers represented by the linked list.
    // Subtract the smaller from the larger one.

    public static Node subLinkedList(Node l1, Node l2) {
        if(l2 == null) return l1;
        if(l1 == null && l2 == null) return null;

        while(l1 != null && l1.data == 0){  // 0 se list suru hui ha to chota karo l1 ko
            l1 = l1.next;
        }
            
        while(l2 != null && l2.data == 0){ // 0 se list suru hui ha to chota karo l2 ko
            l2 = l2.next;
        }
            
        Node x = bigList(l1, l2); // find ki badi list kon si ha and choti list kon si ha
        if(x == l2){
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        } 
        
        Node h1 = reverse(l1); // reverse l1 list them to traverse form left to right
        Node h2 = reverse(l2); // reverse l2 list them to traverse form left to right
        
        Node t1 = h1, t2 = h2;
        int borrow = 0;
        
        Node anshead = null;
        while(t1 != null || t2 != null){  // simply subtract   //NOTE : || ayega yaha pe na ki && 
            int a = t1 == null ? 0 : t1.data;
            int b = t2 == null ? 0 : t2.data;
            
            int diff = borrow + (a - b);
            
            if(diff < 0){
                diff = diff + 10;
                borrow = -1;
            }else{
                borrow = 0;
            }
            
            Node nn = new Node(diff);
            nn.next = anshead;
            anshead = nn;
            
            if(t1 != null) t1 = t1.next;
            if(t2 != null) t2 = t2.next;
        }
        
        while(anshead != null && anshead.data == 0){  // last me jo ans list bani ha usme agge koi 0 ha to hatta do
            anshead = anshead.next;
        }
            
        if(anshead == null){    // null aya iska matlab difference of two list 0 ha na ki null, so we have to return a list with 0 element only
            Node ans = new Node(0);
            return ans;
        }

        return anshead; // final ans return
    }


    public static Node reverse(Node head){
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

    public static Node bigList(Node l1, Node l2){
        long s1 = size(l1);
        long s2 = size(l2);
        
        if(s1 < s2) return l2;
        if(s2 < s1) return l1;
        
        Node t1 = l1;
        Node t2 = l2;
            
        if(s1 == s2){
            while( t1 != null && t2 != null && t1.data >= t2.data ){
            t1 = t1.next;
            t2 = t2.next;
            }
            if(t1 == null) return t1;
            else   return t2;
        }
        return null;
    }

    public static int size(Node head){
        Node temp = head;
        int count = 0;
        while(temp != null){
            temp = temp.next;
            count++;
        }
        return count;
    }




    // MULTIPLICATION OF TWO LINKEDLIST

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l) {
        if(l1 == null || l2 == null)  return new ListNode(0);

        ListNode h1 = reverse(l1);
        ListNode h2 = reverse(l2);

        ListNode t1 = h1, t2 = h2;
        ListNode temp = new ListNode(0);

        while(t2 != null){
            int val = t2.val;
            
            ListNode list1 = multiplyListWithNumber(t1, val);

            temp = sumTwoList(list1, temp);

            t2 = t2.next;
            
        }
      
    }
}