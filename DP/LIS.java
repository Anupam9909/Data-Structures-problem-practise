public class LIS{
   
// LC-300. Longest Increasing Subsequence 

   // simple (recursion & dp)
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
// PRINT ALL THE LONGEST INCRESING SUBSUEQUENCE

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

} 