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
