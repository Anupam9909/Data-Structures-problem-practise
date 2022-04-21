import java.util.*;
// import java.util.ArrayList;


public class targetset{
    public static void main(String[] args){
        int[] arr = {2,3,5,7};
        int target = 10;
        by_forloopsimplemethod_m1(arr,target);
       
        System.out.println("==============================");
        
        // this is important mehtod as it is used in DP KNAPSACK PROBLEMS
        by_Subsequence_Method_m2(arr, target);
        
    }


    public static void by_forloopsimplemethod_m1(int[] arr,int target){

        System.out.println(ccpinfi_m1(arr, 0, arr.length, target ));
        System.out.println(cccinfi_m1(arr, 0, arr.length, target ));
        System.out.println(cccsingle_m1(arr, -1, arr.length, target ));

        boolean[] visited = new boolean[4];
        System.out.println(ccpsingle_m1(arr, 0, arr.length, target, visited )); 
        System.out.println(ccpsingle_opti_m1(arr, 0, arr.length, target )); 
    }

    public static void by_Subsequence_Method_m2(int[] arr,int target){

        System.out.println(ccpInfinte_m2(target, arr, 0));
        System.out.println(cccInfinite_m2(target, arr, 0));
        System.out.println(cccsingle_m2(target, arr, 0));

        boolean[] visited = new boolean[4];
        System.out.println(ccpsingle_m2(target, arr, 0, visited));
        System.out.println(ccpsingle_opti_m2(target, arr, 0));
    }
    
    
    // target sum with subsequence method
    public static int ccpInfinte_m2(int target, int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;
        
        int totalans = 0;
        
        if(target-arr[idx] >= 0){  // idx element ayega 
            totalans +=  ccpInfinte_m2(target-arr[idx], arr, 0);
        }

        totalans += ccpInfinte_m2(target, arr, idx+1);  // idx element nahi ayega

        return totalans;
    }

    
    // coin change combination, infinite - by the subsequence method
    public static int cccInfinite_m2(int target, int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;

        int count = 0;
        if(target-arr[idx] >= 0){
            count += cccInfinite_m2(target-arr[idx], arr, idx);
        }

        count += cccInfinite_m2(target, arr, idx+1);

        return count;
    }


    // coin change combination,single coin - by the subsequence method
    public static int cccsingle_m2(int target , int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;

        int count = 0;
        
        if(target-arr[idx] >= 0)
            count += cccsingle_m2(target-arr[idx] , arr, idx+1);

        count += cccsingle_m2(target, arr, idx+1);

        return count;
    }

    //coin change permutation, single coin  // kuch ni karna bass visited ka check laga lo(nothing to worry about ki kitne calls lagege)
    //calls to do hi lagegi ek baar ayega aur ek baar nahi ayega vali, tree khud sabhal lega (draw tree sab smaj me ayega)
    public static int ccpsingle_m2(int target, int[] arr, int idx, boolean[] visited){
        if(target == 0){
            return 1;
        }
        if(idx == arr.length) return 0;

        int count = 0;
        // ayega
        if(target-arr[idx] >= 0 &&  !visited[idx] ){
            visited[idx] = true; // mark visited
            count += ccpsingle_m2(target-arr[idx], arr, 0, visited); 
            visited[idx] = false;  // mark unvisited
        }
        
        // nahi ayega
        count += ccpsingle_m2(target, arr, idx+1, visited);

        return count;
    }

    public static int ccpsingle_opti_m2(int target, int[] arr, int idx){
        if(target == 0) {
            return 1;
        }
        if(idx == arr.length) return 0;

        int count = 0;
        // ayega vali call
        if(arr[idx] >= 0 && target-arr[idx] >= 0){
            int val = arr[idx];
            arr[idx] = -val;   // mark visited
            count += ccpsingle_opti_m2(target-val, arr, 0); // note yaha (target-val) agega na ki (target-arr[i]) as arr[i] becomes -ve first
            arr[idx] = val;   // mark unvisited
        }

        // nahi ayega vali call
        count += ccpsingle_opti_m2(target, arr, idx+1);
        
        return count;
    }





    //=========================================================================================================================================================

    // by simple for loop method m1

