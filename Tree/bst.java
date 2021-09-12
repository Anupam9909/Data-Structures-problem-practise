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

    
    

    public static void main(String [] args){
        
    }

    
}