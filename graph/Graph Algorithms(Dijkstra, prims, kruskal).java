
// # DIJKSTRA ALGORITHM #

// NOTE: hamesha dijkstra ya kissi bhi question me jisme bfs use ho rhaa ho vaha -> BFS(me rm*wa*) vala(cyclic bfs) vala hi algo likho 

    // NOTE: dijkstra algorithm kuch khaas ni ha -> its nothing but a bfs algorithm jisme haam 
    // bfs hi use karte ha and csf(cost so far) use karte ha and queue ki jagah PriorityQueue 
    // me rakhte ha taki best node hame pehle mile taki destination tak minimum cost me pahuch sake 

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
    
// these questions are good that are based on
//  Dijkstra based question imp. 
//  -> LC - 1786. Number of Restricted Paths From First to Last Node
   https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
//  solution done in the leetcode site itself

//  -> LC - 1631. Path With Minimum Effort
   https://leetcode.com/problems/path-with-minimum-effort/
// solution done in the leetcode site itself


//  -> LC - 778. Swim in Rising Water
   https://leetcode.com/problems/swim-in-rising-water/
// solution done in the leetcode site itself


//===================================================================================


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


// # PRIMS ALGORITHM #

