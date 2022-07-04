public class Articulation_Point_and_Bridges{

// 1. ARTICULATION POINT

    static boolean[] vis, ap; // vis
    static int[] disc, pow;   // disc : dicovered element number -> koi bhi element dfs me sabse pehli baar kab mila ie kissi time pe vohi uska dicovery element hoga
                              // pow : powerfullness of the node(kissi node ki kitni pohoch ha (ie. kon se sabse powerfull admi ko janta ha)
                              // note  : haam yaha ye 
    public int Articulation_Point(ArrayList<Integer>[] graph){ 
        vis = new boolean[N+1];
        ap = new boolean[N+1];
        disc = new int[N+1];
        pow = new int[N+1];
        time = 1;
        rootcall = 0;

        for(int i = 1; i <= N; i++){
            if(!vis[i]){
                DFS(graph, i, -1);
                if(rootcall == 1) ap[i] = false; // agar 1 call lag rahi ha root se that means root ek Articulation [Point] nahi ha ie. root ko hatane se koi gcc count increase nahi hoga
                rootcall = 0;
            }
        }
        
        int count = 0;
        for(int i = 1; i <= N; i++){
            if(ap[i] == true) count++;
        }

        return count;
    }


    static int time = 0;
    static int rootcall = 0;
    public static void DFS(ArrayList<Integer>[] graph, int src, int parent){
        vis[src] = true;
        disc[src] = pow[src] = time;
        time++;
        
        for(int v : graph[src]){
            if(v == parent){  // CASE 1:
                continue; 
            }
            else if(vis[v] == true){   // CASE 2:
                if(v == parent) continue;

                pow[src] = Math.min(pow[src], disc[v]);   // NOTE : yaha disc[v] se update karna ha
            }
            else if(vis[v] == false){   // CASE 3:
                if(parent == -1) rootcall++;

                // call lagegi nbr pe
                DFS(graph, v, src);
                
                // ARTICULATION POINT CONDITION [<= equalto bhi ayega yaah]
                if(disc[src] <= pow[v]){   
                    ap[src] = true;
                }
                
                // update power of src
                pow[src] = Math.min(pow[src], pow[v]);    // NOTE : yaha pow[v] se update karna ha
                
            }

        }
    }


//--------------------------------------------------------------------------------------------------------------------------

    
// 2. ARTICULATION BRIDGE   

    // ye to aur bhi simple ha Articulation Point se
    // -> NOTE 1. [AP ki condition me bass equal to hata do] -> bass ek condition me chota sa change ha baki exact same ha
    // -> NOTE 2. yaha rootcall ki tension bhi ni leni root apne aap thik ayega

    static boolean[] vis, ap; // vis : visited , ap : articulation point
    static int[] disc, pow;   // disc : discovery node(time) , pow : powerfullness of the node(uss node ki kitni pohoch ha(kon se sabse powerfull admi ko janta ha)
    static int time = 0;

    public void Articulation_Bridge(ArrayList<Integer>[] graph){ 
        vis = new boolean[N+1];
        ap = new boolean[N+1];
        disc = new int[N+1];
        pow = new int[N+1];
        time = 0;

        for(int i = 1; i <= N; i++){
            if(!vis[i]){
                DFS(graph, i, -1);  // yaha simple ek DFS ki call lag jayegi bass
            }
        }
        
    }

    public static void DFS(ArrayList<Integer>[] graph, int src, int parent){
        vis[src] = true;
        disc[src] = pow[src] = time;
        time++;
        
        for(int v : graph[src]){
            if(v == parent){   // CASE 1:
                continue;
            }
            else if(vis[v] == true){  //CASE 2:
                // update src ki power(if can)
                pow[src] = Math.min(pow[src], disc[v]);  // NOTE : yaha disc[v] se update karna ha
            }
            else if(vis[v] == false){  // CASE 3:
                // call for nbr's
                DFS(graph, v, src, ans);
                
                if(disc[src] < pow[v]){    // ARTICULATION BRIDGE CONDITION (same ha AP ki condition se bass (=)equal to hata do)
                    System.out.println("Articulation Bridge ->  [" + src + "," + v +"]");
                }
                
                pow[src] = Math.min(pow[src], pow[v]);   // NOTE : yaha pow[v] se update karna ha
            }
        }
    }



//=================================================================================================================================

    // QUESTIONS :-

    // LC - 1192. Critical Connections in a Network

    // ARTICULATION BRIDES FIND KARNE KA HI QUESTION HA
    // articulation edges
    boolean[] vis, ap;
    int[] disc, pow;
    static int time = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> arr){
        // build graph
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(List<Integer> x : arr){
            int u = x.get(0), v = x.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // solving ARTICULATION BRIDGES
        vis = new boolean[n];
        ap = new boolean[n];
        disc = new int[n];
        pow = new int[n];
        time = 0;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                dfs(graph, i, -1, ans);   
            }
        }
        
        return ans;
    }
    
    public void dfs(List<Integer>[] graph, int src, int parent, List<List<Integer>> ans ){
        disc[src] = pow[src] = time;
        time++;
        vis[src] = true;
        
        for(int v : graph[src]){
            if(v == parent){   // CASE 1:
                continue;
            }
            else if(vis[v] == true){  //CASE 2:
                // update src ki power(if can)
                pow[src] = Math.min(pow[src], disc[v]);  // NOTE : yaha disc[v] se update karna ha
            }
            else if(vis[v] == false){  // CASE 3:
                // call for nbr's
                dfs(graph, v, src, ans);
                
                if(disc[src] < pow[v]){  // if this is true means this edge is a critical connection
                    List<Integer> bsans = new ArrayList<>();
                    bsans.add(src);
                    bsans.add(v);
                    ans.add(bsans);
                }
                
                pow[src] = Math.min(pow[src], pow[v]);   // NOTE : yaha pow[v] se update karna ha
            }
        }
    }


