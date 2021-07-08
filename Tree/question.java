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



    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<Integer> psf = new ArrayList<>();
        
        List<String> ans = new ArrayList<String>();
        solve(root, psf, ans);
        return ans;
    }
    
    public void solve(TreeNode root, ArrayList<Integer> psf, List<String> ans){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            psf.add(root.val);
            String stans = makeans(psf); 
            ans.add(stans);
            psf.remove(psf.size()-1);
        }
        
        psf.add(root.val);
        
        solve(root.left, psf, ans);
        solve(root.right, psf, ans);
        
        psf.remove(psf.size()-1);
    } 
    
    
    public String makeans(ArrayList<Integer> arr){
        String ans = "";
        for(int i = 0; i < arr.size(); i++){
            if(i != arr.size()-1) ans = ans + arr.get(i) + "->";
            else ans = ans + arr.get(i);
        }
        return ans;
    }


    // LC-863 All Nodes distance k in binary Tree
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k ){
        if(root == null) return new ArrayList<>();
        // if(k == 0) return new ArrayList<>(target.val);
        
        ArrayList<TreeNode> arr = new ArrayList<>();
        nodeToRootpath(root, target, arr);
        
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode blocknode = null;
        for(int i = 0; i < arr.size() && k-i >= 0; i++){
            printkdown(arr.get(i) , k-i, ans, blocknode);
            blocknode = arr.get(i);    
        }
        return ans;
    }
    
    public boolean nodeToRootpath(TreeNode root, TreeNode target, ArrayList<TreeNode> psf){     
        if(root == null) return false;
        
        if(root == target){
            psf.add(root);
            return true;
        }
        
        boolean ans = false;
        ans = ans || nodeToRootpath(root.left, target, psf);
        ans = ans || nodeToRootpath(root.right, target, psf);
        
        if(ans){
            psf.add(root);
        }
        return ans;
    }
    
    
    public void printkdown(TreeNode root, int k, ArrayList<Integer> ans, TreeNode blocknode){
        if(root == null) return;
        
        if(k == 0){
            ans.add(root.val);
            return ;
        }
        
        if(blocknode != root.left)  printkdown(root.left, k-1, ans, blocknode);
        if(blocknode != root.right)  printkdown(root.right, k-1, ans, blocknode);
    }
    
}