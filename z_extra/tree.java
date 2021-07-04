import java.util.io;
import java.util.Math;

public class tree{
    public class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public int size(Node root){
        return (root == null) ? 0 : ( size(root.left) + size(root.right) + 1 );
    }
    
    // IN long way
    // public int size(Node root){
    //     if(root == null){
    //         return 0;
    //     }

    //     int left_height = size(root.left);
    //     int right_height = size(root.right); 

    //     int subtree_height = left_height + right_height;
    //     int total_height = subtree_height + 1;

    //     return total_height;
    // }

    public int height(Node root){
        return (root == null) ? -1 : Math.max(height(root.left) , height(root.right)) + 1 ;
    }

    public int MaxValue(Node root){
        return (root == null) ? -(int)1e8 : Math.max(Math.max( MaxValue(root.left), MaxValue(root.right)) ,root.data)
    }

    public int MinValue(Node root){
        return (root == null) ? +(int)1e8 : Math.min(Math.min( MinValue(root.left), MinValue(root.right)) ,root.data)
    }


}