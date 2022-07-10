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

// LC-987. Vertical Order Traversal of a Binary Tree (imp. hard question because yaha order question ke hisab se karna ha and uske liye coordinate rakhna padega(r,c) so that to write order in PriorityQueue<>()

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
    
   
 // ------------------------------------------------------------------------------------------------------------------------



    // VOT - SUM

    // simple ha now jo hashmap me values ayi ha VOT Karne ke bad uska haar level ka sum le lo ho gya VOT - SUM




     
//===========================================================================================================



    // DIAGONAL ORDER TRAVERSAL
    // do method ha isko karne ke 

    // METHOD - 1
    // => JESE VOT(VERTICAL ORDER TRAVERSAL) KIYA THA VESE HI karo HASHMAP LE KE [IDX vs ARRAYLIST<>()] 
    // concept :                   If our level idx is X 
    //                                  /           \
    //                                 /             \
    //                                /               \
    //    left child me (X-1) pass kar do     &   right child me X(same idx) pass kar do  
    
    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        
        Queue<pair> que = new LinkedList<>();
        que.add(new pair(root, 0));
        int minval = 0, maxval = 0;
        
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                pair rp = que.remove();
                minval = Math.min(minval, rp.idx);
                maxval = Math.max(maxval, rp.idx);
                
                hm.putIfAbsent(rp.idx, new ArrayList<>());
                hm.get(rp.idx).add(rp.root.val);
                
                if(rp.root.left != null){
                    que.add(new pair(rp.root.left, rp.idx-1));  // left me (idx-1) karke bej do
                } 
                if(rp.root.right != null){
                    que.add(new pair(rp.root.right, rp.idx));  // right me same (idx) karke bej do
                } 
            }
        }
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = maxval; i >= minval; i--){
            ArrayList<Integer> arr = hm.get(i);
            ans.add(arr);
        }
        return ans;
    }
    

//-----------------------------------------------------------------------------------------------------------------------------

  // METHOD - 2
  // can see this video  ->  https://www.youtube.com/watch?v=cvK3Sb6zJ1k

    // kissi bhi node pe aao 
    // 1. add in ans array
    // 2. iska left element to que me daal do
    // 3. and aage baad jao right child pe just
    // apne aap answer banta chala jayega

    public ArrayList<Integer> diagonal(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                Node rn = que.remove();  // rn : remove node
                
                while(rn != null){
                    ans.add(rn.data);
                    if(rn.left != null) que.add(rn.left);
                    
                    rn = rn.right;
                }
                
            }
        }
        
        return ans;
    }

// or this way of BFS jab haar level ke alag alag array chahiye ho elements ke (jo pepcoding ne karaya ha video me)

    public ArrayList<ArrayList<Integer>> solve(TreeNode root){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<Integer>();
        
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        
        while(que.size() != 0){
            int size = que.size();
            ArrayList<Integer> arr = new ArrayList<>();

            while(size-- > 0){
                TreeNode rn = que.remove();
                
                while(rn != null){
                    arr.add(rn.val);
                    
                    if(rn.left != null)  que.add(rn.left); 
                    rn = rn.right;
                }
            }
            ans.add(arr);
        }

        return ans;
    }


  //===================================================================================================================

    // DOT - SUM
    // GFG => Diagonal Sum In Binary Tree   -> imp as we have used TreeMap here simple ha just see

    public static class pair{
        Node root;
        int idx;
        pair(Node root, int i){
            this.root = root;
            this.idx = i;
        }
    }

    public static ArrayList <Integer> diagonalSum(Node root) 
    {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();  // {diagonalIdx, sum}  // treemap is EXACTLY A HASHMAP BASS ISME KEY KI VALUES SORTED ORDER ME STORE HOTI HA , BAKI SARE FUNCTION HAHSMAP JESE HA YAAD KARNE KI KUCH JARURAT NI HA
        
        Queue<pair> que = new LinkedList<>();  // {root, idx}
        que.add(new pair(root,0));
        
        while(que.size() != 0){
            pair rn = que.remove();
            int value = rn.root.data;
            
            tm.putIfAbsent(rn.idx, 0);
            tm.put(rn.idx, tm.get(rn.idx) + value);
            
            if(rn.root.left != null) que.add(new pair(rn.root.left, rn.idx-1));
            if(rn.root.right != null) que.add(new pair(rn.root.right, rn.idx));
        }
        
        for(int v : tm.keySet()){
            int val = tm.get(v);
            ans.add(val);
        }
        
        Collections.reverse(ans);
        return ans;
    }




  //===================================================================================================================
















    
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








}