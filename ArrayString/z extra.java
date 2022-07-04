 // LC - 209. Minimum Size Subarray Sum
 
    // yaha vo AR algo lag jayega ie.
    // moving (ei) pointer -> invalid invalid invalid invalid valid
    // and then moving (si) pointer -> valid valid valid (meanwhile caculating minlen) invalid
    // ye tarike se ho jayega 
    // ek psum le lena ha which tells the sum of the window
    public int minSubArrayLen(int target, int[] arr) {
        int n = arr.length, si = 0, ei = 0;
        int psum = 0, minlen = (int)1e9;
        
        while(ei != n){
            psum += arr[ei];
            ei++;
            
            while(psum >= target){
                minlen = Math.min(minlen, ei-si);
                
                psum -= arr[si];
                si++;
            }
        }
        return (minlen==(int)1e9) ? 0 : minlen;
    }
    
    
    //===========================================================================================================

    // LC-862. Shortest Subarray with Sum at Least K  (can come ek baar dek lena last time pe)

    // CONCEPT : AR ALGORITHM JESA HI LAGEGA BASS YAHA YE DHYAN RAKHNA HA KI HAAME 
    // 1. PSUM (PREFIX SUM) LE KE CHALNA HA NA KI WINDOW KA SUM
    // 2. HAAR BAAR HAAME EK MONOTONIC(STRICLY) INCREASING QUE(ARRAY NI LE SAKTE AS REMOVELAST() KARNA HA) CHAHIYE SO HAAM EK EXTRA CONDITION LIKHEGE KI :-
    //             while(que.size() != 0 && psum <= que.getLast()[0])   que.removeLast();
    
    // then at last add kar do pair{sum, index}
        
    public int shortestSubarray(int[] arr, int k) {
        int n = arr.length;
        LinkedList<long[]> que = new LinkedList<>();  // pair : {sum, index}
        long psum = 0;  // ye actual vala prefix sum ha
        long minlen = (long)1e9+7;
        int ei = 0;   // ending index
        
        while(ei != n){
            // acquire
            psum += arr[ei];
            ei++;
            if(psum >= k) minlen = Math.min(minlen, ei);  // ye ke extra update ka lena minlen ko eg [2,-1,2]
            
            // remove
            while(que.size() != 0 && psum-que.getFirst()[0] >= k){   // yaha haam (si) ko badayege aage 
                long[] rem = que.removeFirst();
                minlen = Math.min(minlen, ei - rem[1]);
             // psum = sum - rem[0]; // haam actual me prefix sum ko subtract ni karna as vo to psum ha window sum nahi ha
            }
            
            // bass ye extra ha -> as hame monotonic banana ha que ko so if(psum <= startingnodeSum) removelast()
            while(que.size() != 0 && psum <= que.getLast()[0]){
                que.removeLast();
            }
            
            que.addLast(new long[]{psum, (long)ei});
        }
        
        
        return (minlen==(long)1e9+7) ? -1 : (int)minlen;
    }
    