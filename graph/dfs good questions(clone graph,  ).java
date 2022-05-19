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