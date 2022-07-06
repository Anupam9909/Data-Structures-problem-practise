// BOTH PRIMS AND KRUSKAL ARE USED TO FIND THE [MST] minimum spanning tree IN A GRAPH 

//-----------------------------------------------------------------------------------------------------------------------

// PRIMS ALGO : 
// => USES BFS ALGORITHM (Q to PQ bana do just like dijkstra but 
//                       (csf me CostSoFar nahi ayega) vaha haar level ka alag jo bhi weight hoga vo ayega
//                        and PQ me order me jiska sabse kaam weight vo niklega pehle 
//                        ese karte karte hamara MST ban jayega
// => koi bhi vtx ko initially que me daal do answer sahi ayega


//-----------------------------------------------------------------------------------------------------------------------
// KRUSKAL ALGO : 
// => USES UNION FIND ALGORITHM (haam pehle hi graph ke given edges ko sort kar lege weight ke basis pe)
// 1. GIVEN EDGES KE LIST KO SORT KARNA HA ON THE BASIS OF (WEIGHT) 
// 1. UNION FIND LAGEGA  -> sirf parent array le lena ie. sirf path commpression karege (we will not use size array to do size commpression as code bada ho jayega and complexity me utna kuch farak ni ata)
// 2. EK EK KARKE EDGE KO UNION FIND KARTE JAO and add karte jao ie.
/*
    int p1 = findParent(u);
    int p2 = findParent(v);
    if(p1 == p2){
        // it means ye edge dalne se cycle create hogi that means ye edge add ni karege graph me
    }else{
        // ye vala edge haam apne MST me add karege so this edge will be our part of the MST graph
        par[p1] = p2;  // parent update kar dege
    }
*/

//-----------------------------------------------------------------------------------------------------------------------


//===================================================================================
//                              # PRIMS ALGORITHM #                                 ||
//===================================================================================

// SUMMARY - PRIMS ALGO:
// 1. BFS me [r m* w a*] vala lagega
// 2. PRIORITY QUEUE me weight ke basis pe sort hoga asccending order me ie.  (a,b)->{return a.wt - b.wt}
// 3. ek parent le ke chalna ha taki kissi bhi point par haam apna edge bana sakte(and ye tabhi hoga jab just piche vala vtx(ie. parent) pata ho)
// 4. dijkstra ki tarah yaha weight so far add ni hoga yaha uss particular point ka weight hi pass hota ha jo abhi present ha 
   
    public static void main(String[] args){
        //                                      
        //          40        2
        //      0--------3----------4
        //      |        |          | \
        //    10|        |10       3|  \8
        //      |        |          |   \
        //      1--------2          5-----6
        //          10                 3
        // 
        int[][] edges = {{0,1,10},{0,3,40},{1,2,10},{2,3,10},{3,4,2},{4,5,3},{4,6,8},{5,6,3}};
        int n = 7;
        primsAlgo(edges, n);

    }

    // PRIMS ALGORITHM :
    public static class pair{
        int src, parent, wt;
        pair(int r, int p, int w){
            this.src = r;
            this.parent = p;
            this.wt = w;
        }
    }

    public static void primsAlgo(int[][] edges, int n){
        // build graph 
        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int[] e : edges){
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wt - b.wt;
        });
        boolean[] vis = new boolean[n];
        
        System.out.println("MST edges are :-");
        pq.add(new pair(0, -1, 0));
        
        while(pq.size() != 0){
            int size = pq.size(); 
            while(size-- > 0){
                // r
                pair rp = pq.remove();  
                
                 // m*
                if(vis[rp.src]== true) continue; 
                vis[rp.src] = true;
                
                // work -> printing the MST edges
                if(rp.parent != -1)
                    System.out.println("[" + rp.src + "," + rp.parent + "," + rp.wt + "],");
                    
                // a*
                for(int[] e : graph[rp.src]){  
                    int vtx = e[0], wt = e[1];
                    if(!vis[vtx]){
                        pq.add(new pair(vtx, rp.src , wt));
                    }
                }
            }
        }

        return;
    }

