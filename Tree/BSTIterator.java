// QUESTION 1 -  BST ITERATOR 
class BSTIterator{
    public BSTIterator(TreeNode root) {  }  // CONSTRUCTOR(isme to jo bhi variable loge usko intialise karna ha bass)
    public int next() { }  // gives the next greator successor element from the bst
    public boolean hasNext() { } // gives true or false if hamara next greator successor element present ha ki ni
}

// SOLUTION 1 (BST ITERATOR)
// [inorder successor of the given data] -> ka hi question ha ye bass class ki form me ha 
// yaha ye jo given data vo hamara minimum element hoga jo hama apna variable banayege

class BSTIterator 
{
    TreeNode root;   // bst ko store karne ke liye
    TreeNode minele;  // to store the minimum element

    public BSTIterator(TreeNode root) {
        // variable initialising here
        minele = null;
        this.root = root;
    }
    
    public int next(){
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

    
    public boolean hasNext(){
        if(inordersuccessor(minele) == null) return false;
        return true;
    }


    public TreeNode inordersuccessor(TreeNode data){   // same inorder succesor vala program likha ha
        if(data == null) return root;    // bass ye line extra ha if(data == null) then return root itself
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
    
}




//===========================================================================
// QUESTION 4.    MEDIAN FROM DATA STREAM





