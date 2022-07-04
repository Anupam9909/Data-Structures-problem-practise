// LC-1091. Shortest Path in Binary Matrix

   //use bfs to solve -> rm*wa*
   public int shortestPathBinaryMatrix(int[][] arr){
    int n = arr.length, m = arr[0].length;
    if(arr[0][0]==1 || arr[n-1][m-1]==1) return -1;
    
    int[][] dir = {{0,1},{1,0},{0,-1},{-1,0},{1,-1},{-1,1},{1,1},{-1,-1}};
    boolean[][] vis = new boolean[n][m];
    
    // bfs
    Queue<Integer> que = new LinkedList<>();
    que.add(0*m+0);
    int level = 0;
    
    while(que.size() != 0){
        int s = que.size();
        while(s-- > 0){
            // r
            int rem = que.remove();
            int r = rem/m, c = rem%m;
            
            // m*
            if(vis[r][c]){
               continue; 
            }
            vis[r][c] = true;
            
            // work
            if(r==n-1 && c == m-1) return level+1;
            
            // a*
            for(int d = 0; d < 8; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if(x >= 0 && x < n && y >= 0 && y < m && !vis[x][y] && arr[x][y]==0){
                    que.add(x*m+y);
                }
            }
        }
        level++;
    }
    
    return -1;
}





// bfs :  rwa*->m
//     public int shortestPathBinaryMatrix(int[][] grid){
//         if(grid.length == 0 || grid[0].length == 0) return -1;
    
//         int n = grid.length;
//         int m = grid[0].length;
    
//         if(grid[0][0] == 1 || grid[n-1][m-1] == 1)  return -1;
    
//         LinkedList<Integer> que = new LinkedList<>();
//         grid[0][0] = 1;
//         que.addLast(0*m+0);
    
    
    
//         int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{-1,1},{1,-1},{-1,-1},{1,1}};
//         int level = 1;
    
//         while(que.size() != 0){
//             int size = que.size();
//             for(int i = 0; i < size; i++){
//                 int rem = que.removeFirst();
//                 int r = rem/m, c = rem%m;
            
//                 if(r == n-1 && c == m-1){
//                     return level;    
//                 }
            
//                 for(int d = 0;  d < dir.length; d++){
//                     int x = r + dir[d][0];
//                     int y = c + dir[d][1];
                
//                     if(x<n && x>=0 && y<m && y>=0 && grid[x][y] == 0){
//                         grid[x][y] = 1;
//                         que.addLast(x*m+y);
//                     }
//                 }
//             }
//             level++;
//         }
//         return -1;
//     }


//======================================================================================================


// LC- 785. Is Graph Bipartite?

     //-----------------------------------------------------------------
    
   // solution to this problem
    // 0 = unvisited node
    // 1 = visited and red colour node
    // 2 = visited and green colour node
    
    public boolean isBipartite(int[][] graph) {
        if(graph.length == 0) return false;
        int n = graph.length;
        int[] visited = new int[n];
        boolean ans = false;
        
        for(int i = 0 ; i < n; i++ ){
            if(visited[i] == 0){
                ans = isbipartite(graph, i, visited);
                if(ans == false) return false;
            }
        }
        return true;
    }
    
    public boolean isbipartite(int[][] graph, int src, int[] visited){
        LinkedList<Integer> que = new LinkedList<>();
        visited[src] = 2;
        que.addLast(src);
        int level = 1;
        
        while(que.size() != 0){
            int size = que.size();
            while(size-->0){
                int rem = que.removeFirst();  // rem(removed vtx) => u 
                
                for(int v : graph[rem]){
                    if(visited[v] == 0){
                        visited[v] = (level%2 == 0)? 2 : 1; // mark visited
                        que.addLast(v); // add in queue
                    }else if(visited[v] == visited[rem]){
                        return false;
                    }
                }
            }
            level++;
        }
        return true;
    }
        
        
    //------------------------------------------------------------------------------    
        // BFS without cycle(  rwa*->m  )  1st way
    //     public boolean isbipartite(int[][] graph, int src, int[] visited){
            
    //         LinkedList<Integer> que = new LinkedList<>();
    //         visited[src] = 2;
    //         que.addLast(src);
    //         int level = 1;
            
    //         while(que.size() != 0){
    //             int size = que.size();
    //             while(size-->0){
    //                 int rem = que.removeFirst();  // rem(removed vtx) => u 
                    
    //                 for(int v : graph[rem]){
    //                     if(visited[v] == 0){
    //                         visited[v] = (level%2 == 0)? 2 : 1; // mark visited
    //                         que.addLast(v); // add in queue
    //                     }else if(visited[v] == visited[rem]){
    //                         return false;
    //                     }
    //                 }
    //             }
    //             level++;
    //         }
    //         return true;
    //     }
            
        
    //------------------------------------------------------------------------------

    //     // BFS without cycle(  rwa*->m  )  2st way
    //     public boolean isbipartite(int[][] graph, int src, int[] visited){
            
    //         LinkedList<Integer> que = new LinkedList<>();
    //         visited[src] = 2;
    //         que.addLast(src);
    //         int level = 0;
            
    //         while(que.size() != 0){
    //             int size = que.size();
    //             while(size-->0){
    //                 int rem = que.removeFirst();  // rem(removed vtx) => u 
                    
    //                 //work
    //                 if(visited[rem] != 0){
    //                     int color = level%2 ;
    //                     if(visited[rem] == )
    //                 }
                    
    //                 for(int v : graph[rem]){
    //                     if(visited[v] != 0){
    //                         visited[v] = ((level+1)%2==0)? 2:1;//visited
    //                         que.addLast(v);
    //                     }
    //                 }
    //             }
    //             level++;
    //         }
    //         return true;
    //     }
     


