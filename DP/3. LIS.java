public class LIS{
   
// LC-300. Longest Increasing Subsequence 

    // TABULATION: learn this 
   // O(n^2) approach
    public int lengthOfLIS(int[] arr){
        int n = arr.length, max = 1, dp[] = new int[n];
        
        for(int i = 0; i < n ; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[i]  > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }        
            max = Math.max(max, dp[i]);
        }
        return max;
    }


//-------------------------------------

// OPTIMISED LIS  ->  O(nlogn) approach
// using binary search 
// concept : 
// ek arraylist(al) lelo
// given array ko traverse karo and for any ith element x
// bring the insert position of the x in the arraylist al
// and now x ko replace kar do uss insert postion pe (i.e use set() function which directly replace) kar do arraylist al me 
// and last me lis will be our size of the arraylist

// using binary search
     public int lengthOfLIS(int[] arr){
         ArrayList<Integer> al = new ArrayList<>();
         int n = arr.length;
         
         for(int i = 0; i < n; i++){
             int idx = Collections.binarySearch(al, arr[i]);
             if(idx < 0){   // that means element is not present so set them
                 idx = -idx-1;
                 if(idx == al.size()) al.add(arr[i]);
                 else al.set(idx, arr[i]);  // set() function replace bhi karta ha and remove bhi
             }  
         }
         
         return al.size();
     }



//-------------------------------------
// using simple (recursion & dp)
    public int lengthOfLIS(int[] arr){
        int n = arr.length, ans = 0, dp[] = new int[n];
        Arrays.fill(dp, -1);
        for(int i = 0; i < n; i++)
            ans = Math.max(solve(arr, i, dp), ans);
        
        return ans;
    }
    
    public int solve(int[] arr, int idx, int[] dp){
        int n = arr.length, max = 1;
        if(dp[idx] != -1) return dp[idx];
        
        for(int i = idx+1; i < n; i++){
            if(arr[idx] < arr[i]){
                max = Math.max(max, solve(arr, i, dp)+1); 
            }                 
        }
        
        return dp[idx] = max;
    }   

//========================================================================
// PRINT ALL LONGEST INCREASING SUBSUEQUENCE

// print all the longest increasing subsequence
// agar sare chahiye then to recurssion hi lagana padega dp me hi
    
     public void lengthOfLIS(int[] arr){ 
        int n = arr.length, maxlen = 0, dp[] = new int[n];
        Arrays.fill(dp, -1);
        for(int i = 0; i < n; i++)
            maxlen = Math.max(solve(arr, i, dp), maxlen);
        
        
        // making ans
        List<List<Integer>> fans = new ArrayList<>();
        for(int i= 0; i < n; i++){
            if(dp[i]==maxlen){
                List<Integer> tp = new ArrayList<>();
                subseq(arr,dp, i, maxlen, tp,fans);
            }
        }
        
        System.out.println(fans);
        
        return;
    }
    
    public void subseq(int[] arr, int[] dp,int idx, int len, List<Integer> tp, List<List<Integer>> fans){
        if(len == 1){
            tp.add(arr[idx]);
            List<Integer> bsans = new ArrayList<>(tp);
            fans.add(bsans);
            tp.remove(tp.size()-1);
            return;
        }
        
        tp.add(arr[idx]);
        for(int i = idx; i < dp.length; i++){
            if(dp[i] == len-1) subseq(arr, dp, i, len-1, tp, fans);
        }
        tp.remove(tp.size()-1);
    }
    
    
    public int solve(int[] arr, int idx, int[] dp){
        int n = arr.length, max = 1;
        if(dp[idx] != -1) return dp[idx];
        
        for(int i = idx+1; i < n; i++){
            if(arr[idx] < arr[i]){
                max = Math.max(max, solve(arr, i, dp)+1); 
            }                 
        }
        
        return dp[idx] = max;
    }
    
//======================================================================================
// LC - 673. Total Number of Longest Increasing Subsequence

