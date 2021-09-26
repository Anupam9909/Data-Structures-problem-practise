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
}