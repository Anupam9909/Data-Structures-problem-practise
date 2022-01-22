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
