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


//==========================================================================
// bass question me implementation thodi si tough ha baki question aasan ha BFS hi ha 
// BFS LAGAO SIMPLE EK QUEUE LE KE
// BASS JESE HI QUEUE ME ELEMENTS REMOVE KARO TO SIRF VO 
// ELEMENTS KO PQ ME DAAL DO JINKI VALUE 1 NAHI HA AND THE VALUE IS IN THE RANGE(GIVEN)
// lc-2146. K Highest Ranked Items Within a Price Range

    public class pair{
        int i, j;
        pair(int i , int j){
            this.i = i;
            this.j = j;
        }
    }
    
    public class pp implements Comparable<pp>{
        int row;
        int col;
        int price;
        int level;
        pp(int r, int c, int p, int l){
            this.row = r;
            this.col = c;
            this.price = p;
            this.level = l;
        }
        
        public int compareTo(pp other){
            if(this.level == other.level){
                if(this.price == other.price){
                    if(this.row == other.row){
                        return this.col-other.col;
                    }else{
                        return this.row-other.row;
                    }
                }else{
                    return this.price-other.price;
                }
            }else{
                return this.level-other.level;
            }
        }
    }
        
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int n = grid.length, m = grid[0].length;
        
        PriorityQueue<pp> pq = new PriorityQueue<>(Collections.reverseOrder());  // max pq  banani ha
        int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        
        LinkedList<pair> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        
        int level = 0;
        q.add(new pair(start[0], start[1]));
        vis[start[0]][start[1]] = true;
        
        while(q.size() != 0){
            int s = q.size();
            while(s-- > 0){
                pair rp = q.removeFirst();
                int r = rp.i, c = rp.j;
                int val = grid[r][c];
                
                if(val != 1 && val >= pricing[0] && val <= pricing[1]){ 
                    pq.add(new pp(r,c,val,level));
                    // System.out.println(r + " " + c);
                    if(pq.size() > k){
                        pq.remove();
                    }
                }
                
                for(int d = 0; d < 4; d++){
                    int x = r+dir[d][0];
                    int y = c+dir[d][1];
                    if(x >= 0 && x < n && y >= 0 && y < m && !vis[x][y]  && val != 0){
                        vis[x][y] = true;
                        // System.out.println(x + " " + y);
                        q.add(new pair(x,y));
                    }
                }
                
            }
            level++;
        }
        // System.out.println(pq.size());
        
        // making ans
        List<List<Integer>> ans = new ArrayList<>();
        while(pq.size() != 0){
            pp r = pq.remove();
            // System.out.println(r.row + " " + r.col + " " + r.price + " " + r.level);
            List<Integer> t = new ArrayList<>();
            t.add(r.row);
            t.add(r.col);
            
            ans.add(t);
        }
        
        // ans.reverse();
        Collections.reverse(ans);
        return ans;
    }   


//==============================================================================

// 347. Top K Frequent Elements
// doo tarike se kar sakte ha i.e (class me sort[using implements comparable]) se bhi
// and (pq me sort[using lambda function]) karke bhi

    // I WAY
    // simple class pair + pq me sort kar diya definition likte vakt
    public class pair{
        int ele;
        int freq;
        pair(int e, int f){this.ele = e; this.freq = f;}
    }
    
    public int[] topKFrequent(int[] arr, int k){
        int n = arr.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int x : arr) hm.put(x, hm.getOrDefault(x, 0)+1);
        
        
        //{ele, freq}
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.freq - b.freq;
        });
        
        for(Integer p : hm.keySet()){
            pair np = new pair(p,hm.get(p));
            pq.add(np);
            if(pq.size() > k) pq.remove();
        }
        
        // making ans
        int[] ans = new int[pq.size()];
        int t = 0;
        while(pq.size() != 0){
            ans[t++] = pq.remove().ele;
        }
        return ans;
    }
    
    
    // II WAY
    // class pair me implements comparable use karke sort kara +  simple pq use kiya
//     public class pair implements Comparable<pair>{
//         int val;
//         int freq;
//         pair(int v, int f){
//             val = v;
//             freq = f;
//         }
        
//         public int compareTo(pair other){
//             return  this.freq-other.freq;  // this - other => default behaviour i.e [min pq]
//         }
//     }
    
//     public int[] topKFrequent(int[] arr, int k){ 
//       PriorityQueue<pair> pq = new PriorityQueue<>();
//       HashMap<Integer, Integer> hm = new HashMap<>();
        
//       for(int x : arr)  hm.put(x, hm.getOrDefault(x,0)+1);  // ek choti si line me freq table banao
      
//       for(int x : hm.keySet()){             // pq for top k elements
//           int f = hm.get(x);
//           pq.add(new pair(x,f));
//           if(pq.size() > k) pq.remove();
//       }
        
//       int[] ans = new int[k];
//       int i = 0;
//       while(i < k) ans[i++] = pq.remove().val;  // ek choti si line me ans add karo
        
//       return ans;   
        
//     }
    
//=====================================================================================================================


// LC - 295. Find Median from Data Stream              //  bhot simple ha logic easy ha karna bhi
// since, haame median nikalna ha kaam time me so
// haam do PQ le lete ha,
// pehla left PQ jo ki MAX PQ hoga taki left half ke number hamesha sorted order me rahe in increasing order
// and dusra right PQ jo MIN PQ hoga taki right half ke number hamesha sorted order me rahe in increasing order 

//   1.2.3.(MAX PQ).4.5.6.  <  [median]  <  7.8.9.(MIN PQ).10.11.12     
// step1 : add() me dono pq ke peek() element dek ke insert kar do
//         and balance karna imp ha dono pq ko
// step2 : findmedian() function me simple agar size dono pq ka equal ha to return avg of both peek ele of PQ's
//         and agar size unequal ha to return the peek of greater size pq vohi median hoga pakka 
class MedianFinder {
    PriorityQueue<Integer> maxpq;
    PriorityQueue<Integer> minpq;
    public MedianFinder() {
        maxpq = new PriorityQueue<>((a,b)->{  // max pq  -> which hold left half of the numbers
            return b-a;
        });   
        minpq = new PriorityQueue<>();    // min pq  -> which hold right half of the numbers
    }
    
    public void addNum(int num) {
        if(maxpq.size() == 0 || num <= maxpq.peek()){
            maxpq.add(num);
        }else{
            minpq.add(num);
        }
        
        // balance both the PQ  -> v.imp ha karna tabhi to haam kissi bhi samaye median nikal sakte ha O(1) me
        if(maxpq.size() > minpq.size()+1){
            int temp = maxpq.remove();
            minpq.add(temp);
        }
        else if(maxpq.size()+1 < minpq.size()){
            int temp = minpq.remove();
            maxpq.add(temp);
        }
    }
    
    public double findMedian() 
        if(maxpq.size() == minpq.size()){  // even number of elements present ->so, avg of two terms lena hoga
            double ans = (double)(maxpq.peek() + minpq.peek())/2;
            return ans;
        }
        else{  // odd number of element present -> so jisme bhi jada element hoga means vohi median hoga pakka
            if(maxpq.size() > minpq.size()){
                return maxpq.peek();
            }else{
                return minpq.peek();
            }
        }
    }
}



// FOLLOW UP QUESTION : 
// Q1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

// ANSWER 1. We can maintain an integer array of length 100 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.
// Time and space complexity would be O(100) = O(1).


// Q2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
// ANSWER 2.In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].



//=====================================================================================================================

