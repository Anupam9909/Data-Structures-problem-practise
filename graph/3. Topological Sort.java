// FOR DIRECTED GRAPH ONLY:
// find topological order -> sirf directed graph me hi nikal sakte ha
    // METHOD - 1 
    // USING DFS - mc*s  -> mark, call for dfs , fill in stack
    // disadvantage -> it cannot detect cycle in a graph

    public topologicalSort(List<Integer>[] graph){
        int n = graph.length;
        boolen[] vis = new boolean[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                dfs_topo(graph, i, st);
            }
        }

        System.out.println("TOPOLOGICAL ORDER -> " + st);
    }

    public dfs_topo(List<Integer>[] graph, int src, Stack<Integer> st){
        vis[src] = true;  // m (mark vis)
        for(int v : graph[src]){  // c*  (call for child)
            if(!vis[v]){
                dfs_topo(graph, v) ;
            }
        }

        st.push(src); // s (push in stack)
    }



//===========================================================================================


     // METHOD - 2    
    // KHAN'S ALGORITHM    ip + carefull bfs
    //           a*
    //          /|\
    //   i p r w m
    // i - calulate indgree , p - put vtx in que whose indegree=0 , r - remove , w - cut vtx , m - add in ans AL, -> means remove indegree of nbr's by -1 , a* - add only when indegree = 0
	
    // yaha haam cycle bhi detect kar sakte ha 
    // after applying khans algo just check if(ans.size() != n) then graph has cycle
    public static void khanalgo(ArrayList<ArrayList<Integer>> adj){
		int vtx = adj.length;

		// build graph
		ArrayList<Integer>[] graph = new ArrayList[adj.length];
		for(int i = 0; i < graph.length; i++)  graph[i] = new ArrayList<>();
		for(ArrayList<Integer> x : adj){
			int u = x.get(0);
			int v = x.get(1);
			graph[u].add(v);
		}

		// khan's algo (iprwm->a*)
		int[] indegree = new int[vtx];
		
		// i
		for(int i = 0; i < vtx ; i++)
			for(Integer v : graph[i])
				indegree[v]++;

		
		 LinkedList<Integer> que = new LinkedList<>();		
		// p
		for(int i = 0; i < vtx; i++){
			if(indegree[i] == 0)  que.addLast(i);
		}

		// carefull BFS
		ArrayList<Integer> ans = new ArrayList<>();

		while(que.size() != 0){
			int rem = que.removeFirst(); // r
			ans.add(rem);  // w

			for(Integer v : graph[rem]){ // m->a*
				indegree[v]--;
				if(indegree[v] == 0){
					que.add(v);
				}
			}
		}

        // cycle detection
        if(ans.size() != n){
            System.out.println("cycle is present in graph -> hence T.S cannot be made");
        }else{
            System.out.println("no cycle -> hence T.S order as follows :-");
            System.out.println(ans);
        }
		return ans;
	}
    

    //=================================================================================================

    // METHOD - 3
    // USING DFS - UPDATED VERSION (WHICH CAN FIND CYCLE IN A DIRECTED GRAPH)
    // trick to learn
    //        0 (initial)
    //    m1 c* s b2

    // initailly put 0 in all node
    // m1 - mark visited with 1,  c* - call for dfs , s - push in stack , b2 - backtrack karte vakt mark 2 
    
        
        // 0 -> unvisited
        // 1 -> currently visiting 
        // 2 -> visited
    //Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // code here
        int[] visited = new int[V];
        
        for(int i = 0; i < V; i++){
            if(visited[i] == 0){
                if(DFS(adj, i, visited)) return true;
            }
        }
        return false;
    }
    
    
    // 0 -> unvisited
    // 1 -> currently visiting 
    // 2 -> visited
    public boolean DFS(ArrayList<ArrayList<Integer>> graph, int src, int[] visited){
        visited[src] = 1;
        boolean res = false;
        // call for dfs
        for(Integer v : graph.get(src)){
            if(visited[v] == 1) return true;
            if(visited[v] == 0){
                res = DFS(graph, v, visited);
                if(res) return true;
            }
        }
        
        visited[src] = 2;
        return res;
    }


    //=====================================================================================================

    // QUESTIONS :

