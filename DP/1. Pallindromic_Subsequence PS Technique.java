// PS TECHNIQUE

// NOTE: haam garantee nahi de sakte ki (Si != Sj) ke to 2 calls hagegi ya 
(Si = Sj) ke to 1 call lagegi ye technique bass ye batati ha ki:-
// JAB BHI KISSI KISSI QUESTION ME STRING ME PALLINDROME SE RELATED KAAM KARNA HA YA PHIR
// KISSI QUESTION ME 2 ALAG STRING DI HO  YA 2 ALAG ARRAY DIYE HO QUESTION ME UNME KUCH KARNA HA
// AND HAAME USME KUCH NIKALNA HO TO YE TARIKA LAGA SAKTE HA 

// AND YE BHI HO SAKTA HA KI  [(Si != Sj) YA (Si = Sj)]  or [(arri != arrj) YA (arri = arrj)] 
// inki baat na ho and bass haar level pe 3 call lag jaye ye bhi ho sakta ha 
// so bass PS technique ek concept ha sochne ka easy and best way ha and v.imp bhi



//LC - 516. Longest Palindromic Subsequence

// BY PS TECHNIQUE
    public int longestPalindromeSubseq(String str){
        if(str.length() == 0) return 0;
        int size = str.length();
        int[][] dp = new int[size+1][size+1];
        
        for(int i = 0; i < size; i++)   Arrays.fill(dp[i], -1);
        return solve_memo(str, 0, size-1, dp);

        // for(int i = 0; i < size; i++)   Arrays.fill(dp[i], 0);  // yaha bhi agar -1 value kiya to galat ans ayega   
        // return solve_tabu(str, 0, size-1, dp);
    }

    public int solve_memo(String str, int i, int j, int[][] dp){
        if(i >= j) return dp[i][j] = (i == j) ? 1 : 0;

        if(dp[i][j] != -1) return dp[i][j];
        
        int ans = 0;
        if(str.charAt(i) == str.charAt(j)){
            int recans = solve_memo(str, i+1, j-1, dp);
            ans = recans + 2;
        }else{
            int leftans = solve_memo(str, i+1, j, dp);
            int rightans = solve_memo(str, i, j-1, dp);

            ans = Math.max(leftans, rightans);
        }

        return dp[i][j] = ans;
    }

// TABULATION:

//     public int solve_tabu(String str, int I, int J, int[][] dp){
      // initially dp me 0 hi fill hoga
//         int size = str.length();
//         for(int gap = 0; gap < size; gap++){
//             for(int i = 0, j = gap; (i < size && j < size); i++, j++){
//                 if(i >= j){
//                     dp[i][j] = (i == j) ? 1 : 0;
//                     continue;
//                 } 
                
//                 int ans = 0;
//                 if(str.charAt(i) == str.charAt(j)){
//                     int recans = dp[i+1][j-1];  // solve_memo(str, i+1, j-1);
//                     ans = recans + 2;
//                 }else{
//                     int leftans = dp[i+1][j];  // solve_memo(str, i+1, j);
//                     int rightans = dp[i][j-1];  // solve_memo(str, i, j-1);

//                     ans = Math.max(leftans, rightans);
//                 }
//                 dp[i][j] = ans;
//             }
//         }
//         return dp[I][J];
//     }
    

//========================================================================================

// count palindromic subsequence
yaha hamm har level par nikal lege and then dry run karo condition ke liye ni aya to ratt lo solution
long countPS(String str){ 
        // iss question me haar level pe 3 call lagege as hame count nikalna ha so sare call lagane padege
        long[][] dp = new long[str.length()+1][str.length()+1];
        for(long[] x : dp) Arrays.fill(x, -1);
        
        long ans = solve(str, 0, str.length()-1, dp);
        return ans;
    }
    
    public long solve(String s, int i, int j, long[][] dp){
        if(i > j) return dp[i][j] = 0;
        if(i == j) return dp[i][j] = 1;
        
        if(dp[i][j] != -1) return dp[i][j];
        long ans = 0 ;
        
        long x = solve(s, i+1, j-1, dp);
        long y = solve(s, i+1, j, dp);
        long z = solve(s, i, j-1, dp);
        
        if(s.charAt(i) == s.charAt(j)) ans = y+z+1;  // (x+1) + (y + z - x);  
        else ans =  y + z - x; // (x + y + z) - x;
        
        return dp[i][j] = ans;
    }


//=========================================================================================

