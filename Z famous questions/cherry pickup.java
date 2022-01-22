// 741. Cherry Pickup

    // SOLUTION  : HAAM BAICALLY DO ADMI KO EK SATH CHALA DEGE JO DFS KARTE HUE JAYEGE
    // EK SATH DONO KO BEJEGE (we can do it becuse hamesha chahe kese bhi raste lo number of step to fix rahege lets say 6 jump ha to 6 hi rahege haar baar )
    // recursion calls hamari 4 lagegi i.e
    //  1 person going R, 2 person going R
    //  1 person going R, 2 person going D
    //  1 person going D, 2 person going R
    //  1 person going D, 2 person going D
    // use dp to optimise it.
    //========
    
    
    // now yaha to backtrack ki jarurat hi nahi ha 
    // since [r,c] can be uniquely identified as [idx = r*n + c] 
    public int cherryPickup(int[][] arr){
        int n = arr.length;
        int[][] dp = new int[5000][5000];
        // for(int[]  x : dp) Arrays.fill(x, -1);
        
        int ans = solve(arr, 0, 0, 0, 0, dp); 
    
        return ans < 0 ? 0 : ans;      
    }
    
    // dr, dc to fix rahega i.e n-1 so mat lo parameter me 
    public int solve(int[][] arr, int sr1, int sc1, int sr2, int sc2, int[][] dp){
        int n = arr.length;
        int x = sr1*n + sc1, y = sr2*n + sc2;
        if(sr1 > n-1 || sc1 > n-1 || sr2 > n-1 || sc2 > n-1 || arr[sr1][sc1] == -1 || arr[sr2][sc2] == -1) return -(int)1e9;
        if(sr1 == n-1 && sc1 == n-1 && sr2 == n-1 && sc2 == n-1){  // base case
            return dp[x][y] = arr[sr1][sc1];
        }
        
        if(dp[x][y] != 0) return dp[x][y] ;
        
        int ans = 0;
        int cherry1 = arr[sr1][sc1];
        int cherry2 = arr[sr2][sc2];
        
        int totalcherry = (sr1 == sr2) ? (cherry1) : (cherry1 + cherry2);
        
        int a = solve(arr, sr1 + 1, sc1, sr2 + 1, sc2, dp);   // DD
        int b = solve(arr, sr1, sc1 + 1, sr2, sc2 + 1, dp);   // RR
        int c = solve(arr, sr1 + 1, sc1, sr2, sc2 + 1, dp);   // DR
        int d = solve(arr, sr1, sc1 + 1, sr2 + 1, sc2, dp);   // RD
        
        ans = Math.max(Math.max(a,b), Math.max(c,d));
        
        return dp[x][y] = ans+totalcherry;
    }
    
    
    // using just 4D dp
    public int cherryPickup(int[][] arr){
        int n = arr.length;
        int[][][][] dp = new int[n][n][n][n];
        
        int ans = solve(arr, 0, 0, 0, 0, dp); 
    
        return ans < 0 ? 0 : ans;      
    }
    
    // dr, dc to fix rahega i.e n-1 so mat lo parameter me 
    public int solve(int[][] arr, int sr1, int sc1, int sr2, int sc2, int[][][][] dp){
        int n = arr.length;
        if(sr1 > n-1 || sc1 > n-1 || sr2 > n-1 || sc2 > n-1 || arr[sr1][sc1] == -1 || arr[sr2][sc2] == -1) return -(int)1e9;
        if(sr1 == n-1 && sc1 == n-1 && sr2 == n-1 && sc2 == n-1){  // base case
            return dp[sr1][sc1][sr2][sc2]  = arr[sr1][sc1];
        }
        
        if(dp[sr1][sc1][sr2][sc2] != 0) return dp[sr1][sc1][sr2][sc2] ;
        
        int ans = 0;
        int cherry1 = arr[sr1][sc1];
        int cherry2 = arr[sr2][sc2];
        
        int totalcherry = (sr1 == sr2) ? (cherry1) : (cherry1 + cherry2);
        
        int a = solve(arr, sr1 + 1, sc1, sr2 + 1, sc2, dp);   // DD
        int b = solve(arr, sr1, sc1 + 1, sr2, sc2 + 1, dp);   // RR
        int c = solve(arr, sr1 + 1, sc1, sr2, sc2 + 1, dp);   // DR
        int d = solve(arr, sr1, sc1 + 1, sr2 + 1, sc2, dp);   // RD
        
        ans = Math.max(Math.max(a,b), Math.max(c,d));
        
        return dp[sr1][sc1][sr2][sc2]  = ans+totalcherry;
    }

    
    
    
    // I Way backtracking way TLE ayega hamesha 
    
