 // LC - 169. Majority Element

    // O(n) time & 
    // moore's voting algorithm // https://www.youtube.com/watch?v=AoX3BPWNnoE 
    // see this video at exam time and learn this
    // NOTE: basically haam non identical elements ko neglect karte ja rahe ha and last me jo bhi 
    // candidate bachega vohi majority element hoga
    public int majorityElement(int[] arr) {
        int count = 0;
        int candidate = 0;
        
        for(int num : arr){
            if(count == 0){
                candidate = num;
            }
            
            if(num == candidate) count++;
            else count--;
        }
        
        return candidate;
    }
    
    
    // yahi use karo using hashmap space 
    // O(n) time & O(n) space
//     public int majorityElement(int[] arr) {
//         HashMap<Integer, Integer> hm = new HashMap<>();
        
//         for(int x : arr){
//             hm.put(x, hm.getOrDefault(x,0)+1);
//         }
//         int n = arr.length;
//         int val = 0;
//         for(int x : hm.keySet()){
//             if(hm.get(x) > n/2) val = x;
//         }
//         return val;
//     }
    
    // using -> O(nlogn) time  &&   O(1) space
//     public int majorityElement(int[] arr){
//         Arrays.sort(arr);
//         return arr[arr.length/2];
//     }

//=====================================================================================================
// LC - 229. Majority Element II

   // O(n) time & 
    // moore's voting algorithm // https://www.youtube.com/watch?v=yDbkQd9t2ig&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=18 
    // see this video at exam time and learn this
    public List<Integer> majorityElement(int[] arr){
        int n = arr.length;
        int count1 = 0, count2 = 0;
        int number1 = (int)1e9+7, number2 = (int)1e9+7;
        
        for(int val : arr){
            if(val == number1) { 
                count1++;
            }
            else if(val == number2){  
                count2++;
            }
            else if(count1 == 0){
                number1 = val;
                count1 = 1;
            }
            else if(count2 == 0){
                number2 = val;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }
        
        // double check it
        int c1 = 0, c2 = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] == number1) c1++;
            else if(arr[i] == number2) c2++;
        }
        
        List<Integer> ans = new ArrayList<>();
        if(c1 > n/3) ans.add(number1);
        if(c2 > n/3) ans.add(number2);
        
        return ans;
    }
    
    
    
    //------------------------------------------------------------------------------------
    
    
//     // using space i.e O(n) time and O(n) space
//     public List<Integer> majorityElement(int[] nums) {
//         HashMap<Integer, Integer> hm = new HashMap<>();
//         int n = nums.length;
//         for(int x : nums){
//             hm.put(x, hm.getOrDefault(x, 0)+1);
//         }
        
//         List<Integer> ans = new ArrayList<>();
//         for(int x : hm.keySet()){
//             if(hm.get(x) > n/3) ans.add(x);
//         }
//         return ans;
//     }
    