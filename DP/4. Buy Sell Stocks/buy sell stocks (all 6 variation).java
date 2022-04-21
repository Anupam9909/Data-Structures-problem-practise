// BUY SELL STOCKS:

// LC-121. Best Time to Buy and Sell Stock
// K=1 ONLY (i.e only 1 transaction allowed)
// ye to simple ha O(n) time & O(1) space 
// kth point par khade rah kar haam [si,k-1] window ka minimum cost nikalege taki max profit ho jaye
// to do this, ek minimum le ke chalo and haar baar maxprofit nikal lo simply
    public int maxProfit(int[] arr){
        int n = arr.length, min = arr[0], profit = 0;
        
        for(int i = 0; i < n ; i++){
            min = Math.min(min, arr[i]);
            profit = Math.max(profit, arr[i]-min);
        }
        return profit;
    }

//==============================================================================

// LC-122. Best Time to Buy and Sell Stock II
// K = INFINITE (i.e infinite transactions)

    // recurssion(memo) 
    // O(n*2) time & O(n) space
    public int maxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(prices, 0, 1, dp);
        return ans;
    }
    
    public int solve(int[] arr, int idx, int buy, int[][] dp){
        if(idx == arr.length) return dp[idx][buy] = 0;
        
        if(dp[idx][buy] != -1) return dp[idx][buy];
        
        int ans = 0;
        if(buy == 1){  // [can buy] condition
            int recans1 = solve(arr,idx+1, 0, dp) - arr[idx];  
            int recans2 = solve(arr,idx+1, 1, dp);
            
            ans = Math.max(recans1, recans2);
        }
        else{ // [can sell] condition
            int recans1 = solve(arr,idx+1, 1, dp) + arr[idx];
            int recans2 = solve(arr,idx+1, 0, dp);
            
            ans = Math.max(recans1, recans2);
        }
        
        return dp[idx][buy] = ans;
    }
    
    
//    // tabulation
    // O(n) time & O(n) space
    public int maxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(prices, 0, 1, dp);
        return ans;
    }
    
    public int solve(int[] arr, int IDX, int BUY, int[][] dp){
        int n = arr.length;
        
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){
                if(idx == arr.length) {dp[idx][buy] = 0; continue;}

                int ans = 0;
                if(buy == 1){  // [can buy] condition
                    int recans1 = dp[idx+1][0] - arr[idx]; 
                    int recans2 = dp[idx+1][1]; 

                    ans = Math.max(recans1, recans2);
                }
                else{ // [can sell] condition
                    int recans1 = dp[idx+1][1] + arr[idx];
                    int recans2 = dp[idx+1][0]; 

                    ans = Math.max(recans1, recans2);
                }

                dp[idx][buy] = ans;                
            }
        }
        
        return dp[IDX][BUY];
    }    
    
    
//  space optimisation from tabulation
    //step 1: pattern dek lo 
    //step 2: two array le lo fix size ka ek [prev] and ek [curr]
    //step 3: change dp[idx+1] with prev   &&   change dp[idx] with curr
    // O(1) space
    public int maxProfit(int[] prices){
        return solve(prices, 0, 1);
    }
    
    public int solve(int[] arr, int IDX, int BUY){
        int n = arr.length;
        int[] curr = new int[2];
        int[] prev = new int[2];
        // change dp[idx+1] with prev
        // change dp[idx] with curr
        
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){
                if(idx == arr.length) {curr[buy] = 0; continue;}

                int ans = 0;
                if(buy == 1){  // [can buy] condition
                    int recans1 = prev[0] - arr[idx];
                    int recans2 = prev[1]; 

                    ans = Math.max(recans1, recans2);
                }
                else{ // [can sell] condition
                    int recans1 = prev[1] + arr[idx]; 
                    int recans2 = prev[0]; 

                    ans = Math.max(recans1, recans2);
                }

                curr[buy] = ans;                
            }
            
            prev = curr;  // ye karna jaruri ha
        }
        
        return curr[BUY];
    }    
   

