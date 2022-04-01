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


// ============================================================================

// LC-49. Group Anagrams

// hame actual me yaha 
    // simply string ki array ko traverse karege then
    // for a particular string ham uska freq array banayege and iss freq(of 26 size) array ko ek string me convert kar lege 
    // and now ye string ek unique string hogi jo ki hashmap me ek key ki tarah act karegi
    // so we will make HashMap of (String vs List<String>) 
    // List<String> me ans store karte chalege
    
	// ie for an lame example hm will be
    // 20001010011000110011 ----> eat, tea, ate
    // 10000101001010000010 ----> bat, 
    // 00000000001000001000 ----> nat, tan

    public List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> ans = new ArrayList<>();
        int n = strs.length;
        
        HashMap<String, List<String>> hm = new HashMap<>();
        
        for(int i = 0; i < n; i++){
            String s = strs[i];
            
            int[] freq = new int[26];
            for(char c : s.toCharArray()) freq[c-'a']++;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 26; j++) sb.append((char)freq[j]+"");
            String st = sb.toString();
            
            if(hm.containsKey(st)){
                hm.get(st).add(s);
            }else{
                List<String> tp = new ArrayList<>();
                tp.add(s);
                hm.put(st, tp);
            }
        }
        
        // making ans
        for(String x : hm.keySet()){
            ans.add(hm.get(x));
        }
        
        return ans;
    }
    

// ============================================================================

// LC-991. Broken Calculator
// QUESTION KE NAAM SE KUCH LENA DENA NAHI HA (read question)
YE SIMPLY VALUE SE TARGET MIN STEP KITNE STEP ME POCHEGE YE PUCHA HA

// CONCEPT : ulta chalo i.e 
    // target se val banao recurrsion karke vakt 
    // and cases socho if(val > target)
    // and if(val < target)
    
    static int minop;
    public int brokenCalc(int val, int target) {
        minop = (int)1e9+7;
        // now target will be val & val will be our target
        solve(target, val, 0);
        return minop;
    }
    
    public void solve(int val, int target, int op){
        if(val == target){
            minop = Math.min(minop, op);
            return;
        }
        
        // System.out.println(val + " " + target);
        if(val > target){  // yaha dono case ho sakte ha /2 vala bhi and +1 vala bhi 
            if(val%2 == 0) solve(val/2, target, op+1);
            else  solve(val+1, target, op+1);
        }else{
            // yaha to /2 vala case hoga hi nahi +1 vala hi hoga and ye to haam direct hi nikal sakte ha
            int temp = target-val;
            solve(target, target, op+temp);
            // solve(val+1, target, op+1); // NOTE: yaha ye karne se overflow ayega
        }
    }
    