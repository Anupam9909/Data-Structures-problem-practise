// ye sab diameter vale question jese ha 


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

































    //LC: 437 Path Sum III   (jo sochte ha solution vo nahi hota)
    // NOTE : yaha only parent to child bola ha path na ki koi bhi path
    // so this below path sum question is different from the above diameter concept

    // ye question vo psum concept pe based ha jo haam array me karte the
    // i.e ye vala
    // LC 560. Subarray Sum Equals K
    // public int subarraySumEqualsk(int[] arr, int k) {
    //     HashMap<Integer, Integer> hm = new HashMap<>();
    //     hm.put(0, 1);   // {sum , frequency}
    //     int psum = 0;
    //     int ans = 0;
        
    //     for(int i = 0 ; i < arr.length; i++){
    //         psum += arr[i];
            
    //         if(hm.containsKey(psum-k)){
    //             int count = hm.get(psum-k);
    //             ans = ans + count;
    //         }
            
    //         hm.put(psum, hm.getOrDefault(psum,0)+1);
    //     }
        
    //     return ans;
    // } 


    // SAME VAHI CONCEPT LAGAYEGE BASS YAHA ARRAY HAMARE BHOT SARE HA
    //               10
    //             /    \
    //            5      -3
    //          /   \      \
    //         3     2      11
    //       /   \    \
    //      4    -2    1
    //             \
    //              6
    //
    //     means yaha hamare pass bhot sare array hoge
    //     array1  :  10, 5, 3, 4
    //     array2  :  10, 5, 3, -2, 6
    //     array3  :  10, 5, 2, 1
    //     array4  :  10, -3, 11   
    //     ab simply jesa array me psum vala code kiya tha vesa hi kar do

    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0,1);
        int k = targetSum;
        int psum = 0;
        solve(root,hm, k, 0);
        return ans;
    }
    
    int ans = 0;
    public void solve(TreeNode root, HashMap<Integer, Integer> hm, int k, int psum){
        if(root == null) return ;
        
        psum += root.val;
        if(hm.containsKey(psum-k)){
            int count = hm.get(psum-k);
            ans = ans + count;
        }
        
        hm.put(psum, hm.getOrDefault(psum, 0)+1);
        
        solve(root.left, hm, k, psum);
        solve(root.right, hm , k , psum);
        
        hm.put(psum, hm.getOrDefault(psum,1)-1);
        // psum -= root.val;    // ye line likho ya mat likho koi farak ni padega because psum to haar rec fuction call stack me alag alag hoge  
    }

    // NOW Follow up question

    // find all count of path sum which has sum divisible by K
    // to haamne jesa array me kiya tha vesa hi karege similar to this concept i.e jab bhi bass -ve remainder aye to +k karke add karna ha hashmap me 




























