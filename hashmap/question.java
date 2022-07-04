
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



//====================================================================================================


 // LC-632. Smallest Range Covering Elements from K Lists

// yahi tarika ha yaad kar lo see video ->https://www.youtube.com/watch?v=-2h54qDGQiE  -> ISSI VIDEO SE DEK KE DIAGRAM YAAD KAR LO
    // note 1: to uniquely define an element we take index ( so index se khel rahe ha haam) ->  int[] : {row,col}      
    // NOTE 2: since haam pq ka size (k) le ke chal rahe ha that means haar point pe haamre pass pq me arr 2D list me se haar 1D array ka element pada hoga so kissi bhi point pe haam finalans ka range nikalege to vo correct hoga as uss range me pakka are k arrays ka ek element to present ha hi
    
    // ALGORITHM :  r u a -> remove, update , add
    
    public int[] smallestRange(List<List<Integer>> arr){
        int k = arr.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{   // int[] : {row, col}
            int val1 = arr.get(a[0]).get(a[1]);
            int val2 = arr.get(b[0]).get(b[1]);
            return val1-val2;
        }); 
        
        int maxval = -(int)1e9;
        for(int i = 0; i < arr.size(); i++){
            pq.add(new int[]{i, 0});
            maxval = Math.max(maxval, arr.get(i).get(0));
        }
        
        int[] finalans = {-(int)1e5, (int)1e5};
        
        while(pq.size() == k){
            // remove (r)
            int[] rem = pq.remove();
            int minval = arr.get(rem[0]).get(rem[1]);
            
            // update ans (u)
            if(maxval-minval < finalans[1]-finalans[0]){
                finalans[0] = minval;
                finalans[1] = maxval;
            }
            
            // add element (a)
            if(rem[1] + 1 < arr.get(rem[0]).size()){  // ussi removed element ka next vala element dalna ha hame
                int num = arr.get(rem[0]).get(rem[1]+1);
                maxval = Math.max(maxval, num);
                pq.add(new int[]{rem[0], rem[1]+1});  
            }   
            
        }
        
        return finalans;
    }

    
//====================================================================================================


// LC - 502. IPO

    // ek max pq le lege and ek minpq ->  https://www.youtube.com/watch?v=e7ZYZmGImSE  
    // see concept from this video
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> minpq = new PriorityQueue<>((a,b)->{  // for capital
            return capital[a]-capital[b];
        }); 
        PriorityQueue<Integer> maxpq = new PriorityQueue<>((a,b)->{  // for profit
            return  profits[b]-profits[a];
        });
        
        // note : haam index hi store kar rahe ha pq me becuase kissi bhi time haam capital[index] and profit[index] nikal sakte ha
        int n = profits.length;
        for(int i = 0; i < n; i++){
            minpq.add(i);
        }
        
        long maxcapital = w;
        while(k > 0){
            while(minpq.size() != 0 && capital[minpq.peek()] <= maxcapital){
                int idx = minpq.remove();
                maxpq.add(idx);
            }
            
            if(maxpq.size() == 0) return (int)maxcapital;
            
            maxcapital += profits[maxpq.remove()];
            k--;
        }
        
        return (int)maxcapital;
    }



//====================================================================================================

// LC - 895. Maximum Frequency Stack

// I WAY -> {frequency(integer) Vs Stack<Integer>} ka DATA STRUCTURE BANA KE WE CAN SOLVE 
// bass ye concept yaad rakna ha baki code to easy ha, video => https://www.youtube.com/watch?v=KVg4Y0sI4Iw
// CONCEPT -> haam ek Arraylist(hashmap bhi le sakte the but since freq to 1 se hi suru hoti ha so we can take arraylist for simplification) lege jisme haar index ek particular frequency ko batayega ie.  => ArrayList<Stack<Integer>> arr
// and haar index(frequency) pe haam ek stack<Integer> store kar lege
// -> AND ek frequency ka array bhi store kar lege (yaha array freq[] nai le sakte as values 1e9 tak ha so we can take hashmap which stores values )
class FreqStack {
    HashMap<Integer, Integer> hm = new HashMap<>();  // stores frequency of a value
    ArrayList<Stack<Integer>> arr = new ArrayList<>();
    public FreqStack() {
        arr.add(new Stack<>());  // for index 0
        arr.add(new Stack<>());  // for index 1
    }
    
    public void push(int val) {
        if(hm.containsKey(val)){
            int freq = hm.get(val);
            freq++;  // increased vali freq ke stack me dalna ha
            
            if(freq == arr.size()) arr.add(freq, new Stack<>()); 
            arr.get(freq).push(val);
            
            hm.put(val, hm.get(val)+1);  // and at last increase the frequency of added ele in frequency hm
        }else{
            arr.get(1).push(val);
            hm.put(val, 1);  // newly added ki ele ki value ki bhi freq 1 daal do 
        }
    }
    
    public int pop() {
        int freq = arr.size()-1;
        Stack<Integer> st = arr.get(freq);
        
        if(st.size() == 1){
            int temp = st.pop();
            arr.remove(freq);
            
            hm.put(temp, hm.get(temp)-1);  // decrease the freq also
            return temp;
        }else{
            int rem = st.pop();
            hm.put(rem, hm.get(rem)-1);
            return rem;
        }
    }


//====================================================================================================


  // LC - 407. Trapping Rain Water II
    
    // NOTE :   vo 1D vala solution yaha work ni karega
    // reason why 1D vala solution does not work here
    // wrong approach - agar vohi 1D rainwater trapping vala solution iss 2D rainwater trapping water vale me use karege to ans galat ayega as because
    // 1D me hamare pass only do raste the water ko flow hone ke (left or right)
    // but 2D me hamare pass kai sare raste ha flow hone ke eg
    //   0 0 0 1 0 0 0
    //   1 0 0 0 0 0 1      for(1,3) ans will come to be 1 (iff we use that 1D logic)
    //   0 0 0 1 0 0 0      // but the actual ans is 0 everywhere
    // hence we cannot use that logic here


    // CONCEPT : haam ek priorityQueue le lege and boundry ko kaam karte jayege and water ko add karte jayege
    // ek visited array bhi le lege so that dubara uspe na jaye
    // see this video for concept (simple ha pura dekna) imp ->  https://www.youtube.com/watch?v=fywyCy6Fyoo     (acha bataya ha inhone isme)
    public int trapRainWater(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // 2d ka 1d karke store karege pq me
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{ // min pq
            return arr[a/m][a%m] - arr[b/m][b%m];  // this-other => min pq
        });
        int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        
        boolean[][] vis = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 || i == n-1 || j == 0 || j == m-1){
                    vis[i][j] = true;
                    pq.add(i*m+j);
                }
            }
        }
        
        int totalwater = 0;
        
        while(pq.size() != 0){
            int rem = pq.remove();
            int r = rem/m, c = rem%m;
            
            for(int d = 0; d < 4; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x >= 0 && y >= 0 && x < n && y < m && vis[x][y] == false){
                    vis[x][y] = true;
                    if(arr[x][y] < arr[r][c]){
                        totalwater += (arr[r][c] - arr[x][y]); 
                        arr[x][y] = arr[r][c];  //V.Imp->water ki height same bana do abb
                    }                           // and then pq me add kar do ie now usko 
                                                // kaam assign kar do ki jao ans bana lo
                    pq.add(x*m+y);
                }
            }
        }
        
        return totalwater;
    }
       
       
       
       