//======================================================================================================


// LC- 296 solution (best meeting place)

    public int dist(int x1, int y1, int x2, int y2){
        int a = x1-x2;
        if(a < 0) a = -a;
        
        int b = y1-y2;
        if(b < 0) b = -b;
        
        return a+b;
    }
    
    public void BFS(int[][] arr, int i, int j, int[][] ans){
        int n = arr.length;
        int m = arr[0].length;
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        boolean[][] visited = new boolean[n][m];
        LinkedList<Integer> que = new LinkedList<>();
        que.add(i*m + j);
        visited[i][j] = true;
        
        while(que.size() != 0){
            int rem = que.removeFirst();
            int r = rem/m, c = rem%m;

            ans[r][c] += dist(i, j, r, c);

            for(int d = 0; d < dir.length; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x<n && x>=0 && y>m && y>=0 && !visited[x][y]){
                    visited[x][y] = true;
                    que.addLast(x*m+y);
                }
            }   
        }
        return ;
    }
    
    public boolean Mindist(int[][] arr){
        if(arr.length == 0 || arr[0].length == 0) return -1;
        int n = arr.length;
        int m = arr[0].length;
        
        int[][] ans = new int[n][m];
       
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1{
                    
                    BFS(arr, i, j, ans);
                }
            }
        }
        
        int mindist = (int)1e8;
                   
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m ; j++){
                if(mindist < ans[i][j])  mindist = ans[i][j];
            }
        }
                   
      mindist;
      return true;
                     
    }
        


//======================================================================================================



// LC- 994. Rotting Oranges

// CONCEPT : SARE ROOTTEN ORANGES KO STARTING ME HI BFS QUEUE ME DAAL DO AND 
// SIMPLY BFS LAGA DO

public int orangesRotting(int[][] arr){
    if(arr.length == 0) return -1;
    int n = arr.length;
    int m = arr[0].length;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    int freshorange = 0;
    LinkedList<Integer> que = new LinkedList<>();
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++){
            if(arr[i][j] == 2)  que.addLast(i*m+j);
            if(arr[i][j] == 1)  freshorange++;
        }
    }
    if(freshorange == 0) return 0;
    
    int level = 0; // level => act as time
    
    // BFS rwa*->m 
    while(que.size() != 0){
        int size = que.size();
        while(size-->0){
            int rem = que.removeFirst();
            int r = rem/m, c = rem%m;
            
            for(int d = 0; d < 4 ; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x<n && y<m && x>=0 && y>=0 && arr[x][y] == 1){
                   arr[x][y] = 2;
                   freshorange--;
                   que.addLast(x*m + y); 
                   if(freshorange == 0) return level+1;
                }
            }   
        }
        level++;
    }
  
    return (freshorange==0) ? (level-1) : -1; 
}




//======================================================================================================



// LC-286 Walls and Gates

