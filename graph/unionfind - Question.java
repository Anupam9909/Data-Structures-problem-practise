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