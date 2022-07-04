import java.util.ArrayList;

public class graph{

    int N = 7;
    ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u , int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void constructgraph(){
        addEdge()
    }

    public static void display(ArrayList<Edge>[] graph){
        for(int i= 0 ; i < graph.length;i++){
            System.out.print(i + "->");
            for(Edge e : graph[i]){
                System.out.print("("+ e.v + "," + e.w + ")"+" ");
            }
            System.out.println();
        }
    }



    public static void removeEdge(ArrayList<Edge>[] graph,int u , int v){
        int idx1 = findindex(graph,u,v);
        graph[u].remove(idx1);

        int idx2 = findindex(graph,v,u);
        graph[v].remove(idx2);
    }

    public static void findindex(ArrayList<Edge>[] graph,int u , int v){
        int idx = -1;
        for(Edge e : graph[u]){
            idx++;
            if(e.v == v)  return idx;
        }
        return idx;
    }

    public static void removevtx(ArrayList<Edge>[] graph, int vtx){
        for(int i = 0 ; i < graph[vtx].size() ; i++){
            Edge e = graph[vtx].get(graph.size()-1-i);
            removeEdge(graph,vtx,e.v);
        }

        // for(int i = graph[vtx].size()-1 ; i >= 0 ; i--){
        //     Edge e = graph[vtx].get(i);
        //     removeEdge(vtx,e.v);
        // }
    }

     
    // fully wrong 
    // public static void removevtx(ArrayList<Edge>[] graph, int vtx){
    //     for(Edge e : graph[vtx]){
    //         removeEdge(vtx,e.v);
    //     }
    // }
    //-----------------------



    //dfs
    public static boolean dfs(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited){
        if(src == dest)  return true;

        visited[src] = true;
        boolean result = false;

        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                result = dfs(graph, e.v, dest, visited);
                if(result == true)  return true;
            }
        }
    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] visited = new boolean[graph.length];
        System.out.println(printAllPath(graph,0,dest,visited,""));
    }
    

    public static int printAllPath_(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited,String psf){
        if(src == dest){
            System.out.println(psf);
            return 1;
        }
        visited[src] = true;
        int count = 0;
        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                count += printAllPath_(graph, e.v, dest, visited,psf + e.v);
            }
        }
        visited[src] = false;
        return count;
    }

    public class pair{
        int wt ;
        String psf;
        pair(int w , int p){
            this.wt = w;
            this.psf = p;
        }
    }

    public static void maxpath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited){
        if(src == dest){
            pair baseans = new pair(0,src+"");
            return baseans;
        }
        visited[src] = true;
        pair mymax = new pair(-(int)1e8,"");

        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                pair recans = maxpath(graph, e.v, dest, visited );
                if(mymax.wt < recans.wt + e.wt ){
                    mymax.wt = recans.wt + e.wt;
                    mymax.psf = src + recans.psf;
                }
            }
        }
        return myans;
    }


    public static void maxpath(ArrayList<Edge>[] graph, int src, int dest){
        boolean[] visited = new boolean[graph.length];
        pair myans = maxpath(graph,src,dest,visited);
        System.out.println("longest path by weigth : " + myans.path + "@" + myans.wt);
    }

    // solving by parameter method
    public static void solve(ArrrayList<Edge>[] graph, int src, int dest,boolean[] visited, int csf, int psf, int[] cost, String[] path){
        if(src == dest){
            setvalues(csf,psf,cost,path);
            return ;
        }

        visited[src] = true;

        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                int ncsf = csf + e.wt;
                String npsf = psf + e.v;
                solve(graph, e.v, dest,visited ,ncsf, npsf, cost , path);
            }
        }

        visited[src] = false;
    }

    public static void setvalues(int csf, String psf, int[] cost , String[] path){
        if(cost[0] < csf){
            cost[0] = csf;
            path[0] = psf;
        }
        if(cost[1] > csf){
            cost[1] = csf;
            path[1] = psf;
        }
    }

    // solving by return type method
    public class pair{
        int wt ;  // wt : weigth
        String path;
        pair(int w , int p){
            this.wt = w;
            this.path = p;
        }
    }

    public static void solve(ArrayList<Edge>[] graph, int src,int dest,boolean[] visited){
        if(src == dest){
            pair baseans = new Pair(0,src+"");
            return baseans;
        }

        visited[src] = true;
        pair myans = new pair(0,"");

        for(Edge e : graph[src]){
            if(visited[e.v] == false){ 
                pair recans = solve(graph, e.v, dest){ 
                if(myans.wt < recans.wt + e.wt){
                    myans.wt = (recans.wt + e.wt);
                    myans.path = (src + recans.path);
                }

            }
        }

        visited[src] = false;
    }
    // asrc : actuall source
    // src : source
    public static void hamiltonian_pathcycle(ArrayList<Edge>[] graph,int asrc, int src, int dest, boolean[] visited,int count,String psf){
        if(src == dest){
            if(count == N-1) System.out.println("Hamiltonian Path " + psf);
            if(cycle(graph,asrc,src)) System.out.println("Hamiltonian Cycle " + psf); 
            return ;
        }

        visited[src] = true;
        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                String npsf = psf + e.v;  // npsf : new path so far
                hamiltonian_pathcycle(graph, asrc, e.v,dest, visited, count + 1 , npsf );
            }
        }
        visited[src] = false;
    }

    public static boolean cycle(ArrayList<Edge>[] graph,int asrc, int src ){
        for(Edge e : graph[src]){
            if(e.v == asrc)  return true;
        }
        return false;
    }

    // GCC -> GET CONNECTED COMPONENTS:

    //QUESTION => return true/flase if graph is a fully connected graph or not?

    // import java.util.*;
    // import java.lang.*;
    // import java.io.*;

    // class GFG {

    //     public static void addEdge(ArrayList<Integer>[] graph,int u , int v){
    //         graph[u].add(v);
    //         graph[v].add(u);
    //     }
        
    //     public static ArrayList<Integer>[] buildgraph(int N,int M){
    //         ArrayList<Integer>[] graph = new ArrayList[N+1];
    //         for(int i = 1 ; i <= N ; i++){
    //             graph[i] = new ArrayList<>();
    //         }
    //         return graph;
    //     }
        
    //     public static void main (String[] args) {
    //         Scanner scn = new Scanner(System.in);
            
    //         int T = scn.nextInt();
            
    //         for(int i = 1 ; i <= T ; i++){
    //             int N = scn.nextInt();
    //             int M = scn.nextInt();
                
    //             int src = 0;
    //             ArrayList<Integer>[] graph = buildgraph(N,M);
    //             for(int j = 1 ; j <= M ; j++){
    //                  int u = scn.nextInt();
    //                  int v = scn.nextInt();
    //                  src = u;
    //                  addEdge(graph,u,v);
    //             }
                
    //             if(isGraphConnected(graph,src)) System.out.println(1);
    //             else System.out.println(0);
    //         }
    //     }
        
    //     public static boolean isGraphConnected(ArrayList<Integer>[] graph, int src){
    //         boolean[] visited = new boolean[graph.length];
    //         dfs(graph,src,visited);

    //         for(int i= 1 ; i < graph.length; i++){
    //             if(visited[i] == false)  return false;
    //         }
    //         return true;
    //     }
        
    //     public static void dfs(ArrayList<Integer>[] graph, int src, boolean[] visited){
    //         visited[src] = true;
    //         for(Integer x : graph[src]){
    //             if(visited[x] == false){
    //                 dfs(graph,x,visited);
    //             }
    //         }
    //     }
        
    // }


    // GFG =  haminltonian path & cycle
    public static int hamiltonianPathCycle(ArrayList<Edge>[] graph, int src){
        boolean[] visited = new boolean[graph.length];
        int SRC = src;
        hamiltonianPathCycle( graph, SRC,  src, visited, 1 , src+"");
    }


    public static int hamiltonianPathCycle(ArrayList<Edge>[] graph,int SRC , int src, boolean[] visited, int count, String psf){
        if(count == graph.length-1){
            System.out.println("path : " + psf);
            if(iscycle(graph,SRC,src))  
                System.out.println("cycle : " + psf);
            return 1;

        }

        visited[src] = true;
        int totalans = 0;
        for(Edge e : graph[src]){
            if(visited[e.v] == false){
                totalans += hamiltonianPathCycle(graph, e.v, visited,count,psf + e.v);
            }
        }
        visited[src] = false;
        return totalans;
        
    }

    public static boolean cycle(ArrayList<Edge>[] graph,int SRC , int src){
        for(Edge e : graph[SRC]){
            if(e.v == src)  return true;
        }
        return false;
    }

