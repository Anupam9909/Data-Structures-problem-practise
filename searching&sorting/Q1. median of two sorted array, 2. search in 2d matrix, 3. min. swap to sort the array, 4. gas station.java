
LC-Median of Two Sorted Arrays
// DO CHIJ HA ISME 
// 1. hame median nikalna ha so we will make cut in the first array and in the second array
//    aur cut hamesha esa hoga ki dono array ka lefthalf size == dono array ka righthalf size
// 2. haam binary search lagate ha keval arr1 me (since arr2 me to pata hi chal jayega ki kaha ha)
//    and jab tak chalayege jab tak   (l1 <= r2 && l2 <= r1) 
//                       l1 |  r1
//                       l2 |  r2           where | -> is the cut  
// and if (l1 > r1)  -> (ei = mid-1) kar do
// and else (si = mid+1)
// NOTE : haam sirf chote vale array pe hi binary search lagagyege (esa nai kiya to wrong ans ayega) 
// see the code before exam

    public double findMedianSortedArrays(int[] arr1, int[] arr2){
        int n = arr1.length, m = arr2.length;
        if(n > m) return findMedianSortedArrays(arr2, arr1);  // hamesha haam minimum vale array ke upar hi traverse karege
        
        int leftHalf = (n+m+1)/2;   // ye dono odd even ke liye chal jata ha
        int si = 0, ei = n;
        
        while(si <= ei){
            int cut1 = (si+ei)/2;    // cut1 : hamara yaha mid ka kaam kar raha ha
            
            int cut2 = leftHalf - cut1;
            int l1 = (cut1 == 0) ? -(int)1e9 : arr1[cut1-1];
            int l2 = (cut2 == 0) ? -(int)1e9 : arr2[cut2-1];
            
            int r1 = (cut1 == n) ? (int)1e9 : arr1[cut1];
            int r2 = (cut2 == m) ? (int)1e9 : arr2[cut2];
            
            if(l1 <= r2 && l2 <= r1){
                if((n+m)%2 == 0){
                    return (double)(Math.max(l1, l2) + Math.min(r1, r2))/2;
                }else{
                    return (double)Math.max(l1, l2);
                }
            }
            else if(l1 > r2){
                ei = cut1-1;
            }
            else{
                si = cut1+1;
            }
        } 
        return 0;
    }


//=======================================================================================

// SEARCH IN A 2D MATRIX
    // top right se suru karo and dfs ke jese chalte jao
    
    // We start search the matrix from top right corner, initialize the current position to top right corner, if the target is greater than the value in current position, then the target can not be in entire row of current position because the row is sorted, if the target is less than the value in current position, then the target can not in the entire column because the column is sorted too. We can rule out one row or one column each time, so the time complexity is O(m+n).
 
    
    
    public boolean searchMatrix(int[][] arr, int data){
        int n = arr.length, m = arr[0].length;
        
        int r = 0, c = m-1;
        
        while(r >= 0 && r < n && c >= 0 && c < m){
            int val = arr[r][c];
            
            if(val == data) return true;
            else if(val < data)   r++;
            else   c--;
        }
        
        return false;
    }
    
    // another intution  -> tree ki bst ki tarah ha ye 
    // see this 
    // https://assets.leetcode.com/static_assets/discuss/uploads/files/1488858512318-monosnap-2017-03-06-22-48-17.jpg 
    
    
//=============================================================================================

// GFG - MINIMUM SWAP TO SORT THE ARRAY 
// https://practice.geeksforgeeks.org/problems/minimum-swaps/1#

  // conecpt ha ye raat lo:
    // just simply-> find the total number of circular graph in the array
    // haar ek circular graph me jitne element hoge usse -1 karke count me add karte raho
    // and get the ans visited leke kaam karna ha
    public class pair{
        int val;
        int idx;
        pair(int v, int i){this.val = v; this.idx = i;}
    }
    
    //Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int nums[])
    {
        // Code here
        int n = nums.length;
        pair[] arr = new pair[n];
        for(int i = 0; i < n; i++){
            arr[i] = new pair(nums[i], i);
        }
        
        Arrays.sort(arr, (a,b)->{
            return a.val-b.val;
        });
        
        boolean[] vis = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++){
            pair p = arr[i];
            if(!vis[i]){
                int len = solve(arr, i, vis);
                count += (len-1);
            }
        }
        
        return count;
    }
    
    public int solve(pair[] arr, int src, boolean[] vis){
        
        vis[src] = true;
        pair p = arr[src];
        int count = 0;
        if(!vis[p.idx])  count += solve(arr, p.idx, vis);
        
        return count+1;
    }



//=============================================================================================

    
    
// LC- 134. Gas Station

    // O(n) solution -> isko yaad kar lo (concept not understood)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int extraGas = 0;
        int sp = 0;
        int deficit = 0;
        
        for(int i = 0; i < gas.length; i++){
            extraGas += gas[i] - cost[i];
            if(extraGas < 0){
                deficit += extraGas;
                extraGas = 0;
                sp = i+1;
            }
        }
        return (sp == n || extraGas + deficit < 0) ? -1 : sp; 
    }
    
    //----------------------------
    
//     // brute force O(n^2) solution 
//     public int canCompleteCircuit(int[] gas, int[] cost) {
//         int n = gas.length;
        
//         for(int i = 0; i < n; i++){
//             int tank = 0;
//             boolean check = true;
//             int num = 0;
//             for(int j = i; j < 2*n; j++){
//                 if(num == n) break;
//                 num++;
//                 tank += gas[j%n];
                
//                 if(tank < cost[j%n]){  
//                     check = false; 
//                     break;
//                 }else{
//                     tank -= cost[j%n];
//                 }
//             }
            
//             if(check){
//                return i; 
//             }
//         }
        
//         return -1;
//     }


//=============================================================================================
