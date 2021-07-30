public class toLearnCode{
    // NGOR -> greater i.e bada vala chote vale ko pop karayega
    //      -> loop move from 0 to n-1
    public void NGOR(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, (int)1e9);

        for(int i = 0; i < arr.length; i++){
            while(st.size() != 0 && arr[i] > arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // NGOL -> greater i.e bada vala chote vale ko pop karayega
    //      -> loop move from n-1 to 0, i.e ulta
    public void NGOL(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);
        int n = arr.length;

        for(int i = n-1; i >= 0; i--){
            while(st.size() != 0 && arr[i] > arr[st.peek()]){
                ans[st.pop()] = i; 
            }
            st.push(i);
        }
    }

    //----------------------------------------------------------------

    // NSOR -> smaller i.e chotta vala bade vale ko pop karayega
    //      -> loop move from 0 to n-1
    public void NSOR(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(arr, (int)1e9);

        for(int i = 0 ; i < arr.length; i++){
            while(st.size() != 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // NSOL -> smaller i.e chotta vala bade vale ko pop karayega
    //      -> loop move from n-1 to 0 , i.e olta
    public void NSOL(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for(int i = arr.length-1; i >= 0; i--){
            while(st.size()!= 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }


    while(t1 != null && t2 != null){
        int a = t1 == null ? 0 : t1.val;
        int b = t2 == null ? 0 : t2.val;
        
        int val = a-b+borrow;
        
        if(val < 0){
            val = val + 10;
            borrow = -1;
        }else{
            borrow = 0;
        }
        
        ListNode nn = new ListNode(val);
        nn.next = anshead;
        anshead = nn;
        
        t1 = t1.next;
        t2 = t2.next;
    }
}