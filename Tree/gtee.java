import java.util.*;

public class gtree{
    // zig zag level order traversal
    public List<List<Integer>> zigzagLevelOrder(Node root){
        List<List<Integer>> ans = new ArrayList<>();
        Stack<Node> currst = new Stack<>();
        Stack<Node> forwst = new Stack<>();
        boolean order = true;      
        // if (order = true)  -> then it means we have to add the children from LEFT TO RIGHT
        // if (order = false) -> then it means we have to add the children from RIGHT TO LEFT

        while(currst.size() != 0){
            int size = currst.size();
            ArrayList<Integer> arr = new ArrayList<>();

            while(size-- > 0){
                Node rnode = currst.pop();
                
                arr.add(rnode.data);

                if(order){
                    for(Node x : rnode.children){  // adding the children form left to right
                        forwnode.push(x);
                    }
                }else{
                    for(int i = rnode.children.size()-1;  i>= 0; i--){ // adding the children from right to left
                        forwnode.push(rnode.get(i));
                    }
                }
            }
            ans.add(arr);

            // Now, we will update the (order) & both stack
            order = !order;

            Stack<Node> temp = currst;
            currst = forwst;
            forwst = temp;
        }
    }


    // Mirror of n-ary Tree (convert a n-ary tree into it's mirror)
    // kuch ni karna yaha just children ko swap kar do 0 se n-1 ke logo ko
    // i.e   (0 with n-1) , (1 with n-2) ... so on

    public Node convertIntoMirror(Node root){
        if(root == null || (root.left == null && root.right == null)) return root;

        ConvertintoMirrorTree(root);
    }

    public void ConvertintoMirrorTree(Node root){
        if(root == null) return ;

        int n = root.children.size();
        int si = 0, ei = n-1;
        while(si < ei){
            Node temp = root.children.get(si);
            root.children.set(si, root.children.get(ei));
            root.children.set(ei, temp);
            si++; ei--;
        }

        for(Node x : root.children){
            ConvertintoMirrorTree(x);
        }

    }
    

    // check symmetry of an n-ary tree 
    // solution : bilkul binary tree jaisa ha 
    public void checksymmetry(Node root){
        if(root == null || (root.left == null  && root.right == null)) return null;

        Node leftTree = root.left;
        Node rightTree = root.right;
        boolean ans = checksymmetry(leftTree, rightTree);

        return ans;
    }


    public boolean checksymmetry(Node root1, Node root2){
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;

        boolean ans = (root1.val == root2.val);
        
        if(root1.children.size() != root2.children.size()) return false;

        int n = root1.children.size();
        for(int i = 0; i < root1.children.size(); i++){
            ans = ans && checksymmetry(root1.children.get(i), root2.children.get(n-1-i));
        }

        return ans;
    }












}