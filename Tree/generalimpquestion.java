  
  //LC- 968. Binary Tree Cameras 

    // 0 : not required
    // 1 : ha mere pass 
    //-1 : nahi ha mere pass
    
    static int count = 0;
    public int minCameraCover(TreeNode root) {
        if(root.left == null && root.right == null) return 1;
        count = 0;  // ye initialise karna jaruri ha nahi to kabhi kabhi SUBMIT(run to ho jata ha par submit ni hota) karne me dikkat ati ha  
        int recans = solve(root);
        if(recans == -1) count++;
        
        return count;
    }
    
     public int solve(TreeNode root){
        if(root == null) return 0;
        
        int leftans = solve(root.left);
        int rightans = solve(root.right);
    
        if(leftans == 0 && rightans == 0) return -1; 
        else if(leftans == 1 && rightans == 1) return 0;
        else if((leftans == 0 && rightans == 1) || (leftans == 1 && rightans == 0)) return 0;
        else if(leftans == -1 && rightans == -1){
            count++;
            return 1;
        }
        else if((leftans == 0 && rightans == -1) || (leftans == -1 && rightans == 0)){
            count++;
            return 1;
        }
        else if((leftans == 1 && rightans == -1) || (leftans == -1 && rightans == 1)){
            count++;
            return 1;
        }
        
        return -(int)1e9; // kuch bhi bej do yaha kabhi ayega hi ni recursion
    }
    
    // or jab pura ho jaye then we can use this
    // else kar do baki ko to
    
    // public int solve(TreeNode root){
    //     if(root == null) return 0;
        
    //     int leftans = solve(root.left);
    //     int rightans = solve(root.right);
    
    //     if(leftans == 0 && rightans == 0) return -1; 
    //     else if(leftans == 1 && rightans == 1) return 0;
    //     else if(leftans == 0 && rightans == 1) return 0;
    //     else if(leftans == 1 && rightans == 0) return 0;
    //     else {
    //         count++;
    //         return 1;
    //     }
    // }

    //================================================================================================= 

    // LC-337. House Robber III   (diameter jesa ha ye) rec concept : {maxvalueifRobbed, maxvalueifNotRobbed}
        
    // RECURSION: 
    //       {maxvalueif(a)wasRobbed, maxvalueif(a)NotRobbed}  (a)
    //                                                         / \
    //                                                        /   \
    //    {maxvalueif(b)wasRobbed, maxvalueif(b)NotRobbed} (b)    (c)   {maxvalueif(c)wasRobbed, maxvalueif(c)NotRobbed} 


    // NOTE: {maxvalueifRobbed, maxvalueifNotRobbed}  note: dono me se koi bhi value badi ho sakti ha 
    
    public int rob(TreeNode root) {
        if(root == null) return 0;
        
        int[] ans = solve(root);
        int finalans = Math.max(ans[0], ans[1]);
        return finalans;
    }
    
    public int[] solve(TreeNode root){
        if(root == null) return new int[]{0,0};
        
        int[] lans = solve(root.left);
        int[] rans = solve(root.right);
        
        int[] myans = new int[2];
        myans[0] = lans[1]+rans[1]+root.val;
        
        myans[1] = Math.max(lans[0], lans[1]) + Math.max(rans[0], rans[1]);
        
        return myans;
    }


    //LC-198 House Robber
    // now ye recursion, dp ka question ho jayega (but concept to same ha yaha bhi)
    // Array me ha ye so ek call laga do bass 
    public int rob(int[] arr) {
        if(arr.length == 0) return 0;
        
        int [] ans = solve(arr, 0);
        return Math.max(ans[0], ans[1]);
    }
    
    public int[] solve(int[] arr, int idx){
        if(idx == arr.length) return new int[]{0,0};
        
        int[] recans = solve(arr, idx + 1);
        
        int[] myans = new int[2];
        
        myans[0] = arr[idx] + recans[1];
        myans[1] = Math.max(recans[0], recans[1]);
        
        return myans;
    }


    // LC-213. House Robber II
    // same as house robberI 
    // Since House[1] and House[n] are adjacent, they cannot be robbed together. 
    // Therefore, the problem becomes to rob either ( House[1]-House[n-1] ) or ( House[2]-House[n] )
    // dono me se jo maximum ayega vo bej dega ans.

    public int rob(int[] arr) {
        if(arr.length == 1) return arr[0];
        int finalans = 0;
        int n = arr.length;
        
        int[] ans1 = solve(arr, 0, n-2);
        finalans = Math.max(ans1[0], ans1[1]);
        
        int[] ans2 = solve(arr, 1, n-1);
        finalans = Math.max(finalans, Math.max(ans2[0], ans2[1]));
        
        return finalans; 
    }
    
    
    public int[] solve(int[] arr, int si, int ei){
        if(si == ei) return new int[]{arr[si], 0};
        
        int[] recans = solve(arr, si + 1, ei);
        int[] myans = new int[2];
        
        myans[0] = arr[si] + recans[1];
        myans[1] = Math.max(recans[0], recans[1]);
        
        return myans;
    }

    
    //=================================================================================================

    // LC-1372 Longest ZigZag Path in a Binary Tree

    // yaha haam do entity lege int[] me 
    // {longestZZpathleftway, longestZZpathrightway}
    // and ek global variable ans le lege to ensure kabhi bhi kissi level pe ans max ho to ans update ho jaye
    static int ans = 0;
    public int longestZigZag(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;
        
        ans = 0; // ye karna jaruri ha (nahi to submit me dikkat ati ha)
        int[] val = solve(root);
        ans = Math.max(ans, Math.max(val[0], val[1]));
        
        return ans-1;
    }
    
    public int[] solve(TreeNode root){
        if(root == null) return new int[]{0,0};
        
        int[] lans = solve(root.left);
        int[] rans = solve(root.right);
        
        ans = Math.max(ans, Math.max(lans[0],lans[1]));
        ans = Math.max(ans, Math.max(rans[0], rans[1]));
        
        int[] myans = new int[2];
        myans[0] = lans[1]+1;
        myans[1] = rans[0]+1;
        
        return myans;
    }


    // WE CAN DO ANOTHER THING BY TAKING 3 size ka ARRAY

    // global variable ki jaga 3rd variable rakh do array me
    // {longestZZpathleftway, longestZZpathrightway, maxpathans}
    public int longestZigZag(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;
        
        int[] ans = solve(root);
        
        return Math.max(Math.max(ans[0], ans[1]), ans[2]);
        
    }
    
    public int[] solve(TreeNode root){
        if(root == null) return new int[]{-1,-1,0};
        
        int[] lans = solve(root.left);
        int[] rans = solve(root.right);
        
        int[] myans = new int[3];
        myans[2] = Math.max(Math.max(lans[0], lans[1]), Math.max(rans[0], rans[1]));
        myans[2] = Math.max(myans[2], Math.max(lans[2], rans[2]));
        
        myans[0] = lans[1] + 1;
        myans[1] = rans[0] + 1;
        
        return myans;
    }
    
    //=================================================================================================
    

























    
    // FIND MEDIAN IN A BST
    // T : O(n)
    // S : O(n)
    // simply inorder me traverse karo and find the median (pehle size of tree nikal lo(n) then find the median)
    static int count = 0;
    static int prev = -(int)1e9;
    static double ans = 0;
    public static double findMedian(Node root)
    {
        count = 0;
        int n = findsize(root);
        int mid = n%2 == 0 ? (n/2) + 1 : (n+1)/2;
        
        solve(root, mid , n);
        
        return ans;
    }

    public static int findsize(Node root){
        return root == null ? 0 : findsize(root.left) + findsize(root.right) + 1;
    }
    
    
    public static void solve(Node root, int mid , int n){
        if(root == null) return ;
        
        solve(root.left, mid , n);
        
        count++;
        if(count == mid){  
            if(n%2 == 0)  ans = ((prev + root.data)*1.0)/(2*1.0);
            else    ans = root.data;
        }
        prev = root.data;
        
        solve(root.right, mid, n);
    }