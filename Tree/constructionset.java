public class constructionset{
    // (I) CONSTRUCTION OF BINARY TREE (BT):
    // saro ki complexity = O(n^2) ha but if space (hashmap use kiya then O(n) me kar sakte ha)

    // CONSTRUCT BT FORM  (PRE-ORDER & IN-ORDER)
    // psi : preorder starting index,  pei : preorder ending index
    // isi : inorder starting index,  iei : inorder ending index

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        if(n == 0) return null;
        TreeNode ans = solve(preorder, 0, n-1, inorder, 0, n-1);
        
        return ans;
    }
    
    
    public TreeNode solve(int[] preorder, int psi, int pei, int [] inorder, int isi, int iei){
        if(psi > pei) return null;

        int val = preorder[psi];
        TreeNode root = new TreeNode(val);
        
        int len = 0;
        for(int i = isi; i <= iei; i++){
            if(inorder[i] == val) break;
            len++;
        }
        
        root.left = solve(preorder, psi+1, psi+len,inorder, isi, isi+len-1);
        root.right = solve(preorder, psi+len+1, pei,inorder, isi+len+1, iei);
        
        return root;
    }

    
    // CONSTRUCT BT FORM  (POST-ORDER & IN-ORDER)
    // just (pre-in) ka opposite hi ha ye vala bhi 
    // bass isme hame loop n-1 se 0 tak chalana ha only

    // psi : preorder starting index,  pei : preorder ending index
    // isi : inorder starting index,  iei : inorder ending index
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if(n == 0) return null;
        return buildtree(postorder,0, n-1, inorder, 0, n-1);
    }
    
    public TreeNode buildtree(int[] postorder, int psi, int pei ,int[] inorder, int isi , int iei){
        if(psi > pei) return null;
        
        int val = postorder[pei];
        TreeNode root = new TreeNode(val);
        
        int len = 0;  
        for(int i = iei; i >= isi; i--){
            if(inorder[i] == val) break;
            len++;
        }
        
        root.left = buildtree(postorder, psi, pei-len-1, inorder, isi, iei-len-1);
        root.right = buildtree(postorder, pei-len, pei-1, inorder, iei-len+1, iei);
        
        return root;
    }  



    // CONSTRUCT BT FORM  (PRE-ORDER & POST-ORDER)
    // bass thoda sa different ha isme baki logic same hi ha 
    // NOTE : binary tree formed by this is not unique(hame tree ki arrangement milegi) 
    
    // presi : preorder starting index , preei : preorder ending index
    // postsi : postorder starting index, postei : postorder ending index
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        if(n == 0) return null;
        
        return buildTree(preorder, 0, n-1, postorder, 0, n-1);
    }
    
    public TreeNode buildTree(int[] preorder, int presi, int preei, int[] postorder, int postsi, int postei){
        if(presi > preei) return null;
        if(presi == preei) return new TreeNode(preorder[presi]); // equal vala case handle it, bass ye line extra lagegi yaha(imp. ha ye)
        
        TreeNode root = new TreeNode(preorder[presi]);
        
        int findval = preorder[presi+1];
        int len = 0; 
        for(int i = postsi; i <= postei; i++){
            if(findval == postorder[i]) break;
            len++;
        }
        
        root.left = solve(preorder, presi+1, presi+len+1, postorder, postsi, postsi+len);
        root.right = solve(preorder, presi+len+2 , preei , postorder, postsi+len+1, postei-1);
        
        return root;
    }
    



}