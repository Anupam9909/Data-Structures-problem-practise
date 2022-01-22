
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




    // LC- 128 longest Consecutive sequence

    // sabse best solution ha ki sare elements ko Hashset me daalo
    // then for each nums ele do this thing
    // x element ko x-- karte karte le jao and calculate (leftcount) i.e -> hs.contains(x) and remove those x-- element
    // similarly, x element ko x++ karte karte le jao and calculate (rightcount) i.e -> hs.contains(x++) and remove those x++ element
    
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
            
            if(ans < (lcount + rcount+1)) ans = (lcount + rcount + 1);
            hs.remove(ch);
        }
        
        return ans;
    }

    


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


}