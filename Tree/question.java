public class question{

    public class Node{
        int val;
        Node left;
        Node right;
        Node(int v) this.val = v;
        Node(int v, Node l){this.val = v; this.left = l;}
        Node(int v, Node l, Node r) { this.val = v; this.left = l; this.right = r;}
    }
    
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




    // LC- 103. Binary Tree Zigzag Level Order Traversal 

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Stack<TreeNode> currst = new Stack<>();
        Stack<TreeNode> forwst = new Stack<>();
        
        boolean order = true;
        currst.push(root);
        
        while(currst.size() != 0){
            int size = currst.size();
            ArrayList<Integer> arr = new ArrayList<>();
            while(size-- > 0){
                TreeNode rnode = currst.pop();
                    
                arr.add(rnode.val);
                
                if(order){
                    if(rnode.left != null) forwst.push(rnode.left);
                    if(rnode.right != null) forwst.push(rnode.right);
                }else{
                    if(rnode.right != null) forwst.push(rnode.right);
                    if(rnode.left != null) forwst.push(rnode.left);
                }
                
            }
            ans.add(arr); 
            // level changes. so, update the (order) & both stack
            order = !order;
        
            Stack<TreeNode> temp = currst;
            currst = forwst;
            forwst = temp;
        }
        
        return ans;
    }































































    // vertical view 

    public class pair{
        Node node;
        int index;
        pair(Node n , int i){
            this.node = n;
            this.index = i;
        }
    }

    public static void verticalview(Node root){
        LinkedList<Node> que = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        que.add(root);
        int minorder = 0;
        int maxorder = 0;


        while(que.size() != 0){
            int size  = que.size();
            while(size-- > 0){
                pair rempair = que.removeFirst();
                Node rnode = rempair.node;
                int index = rempair.index;

                if(minorder > index) minorder = index;
                if(maxorder < index) maxorder = index;

                hm.putIfAbsent(index, new ArrayList<>());
                hm.get(index).add(rnode.val);

                if(rnode.left != null)
                    que.addLast(rnode.left);

                if(rnode.right != null)
                    que.addLast(rnode.right);

            }
        }

        return; 
    }



    // ek aur similar tarika ha (ye vala har jagah submit hoga)  CORRECT 100%
    public static class pair {
        TreeNode node = null;
        int index = 0; // index of node 
        int y = 0; // level of node 

        pair(TreeNode node, int i, int y) {
            this.node = node;
            this.index = i;
            this.y = y;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<pair> que = new PriorityQueue<>((a, b) -> {
            if (a.y != b.y)
                return a.y - b.y;   // this - other  => for default behaviour
            else
                return a.node.val - b.node.val; // this - other  => for default behaviour
        });

        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        
        int minindex = 0;
        int maxindex = 0;

        que.add(new pair(root, 0 , 0));

        while (que.size() != 0) {
            pair rp = que.remove();
            TreeNode rnode = rp.node;
            int index = rp.index;
            
            minindex = Math.min(minindex, index);
            maxindex = Math.max(maxindex, index);

            hm.putIfAbsent(index, new ArrayList<>());
            hm.get(index).add(rnode.val);

            if (rnode.left != null)
                que.add(new pair(rnode.left, rp.index - 1, rp.y + 1));

            if (rnode.right != null)
                que.add(new pair(rnode.right, rp.index + 1, rp.y + 1));

        }

        
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = minindex; i<= maxindex; i++){
            ans.add(hm.get(i));
        }
        
        return ans;
        
    }





























    // CONSTRUCTION BASES QUESTION OF BINARY TREE



















































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


    
    //-----------------------------
    //-=========================


    









}