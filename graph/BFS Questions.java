import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class bfs{
    // BFS_with cycle 
    public static void bfs(ArrayList<Integer>[] graph, int src, boolean[] visited){
        LinkedList<Integer> que = new LinkedList<>();

        que.add(src);
        // rm*wa*
        int level = 0;
        boolean cycle = false;

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rem = que.removeFirst();

                if(visited[rem]){
                    cycle = true;
                    continue;
                }
                visited[rem] = true;
                // yaha jo (rem) nodes ha vo (level) vale ha
                dis[rem] = level;

                for(Integer x : graph[rem]){
                    if(!visited[x]){
                        // yaha jo (x) nodes ha vo (level+1) vale ha
                        que.addLast(x);
                    }
                }
            }
            level++;
        }
    }


    // BFS_without cycle
    public static void bfs(ArrayList<Integer>[] graph, int src, boolean[] visited){
        LinkedList<Integer> que = new LinkedList<>();

        que.addLast(src);
        visited[src] = true;
        //rwa*->m

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rem = que.removeFirst();

                // yaha pe (rem) nodes hoge vo (level) vale hoge
                dis[rem] = level;

                for(Integer x : graph[rem]){
                    if(!visited[rem]){
                        visited[rem] = true;
                        // yaha pe jo (x) nodes hoge vo (level+1) pe hoge
                        que.addLast(rem);
                    }
                } 
            }
            level++;
        }
    }



    public static void bfs(int[][] graph){
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] visited  = new boolean[graph.length];
        int[] dis = new int[graph.length];
        int level= 0;
        que.add(0);
// rwa*->m
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rem = que.removeFirst();
                dis[rem] = level;

                for(Integer x : graph[rem]){
                    if(!visited[x]){
                        visited[x] = true;
                        dis[x] = level+1;
                        if(dis[x] == dis[rem]){
                            return false;
                        }
                    }
                }
            }
            level++;
        }
        return true;
    }



























    // 785. Is Graph Bipartite?

    // I-way(using level as the filling colors)

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
            if(visited[i] == false){
                ans = isbipartite(graph, i, visited);
                if(ans == false) return false;
            }
        }
        return true;
    }

    // BFS without cycle(  rwa*->m  )  1st way
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
    
    // issi function ko haam ese bhi BFS with cycle se bhi likh sakte the
    // BFS withcycle (rm*wa*)
    public boolean isbipartite(int[][] graph, int src, int[] visited){
        
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);
        int level = 0;
        
        while(que.size() != 0){
            int size = que.size();
            while(size-->0){
                int rem = que.removeFirst();
                
                if(visited[rem] != 0){
                    continue;
                }
                visited[rem] = (level%2==0)? 1 : 2; 
                
                for(int v : graph[rem]){
                    if(visited[v] == 0){
                        que.addLast(v);
                    }
                }
                
            }
            level++;
        }
    }
    
    // II-way approach to this problem
    // (using distance array as the method to find ans)
    // if two adjacent element have same level then the graph is not bipartite

    // I-way(using level as the filling colors)
    public boolean isBipartite(int[][] graph) {
        if(graph.length == 0) return false;
        int n = graph.length;
        
        boolean ans = false;
        boolean[] visited = new boolean[n];
        int[] dis = new int[graph.length];
        for(int i = 0 ; i < n; i++ ){
            if(visited[i] == false){
                ans = bfs(graph,i,visited, dis);
                if(ans == false) return false;
            }
        }
        return true;
    }
    
    public static boolean bfs(int[][] graph,int src, boolean[] visited, int[] dis){
        LinkedList<Integer> que = new LinkedList<>();

        int level= 0;
        que.add(src);
        visited[src] = true;
        // rwa*->m
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rem = que.removeFirst();
                dis[rem] = level;

                for(Integer x : graph[rem]){
                    if(!visited[x]){
                        visited[x] = true;
                        dis[x] = level+1;
                        que.addLast(x);
                        
                    }else if(dis[x] == dis[rem]){
                        return false;
                    }
                }
            }
            level++;
        }
        return true;
    }



















    
}