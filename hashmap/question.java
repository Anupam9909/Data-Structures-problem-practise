
public class question{
    // LC - 349 intersection of two array(easy tag)
    // hm lene ki bhi jarurat ni ha simply hashset le lo and do it
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hs = new HashSet<>();
        for(int x : nums1) hs.add(x);
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i < nums2.length; i++){
            if(hs.contains(nums2[i])) {
                ans.add(nums2[i]); 
                hs.remove(nums2[i]);
            }    
        }
        
        int[] fans = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) fans[i] = ans.get(i);
        
        return fans;
    }


    // LC - 350 Interaction of two array - II (easy tag)
    // Simply ek array ko HashMap me daal lo i.e {element, freqency}
    // then dusre array ko traverse karke decrease freq each time and remove those freq = 0
    // and calculate the intersection (common) element.
    
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int i = 0; i < nums1.length; i++){
            hm.put(nums1[i], hm.getOrDefault(nums1[i],0) + 1);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < nums2.length; i++){
            int t = hm.getOrDefault(nums2[i],-1);
            if(t != -1){
                ans.add(nums2[i]);
                if(t == 1) hm.remove(nums2[i]);
                else hm.put(nums2[i], hm.get(nums2[i])-1);
            } 
        }
        
        int[] fans = new int[ans.size()];
        for(int i = 0;i < ans.size(); i++) fans[i] = ans.get(i);
        
        return fans;
    }

//====================================================================================================


    // LC- 128 longest Consecutive sequence
    // sabse best solution ha ki sare elements ko Hashset me daalo
    // then for each nums ele do this thing
    // x element ko x-- karte karte le jao and calculate (leftcount) i.e -> hs.contains(x) and remove those x-- element
    // similarly, x element ko x++ karte karte le jao and calculate (rightcount) i.e -> hs.contains(x++) and remove those x++ element

    // NOTE : iski complexity O(N^2) nahi hogi as haam remove bhi kar rahe ha 
    public int longestConsecutive(int[] nums){
        HashSet<Integer> hs = new HashSet<>();
        
        for(int x : nums) hs.add(x);
        int ans = 0;
        for(int ch : nums){
            if(!hs.contains(ch)) continue; 
                
            int temp = ch-1;
            int lcount = 0;
            while(hs.contains(temp)){
                hs.remove(temp);
                lcount++;
                temp--;
            } 
            
            int rcount = 0;
            int temp2 = ch+1;
            while(hs.contains(temp2)){
                hs.remove(temp2);
                rcount++;
                temp2++;
            }
            
            ans = Math.max(ans, lcount+rcount+1);
                hs.remove(ch);
        }
        
        return ans;
    }
    
    

//====================================================================================================


    // LC-347.    TOP K-FREQUENT ELEMENTS

    public class pair implements Comparable<pair>{
        int val;
        int freq;
        pair(int v, int f){
            this.val = v;
            this.freq = f;
        }
        
        public int compareTo(pair p){
            return this.freq - p.freq;  // (this - other) -> gives default behaviour [i.e MIN. PQ]
        }
    }

    public int[] topKFrequent(int[] arr, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        PriorityQueue<pair> pq = new PriorityQueue<>();
        
        for(int i = 0; i < arr.length; i++){
            if(hm.getOrDefault(arr[i],0) == 0){
                hm.put(arr[i], 1);
            }else{
                hm.put(arr[i], hm.getOrDefault(arr[i],0) + 1);
            }
        }
        
        for(Integer x : hm.keySet()){
            pair p = new pair(x, hm.get(x));
            pq.add(p);
            if(pq.size() > k){
                pq.remove();
            }
        }
        
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){
            pair p = pq.remove();
            ans[i] = p.val;
        }
        
        return ans;
    }
        
    
    // LC - 692. Top K Frequent Words
    // QUESTION:
    // Given an array of strings words and an integer k, return the k most frequent strings.
    // Return the answer sorted by the frequency from highest to lowest. 
    // Sort the words with the same frequency by their lexicographical order.

    // SIMILAR TO ABOVE QUESTION HI HA BASS HAME COMPARE KARTE VAKAT EK CONDITION EXTRA LAGEGI
    public class pair implements Comparable<pair>{
        String st ;
        int freq;
        pair(String s, int f){
            this.st = s;
            this.freq = f;
        }
        
        public int compareTo(pair other){
            if(this.freq == other.freq){
                return other.st.compareTo(this.st);
            }else{
                return this.freq-other.freq;
            }
        }
        
    }
    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(String s : words) hm.put(s, hm.getOrDefault(s,0)+1);
        
        for(String s : hm.keySet()){
            int frq = hm.get(s);
            pq.add(new pair(s,frq));
            
            if(pq.size() > k) pq.remove(); 
        }
        
        List<String> ans = new ArrayList<>();
        int i = 0;
        while(i < k) ans.add(i++, pq.remove().st);
        
        Collections.reverse(ans);
        return ans;
    }

