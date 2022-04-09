// DP - cut set 

// GFG => MCM - MATRIX CHAIN MULTIPLICATION:

// MEMOIZATION:
// since yaha cut element ke bich me se lagege 
    // so, we will use
    //  for(int k = si+1; k < ei; k++){
    //     solve(si, k);
    //     solve(k, ei);
    //  }


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

// GFG - PRINT MCM 

// GFG - Brackets in Matrix Chain Multiplication -> https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1/# 

    // print karana ha to dp haam pair class ki bana lege jisme 
    // {minimum operation , String } ye store karayege
    static class pair{
        int op = 0;
        String s = "";
        pair(int m, String s){
            this.op = m;
            this.s = s;
        }
    }
    
    static String matrixChainOrder(int arr[], int n){
        // code here
        pair[][] dp = new pair[n][n];
        for(int i = 0; i < n; i++) for(int j = 0; j < n; j++) dp[i][j] = null;
        pair ans = solve(arr, 0, n-1, dp);
        
        return ans.s;
    }
    
    static pair solve(int[] arr, int si, int ei, pair[][] dp){
        if(si+1 == ei){
            char ch = (char)('A'+ si);
            // System.out.println(ch + "");
            return new pair(0, ch+"");
        }
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        pair p = new pair((int)1e9, "");
        
        for(int k = si+1; k < ei; k++){
            pair r1 = solve(arr, si, k, dp);
            pair r2 = solve(arr, k, ei, dp);
            int totalop = r1.op + r2.op + arr[si]*arr[k]*arr[ei];
            
            if(p.op > totalop){
                p.op = totalop;
                p.s = "(" + r1.s + r2.s + ")";
            }
        }
        return dp[si][ei] = p;
    }




















//=======================================================================================

// LC-132. Palindrome Partitioning II
 // since : yaha partitioning simple alag alag hogi so
 // we will use
 //  for(int k = si; k < ei; k++){
 //     solve(si, k);
 //     solve(k+1, ei);
 //  }


    // NOTE : agar ye question exam me aya aur pass nahi hua that means hame vo 
    // isPalindrome vala function dp se karna hoga i.e 647. Palindromic Substrings ye vala karna hoga taki O(1) time me nikal sake [isPalindrome(s)]
  
    public int minCut(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] x: dp) Arrays.fill(x, (int)1e9);
        
        int ans = solve(s, 0, n-1, dp);
        return ans;
    }
    
// memoization (tabulation bhi kar sakte ha with simple apply vvrrc)
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
    
 // this method works in O(n) solution 
    public boolean isPalindrome(String s, int si, int ei){
        while(si < ei){
            if(s.charAt(si) == s.charAt(ei)) { si++; ei--;}
            else return false;
        }
        return true;
    }  
    
    
    // usi jagah agar ese karege to O(1) me solve ho jayega isPalindrome()
//     public boolean isPalindrome(String s, int si, int ei){
//         int n = str.length();
//         char[] ch = s.toCharArray();
//         boolean[][] dp = new boolean[n][n];
        
//         for(int gap = 0; gap < n; gap++){
//             for(int i = 0, j < gap; (i < n && j < m); i++, j++ ){
//                 if(gap == 0) dp[i][j] = true;
//                 else if(gap == 1 && s[i] == s[j]) dp[i][j] = 0;
//                 else{
//                     if(s[i] == s[j] && dp[i+1][j-1]) dp[i][j] = true;
//                     else dp[i][j] = false;
//                 }
//             }
//         }
//     }   




//===========================================================================

