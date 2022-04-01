// LC-684. Redundant Connection

// SOLUTION:

// using only path commpression (YAHI TARIKA USE KARO HAMESHA (SHORT+VERY LESS COMPLEXITY-> BECAUSE SIZE VALA ARRAY KARO YA MAT KARO JADA FARAK NI PADTA)
   static int[] par;
    
    public int[] findRedundantConnection(int[][] edges) {
        int[] ans = new int[2];
        
        // filing parent array
        par = new int[1000+1];
        for(int i = 0; i <= 1000; i++) par[i] = i;
        
        int n = edges.length;
        for(int i = 0; i < n;  i++){
            int u = edges[i][0], v = edges[i][1];
            
            int l1 = findleader(u);
            int l2 = findleader(v);
            
            if(l1 != l2){
                par[l1] = l2;
            }else{
                // cycle ha so, save ans
                ans[0] = edges[i][0];
                ans[1] = edges[i][1];
            }
        }
        
        return ans;
    }
    
    public int findleader(int u){
        if(par[u] == u) return u;
        
        int recans = findleader(par[u]);
        par[u] = recans;
        
        return recans;
    }
    

//====================================================================================

// gcc - get Connected Component using [union find]

    public int getConnectedComponent(int n, int[][] arr){
	// firstly make graph(ie. fill the parent array)
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        for(int[] x : arr){
            int u = x[0], v = x[1];
            int l1 = findleader(u);
            int l2 = findleader(v);
            
            if(l1 != l2)  parent[l1] = l2; 
        }
        

        // now find gcc
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0; i < n; i++) hs.add(findleader(i));   // findleader function hi call karna padega imp. ha

        int ans = hs.size();
	return ans;
    }


//=================================================================================

// LC-1319 -  Number of Operations to Make Network Connected

// dfs se bhi kar sakte ha but union find se quickly ho jayega as yaha graph banana ni padta
    static int[] parent;
    public int findleader(int u){
        if(parent[u] == u) return u;
        int recans = findleader(parent[u]);
        return parent[u] = recans;
    }
    
    public int makeConnected(int n, int[][] arr){
        int edges = arr.length;
        if(edges < n-1) return -1;
        
        // find gcc using union find
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;  // initialise is very imp
        for(int[] x : arr){
            int u = x[0], v = x[1];
            int l1 = findleader(u);
            int l2 = findleader(v);
            
            if(l1 != l2) parent[l1] = l2;
        }

        HashSet<Integer> hs = new HashSet<>();
        
        for(int i = 0; i < n; i++) hs.add(findleader(i));
        int gcc = hs.size()-1;
        
        return gcc;        
    }