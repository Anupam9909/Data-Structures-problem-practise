// DP - cut set 

  (cut in b/w element)     ;    (cut on the element)
     A B C D E             ;        A B C D E           
      | | | |              ;          | | |
   k = si; k < ei          ;    k = (si+1) ; k < ei

f(si, k+1),  f(k, ei)      ;    f(si, k) ,  f(k, ei)


// mostly question me ye apply ho jata ha, phir bhi kissi 
// question me alag bhi ho sakta ha eg. LC-312. Burst Balloons yaha cut alag tarike se ha
// so dry run kar ke dekna thoda kya fit bethta ha

// NOTE 1.  : cut set me hamesha tabulation me gap strategy lagegi ye to pakka ha
// NOTE 2.  : MCM vale problem me kabhi (new int[2]) ye mat lena, apna pair class bana ke karna nahi to dp likte vakt dikkat ayegi

//=======================================================================================================
// QUESTIONS:-

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
            
	    int thisLevelMultiplicationCost = (arr[si]*arr[k]*arr[ei]);
            int totalcost = recans1 + recans2 + thisLevelMultiplicationCost; 
            
            minans = Math.min(minans, totalcost);
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




//=================================================================================

// GFG - Boolean Parenthesization - imp question  ->  find number of true in this exp:  T|F^T&T|T^F|T&T

       // NOTE : most importantly haam iss question me na sirf recurssion me faith me
        // number of trues return karvayege but also number of false bhi return karayege
        // and operator ke hisab se condition banayege apne level ka ans banane ke liye
        
        // so finally here 3d dp na use karke haam [pair class use karege with 2d dp]-> yahi tarika best hota ha 3dp ke question ko karne ka
    // pair ka 0 index number of false's ko represent karta ha
    // pair ka 1 index number of true's ko represent karta ha 
    // {number of false, number of true}
    
    //  CONCEPT : ISS QUESTION ME CUT SET LAGEGA 
    //  (K = si; k < ei; k = k+2)     => k = k+2 isliye kiya as operator(|,&,^) ye to +2 pe hi milege

// NOTE : MCM vale problem me kabhi (new int[2]) ye mat lena, apna pair class bana ke karna nahi to dp likte vakt dikkat ayegi
    public static class pair{
        int f;  // total number of true's
        int t;  // total number of false's
        pair(int f, int t) {
            this.f = f; 
            this.t = t;
        }
    }
    
    public static int countWays(int N, String S){
        pair[][] dp = new pair[N][N];
        pair ans = solve(S, 0, N-1, dp);
        return ans.t;
    }
    

    public static pair solve(String s, int si, int ei, pair[][] dp){
        if(si == ei){
            if(s.charAt(si) == 'T') return dp[si][ei] = new pair(0,1);
            else return dp[si][ei] = new pair(1,0);
        }
        
        if(dp[si][ei] != null) return dp[si][ei];
        
        pair myans = new pair(0,0);
        int n = s.length(), mod = 1003;
        
        for(int k = si+1; k < ei; k += 2){
            
            pair left = solve(s, si, k-1, dp);
            pair right = solve(s, k+1, ei, dp);
 
            if(s.charAt(k) == '|'){
                myans.f += left.f * right.f;
                myans.t += (left.f * right.t) + (left.t * right.f) + (left.t * right.t);
            }
            else if(s.charAt(k) == '&'){
                myans.f +=  (left.f * right.f) + (left.t * right.f) + (left.f * right.t);  
                myans.t +=  (left.t * right.t);
            }
            else if(s.charAt(k) == '^'){
                myans.f += (left.f * right.f) + (left.t * right.t);  
                myans.t += (left.f * right.t) + (left.t * right.f);  
            }
            myans.f %= mod;
            myans.t %= mod;
            
        }
        
        return dp[si][ei] = myans;
    }



//===========================================================================

