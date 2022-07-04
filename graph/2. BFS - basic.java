import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class bfs{
    // BFS_with cycle   -> r m* w a* -> remove mark* work add*
    // yaha starting me vis[src] nahi hota bfs ke ander hi check hota ha
    
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

//-----------------------------------------------------------------------------------------------------

    //  BFS_without cycle -> 
                //   r w a*
                //      \|/
                //       m
    
    // yaha starting me hi vis[src] =true karna hota ha 
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


//======================================================================================================


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


    
}