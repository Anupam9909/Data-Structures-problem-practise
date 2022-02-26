// LC-303. Range Sum Query - Immutable

// 	QUESTION
class NumArray {
    public NumArray(int[] nums) {
        
    }
    
    public int sumRange(int left, int right) {
        
    }
}

//   ANSWER
// using segment tree
class NumArray {
    int[] tree;
    int[] arr;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        arr = nums;
        tree = new int[4*n+1];
        // first we have to build the sg
        build(arr, 0, 0, n-1);
    }
    
    // use simple recurssion
    // left ko bola ja apna tree bhar ke aja
    // right ko bola ja apna tree bhar ke aja
    // then apne position ka i.e tree[idx] ka fill kar diya ans
    public void build(int[] arr, int idx, int si, int ei){
        if(si == ei){
            tree[idx] = arr[si];     // note: arr[si] hoga na ki arr[idx]
            return;
        }
        
        int mid = (si+ei)/2;
        build(arr, 2*idx + 1, si, mid);
        build(arr, 2*idx + 2, mid+1, ei);
        
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    
    // query(arr, idx, si, ei, l, r)
    // O(log(n)) solution 
    public int query(int idx, int si, int ei, int l, int r){
        if(l <= si && ei <= r) return tree[idx];
        if(ei < l || r < si) return 0;
        
        int mid = (si+ei)/2;
        int a = query(2*idx+1, si, mid, l, r);
        int b = query(2*idx+2, mid+1, ei, l, r);
        return (a+b);
    }
    
    public int sumRange(int left, int right) {
        int ans = query(0, 0, n-1, left, right);  //  query me (arr) pass karne ki jarurat nahi ha
    }
}



//==================================================================================

// LC - 307. Range Sum Query - Mutable

// using segment tree
class NumArray {
    int[] tree;
    int[] arr;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        arr = nums;
        tree = new int[4*n+1];
        // first we have to build the sg
        build(arr, 0, 0, n-1);
    }
    
    
    public void build(int[] arr,int idx, int si, int ei){
        if(si == ei){
            tree[idx] = arr[si];    // note: arr[si] hoga na ki arr[idx]
            return;
        }
            
        int mid = (si+ei)/2;
        build(arr, 2*idx+1, si, mid);
        build(arr, 2*idx+2, mid+1, ei);
        
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    
    // query(arr, idx, si, ei, l, r)
    // O(log(n)) solution 
    public int query(int[] arr, int idx, int si, int ei, int l, int r){
        if(l <= si && ei <= r) return tree[idx];
        if(ei < l || r < si) return 0;
        
        int mid = (si+ei)/2;
        int a = query(arr, 2*idx+1, si, mid, l, r);
        int b = query(arr, 2*idx+2, mid+1, ei, l, r);
        
        return a+b;
    }
    
    public int sumRange(int left, int right) {
        return query(arr, 0, 0, n-1, left, right);
    }
    
    public void update(int index, int val) {
        pointUpdate(0, 0, n-1, index, val);  // pu : point update
    }
    
    
    public void pointUpdate(int idx, int si, int ei, int x, int val){
        if(si == ei){
            tree[idx] = val;
            return;
        }
        
        int mid = (si+ei)/2;
        if(x <= mid) pointUpdate(2*idx+1, si, mid, x, val);
        else pointUpdate(2*idx+2, mid+1, ei, x, val);
        
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];    // vohi condition jo build karte hue ki thi same yahi kar do tree apne aap sahi hota chala jayega
    }
    
} 
    