// EXTRA QUESTION:
// LC-1771. Maximize Palindrome Length From Subsequences

 // pura concept vohi ha finding longest palindrome sequence 
    // tabu se kar lo chota rahta ha 
    // bass jab bhi 
    // Si == Sj ho then if(i is between w1.length) and (j between w2.length) tabhi haam apna answer mangege 
    public int longestPalindrome(String w1, String w2){
        int n = w1.length(), m = w2.length(), N = n+m;
        int[][] dp =  new int[N][N];
        StringBuilder s = new StringBuilder();
        s.append(w1);
        s.append(w2);
        String str = s.toString();
        int myans = 0;
        
        for(int gap = 0; gap < N; gap++){
            for(int i = 0, j = gap; i < N && j < N ; i++, j++){
                if(i >= j){
                    dp[i][j] = i==j ? 1 : 0;
                    continue;
                }
                int ans = 0;
                
                if(str.charAt(i) == str.charAt(j)){
                    ans = dp[i+1][j-1]+2;
                    
                    if((i >= 0 && i < n) && (j >= n && j < n+m)){  // sirf ye condition extra lagegi iss question me 
                       myans = Math.max(myans, ans);  
                    } 
                       
                }else{
                    ans = Math.max(dp[i+1][j], dp[i][j-1]);
                }
                dp[i][j] = ans;
            }
        }
        
        return myans;
    }
    

//==========================================================================



// 115. Distinct Subsequences

// IS QUESTION ME PALLINDROME SE RELATED KAAM NI HA BUT YAHA 2 STRING ME HAME DISTINT SUBSEQUENCE 
// BANANA HA SO HAAM ESE KAR SAKTE HA

public int numDistinct(String s, String t){
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        int ans = solve(s, t, 0, 0, dp);
        
        return ans;
    }
    
    public int solve(String s, String t, int i, int j, int[][] dp){
        if(j == t.length()) return dp[i][j] = 1;
        else if(i == s.length()) return dp[i][j] = 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        
        int ans = 0;
        if(s.charAt(i) == t.charAt(j)){
            ans += solve(s, t, i+1, j+1, dp);
            ans += solve(s, t, i+1, j, dp);
        }else{
            ans += solve(s, t, i+1, j, dp);
        }
        
        return dp[i][j] = ans;
    }


//=========================================================================================

// 1143. Longest Common Subsequence

public int longestCommonSubsequence(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int[] x : dp) Arrays.fill(x,-1);
        
        return solve(s1, s2, 0, 0, dp);
    }
    
    public int solve(String s1, String s2, int i, int j, int[][] dp){
        if(i >= s1.length() || j >= s2.length()) return dp[i][j] = 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        int ans = 0;
        if(s1.charAt(i) == s2.charAt(j)){
            ans = solve(s1, s2, i+1, j+1, dp) + 1;
        }else{
            ans = Math.max(solve(s1, s2, i+1, j, dp), solve(s1, s2, i, j+1, dp));
        }
        
        return dp[i][j] = ans;
    }
    
    

//=========================================================================================

// 1035. Uncrossed Lines

// YAHA HAME VOHI KAAM USE KARNA HA PS TECHNIQUE SO VOHI KAAM HAAM ARRAY ME KAREGE 
// DO ARRAY GIVEN HA UNME 

 public int maxUncrossedLines(int[] A, int[] B){
         int[][] dp = new int[A.length+1][B.length+1];
         for(int[] x : dp) Arrays.fill(x, -1);
         
         int ans = solve(A, B, 0, 0, dp);
         return ans;
     } 
    
    public int solve(int[] A, int[] B, int i, int j, int[][] dp){
        if(i == A.length || j == B.length) return dp[i][j] = 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        int ans = 0;
        if(A[i] == B[j]){
            ans = solve(A, B, i+1, j+1, dp) + 1;
        }else{
            ans = Math.max(solve(A, B, i+1, j, dp) , solve(A, B, i, j+1, dp));
        }
        return dp[i][j] = ans;
    }
    


//=========================================================================================

// 1458. Max Dot Product of Two Subsequences

// ek level pe total 3 calls lagegi 
    public int maxDotProduct(int[] nums1, int[] nums2){
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        int ans = solve(nums1,nums2, 0, 0, dp);
        
        // ye extra karna padega bass when ans == 0 means -ve values 
        if(ans == 0){
            ans = -(int)1e9;
            for(int i = 0; i < nums1.length; i++){
                for(int j = 0; j < nums2.length; j++){
                    ans = Math.max(ans, nums1[i]*nums2[j]);
                }
            }
        }
        return ans;
    }
    
    public int solve(int[] arr1, int[] arr2, int i, int j, int[][] dp){
        if(j == arr2.length || i == arr1.length) return dp[i][j] = 0;
        if(dp[i][j] != -1) return dp[i][j];
        
        int a = solve(arr1, arr2, i+1, j+1, dp);
        int b = solve(arr1, arr2, i+1, j, dp);
        int c = solve(arr1, arr2, i, j+1,dp);
        
        int ans = Math.max(b,c);
        ans = Math.max(ans, a+(arr1[i]*arr2[j]));
        return dp[i][j] = ans;
    }
    
    
    
 //=========================================================================================

//   LC- 72. Edit Distance

// ISS QUESTION ME DISTANCE SE RELATED KUCH NI HA 
// ISS QUESTION ME HAAME BASICALLY EK STRING S1 KO DUSRI STRING S2 ME CONVERT 
// KARNA HA BY EITHER DELTION , REPLACE AND INSERT KARKE 

