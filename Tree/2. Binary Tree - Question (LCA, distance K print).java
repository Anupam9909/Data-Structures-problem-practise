// tree ka question jab bhi aye pehle recurssion se try karna ho jayega ya to paramter me kuch le ke ya to 
// kuch pair class return kara ke ya to simple value return kara ke ya to inorder me chal ke bst me etc etc 
// ye lee ne bataya ha vo leetcode vale ne

public class question{

    public class Node{
        int val;
        Node left;
        Node right;
        Node(int v) this.val = v;
        Node(int v, Node l){this.val = v; this.left = l;}
        Node(int v, Node l, Node r) { this.val = v; this.left = l; this.right = r;}
    }



//=======================================================================================

// LC-236. Lowest Common Ancestor of a Binary Tree      
// issi question ka dursra naam:-
// I way (without using space)

    // O(n) time, O(1) space
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        TreeNode ans = solve(root, p, q);
        return ans;
    }
    
    public TreeNode solve(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        if(root == p || root == q) return root;
        
        TreeNode leftans = solve(root.left, p, q);
        TreeNode rightans = solve(root.right, p, q);
        
        if(leftans == null) return rightans;
        else if(rightans == null) return leftans;
        else if(leftans == null && rightans == null) return null;
        else {  // means this root is LCA -> ie. -> (leftans != null && rightans != null)
            return root; 
        }
    }
    
    
//------------------------------------------------------------------------------------------

  // II WAY
  // O(N) time & O(N) space (USING NODE TO ROOT PATH)

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

//=======================================================================================

  // LC-257. Binary Tree Paths

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return new ArrayList<String>();
        
        String psf = root.val + "";
        List<String> ans = new ArrayList<>();
        solve(root, psf, ans);
        return ans;
    }
    
    public void solve(TreeNode root, String psf, List<String> ans ){
        if(root == null) return;
        if(root.left == null && root.right == null){
            ans.add(psf);
            return ;
        } 

        if(root.left != null) solve(root.left, psf +"->"+ root.left.val, ans);
        if(root.right != null) solve(root.right, psf +  "->" + root.right.val, ans);
    }
    
//=======================================================================================

  // LC-863 All Nodes distance k in binary Tree
    // I WAY -> find root to node path and then print k distance down function
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

    // --------------------------------------------

        // II way solution
    // TREE KO GRAPH BANA LO AND THEN BFS LAGA DO (and add all those node which are k distance far from target node)
    // graph banane ke liye root ko parent chahiye so haam HASHMAP bana lege of {node , parent} ka
    // and then BFS laga do 
    public void markParent(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> hm){
        if(root == null) return;
        size++;
        // work
        hm.put(root, parent);
        markParent(root.left, root, hm);
        markParent(root.right, root, hm);
    }
    
    public class pair{
        TreeNode root;
        int dist;
        pair(TreeNode root, int d){
            this.root = root;
            this.dist = d;
        }
    }
    static int size = 0;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode, TreeNode> hm = new HashMap<>();
        size = 0;
        markParent(root, null, hm);
        // System.out.println(hm);
        List<Integer> ans = new ArrayList<>();
        LinkedList<pair> que = new LinkedList<>();
        que.add(new pair(target, k));
        boolean[] vis = new boolean[size+1];
        
        while(que.size() != 0){
            int s = que.size();
            while(s-- > 0){
                // r
                pair rp = que.removeFirst();
                
                // work
                if(rp.dist == 0){
                    ans.add(rp.root.val);
                }
                
                // m*
                if(vis[rp.root.val]){
                   continue;
                }
                vis[rp.root.val] = true;
                
                // a*
                // adding left child node in que
                TreeNode lnode = rp.root.left;
                if(lnode != null && !vis[lnode.val] && rp.dist >= 0) {
                    que.addLast(new pair(lnode, rp.dist-1));
                }
                // adding  right child node in que
                TreeNode rnode = rp.root.right;
                if(rnode != null && !vis[rnode.val] && rp.dist >= 0) {
                    que.addLast(new pair(rp.root.right, rp.dist-1));
                }
                // adding  parent node in que
                TreeNode pnode = hm.get(rp.root);
                if(pnode != null  && !vis[pnode.val] && rp.dist >= 0){ 
                    que.addLast(new pair(pnode, rp.dist-1));
                }
            }
        }
        return ans;
    }

    
//=======================================================================================

// GFG - BURNING TREE (INTERVIEW QUESTION)
// NOTE : ye question bhi upar vale tarike se kar sakte ha i.e k tak ke parent ko list me store kara ke.. soon but ye lamba method ha 
// best method down 
    // CONEPT: SIMPLE YE CONCEPT YAAD RAKHO KI
    // haam ek hashmap bana lege are node me [child ---> parent] ka
    // and now the question becomes : find the maximum time to traverse the whole tree
    // now to find the maximum time traverse the whole tree chahe DFS se kar lo ya BFS se 
    // baat ek hi ha 
    // I HAVE DONE WITH DFS code
    
    public static int minTime(Node root, int target) 
    {
        // Your code goes here
        tarnode = null;
        
        HashMap<Node, Node> hm= new HashMap<>();
        dfsfindParent(root, hm, target);   // sare nodes ke parents hm me daal lo
        
        boolean[] vis = new boolean[10000];
        totaltime = 0;
        dfs(tarnode, vis, 0, hm);
        return totaltime-1;
    }
    
    public static void dfs(Node root, boolean[] vis, int t, HashMap<Node, Node> hm){
        if(root == null){
            totaltime = Math.max(totaltime, t);
            return;
        }
        
        if(vis[root.data]) return;
        
        vis[root.data] = true;
        
        dfs(root.left, vis, t+1, hm);
        dfs(root.right, vis, t+1, hm);
        dfs(hm.get(root), vis, t+1, hm);
        
    }
    
    static int totaltime;
    static Node tarnode;
    
    public static void dfsfindParent(Node root, HashMap<Node, Node> hm, int target){
        if(target == root.data) tarnode = root;
        
        if(root.left != null){
            hm.put(root.left, root);
            dfsfindParent(root.left, hm, target);
        }
        
        if(root.right != null){
            hm.put(root.right, root);
            dfsfindParent(root.right, hm, target);
        }
    }

    //===========================================================================================================================


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
     
    
}