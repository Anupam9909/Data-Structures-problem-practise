import java.util.*;
// import java.util.ArrayList;


public class targetset{
    public static void main(String[] args){
        int[] arr = {2,3,5,7};
        int target = 10;
        by_forloopsimplemethod_m1(arr,target);
       
        System.out.println("==============================");
        
        // this is important mehtod as it is used in DP KNAPSACK PROBLEMS
        by_Subsequence_Method_m2(arr, target);
        
    }


    public static void by_forloopsimplemethod_m1(int[] arr,int target){

        System.out.println(ccpinfi_m1(arr, 0, arr.length, target ));
        System.out.println(cccinfi_m1(arr, 0, arr.length, target ));
        System.out.println(cccsingle_m1(arr, -1, arr.length, target ));

        boolean[] visited = new boolean[4];
        System.out.println(ccpsingle_m1(arr, 0, arr.length, target, visited )); 
        System.out.println(ccpsingle_opti_m1(arr, 0, arr.length, target )); 
    }

    public static void by_Subsequence_Method_m2(int[] arr,int target){

        System.out.println(ccpInfinte_m2(target, arr, 0));
        System.out.println(cccInfinite_m2(target, arr, 0));
        System.out.println(cccsingle_m2(target, arr, 0));

        boolean[] visited = new boolean[4];
        System.out.println(ccpsingle_m2(target, arr, 0, visited));
        System.out.println(ccpsingle_opti_m2(target, arr, 0));
    }
    
    
    // target sum with subsequence method
    public static int ccpInfinte_m2(int target, int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;
        
        int totalans = 0;
        
        if(target-arr[idx] >= 0){  // idx element ayega 
            totalans +=  ccpInfinte_m2(target-arr[idx], arr, 0);
        }

        totalans += ccpInfinte_m2(target, arr, idx+1);  // idx element nahi ayega

        return totalans;
    }

    
    // coin change combination, infinite - by the subsequence method
    public static int cccInfinite_m2(int target, int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;

        int count = 0;
        if(target-arr[idx] >= 0){
            count += cccInfinite_m2(target-arr[idx], arr, idx);
        }

        count += cccInfinite_m2(target, arr, idx+1);

        return count;
    }


    // coin change combination,single coin - by the subsequence method
    public static int cccsingle_m2(int target , int[] arr, int idx){
        if(target == 0) return 1;
        if(idx == arr.length) return 0;

        int count = 0;
        
        if(target-arr[idx] >= 0)
            count += cccsingle_m2(target-arr[idx] , arr, idx+1);

        count += cccsingle_m2(target, arr, idx+1);

        return count;
    }

    //coin change permutation, single coin  // kuch ni karna bass visited ka check laga lo(nothing to worry about ki kitne calls lagege)
    //calls to do hi lagegi ek baar ayega aur ek baar nahi ayega vali, tree khud sabhal lega (draw tree sab smaj me ayega)
    public static int ccpsingle_m2(int target, int[] arr, int idx, boolean[] visited){
        if(target == 0){
            return 1;
        }
        if(idx == arr.length) return 0;

        int count = 0;
        // ayega
        if(target-arr[idx] >= 0 &&  !visited[idx] ){
            visited[idx] = true; // mark visited
            count += ccpsingle_m2(target-arr[idx], arr, 0, visited); 
            visited[idx] = false;  // mark unvisited
        }
        
        // nahi ayega
        count += ccpsingle_m2(target, arr, idx+1, visited);

        return count;
    }

    public static int ccpsingle_opti_m2(int target, int[] arr, int idx){
        if(target == 0) {
            return 1;
        }
        if(idx == arr.length) return 0;

        int count = 0;
        // ayega vali call
        if(arr[idx] >= 0 && target-arr[idx] >= 0){
            int val = arr[idx];
            arr[idx] = -val;   // mark visited
            count += ccpsingle_opti_m2(target-val, arr, 0); // note yaha (target-val) agega na ki (target-arr[i]) as arr[i] becomes -ve first
            arr[idx] = val;   // mark unvisited
        }

        // nahi ayega vali call
        count += ccpsingle_opti_m2(target, arr, idx+1);
        
        return count;
    }














    //==============================================================
    // by simple for loop method m1

    // I-1
    // coin change combination infinite coin
    public static int ccpinfi_m1(int[] arr,int si, int ei, int target){  // yaha si lene ki bhi jarurat ni thi as haar baar hame loop ya call 0 to n tak hi karna ha
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si ; i < ei ; i++){
            if(target-arr[i] >= 0){
                ans += ccpinfi_m1(arr, si, ei, target-arr[i]);
            }
        }
        return ans;
    }

    // I-2
    // coin change combination infinite coin
    public static int cccinfi_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si; i < ei ; i++){
            if(target-arr[i] >= 0){
                ans += cccinfi_m1(arr, i, ei, target-arr[i]);
            }
        } 
        return ans;
    }

    // I-3
    // coin change combination single coin
    public static int cccsingle_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;

        int ans = 0;
        for(int i = si+1; i < ei; i++){
            if(target-arr[i] >= 0){
                ans += cccsingle_m1(arr, i, ei, target-arr[i]);
            }
        }
        return ans;
    }

    // I-4
    // coin change permutation single coin
    public static int ccpsingle_m1(int[] arr, int si, int ei, int target, boolean[] visited){
        if(target == 0) return 1;
        int ans = 0;
        
        for(int i = si; i < ei; i++){
            if(target-arr[i] >= 0 && !visited[i] ){
                visited[i] = true;
                ans += ccpsingle_m1(arr, si, ei , target-arr[i], visited);
                visited[i] = false;
            }
        }
        return ans;
    }

    // space optimization of [coin change permutation single coin]
    // hame ussi array me arr[i] ko -arr[i] kar dege which we know acts as a visited property (graph theory) 
    // this algo will not work when array elements are -ve or 0 in input array;
    public static int ccpsingle_opti_m1(int[] arr, int si, int ei, int target){
        if(target == 0) return 1;
        int ans = 0;
        
        for(int i = si; i < ei; i++){
            if(arr[i] >= 0 && target-arr[i] >= 0 ){
                int val = arr[i];
                arr[i] = -val; // mark visited
                ans += ccpsingle_opti_m1(arr, si, ei , target-val);
                arr[i] = val; // mark unvisited
            }
        }
        return ans;
    }


}