public int findNumberOfLIS(int[] arr){
        int n = arr.length, maxlen = 0;
        int[] dp = new int[n];
        
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        
        int ans = 0;
        // ye bhi to recussion hi ha so yaha bhi dp lagg jayegi
        int[] ndp = new int[n];   // ndp : new dp 
        Arrays.fill(ndp, -1);
        for(int i = n-1; i >= 0; i--){
            if(dp[i] == maxlen)
                ans += solve(arr, i, dp, maxlen, ndp);
        }
        
        return ans;
    }
    
    public int solve(int[] arr, int idx, int[] dp, int maxlen, int[] ndp ){
        if(maxlen == 1) return ndp[idx] = 1;
        if(idx < 0) return ndp[idx] = 0;
        
        if(ndp[idx]  != -1) return ndp[idx] ;
        int ans = 0;
         for(int i = idx-1; i >= 0; i--){
             if(arr[i] < arr[idx] && dp[i] == maxlen-1){
                 ans += solve(arr, i, dp, maxlen-1, ndp);
             }
         }     
        
        return ndp[idx] = ans;
        
    }
    
//========================================================================================

// gfg- Minimum number of deletions to make a sorted sequence 

   public int minDeletions(int arr[], int n) {
        int ans = 0, dp[] = new int[n];
        
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        
        return n-ans;
    }


//========================================================================================

// gfg-Maximum sum increasing subsequence 

// same concept ha bass isme haam length ki jagah sum nikalege
// do line me change ha bass i.e
// dp[i] = Math.max(dp[i], dp[j]+arr[i]);
// dp[i] = arr[i];

      public int maxSumIS(int arr[], int n){  
	    int maxans = 0, dp[] = new int[n];
	    
	    for(int i = 0; i < n; i++){
	        dp[i] = arr[i];
	        for(int j = i-1; j >= 0; j--){
	            if(arr[j] < arr[i]){
	                dp[i] = Math.max(dp[i], dp[j]+arr[i]);
	            }
	        }
	        maxans = Math.max(maxans, dp[i]);
	    }
	    return maxans;
	}  



//========================================================================================

// MAX SUM OF LONGEST INCREASING SUBSEQUENCE (YE KAHI BHI QUESTION NI HA JUST KIYA HA FOR PRACTICE)

// maximum sum of longest increasing subsequence(sare max length ke LIS me se max sum vala print karna ha iss question me)
    
    public int lengthOfLIS(int arr[]){  
	    int n = arr.length, maxlen = 0, dp[] = new int[n];
	    
	   // filling dp
	    for(int i = 0; i < n; i++){
	        dp[i] = 1;
	        for(int j = i-1; j >= 0; j--){
	            if(arr[j] < arr[i]){
	                dp[i] = Math.max(dp[i], dp[j]+1);
	            }
	        }
	        maxlen = Math.max(maxlen , dp[i]);
	    }
	    
        // sare lis me se max sum vala nikalege
	   fans = 0;
	   for(int i = n-1; i >= 0; i--){
	       if(dp[i] == maxlen){
	           solve(arr, dp, i, maxlen,0);
	       }
	   }
	    System.out.println(fans);
	    return 0;
	}  
    
	static int fans = 0;
	
	public void solve(int[] arr, int[] dp, int idx, int maxlen,int sum){
	    if(maxlen == 1){
	        fans = Math.max(fans, sum+arr[idx]);
	        return;
	    }
	    
	    for(int i = idx-1; i >= 0; i--){
	        if(dp[i] == maxlen-1 && maxlen >= 1 && arr[i] < arr[idx])  
	            solve(arr, dp, i, maxlen-1, sum+arr[idx]);
	    }
	}
	
    




// ==============================================================================

// gfg - LONGEST BITONIC SUBSEQUENCE

