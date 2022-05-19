import java.util.*;

public class pairsum{

// 1. Two Sum  
// NOTE : since isne index manga ha pair ka. so, haam yaha two pointer se nahi hoga kuch bhi kar lo, sirf hashmap vale tarike se hi hoga
   
   // brute force : O(N^2) ka solution ha
    
    // optimize version : O(n) time,  O(n) space
    // ek hashmap le lo and for any element arr[i] check for (target-arr[i]) in the hashmap 
    // simply {arr[i], index} store karte jao
    // NOTE: ye question isi tarah se hoga -> bina hm ke nahi hoga and sirf pointer se bhi ni hoga
    // as haam sort karege to index kho jayege and agar index pehle store bhi kara liye tab bhi 
    // nahi hoga because [duplicate] aa gye to nahi hoga hm me store
    
   public static int[] twoSum(int[] arr, int target){
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = arr.length;
        
        for(int i = 0; i < n; i++){
            if(hm.containsKey(target-arr[i])){
                int idx = hm.get(target-arr[i]);
                return new int[]{i, idx};
            }
            hm.put(val, i);
        }
        
        return new int[]{0,0};
    }
    

//========================================================================================================
// 2 sum II - leetcode
// NOTE : yaha bhi index manga ha pair ka. but question me given ha ki array sorted ha.
// so, we can use this method ie. (ye si and ei vala karke)
 
    // brute force : O(N^2) ka solution ha
    
    // optimize version : O(n) time,  O(1) space
    // TWO POINTER TECHNIQUE
// NOTE : yaha iss question me duplicate nahi the so ye tarika use karege
    public int[] twoSumII(int[] arr, int target){
        int si = 0, ei = arr.length-1;
        
        while(si < ei){
            int sum = arr[si] + arr[ei];
            
            if(sum == target) return new int[]{si+1, ei+1};  // +1 kiya as 1 indexed pucha ha 
            else if(sum < target)  si++;
            else  ei--;
        } 
        
        return new int[]{-1,-1};
    }
    

//========================================================================================================

// TWO SUM - RETURN ALL UNIQUE PAIRS 
// jab bhi duplicate aaye to (si) and (ei) pointer ko move kar do naye vale value pe 

    public List<List<Integer>> twoSumII(int[] arr, int target){
        int si = 0, ei = arr.length-1;
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        
        while(si < ei){
            int sum = arr[si] + arr[ei];
            
            if(sum < target)  si++;
            else if(sum > target)  ei--;
            else if(sum == target){
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[si]);
                temp.add(arr[ei]);
                ans.add(temp);
                
                // ye haam si and ei ko aage and piche issliye move kar rahe ha as haame unique pair chahiye 
                while(si+1 != n && arr[si] == arr[si+1]) si++;
                while(ei-1 != -1 && arr[ei] == arr[ei-1]) ei--;
                

                // ye to karna hi ha as hame sari possible pairs nikalne ha
                si++; 
                ei--;
            }
        } 
        
        return ans;
    }
    
    
   
//========================================================================================================

// no need to do this just known it
 // // RETRUN ALL PAIRS (with duplicate) -> iff sare pair lane ke liye to binary search 
 // first occurence and last occurence chahiye hoga  as [1,2,2,2,2,3,4,5,6,7,7,7,7,7,8,9,10] 
 // me (4 Times 2)*(5 Times 7) = 20 to bass inhi ke pair ho gye ,  and ek equal to vala case bhi dyan rakhna hoga i.e [2,2,2,2,2,2,2]
 

//========================================================================================================

   
// GFG - Count pairs with given sum 
// NOTE: since yaha hame count nikalna ha so haam hashmap ka use karege <value, frequency> ka
// and iss tarike se sare count nikal lege

    int getPairsCount(int[] arr, int n, int k) {
        // code here
        HashMap<Integer, Integer> hm = new HashMap<>();
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(hm.containsKey(k-arr[i])){
                ans += hm.get(k-arr[i]);
            }
            hm.put(arr[i], hm.getOrDefault(arr[i], 0)+1);
        }
        return ans;
    }


//================================================================================================

LC-454->   4Sum II
    // two sum count ki tarah ha thoda 
    // step 1 : make arr1 from which contains the sum combination of nums1 and nums2, similarly    
    // step 2 : make arr2 from which contains the sum combination of nums3 and nums4
    
    // step 3 :  then haam arr1 ko HM me daal dege -> {val, freq}
    // step 4 :  and we will traverse the arr2 then for each arr2[i] we find the the (k-val) in the hm and increase the ans+=hm.get(k-val)  // yaha freq ko increase ni karna as yaha charo array ke combination se hi 4 sum banana ha
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // make first array
        int n = nums1.length;
        int[] arr1 = new int[n*n];
        
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr1[idx++] =  nums1[i] + nums2[j];
            }
        }
        
        // make second array
        int[] arr2 = new int[n*n];
        
        idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr2[idx++] =  nums3[i] + nums4[j];
            }
        }
        
        // make ans
        // hm{val, freq}
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < arr1.length; i++){
            hm.put(arr1[i], hm.getOrDefault(arr1[i], 0)+1);
        }
        
        // last step
        int k = 0, ans = 0;
        for(int i = 0; i < arr2.length; i++){
            int val = arr2[i];
            if(hm.containsKey(k-val)){
                ans += hm.get(k-val);
            }
        }
        return ans;
    }
    

