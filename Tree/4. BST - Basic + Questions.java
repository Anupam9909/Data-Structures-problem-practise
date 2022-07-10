import java.util.ArrayList;
import java.util.Arrays;
import java.uitl.io;

public class bst{
    public class Node{
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val = val;
        }
    }

    // function depending on Tree Structure, then BT AND BST SAME
    public static int size(Node root ){
        if(root == null) return 0;

        return size(root.left)+size(root.right)+1;
    }

    public static int height(Node root){
        if(root == null) return -1;

        return Math.max(height(root.left), height(root.right))+1;
    }

    //{height, dia}
    public static int[] diameter(Node root){
        if(root == null ) return new int[]{-1,-1};

        int[] leftans = diameter(root.left);
        int[] rightans = diameter(root.right);

        int mydia = leftans[0] + rightans[0] + 2;

        int[] myans = new int[2];

        myans[0] = Math.max(leftans[0], rightans[0]) + 1;
        myans[1] = Math.max(mydia, Math.max(leftans[1], rightans[1]));

        return myans;
    }


    // function depending on tree node values, then BST AND BT different

    // max reccursive
    public static int max_val(Node root){
        if(root.right == null) return root.val;

        return max_val(root.right);
    }

    // min recursive
    public static int min_val(Node root){
        if(root.left == null) return root.val;

        return min_val(root.left);
    }

    // max itterative
    public static int max_val(Node root){
        Node curr  = root;

        while(curr.right != null)  curr = curr.right;

        return curr;
    }

    // min itterative
    public static int min_val(Node root){
        Node curr = root;

        while(curr.left != null) curr = curr.left;

        return curr;
    }


    // FIND IN BST
    public static boolean findInBst(Node root, Node p){
        if(root == null) return false;

        if(root.val == p.val){
            return true;
        }  
        else if(root.val < p.val){
            return findInBst(root.right, p);
        } 
        else{
            return findInBst(root.left, p);
        }  

    }


//====================================================================================


    //LC-701  -  INSERT INTO BST

    //(I) recurrsive Approach
    // 1nd way(short way) -> BEST SOLUTION
    public TreeNode insertNode(TreeNode root, int data){
        if(root == null) return new TreeNode(data);

        if(root.val < data){
            root.right = insertNode(root.right, data);
        }else{
            root.left = insertNode(root.left, data);
        }

        return root;
    }

     // 2st way (long way) ek prev variable rakh lege and tree ko traverse kar dege tree me to find the element
    TreeNode prev = null;
    public TreeNode insertIntoBST(TreeNode root, int data){
        TreeNode node = new TreeNode(data);
        if(root == null) return node;
        
        findinsert(root, data);
        
        if(prev.val < data) prev.right = node;
        else prev.left = node;
        
        return root;
    }

    public TreeNode findinsert(TreeNode root, int data){
        if(root == null) return null;
        
        prev = root;
        if(root.val < data) return findinsert(root.right, data);
        else return findinsert(root.left, data);
    }

    
    //(II) itterative Approach
    public TreeNode insertIntoBST(TreeNode root, int data) {
        TreeNode prev = null;
        TreeNode curr = root;
        
        TreeNode node = new TreeNode(data);
        if(root == null) return node;
        
        while(curr != null){
            prev = curr;
            
            if(curr.val < data) curr = curr.right;
            else curr = curr.left;
        }
        
        if(prev.val < data) prev.right = node;
        else  prev.left = node;
        
        return root;
    }



//====================================================================================



    // LC- 450 , DELETE A NODE IN BST

    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null ) return null;
        
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }
        else if(root.val  > key){
            root.left = deleteNode(root.left, key);
        }
        else if(root.val == key){
            if(root.left == null && root.right == null){
                return null;
            }
            else if(root.left != null && root.right == null){
                return root.left;
            }
            else if(root.left == null && root.right != null){
                return root.right;
            }
            else{
                // ya to left se maximum value nikal ke kar lo
                int maxvalue = maxval(root.left);
                root.val = maxvalue;
                
                root.left = deleteNode(root.left, maxvalue);
            }
        }
        
        return root;
        
    } 
    
    public int maxval(TreeNode root){
        return root.right == null ? root.val : maxval(root.right);
    }
    


