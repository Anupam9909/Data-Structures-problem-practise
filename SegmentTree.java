import java.util.*;

public class SegmentTree{
    static int[] tree;
    public static void build(int[] arr, int idx, int si, int ei){
        if(si == ei) {
            tree[idx] = arr[si];
            return;
        }

        int mid = (si+ei)/2;
        build(arr, 2*idx+1, si, mid);
        build(arr, 2*idx+2, mid+1, ei);

        tree[idx] = Math.max(tree[2*idx+1] , tree[2*idx+2]);
    }

	// NOTE : ye asli ka tree nahi hota
// so yaha haam range ki baat karte ha recursion sochte samaye
// eg for [0 to 9] array
// for 0-7 range 
// -> (0 to 3) tum apna ans le aao &  -> (4 to 7) tum apna ans le aao
// pura range complete ho gya i.e from 0 to 7
 
    public static int query(int idx, int si, int ei, int l, int r){   //  yaha arr pass karne ki jarurat nahi ha
        if(l <= si && ei <= r) return tree[idx];
        if(ei < l || r < si) return -(int)1e9;

        int mid = (si+ei)/2;
        int lans = query(2*idx+1, si, mid, l, r);
        int rans = query(2*idx+2, mid+1, ei, l, r);
        return Math.max(lans, rans);
    }

    // Point Update
    public void pointUpdate(int[] arr, int idx, int si, int ei, int x, int val){
        // hamesha x vala index ek leaf hoga in segment tree, so vaha change kar do
         if(si == ei){
             tree[idx] = val;  // update the value of the index
             arr[x] = val;  // array me bhi kar do kya pata age use ho aage question me
             return;
         }
         
         int mid = (si+ei)/2;
         if(x <= mid) pointUpdate(arr, 2*idx+1, si, mid, x, val);
         else pointUpdate(arr, 2*idx+2, mid+1, ei, x, val);
         
         tree[idx] = Math.max(tree[2*idx+1] , tree[2*idx+2]);    // jate vakt vohi condition likh do baws jo build karte hue ki thi same yahi kar do tree apne aap sahi hota chala jayega
     }
 
    
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter size = ");
        int n = scn.nextInt();

        System.out.println();

        int[] arr = new int[n];

        System.out.print("Enter Elements => ");
        for(int i = 0; i< n; i++) arr[i] = scn.nextInt();
        
        System.out.println();
        
        tree = new int[4*n+1];
        
        build(arr, 0, 0, n-1);
        System.out.print("Enter range i.e (l,r) => ");
        int l = scn.nextInt();
        int r = scn.nextInt();
        
        int ans = query(arr, 0, 0, n-1, l, r);
        System.out.println();
        System.out.print("ans = " + ans);
        System.out.println();
        
        for(int i = 0; i< tree.length; i++) System.out.print(tree[i] + " " );
        
        System.out.print("Enter the value = ");
        int x = scn.nextInt();
        System.out.println();
        
        System.out.print("Enter the value = ");
        int val = scn.nextInt();
        System.out.println();
        
        pointUpdate(arr, 0, 0, n-1, x, val);

        
    }
}













    













































// class SegmentTree{
//     int[] tree;

//     SegmentTree(int n){
//         tree = new int[n];
//     }

//     void build(int[] arr, int node, int start, int end){
//         if(start == end){
//             tree[node] = arr[start];
//         }
//         else{
//             int mid = (start + end)/2;
//             build(arr, 2*node + 1, start, mid);
//             build(arr, 2*node + 2, mid + 1, end);
//             tree[node] = tree[2*node + 1] + tree[2*node + 2];
//         }
//     }

//     void update(int[] arr, int node, int index, int val, int start, int end){
//         if(start == end){
//             tree[node] += val;
//             arr[start] += val;
//         }
//         else{
//             int mid = (start + end)/2;
//             if(start <= index && index <= mid){
//                 update(arr, 2*node + 1, index, val, start, mid);
//             }
//             else{
//                 update(arr, 2*node + 2, index, val, mid + 1, end);
//             }
//             tree[node] = tree[2*node + 1] + tree[2*node + 2];
//         }
//     }

//     int query(int node, int start, int end, int left, int right){
//         if(right < start || end < left){
//             return 0;
//         }
//         if(left <= start && end <= right){
//             return tree[node];
//         }
//         int mid = (start + end)/2;
//         int p1 = query(2*node + 1, start, mid, left, right);
//         int p2 = query(2*node + 2, mid + 1, end, left, right);
//         return p1 + p2;
//     }
// }

// public class Test{
//     public static void main(String[] args){
//         int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
//         int n = arr.length;
//         int height = (int)(Math.log(n)/Math.log(2)) + 1;
//         int tree_nodes = (int) Math.pow(2, height + 1);
//         SegmentTree ob = new SegmentTree(tree_nodes);
//         ob.build(arr, 0, 0, n - 1);
//         for(int i = 0; i < tree_nodes; i++){
//             System.out.print(ob.tree[i] + " ");
//         }
//         System.out.println();
//         System.out.println(ob.query(0, 0, n - 1, 0, 5));
//     }
// }