//=================================================================================================================================

 
// SOLUTION : ye application ha AP(ariticulation point) ka 
    // bring AP boolean array and if :-
    // CASE 1:
    // if graph is already disconnected ie (gcc > 1) -> means uss graph me 0 time lagega to disconnect that graph->so we return (ans = 0)

    // CASE 2:
    // if count of AP is 1->means uss graph me sirf ek node todne se hi vo graph disconnected ho jayega -> so we return (ans = 1)

    // CASE 3:
    // if count of AP is 0->means uss graph me pakka 2 node todne padege jisse ki vo graph disconnect ho jayega->so we return (ans = 2) because kissi bhi tarah ka graph ho usko diconnect karne ke liye haame bass ek node ko bahar nikalna ha. so, in the worst case graph could be like this:
    // 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1
    // 1 1 1 1 1 1 1 1
    // so yaha kissi bhi outer node(jiske pass only do connection ho) ko pakdo and uske dono connection kaat do so we get the disconnected graph 

    static boolean[][] vis, ap;
    static int[][] disc, power;
    static int time = 0;
    
    public int minDays(int[][] grid){
        int n = grid.length, m = grid[0].length;
        vis = new boolean[n][m];
        ap = new boolean[n][m];
        disc = new int[n][m];
        power = new int[n][m];
        time = 0;
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        
        rootcall = 0;
        int numofones = 0;
        int numofcomponent = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1) numofones++;
                if(grid[i][j] == 1 && !vis[i][j]){
                    numofcomponent++;
                    dfs(grid, i, j, -1, -1, dir);
                    if(rootcall == 1) ap[i][j] = false;
                    rootcall = 0;
                }                
            }
        }
        
        if(numofones == 1) return 1;  // ye chote edge cases ha handle karna padega
        if(numofones == 0) return 0;  // ye chote edge cases ha handle karna padega
        
        // case 1 :
        if(numofcomponent > 1) return 0;
        
        // case 2:
        int count = 0;
        for(int i = 0; i < n; i++) for(int j = 0; j < m; j++) if(ap[i][j]) count++;
        if(count >= 1) return 1;
        
        // case 3:
        return 2;
    }
    
    // sr:source row, sc:source col, pr : source row, pc : source col
    static int rootcall = 0;
    public void dfs(int[][] graph, int sr, int sc, int pr, int pc, int[][] dir){  
        int n = graph.length, m = graph[0].length;
        vis[sr][sc] = true;
        disc[sr][sc] = power[sr][sc] = time;
        time++;
        
        for(int d = 0; d < 4; d++){
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];
            
            //ye line extra ayegi , ie. graph[x][y]=1   , tabhi ander aye
            if(x >= 0 && y >= 0 && x < n && y < m && graph[x][y] == 1){ 
                if(vis[x][y] == false){
                    if(pr == -1 && pc == -1) rootcall++;
                    
                    dfs(graph, x, y, sr, sc, dir);
                    
                    if(disc[sr][sc] <= power[x][y]) ap[sr][sc] = true;
                    
                    power[sr][sc] = Math.min(power[sr][sc], power[x][y]);
                }
                else if(vis[x][y] == true){
                    if(x == pr && y == pc) continue;   // if (v == parent) then continue;
                    
                    power[sr][sc] = Math.min(power[sr][sc], disc[x][y]);
                }
            }
        }
    }
    
    
    

//========================================================================================================================================== 












   
}