// LC- 1074. Number of Submatrices That Sum to Target

// NOTE 1. : haam use karege countSubarraywithSumK() function jo haam hashmap se solve karte the, which will give us the count of number of subarrays with sum k
// NOTE 2. : haam basically 2-D array ko ek prefix sum(column wise) bana ke 1-D array me store kar lete ha yaha so that 
//           jab haam iss 1-D array ka count subarray with sum k nikalege to ultimately we will end up with finding the sum of 2-D submatrix
//           eg.     0  1  0      ->      0  1  0
//                   1  1  1      ->      1  2  1     now this [1  2  1]  -> (1-D) submatrix is ultimately the sum of this matrix ->  0  1  0
//                   0  1  0                                                                                                          1  1  1 

// complexity : O(n^3) time 
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int count = 0;
        for(int rsi = 0; rsi < n; rsi++){  // rsi : row starting index
            int[] arr = new int[m]; // haar new (rsi) me new arr banaega
            for(int rei = rsi; rei < n; rei++){  // rei : row ending index
                // update row 
                for(int col = 0; col < m; col++){
                    arr[col] += matrix[rei][col];
                }
                
                int num = countSubarraySumK(arr, target);
                count += num;
            }
        }
        return count;
    }
    
    public int countSubarraySumK(int[] arr, int k){
        HashMap<Integer, Integer> hm = new HashMap<>();  // {val, frequency}
        hm.put(0,1);
        int ans = 0, psum = 0;
        for(int i = 0; i < arr.length; i++){
            psum += arr[i];
            if(hm.containsKey(psum-k)){
                int count = hm.get(psum-k);
                ans += count;
            }
            hm.put(psum, hm.getOrDefault(psum,0)+1);
        }
        return ans;
    }


// =======================================================================================================================


 //  LC-363. Max Sum of Rectangle No Larger Than K

    // ye jo ek question KA upgraded version HA i.e 
    // find the Maximum sum subarray having sum less than or equal to given sum (with -ve element)(sirf +ve diye ha to bhot simple ha gfg pe ha (simple AR algo laga lo))
    // concept : haam vo vala kaam karte ha finding all sum rectangle in a matrix(ek base line banane vala jo karte the haam)
    // and then haar 1d array ke liye  maxSumLessThanK() ye function me ans cacluate kar lo apna and sabka max nikal lo 
    // ITS WORST COMPLEXITY IS -> O(N^3 log(N)) 
    public int maxSumSubmatrix(int[][] arr, int k) {
        int n = arr.length, m = arr[0].length;
        int maxsum = -(int)1e9;
        
        for(int i = 0; i < n; i++){
            int[] temp = new int[m];
            for(int j = i; j < n; j++){ 
                
                // O(n) worst case
                for(int idx = 0; idx < m; idx++){
                    temp[idx] += arr[j][idx];   // [j] & [idx] ayega
                }
                
                //O(nlogn) worst case
                int ans = maxSumLessThanK(temp, k);
                maxsum = Math.max(maxsum, ans);
            }
        }
        
        return maxsum;
    }
    
    public int maxSumLessThanK(int[] arr, int k){
        int n = arr.length;
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0); // imp.  [sidha arr[0] == k mil gya to isliye ye jaruri ha]
        
        int maxval = -(int)1e9;
        int psum = 0;
        for(int i = 0; i < n; i++){
            psum += arr[i];
            
            Integer prevsum = ts.ceiling(psum - k);  
            if(prevsum != null){
                maxval = Math.max(maxval, psum - prevsum);
            }
            
            ts.add(psum);  // last me add karna nahi bhulna ha
        }
        
        return maxval;
    }