//==============================================================================

// LC-123. Best Time to Buy and Sell Stock III
// k = (At Most 2) transation
    
//     // MEMOIZATION  // ye ho sakte ha TLE  aa jaye online exam me so tabu karne ke liye ready raho
    // and interview me aa raha ha that means tabu and space optimisation must ha puchega hi pakka 100%
    // O(n*2*3) time && space -> [O(n*2*3)normal + O(n)stack space] 
    public int maxProfit(int[] prices){
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n+1][2][3];
        for(int[][] x : dp) for(int[] y : x) Arrays.fill(y, -1);
        
        int ans = solve(prices, 0, 1, k, dp);
        return ans;
    }
    
    public int solve(int[] arr, int idx, int buy, int k, int[][][] dp){
        if(idx == arr.length || k == 0) return dp[idx][buy][k] = 0;
        
        if(dp[idx][buy][k] != -1) return dp[idx][buy][k];
        
        int maxprofit = 0;
        if(buy == 1){
            int recans1 = solve(arr, idx+1, 0, k, dp) - arr[idx];
            int recans2 = solve(arr, idx+1, 1, k, dp);
            
            maxprofit = Math.max(recans1, recans2);
        }
        else{
            int recans1= solve(arr, idx+1, 1, k-1, dp) + arr[idx];
            int recans2 = solve(arr, idx+1, 0, k, dp);
            
            maxprofit = Math.max(recans1, recans2);
        }
        
        return dp[idx][buy][k] = maxprofit;
    }
    
    
//     //TABULATION (apply vohi apna -> vvrrc)
    // O(n*2*3) time && O(n*2*3) space
    public int maxProfit(int[] prices){
        int n = prices.length;
        int k = 2;
                                  [buy][k] // buy -> 0/1 (size=2) , k ->  0/1/2(size = 3)
        int[][][] dp = new int[n+1][2][3];
        for(int[][] x : dp) for(int[] y : x) Arrays.fill(y, -1);
        
        int ans = solve(prices, 0, 1, k, dp);
        return ans;
    }
    
     public int solve(int[] arr, int IDX, int BUY, int K, int[][][] dp){
         int n = arr.length;
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){    // buy ka loop 1 se 0 kar do
                for(int k = K; k >= 0; k--){       // k ka loop 2 se 0 kar do ya 0 to 2 no problem
                    if(idx == n || k == 0){ dp[idx][buy][k] = 0; continue;}

                    int maxprofit = 0;
                    if(buy == 1){
                        int recans1 = dp[idx+1][0][k] - arr[idx]; 
                        int recans2 = dp[idx+1][1][k];  

                        maxprofit = Math.max(recans1, recans2);
                    }
                    else{
                        int recans1= dp[idx+1][1][k-1] + arr[idx]; 
                        int recans2 = dp[idx+1][0][k];

                        maxprofit = Math.max(recans1, recans2);
                    }

                    dp[idx][buy][k] = maxprofit;                    
                }
            }
        } 
        
         return dp[IDX][BUY][K];
    }
    
    
    // SPACE OPTIMIZATION
    
    public int maxProfit(int[] prices){
        int n = prices.length;
        int k = 2;
        int ans = solve(prices, 0, 1, k);
        return ans;
    }
    
     public int solve(int[] arr, int IDX, int BUY, int K){
         //                   [buy][k] // buy -> 0/1 (size=2) , k ->  0/1/2(size = 3)
        int[][] curr = new int[2][3];
        int[][] prev = new int[2][3];
        // change dp[idx] to curr (sabse pehle ye chalna chahiye base case me(so, vo to curr) hi hoga)
        // change dp[idx+1] to prev
         
        int n = arr.length;
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){    // buy ka loop 1 se 0 kar do
                for(int k = K; k >= 0; k--){       // k ka loop 2 se 0 kar do ya 0 to 2 no problem
                    if(idx == n || k == 0){ curr[buy][k] = 0; continue;}

                    int maxprofit = 0;
                    if(buy == 1){
                        int recans1 = prev[0][k] - arr[idx]; 
                        int recans2 = prev[1][k];  

                        maxprofit = Math.max(recans1, recans2);
                    }
                    else{
                        int recans1= prev[1][k-1] + arr[idx]; 
                        int recans2 = prev[0][k];

                        maxprofit = Math.max(recans1, recans2);
                    }

                    curr[buy][k] = maxprofit;                    
                }
            }
            prev = curr;
        } 
        
         return curr[BUY][K];
    }
    
    
   

