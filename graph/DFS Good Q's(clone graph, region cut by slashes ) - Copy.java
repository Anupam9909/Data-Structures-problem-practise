// LC - 133. Clone Graph

    // ye vo clone linkedlist vale ki tarah hi question ha (vaha ek hashmap vala solution hota tha i.e-> ek baar graph ko traverse karke naye node bana do and store the address of <original node, clone node> in hashmap)
    // then dubara traverse karo graph ko and then make link in the clone graph
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        HashMap<Node, Node> hm = new HashMap<>();
        boolean[] vis = new boolean[10000];
        
        dfs(node, hm, vis);
        
        vis = new boolean[10000];
        dfs_clone(node, hm, vis);  // making link in the clone graph
        
        Node ans = hm.get(node);
        return ans;
    }
    
    public void dfs(Node root, HashMap<Node, Node> hm, boolean[] vis){
        vis[root.val] = true;   
        
        // work
        Node nn = new Node(root.val);
        nn.neighbors = new ArrayList<>();
        hm.put(root, nn);
        
        for(Node v : root.neighbors){
            if(!vis[v.val]){
                dfs(v, hm, vis);   
            }
        }
    }
    
    
    public void dfs_clone(Node root, HashMap<Node, Node> hm, boolean[] vis){
        vis[root.val] = true;
        List<Node> x = hm.get(root).neighbors;
        
        for(Node v : root.neighbors){
            hm.get(v).neighbors.add(hm.get(root)); // ye line imp. ha ki c* condition ke upar ye line likhege to link the nodes
            // agar ander vali condition me likhege to duplicate ayege ans me
            if(!vis[v.val]){
                dfs_clone(v, hm, vis);
            }
        }
    }


//==================================================================================================================


    //LC - 959. Regions Cut By Slashes

    //NOTE : "/\\" iska length 2 ha na ki 3
// '/' ye ek character ha and 
// '\\' ye pura ek character ha

class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] arr = new int[3*n][3*n];
        
        for(int i = 0; i < n; i++){
            String s = grid[i];
            
            for(int j = 0 ; j < s.length(); j++){
                char ch = s.charAt(j);
                
                if(ch == '/'){
                    arr[3*i + 2][j*3 + 0] = 1;
                    arr[3*i + 1][j*3 + 1] = 1;
                    arr[3*i + 0][j*3 + 2] = 1;
                }else if(ch == '\\'){
                    arr[3*i+0][j*3+0] = 1;
                    arr[3*i+1][j*3+1] = 1;
                    arr[3*i+2][j*3+2] = 1;
                }else{
                    // no box is blocked
                }
            }
        }
        
        
        // now finding ans
        // find the number of island present in the arr matrix
        int ans = numberOfIsland(arr);
        return ans;
    }
    
    public int numberOfIsland(int[][] arr){
        int count = 0, n = arr.length;
        int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] == 0){
                    count++;
                    dfs(arr, i, j, dir);
                }
            }
        }
        
        return count;
    }
    
    public void dfs(int[][] arr, int i, int j, int[][] dir){
        arr[i][j] = 1;
        int n = arr.length;
        
        for(int d = 0; d < 4; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if(x >= 0 && x < n && y >= 0 && y < n && arr[x][y] == 0){
                dfs(arr, x, y, dir);
            }
        }
        
    }
    

    //=====================================================================================================================================

}