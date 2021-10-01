import java.util.*;

public class morrisTraversal{
    // ITERATIVE IN-TRAVERSAL OF A BINARY TREE
    // iterative solution(by making a stack)
    // TIME: O(N) , SPACE : O(N)
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        iterativeStacksolution(root, ans);
        return ans;
    }
    
    public void iterativeStacksolution(TreeNode root,List<Integer> ans) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(cur!=null || !stack.empty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }

    }


    // MORRIS TRAVERSAL (In-order)
    // TIME : O(N) , SPACE : O(1)
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        morrisTraversal(root, ans);
        
        return ans;
    }
    
    public void morrisTraversal(TreeNode root, List<Integer> ans){
        TreeNode curr = root;
        
        while(curr != null){
            TreeNode leftnode = curr.left;
            if(leftnode == null){
                ans.add(curr.val);
                curr = curr.right;
            }else{
                TreeNode rmnode = leftnode;  // rmnode : rightmostnode
                while(rmnode.right != null && rmnode.right != curr){ 
                    rmnode = rmnode.right;
                }
                
                if(rmnode.right == null){
                    rmnode.right = curr;
                    curr = curr.left;
                }else{
                    rmnode.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        
    }


    // MORRIS TRAVERSAL (pre-order)
    // TIME : O(N) , SPACE : O(1)
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        morrisTraversalpre(root, ans);
        
        return ans;
    }
    
    public void morrisTraversalpre(TreeNode root, List<Integer> ans){
        TreeNode curr = root;
        
        while(curr != null){
            TreeNode leftnode = curr.left;
            if(leftnode == null){
                ans.add(curr.val);
                curr = curr.right;
            }else{
                TreeNode rmnode = leftnode;  // rmnode : rightmostnode
                while(rmnode.right != null && rmnode.right != curr){ 
                    rmnode = rmnode.right;
                }
                
                if(rmnode.right == null){
                    rmnode.right = curr;
                    ans.add(curr.val);  // bas ye line shift hui ha upar
                    curr = curr.left;
                }else{
                    rmnode.right = null;
                    curr = curr.right;
                }
            }
        }
        
    }












    // EXTRA FOR MORRIS ( IN-ORDER , PRE-ORDER , POST ORDER ) -> kissi bande ka ha leetcode discuss pe
    public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		List<Integer> res = inorder(root);
		System.out.println("inorder: ");
		System.out.println(res);
		res = preorder(root);
		System.out.println("preorder: ");
		System.out.println(res);
		res = postorder(root);
		System.out.println("postorder: ");
		System.out.println(res);
	}

	public static List<Integer> inorder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			} else {
				TreeNode p = cur.left;
				while (p.right != null && p.right != cur) {
					p = p.right;
				}
				if (p.right == null) {
					p.right = cur;
					cur = cur.left;
				} else {
					p.right = null;
					res.add(cur.val);
					cur = cur.right;
				}
			}
		}
		return res;
	}
	
	public static List<Integer> preorder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			}
			else {
				TreeNode p = cur.left;
				while (p.right != null && p.right != cur) {
					p = p.right;
				}
				if (p.right == null) {
					p.right = cur;
					res.add(cur.val);
					cur = cur.left;
				}
				else {
					p.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}
	
	public static List<Integer> postorder(TreeNode root) {
		Deque<Integer> res = new ArrayDeque<>();
		if (root == null) return new ArrayList<>(res);
		TreeNode cur = root;
		while (cur != null) {
			if (cur.right != null) {
				TreeNode p = cur.right;
				while (p.left != null && p.left != cur) {
					p = p.left;
				}
				if (p.left == null) {
					p.left = cur;
					res.addFirst(cur.val);
					cur = cur.right;
				}
				else {
					p.left = null;
					cur = cur.left;
				}
			}
			else {
				res.addFirst(cur.val);
				cur = cur.left;
			}
		}
        
		return new ArrayList<>(res);
	}

}