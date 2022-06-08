// LC - 37. Sudoku Solver (hard)

    // exam se pehle ek baar iska solution dek lena kese implement kiya ha important ha kese implement kiya ha baki logic to normal ha
    
public void solveSudoku(char[][] board) {
        int[][] arr = new int[9][9];
        int count = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') {arr[i][j] = 0; count++;}
                else arr[i][j] = board[i][j]-'0';
            }
        }
        
        HashSet<Integer>[][] vis = new HashSet[3][3];
        for(int  i = 0; i < 3; i++){ 
            for(int j = 0; j < 3; j++){ 
                vis[i][j] = new HashSet<>();   // vis[i][j] ek hashset ha (i,j) pe rakha hua
                for(int k = 1; k <= 9; k++) vis[i][j].add(k);
                
                for(int r = i*3; r < i*3+3; r++){
                    for(int c = j*3; c < j*3+3; c++){
                        int val = board[r][c];
                        vis[i][j].remove(val-'0');  // note : val-'0' lagana bhot hi 
                                                    //jaruri ha (ni to bhot dikkat ayegi)
                    }
                }
            }
        }
        
        ans = new int[9][9];
        solve(arr, 0, 0, vis);
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = (char)('0' + ans[i][j]);
            }
        }
    }
    
    static int[][] ans;
    public void solve(int[][] arr, int r, int c, HashSet<Integer>[][] vis){
        if(r == 9){
            // System.out.println("hello");
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    ans[i][j] = arr[i][j];
                }
            }
            return;
        }
        if(c == 9) { 
            solve(arr, r+1, 0, vis); 
            return; 
        }
        if(arr[r][c] != 0) {
            solve(arr, r, c+1, vis); 
            return;
        }
        

        int x = r/3, y = c/3;
        HashSet<Integer> hs = vis[x][y];
        ArrayList<Integer> al = new ArrayList<>(hs);
        
        for(int i = 0; i < al.size(); i++){
            int num = al.get(i);
            if(canplace(arr, r, c, num)){
                arr[r][c] = num;
                hs.remove(num);
                solve(arr, r, c+1, vis);
                hs.add(num);
                arr[r][c] = 0;                    
            }

        }                  
    }
    
    public boolean canplace(int[][] arr, int i, int j, int x){
        boolean ans = true;
        // horizontal check
        for(int c = 0; c < 9; c++){
            if(arr[i][c] == x){ ans = false; break;}
        }
        
        // vertical
        for(int r = 0; r < 9; r++){
            if(arr[r][j] == x){ ans = false; break;}
        }
        return ans;
    }
        

//==================================================================================================

// LC- 36. Valid Sudoku (medium)

    public boolean isValidSudoku(char[][] arr) {
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(arr[i][j] != '.'){ 
                    char val = arr[i][j];
                    // horizontal check
                    for(int k = 0; k < 9; k++) if( k!=j && arr[i][k] == val) return false;
                    
                    // vertical check
                    for(int k = 0; k < 9; k++) if( k!=i && arr[k][j] == val) return false;

                    // subbox check
                    int r = i/3, c = j/3;
                    // subbox row 1
                    if(3*r != i && 3*c != j && arr[3*r][3*c] == val) return false;
                    if(3*r != i && 3*c+1 != j && arr[3*r][3*c+1] == val) return false;
                    if(3*r != i && 3*c+2 != j && arr[3*r][3*c+2] == val) return false;
                    
                    // subbox row 2
                    if(3*r+1 != i && 3*c != j && arr[3*r+1][3*c] == val) return false;
                    if(3*r+1 != i && 3*c+1 != j && arr[3*r+1][3*c+1] == val) return false;
                    if(3*r+1 != i && 3*c+2 != j && arr[3*r+1][3*c+2] == val) return false;
                    
                    // subbox row 3
                    if(3*r+2 != i && 3*c != j && arr[3*r+2][3*c] == val) return false;
                    if(3*r+2 != i && 3*c+1 != j && arr[3*r+2][3*c+1] == val) return false;
                    if(3*r+2 != i && 3*c+2 != j && arr[3*r+2][3*c+2] == val) return false;

                }
            }
        }
        
        return true;
    }

//=====================================================================================================

