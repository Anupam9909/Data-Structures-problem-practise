  
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