// MATLAB BASS YE THIN KAAM KARNA HA JAAB (Si != Sj) ke:-
int a = solve(s1, s2, i, j+1, dp)+1;  // insert
int b = solve(s1, s2, i+1, j, dp)+1;  // delete
int c = solve(s1, s2, i+1, j+1, dp)+1; // replace
// thino me 1 add isliye kiya ki kuch operation kar rahe ha haam yaha 1 - 1

// & YE THIN KAAM KARNA HA JAAB (Si == Sj) ke:-
ans = Math.min(ans,solve(s1, s2, i+1, j+1, dp))

// SOLUTION:
 public int minDistance(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int[] x : dp) Arrays.fill(x, (int)1e9);
        
        int ans = solve(s1, s2, 0, 0, dp);
        
        return ans;
    }
    
    public int solve(String s1, String s2, int i, int j, int[][] dp){
        int n = s1.length(), m = s2.length();
        if(i == n) return dp[i][j] = m-j;
        if(j == m) return dp[i][j] = n-i;
        
        if(dp[i][j] != (int)1e9) return dp[i][j];
        int ans = (int)1e9;
        
        if(s1.charAt(i) == s2.charAt(j)){
            ans = Math.min(ans,solve(s1, s2, i+1, j+1, dp));
        }else{
            
            int a = solve(s1, s2, i, j+1, dp)+1;  // insert
            int b = solve(s1, s2, i+1, j, dp)+1;  // delete
            int c = solve(s1, s2, i+1, j+1, dp)+1; // replace
            
            int d = Math.min(a,b);
            ans = Math.min(ans, Math.min(c,d));
        }
        
        return dp[i][j] = ans;
    }
    

    
 //=========================================================================================

//   LC - 44. Wildcard Matching

// 0 : false
    // 1 : true
    // 2 : empty
    public boolean isMatch(String s, String p){
        int[][] dp = new int[s.length()+1][p.length()+1];
        for(int[] x : dp) Arrays.fill(x, 2);
        
        int ans = solve(s, p, 0, 0, dp);
        return ans==0 ? false : true;
    } 
    
    public int solve(String s, String p, int i, int j, int[][] dp){
        int n = s.length(), m = p.length();
        if(i == n && j != m && p.charAt(j) == '*') return dp[i][j] = solve(s, p, i, j+1, dp);
        if(i == n && j == m) return dp[i][j] = 1;
        if(i == n) return dp[i][j] = 0;
        if(j == m) return dp[i][j] = 0;
        
        if(dp[i][j] != 2) return dp[i][j];
        
        int ans = 0;
        if(Character.isLetter(p.charAt(j))){
            if(s.charAt(i) == p.charAt(j)){
                if(solve(s, p, i+1, j+1, dp) == 1) ans = 1;
            }else{
                ans = 0;
            }
        }else{
            if(p.charAt(j) == '*'){
                if(solve(s, p, i+1, j, dp) == 1) ans = 1;
                if(solve(s, p, i+1, j+1, dp) == 1) ans = 1;
                if(solve(s, p, i, j+1, dp) == 1) ans = 1;
            }else{
                if(solve(s, p, i+1, j+1, dp) == 1) ans = 1;
            }
        }
        
        return dp[i][j] = ans;
    }
    
    
    // TABULATION : vohi DO IT :   V V R R C
//     public boolean isMatch(String s, String p){
//         int[][] dp = new int[s.length()+1][p.length()+1];
//         for(int[] x : dp) Arrays.fill(x, 2);
        
//         int ans = solve(s, p, 0, 0, dp);
//         return ans==0 ? false : true;
//     } 
    
//     public int solve(String s, String p, int I, int J, int[][] dp){
       
//         int n = s.length(), m = p.length();
        
//         for(int i = n ; i >= 0; i--){
//             for(int j = m; j >= 0; j--){
//                 if(i == n && j != m && p.charAt(j) == '*') { dp[i][j] = dp[i][j+1]; continue;}
//                 if(i == n && j == m) { dp[i][j] = 1; continue;}
//                 if(i == n) {  dp[i][j] = 0; continue;}
//                 if(j == m) { dp[i][j] = 0; continue;}


//                 int ans = 0;
//                 if(Character.isLetter(p.charAt(j))){
//                     if(s.charAt(i) == p.charAt(j)){
//                         if(dp[i+1][j+1] == 1) ans = 1;
//                     }else{
//                         ans = 0;
//                     }
//                 }else{
//                     if(p.charAt(j) == '*'){
//                         if(dp[i+1][j] == 1) ans = 1;
//                         if(dp[i+1][j+1] == 1) ans = 1;
//                         if(dp[i][j+1] == 1) ans = 1;
//                     }else{
//                         if(dp[i+1][j+1] == 1) ans = 1;
//                     }
//                 }

//                  dp[i][j] = ans;        
//             }
            
//         }
        
//         return dp[I][J];
//     }

