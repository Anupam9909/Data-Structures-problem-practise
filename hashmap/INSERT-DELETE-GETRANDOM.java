// LC-380  INSERT - DELETE GET RANDOM IN O(1)

class RandomizedSet {
    HashMap<Integer, Integer> hm;
    ArrayList<Integer> arr;

    public RandomizedSet() {
        hm = new HashMap<>(); 
        arr = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(hm.containsKey(val)){
            return false;
        }else{
            arr.add(val);
            hm.put(val, arr.size()-1);

            return true;
        }
    }
    
    public boolean remove(int val) {
        if(hm.containsKey(val)){
            int idx = hm.get(val);
            int n = arr.size();
            arr.set(idx, arr.get(n-1));
            hm.put(arr.get(n-1), idx);

            arr.remove(n-1);
            hm.remove(val);
            
            return true;  
        }else{
            return false;
        }
    }
    
    public int getRandom() {
        int min = 0;
        int max = arr.size()-1;
        int range = max - min + 1;
        int randomIndex = (int)(Math.random() * range) + min;  // ye formula ha [to find random value b/w min and max value]
        
        return arr.get(randomIndex);
    }
}






// LC-381  INSERT - DELETE GET RANDOM IN O(1) [when duplicate elements also present]
class RandomizedCollection {
    List<Integer> list;
    Map<Integer, Set<Integer>> map;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, new HashSet<>());
        }

        map.get(val).add(list.size());
        list.add(val);

        return map.get(val).size() == 1;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) return false;

        int removeIdx = map.get(val).iterator().next();
        map.get(val).remove(removeIdx);

        int lastElement = list.get(list.size() - 1);
        list.set(removeIdx, lastElement);

        map.get(lastElement).add(removeIdx);
        map.get(lastElement).remove(list.size() - 1);

        list.remove(list.size() - 1);

        return true;
    }

    public int getRandom() {
        int randomIdx = (int) (Math.random() * list.size());
        return list.get(randomIdx);
    }
}

// TC: O(1) - O(1) - O(1)
// SC: O(n)
