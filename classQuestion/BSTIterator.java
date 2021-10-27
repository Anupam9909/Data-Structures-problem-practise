// QUESTION 1 -  BST ITERATOR 
class BSTIterator{
    public BSTIterator(TreeNode root) {  }  // CONSTRUCTOR(isme to jo bhi variable loge usko intialise karna ha bass)
    public int next() { }  // gives the next greator successor element from the bst
    public boolean hasNext() { } // gives true or false if hamara next greator successor element present ha ki ni
}

// SOLUTION 1 (BST ITERATOR)
class BSTIterator {
    // [inorder successor of the given data] -> ka hi question ha ye bass class ki form me ha 
    
    // variable defining here
    TreeNode root;
    TreeNode minele;
    public BSTIterator(TreeNode root) {
        // variable initialising here
        minele = null;
        this.root = root;
    }
    
    public TreeNode inordersuccessor(TreeNode data){
        if(data == null) return root;
        if(root == null) return null;
        TreeNode curr = root;
        TreeNode succ = null;
        
        while(curr != null){
            if(curr.val < data.val){
                curr = curr.right;
            }else if(curr.val > data.val){
                succ = curr;
                curr = curr.left;
            }else{
                if(curr.right == null) return succ;
                TreeNode ptr = curr.right;
                while(ptr.left != null) ptr = ptr.left;
                
                succ = ptr;
                return succ;
            }
        }
        
        return null;
        
    }
    
    public int next() {
        // do function work
        if(minele == null){
            TreeNode temp = root;
            while(temp.left != null) temp = temp.left;
            minele = temp;
        }else{
            TreeNode ans = inordersuccessor(minele);
            minele = ans;
        }
        
        return minele == null ? -1 : minele.val;
    }
    
    public boolean hasNext() {
        // do function work
        if(inordersuccessor(minele) == null) return false;
        return true;
    }
}


//===========================================================================
// QUESTION 2   MIN STACK 




























//===========================================================================
// QUESTION 2.    LRU CACHE 



























//===========================================================================
// QUESTION 3.    GET RANDOM IN O(1)   (asked from devashish(pocket pills))






























//===========================================================================
// QUESTION 4.    MEDIAN FROM DATA STREAM





