 // LC - 239. Sliding Window Maximum
   // DEQUE WAY
   // 1. deque me hamesha window se jada size nahi hona chahiye, agar bada ho gaya(dq.size() se nahi pata lagea, index se hi pata lagega)
   // 2. deque me hamesha values decreasing order me sorted rahengi starting se taki greatest element dq me front pe ho
   // 3. issi order me code likho 
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        Deque<Integer> dq = new ArrayDeque<>();  // stores index (index hi daalna hai)
        int[] ans = new int[n-k+1];
        int idx = 0;

        for(int i = 0; i < n; i++){
            //1. removing the index outside window
            while(dq.size() != 0 && dq.peekFirst() <= i-k){
                dq.removeFirst();
            }

            //2. remove from right which are smaller than arr[i]
            while(dq.size() != 0 && arr[dq.peekLast()] < arr[i]){
                dq.removeLast();
            }

            dq.addLast(i);

            // get answer from front: valid window check lagega ek bass
            if(i >= k-1){ 
                ans[idx++] = arr[dq.peekFirst()];
            }
        }
        return ans;
    }


    // STACK WAY
    // TIME - O(N) time complexity (100% true)  but Space: O(N)
    // because as j pointer haar baar i se start ni ho raha vo ngor pe hi rahega and window ke ander hi rahega and haar baar at max 1 jump hi kar sakta ha
    // for worst case also -> first loop me j bhale hi pura window traverse kar le but agle sare window me bass max 1 jump hi karega 
    public int[] maxSlidingWindow(int[] arr, int k){
        int n = arr.length;
        int[] ngor = new int[n]; // storing the index in ngor : next greater on right
        Arrays.fill(ngor, n);
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ngor[st.pop()] = i;
            }
            st.push(i);
        }
        
        int[] ans = new int[n-k+1];
        int j = 0;  // j ko yahi pe declare karna ha so that j pointer ko baar baar jump na karna pade
        for(int i = 0; i+k <= n; i++){
            if(j < i) j = i;  // if in case (j) piche reh gya to usko i pe le aao(imp to write eg[1,-1],k=1)
            
            while(ngor[j] < i+k){
                j = ngor[j];
            }
            ans[i] = arr[j];
        }
        return ans;
    }
    
    