// LC-312. Burst Balloons (V.Imp for Interview)

    // yahi tarike se karo code (aur kahi se mat karna confusion hogi)
    // concept :
    // 1. cut set ka question ha and yaha kuch alag type ka cut lagana padega i.e [k = si; k <= ei] as hame sare posibilities check karni ha haar ballon par
    
    // 2. let say we are at kth position then recurssion is 
    // hamari window [si,ei] me Kth position vale ballon ko last me phodne par kitna max coins bana sakte ha ans is :-(YE LAST ME BALLON PHODNE VALA POINT IS MOST IMPORTANT ISSI KA QUESTION HA YE)
    //  lastburstcoins = arr[si-1]*arr[k]*arr[ei+1]     // si-1 and ei+1 isliye liya as jab [si,ei] is window me sab phod dege to uske baad to sirf Kth ballon and (si-1) ballon and (ei+1) ballon hi bachega that why we have taken this
    // totalcoins === [left subarray me sare ballon ko phodne ki total kimat] 
    //                                        + 
    //                [right subarray me sare ballon ko phodne ki total kimat] 
    //                                        +
    //                [abhi Kth last vale ballon ko phodne ki total kimat]
    // i.e,
    //  totalcoins = solve(si,k-1) + solve(k+1,ei) + (arr[si-1]*arr[k]*arr[ei+1]);
    
    // 3. since arr[si-1] , arr[ei+1] me out of bound ki dikkat hogi isliye naya array banayege n+2 size ka
    // jisme newarr[first index] = 1  hoga and  newarr[last index] = 1 hoga
    // and solve(newarr, 1, n) hoga
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = 1; arr[n+1] = 1;
        for(int i = 0; i < n; i++) arr[i+1] = nums[i];
        
        int[][] dp = new int[n+2][n+2];
        int ans = solve(arr, 1, n, dp);
        return ans;
    }
    
    // solve()-> (si se ei) tak ke sare ballon ko phodne ki total kimat return karega
    public int solve(int[] arr, int si, int ei, int[][] dp){
        if(si > ei) return dp[si][ei] = 0;
        if(si == ei) return dp[si][ei] =  (arr[si-1]*arr[si]*arr[si+1]);
        
        if(dp[si][ei] != 0) return dp[si][ei];
        
        int maxans = 0, n = arr.length;
        for(int k = si; k <= ei; k++){
            int leftans = solve(arr, si, k-1, dp);
            int rightans = solve(arr, k+1, ei, dp);
            
            int lastburstcoins = (arr[si-1]*arr[k]*arr[ei+1]);
            int totalcoin = leftans + rightans + lastburstcoins;
                                                                 
            maxans = Math.max(maxans, totalcoin);
        }
        return dp[si][ei] = maxans;
    }
    
    
    
    // TABULATION:
    
//     public int solve(int[] arr, int SI, int EI, int[][] dp){
//         int n = arr.length-2;  // imp ha ye as upar se n+2 vala size aya tha
        
//         for(int gap = 0; gap <= n; gap++){
//             for(int si = 1,ei = gap; (si <= n && ei <= n); si++, ei++){
//                 if(si > ei) { 
//                     dp[si][ei] = 0; 
//                     continue;
//                 }
                
//                 if(si == ei) {
//                     dp[si][ei] =  (arr[si-1]*arr[si]*arr[si+1]); 
//                     continue;
//                 }

//                 if(dp[si][ei] != 0) return dp[si][ei];

//                 int maxans = 0;
//                 for(int k = si; k <= ei; k++){
//                     int leftans = dp[si][k-1]; // solve(arr, si, k-1, dp);
//                     int rightans = dp[k+1][ei]; // solve(arr, k+1, ei, dp);

//                     int lastburstcoins = (arr[si-1]*arr[k]*arr[ei+1]);
//                     int totalcoin = leftans + rightans + lastburstcoins;

//                     maxans = Math.max(maxans, totalcoin);
//                 }
                
//                 dp[si][ei] = maxans;                
//             }
//         }
        
//         return dp[SI][EI];
//     }    
    
    

//=================================================================================

// LC-1039. Minimum Score Triangulation of Polygon

    // vohi cut vala concept lagega where, [k = si+1 and k < ei]
    // dry run karo and find the recurssion
    // iss question ke liye yaad hi rakh lena ki recurssion karte vakt theen triangle vala system banega
    // solve(arr, si, k) tak ka leftans
    // solve(arr, k, ei) tak ka rightans &
    // hamare level ka jo triangle bana ha i.e [si,k,ei] se milkar jo triangle bana ha uska score = arr[si]*arr[k]*arr[ei]  bhi add karna ha remember
    
    public int minScoreTriangulation(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(arr, 0, n-1, dp);
        return ans;
    }
    
    // memoization
    public int solve(int[] arr, int si, int ei, int[][] dp){
        if(si+1 == ei) return dp[si][ei] = 0;
        
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int min = (int)1e9;
        for(int k = si+1; k < ei; k++){
            int leftans = solve(arr, si, k, dp);
            int rightans = solve(arr, k, ei, dp);
            int totalscore = leftans + (arr[si]*arr[k]*arr[ei]) + rightans;
            
            min = Math.min(min, totalscore);
        }
        return  dp[si][ei] = min;
    }
    
    // tabulation(vvrrc)
    public int solve(int[] arr, int SI, int EI, int[][] dp){
        int n = arr.length;
        for(int gap = 0; gap < n; gap++){
            for(int si = 0, ei = gap; (si < n && ei < n); si++, ei++){                                       if(si+1 == ei) { dp[si][ei] = 0; continue;}

                int min = (int)1e9;
                for(int k = si+1; k < ei; k++){
                    int leftans = dp[si][k]; // solve(arr, si, k, dp);
                    int rightans = dp[k][ei]; // solve(arr, k, ei, dp);
                    int totalscore = leftans + (arr[si]*arr[k]*arr[ei]) + rightans;

                    min = Math.min(min, totalscore);
                }
                 dp[si][ei] = min;
                
            }
        }
        return dp[SI][EI];

    }
    
     