    // vertical view (VERTICAL ORDER TRAVERSAL)

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


//==========================================================================================================
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

//============================================================================================================

// LC-987. Vertical Order Traversal of a Binary Tree

    // note : yaha since question me given ha ki jab elements ka (row, col) equal ho to we have to sort them and store in the ans list, So, simple VOT Algorithm laga ke vo nahi hoga hence, haame:-
    // pair me idx na rakh ke [row] & [col] rakhna padega to differentiate each element
    // and ek PriorityQueue lena padega so that element correct sorted order me hi bahar aye and our result will be correct at last
    public class pair{
        TreeNode root;
        int row, col;
        pair(TreeNode root, int r, int c){
            this.root = root;
            this.row = r;
            this.col = c;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        int minval = 0, maxval = 0;
        
        PriorityQueue<pair> que = new PriorityQueue<>((a,b)->{
            if(a.row == b.row){
                if(a.col == b.col){
                    return a.root.val - b.root.val;  // this - other => sorted inc. order
                }
                return a.col - b.col;
            }
            return a.row - b.row;
        });
        que.add(new pair(root, 0, 0));
        
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                pair rp = que.remove(); // r
                
                minval = Math.min(minval, rp.col);
                maxval = Math.max(maxval, rp.col);
                
                // w
                hm.putIfAbsent(rp.col, new ArrayList<>());
                hm.get(rp.col).add(rp.root.val);
                
                // a*
                if(rp.root.left != null){
                    que.add(new pair(rp.root.left, rp.row+1, rp.col-1));
                }
                
                if(rp.root.right != null){
                    que.add(new pair(rp.root.right, rp.row+1, rp.col+1));
                }
            }
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = minval; i <= maxval; i++){
            ans.add(hm.get(i));
        }
        return ans;
    }
    
    
    
//===========================================================================================================



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

//==========================================================================================================







}