//==============================================================================

// LC-188. Best Time to Buy and Sell Stock IV

// K = K Transaction allowed

// //     // MEMOIZATION  // ye ho sakte ha TLE  aa jaye online exam me so tabu karne ke liye ready raho
//     // and interview me aa raha ha that means tabu and space optimisation must ha puchega hi pakka 100%
//     // O(n*2*k) time && space -> [O(n*2*k)normal + O(n)stack space] 
    public int maxProfit(int k, int[] prices){
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1];
        for(int[][] x : dp) for(int[] y : x) Arrays.fill(y, -1);
        
        int ans = solve(prices, 0, 1, k, dp);
        return ans;
    }
    
    public int solve(int[] arr, int idx, int buy, int k, int[][][] dp){
        if(idx == arr.length || k == 0) return dp[idx][buy][k] = 0;
        
        if(dp[idx][buy][k] != -1) return dp[idx][buy][k];
        
        int maxprofit = 0;
        if(buy == 1){
            int recans1 = solve(arr, idx+1, 0, k, dp) - arr[idx];
            int recans2 = solve(arr, idx+1, 1, k, dp);
            
            maxprofit = Math.max(recans1, recans2);
        }
        else{
            int recans1= solve(arr, idx+1, 1, k-1, dp) + arr[idx];
            int recans2 = solve(arr, idx+1, 0, k, dp);
            
            maxprofit = Math.max(recans1, recans2);
        }
        
        return dp[idx][buy][k] = maxprofit;
    }
    
    
// //     //TABULATION (apply vohi apna -> vvrrc)
//     // O(n*2*k) time && O(n*2*k) space
    public int maxProfit(int k, int[] prices){
        int n = prices.length;
                                  // [buy][k] // buy -> 0/1 (size=2) , k ->  0/1/2(size = 3)
        int[][][] dp = new int[n+1][2][k+1];
        for(int[][] x : dp) for(int[] y : x) Arrays.fill(y, -1);
        
        int ans = solve(prices, 0, 1, k, dp);
        return ans;
    }
    
     public int solve(int[] arr, int IDX, int BUY, int K, int[][][] dp){
         int n = arr.length;
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){    // buy ka loop 1 se 0 kar do
                for(int k = K; k >= 0; k--){       // k ka loop 2 se 0 kar do ya 0 to 2 no problem
                    if(idx == n || k == 0){ dp[idx][buy][k] = 0; continue;}

                    int maxprofit = 0;
                    if(buy == 1){
                        int recans1 = dp[idx+1][0][k] - arr[idx]; 
                        int recans2 = dp[idx+1][1][k];  

                        maxprofit = Math.max(recans1, recans2);
                    }
                    else{
                        int recans1= dp[idx+1][1][k-1] + arr[idx]; 
                        int recans2 = dp[idx+1][0][k];

                        maxprofit = Math.max(recans1, recans2);
                    }

                    dp[idx][buy][k] = maxprofit;                    
                }
            }
        } 
        
         return dp[IDX][BUY][K];
    }
    
    
