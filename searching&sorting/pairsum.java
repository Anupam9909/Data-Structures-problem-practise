import java.util.*;

public class pairsum{

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