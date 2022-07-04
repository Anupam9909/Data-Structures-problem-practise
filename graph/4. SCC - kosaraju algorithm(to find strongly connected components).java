  // KOSARAJU'S Algorithm:
  // step-1 -> do DFS and GET THE TS ORDER IN A STACK (or in an arraylist(ye liya ha to phir 3rd step me (n-1) se 0 traverse karna padega))
  // step-2 reverse the graph  
  // step-3 calcualate gcc in ngraph and this gcc is equal to scc(strongly connected components)

  public int kosaraju(int n, ArrayList<ArrayList<Integer>> edges){
       
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(ArrayList<Integer> e : adj){
            System.out.println(e.get(0) + " "+ e.get(1));
            int u = e.get(0);
            int v = e.get(1);
            graph[u].add(v);
        }
        
        // step-1 -> do DFS and ts
        List<Integer> arr = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++){
            // System.out.println(i);
            if(!vis[i]){
                dfs_ts(graph, i, arr, vis);
            }
        }
        
        
        // step-2 reverse the graph
        List<Integer>[] ngraph = new ArrayList[n];
        for(int i = 0; i < n; i++) ngraph[i] = new ArrayList<>();
        for(ArrayList<Integer> e : adj){
            int u = e.get(0);
            int v = e.get(1);
            ngraph[v].add(u);
        }
        
        // step-3 calcualate gcc in ngraph
        vis = new boolean[n];
        int count = 0;
        for(int i = arr.size()-1; i >= 0; i--){
            int vtx = arr.get(i);
            if(!vis[vtx]){
                count++;
                dfs_(ngraph, vtx, vis);
            }
        }
        return count;
    }
    
    public void dfs_ts(List<Integer>[] graph, int src, List<Integer> arr, boolean[] vis){
        vis[src] = true;
        for(int v : graph[src]){
            if(!vis[v]){
                dfs_ts(graph, v, arr, vis);
            }
        }
        arr.add(src);
    }
    
    public void dfs_(List<Integer>[] graph, int src, boolean[] vis){
        vis[src] = true;
        for(int v : graph[src]){
            if(!vis[v]){
                dfs_(graph, v, vis);
            }
        }
    }
    