//     // SPACE OPTIMIZATION
    
    public int maxProfit(int k, int[] prices){
        int n = prices.length;
        int ans = solve(prices, 0, 1, k);
        return ans;
    }
    
     public int solve(int[] arr, int IDX, int BUY, int K){
         //                   [buy][k] // buy -> 0/1 (size=2) , k ->  0/1/2(size = 3)
        int[][] curr = new int[2][K+1];
        int[][] prev = new int[2][K+1];
        // change dp[idx] to curr (sabse pehle ye chalna chahiye base case me(so, vo to curr) hi hoga)
        // change dp[idx+1] to prev
         
        int n = arr.length;
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){    // buy ka loop 1 se 0 kar do
                for(int k = K; k >= 0; k--){       // k ka loop 2 se 0 kar do ya 0 to 2 no problem
                    if(idx == n || k == 0){ curr[buy][k] = 0; continue;}

                    int maxprofit = 0;
                    if(buy == 1){
                        int recans1 = prev[0][k] - arr[idx]; 
                        int recans2 = prev[1][k];  

                        maxprofit = Math.max(recans1, recans2);
                    }
                    else{
                        int recans1= prev[1][k-1] + arr[idx]; 
                        int recans2 = prev[0][k];

                        maxprofit = Math.max(recans1, recans2);
                    }

                    curr[buy][k] = maxprofit;                    
                }
            }
            prev = curr;
        } 
        
         return curr[BUY][K];
    }
    
//==================================================================================

// LC-309. Best Time to Buy and Sell Stock with Cooldown

    // pura BUY SELL STOCK K = INFINITE TRANSACTION KA COPY PASTE HA 
    // BUS EK LINE ME CHANGE HA
    // SELL KARTE TIME f(idx+2) kake bej do bass
    // and ye base case extra laga do if(idx == arr.length+1) return 0;  
    // done question!!
    
    // recurssion(memo) 
    // O(n*2) time & O(n) space
    public int maxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int[] x : dp) Arrays.fill(x, -1);
        
        int ans = solve(prices, 0, 1, dp);
        return ans;
    }
    
    public int solve(int[] arr, int idx, int buy, int[][] dp){
        if(idx == arr.length+1) return 0;   // and ye bhi karna padega as it can overflow
        if(idx == arr.length) return dp[idx][buy] = 0;
        
        if(dp[idx][buy] != -1) return dp[idx][buy];
        
        int ans = 0;
        if(buy == 1){  // [can buy] condition
            int recans1 = solve(arr,idx+1, 0, dp) - arr[idx];  
            int recans2 = solve(arr,idx+1, 1, dp);
            
            ans = Math.max(recans1, recans2);
        }
        else{ // [can sell] condition
            int recans1 = solve(arr,idx+2, 1, dp) + arr[idx];  // bass yaha change ayega idx+1 -> idx+2 kar dege bass
            int recans2 = solve(arr,idx+1, 0, dp);
            
            ans = Math.max(recans1, recans2);
        }
        
        return dp[idx][buy] = ans;
    }
    
    //    // tabulation
    // O(n) time & O(n) space
    public int maxProfit(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+2][2];   // NOTE : yaha size (n+2) ho jayega
        // for(int[] x : dp) Arrays.fill(x, -1);  // -1 initialize ni karna wrong ans ayega 0 hi rehne do 
        
        int ans = solve(prices, 0, 1, dp);
        return ans;
    }
    
    public int solve(int[] arr, int IDX, int BUY, int[][] dp){
        int n = arr.length;
        
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){
                if(idx == arr.length+1) continue;   // and ye bhi karna padega as it can overflow
                if(idx == arr.length){ dp[idx][buy] = 0; continue;}

                int ans = 0;
                if(buy == 1){  // [can buy] condition
                    int recans1 = dp[idx+1][0] - arr[idx]; //  solve(arr,idx+1, 0, dp) - arr[idx];  
                    int recans2 = dp[idx+1][1]; // solve(arr,idx+1, 1, dp);

                    ans = Math.max(recans1, recans2);
                }
                else{ // [can sell] condition
                    int recans1 = dp[idx+2][1] + arr[idx]; // // bass yaha change ayega idx+1 -> idx+2 kar dege bass
                    int recans2 = dp[idx+1][0];

                    ans = Math.max(recans1, recans2);
                }

                dp[idx][buy] = ans;                
            }
        }
        
        return dp[IDX][BUY];
    }
        

