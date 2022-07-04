// Count Inversions gfg  -> https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1#
// pura merge sort hi ha bass merge sort me ek jagah ek line add karni ha
// jisse haam count karege 
// NOTE : count increment jab hoga jab arr1 ka (i) pointer aage jayega i.e [count += j] 

    static long count = 0;
    public static long inversionCount(long arr[], long n) {
        count = 0;
        mergesort(arr, 0, (int)n-1);
        return count;
    }

    public static long[] mergesort(long[] arr, int si, int ei){
        if(si == ei) return new long[]{arr[si]};
        int mid = (si+ei)/2;
        
        long[] left = mergesort(arr, si, mid);
        long[] right = mergesort(arr, mid+1, ei);
        
        long[] ans = mergeTwoSortedArray(left, right);
        return ans;
    }
    
    public static long[] mergeTwoSortedArray(long[] left, long[] right){
        int n = left.length, m = right.length;
        long[] ans = new long[n+m];
        int i = 0, j = 0, k = 0;
        while(i != n && j != m){
            if(left[i] <= right[j]){
                ans[k++] = left[i++];
                count += j;           // haam count me increment yaha hi karege as jese hi i aage jayega that means previous ith position pe jo element that vo sare (0,j) element se bada tha isliye we add [count+=j]
            }else {
                ans[k++] = right[j++];
            }
        }
        
        while(i != n){ ans[k++] = left[i++] ; count += j;}  // yaha bhi [count+=j] karna ha
        while(j != m) ans[k++] = right[j++];
        
        return ans;
    }
    
    
    
 //---------------------------------------------------------------------------
    
    
    // brute force O(N^2) solution lame approach
    // public static long inversionCount(long arr[], long n)
    // {
    //     // Your Code Here
    //     long count = 0;
    //     for(int i = 0; i < n; i++){
    //         for(int j = i+1; j < n; j++){
    //             if(arr[i] > arr[j]) count++;
    //         }
    //     }
    //     return count;
    // }


//==========================================================================================================


// LC - 493. Reverse Pairs

    // YE QUESTION -> count inversion vala ha bilkul bass thoda updated version ha
    // bass yaha mergeTwoSortedArray() function me haam 
    // count ki calculation alag karege and 
    // mergetwosort ki alag calculation karege
    static int count;
    public int reversePairs(int[] arr){
        int n = arr.length;
        count = 0;   // ye karna bhot jaruri ha varna ans galat ata ha submit karne pe
        
        int[] ans = mergesort(arr, 0, n-1);
        return count;
    }
    
    public int[] mergesort(int[] arr, int si, int ei){
        if(si == ei) return new int[]{arr[si]};
        
        int mid = (si+ei)/2;
        int[] lans = mergesort(arr, si, mid);
        int[] rans = mergesort(arr, mid+1, ei);
        
        int[] myans =  mergeTwoSortedArray(lans, rans);
        
        return myans;
    }
    
    public int[] mergeTwoSortedArray(int[] left, int[] right){
        // 1st work -> count calculation
        int i = 0, j = 0, n = left.length, m = right.length;
        while(i != n){
            while(j != m && left[i] > (long)2*right[j])  j++;     // long ayega 2*right[i] me (overflow cond.)  
            i++;
            count += j;
        }
        
        // 2nd work ->  now merge sort
        i = 0; j = 0; 
        int k = 0;
        int[] ans = new int[n+m];
        while(i != n && j != m){
            if(left[i] <= right[j]){
                ans[k++] = left[i++]; 
            }else{
                ans[k++] = right[j++];
            }
        }
        
        while(i != n) ans[k++] = left[i++];
        while(j != m) ans[k++] = right[j++];
        
        return ans;
    }
    
    
    
 
//==========================================================================================================


   
    