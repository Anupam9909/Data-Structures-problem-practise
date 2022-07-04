
//===================================================================================
//                              # DIJKSTRA ALGORITHM #                              ||
//===================================================================================
// DIJKSTRA ALGO -> USED TO FIND THE SHORTEST PATH FROM A PARTICULAR SOURCE TO ALL OTHER NODES IN ONE GO

// NOTE: dijkstra algorithm kuch khaas ni ha -> its nothing but a bfs algorithm jisme haam 
// bfs hi use karte ha and csf(cost so far) use karte ha and queue ki jagah PriorityQueue 
// me rakhte ha taki best node hame pehle mile taki destination tak minimum cost me pahuch sake 

// NOTE: hamesha dijkstra ya kissi bhi question me jisme bfs use ho rhaa ho vaha -> BFS me(rm*wa*) vala(cyclic bfs) vala hi algo likho 

// summary :
// NOTE : 1. PQ me return (this.dist-other.dist) hoga  // ie. (min pq)
//        2. initialise the ans dist array with (int)1e9 -> ie. max value
//        3. rm*wa* (cycle)vala hi bfs likhna isme    

   public int[] dijkstra(ArrayList<pair>[] graph, int src, int n){
        boolean[] vis = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, (int)1e9);

        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->{   // pair : {idx, distance}
            return a[1]-b[1];     // distance ke basis pe sort karna ha imp this also
        }); 
        
        que.add(new int[]{src, 0});
        // vis[src] = true;
        
        while(que.size() != 0){
            int s = que.size();
            while(s-- > 0){
                int[] rp = que.remove();
                
                // mark*
                if(vis[rp[0]]){
                    continue;
                }
                vis[rp[0]] = true;
                
                //work
                dist[rp[0]] = rp[1];
                
                // a*
                for(pair e : graph[rp[0]]){
                    int v = e.v, w = e.w;
                    if(!vis[v]){
                        que.add(new int[]{v, rp[1]+w});
                    }
                }
            }
        }
        
        return dist;
    }
    

//============================================================================================================

// copy questions
// LC - 743 Network Delay Time

    // cocept: jab bhi question me lage ki bfs lag raha ha and ek priority ke order me lag raha 
    // ha ie csf(cost so far) ke basis par que me dalna paad raha ha node ko to issi ko dijkstra algo kehte ha  
    // yaha bhi bfs+priority-> ie. dijkstra algorithm kehte ha
    public class pair{
        int v, w;
        pair(int v, int w){this.v = v; this.w = w;}
    }
    
    public int networkDelayTime(int[][] edges, int n, int k) {
        List<pair>[] graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)  graph[i] = new ArrayList<>();
        for(int[] e : edges){
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new pair(v, w));          
        }
        
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->{
            return a[1]-b[1];
        }); //int[] === pair:{idx, csf}
        
        boolean[] vis = new boolean[n+1];
        que.add(new int[]{k, 0});
        int count = 0;
        int ans = 0;
        
        while(que.size() != 0){
            int s = que.size();
            while(s-->0){
                // r
                int[] rem = que.remove();
                int idx = rem[0], csf = rem[1];
                
                // m*
                if(vis[idx]){
                    continue;
                }
                vis[idx] = true;
                
                // w
                count++;
                if(count == n){
                    return csf;
                } 
                
                //a*
                for(pair p : graph[idx]){
                    if(!vis[p.v]){
                        que.add(new int[]{p.v, csf+p.w});
                    }
                }
            }
        }
        
        if(count < n) return -1;
        return 0;
    }
    

//===================================================================================

// LC- 787. Cheapest Flights Within K Stops

    // 1. DFS SOLUTION : will give tle(not a good solution)
    // 2. BFS solution ie.(DIJKSTRA ALGORITHM) iss question me ye dijkstra nahi lagega as yaha only directed edges nahi ha both sided directed(ye undirected jesa hi ha) given ha graph. since we know that bina visited array liye bhi dijkstra algo chalta ha but iss question me nahi chal payega as undirected jesa graph ban raha ha. Now agar haam visited le bhi lege to bhi ans galat ayega as try this test case:
// 4
// [[0,3,59],[2,0,83],[2,3,32],[0,2,97],[3,1,16],[1,3,16]]
// 3
// 0
// 3
    
    // so visited use kiya to ans galat ayega kuch example me 
    // and use nahi kiya to infinite loop baan jayega
    // so to resolve this BELLMAN FORD ALGORITHM IS their to solve question
    
    // BELLMAN FORD solution
    public int findCheapestPrice(int n, int[][] edges, int src, int dst, int k){
        int[] dest = new int[n];
        Arrays.fill(dest, (int)1e9);
        dest[src] = 0;
        
        for(int edgeCount = 1; edgeCount <= k+1; edgeCount++){
            int[] newdest = new int[n];
            for(int i = 0; i < n; i++)  newdest[i] = dest[i];
            
            for(int[] e : edges){
                int u = e[0], v = e[1], w = e[2];
                if(dest[u] == (int)1e9) continue;
                
                // if(dest[u] + w < newdest[v]) newdest[v] = dest[u] + w;  // ye condition likho ya niche vali baat same ha
                newdest[v] = Math.min(newdest[v], dest[u] + w);  
            }
            
            dest = newdest;
        }
        
        return dest[dst]==(int)1e9 ? -1 : dest[dst];
    }
    
       


//===================================================================================

//  -> LC - 778. Swim in Rising Water

class pair{
    int idx;
    int tsf;
    pair(int i, int t){this.idx = i; this.tsf = t;}
}
public int swimInWater(int[][] arr){
    int n = arr.length, m = arr[0].length;
    
    PriorityQueue<pair> que = new PriorityQueue<>((a,b)->{
        return a.tsf-b.tsf;  //this-other // min pq
    });
    int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    boolean[][] vis = new boolean[n][m];
    que.add(new pair(0,arr[0][0]));
    
    while(que.size() != 0){
        int s = que.size();
        while(s-- > 0){
            pair p = que.remove();  // r
            int r = p.idx/m, c = p.idx%m;
            int tsf = p.tsf;
            
            if(vis[r][c]){ //m*
                continue;
            }
            vis[r][c] = true;
            
            // w
            if(r == n-1 && c == m-1)  return tsf; 
            
            //a*
            for(int d = 0; d < 4; d++){
                int x = r+dir[d][0];
                int y = c+dir[d][1];
                if(x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]){
                    int nidx = x*m+y;
                    int ntsf = Math.max(tsf, arr[x][y]);
                    
                    que.add(new pair(nidx, ntsf));
                }
            }    
        }
    }
    
    return 0;
}

//===========================================================================================================

// these questions are good that are based on
//  Dijkstra based question imp. 

//  -> LC - 1631. Path With Minimum Effort
    // graph ka question ha ye // dp se nahi hoga(TLE ayega)
    // yaha bfs laag raha tha and since we have to find the minimum jump so yaha
    // dijkstra algorithm(bass bfs me Q ko PQ bana do ho gya dijkstra) lagega jisme  
    class pair{
        int idx, val, maxjump;   
        pair(int i, int v, int j){this.idx = i; this.val = v; this.maxjump = j;}
    }
    
    public int minimumEffortPath(int[][] arr){
        int n = arr.length, m = arr[0].length;
        PriorityQueue<pair> que = new PriorityQueue<>((a,b)->{
            return a.maxjump - b.maxjump;
        });  // pair:{idx, val, maxjump}
        
        int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
        
        boolean[][] vis = new boolean[n][m];
        que.add(new pair(0, arr[0][0], 0));
        
        while(que.size() != 0){
            int s = que.size();
            while(s-- > 0){
                pair p = que.remove();
                int r = p.idx/m, c = p.idx%m;
                
                if(vis[r][c]){
                    continue;
                }
                vis[r][c] = true;
                
                // work
                if(r == n-1 && c == m-1) return p.maxjump;
                //a*
                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if(x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]){
                        int nidx = x*m+y;
                        int nval = arr[x][y];
                        int jump = Math.abs(p.val-nval);
                        int newmaxjump = Math.max(p.maxjump, jump);
                        
                        que.add(new pair(nidx, nval, newmaxjump));
                    }
                }
            }
        }
        return 0;
    }
    