// NOTE : 
// SPACE OPTIMIZATION: ni hogi iski as idx+2 hoga to 3 array lena padega and swapping me dikkat ayegi
    
    
//==================================================================================

// LC-714. Best Time to Buy and Sell Stock with Transaction Fee

// ye vohi question ha [K == INFINTE TRANSACTION] VALA 
// BASS YAHA [CAN SELL CONDITION] me sell karte time (-fee) kar dege extra bass
// baki ditto same ha pura code

// MEMOIZATION :
   public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        int ans = solve(prices, 0, 1, fee, dp);
        return ans;
    }
    
    public int solve(int[] arr, int idx, int buy, int fee, int[][] dp){
        if(idx == arr.length) return dp[idx][buy] = 0;
        
        if(dp[idx][buy] != 0) return dp[idx][buy];
        
        int maxans = 0;
        if(buy == 1){ // [can buy condition]
            int recans1 = solve(arr, idx+1, 0, fee, dp) - arr[idx];
            int recans2 = solve(arr, idx+1, 1, fee, dp);
            
            maxans = Math.max(recans1, recans2);
        }
        else{  // [can sell condition]
            int recans1 = solve(arr, idx+1, 1, fee, dp) + arr[idx] - fee;  // transaction fees yaha minus karni padegi sell karte vakt as 1 transaction complete hui ha
            int recans2 = solve(arr, idx+1, 0, fee, dp);
            
            maxans = Math.max(recans1, recans2);
        }
        
        return dp[idx][buy] = maxans;
    }

// TABULATION:

// TABULATION
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        int ans = solve(prices, 0, 1, fee, dp);
        return ans;
    }
    
    public int solve(int[] arr, int IDX, int BUY, int fee, int[][] dp){
        int n = arr.length;
        
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){  // since buy can be -> 0/1 isliye 1 se start kiya

                if(idx == arr.length) {dp[idx][buy] = 0; continue;}

                int maxans = 0;
                if(buy == 1){ // [can buy condition]
                    int recans1 = dp[idx+1][0] - arr[idx];  
                    int recans2 = dp[idx+1][1];  

                    maxans = Math.max(recans1, recans2);
                }
                else{  // [can sell condition]
                    int recans1 = dp[idx+1][1] + arr[idx] - fee;  // transaction fees yaha minus karni padegi sell karte vakt as 1 transaction complete hui ha
                    int recans2 = dp[idx+1][0];

                    maxans = Math.max(recans1, recans2);
                }

                dp[idx][buy] = maxans;                
            }
        }

        return dp[IDX][BUY];
    }

// SPACE OPTIMIZATION:

   // SPACE OPTIMIZATION
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        int ans = solve(prices, 0, 1, fee);
        return ans;
    }
    
    public int solve(int[] arr, int IDX, int BUY, int fee) {
        int n = arr.length;
        int[] curr = new int[n+1];
        int[] prev = new int[n+1];
        // replace dp[idx] to curr
        // replace dp[idx+1] to prev
        
        for(int idx = n; idx >= 0; idx--){
            for(int buy = 1; buy >= 0; buy--){  // since buy can be -> 0/1 isliye 1 se start kiya

                if(idx == arr.length) {curr[buy] = 0; continue;}

                int maxans = 0;
                if(buy == 1){ // [can buy condition]
                    int recans1 = prev[0] - arr[idx];  
                    int recans2 = prev[1];  

                    maxans = Math.max(recans1, recans2);
                }
                else{  // [can sell condition]
                    int recans1 = prev[1] + arr[idx] - fee;  // transaction fees yaha minus karni padegi sell karte vakt as 1 transaction complete hui ha
                    int recans2 = prev[0];

                    maxans = Math.max(recans1, recans2);
                }

                curr[buy] = maxans;                
            }
            prev = curr;  // ye karna bhot jaruri ha varna wrong ans ayega as ek itteration ke baad prev amara curr le lega and now curr naye tarike se banega abb
        }

        return curr[BUY]; // yaha bhi replace dp[IDX] to curr
    }