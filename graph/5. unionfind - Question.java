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




//=============================================================================================================================================

// LC - 
// question -> https://leetcode.ca/all/1061.html
// submit here -> https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859?leftPanelTab=0

// concept : 1. since haame group me karna ha same valo ko and then ans banana ha (so group bola iska matlab union find lagega)
//              & union find haam number pe lagayege na ki character pe (ek char array le lo 26 size ka and do it)
//           2. since question me bola ha ki lexiographically order me ana chahiye ans -> to ye bhi ho jayega yahi pe -> bass haame 
//              dyaan rakhna ha ki union find me merge karte vakt jiska number chota hua(means vo chota ha character me so ye hamesha parent banega dusre ka)

    static int[] par;
    public static int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }
    public static String smallestString(String s, String t, String str) {
        par = new int[26];
        for(int i = 0; i < 26 ; i++){
        par[i] = i;
        }
        
        for(int i = 0; i < s.length(); i++){
            char ch1 = s.charAt(i), ch2 = t.charAt(i);
            int a = ch1-'a', b = ch2-'a';
            // union find ch1 & ch2
            int p1 = findParent(a);
            int p2 = findParent(b);
            
            if(p1 != p2){
                if(p1 < p2){
                    par[p2] = p1;
                }else{
                    par[p1] = p2;
                }
            }
        }
        
        StringBuilder sb = new  StringBuilder();
        for(int i = 0; i < str.length(); i++){
            int p1 = findParent(str.charAt(i)-'a');
            char ans = (char)('a'+p1);
            
            sb.append(ans); 
        }
        
        return sb.toString();
    }




//================================================================================================================================================


// LC - 839. Similar String Groups 

// hard question ha but union find se bhot simple se ho jayega
    // concepy :-
    // union find -> simple haam haar ek element ke liye array ko traverse karege for loop ese laga ke
    // for(int i = 0; i < n; i++){
    //     for(int j = i+1; j < n; j++){
    //         // now apply union find, aur jo bhi same ha string (using a function isSimilar()) unko merge karte jao 
    //     }
    // }
    
    // and last me finally dek lo kitne groups ha total that is our ans
    public boolean isSimilar(String a, String b){
        if(a.length() != b.length()) return false;
        int count = 0;
        for(int i = 0;i < a.length() ; i++){
            if(a.charAt(i) != b.charAt(i))  count++;
        }
        
        if(count == 0 ||count == 2) return true;
        return false;
    } 
    
    static int[] par;
    public int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }
    
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        par = new int[n];
        for(int i = 0 ;i < n; i++)  par[i] = i;
        
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(isSimilar(strs[i], strs[j])){
                    // if yes then merge them as they are similar
                    int p1 = findParent(i);
                    int p2 = findParent(j);
                    
                    if(p1 != p2){
                        par[p1] = p2;
                    }
                }
            }
        }
        
        // find number of group in total in union find
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0; i < n; i++){
            int p = findParent(par[i]);
            hs.add(p);
        }
        return hs.size();
    }
        


//================================================================================================================================================



// LC - 990. Satisfiability of Equality Equations

    // union find se solve karege question 
    // NOTE 1 : pehle haam sare only equal(==) vale equation ko union find kar lege taki jo bhi equal ha vo apne apne group me aa jaye
    // NOTE 2 : now haam sare only not equal(!=) vale equation ko union find me check karege ki equal to nahi ha inke parent agar ha that means ye equation galat ha 
        
    // NOTE : agar ye sath me hi union find karte to hamara ans galat ata as kya pata == vali equation baad me ayi ho eg. ["a==b", "b==c", "c!=d", "a==d"] iss case me galat ho jayega ans, hence isliye pehle equal vale sare cases ko union find kar diya then check kiya unequal valo ko
        
    int[] par;
    public int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }

    public boolean equationsPossible(String[] arr) {
        par = new int[26];
        for(int i = 0; i < 26; i++) par[i] = i;
        
        int n = arr.length;
        // pehle haam sare only equal(==) vale equation ko union find kar lege taki jo bhi equal ha vo apne apne group me aa jaye
        for(int i = 0; i < n; i++){
            String str = arr[i];
            if(str.charAt(1) == '='){
                char c1 = str.charAt(0), c2 = str.charAt(3);
                int p1 = findParent((int)(c1-'a'));
                int p2 = findParent((int)(c2-'a'));
                
                if(p1 != p2){
                    par[p1] = p2;
                }
            }
        }
        
        // now haam sare only not equal(!=) vale equation ko union find me check karege ki equal to nahi ha inke parent agar ha that means ye equation galat ha 
        for(int i = 0; i< n; i++){
            String str = arr[i];
            if(str.charAt(1) != '='){
                char c1 = str.charAt(0), c2 = str.charAt(3);
                
                int p1 = findParent((int)(c1-'a'));
                int p2 = findParent((int)(c2-'a'));
                
                if(p1 == p2) return false;
            }
        }
        return true;
    }



//================================================================================================================================================


// LC - 947. Most Stones Removed with Same Row or Column
// this question is based on number of island
// concept: iss question me basically hame number of island nikalne ha 
// ek island me remove karte karte last me sirf ek node bachega 
// so, and  = (total nodes - number of islands)

    public int find(int i){
        if(parent[i] == i) return parent[i] = i;
        int ans = find(parent[i]);
        return parent[i] = ans;
    }
    
    int[] parent;
    public int removeStones(int[][] stones) {
        int n = stones.length;
        
        // making the parent array for union find
        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        
        for(int i = 0; i < n; i++){
            int x1 = stones[i][0], y1 = stones[i][1];
            for(int j = 0; j < n; j++){
                int x2 = stones[j][0], y2 = stones[j][1];

                if(x1 == x2 || y1 == y2){
                    int par1 = find(i);  // find
                    int par2 = find(j);

                    if(par1 != par2){
                        parent[par1] = par2;   // union(merge)
                    }
                }
            }
        }
        
        HashSet<Integer> hs = new HashSet<>();
        for(int x : parent){
            int par = find(x);
            hs.add(par);
        }
        
        int unremovednodes = hs.size();
        int ans = n-unremovednodes;
        return ans;
    }