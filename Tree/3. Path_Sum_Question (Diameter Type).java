// BASIC QUESTION JISS CONCEPT KE UPAR SARE HARD QUESTION BANEGE ie. path sum vale
// LC-543. Diameter of Binary Tree

   // long way O(n2) time
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return -1;
            
        int leftans = diameterOfBinaryTree(root.left);
        int rightans = diameterOfBinaryTree(root.right);
        
        int myans = height(root.left) + height(root.right) + 2;
        
        int dia = Math.max(Math.max(myans, leftans), rightans);
        return dia;
        
    }
    
    public int height(TreeNode root){
        return root == null? -1 : Math.max(height(root.left), height(root.right))+1;
    }

//-----------------------------------------------------

// LC-543. Diameter of Binary Tree
    // optimized way ie of (PAIR CLASS)
    // haam ek pair bana lege taki ek sath haam jo chije kar paye ie height bhi nikal le and maxdiameter bhi calculate kar paye haar level pe
    // {maxdiamtertillnow, maxheight}
    public int diameterOfBinaryTree(TreeNode root){
        int[] ans = diameter(root); // {dia, height}
        
        return ans[0];
    }

    public int[] diameter(TreeNode root){
        if(root == null) return new int[]{-1, -1};
        
        int[] leftans = diameter(root.left);
        int[] rightans = diameter(root.right);
        
        int mydia = leftans[1] + rightans[1] + 2;
        int myheight = Math.max(leftans[1], rightans[1]) +1;
        
        int[] ans = new int[2];
        
        ans[0] = Math.max(Math.max(leftans[0], rightans[0]), mydia);
        ans[1] = myheight;
        
        return ans;
    }

//============================================================================================================

    // QUESTION : GIVEN A TREE => tree array  = [-1, 0, 0, 0, 3] where index is node and their parent are arr[i] respectively
    // ANSWER:
    // NOTE : jab bhi esa input diya ho ki ek array ha tree ka then usko graph ki tarah hi build kar 
    // lo and vo bhi directed graph and then solve it considering it to be a tree
    public int solve(int[] arr) {
        // iss given array se graph(directed graph) bana lo
        int n = arr.length;
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int i = 1; i < n; i++){
            int u = arr[i], v = i;
            graph[u].add(v);
        }
        
        int src = 0;
        int[] ans = solve(graph, src);
        return Math.max(ans[0], ans[1]);
    }
    
    // {maxdiameter, maxheight}  
    public int[] solve(List<Integer>[] graph, int src){
        int[] myans = {0, 0};
        List<Integer> maxheight = new ArrayList<>();
        for(int v : graph[src]){
            int[] recans = solve(graph, v);
            maxheight.add(recans[1]);
            myans[0] = Math.max(myans[0], recans[0]);  // max diameter
            myans[1] = Math.max(myans[1], recans[1]+1);
        }
        Collections.sort(maxheight);
        if(maxheight.size() > 1){
            int n = maxheight.size();
            int currmaxdia = maxheight.get(n-1) + maxheight.get(n-2) + 2;
            myans[0] = Math.max(myans[0], currmaxdia);
        }
        return myans;
    }



//============================================================================================================

    // NOW, 
// ye niche ke sab Question diameter vale question jese ha 


//===========================================================================================================



//  LC-112. Path Sum

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return solve(root, 0, targetSum);
    }

    public boolean solve(TreeNode root, int sum, int target){
        if(root == null) return false;
        if(root.left == null && root.right == null){  // leaf
            if(sum+root.val == target) return true;
            else return false;
        }
        boolean myans = false;
        myans = myans || solve(root.left, sum + root.val, target);
        myans = myans || solve(root.right, sum + root.val, target);
        
        return myans;
    }


//===========================================================================================================



//  LC-113. Path Sum II

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(root, 0, targetSum, new ArrayList<>(), ans);
        return ans;
    }

    public void solve(TreeNode root, int sum , int target, List<Integer> psf, List<List<Integer>> ans){
        if(root == null) return;
        
        if(root.left == null && root.right == null){  // leaf condition
            if(sum+root.val == target){
                List<Integer> bsans = new ArrayList<>(psf);
                bsans.add(root.val);
                ans.add(bsans);
            }
            return;
        }
        
        psf.add(root.val);
        solve(root.left, sum + root.val, target, psf, ans);
        
        solve(root.right, sum + root.val, target, psf, ans);
        psf.remove(psf.size()-1);
    }



//===========================================================================================================



//  IMPORTANT QUESTION -> Maximum Path Sum In Between Two leaves of Binary Tree
// for submit -> https://practice.geeksforgeeks.org/problems/maximum-path-sum/1/#   -> yaha ek extra condition lagani padegi see in gfg code submittion (jo ki question gfg site ke question ke hisab se galat ha but vohi karke submit hota ha kya kare)
// concept : concept to same diameter vale logic ka hi ha were

// pair : {maxAnsTillNow, maxSumFromNodeToLeaf}    
// NOTE : jo imp ha iss question me is : haame alag alag cases bana ke hi solve karna padega and 
//        then vo diameter ka concept lagega alag alag need ke hsab se ki rec call lagani bhi ha ki ni
//
//      CASE-1 (leaf)         CASE-2          CASE-3            CASE-4 (yaha to haam vo sara code likhege jo required hota ha baki cases ke liye to half half code hoga) 
//         X                    X               X                  X
//        / \                  / \             / \                / \
//       /   \                /   \           /   \              /   \
//      /     \            null    \         /    null          /     \
//   null     null                  Y       Y                  Y       Z      

    public static int maxPathSum(TreeNode root) {
        int[] ans = solve(root);
        return ans[0];  // returning the maximum ans till now of (LEAF TO LEAF)
    }
 
    public static int[] solve(TreeNode root){
        // base case no need handle ho gya niche sab

        int[] myans = new int[]{0, 0};  // initial value kuch bhi daal do aage haame change hi karna ha

        if(root.left == null && root.right == null){  // leaf
            myans =  new int[]{-(int)1e9, root.val};  // {-infinity, root.val} hi return hoga aur kuch nai
        }
        else if(root.left != null && root.right == null){  // only left child present
            int[] left = solve(root.left); // only left se ans manga lo
            myans[0] = left[0];
            myans[1] = left[1] + root.val;
        }
        else if(root.left == null && root.right != null){ // only right child present
            int[] right = solve(root.right);  // only right se ans manga lo
            myans[0] = right[0];
            myans[1] = right[1] + root.val;
        }
        else if(root.left != null && root.right != null){ // both left & right child present -> yaha haam apna normal tarike se nikalege ie myans[0], myans[1] ko jo concept hota ha
            int[] left = solve(root.left);  // dono left & right se ans mangane ki jarurat padegi
            int[] right = solve(root.right);  // ans dono se max ans nikalege

            myans[0] = Math.max(left[0], right[0]);
            myans[0] = Math.max(myans[0], (left[1] + right[1] + root.val));
            
            myans[1] = Math.max((left[1]+root.val), (right[1] + root.val));
        }
        
        return myans;
    }


//===========================================================================================================


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




//===========================================================================================================


// acha question ha
    // LC: 437 Path Sum III   (jo sochte ha solution vo nahi hota yaha solution)

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


//==========================================================================================================================================================

