// LC-136 Single Number -> Given a non-empty array of integers nums, every element appears 
//                         twice except for one. Find that single one You must implement 
//                         a solution with a linear runtime complexity and use only constant extra space.

// SOLUTION : we will use XOR approach 
// CONCEPT :    A ^ A = 0   ,  A ^ 0 = A
// A ^ B ^ C ^ P ^ A ^ C ^ B = P
// 0 ^ 0 ^ 0 ^ P = P

public int singleNumber(int[] nums) {
    int ans = 0;
    
    for(int i =0 ; i < nums.length; i++){
        ans = ans ^ nums[i];
    }
    
    return ans; // apne aap jo ans ayega vohi hamara bacha hua integer hoga
}



// 137. Single Number II
// Given an integer array nums where every element appears three times except for one, 
// which appears exactly once. Find the single element and return it.

// You must implement a solution with a linear runtime complexity and use only 
// constant extra space.

    // T : O(N)
    // S : O(N) solution
    public int singleNumber(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++){
            if(!hm.containsKey(nums[i])) hm.put(nums[i],1);
            else hm.put(nums[i], hm.get(nums[i])+1);
        }
        
        for(Integer x : hm.keySet()){
            if(hm.get(x) == 1) ans = x;
        }
        
        return ans;
    }


    // BIT MANIPULATION
    // T : O(N)
    // S : O(1) SOLUTION
    public int singleNumber(int[] nums){

    }


















    // 260. Single Number III
    // Given an integer array nums, in which exactly two elements appear only once 
    // and all the other elements appear exactly twice. Find the two elements that 
    // appear only once. You can return the answer in any order.

    // You must write an algorithm that runs in linear runtime complexity and uses only 
    // constant extra space.

    
    // T : O(N)
    // S : O(N) solution
    public int[] singleNumber(int[] nums) {
        int[] ans = new int[2];
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0 ; i < nums.length; i++){
            if(hs.contains(nums[i])) hs.remove(nums[i]);
            else hs.add(nums[i]);
        }
        
        int k = 0;
        for(Integer x : hs){
            ans[k++] = x;
        }
        
        return ans;
    }


    // BIT MANIPULATION
    // T : O(N)
    // S : O(1) SOLUTION

    public int singleNumber(int[] nums){
        
    }











