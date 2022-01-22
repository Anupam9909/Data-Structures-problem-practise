// 215. Kth Largest Element in an Array

    public int findKthLargest(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int x : arr){
            pq.add(x);
            if(pq.size() > k) pq.remove();
        }
        
        return pq.remove();
    }

// 2 line solution 
   public int findKthLargest(int[] arr, int k){
        Arrays.sort(arr);
        return arr[arr.length-k];
    }

// NOTE : 
// jab bhi priority Queue ka question aye to ek baar dek lo ki just array, arraylist ko 
// sort karne se to kahi answer ni ban raha agar ban raha ha to simply return ans kar do
// bass ye dyan rakhna ki sort karne se complexity (NlogN) hogi na ki (NlogK)

//================================================================================

// 703. Kth Largest Element in a Stream

// NOTE : yaha k fix rahega change ni horaha ha kar add ke sath so simple ha 
class KthLargest {
    int k = 0;
    PriorityQueue<Integer> pq;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for(int x : nums){
            pq.add(x);  
            if(pq.size() > this.k)  pq.remove();
        }  
    }
    
    public int add(int val){
        pq.add(val);
        if(pq.size() > this.k)  pq.remove();
        
        return pq.peek();
    }
}



//================================================================================

// 973. K Closest Points to Origin

public int dist(int a, int b){
        return ((a*a)+(b*b));
    } 
    
    
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> {
            return dist(p2[0], p2[1]) - dist(p1[0], p1[1]) ;
        });

        for (int[] p : points) {
            pq.add(p);
            if (pq.size() > K) pq.remove();
        }

        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.remove();
        }
        return res;
    }

 // 2 line solution pq me sort vala trick vala chal jayega yaha bhi
// and question me bhi NlogN allowed ha so yahi karegi to save time
//     public int[][] kClosest(int[][] arr, int k){
//         Arrays.sort(arr, (a,b)->{
//            return dist(a[0], a[1]) - dist(b[0], b[1]); 
//         });
//         // Arrays.copyOfRange(arr, i, j);  -> returns ele from (i to j-1)
//         int[][] ans = Arrays.copyOfRange(arr, 0, k);
        
//         return ans;
//     }

//============================================================================    

// LC- 658. Find K Closest Elements

 public List<Integer> findClosestElements(int[] arr, int k, int x){ 
        Queue<Integer> pq = new PriorityQueue<>((a,b)->{
            int d1 = Math.abs(arr[a]-x),  d2 = Math.abs(arr[b]-x);
            
            if(d1 != d2) return d2 - d1;    // other - this  -> i.e [max pq]-> means max vala upar rahega    (aha diff ke basis par lagaya ha ) 
            else    return arr[b]-arr[a];  // other - this   -> i.e [max pq]-> means max vala upar rahega  (aha array ki value par lagaya ha) - again since ye (other-this) ha so ye opposite of default behavior hoga and i.e maximum pq (because default is min pq) so, since ye maximum pq order me values dalegi so maximum vala pq me top par ayega then phir choti values niche ayegi... ans soon.       
        });
        
        int n = arr.length;
        for(int i = 0; i < n; i++){
            pq.add(i);
            if(pq.size() > k) pq.remove();
        }
        
        List<Integer> ans = new ArrayList<>();
        while(pq.size() != 0) ans.add(arr[pq.remove()]);
        
        Collections.sort(ans);
        
        return ans;
    }
    