/==============================================================================================

        // GET NUMBER OF CONNECTED COMPONENTS IN A GRAPH  and print also
        // I way 
        public static int gcc(ArrayList<Edge>[] graph){
            int count = 0;  // count for the number of components 
            boolean[] visited = new boolean[graph.length];
            for(int i = 0 ; i < graph.length; i++){
                if(visited[i] == false){
                    dfs(graph,src,visited);
                    System.out.println();
                    count++;
                }
            }

            // jo single node bach gaye vo print kar do
            for(int j = 0 ; j < visited.length; j++){
                if(visited[j] == false)
                    System.out.println(j);
            }
        }

        public static void dfs(ArrayList<Edge>[] graph, int src , boolean[] visited){
            visited[src] = true;
            System.out.print(src + " ");
            for(Edge e : graph[src]){
                if(visited[e.v] == false){
                    dfs(graph,e.v,visited);
                }
            }
        }

//==============================================================================================

        // II way - not much difference just AL me ans mangaya ha 
        public static int gcc(ArrayList<Edge>[] graph){
            int count = 0;
            boolean[] visited = new boolean[graph.length];
            for(int i = 0 ; i < graph.length; i++){
              ArrayList<Character> ans = new ArrayList<>();
              dfs(graph,src,visited,ans);
              System.out.println(ans);
              count++;
            }
            System.out.println(count);
        }

        
        public static void dfs(ArrayList<Edge>[] graph, int src , boolean[] visited. ArrayList<Character> ans){
            visited[src] = true;
            for(Edge e : graph[src]){
                if(visited[e.v] == false){
                    dfs(graph,e.v,visited,ans+e.v);
                }
            }
        }




	}
}