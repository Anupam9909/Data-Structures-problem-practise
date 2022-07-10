// UNION FIND

// LC - 684. Redundant Connection (medium)

    // ye to pure union find ka hi question ha 
    // since union find => fmc*  -> where c* means agar cycle ha to take that ans and hame yahi nikalna ha
    // using only path commpression (YAHI TARIKA USE KARO)
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
    

//====================================================================================================================================================================


// LC - 685. Redundant Connection II (hard)

    // see this video -> https://www.youtube.com/watch?v=d0tqBMRZ6UQ
    // union find ka application ha ye = karna ha to last me kar lena 
    // 3 cases banege isme:-
    // (a) 2 parent
    // (b) cycle
    // (c) 2 parent + cycle
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        // we know in a tree if n edges are present then it means (n-1) nodes are present.
        // and here since one extra edge is present in graph so total nodes will be n itself
        int[] indegree = new int[n+1];  // as 1 indexing ha isliye n+1 liya
        int parent = 1, vtx = -1;
        for(int[] e : edges){
            int u = e[0], v = e[1];
            indegree[v]++;
            if(indegree[v] == 2){
                vtx = v;
                parent = 2;
            }
        }
        
        if(parent == 1){  // case (only cycle) vala case
            int[] notToTake = new int[]{-1,-1};
            int[] ans = unionFind(edges, notToTake);
            return ans; 
        }
        else if(parent == 2){
             int[] firstblacklistedge = new int[]{-1,-1};
             int[] secondblacklistedge = new int[]{-1,-1};
            int count = 0;
            
            for(int[] e : edges){
                int u = e[0], v = e[1];
                if(v == vtx){
                    if(count == 0){
                        secondblacklistedge = e;  // isko hamne secondblacklist jaan buj ke mana ha taki haam baad vali ans ki condition me use kar sakte
                        count++;
                    }else{
                        firstblacklistedge = e;
                    }
                }
            }
            
            // not taking tempans[0] edge and now doing unionfind
            int[] ans = unionFind(edges, firstblacklistedge);
            
            if(ans[0] == -1 && ans[1] == -1){  // means no cycle present -> that means yaha case (2 parent) vala bhi ho sakta ha and (2 parent + cycle) vala bhi but haamne jo upar jaan buj ke secondblacklistedge me jo edge daali ha vo isliye daali thi ki ye condition correct hoti hi haam firstblacklistedge ko return kar dege jo ki dono cases ka ans hoga
                return firstblacklistedge;
            }
            else{  // means cycle is present  => then ultimately means ye case (2 parent + cycle vala ha) : ha
                return secondblacklistedge;
            }
        }
        
        return new int[]{-1,-1};
    }
    
    static int[] par;
    public int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }
    
    public int[] unionFind(int[][] edges, int[] notToTake){
        int n = edges.length;
        par = new int[n+1];  // as 1 indexing ha isliye n+1 liya
        for(int i = 0; i < n; i++) par[i] = i;
        
        for(int[] e : edges){
            int u = e[0], v = e[1];
            if(u == notToTake[0] && v == notToTake[1]) continue;
            
            int p1 = findParent(u);
            int p2 = findParent(v);
            
            if(p1 != p2){
                par[p1] = p2;  // merge
            }else{
                return e;
            }
        }
        return new int[]{-1,-1};
    }


    //=======================================================================================================================

    // LC - 721. Accounts Merge
    
     // question union find se solve hoga
    // crux of the solution : 
    // haame basically jo [row ke indexes] ha given 2D array (accounts) me unko merge karna ha so that common vale ek sath aa jaye and baki vale alag  
    // taki same email vale ek index me aa jaye hashmap me of {index vs TreeSet} me Treeset isliye use kiya as sorted order me sort hoga string 
    
    static int[] par;
    public int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        par = new int[n];
        for(int i = 0; i < n; i++) par[i] = i;
        
        HashMap<String, Integer> hm = new HashMap<>();

        for(int i = 0; i < accounts.size(); i++){
            for(int j = 1;  j < accounts.get(i).size(); j++){
                String mail = accounts.get(i).get(j);
                if(!hm.containsKey(mail)){
                    hm.put(mail, i);
                }
                else{ // if(present in hm)
                    int idx = hm.get(mail);
                    int u = idx, v = i;
                    
                    // merge
                    int p1 = findParent(u);
                    int p2 = findParent(v);
                    
                    if(p1 != p2){
                        par[p1] = p2;  // merging
                    }else{
                        // cycle ka koi kaam ni ha yaha so leave it
                    }
                }
            }
        }
        
        HashMap<Integer, TreeSet<String>> hash = new HashMap<>();  // TreeSet isliye use kiya as sorted order me chahiye
        for(int i = 0; i < n; i++){
            int parent = findParent(i);
            hash.putIfAbsent(parent, new TreeSet<>());
            
            for(int j = 1; j < accounts.get(i).size(); j++){  // kaha (i) ayega and kaha (parent) index me dyan rakhna ha
                String str = accounts.get(i).get(j);
                hash.get(parent).add(str);
            }
        }
        
        // now just making our final ans
        List<List<String>> fans = new ArrayList<>()
            
        for(int x : hash.keySet()){
            List<String> temp = new ArrayList<>();
            
            String str = accounts.get(x).get(0);
            temp.add(str);
            
            for(String s : hash.get(x)) temp.add(s);
            
            fans.add(temp);
        }
        
        return fans;
    }

    

    //=======================================================================================================================

    // LC - 