//===============================================================================

// LC- 49. Group Anagrams

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
    // I way hm ->{freq string , List<String>}
    public List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> hm = new HashMap<>();
        int n = strs.length;
        
        for(int i = 0; i < n; i++){
            String s = strs[i];
            int[] freq = new int[26];
            for(char ch : s.toCharArray()) freq[ch-'a']++; 
            
            String st = "";
            for(int x : freq) st += (x+".");   // x + "." -> yaha haam dot se seperate kar lege numbers ko as 1.10 bhi ho sakta ha koi string and 11.0 bhi ho sakte ha so dot se hi differentiate ho payega
            
            if(!hm.containsKey(st)){
                List<String> ans = new ArrayList<>();
                ans.add(s);
                hm.put(st, ans);
            }else{
                hm.get(st).add(s);
            }
        }
        
        List<List<String>> fans = new ArrayList<>();
        for(String s : hm.keySet()){
            List<String> temp = hm.get(s);
            fans.add(temp);
        }
        
        return fans;
    }
    
    //====================================================================================
    
    // II way  ->{sorted string , List<String>}
    // using sorting of string
       public List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> hm = new HashMap<>();
        int n = strs.length;
        
        for(int i = 0; i < n; i++){
            String s = strs[i];
            
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = "";
            for(char c : ch)  key += (c+"");
            
            if(!hm.containsKey(key)){
                List<String> ans = new ArrayList<>();
                ans.add(s);
                hm.put(key, ans);
            }else{
                hm.get(key).add(s);
            }
        }
        
        List<List<String>> fans = new ArrayList<>();
        for(String s : hm.keySet()){
            List<String> temp = hm.get(s);
            fans.add(temp);
        }
        
        return fans;
    }
    

//===============================================================================





















//===============================================================================

// HASHMAP + BINARY TREE question

// 652. Find Duplicate Subtrees

// concept : ek hashmap le lo ie. ---> HashMap =>  <String , ArrayList<TreeNode>>
// tree ka structure store kara lo haar subtree ka ye ek ID hogi and jiss bhi subtree ka ye id same hoga(ie. structure same hoga) then uska root store kara lo ArrayList<TreeNode> me and then finally make the ans from the hashmap

class Solution {
    HashMap<String, ArrayList<TreeNode>> hm;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        hm = new HashMap<>();
        
        String str = solve(root);
        
        // making ans 
        List<TreeNode> fans = new ArrayList<>();
        for(String x : hm.keySet()){
            if(hm.get(x).size() > 1){
                TreeNode t = hm.get(x).get(0);
                fans.add(t);
            }
        }
        
        return fans;
    }
    
    public String solve(TreeNode root){
        if(root == null) return "null";
        
        String leftstr = solve(root.left);
        String rightstr = solve(root.right);
        
        String mystr = (root.val +","+ leftstr + "," + rightstr);
        
        if(hm.containsKey(mystr)){
            ArrayList<TreeNode> arr = hm.get(mystr);
            arr.add(root);
        }else{
            ArrayList<TreeNode> tans = new ArrayList<>();
            tans.add(root);
            hm.put(mystr, tans);
        }
        
        return mystr;
    }
}