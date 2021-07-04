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
    
     
     















}