public int LongestBitonicSequence(int[] arr)
    {
        int n = arr.length;
        
        // LEFT DP
        int[] dp1 = new int[n];
        
        for(int i = 0; i < n ;i++){
            dp1[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    dp1[i] = Math.max(dp1[i], dp1[j]+1);
                }
            }
        }
        
        // RIGHT DP
        int[] dp2 = new int[n];
        
        for(int i = n-1; i >= 0; i--){
            dp2[i] = 1;
            for(int j = i + 1; j < n; j++){
                if(arr[j] < arr[i]){
                    dp2[i] = Math.max(dp2[i], dp2[j]+1);
                }
            }
        }
        
        // making final answer
        int maxlen = 0;
        for(int i = 0; i < n; i++){
            maxlen = Math.max(maxlen, dp1[i]+dp2[i]);
        }
        return maxlen-1;   // ek common bhi hota ha so -1 kiya
    }
    
// ==============================================================================

// 1671. Minimum Number of Removals to Make Mountain Array

// ans = n-lbs;   // lbs -> longest bitonic subsequence

// iss question me hamara vo lis & lds vala kaam chal jayega
    // ans = n - maxlen
    public int minimumMountainRemovals(int[] arr) {
        int n = arr.length;
        
        // lcs- longest increasing subsequence
        int[] dp1 = new int[n];
        for(int i = 0 ; i < n; i++){
            dp1[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    dp1[i] = Math.max(dp1[i], dp1[j]+1);
                }
            }
        }
        
        // lds - longest decreasing subsequence
        int[] dp2 = new int[n];
        for(int i = n-1 ; i >= 0; i--){
            dp2[i] = 1;
            for(int j = i+1; j < n; j++){
                if(arr[j] < arr[i]){
                    dp2[i] = Math.max(dp2[i], dp2[j]+1);
                }
            }
        }
        
        for(int x : dp1) System.out.print(x + " ");
        System.out.println();
        for(int x : dp2) System.out.print(x + " ");
        
        long ans = 0;
        for(int i = 0; i < n ;i++){
            if(dp1[i] != 1 && dp2[i] != 1){
                ans = Math.max(ans, dp1[i]+dp2[i]);
            }
        }
        long fans = n-ans+1;
        return (int)fans;
    }




// ==============================================================================

// gfg - Maximum Sum Bitonic Subsequence 
// concept same hi ha lis jesa bass length ki jagah haam yaha sum nikalege

public static int maxSumBS(int arr[], int n)
    {
        int[] lis = new int[n];
        for(int i = 0; i < n;i++){
            lis[i] = arr[i];
            for(int j = i-1; j >= 0; j--){
                if(arr[j] < arr[i]){
                    lis[i] = Math.max(lis[i], lis[j] + arr[i]);
                }
            }
        }
        
        int[] lds = new int[n];   // concept bohi rahega bass uss concept me haam ulta ch chalege
        for(int i = n-1; i >= 0; i--){
            lds[i] = arr[i];
            for(int j = i+1; j < n; j++ ){
                if(arr[j] < arr[i]){
                    lds[i] = Math.max(lds[i], lds[j] + arr[i]);
                }
            }
        }
        
        // making ans
        int ans = 0;
        
        for(int i = 0; i < n;i++){
            ans = Math.max(ans, lis[i]+lds[i]-arr[i]);   // minus arr[i] bhi karege  
        }
        
        return ans;
    }

//==================================================================================

// gfg - BUILDING BRIDGES

input:
10
10 20
2 7
8 15
17 3
21 40
50 4
41 57
60 80
80 90
1 30
     public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n][2];
        
        for(int i = 0 ; i < n; i++){
            arr[i][0] = scn.nextInt();
            arr[i][1] = scn.nextInt();
        }
        
        int ans = solve(arr, n);
        
        System.out.println(ans);
    }
    
    public static int solve(int[][] arr, int n){
        Arrays.sort(arr, (a,b)->{
            return a[0]-b[0];
        });
        
        int maxlen = 0;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[j][1] < arr[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        
        return maxlen;
    }



























}