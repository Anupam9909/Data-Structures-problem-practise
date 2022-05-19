// LC - 802. Find Eventual Safe States

 // using dp i.e memo(accepted)
 // kabhi kabhi simple graph solution me for loop me jab bhi kaam karte ha 
 // to usme repeted kaam karna padta ha jiski vajah se TLE aa jata ha question me.
 // so we can use DP i.e memoization (ek array me ans store kara lege and we use it whenever it is needed,)(and we know memo code me 2 line thokni ha bass) in this to reduce our operation and it will accept.
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> fans = new ArrayList<>();
        int n = graph.length;
        boolean[] memo = new boolean[n];
        for(int i = 0; i < n; i++){
            boolean[] vis = new boolean[n];
            boolean ans = dfs(graph, i, vis, memo);
            if(ans)  fans.add(i);
        }
        
        Collections.sort(fans);
        return fans;
    }
    
    public boolean dfs(int[][] graph, int idx, boolean[] vis, boolean[] memo){
        if(memo[idx] == true) return true;
        vis[idx] = true;
        boolean ans = true;
        for(int v : graph[idx]){
            if(vis[v]) return false;
            if(!dfs(graph, v, vis, memo)) return false;
        }
        vis[idx] = false;
        return memo[idx] = true;
    }

