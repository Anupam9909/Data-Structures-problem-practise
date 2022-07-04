// LC-42. Trapping Rain Water
 
// O(n) time & O(n) space
    // simple ha NGOR AND NGOL nikalne ki bhi jarurat ni ha (bina stack ke hi ho jata ha)
    // bus yaha ye concept lagao
    //  concept:
    // stand at the ith position
    // left se max building ki length le aao (say x)
    // right se max building ki length le aao (say y)
    // water level above the ith block is = arr[i] - [Math.abs(x-y)]
    // buss sab jagah calculate kar lo ye \
    
    public int trap(int[] arr) {
        int n = arr.length;
        int[] MGOL = new int[n];  // max greater on left
        int max = arr[0];
        for(int i = 0; i < n ;i++){
            max = Math.max(max, arr[i]);
            MGOL[i] = max;
        }
        
        int[] MGOR = new int[n];  // max greater on right
        max = arr[n-1];
        for(int i = n-1; i>=0 ; i--){
            max = Math.max(max, arr[i]);
            MGOR[i] = max;
        }
        
        // making ans
        int count = 0;
        for(int i = 0; i < n;i++){
            int val = Math.min(MGOL[i], MGOR[i]);
            int height = Math.abs(val-arr[i]);
            count += height;
        }
        
        return count;
    }
    

    // OPTIMISED VERSION:
    // O(n) time & o(1) space -> can ask in interview

    // yaha haam two pointer see bhi vahi kaam kar sakte ha ( where-> si = 0, ei = n-1)
    // concept: leftmax and rightmax le ke chalte ha haar itteration me
    // if(arr[si] < arr[ei]) // means haam si ke upar apna ans bana sakte ha as right me kitni bhi badi building ku na ho leftmax ki building se to pani nikal jayega
    // similarly with other condition i.e
    // if(arr[si] > arr[ei]) // means haam ei ke upar apna ans bana sakte ha as left me kitni bhi badi building ku na ho rightmax ki building se to pani nikal jayega
    public int trap(int[] arr){
        int n = arr.length;
        
        int maxleft = 0, maxright = 0;
        int si = 0, ei = n-1;
        int ans = 0;
        
        while(si <= ei){
            maxleft = Math.max(maxleft, arr[si]);  // update maxleft and maxright values
            maxright = Math.max(maxright, arr[ei]);
            
            if(arr[si] < arr[ei]){  // calculating the water above si building
                int height = Math.min(maxleft, maxright);
                ans += (height-arr[si]);
                si++; 
            }
            else if(arr[si] > arr[ei]){  // calculating the water above si building
                int height = Math.min(maxleft, maxright);
                ans += (height-arr[ei]);
                ei--;
            }
            
            
        }
        return ans;
    }
    
    



    //============================================================================================================================

    // LC - 407. Trapping Rain Water II
    
    // NOTE :   vo 1D vala solution yaha work ni karega
    // reason why 1D vala solution does not work here
    // wrong approach - agar vohi 1D rainwater trapping vala solution iss 2D rainwater trapping water vale me use karege to ans galat ayega as because
    // 1D me hamare pass only do raste the water ko flow hone ke (left or right)
    // but 2D me hamare pass kai sare raste ha flow hone ke eg
    //   0 0 0 1 0 0 0
    //   1 0 0 0 0 0 1      for(1,3) ans will come to be 1 (iff we use that 1D logic)
    //   0 0 0 1 0 0 0      // but the actual ans is 0 everywhere
    // hence we cannot use that logic here


    // CONCEPT : haam ek priorityQueue le lege and boundry ko kaam karte jayege and water ko add karte jayege
    // ek visited array bhi le lege so that dubara uspe na jaye
    // see this video for concept (simple ha pura dekna) imp ->  https://www.youtube.com/watch?v=fywyCy6Fyoo     (acha bataya ha inhone isme)
    public int trapRainWater(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // 2d ka 1d karke store karege pq me
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{ // min pq
            return arr[a/m][a%m] - arr[b/m][b%m];  // this-other => min pq
        });
        int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        
        boolean[][] vis = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 || i == n-1 || j == 0 || j == m-1){
                    vis[i][j] = true;
                    pq.add(i*m+j);
                }
            }
        }
        
        int totalwater = 0;
        
        while(pq.size() != 0){
            int rem = pq.remove();
            int r = rem/m, c = rem%m;
            
            for(int d = 0; d < 4; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x >= 0 && y >= 0 && x < n && y < m && vis[x][y] == false){
                    vis[x][y] = true;
                    if(arr[x][y] < arr[r][c]){
                        totalwater += (arr[r][c] - arr[x][y]); 
                        arr[x][y] = arr[r][c];  //V.Imp->water ki height same bana do abb
                    }                           // and then pq me add kar do ie now usko 
                                                // kaam assign kar do ki jao ans bana lo
                    pq.add(x*m+y);
                }
            }
        }
        
        return totalwater;
    }
       
       
       
       