
// LC - 1373. Maximum Sum BST in Binary Tree

    // CONCEPT 
    
    // The main problem is how to check wheather a subtree is bst or not  -> this will used in all problems
    // to solve this there is a way:-
    // haam ek pair bana lege jisme (smallest, largest, maxsum, isbst) elements store hoge
    // and haam post order me upar ki taraf ja rahe ha and at each level we check
    // if(leftans.smallest < root.val && root.val < rightans.largest) => true: means our level is valid bst
    // and now we will calculate the sum at our level and update our finalans(global integer)

    // ye concept true isliye hoga because haam niche se valid bst nikalte ja rahe ha agar kisi bhi point par bst invalid hoga then ussi time isbst = false; ho jayega 
    public int maxSumBST(TreeNode root) {
        maxans = 0;
        solve(root);
        return maxans;
    }  

    public class pair{
        int smallest, largest, maxsum;
        boolean isbst;
        pair(int small, int large, int m, boolean val){
            this.smallest = small;
            this.largest = large;
            this.maxsum = m;
            this.isbst = val;
        }
    }

    static int maxans = 0;
    public pair solve(TreeNode root){
        if(root == null) return new pair((int)1e9, -(int)1e9, 0, true);
        
        pair left = solve(root.left);
        pair right = solve(root.right);
        
        pair myans = new pair((int)1e9, -(int)1e9, 0, false);  // isbst me false karna padega 
        
        myans.smallest = Math.min(root.val, Math.min(left.smallest, right.smallest));
        myans.largest = Math.max(root.val, Math.max(left.largest, right.largest));
        myans.maxsum = left.maxsum + right.maxsum + root.val;
        
        // condition for true bst 
        if((left.isbst && right.isbst) && (left.largest < root.val && root.val < right.smallest)){ 
            int totalsum = left.maxsum + right.maxsum + root.val;
            maxans = Math.max(maxans, totalsum);
            myans.isbst = true;   // ye yahi karna hoga nahi to ye false hi rahega by default
        }
        
        return myans;
    }



    //==================================================================================================================



    // GFG - Largest BST

    // NOTE : yaha size of largest bst pucha ha na ki maxsum of valid bst
    static class pair{
        int smallest, largest, size;
        boolean isbst;
        pair(int small, int large, int size, boolean b){
            this.smallest = small;
            this.largest = large;
            this.size = size;
            this.isbst = b;
        }
    }
    static int maxans = 0;
    static int largestBst(Node root)
    {
        // Write your code here
        maxans = 0;
        solve(root);
        return maxans;
    }
    
    public static pair solve(Node root){
        if(root == null) return new pair((int)1e9, -(int)1e9, 0, true);
        
        pair left = solve(root.left);
        pair right = solve(root.right);
        
        pair myans = new pair((int)1e9, -(int)1e9, 0, false);
        
        myans.smallest = Math.min(root.data, Math.min(left.smallest, right.smallest));
        myans.largest = Math.max(root.data, Math.max(left.largest, right.largest));
        myans.size = left.size + right.size + 1;
        
        if((left.isbst && right.isbst) && (left.largest < root.data && root.data < right.smallest)){
            int totalsize = left.size + right.size + 1;
            maxans = Math.max(maxans, totalsize);  // update finalans(global value)
            myans.isbst = true;
        }
        
        return myans;
    }
    
    
    
    