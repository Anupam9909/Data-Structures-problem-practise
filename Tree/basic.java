import java.util.io;

public class basic{
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){ };
        TreeNode(int v) this.val = v;
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // WRITING BASE CASE IN A BINARY TREE
    // if(root.left == null && root.right == null) return 0;   // IT IS A LONG WAY because this is binary tree so At most two nodes ho sakte ha so dono ke liye condition lagani padegi
    // if(root == null) return -1;  //  WRITE THIS WAY ONLY 

    // HEIGHT OF TREE (AS EDGE) - (USED AS DEFAULT)
    public int height(TreeNode root){
        if(root == null) return -1;

        int leftans = height(root.left);
        int rightans = height(root.right);
        int totalheight = Math.max(leftans, rightans) + 1;

        return totalheight;
    }

    // In short form
    public int height(TreeNode root){
        return root == null ? -1 : Math.max(root.left, root.right) + 1;
    }

    // HEIGHT OF TREE (AS NODE)
    public int height(TreeNode root){
        if(root == null) return 0;

        int leftans = height(root.left);
        int rightans = height(root.right);

        int totalheight = Math.max(leftans, rightans) + 1;
        return totalheight;
    }

    // In short form 
    public int height(TreeNode root){
        return root == null ? 0 : (Math.max(root.left, root.right) + 1);
    }

    // REMEMBER BASE CASES (base case to NULL pe hi hoga hamesha ye to pakka ha)
    // FOR EDGE -> return -1;
    // FOR NODE -> return  0;

    // size of tree
    public int sizeofTree(TreeNode root){
        if(root == null) return 0;

        int leftans = sizeofTree(root.left);
        int rightans = sizeofTree(root.right);
        return leftans + rightans + 1;

    }

    // In short form
    public int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right)+1;
    }


    // MAXIMUM AND MINIMUM VALUE IN TREE
    public int maxValue(TreeNode root){
        if(root == null) return -(int)1e8;

        int leftmax = maxValue(root.left);
        int rightmax = maxValue(root.right);

        int mymax = Math.max( (Math.max(leftmax, rightmax) , root.val);
        return mymax;
    } 

    // In short form 
    public int maxValue(TreeNode root){
        return root == null ? -(int)1e8 : Math.max( Math.max(maxValue(root.left), maxValue(root.right)), root.val);
    }

    public int minValue(TreeNode root){
        if(root == null) return (int)1e8;

        int leftmin = minValue(root.left);
        int rightmin = minValue(root.right);

        int mymin = Math.min(Math.min(leftmin, rightmin), root.val);

        return mymin;
    }

    // In short form
    public int minValue(TreeNode root){
        return root == null ? (int)1e8 : Math.min( Math.min( minValue(root.left), minValue(root.right) ), root.val));
    }


    // find data
    public boolean findData(TreeNode root, int data){
        if(root == null) return false;

        if(root.val == data) return true;

        boolean ans = false;
        ans = ans || findData(root.left);
        ans = ans || findData(root.right);

        return ans;
    }


    // or 
    public boolean findData(TreeNode root, int data){
        if(root == data) return true;

        if(root.val == data) return true;

        return findData(root.left, data) || findData(root.right, data);
    }

    // parameter as a storage
    public boolean rootToNode(TreeNode root, ArrayList<Integer> psf){

        if(root.val == data){
            psf.add(root.val);
            return true;
        }

        boolean ans = rootToNode(root.left, psf) || rootToNode(root.right, psf);

        if(ans){
            psf.add(root.val);
            return true;
        }
        return false;
    }

    // root To Node (by return type of a ArrayList)
    public ArrayList<Integer> rootToNode(TreeNode root, int data){
        if(root == null){
            return new ArrayList<>();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        if(root.val == data){
            ans.add(root.val);
            return ans;
        }
        
        ans = rootToNode(root.left, data);
        if(ans.size() != 0){
            ans.add(root.val);
            return ans;
        }
        ans = rootToNode(root.right, data);
        if(ans.size() != 0){
            ans.add(root.val);
            return ans;
        }

        return ans;
    }



}