//====================================================================================

    // ROOT TO NODE PATH IN BST (ye bilkul different ha binary tree se(easy ha usse))
    public static boolean rootToNodePathBST(Node root, Node dataNode, ArrayList<Node> path){    // O(log n) time
        if(root == null) return false;

        path.add(root);   // ye function missile ki tarah dataNode pe jayega so jaha bhi jaye atte hi add kar do
        
        if(root.val == dataNode.val){
            return true;
        }
        else if(root.val < dataNode.val){
            return rootToNodePathBST(root.right, dataNode , path);
        }
        else{
            return rootToNodePathBST(root.left, dataNode , path);
        }
    }

    // LCA IN BST
    // I way (long way)
    public static Node LCAinBst(Node root, Node q, Node p){
        ArrayList<Node> path1 = new ArrayList<>();
        rootToNodePathBST(root, p, path1);

        ArrayList<Node> path2 = new ArrayList<>();
        rootToNodePathBST(root, q, path2);

        int i = 0, j = 0;  // as yaha 0 index se same hona suru hua ha then baad me split hoga

        while(i < path1.size() && j < path2.size() && path1.get(i) == path2.get(j)){
            i++;
            j++;
        }

        return path1.get(--i);
    }

    // ii WAY . BST HA TO FAYEDA UTHAYEGE (SHORT WAY-> DIRECT HI HA)
    public Node LCA(Node root, int n1, int n2)
	{
        if(root == null) return null;

        if(n1.data < root.data && n2.data < root.data){
            LCA(root.left, n1, n2);
        }
        else if(n1.data > root.data && n2.data > root.data){
            LCA(root.right, n1, n2);
        }
        else{
            return root;
        }

    }

    

//====================================================================================




    // Inorder successor in BST (leetcode premium ha) 
    // https://www.interviewbit.com/problems/next-greater-number-bst/

    // O(logn) - imp. ha ye

    public TreeNode inorder_Successor_BST(TreeNode root,TreeNode data){
        TreeNode succ = null;
        TreeNode curr = root;
        
        while(curr != null){
            if(curr.val <= data.val){   // agar equal ho jaye to haam right chale jayege as succ to udhar hi milega and successor update ho jayega baad me (dry run it)
                curr = curr.right;
            }
            else if(curr.val > data.val){
                succ = curr;
                curr = curr.left;
            }
        }
        
        return succ;        
    }



//=====================================================



// Inorder Predecesor in BST (if question ask)

    public TreeNode inorder_Predecessor_BST(TreeNode root,TreeNode data){
        TreeNode pred = null;
        TreeNode curr = root;
        
        while(curr != null){
            if(curr.val < data.val){   // agar equal ho jaye to haam right chale jayege as succ to udhar hi milega and successor update ho jayega baad me (dry run it)
                pred = curr;
		curr = curr.right;
            }
            else if(curr.val >= data.val){
                curr = curr.left;
            }
        }
        
        return pred;
    }

//================================================================

// ek sath bhi calculate kar sakte ha but lamba ha and bada ha aur break; na lagao to TLE bhot confusion hoti ha so niche vala tarike use mat karna upar vala hi yaad karo

// equal to vale case me pred and succ dono alag tarike se nikalna padega
//     public void predecessor_successor_ofBST(TreeNode root, int data){
//         TreeNode pred = null;
//         TreeNode succ = null;

//         TreeNode curr = root;
//         while(curr != null){
//             if(curr.val < data){
//                 pred = curr;
//                 curr = curr.right;
//             }
//             else if(curr.val > data){
//                 succ = curr;
//                 curr = curr.left;
//             }
//             else if(curr.val == data){
//                 // updating predessor
//                 if(curr.left != null){
//                     TreeNode ptr = curr.left;
//                     while(ptr != null) 
//                         ptr = ptr.right;

//                     pred = ptr;
//                 }

//                 // updating successor
//                 if(curr.right != null){
//                     TreeNode ptr = curr.right;
//                     while(ptr.left != null) 
//                         ptr = ptr.left;

//                     succ = ptr;
//                 }

//                 break;    //(IMPORTANT HA YE LIKHNA) since data mil gya ha so break the loop aur aage ni jana
//             }
//         }

//         // now jo bhi return karna ha kar do
//         // return pred;
//         return succ;
//     }




