// 222. Count Complete Tree Nodes

// brute force

//     // simply write the size() function of a tree -> O(N) solution
//     public int countNodes(TreeNode root) {
//         if(root == null) return 0;
//         return solve(root.left) + solve(root.right) + 1;
//     }
    

// optimized code
// (logN)^2 time

    // complete binary me last level pe left to right node fill hoge
    // concept : ek perfect binary tree ka size is uqual to (2^level-1)
    // so, solution
    // har level pe ye (leftmostheight) & (rightmostheight) pehle hi nikal lo
    // then see if they are equal use direct formula to calculate the size 
    // if not equal then use recursion to calculate size
    public int countNodes(TreeNode root){
        int ans = solve(root);
        return ans;
    }
    
    public int solve(TreeNode root){
        if(root == null) return 0;
        int leftmostHeight = heightleft(root.left);
        int rightmostHeight = heightright(root.right);
        
        if(leftmostHeight == rightmostHeight) return (int)Math.pow(2,rightmostHeight)-1;
        else{
            int leftans = solve(root.left);
            int rightans = solve(root.right);
            
            return leftans + rightans + 1;
        }
    }
    
    public int heightleft(TreeNode root){
        int count = 1;
        while(root != null){
            count++;
            root = root.left;
        }
        return count;
    }
    
    public int heightright(TreeNode root){
        int count = 1;
        while(root != null){
            count++;
            root = root.right;
        }
        return count;
    }
     