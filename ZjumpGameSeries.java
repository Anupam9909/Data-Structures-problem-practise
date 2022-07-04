
// jump game - Q1, Q3(Q1 ka bhai) easy ha compare to Q2, Q4(Q2 ka bhai) 
// 55. Jump Game-I

// greedy way - very optimise
    // II WAY solution greedy (ye sorting vali greedy nahi ha)
    // yaha haam linearly traverse karege and haar step me apni ek maximum boundry banayege jaha tak haam ja sakte ha and usko update karte rahege aage 
    // if vo boundry n-1 ko touch kar gayi to return true 
    // else agar haam maximum boundry ko nahi le ja paye to the boundry then return false;
    // O(N) solution worst case me bhi (n) presise hoga
    public boolean canJump(int[] arr){
        int n = arr.length;
        int maxboundry = 0;
        
        for(int i = 0; i < n; i++){
            // increase boundry
            maxboundry = Math.max(maxboundry, i + arr[i]);
            if(maxboundry >= n-1) return true;
            
            // check for boundry
            if(i >= maxboundry) return false;
        }
        
        return true;
        
    }


 // II way simple way recurrsion & dp
//     public boolean canJump(int[] nums) {
//         int[] dp = new int[nums.length+1];
//         Arrays.fill(dp, 2);
//         int ans= solve(nums, 0, dp);
        
//         return ans == 1 ? true : false;
//     }
    
//     public int solve(int[] nums, int idx, int[] dp){
//         if(idx == nums.length-1) return dp[idx] = 1;
        
//         if(dp[idx] != 2) return dp[idx];
//         int ans = 0;
//         for(int jump = 1; jump <= nums[idx]; jump++){
//             if(idx+jump <= nums.length)  ans = solve(nums, idx+jump, dp);
//             if(ans == 1) break;
//         }
        
//         return dp[idx] = ans;
//     }

//=============================================================================================
 // LC-45 JUMP GAME - II

