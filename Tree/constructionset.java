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


//=================================================================================================================


    
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



//=================================================================================================================



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
    



//=================================================================================================================



    // SERIALIZE AND DESERIALIZE OF A BINARY TREE
    public void serialize(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("-1001,");
            return;
        }
        
        sb.append(root.val + ",");
        serialize(root.left, sb);
        serialize(root.right, sb);
        
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(itr >= data.length() || data.length() == 0) return null;
        int num = numval(data);
        if(num == -1001) return null;
        
        TreeNode root =  new TreeNode(num);
        
        root.left = deserialize(data);
        root.right = deserialize(data);
        
        return root;
    }
    
    int itr = 0;
    public int numval(String data){
        String str = new String();
        if(data.charAt(itr) == ',') itr++;
            
        for(int i = itr; i < data.length(); i++){
            String s = data.charAt(i)+"";
            if(s.equals(",") == true){
                break;
            }else{
                str = str + data.charAt(i);
                itr++;
            }
        }
        
        int ans = Integer.parseInt(str);
        return ans;
    }



//=================================================================================================================



    // LC-101. Symmetric Tree
    // (short recurssive code)
    public boolean isSymmetric(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) return true;
        
        // do alag-alag tree bana lo pehle to (and ab se dono alag tree ha ye socho)
        TreeNode leftTree = root.left;
        TreeNode rightTree = root.right;
        
        boolean ans =  checkmirror(leftTree, rightTree);
        return ans;
    }
    // now haam dono ko sath-sath check karte chlege ki sahi ha na 
    
    public boolean checkmirror(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null)  return true;
        if(root1 == null || root2 == null) return false;
        
        boolean ans = (root1.val == root2.val);
        ans = ans && checkmirror(root1.left, root2.right);
        ans = ans && checkmirror(root1.right, root2.left);
        
        return ans;
    }


    

//=================================================================================================================


    

}