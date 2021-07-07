
// FindData(); -  this template used in further questions
    public boolean findData(TreeNode root, int data){
        if(root == data) return true;

        if(root.val == data) return true;

        boolean ans = false;
        ans = ans || findData(root.left, data) ;
        ans = ans || findData(root.right, data);

        return ans;
    }


// NODE TO ROOT PATH
    public boolean nodeToRootPath(TreeNode root, ArrayList<TreeNode> psf){
        if(root == null)    return false;
        
        if(root.val == data){
            psf.add(root);
            return true;
        }

        boolean ans = false; 
        ans = ans || nodeToRootPath(root.left, psf);
        ans = ans || nodeToRootPath(root.right, psf);

        if(ans){
            psf.add(root);
        }

        return ans;
    }