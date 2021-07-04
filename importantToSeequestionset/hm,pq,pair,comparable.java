// LC-347.    TOP K-FREQUENT ELEMENTS

public class pair implements Comparable<pair>{
    int val;
    int freq;
    pair(int v, int f){
        this.val = v;
        this.freq = f;
    }
    
    public int compareTo(pair p){
        return this.freq - p.freq;  // (this - other) -> gives default behaviour [in this pq case it is MIN. PQ]
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