LC-310. Minimum Height Trees

SOLUTION:
LEAVES KO HATATE JAO JAB TAK 1 YA 2 NODE NI MILTE 
(EK LEVEL KE SARE LEAVES HATANE KE BAAD HI CHECK KARNA HA)

 public List<Integer> findMinHeightTrees(int n , int[][] edges){
        HashSet<Integer>[]  tree = new HashSet[n];
        for(int i  = 0; i < n; i++) tree[i] = new HashSet<>();
        
        for(int[] e : edges){
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        
        int tn = n; // tn : total nodes
        while(tn > 2){
            ArrayList<Integer> leafs = new ArrayList<>();
            for(int i = 0; i < n; i++) 
                if(tree[i] != null && tree[i].size() == 1) 
                    leafs.add(i);
            
            
            for(int i = 0; i < leafs.size(); i++){
                int u = leafs.get(i);
                int v = -1;
                for(int x : tree[u]) v = x;
                
                tree[u]= null;
                tree[v].remove(u);
                tn--;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i  < n; i++){
            if(tree[i] != null) ans.add(i);
        }
        return ans;
    }