//---------------------------------------------------------------------------------------

    // OUTPUT : MST minimum Cost => 38 
    // 
    //                         2
    //          0         3----------4
    //          |         |          | 
    //        10|         |10       3|  
    //          |         |          |   
    //          1---------2          5-------6
    //              10                   3


//---------------------------------------------------------------------------------------








//====================================================================================================================================================================================
//====================================================================================================================================================================================


// KRUSKAL ALGORITHM


//===================================================================================
//                              # KRUSKAL ALGORITHM #                               ||
//===================================================================================

// SUMMARY - KRUSKAL ALGO:   

// 1. GIVEN EDGES KE LIST KO SORT KARNA HA ON THE BASIS OF (WEIGHT) 
// 1. UNION FIND LAGEGA  -> sirf parent array le lena ie. sirf path commpression karege (we will not use size array to do size commpression as code bada ho jayega and complexity me utna kuch farak ni ata)
// 2. EK EK KARKE EDGE KO UNION FIND KARTE JAO and add karte jao ie.
/*
    int p1 = findParent(u);
    int p2 = findParent(v);
    if(p1 == p2){
        // it means ye edge dalne se cycle create hogi that means ye edge add ni karege graph me
    }else{
        // ye vala edge haam apne MST me add karege so this edge will be our part of the MST graph
        par[p1] = p2;  // parent update kar dege
    }

 */
    public static void main(String[] args) {
        //          40        2
        //      0--------3----------4
        //      |        |          | \
        //    10|        |10       3|  \8
        //      |        |          |   \
        //      1--------2          5-----6
        //          10                 3
        int[][] edges = {{0,1,10},{0,3,40},{1,2,10},{2,3,10},{3,4,2},{4,5,3},{4,6,8},{5,6,3}};
        int n = 7;
        kruskalAlgo(edges, n);

    }

    // KRUSKAL ALGORITHM :
    static int[] par;

    public static int findParent(int u){
        if(par[u] == u) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }

    public static void kruskalAlgo(int[][] edges, int n){
        Arrays.sort(edges, (a,b)->{  // {src, v, wt}
            return a[2]-b[2];  // wt ke basis pe sort kar diya asscending order me
        });
        
        par = new int[n];
        for(int i = 0; i < n; i++) par[i] = i;  // pehle sab apne khud ke hi parent ha
        
        for(int[] e : edges){
            int u = e[0], v = e[1], wt = e[2];
            
            int p1 = findParent(u);
            int p2 = findParent(v);
            
            if(p1 == p2){
                // cycle -> so, not to add this edge in mst
            }else{
                par[p1] = p2;
                System.out.println("[" + u + "," + v + "," + wt + "],"); // yahi edge hamari mst ka part hogi so save it => abb yaha se chahe 
            }
        }
        
        return;
    }

    //---------------------------------------------------------------------------------------

    // OUTPUT : MST minimum Cost => 38 
    // 
    //                         2
    //          0         3----------4
    //          |         |          | 
    //        10|         |10       3|  
    //          |         |          |   
    //          1---------2          5-------6
    //              10                   3


    //---------------------------------------------------------------------------------------


//====================================================================================================================================================================================




// QUESTIONS :-
// apni sahuliyat ke liye kiya ha ye aasan rehta ha karna question ko solve as because dono hi algo MST hi dete ha

// CONCEPT : 
// 1. Agar question me Graph [Edges] ki form me given ha to KRUSKAL LAGA LO (UNION FIND) SE KAR LO AS UNION FIND SE DIRECT HO AYEGA, GRAPH BANANE KI JARURAT NI HA
// 2. Agar question me Graph [Adjacency list] ki form me given ha to PRIMS LAGA LO (BFS (Q to PQ)) SE KAR LO AS BFS SE DIRECT HO AYEGA, GRAPH ME KUCH BHI CHANGE KARNE KI JARURAT NI HA

//-----------------------------------------------------------------------------------------