//     public int cherryPickup(int[][] arr){
//         int n = arr.length;
//         myans = 0;
//         boolean p = true;
//         solve(arr, 0, 0, n-1, n-1, 0);
        
//         return myans;
//     }
    
//     static int myans = 0;
//     public void solve(int[][] arr, int sr, int sc, int dr, int dc, int csf){
//         if(sr == dr && sc == dc){
//             helper(arr, sr, sc, 0,0, csf);
//             return ;
//         }
        
//         int n = arr.length;
//         int temp = arr[sr][sc];   // temp contains cherries
//         arr[sr][sc] = 0;     // 0 kar do ya -1 since 4 direction nahi.
    
//         if(sr + 1 < n && arr[sr+1][sc] != -1) solve(arr, sr+1, sc, dr, dc, csf+temp);
//         if(sc + 1 < n && arr[sr][sc+1] != -1) solve(arr, sr, sc+1, dr, dc, csf+temp);
        
//          arr[sr][sc] = temp;  // backtracking 
//     }
    
    
    
//     public void helper(int[][] arr, int sr, int sc, int dr, int dc,int csf){
//         if(sr == dr && sc == dc){
//             if(arr[sr][sc] == 1) csf++;
//             if(myans < csf) myans = csf;
//             return;
//         }
        
//         int n = arr.length;
//         int temp = arr[sr][sc];   // temp contains cherries
//         arr[sr][sc] = 0;     // 0 kar do ya -1 since 4 direction nahi.

//         if(sr -1 >= 0 && arr[sr-1][sc] != -1) helper(arr, sr-1, sc, dr, dc, csf+temp);
//         if(sc -1 >= 0 && arr[sr][sc-1] != -1) helper(arr, sr, sc-1, dr, dc, csf+temp);
        
//          arr[sr][sc] = temp;  // backtracking 
//     }
    
    //  |
    //  |
    //  |
    // \ /
    
    // inn dono ko ek me bhi kar sakte the bass ek boolean ka variable le ke
      
//     public int cherryPickup(int[][] arr){
//         int n = arr.length;
//         myans = 0;
//         boolean p = true;
//         solve(arr, 0, 0, n-1, n-1, p, 0);
        
//         return myans;
//     }
    
//     static int myans = 0;
//     public void solve(int[][] arr, int sr, int sc, int dr, int dc, boolean p,int csf){
//         if(!p && sr == 0 && sc == 0){
//             if(arr[sr][sc] == 1) csf++;
//             if(myans < csf) myans = csf;
//             return;
//         }
        
//         if(sr == dr && sc == dc){
//             int temp = arr[sr][sc];   // temp contains cherries
//             arr[sr][sc] = 0;     // 0 kar do ya -1 since 4 direction nahi.
//             solve(arr, sr, sc, 0, 0, false, csf+temp);
//             arr[sr][sc] = temp;  // backtracking 
//         }
        
//         int n = arr.length;
//         int temp = arr[sr][sc];   // temp contains cherries
//         arr[sr][sc] = 0;     // 0 kar do ya -1 since 4 direction nahi.
        
