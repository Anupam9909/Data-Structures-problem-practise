// LC-380  INSERT - DELETE GET RANDOM IN O(1)

class RandomizedSet {
    HashMap<Integer, Integer> hm;
    ArrayList<Integer> arr;
    int size;
    public RandomizedSet() {
        hm = new HashMap<>(); 
        arr = new ArrayList<>();
        this.size = 0;
    }
    
    public boolean insert(int val) {
        if(hm.containsKey(val)){
            return false;
        }else{
            int idx = size;
            arr.add(val);
            hm.put(val, idx);
            size++;

            return true;
        }
    }
    
    public boolean remove(int val) {
        if(hm.containsKey(val)){
            int curridx = hm.get(val);
            hm.remove(val);
            if(curridx == size-1){   // if element is present at last index of arraylist itself
                arr.remove(size-1);
                size--;
                return true;
            }
            
            int lastidx = size-1;
            int lastval = arr.get(lastidx);
            
            arr.set(curridx, lastval);
            arr.remove(size-1);
            
            hm.put(lastval, curridx);
            size--;
            return true;
            
        }else{
            return false;
        }
    }
    
    public int getRandom() {
        int min = 0;
        int max = size-1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;  // ye formula ha [to find random value b/w min and max value]
        
        return arr.get(rand);
    }
}






// LC-381  INSERT - DELETE GET RANDOM IN O(1) [when duplicate elements also present]