// O(N) Solution
    // iska bhi greedy solution exist karta ha (yaha bhi sorting vala nahi ha greedy)
    // yaha BFS lagega pura 
    // jisme que me 0 daal do pehle and then jitni jump kar sakte ha vo next level queue me daal do

    public int jump(int[] arr){
        int n = arr.length, level = 0;
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(0);
        visited[0] = true;
        while(q.size() != 0){
            int s = q.size();
            while(s-- > 0){
                int rem = q.removeFirst();
                
                if(rem == n-1) return level;
                for(int i = rem; (i <= rem + arr[rem] && i < n); i++){
                    if(!visited[i]){
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
            level++;
        }
        
        return 0;
    }
    
    
    
    // O(N^2) solution
    
    // I way solution (PASSED) simple way jo pepcoding me sikhaya tha
//     public int jump(int[] nums) {
//         int n = nums.length;
//         int[] dp = new int[n+1];
//         Arrays.fill(dp, -1);
//         int ans = solve(nums, 0, dp);
        
//         return ans;
//     }
    
//     public int solve(int[] nums, int idx, int[] dp){
//         if(idx == nums.length-1) return dp[idx] = 0;
        
//         if( dp[idx] != -1) return  dp[idx];
        
//         int ans = (int)1e9;
//         for(int jump = 1; jump <= nums[idx]; jump++){
//             if(idx+jump < nums.length){
//                 ans = Math.min(ans, solve(nums, idx+jump, dp));
//             }   
//         }
        
//         return  dp[idx] = ans+1;
            
//     }

//==============================================================================

// LC-1306. Jump Game III
// iska greedy solution exist ni karta 

// O(N) Solution 
// ye question simple dfs hi ha i.e use recursion & visited ka ha array
// simply ek travel kar lo backtracking laga ke visited le ke
// jaha 0 mile return true;
    
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        
        return solve(arr, start, visited);
        
    }
    
    public boolean solve(int[] arr, int idx, boolean[] visited){
        if(idx < 0 || idx >= arr.length) return false;
        if(arr[idx] == 0) return true;
        
        visited[idx] = true;
        
        int t = idx-arr[idx];
        if(t >= 0 && !visited[t] ) if(solve(arr, t, visited)) return true;
        
        int p = idx+arr[idx];
        if(p < arr.length && !visited[p]) if(solve(arr, p, visited)) return true;
        
        visited[idx] = false;
        
        return false;
    }


//=============================================================================
// LC- 1345. Jump Game IV

// BFS SOLUTION PURA WORK KAREGA 
public int minJumps(int[] arr){
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i < n; i++){
            hm.putIfAbsent(arr[i], new ArrayList<>());
            hm.get(arr[i]).add(i);
        }
        
        boolean[] visited = new boolean[n];
        int ans = solve(arr, 0, visited, hm);
        
        return ans;
    }
    
    public int solve(int[] arr, int src, boolean [] visited, HashMap<Integer, ArrayList<Integer>> hm){
        LinkedList<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;
        int n = arr.length, level = 0;
        
        while(q.size() != 0){
            int s = q.size();
            while(s-- > 0){
                int rem = q.removeFirst();
                
                if(rem == n-1) return level;
                
                int forw = rem+1;
                if(forw < n && !visited[forw]) {visited[forw] = true ; q.add(forw);}
                
                int back = rem-1;
                if(back >= 0 && !visited[back]) {visited[back] = true; q.add(back);}
                
                for(int x : hm.get(arr[rem])){
                    if(x != forw && x != back && !visited[x]){
                        visited[x] = true;
                        q.add(x);
                    }
                }
                
                hm.put(arr[rem], new ArrayList<>());  // ye karna jaruri ha nahi to TLE ayega. so, to prevent redundant values we do this (null ni put kar sakte as null exception aa jata ha);
            }
            level++;
        }
        return level; // yah ni ayega kabhi
    }
    
    
    
    
    
    // NOTE: YAHA DFS WORK NI KARTA BECAUSE KABHI KABHI MIN VALUE BFS SE MILL JAYEGI VO DFS ME TLE DE DEGA . 
    // NOTE : YE DFS SOLUTION WORK NI KAREGA TLE AYEGA  (just idea ho isliye paste kiya ha )
//     public int minJumps(int[] arr) {
//         HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
//         int n = arr.length;
//         for(int i = 0; i < n; i++){
//             hm.putIfAbsent(arr[i], new ArrayList<>());
//             hm.get(arr[i]).add(i);
//         }
        
//         int[] dp = new int[n+1];
//         Arrays.fill(dp, (int)1e9);
//         boolean[] visited = new boolean[n];
//         int ans = solve(arr, 0, visited, hm, dp);
        
//         for(int x : dp) System.out.print(x + " ");
        
//         return ans;
//     }
    
//     public int solve(int[] arr, int idx, boolean[] visited, HashMap<Integer, ArrayList<Integer>> hm, int[] dp){
//         if(idx == arr.length-1) return dp[idx] = 0;
        
//         if(dp[idx] != (int)1e9) return dp[idx];
//         int ans = (int)1e9;
//         visited[idx] = true;
        
//         int forw = idx+1;
//         if(forw < arr.length && !visited[forw]){
//             ans = Math.min(ans, solve(arr, forw, visited, hm, dp)+1);
//         } 
        
//         int back = idx-1;
//         if(back >= 0 && !visited[back]){
//             ans = Math.min(ans, solve(arr, back, visited, hm, dp)+1);
//         } 
        
//         for(int x : hm.get(arr[idx])){
//             if(!visited[x] && x != idx && x != forw && x != back){
//                 ans = Math.min(ans, solve(arr, x, visited, hm, dp)+1);
//             } 
//         }
        
//         visited[idx] = false;
        
//         return dp[idx] = ans;
//     }
    















