//========================================================================================================


    public static void main(String[] args){
        int[] arr = {1,0,-1,0,-2,2};
        int target = 0;
        // calculating 4 sum
        List<List<Integer>> ans = kSum(arr, target,0, arr.length-1, 4);
        System.out.println(ans);
    }

    // finally jo yaad rakna ha vo ye ha ki
    // KSum vala fuction bass karna ha and recursion call kar do bass then issi me base case hoga i.e k= 2 ka 
    // so hame bass 2sum ka function likhna ha only and ho gya !!
    //----------------------------------------
    public List<List<Integer>> kSum(int[] arr, int target,int si, int ei, int k){
        if(k == 2) return twoSum(arr, target, si, ei);

        List<List<Integer>> myans = new ArrayList<>();
        Arrays.sort(arr);
        int n = arr.length;
        
        for(int i = si; i < ei; i++){
            List<List<Integer>> smallans = kSum(arr, target-arr[i] , i+1, ei, k-1);
            addans(arr[i], smallans, myans);
            
            while(i < ei && arr[i] == arr[i+1]) i++;
        }
        return myans;
    }
    
    public  static List<List<Integer>> twoSum(int[] arr, int target, int si, int ei){
        int n= arr.length;
        List<List<Integer>> ans = new ArrayList<>();
        
        while(si < ei){
            int sum = arr[si] + arr[ei];
            
            if(sum == target){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(arr[si]);
                temp.add(arr[ei]);
                
                ans.add(temp);
		// to remove duplicates we do this
                while(si < ei && arr[si+1] == arr[si]) si++; 
                while(si < ei && arr[ei-1] == arr[ei]) ei--;
                si++;
                ei--;
            }
            else if(sum < target){
                si++;
            }else{
                ei--;
            }
        }
        return ans;
    }
    
    public static void addans(int data, List<List<Integer>> arr, List<List<Integer>> ans){
        for(List<Integer> ar : arr){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(data);
            for(int x : ar){
               temp.add(x);
            }
            ans.add(temp);
        }
    }
    // bass yahi tak yaad rakho
    //-----------------------------------------------------------------------------------------












    //  LONG WAY!!!!

    // public static List<List<Integer>> fourSum(int[] arr, int target){
    //     List<List<Integer>> myans = new ArrayList<>();
    //     Arrays.sort(arr);
    //     int si = 0, ei = arr.length-1;
    //     int n = arr.length;
        
    //     for(int i = si; i < ei; i++){
    //         List<List<Integer>> smallans = threeSum(arr, target-arr[i] , i+1, ei);
    //         addans(arr[i], smallans, myans);
            
    //         while(i < ei && arr[i] == arr[i+1]) i++;
    //     }
    //     return myans;
    // }
    
    // public static List<List<Integer>> threeSum(int[] arr, int target, int si, int ei){
    //     List<List<Integer>> myans = new ArrayList<>();
      
    //     int n = arr.length;
        
    //     for(int i = si; i < ei; i++){
    //         List<List<Integer>> smallans = twoSum(arr, target-arr[i] , i+1, ei);
    //         addans(arr[i], smallans, myans);
            
    //         while(i < ei && arr[i] == arr[i+1]) i++;
    //     }
    //     return myans;
    // }
    
    // // solution for all two sum pairs (if duplicate elements are not included) 
    // public  static List<List<Integer>> twoSum(int[] arr, int target, int si, int ei){
    //     int n= arr.length;
    //     List<List<Integer>> ans = new ArrayList<>();
        
    //     while(si < ei){
    //         int sum = arr[si] + arr[ei];
            
    //         if(sum == target){
    //             ArrayList<Integer> temp = new ArrayList<>();
    //             temp.add(arr[si]);
    //             temp.add(arr[ei]);
                
    //             ans.add(temp);
    //             while(si < ei && arr[si+1] == arr[si]) si++; 
    //             while(si < ei && arr[ei-1] == arr[ei]) ei--;
    //             si++;
    //             ei--;
    //         }
    //         else if(sum < target){
    //             si++;
    //         }else{
    //             ei--;
    //         }
    //     }
    //     return ans;
    // }
    
    // public static void addans(int data, List<List<Integer>> arr, List<List<Integer>> ans){
    //     for(List<Integer> ar : arr){
    //         ArrayList<Integer> temp = new ArrayList<>();
    //         temp.add(data);
    //         for(int x : ar){
    //            temp.add(x);
    //         }
    //         ans.add(temp);
    //     }
    // }
    
}