// GFG - Minimum Spanning Tree  ->  https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1/#

    // note : 
    // since yaha question me haame pehle se hi GRAPH -> ADJECENCY LIST KI FORM
    // ME DIYA THA SO HAAM PRIMS LAGA LEGE SIMPLY KRUSKAL SE AS PRIMS ME ADJACENCY LIST
    // ME HI BFS LAGTA HA 
    // AND BECAUSE KRUSKAL ME HAAME AGGAR EDGES KI FORM ME GRAPH MILTA TO EASY 
    // HOTA TO FIND THE MINIMUM SPANNING TREE AS VAHA UNION FIND LAGTA HA
    public static class pair{
        int src, parent, wt;
        pair(int s, int p, int w){
            this.src = s;
            this.parent = p;
            this.wt = w;
        }
    }
    
    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        int n = V;
        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wt - b.wt;
        });
        
        pq.add(new pair(0, -1, 0));
        boolean[] vis = new boolean[n];
        int countOfMSTWeight = 0;

        while(pq.size() != 0){
            pair rp = pq.remove();
            
            if(vis[rp.src]) continue;
            vis[rp.src] = true;
            
            // w
            if(rp.parent != -1){
                countOfMSTWeight += rp.wt;  // now yaha jo bhi edge add hoga MST me haam uska weight simple add karte jayege -> to find the total MST WEIGHT
            }
            
            // a*
            int idx = rp.src;
            ArrayList<ArrayList<Integer>> arr = adj.get(idx);
            for(ArrayList<Integer> x : arr){
                int v = x.get(0), w = x.get(1);
                if(!vis[v]){
                    pq.add(new pair(v, idx, w));
                }
            }
        }
        return countOfMSTWeight;
        
    }  


//=================================================================================================================

// LC 1168- Optimize Water Distribution in a Village  

// to submit =>   https://www.codingninjas.com/codestudio/problems/water-supply-in-a-village_1380956?leftPanelTab=1

// CONCEPT : since agar wells ka array na diya hota to simple haam MST ka cost nikal lete to find 
// the minimum cost to plant all the pipes. but question me haame wells khodne ke liye bhi bola ha agar cost minimum karni ha to
// so iss case me direct haam MST Algo(prims, kruskal) nahi laga sakte galat hoga as esa bhi case ho sakta ha jab saare wells khodna hi min cost ho

// HENCE, EK TRICK KHELTE HA HAAM (yahi catch ha):
// 1. ek new node bana lete ha let(x)
// 2. now haam sare nodes ko iss x se connect kar dete ha new edge bana ke jisme weight hoga wells[i] ie. wells ka cost 
// 3. now jo haamra new edges ka graph banega agar isme ab haam MST nikalege to vo minimum cost ayegi as haamne haar house me wells 
//    ko hata ke vaha bhi connection bana di pipe ki jisse ki  
//    [MST ka jo bhi cost ayega vo ek combine ans hoga = wells lagane ki cost + pipe lagane ki cost]

// NOTE : Now, since haame question me edges ki form me milega graph so haam mst nikalne ke liye kruskal Algo ka use karege
// as isme union find lagega and isme sirf edges chahiye hota ha

    static int[] par;
    public static int findParent(int u){
        if(u == par[u]) return u;
        int recans = findParent(par[u]);
        return par[u] = recans;
    }

    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        // build graph
        int num = pipes.length;
        int[][] edges = new int[num + n][num + n];
        int idx = 0;
        for(int i = 0; i < pipes.length; i++){
            int u = pipes[i][0], v = pipes[i][1], wt = pipes[i][2];
            edges[idx++] = new int[]{u, v, wt};
        }
        
        // adding new edges of wells 
        for(int i = 0; i < wells.length; i++){
            int u = i+1, v = n+1, wt = wells[i];   // v : ko haamne extra liya ha node to connect all the wells
            edges[idx++] = new int[]{u, v, wt};
        }
        
        // applying kruskal algorithm -> ie. UNION FIND
        Arrays.sort(edges, (a,b)->{
        return a[2]-b[2];  // this - other : ascending order sorting
        });
        
        par = new int[n+2];
        for(int i = 0; i < n+2; i++) par[i] = i; // suru me sab apne khud ke hi parent ha
        int cost = 0;
        for(int[] e : edges){
            int u = e[0], v = e[1], wt = e[2];
            int p1 = findParent(u);
            int p2 = findParent(v);
            
            if(p1 == p2){
                // cycle will not add in mst
            }else{
                // add in mst
                cost += wt;
                par[p1] = p2;  // merge the edge
            }
        }
        
        return cost;
    }


