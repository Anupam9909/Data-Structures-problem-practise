//  LC-124. Binary Tree Maximum Path Sum

// {maxpathsumtillNow, overallpathsumAns}

public int maxPathSum(TreeNode root) {
    if(root.left == null && root.right == null) return root.val;
    int[] ans = solve(root);
    
    return ans[1];
}

public int[] solve(TreeNode root){
    if(root ==  null) return new int[]{-1,-1001};
    
    int[] lans = solve(root.left);
    int[] rans = solve(root.right);
    
    int lans_ = (lans[0] < 0 ? 0 : lans[0]);
    int rans_ = (rans[0] < 0 ? 0 : rans[0]);
    
    int[] myans = new int[2];
    myans[0] = Math.max(lans_ , rans_) + root.val;
    
    myans[1] = Math.max(Math.max(lans[1], rans[1]), (lans_ + rans_ + root.val));
    
    return myans;
    
}


