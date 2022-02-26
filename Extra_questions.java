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

//===================================================================================

// gfg - Count pairs in array whose sum is  divisible by K
// jab bhi (array) and (divisible by k) jesa term aye question me to hashmap use 
// ho raha hoga
public static int countKdivPairs(int arr[], int n, int k)
    {
        //code here
        int ans = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(k,0);
        
        for(int i = 0; i < n; i++){
            int rem = arr[i]%k;
        
            if(hm.containsKey(k-rem)){
                int count = hm.get(k-rem);
                ans += count;
            }
            
            hm.put(rem, hm.getOrDefault(rem,0)+1);
            
            // 0 ka special case handle karna padega 
            // when rem == 0 then increase the frequency of (k-rem) in hm
            if(rem == 0){
                hm.put(k-rem, hm.getOrDefault(k-rem,0)+1);    
            }
        }
        
        return ans;
    }