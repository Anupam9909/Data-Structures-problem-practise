// DP - cut set 

// GFG => MCM - MATRIX CHAIN MULTIPLICATION:

// MEMOIZATION:
// since yaha cut element ke bich me se lagege 
// so, use [k = si+1  to k < ei]

    public static int matrixMultiplication(int N, int arr[]){
        int[][] dp = new int[N+1][N+1];
        for(int[] x : dp) Arrays.fill(x, (int)1e9);
        int ans = solve(arr, 0, N-1, dp);
        return ans;
    }
    
    public static int solve(int[] arr, int si, int ei, int[][] dp){
        if(si+1 == ei) return dp[si][ei] = 0;

        if(dp[si][ei] != (int)1e9) return dp[si][ei];
        
        int minans = (int)1e9;
        for(int k = si+1; k < ei; k++){
            int recans1 = solve(arr, si, k, dp);
            int recans2 = solve(arr, k, ei, dp);
            
            int val = recans1 + recans2 + (arr[si]*arr[k]*arr[ei]);
            
            minans = Math.min(minans, val);
        }
        // System.out.println(minans);
        return dp[si][ei] = minans;
    }


// TABULATION:
// NOTE : yaad rakhna CUT-DP SET me -> GAP STRATERGY LAGTI HA TABULATION KARTE VAKT (YE RULE HA MAAN LO BASS)

    public int matrixMultiplication(int N, int arr[]){
        int[][] dp = new int[N+1][N+1];
        for(int[] x : dp) Arrays.fill(x, (int)1e9);
        int ans = solve(arr, 0, N-1, dp);
        return ans;
    } 

    public int solve(int[] arr, int SI, int EI, int[][] dp){
        int n = arr.length;
        
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; (si < n && ei < n); si++, ei++){
                if(si+1 == ei) {dp[si][ei] = 0; continue;}
                
                int minans = (int)1e9;
                for(int k = si+1; k < ei; k++){
                    int recans1 = dp[si][k]; // solve(arr, si, k, dp);
                    int recans2 = dp[k][ei]; //solve(arr, k, ei, dp);
                    
                    int val = recans1 + recans2 + (arr[si]*arr[k]*arr[ei]);
                    
                    minans = Math.min(minans, val);
                }
                
                dp[si][ei] = minans;                
            }
        }
        
        return dp[SI][EI];
    }


//=================================================================================

// LC-























//=======================================================================================

// LC-132. Palindrome Partitioning II
    // NOTE : agar ye question exam me aya aur pass nahi hua that means hame vo 
    // isPalindrome vala function dp se karna hoga i.e 647. Palindromic Substrings ye vala karna hoga taki O(1) time me nikal sake [isPalindrome(s)]
  
    public int minCut(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] x: dp) Arrays.fill(x, (int)1e9);
        
        int ans = solve(s, 0, n-1, dp);
        return ans;
    }
    
    public boolean isPalindrome(String s, int si, int ei){
        while(si < ei){
            if(s.charAt(si) == s.charAt(ei)) { si++; ei--;}
            else return false;
        }
        return true;
    }    
    

    public int solve(String s, int si, int ei, int[][] dp){
        if(si >= ei) return dp[si][ei] = 0;
        if(dp[si][ei] != (int)1e9) return dp[si][ei];
        
        if(isPalindrome(s, si, ei)) return 0;
        
        int mincut = (int)1e9;
        for(int k = si; k < ei; k++){
            if(isPalindrome(s, si, k)){  // left rec call check kar lo agar palindrome ha to hi ander aao and dusre ko check karo varna agar nahi ha to dusre ko call karke duplicate calls lagegi
                int recans = solve(s, k+1, ei, dp);
                mincut = Math.min(mincut, recans+1);
            }
        }
        return dp[si][ei] = mincut;
    }
    
    
  // for loop ke ander ye na karke upar vala kiya ha so as to reduce rec. call VARNA TLE AYEGA PAKKA 
    // int recans1 = solve(s, si, k, dp);
    // int recans2 = solve(s, k+1, ei, dp);
    // int totalcut = recans1 + recans2 + 1;
    // mincut = Math.min(mincut, totalcut);
    
    