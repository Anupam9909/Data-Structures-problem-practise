// LC - 1015. Smallest Integer Divisible by K
 public int smallestRepunitDivByK(int k) {
        long num = 1;
        int ans = -1;
        for(int i = 1; i <= k; i++){
            if(num % k == 0){ 
                ans = i; 
                break;
            }
            num = num*10 + 1;
            
            num = num % k;            // IMPORTANT  LINE (NAHI TO OVERFLOW HO JAYEGA AS ANS TO 819 BHI AA SAKTA HA MATLAB BIG INTEGER BHI LE LEGE TO BHI KAAM PADEGA SO YAHI EK TARIKA HA KI  [ NUM = NUM % K ] KAR DO)
            
            // System.out.println(num);
            
        }
        return ans;
    }




    //==================================================================================================================


// LC - 1026. Maximum Difference Between Node and Ancestor

// hame sare ansestors ko dekne ha yaha
    // ek minimum ele ki list bana lo 
    // ek maximum ele ki list bann lo
    // haar level pe max ans nikalte chalo
    
    static int ans = 0;
    public int maxAncestorDiff(TreeNode root){
        List<Integer> a = new ArrayList<>();   // a : minimum list
        List<Integer> b = new ArrayList<>();   // b : maximum list
        
        ans = -(int)1e9;
        solve(root, a, b);
        
        return ans;
    }
    
    public void solve(TreeNode root, List<Integer> a, List<Integer> b){
        if(root == null) return ;
        
        if(a.size() == 0 || a.get(a.size()-1) >= root.val) a.add(root.val);
        if(b.size() == 0 || b.get(b.size()-1) <= root.val) b.add(root.val);
        
        ans = Math.max(ans, Math.abs(a.get(a.size()-1) - b.get(b.size()-1)));
        
        solve(root.left, a, b);
        solve(root.right, a, b);
        
        if(a.get(a.size()-1) == root.val) a.remove(a.size()-1);
        if(b.get(b.size()-1) == root.val) b.remove(b.size()-1);
        
    }
    
    
    // short way(optimize space)
    // ye vohi vala tarika ha bst me ek <min, max> vala 
    // but haam issko min max category me bhi use kar sakte ha BT ke
     public int maxAncestorDiff(TreeNode root){
         return solve(root, root.val, root.val);
     }
    
     public int solve(TreeNode root, int min , int max){
         if(root == null) return 0;
         
         min = Math.min(min, root.val);
         max = Math.max(max, root.val);
         int ans = 0;
         
         ans = Math.max(ans , solve(root.left, min, max));
         ans = Math.max(ans , solve(root.right, min, max));
         
         return Math.max(ans, Math.abs(min-max));
     }
    

    //==================================================================================================================

    // 874. Walking Robot Simulation
    public int robotSim(int[] com, int[][] obst) {
        int X = 0, Y = 0;
        int maxdist = 0;
    
        HashSet<List<Integer>> hs = new HashSet<>();
        for(int[] x : obst){
            hs.add(List.of(x[0],x[1]));
        }
        
        int dir = 1;
        
        int n = com.length;
        for(int i = 0; i < n; i++){
            int step = com[i];
            
            if(step == -1) {
                if(dir == 0) dir = 3; 
                else dir--;           
                continue;
            }
            else if(step == -2) {
                dir++;
                continue;
            }
            
            
            for(int j = 0; j < step; j++){
                int x = X, y = Y;
                if(dir%4 == 0) x++;
                else if(dir%4 == 1) y++;
                else if(dir%4 == 2) x--;
                else y--;
                
                if(hs.contains(List.of(x,y))) break;
                
                maxdist = Math.max(maxdist, (x*x)+(y*y));
                X = x;
                Y = y;
            }
        }
        
        return maxdist;
    }

    // 2069. Walking Robot Simulation II

    class Robot {
        int r, c, n, m, dir;
        int perimeter;
        public Robot(int width, int height) {
            r = 0; c = 0; n = height-1; m = width-1;
            dir = 0;
            perimeter = (height*2) + (width*2 - 4);
        }

        public void step(int num) {
            num = num%(perimeter);     // case-I -> loop me jada na chale result vohi ayega (avoid TLE)
            if(num == 0) num = perimeter;  // CASE- ii ->  if same position pe aya to direction galat ho jayegi so kaam se kaam perimeter tak to chalana hi    
            
            while(num > 0){
                int R = r, C = c;
                if(dir%4 == 0)       C++;
                else if(dir%4 == 1)  R++;
                else if(dir%4 == 2)  C--;
                else                 R--;
                
                if(R < 0 || R > n || C < 0 || C > m){
                    dir++;
                    continue;   // yaha continue hi karna padega [step(num) pass kiya i.e recurrsion kiya to wrong solution hoga pakka jo ki samaj ni ayega. so class me kabhi bhi recurssive function use mat karna]
                }
                
                // System.out.println(R + " " + C + "  num = " + num + "  dir = " + dir);
                r = R;
                c = C;
                num--;
            }
        }

        public int[] getPos() {
            return new int[]{c,r};
        }

        public String getDir() {
            if(dir%4 == 0) return "East";
            else if(dir%4 == 1) return "North";
            else if(dir%4 == 2) return "West";
            else return "South";
        }
    }

    

    //==================================================================================================================