    // I-1
    // coin change combination infinite coin
    public static int ccpinfi_m1(int[] arr,int si, int ei, int target){  // yaha si lene ki bhi jarurat ni thi as haar baar hame loop ya call 0 to n tak hi karna ha
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si ; i < ei ; i++){
            if(target-arr[i] >= 0){
                ans += ccpinfi_m1(arr, si, ei, target-arr[i]);
            }
        }
        return ans;
    }

    // I-2
    // coin change combination infinite coin
    public static int cccinfi_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si; i < ei ; i++){
            if(target-arr[i] >= 0){
                ans += cccinfi_m1(arr, i, ei, target-arr[i]);
            }
        } 
        return ans;
    }

    // I-3
    // coin change combination single coin
    public static int cccsingle_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si+1; i < ei; i++){
            if(target-arr[i] >= 0){
                ans += cccsingle_m1(arr, i, ei, target-arr[i]);
            }
        }
        return ans;
    }

    // I-4
    // coin change permutation single coin
    public static int ccpsingle_m1(int[] arr, int si, int ei, int target, boolean[] visited){
        if(target == 0) return 1;
        int ans = 0;
        
        for(int i = si; i < ei; i++){
            if(target-arr[i] >= 0 && !visited[i] ){
                visited[i] = true;
                ans += ccpsingle_m1(arr, si, ei , target-arr[i], visited);
                visited[i] = false;
            }
        }
        return ans;
    }

    // space optimization of [coin change permutation single coin]
    // hame ussi array me arr[i] ko -arr[i] kar dege which we know acts as a visited property (graph theory) 
    // this algo will not work when array elements are -ve or 0 in input array;
    public static int ccpsingle_opti_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;
        int ans = 0;
        
        for(int i = si; i < ei; i++){
            if(arr[i] >= 0 && target-arr[i] >= 0 ){
                int val = arr[i];
                arr[i] = -val; // mark visited
                ans += ccpsingle_opti_m1(arr, si, ei , target-val);
                arr[i] = val; // mark unvisited
            }
        }
        return ans;
    }


//====================================================================================================================

//====================================================================================================================

// QUESTION:

