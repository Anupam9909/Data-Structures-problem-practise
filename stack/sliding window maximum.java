 // LC - 239. Sliding Window Maximum
 
    // TIME - O(N) time complexity (100% true) 
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
    
    