// LC- 200. Number of Islands

public int numIslands(char[][] grid){
    int n = grid.length, m = grid[0].length;
    boolean[][] vis = new boolean[n][m];
    int count = 0;
    int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    
    for(int i = 0; i < n ;i++){
        for(int j = 0; j < m; j++){
            if(grid[i][j] == '1' && !vis[i][j]){
                count++;
                solve(grid, i, j, vis, dir);
            }
        }
    }
    return count;
}

public void solve(char[][] grid, int r, int c, boolean[][] vis, int[][] dir){
    int n = grid.length, m = grid[0].length;
    vis[r][c] = true;
    for(int i = 0; i < dir.length; i++){
        int x = r + dir[i][0];
        int y = c + dir[i][1];
        if(x >= 0 && x < n && y >= 0 && y < m && !vis[x][y] && grid[x][y] == '1'){
            solve(grid, x, y, vis, dir);
        }
    }   
}

//======================================================================================================


// LC- 695. Max Area of Island

public int maxAreaOfIsland(int[][] grid){
    int max = 0, n = grid.length, m = grid[0].length;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(grid[i][j] == 1){
                max = Math.max(max, dfs(grid, i, j));
            }
        }
    }
    
    return max;
}

public int dfs(int[][] arr, int i, int j){
    arr[i][j] = 0;
    int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    int count = 0, n = arr.length, m = arr[0].length;
    for(int d = 0; d < 4; d++){
        int x = i+dir[d][0];
        int y = j + dir[d][1];
        if(x >= 0 && x < n && y >= 0 && y < m && arr[x][y] == 1){
            count += dfs(arr, x, y);
        }
    }
    return count+1;
}


//======================================================================================================


// LC-130. Surrounded Regions

public void solve(char[][] arr){
    int n = arr.length, m = arr[0].length;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j  < m; j++){
            if(i == 0 || j == 0 || i == n-1 || j == m-1 ){
                if(arr[i][j] == 'O')   dfs(arr, i, j);
            }
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j  < m; j++){
            if(arr[i][j] == 'O') arr[i][j] = 'X';
            if(arr[i][j] == 'A') arr[i][j] = 'O';
        }
    }
    
}

public void dfs(char[][] arr, int i, int j){
    
    arr[i][j] = 'A';
    int n = arr.length, m = arr[0].length;
    int[][] dir = {{-1,0},{0,1},{0,-1},{1,0}};
    for(int d = 0; d < 4; d++){
        int x = i + dir[d][0];
        int y = j + dir[d][1];
        
        if(x >= 0 && x < n && y >= 0 && y < m && arr[x][y] == 'O'){
            dfs(arr, x, y);
        }
    }
}




//======================================================================================================

 // LC- 463. Island Perimeter

    // itterative solution
    // haar cell pe jao and calculate the perimeter of that block and finally add all
    public int islandPerimeter(int[][] arr) {
        int p = 0, n = arr.length, m = arr[0].length;
        int count = 0;
        int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1){
                    for(int d = 0; d < 4; d++){
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
                        if(x < 0 || y < 0 || x >= n || y >= m || arr[x][y] == 0) count++;
                        
                    }
                }
            }
        }
        return count;
    }
    
    

    //DFS Solution
//     public int islandPerimeter(int[][] grid) {
//         int perimeter = 0;
//         boolean[][] visited = new boolean[ grid.length][grid[0].length];
//         for(int i = 0; i < grid.length; i++){
//             for(int j = 0; j < grid[0].length; j++){
//                 if(grid[i][j] == 1){
                    
//                     perimeter = DFS(grid, i, j, visited);
//                     return perimeter;
//                 }
//             }
//         }
        
//         return perimeter ;
//     }
//     int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};
    
//     public int DFS(int[][] grid, int i , int j, boolean[][] visited){
//         visited[i][j] = true;
//         int perimeter = 0, count = 0, n = grid.length, m = grid[0].length;
//         for(int d = 0; d < dir.length; d++){
//             int x = i + dir[d][0];
//             int y = j + dir[d][1];
//             if(x < n && y < m && x >= 0 && y >= 0 && grid[x][y] == 1) count++;
//             if(x < n && y < m && x >= 0 && y >= 0 && grid[x][y] == 1 && visited[x][y] == false){
//                 perimeter += DFS(grid, x, y, visited);
//             }
//         }
//         perimeter = perimeter + (4-count);
//         return perimeter;
//     }
    
    
 


//======================================================================================================

// LC- Number of distinct islands

// YAHA BACKTRACK KARTE VAKT BHI EK CHARACTER (B) DAAL DEGE TAKI KOI ISLAND PURA IDENTICAL HO PATA LAG JAYE


    //leetcode 694 (premium) 
    //gfg solution submit

	public static  int identicalIsland(int[][] grid){
        HashMap<String,Integer> hm = new HashMap<>();
        int count = 0;
        for(int i = 0 ; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    str = new String();
                    DFS(grid, i, j);
                    if(!hm.containsKey(str)){
                        hm.put(str,1);
                        count++;
                    }
                }
            }
        }
        return count;
    }
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static char[] ch = {'T','D','L','R'};
    static String str;
    
    public static void DFS(int[][] grid, int i, int j){
        grid[i][j] = 0;
        for(int d = 0; d < grid.length; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x < grid.length && y < grid[0].length && x>=0 && y >= 0 && grid[x][y] == 1){
                str = str+ch[d];
                DFS(grid, x, y);
            }
        }
        str = str + 'B';
    }



//======================================================================================================

// LC-




//======================================================================================================



