

    //LC-329. Longest Increasing Path in a Matrix

    // NOTE : YAHA VISITED KI JARURAT NAHI HA BECUASE KISSI PATH ME AGAR HAAM CHAL RAHE HA
    // THEN USSI PATH ME RECURSSION DUBARA NAHI AA SAKTA BECUASE HAAMNE CONDITIONKI 
    // LAGAAI HA KI ONLY INCREASING PATH PE HI MOVE KARNA HA 
    // yaha to chal jayega but gfg me ni chalega so imp note yaad rakna ha agar pass ni hoga OA me to visited hata lena
    public int longestIncreasingPath(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int maxlen = 0;
        int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
        int[][] dp = new int[n+1][m+1];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                boolean[][] vis = new boolean[n][m];
                int ans = dfs(arr, i, j, vis, dir, dp);
                maxlen = Math.max(maxlen, ans);
            }
        }
        
        return maxlen;
    }
    
    public int dfs(int[][] arr, int sr, int sc, boolean[][] vis, int[][] dir, int[][] dp){
        int n = arr.length, m = arr[0].length;
        if(dp[sr][sc] != 0) return dp[sr][sc];
        
        int count = 0;
        vis[sr][sc] = true;
        for(int d = 0; d < 4; d++){
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            if(x >= 0 && y >= 0 && x < n && y < m && !vis[x][y] && arr[sr][sc] < arr[x][y]){
                count = Math.max(count, dfs(arr, x, y, vis, dir, dp));
            }
        }
        vis[sr][sc] = false;
        return dp[sr][sc] = count+1;
    }



//==================================================================================================================================


// LC - 802. Find Eventual Safe States

 // using dp i.e memo(accepted)
 // kabhi kabhi simple graph solution me for loop me jab bhi kaam karte ha 
 // to usme repeted kaam karna padta ha jiski vajah se TLE aa jata ha question me.
 // so we can use DP i.e memoization (ek array me ans store kara lege and we use it whenever it is needed,)(and we know memo code me 2 line thokni ha bass) in this to reduce our operation and it will accept.
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> fans = new ArrayList<>();
        int n = graph.length;
        boolean[] dp = new boolean[n];
        for(int i = 0; i < n; i++){
            boolean[] vis = new boolean[n];
            boolean ans = dfs(graph, i, vis, dp);
            if(ans)  fans.add(i);
        }
        
        Collections.sort(fans);
        return fans;
    }
    
    public boolean dfs(int[][] graph, int idx, boolean[] vis, boolean[] dp){
        if(dp[idx] == true) return true;
        vis[idx] = true;
        boolean ans = true;
        for(int v : graph[idx]){
            if(vis[v]) return false;
            if(!dfs(graph, v, vis, dp)) return false;
        }
        vis[idx] = false;
        return dp[idx] = true;
    }



    //==================================================================================================================================

    
    