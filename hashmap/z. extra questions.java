// LC - Group Shifted String
// TO SUBMIT -> https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/group-shifted-string-official/ojquestion

// HAAM BASICALLY HASHMAP USE KAREGE AND KEY HOGA HAMARA JO DIFFERENCE BETWEEN THE CHARACTER OF STRING
// ie.  eg =>  "bdg" its key will be (2#3#)  [# isliye lagaya taki alag alag ho sake numbers]
//            NOTE : "za"  yaha (a-z) to -ve ayega so yaha haam +26 kar dege jese hi koi -ve ayega as given in question ki circular me a string values
    
    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        
        for(String s : strs){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length()-1; i++){
                int diff = s.charAt(i+1) - s.charAt(i);
                if(diff < 0) diff += 26;
                sb.append(diff+"#");
            }
            
            String key = sb.toString();
            hm.putIfAbsent(key, new ArrayList<>());
            hm.get(key).add(s);
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for(String key : hm.keySet()){
            ans.add(hm.get(key));
        }
        
        return ans;
    }