// LC- 207. Course Schedule

   
    // BFS (Khan's Algo)
    public boolean canFinish(int numCourses, int[][] prerequisites){
        int vtx =numCourses; 
        // build graph
        ArrayList<Integer>[] graph = new ArrayList[vtx];
        for(int i = 0; i < vtx; i++) graph[i] = new ArrayList<>();
        for(int[] x : prerequisites){
            int u = x[0];
            int v = x[1];
            graph[u].add(v); // directed graph
        }
        
        // khan's algo
        int[] indegree = new int[vtx];
        LinkedList<Integer> que = new LinkedList<>();
        
        for(int i = 0; i < vtx; i++)
            for(Integer v : graph[i])
                indegree[v]++;
         
        for(int i = 0; i < vtx; i++) if(indegree[i] == 0 ) que.add(i);
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(que.size() != 0){
            int rem = que.removeFirst();
            
            ans.add(rem);
           
            for(Integer v : graph[rem]){
                indegree[v]--;
                if(indegree[v] == 0) que.add(v);
            }
        }
        
        return (ans.size() != vtx)? false: true;
    }
        
  //--------------------------------------------

// or DFS Solution
//     // 0 : unvisited node
//     // 1 : currently visiting node
//     // 2 : visited node
    
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         // build graph
//         ArrayList<Integer>[] graph = new ArrayList[numCourses];
//         for(int i = 0; i < numCourses; i++) graph[i] = new ArrayList<Integer>();
//         for(int[] arr : prerequisites)  graph[arr[0]].add(arr[1]);
    
//         // cycle detection DFS
//         int[] visited = new int[numCourses]; // default 0 mark(unvisited)
    
//         for(int i = 0; i < numCourses; i++)
//             if(visited[i] == 0)
//                 if(DFS(graph, i, visited))  return false;
        
        
//         return true;
//     }
    
//     public boolean DFS( ArrayList<Integer>[] graph, int src, int[] visited){
//         visited[src] = 1;
//         for(Integer v : graph[src]){
//             if(visited[v] == 1) return true;
//             if(visited[v] == 0){
//                 if(DFS(graph, v, visited))  return true;
//             }
//         }
//         visited[src] = 2;
//         return false;
//     }




//======================================================================================================

 
 // LC - 210. Course Schedule II

     // BFS Solution (Khan's Algo)   
     public int[] findOrder(int numCourses, int[][] prerequisites){
        int vtx = numCourses;
        // build graph
        ArrayList<Integer>[] graph = new ArrayList[vtx];
        for(int i = 0; i  < vtx; i++)  graph[i] = new ArrayList<>();
        for(int[] x : prerequisites){
            int u = x[0];
            int v = x[1];
            graph[u].add(v);  // directed graph
        }
        
        // BFS khan's Algo
        // iprwm->a*
        int[] indegree = new int[vtx];
        for(ArrayList<Integer> x : graph) {
            for(Integer v : x ) indegree[v]++;
        }   
        
        LinkedList<Integer> que = new LinkedList<>();
        for(int i = 0; i < vtx; i++){
            if(indegree[i] == 0) que.addLast(i);
        }
        
        int[] ans = new int[vtx];
        int idx = vtx-1;
        while(que.size() != 0){
            int rem = que.removeFirst();
            
            ans[idx--] = rem;
            
            for(Integer v : graph[rem]){
                indegree[v]--;
                if(indegree[v] == 0)
                    que.add(v);
            }
        }
        
        return (idx == -1)? ans : new int[0];
    }
    
      
//     DFS Solution
//     // 0 : unvisited node
//     // 1 : currently visiting node 
//     // 2 : visited node
//     public int[] findOrder(int numCourses, int[][] prerequisites) {
//         // build graph
//         ArrayList<Integer>[] graph = new ArrayList[numCourses];
//         for(int i = 0; i < numCourses; i++)  graph[i] = new ArrayList<>();
//         for(int[] arr : prerequisites){
//             int u = arr[0];
//             int v = arr[1];
//             graph[u].add(v);
//         }
        
//         // topological sort
//         // int[] ans = new int[numCourses];
//         ArrayList<Integer> arr = new ArrayList<>();
//         int[] visited = new int[numCourses];
//         for(int i = 0; i < numCourses; i++){
//             if(visited[i] == 0){
//                 if(DFS(graph, i, visited, arr)) return new int[0];
//             }
//         }
//         int[] ans = new int[numCourses];
//         for(int i = 0; i < numCourses; i++) ans[i] = arr.get(i);
//         return ans;
//     }
    
//     public boolean DFS(ArrayList<Integer>[] graph, int src, int[] visited, ArrayList<Integer> ans){
//         visited[src] = 1;
        
//         for(Integer v : graph[src]){
//             if(visited[v] == 1) return true;
//             if(visited[v] == 0){
//                 // call for dfs
//                 if(DFS(graph, v, visited, ans)) return true;
//             }
//         }
//         ans.add(src);
//         visited[src] = 2;
//         return false;
//     }


//==================================================================================================================


 