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


    // MORRIS TRAVERSAL
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

}