//=================================================================================================================


// LC - 1786. Number of Restricted Paths From First to Last Node

    // iss question me (graph + dp) dono lag rahe ha 
    // dfs kane me dp lagegi and dijkstra algo use hogi
    
    // concept : jo question me bola ha vesa kar do, pehle sare node ka restricted path nikal lo 
    // and then through dfs find the total number of path(yaha dp lagegi)
    
    // max do function me likh sakte the but simlify karne ke liye total 4 function me toda ha
    public class pair{  //graph me yaha pair ki jagah int[] bhi le sakte ha //{v, weight} as both are integer
        int v;
        int w;
        pair(int v, int w){this.v = v; this.w = w;}
    }
    
    public int countRestrictedPaths(int n, int[][] edges) {
        List<pair>[] g = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        for(int[] e : edges){
            int u = e[0], v = e[1], w = e[2];
            pair p = new pair(v, w);
            g[u].add(p);
            
            pair p2 = new pair(u,w);
            g[v].add(p2);
        }
        
        int[] shortestpath = distanceToLastNode(g, n);
        // for(int x : shortestpath) System.out.print(x  + " ");
        
        boolean[] vis = new boolean[n+1];
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        int ans = dfs(g, 1, n, shortestpath, vis, dp);
        return ans;
    }
    
        
    public int[] distanceToLastNode(List<pair>[] g, int n){
        int src = n;
        int[] dist = dijkstra(g, src, n);  // yahi catch ha iss question me ->  haam yaha ulta kaam kar rahe ha (src) me last node bej rahe ha taki hame ek hi itteration me sare shortest path mil jaye ultimately ie. from all nodes to dest(nth node)
        // agar haar ek node ke liye loop laga ke dijikstra use karte to TLE ata 
        
        return dist;
    }
    
// NOTE: hamesha dijkstra ya kissi bhi question me jisme bfs use ho rhaa ho vaha -> BFS(me rm*wa*) vala(cyclic bfs) vala hi algo likho 
    public int[] dijkstra(List<pair>[] g, int src, int n){
        boolean[] vis = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, (int)1e9);

        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->{   // pair : {idx, distance}
            return a[1]-b[1];
        }); 
        
        que.add(new int[]{src, 0});
        // vis[src] = true;
        
        while(que.size() != 0){
            int s = que.size();
            while(s-- > 0){
                int[] rp = que.remove();
                
                // mark*
                if(vis[rp[0]]){
                    continue;
                }
                vis[rp[0]] = true;
                
                //work
                dist[rp[0]] = rp[1];
                
                // a*->m
                for(pair e : g[rp[0]]){
                    int v = e.v, w = e.w;
                    if(!vis[v]){
                        que.add(new int[]{v, rp[1]+w});
                    }
                }
            }
        }
        
        return dist;
    }


    public int dfs(List<pair>[] g, int src, int dest, int[] shortestpath, boolean[] vis, int[] dp){
        if(src == dest) return dp[src] = 1;
        
        if(dp[src] != -1) return dp[src];
        
        vis[src] = true;
        int count = 0;
        int mod = (int)1e9+7;
        
        for(pair e : g[src]){
            int v = e.v;
            if(!vis[v] && shortestpath[src] > shortestpath[v]){
                count = (count%mod + dfs(g, v, dest, shortestpath, vis, dp)%mod)%mod;
            }
        }
        vis[src] = false;
        
        return dp[src] = count;
    }
    
    // if no dp use (TLE aya phir)

    
//===================================================================================================================
