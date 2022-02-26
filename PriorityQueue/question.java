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