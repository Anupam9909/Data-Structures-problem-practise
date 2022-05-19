
// Dijkstra Algorithm
// NOTE: hamesha dijkstra ya kissi bhi question me jisme bfs use ho rhaa ho vaha -> BFS(me rm*wa*) vala(cyclic bfs) vala hi algo likho 
    
// NOTE : 1. PQ me return (this.dist-other.dist) hoga 
//        2. initialise the ans dist array with (int)1e9 -> ie. max value
//        3. rm*wa* vala hi bfs likhna isme    

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
    
   
//  Dijkstra based question imp. 
//  -> LC - 1786. Number of Restricted Paths From First to Last Node
//  https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
//  solution done in the leetcode site itself

//  -> LC - 1631. Path With Minimum Effort
// https://leetcode.com/problems/path-with-minimum-effort/
// solution done in the leetcode site itself
//===================================================================================

