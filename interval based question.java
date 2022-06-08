// LC - 57. Insert Interval

    // ye insert interval vale question pen paper pe dry kar ke hote ha 
    // total three step ha follow karo 
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        int u = newInterval[0], v = newInterval[1];
        
        int i = 0;
        
        // 1. add all intervals smaller than u
        for( ; i < n && intervals[i][1] < u; i++){
            ans.add(intervals[i]);
        }
        
        // 2. ek insertpair le lege and then isse sab ko compare karte chalege ki kon kon lie karta ha iss interval ke ander 
        int[] insertpair = {u,v};
        for( ; i < n && intervals[i][0] <= insertpair[1]; i++){
            insertpair[0] = Math.min(insertpair[0], intervals[i][0]);
            insertpair[1] = Math.max(insertpair[1], intervals[i][1]);
        }
        
        // finally this interval will be added in the arraylist
        ans.add(insertpair);
        
        
        // 3. now jo bach gye vo and me add kar do
        for( ; i < n; i++){
            ans.add(intervals[i]);
        }
        
        
        //-------------------------------------------
        // making final ans
        int[][] fans = new int[ans.size()][2];
        int k = 0;
        for(int[] x : ans){
            fans[k++] = x;
        }
        return fans;
    }

//=============================================================================

// LC - 2276. Count Integers in Intervals

class CountIntervals {
    // treemap use karege (ordered map) jo ki key ke basis pe sort hoga and
    TreeMap<Integer, Integer> tmap;
    int count;
    public CountIntervals() {
        tmap = new TreeMap<>();
        count = 0;
    }
    
    public void add(int left, int right) {
        int newleft = left, newright = right;
        
        while(tmap.floorKey(right) != null && tmap.get(tmap.floorKey(right)) >= left){
            int prevleft = tmap.floorKey(right);
            int prevright = tmap.get(prevleft);
            
            newleft = Math.min(newleft, prevleft);
            newright = Math.max(newright, prevright);
            
            count -= (prevright-prevleft+1);
            tmap.remove(prevleft);
            left = newleft;
            right = newright;
        }
        
        tmap.put(newleft, newright);
        count += (newright - newleft + 1); 
    }
    
    public int count() {
        return count;
    }
}


//=============================================================================