//         if(p){
//             if(sr + 1 < n && arr[sr+1][sc] != -1) solve(arr, sr+1, sc, dr, dc,p, csf+temp);
//             if(sc + 1 < n && arr[sr][sc+1] != -1) solve(arr, sr, sc+1, dr, dc,p, csf+temp);
//         }else{
//             if(sr -1 >= 0 && arr[sr-1][sc] != -1) solve(arr, sr-1, sc, dr, dc,p, csf+temp);
//             if(sc -1 >= 0 && arr[sr][sc-1] != -1) solve(arr, sr, sc-1, dr, dc,p, csf+temp);
//         }
        
//          arr[sr][sc] = temp;  // backtracking 
//     }
    
 
    
    // test cases
//     [[0,1,-1],[1,0,-1],[1,1,1]]
// [[1,1,-1],[1,-1,1],[-1,1,1]]
    
    // [[1,1,1,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,1],[1,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,1,1,1]]   ->  15 ha ans iska na ki 14
    
    // [[1,1,1,1,-1,-1,-1,1,0,0],[1,0,0,0,1,0,0,0,1,0],[0,0,1,1,1,1,0,1,1,1],[1,1,0,1,1,1,0,-1,1,1],[0,0,0,0,1,-1,0,0,1,-1],[1,0,1,1,1,0,0,-1,1,0],[1,1,0,1,0,0,1,0,1,-1],[1,-1,0,1,0,0,0,1,-1,1],[1,0,-1,0,-1,0,0,1,0,0],[0,0,-1,0,1,0,1,0,0,1]]
    
//=========================================================================================================


// 1463. Cherry Pickup II

 // same concept use kiya ha cherryI jesa because yaha bhi do log ha so dono ko ek sath chala diya 
    // and yaha calls 9 lagegi
    // LL, LM, LR, ML, MM, MR, RL, RM, RR
    // 4 D dp le lo ya 2D dp le lo
//     public int cherryPickup(int[][] grid) {
//         int n = grid.length, m = grid[0].length;
//         int[][] dp = new int[5000][5000];
//         int ans = solve(grid, 0,0,0,m-1,dp);
       
//         return ans;
//     }
    
//     public int solve(int[][] arr, int sr1, int sc1, int sr2, int sc2, int[][] dp){
//         int n = arr.length-1, m = arr[0].length-1;
//         if( sr1 > n || sc1 > m || sc1 < 0 || sr2 > n || sc2 > m || sc2 < 0 ) return -(int)1e9;

//         int x = sr1*(m+1) + sc1 , y = sr2*(m+1) + sc2;
//         if(sr1 == n || sr2 == n){ // base case
//             return dp[x][y] = (sr1 == sr2 && sc1 == sc2) ? arr[sr1][sc1] : arr[sr1][sc1] + arr[sr2][sc2];
//         }  
        
//         if(dp[x][y] != 0) return dp[x][y];
        
//         int a = solve(arr, sr1+1, sc1-1, sr2+1, sc2-1, dp);  // LL
//         int b = solve(arr, sr1+1, sc1-1, sr2+1, sc2, dp);  // LM
//         int c = solve(arr, sr1+1, sc1-1, sr2+1, sc2+1, dp);  // LR
        
//         int d = solve(arr, sr1+1, sc1, sr2+1, sc2-1, dp);  // ML
//         int e = solve(arr, sr1+1, sc1, sr2+1, sc2, dp);  // MM
//         int f = solve(arr, sr1+1, sc1, sr2+1, sc2+1, dp);  // MR
        
//         int g = solve(arr, sr1+1, sc1+1, sr2+1, sc2-1, dp);  // RL
//         int h = solve(arr, sr1+1, sc1+1, sr2+1, sc2, dp);  // RM
//         int i = solve(arr, sr1+1, sc1+1, sr2+1, sc2+1, dp);  // RR
        
//         int f1 = Math.max(Math.max(Math.max(a,b),Math.max(c,d)), Math.max(Math.max(e,f), Math.max(g,h)));
//         int ans = Math.max(f1 , i);
        
//         int totalcherry = (sr1 == sr2 && sc1 == sc2) ? arr[sr1][sc1] : arr[sr1][sc1] + arr[sr2][sc2];
//         // System.out.println(totalcherry);
//         return dp[x][y] = ans + totalcherry;
//     }
    