// LC-518. Coin Change 2

    public int change(int tar, int[] arr){
        int n = arr.length;
        int[][] dp = new int[n+1][tar+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(arr, 0, tar, dp);
        return ans;
    }
    
      
    // MEMOIZATION:
    public int solve(int[] arr, int idx, int target, int[][] dp){
        if(idx == arr.length) return dp[idx][target] = 0;
        if(target == 0) return  dp[idx][target] = 1;
        
        if( dp[idx][target] != -1) return  dp[idx][target];
        
        int count = 0;
        
        if(target-arr[idx] >= 0){
            count += solve(arr, idx, target-arr[idx], dp);    
        } 
        count += solve(arr, idx+1, target, dp);
        
        return  dp[idx][target] = count;
    }
    
    ///-------------
    // ALSO IN,
    // TABULATION:
    public int solve(int[] arr, int IDX, int TARGET, int[][] dp){
        int n = arr.length, m = TARGET;
        
        for(int idx = n; idx >= 0; idx--){
            for(int target = 0; target <= m; target++){
                if(idx == arr.length){ dp[idx][target] = 0; continue;}
                if(target == 0) { dp[idx][target] = 1;  continue;}

                int count = 0;

                if(target-arr[idx] >= 0){
                    count += dp[idx][target-arr[idx]]; // solve(arr, idx, target-arr[idx], dp);    
                } 
                count += dp[idx+1][target];  // solve(arr, idx+1, target, dp);

                dp[idx][target] = count;                
            }
        }
        return dp[IDX][TARGET];
    }
  

//====================================================================================================================


// GFG => 0/1 Knapsack Problem             ->  https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1#

    public int knapSack(int W, int wt[], int val[], int n) 
    { 
        int target = W;
        int[][] dp = new int[target+1][n+1];
        for(int[] x : dp) Arrays.fill(x, -(int)1e9);
        
        int ans = solve(wt, target, 0, val, dp);
        return ans;
    } 
    
// MEMOIZATION:
    public int solve(int[] wt, int target, int idx, int[] val, int[][] dp){
        if(idx == wt.length) return dp[target][idx] = 0; // imp. yahi catch ha => yaha bhi 0 hi return hoga as agar hamara recursion yaha tak aya iska matlab target 0 to nahi hua abhi tak but question me kaha ha 
        // ki weight should be smaller than or equal to target so yaha se haam return 0 karege ->NOTE: (0 ki jagah -(int)1e9 return kiya to ans ni ayega
        if(target == 0)  return  dp[target][idx] = 0;
        
        if( dp[target][idx] != -(int)1e9) return  dp[target][idx];
        
        int maxans = -(int)1e9;
        // ayega
        if(target-wt[idx] >= 0){
            int totalval = solve(wt, target-wt[idx], idx+1, val, dp) + val[idx];
            maxans =  Math.max(maxans, totalval);
        }
        
        // nahi ayega
        maxans = Math.max(maxans, solve(wt, target, idx+1, val, dp));
        
        return  dp[target][idx] = maxans;
    }


//------------or we can do it with tabu
   // TABULATION:
    public static int solve(int[] wt, int TARGET, int IDX, int[] val, int[][] dp){
        // yaha pattern kuch esa banega agar solve(target, idx) me target pehle ha and idx baad me to [kissi bhi tarah se kar sakte ha but tabu karne se pehle pattern dekna padega ki kya lag rha ha uske hisab se for loop chalayege]
        // <-----------
        // <-----------
        // <-----------
        // <-----------
	//  |
	//  |
	//  |
	// \ /
        int n = wt.length;
        for(int target = 0; target <= TARGET; target++){
            for(int idx = n; idx >= 0; idx--){
                if(idx == wt.length) {dp[target][idx] = 0; continue;}// imp. yahi catch ha => yaha bhi 0 hi return hoga as agar hamara recursion yaha tak aya iska matlab target 0 to nahi hua abhi tak but question me kaha ha 
                // ki weight should be smaller than or equal to target so yaha se haam return 0 karege ->NOTE: (0 ki jagah -(int)1e9 return kiya to ans ni ayega
                if(target == 0){  dp[target][idx] = 0; continue; }
                
                int maxans = -(int)1e9;
                // ayega
                if(target-wt[idx] >= 0){
                    int totalval = dp[target-wt[idx]][idx+1] + val[idx]; // solve(wt, target-wt[idx], idx+1, val, dp) + val[idx];
                    maxans =  Math.max(maxans, totalval);
                }
            
                // nahi ayega
                maxans = Math.max(maxans, dp[target][idx+1]); // solve(wt, target, idx+1, val, dp)
                
                dp[target][idx] = maxans;                
            }
        }
            
        return dp[TARGET][IDX];
    }
    
//================================================================================================================================

// LC- 322. Coin Change

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        
        int[][] dp = new int[amount+1][n+1];
        for(int[] x : dp) Arrays.fill(x, -1);    // NOTE : haam (int)1e9 store ni kar sakte as ye ans ka part ha i.e when idx == arr.length we are returning (int)1e9 thats why we store -1 in dp as a default value because constaint me given : 1 <= coins[i] <= 231 - 1
        
        int ans = solve(coins, amount, 0, dp);
        return (ans == (int)1e9) ? -1 : ans;
    }
    
    public int solve(int[] arr, int target, int idx, int[][] dp){
        if(target == 0)  return dp[target][idx] = 0;
        if(idx == arr.length) return dp[target][idx] = (int)1e9;
        
        if(dp[target][idx] != -1) return dp[target][idx];
        
        
        int minans = (int)1e9;
        if(target-arr[idx] >= 0){
            minans = Math.min(minans, solve(arr, target-arr[idx], idx, dp) + 1);
        }
        
        minans = Math.min(minans, solve(arr, target, idx+1, dp));
        
        return dp[target][idx] = minans;
    }
    


//==========================================================================================
// LC-combination I,  LC-combination II,  LC-combination III,  LC-combination IV -> sare ek hi concept pe based ha jo upar kiye ha subsequence vale method se bass usme dp laga dena ha

// eg1. LC-combination-IV
// concept: (permutation + single coin)
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[target+1][n+1];
        for(int[] x : dp) Arrays.fill(x, -1);    // dp me 0 nahi store hoga as 0 answer ka part ha, hence -1 store kar lete ha
        
        int ans = solve(nums, target, 0, dp);
        return ans;
    }
    
    public int solve(int[] arr, int target, int idx, int[][] dp){
        if(target == 0) return dp[target][idx] = 1;
        if(idx == arr.length) return  dp[target][idx] = 0;
        
        if( dp[target][idx] != -1) return  dp[target][idx];    
        
        int count = 0;
        if(target-arr[idx] >= 0){
            count += solve(arr, target-arr[idx], 0, dp);
        }
        
        count += solve(arr, target, idx+1, dp);
        return  dp[target][idx] = count;
    }
    
    

//==========================================================================================

