// LC - 951. Flip Equivalent Binary Trees

    // CONCEPT :  haam sirf root1 vale tree me changes karege and check karte rahege haar moment pe ki root1 and root2 ke element same ha ya nahi
    
    // short way
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return solve(root1, root2);
    }
    
    public boolean solve(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null) return true; 
        if((root1 == null && root2 != null) || (root1 != null && root2 == null)) return false;
        if(root1.val != root2.val) return false;
        
        boolean myans = false;
        // bina flip kiye hue puch lo ki sahi ha ki nahi
        boolean recans1 = solve(root1.left, root2.left) && solve(root1.right, root2.right); 
        
        // flip karke puch lo sahi ha ki nahi 
        boolean recans2 = solve(root1.left, root2.right) && solve(root1.right, root2.left);  
        
        // kissi bhi ek jagah se true mil jaye to myans is also true
        myans = recans1 || recans2;  
        
        return myans;
    }
    
    
    
    
    
    //--------------------------------------------------------------------------------------------------
    
    
    
    
    // long way -> jab bhi child ka soch ke kaam karege to code bada hoga (my observation)
//     public boolean flipEquiv(TreeNode root1, TreeNode root2) {
//         return solve(root1, root2);
//     }
    
//     public boolean solve(TreeNode root1, TreeNode root2){
//         if((root1 == null && root2 != null) ||  (root1 != null && root2 == null)) return false;
//         if(root1 == null && root2 == null) return true;
//         if(root1.val != root2.val) return false;  // ye bhi check kar lena ha haame for edge case
        
//         int l1val = root1.left == null ? -1 : root1.left.val;
//         int r1val = root1.right == null ? -1 :  root1.right.val;
        
//         int l2val = root2.left == null ? -1 :  root2.left.val;
//         int r2val = root2.right == null ? -1 :  root2.right.val;
        
//         if(l1val == l2val && r1val == r2val){ // ok
//              boolean l = solve(root1.left, root2.left);
//              boolean r = solve(root1.right, root2.right);
//              return (l&&r);
//         }else if(l1val == r2val && r1val == l2val){ // fliped
//              // flip  // haam sirf root1 me changes karege and check karte rahege root3 ko dekte hue
//              TreeNode temp = root1.left;
//              root1.left = root1.right;
//              root1.right = temp;
            
//              boolean l = solve(root1.left, root2.left);
//              boolean r = solve(root1.right, root2.right);
//              return (l&&r);            
//         }else{
//             return false;
//         }
//     }


// =========================================================================================================
    // I WAY : 
    // O(N^2) solution
    // concept : 
//     If left subtree height is greater, then the result is whatever returned by the left as it has highest depth elements.
// Similarly if right subtree height is greater, then the result is whatever returned by the right as it has highest depth elements.
// If heights of both left and right subtrees are equal then the current node is the common ancestors of the deepest leaves.
    
    public int height(TreeNode root){
        return root==null ? 0 : Math.max(height(root.left),height(root.right))+1;
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root){
        if(root == null) return null;
        
        TreeNode left = lcaDeepestLeaves(root.left);
        TreeNode right = lcaDeepestLeaves(root.right);
        
        int lheight = height(root.left);
        int rheight = height(root.right);
        
        if(lheight == rheight) return root;
        else{
            if(lheight > rheight) return left;
            else return right;
        }
    }
    
    
    
//==============================================================================================    
    
    
    // II WAY - vohi root to node path vala concept: [O(n) time & O(n) space]
    // simply sare leaves ke root to node path nikal lo and then lowest common ancestor nikal lo
    // yahi code karna simple and consise ha
    
    public int height(TreeNode root){
        return root==null?0 : Math.max(height(root.left), height(root.right))+1;
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return root;
        int h = height(root);
        List<List<TreeNode>> ans = new ArrayList<>();
        solve(root, h, new ArrayList<>(), ans);
        
        List<TreeNode> firstarr = ans.get(0);
        if(ans.size() == 1) return firstarr.get(firstarr.size()-1);  // sirf ek hi path hoga to just return last node itself
    
        for(int i = 0; i < h; i++){
            boolean c = true;
            TreeNode prev = ans.get(0).get(i);
            for(int j = 0; j < ans.size(); j++){
                if(ans.get(j).get(i) != prev){ c = false; break;} 
            }
            if(!c){
                return ans.get(0).get(i-1);
            }
        }
        return null;
    }
    
    // this solve function gives root to node path for all deepeast leaves
    public void solve(TreeNode root, int h, List<TreeNode> arr, List<List<TreeNode>> ans){
        if(h == 1 && root != null){   // last node par hi rokna padega h=0 pe roka to do baar same path print ho jayega
            List<TreeNode> bsans = new ArrayList<>(arr);
            bsans.add(root);
            ans.add(bsans);
            return;
        }
        if(root == null) return;

        arr.add(root);
        solve(root.left, h-1, arr, ans);
        
        solve(root.right, h-1, arr, ans);
        arr.remove(arr.size()-1);
    }
    
//=========================================================================================

//  LC-865. Smallest Subtree with all the Deepest Nodes -> same question ha find lca of deepest leaves 

  // I WAY
  // O(N^2) time & O(1) space (just recurssion ha aur kuch nahi)

// CONCEPT: 
// If left subtree height is greater, then the result is whatever returned by the left as it has highest depth elements.
// Similarly if right subtree height is greater, then the result is whatever returned by the right as it has highest depth elements.
// If heights of both left and right subtrees are equal then the current node is the common ancestors of the deepest leaves.
    
    public TreeNode LCA(TreeNode root){
        if(root == null) return null;
        
        TreeNode leftLCA = LCA(root.left);
        TreeNode rightLCA = LCA(root.right);
        
        int lheight = height(root.left);
        int rheight = height(root.right);
        
        if(lheight == rheight){
            return root;  // we return this level root because this can be my potential LCA
        }
        else{
            if(lheight > rheight)  return leftLCA;
            else   return rightLCA;
        }
    }

    public int height(TreeNode root){
        return root==null ? 0 : Math.max(height(root.left), height(root.right)) + 1;
    }
//--------------------
// II way
// O(N) space use karke to ho hi jayega root to node path manga ke make solution


// =========================================================================================================