// LC - 132. Palindrome Partitioning II

    // since : yaha partitioning simple alag alag hogi so
    // we will use
    //  for(int k = si; k < ei; k++){
    //     solve(si, k);
    //     solve(k+1, ei);
    //  }
    
    public int minCut(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] x: dp) Arrays.fill(x, (int)1e9);
        
        int ans = solve(s, 0, n-1, dp);
        return ans;
    }
    
      
    public int solve(String s, int si, int ei, int[][] dp){
        if(si >= ei) return dp[si][ei] = 0;
        if(dp[si][ei] != (int)1e9) return dp[si][ei];
        
        if(isPalindrome(s, si, ei)) return 0;
        
        int mincut = (int)1e9;
        for(int k = si; k < ei; k++){
            if(isPalindrome(s, si, k)){  // left rec call check kar lo agar palindrome ha to hi ander aao and dusre ko check karo varna agar nahi ha to dusre ko call karke duplicate calls lagegi (and TLE ayega and will not pass)
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
    
    

//===========================================================================================================


// LC - 132. Palindrome Partitioning III

    // iss question me recursion thode alag tarike ki ha (so yaad kar lena)
    // CONCEPT:
    // 1. cut set ka hi question ha where => (cut = si; cut < ei) hoga since simple partition karna ha
    // 2. ek function banayege minop() which return the min no. of operation to make string[si,ei] palindrome 
    // 3. let say Kth point par cut lagaya inside the solve() function:
    // 4. so, leftcall = minop(s, si, cut)
    //    and rightcall = solve(s, cut+1, ei, k-1)   // [cut+1, ei] tak ke window se (k-1) substring le aao which has all its individual substring palindrome 
    // 5. note: 3 base case ka dyan rakhna ha if(ei-si+1 < k) return 1e9;
    //                                        if(si == ei) return 0;
    //                                        if(k == 1) return minop(s, si, ei);
    // and at last to optimise use dp i.e 3d dp
    
    // NOTE : ISS QUESTION ME solve() recurssion call me hamesha ei fix rehta ha so haam sirf 2d dp bhi bana sakte ha-> dp[n+1][k+1] size ki  
    public int palindromePartition(String s, int k){
        int n  = s.length();
        
        int[][][] dp = new int[n+1][n+1][k+1];
        for(int[][] x : dp) for(int[] y : x) Arrays.fill(y, (int)1e9);
        
        int ans = solve(s, 0, n-1, k, dp);
        return ans;
    }
    
    // 3 base cases ha important
    public int solve(String s, int si, int ei , int k, int[][][] dp){
        if(ei-si+1 < k) return dp[si][ei][k] = (int)1e9;     // agar string ki length(ei-si+1) se jada (k) ki value hui to this is a edge case not i.e not possible case. so, we return (int)1e9 yahi return karna ha varna ans wrong ayega
        if(si == ei) return dp[si][ei][k] = 0;
        if(k == 1){
            return dp[si][ei][k] = minop(s, si, ei);
        }
        
        if(dp[si][ei][k] != (int)1e9) return dp[si][ei][k];
        
        int minans = (int)1e9;
        for(int cut = si; cut < ei; cut++){
            int leftans = minop(s, si, cut);
            int rightans = solve(s, cut+1, ei, k-1, dp);
            
            int totalop = leftans + rightans;

            minans = Math.min(minans, totalop);
        }
        
        return dp[si][ei][k] = minans;
    }
    
    
    public int minop(String s, int si, int ei){  // minop : minimum operation required to make string palindrome
        int count = 0;
        while(si < ei){
            if(s.charAt(si) != s.charAt(ei)) count++;
            si++; 
            ei--;
        }
        return count;
    }



//===========================================================================


// LC-312. Burst Balloons (V.Imp for Interview)
// QUESTION : You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on 
// it represented by an array nums. You are asked to burst all the balloons.
// If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 
// goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

// SOLUTION:
    // yahi tarike se karo code (aur kahi se mat karna confusion hogi)
    // concept :
    // 1. cut set ka question ha and yaha kuch alag type ka cut lagana padega i.e              [k = si; k <= ei] as hame sare posibilities check karni ha haar ballon par
    
    // 2. let say we are at kth position then recurssion faith is:- 
    // faith === hamari window [si,ei] me Kth position vale ballon ko last me phodne par kitna max coins bana sakte ha ans is :-(YE LAST ME BALLON PHODNE VALA POINT IS MOST IMPORTANT ISSI KA QUESTION HA YE)
    // last me kth ballon ko phoda ha iska matlab sare window(i.e [si,ei]) ke ballons phoot gye hoge kth ballon ko chod kar, so now,
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
    
    //NOTE : (INTERVIEWER QUESTION -> WHY WE ARE BURSTING THE BALLONS AT LAST TIME IN THE RECURSSION?) 
//           ANS IS -> BECAUSE KISSI BALLON KO PEHLE PHODNE SE DEPENDENCY KHARAB HO JATI HA
    
public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = 1; arr[n+1] = 1;   // ye karna jaruri ha bass
        for(int i = 0; i < n; i++) arr[i+1] = nums[i];
        
        int[][] dp = new int[n+2][n+2];
        int ans = solve(arr, 1, n, dp);
        return ans;
    }
    
    // solve()-> (si se ei) tak ke sare ballon ko phodne ki total kimat return karega
    public int solve(int[] arr, int si, int ei, int[][] dp){
        if(si > ei) return dp[si][ei] = 0;   // bass ye base case ayega only aur kuch mat likhna      (i.e agar si==ei vali condition likhi to ye karna padega ->  if(si == ei) return dp[si][ei] =  (arr[si-1]*arr[si]*arr[si+1]);   // yahi ayega only arr[si] nai ayega)
        
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
    


//=================================================================================

// GFG - MIN AND MAX VALUE OF AN EXPRESSION
// (CODING NINJA FOR SUBMITTION) CODESTUDIO - Minimum Maximum Value

// SOLUTION: vohi thoda boolean paranthisation ka question ha bass usse aasan ha simple condition lagegi max vale me max ko add kar do and min vale me min ko, same with multiplication			

	public static int[] minMaxValue(String exp) {
		// Write your code here.
		int n = exp.length();
		int[][][] dp = new int[n+1][n+1][2];
		for(int[][] y : dp) for(int[] x : y) {x[0] = (int)1e9; x[1] = -(int)1e9;}
		
		int[] ans = solve(exp, 0, n-1, dp);
		return ans;
	}
	
	public static int[] solve(String s, int si, int ei, int[][][] dp){
		if(si == ei){
			int val = (int)(s.charAt(si)-'0');
			return dp[si][ei] = new int[]{val, val};
		}
		
		if( dp[si][ei][0] != (int)1e9 && dp[si][ei][1] != -(int)1e9) return dp[si][ei];
		
		int[] myans = new int[2];
		myans[0] = (int)1e9;	myans[1] = -(int)1e9;
		
		for(int k = si+1; k < ei; k+= 2){
			int[] rec1 = solve(s, si, k-1, dp);
			int[] rec2 = solve(s, k+1, ei, dp);
			int[] temp = new int[2];
			
			char ch = s.charAt(k);
			if(ch == '+'){
				temp[0] = rec1[0] + rec2[0];
				temp[1] = rec1[1] + rec2[1];
			}else if(ch == '*'){
				temp[0] = rec1[0]*rec2[0];
				temp[1] = rec1[1]*rec2[1];
			}
			
			myans[0] = Math.min(myans[0], temp[0]);
			myans[1] = Math.max(myans[1], temp[1]);
		}
		return  dp[si][ei] = myans;
	}


//=====================================================================================

// OPTIMAL BST (pepcoding submit) - nahi samaj aye to chod dena not that imp. but can be asked kuch keh ni sakte

// concept: simple ha ye MCM hi lagana ha (cut = si, cut <= ei) bhi bass ek chij ayegi ki min cost nikalne ke baad last me freq ka sum de dena ha from (si,ei) of that level
// 1.  har element ko root maan ke leftsubtree ka ans le aao and rightsubtree ka ans le aao
// then totalcost nikal lo ans sare possibility check karke (min) cost value nikal lo

// 2. bass last me jo bhi (min) ans ayega usme freq ka sum ([si,ei] iss window me) add karke return kar dena 
// reason:  https://www.youtube.com/watch?v=HnslzEs8dbY  -> 51:31 pe ye samaj lo ki freq ka sum kyu add karna pad raha ha hame
    public int optimalbst(int[] keys, int[] freq, int n) {
        int[][] dp = new int[n+1][n+1];
        for(int[] x : dp) Arrays.fill(x, (int)1e9);
        
        int ans = solve(keys, freq, 0, n-1, dp);
        return ans;
    }
	
    public int solve(int[] keys, int[] freq, int si, int ei, int[][] dp){
        if(si == ei) return dp[si][ei] = freq[si];
        if(si > ei) return 0;   // yaha 0 return karna ha (int)1e9 nahi 
        
        if(dp[si][ei] != (int)1e9) return dp[si][ei];
        
        int fsum = freqsum(freq, si, ei);
        int min = (int)1e9;
        
        for(int i = si; i <= ei; i++){
            int totalcost = solve(keys, freq, si, i-1, dp) + solve(keys, freq, i+1, ei, dp);
            
            min = Math.min(min, totalcost);
        }
        
        return dp[si][ei] = (min + fsum);
        
    }
    
    public int freqsum(int[] arr, int si, int ei){
        int sum = 0;
        for(int i = si; i <= ei; i++){
            sum += arr[i];
        }
        return sum;
    }