//====================================================================================


    // LC - 98. Validate Binary Search Tree

    // bst traverse karte karte <minvalue, maxvalue> pe check karte jao
    // if going LEFT IN BST ->   <minvalue, root.val>
    // if going RIGHT IN BST -> <root.val, maxvalue>
    // and check everytime for valid return true , and if not return false

    public boolean isValidBST(TreeNode root) {
        return solve(root, -(long)1e13, (long)1e13);
    }
    
    public boolean solve(TreeNode root, long minvalue, long maxvalue){
        if(root == null) return true;
        
        boolean myans = true;
        if(root.val > minvalue && root.val < maxvalue){
            myans = myans && solve(root.left, minvalue, (long)root.val);
            myans = myans && solve(root.right, (long)root.val, maxvalue);
        }else{
            myans = false;
        }
        
        return myans;
    }

//======================================================================================================================


    // LC - 99. Recover Binary Search Tree

        // ese hi karo (easy)
    // case 1 : when non - continuous elements are changed
    //     1 2 3 4 5 6 7 8 9 
    //     1 2 7 4 5 6 3 8 9   [3,7]are changed  -> then, ans[0] = prev && ans[1] = curr
    
    // case 2 : when continuous elements are changed
    //     1 2 3 4 5 6 7 8 9 
    //     1 2 4 5 4 6 7 8 9   [4,5]are changed  -> then,  ans[1] = curr
    
    static TreeNode[] ans;
    static int count;
    static TreeNode prev;
    public void recoverTree(TreeNode root) {
        ans = new TreeNode[2];
        count = 0;
        prev = null;
        
        solve(root);  // ye function donno case sambhal lega and ans me correct swap element ke node le ayega
        
        // swap both of the nodes
        int temp = ans[0].val;
        ans[0].val = ans[1].val;
        ans[1].val = temp;
    }
    
    public void solve(TreeNode root){
        if(root == null) return ;
        
        solve(root.left);
        
        // inorder ka kaam 
        TreeNode curr = root;
        if(prev != null){
            if(prev.val > curr.val){
                if(count == 0){
                    ans[0] = prev;
                    ans[1] = curr;
                    count++;
                }else{
                    ans[1] = curr;
                }
            }
        }
        prev = curr;
        
     
        solve(root.right);
    }

    
//===========================================================================================================================



    // LC-1008. Construct Binary Search Tree from Preorder Traversal
    // O(n) time , O(n) space(of recursion)
    // best approach (basically second 1 property of bst ka use kiya ha vo interval( <minval, maxval> ) vala)
    int i = 0;
    public TreeNode bstFromPreorder(int[] preorder){
        int minval = -(int)1e9;
        int maxval = (int)1e9;
        return solve(preorder, minval, maxval);
    }
    
    public TreeNode solve(int[] arr, int minval, int maxval){
        if( i >= arr.length ) return null;
        
        if(arr[i] < minval || arr[i] > maxval){
            return null;
        }
        
        TreeNode root = new TreeNode(arr[i]);
        i++;
        
        root.left = solve(arr, minval , root.val);
        root.right = solve(arr, root.val, maxval);
        
        return root;
    }
    


     // II approach (dry run pattern)-> O(nlogn) average time(as haam har level pe (n/2 , n/4, n/8... so on) kaam kar rahe ha ), O(n) space (recursion ka) 
//     public TreeNode bstFromPreorder(int[] preorder) {
//         if(preorder.length == 0) return null;
//         return constructBST(preorder, 0, preorder.length-1);
//     }
    
//     public TreeNode constructBST(int[] arr, int si, int ei){
//         if(si > ei) return null;
//         if(si == ei) return new TreeNode(arr[si]);
        
//         int i = si+1;
//         while(i <= ei && arr[i] < arr[si] ) i++;
        
//         TreeNode root = new TreeNode(arr[si]);
        
//         root.left = constructBST(arr, si+1, i-1);
//         root.right = constructBST(arr, i, ei);
        
//         return root;
//     }






//==========================================================================================================================




    // LC-617, merge two bst
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        
        return makeBT(root1, root2);
    }
    
    public TreeNode makeBT(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null) return null;
        
        int ans = 0;
        ans += root1 == null ? 0 : root1.val;
        ans += root2 == null ? 0 : root2.val;
        
        TreeNode temp = new TreeNode(ans);
        
        temp.left  = makeBT( (root1 == null ? null : root1.left), (root2 == null ? null : root2.left));
        temp.right = makeBT( (root1 == null ? null : root1.right), (root2 == null ? null : root2.right));
        
        return temp;
    }










    

    public static void main(String [] args){
        
    }

    
}