CONCEPT -> SARE GATE KO STARTING POINT MAAN KE BFS QUEUE ME DAAL DO  
AS HAAM EK GATE KO LE KE JA RAHE HA ROOM KE PASS -> YAHI CATCH


    // walls and gates

    // solution 1 (using visited 2d array)
    public void wallsAndGates(int[][] arr) {
        // write your code here
        int infi = 2147483647;
        int n = arr.length;
        int m = arr[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0},{-1, 0}};

        LinkedList<Integer> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for(int i  = 0; i < n; i++){
            for(int j = 0;  j < m; j++){
                if(arr[i][j] == 0) que.addLast(i*m + j);
                if(arr[i][j] == -1) visited[i][j] = true;
            }
        }
        
        int level = 0;

        // BFS 
        while(que.size() != 0){
            int size = que.size();
            while(size-->0){
                int rem = que.removeFirst();
                int r = rem/m, c = rem%m;

                if(arr[r][c] == infi){
                    arr[r][c] = level;
                }

                for(int d= 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x<n && y<m && x>=0 && y>=0 && arr[x][y] == infi && !visited[x][y]){
                        visited[x][y] = true;
                        que.addLast(x*m + y);
                    }
                }
            }
            level++;
        }

         // System.out.println(arr);
        // for(int i = 0 ; i< n ; i++){
        //     for(int j = 0; j < m ; j++){
        //         System.out.print(arr[i][j]);
        //     }
        //     System.out.println();
        // }
    }

    // solution 2 (without visited)
    // public void wallsAndGates(int[][] arr) {
    //     // write your code here
    //     int infi = 2147483647;
    //     int n = arr.length;
    //     int m = arr[0].length;
    //     int[][] dir = {{0, 1}, {0, -1}, {1, 0},{-1, 0}};

    //     LinkedList<Integer> que = new LinkedList<>();

    //     for(int i  = 0; i < n; i++){
    //         for(int j = 0;  j < m; j++){
    //             if(arr[i][j] == 0) que.addLast(i*m + j);
    //         }
    //     }

    //     int level = 0;

    //     // BFS 
    //     while(que.size() != 0){
    //         int size = que.size();
    //         while(size-->0){
    //             int rem = que.removeFirst();
    //             int r = rem/m, c = rem%m;

    //             for(int d= 0; d < 4; d++){
    //                 int x = r + dir[d][0];
    //                 int y = c + dir[d][1];

    //                 if(x<n && y<m && x>=0 && y>=0 && arr[x][y] == infi){
    //                     arr[x][y] = level+1;
    //                     que.addLast(x*m + y);
    //                 }
    //             }
    //         }
    //         level++;
    //     }

    //      // System.out.println(arr);
    //     // for(int i = 0 ; i< n ; i++){
    //     //     for(int j = 0; j < m ; j++){
    //     //         System.out.print(arr[i][j]);
    //     //     }
    //     //     System.out.println();
    //     // }
    // }


//======================================================================================================



// LC-815. Bus Routes
// bfs se solve kar lo
// hashmap le lo bhot sare to save the data

public int numBusesToDestination(int[][] routes, int source, int target) {
     
    int n = routes.length;
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    
    for (int i = 0; i<n; i++){
        for (int j = 0; j<routes[i].length; j++){
            int bustopno = routes[i][j];
            ArrayList<Integer> busno = map.getOrDefault(bustopno, new ArrayList<>());
            busno.add(i);
            map.put(bustopno, busno);
        }
    }
    
    LinkedList<Integer> queue = new LinkedList<>();
    HashSet<Integer> busstopvis = new HashSet<>();
    HashSet<Integer> busvis = new HashSet<>();
    int level = 0;
    
    queue.addLast(source);
    busstopvis.add(source);
    
    while(queue.size() > 0){
        int size = queue.size();
        while(size-- > 0){
            int rem = queue.removeFirst();
            if(rem == target){
                return level;
            }
            ArrayList<Integer> buses = map.get(rem);
            for (int bus: buses){
                if(busvis.contains(bus) == true){
                    continue;
                }
                int[]arr = routes[bus];
                for(int busstoop: arr){
                    if(busstopvis.contains(busstoop) == true){
                        continue;
                    }
                    queue.addLast(busstoop);
                    busstopvis.add(busstoop);
                }
                busvis.add(bus);
            }
        }
        level++;
    }
    
    return -1;
    
}




//======================================================================================================



// LC-




//======================================================================================================



