public class question{
    
    // LC-236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> arr1 = new ArrayList<>();  
        nodeToRootPath(root, p, arr1);
        
        ArrayList<TreeNode> arr2 = new ArrayList<>();
        nodeToRootPath(root, q, arr2);
        
        int i = arr1.size()-1;
        int j = arr2.size()-1;
        while(i >= 0 && j >=0 && arr1.get(i) == arr2.get(j)){
            i--; j--;
        }
    
        return arr1.get(i+1);
    }

    public boolean nodeToRootPath(TreeNode root, TreeNode reqNode, ArrayList<TreeNode> psf){
        if(root == null) return false;
        
        if(root == reqNode){
            psf.add(root);
            return true;
        }
        
        boolean ans = false;
        ans = ans || nodeToRootPath(root.left, reqNode, psf);
        ans = ans || nodeToRootPath(root.right, reqNode, psf);
        
        if(ans) psf.add(root);
        
        return ans;
    }
    
}