// GFG-Subset Sum Problem     ->      https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/#
// (combination) + (single coin) lagega
// NOTE: yaha since true & false dono answer ka part ha so, haam dp -1,0,1 ki bana dege
// -1 : default value
//  0 : false
//  1 : true

    public static Boolean isSubsetSum(int N, int arr[], int sum){
        int[][] dp = new int[sum+1][N+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        boolean ans = solve(arr, sum, 0, dp);
        return ans;
        
    }
    
    public static boolean solve(int[] arr, int target, int idx, int[][] dp){
        if(target == 0) { dp[target][idx] = 1; return true;}   // ye vali condition pehle ayegi varna wrong ans ayega as when (idx==arr.length) then also target can be 0
        if(idx >= arr.length){ dp[target][idx] = 0; return false;}

        
        if(dp[target][idx] != -1)  return dp[target][idx]==0 ? false : true;
        
        boolean ans = false;
        if(target-arr[idx] >= 0){
            ans = ans || solve(arr, target-arr[idx], idx+1, dp);
        }
        
        ans = ans || solve(arr, target, idx+1, dp);
        
        dp[target][idx] = (!ans)? 0:1;
        return ans;
    }





//==========================================================================================

// GFG - Partition Equal Subset Sum

    // CONCEPT:
    // 1.   (combination + s  ingle coin) LAGEGA
    // 2.   note since equal parts me divide karna ha means (x + x = totalsum)-> (x = totalsum/2)
    //      now hamara (target = totalsum/2) jo ki haam check karege vohi dp => -1, 0, 1 vali le ke upar vale question ki tarah 
    
    public static int equalPartition(int N, int arr[]){
        int sum = 0;
        for(int x : arr) sum+=x;
        
        if(sum % 2 != 0) return 0;  // IMPORTANT => if sum is odd return false(0) as cannot ve partition into two equal parts
        
        int target = sum/2;
        int[][] dp = new int[N+1][target+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        boolean ans = solve(arr, 0, target, dp);
        return ans?1:0;
    }
    
    public static boolean solve(int[] arr, int idx, int target, int[][] dp){
        if(target == 0) { dp[idx][target] = 1; return true;}
        if(idx >= arr.length) { dp[idx][target] =  0; return false;}
        
        if(dp[idx][target] != -1) return dp[idx][target]==1?true:false;
        
        boolean res = false;
        if(target-arr[idx] >= 0){
            res = res || solve(arr, idx+1, target-arr[idx], dp);
        }
        
        res = res || solve(arr, idx+1, target, dp);
        dp[idx][target] = res?1:0;
        return res;
    }


// NOTE: ye tarika i.e partition equal subset sum sirf 2 me split karne par hi dp lagegi, baki agar question aye 
// GFG - partition array to k subsets -> to yaha pura recurssion hi lagega pura ye dp se nahi hoga 
//==========================================================================================

// LC - 494. Target Sum

    // important question - question pado pehle
    // thoda alag ha simple coin change se (yaha hame sare elements ko le ke hi target banana ha chahe + me lo uss element ko ya - me lo)
    // CONCEPT 1.: simple do calls lagegi :     count += solve(arr, sum+arr[idx], idx+1, target, dp);
    //                                           count += solve(arr, sum-arr[idx], idx+1, target, dp);
    // 2. since dp lagate time sum variable -ve ja raha ha so isko +ve karne ke liye 
    //    vo vala tarika use karege i.e SUM add kar dege if sum is -ve
    
    static int x = 0;
    public int findTargetSumWays(int[] arr, int target ) {
        int n = arr.length;
        int sum = 0;
        for(int x : arr) sum+=x; 
        // max range of sum -> [-sum, +sum]
        x = sum;
        
        int[][] dp = new int[2*sum+2][n+1];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(arr, 0, 0, target, dp);  // yaha haame 0 hi pass karna padega sum me
        return ans;
    }
    
    // kuch ni bass jaha jaha  dp[sum][idx] ye ha vaha vaha ye kar do---> dp[SUM + sum][idx] 
    public int solve(int[] arr, int sum, int idx, int target, int[][] dp){
        if(idx == arr.length){ 
            if(target == sum) return dp[x + sum][idx] = 1;
            else return dp[x + sum][idx] = 0;
        }
        
        if(dp[x + sum][idx] != -1) return dp[x + sum][idx];
        
        int count = 0;
        count += solve(arr, sum+arr[idx], idx+1, target, dp);
        count += solve(arr, sum-arr[idx], idx+1, target, dp);
        
        return dp[x + sum][idx] = count;
    }
    
    

    
}