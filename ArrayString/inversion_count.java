// Count Inversions gfg  -> https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1#
// pura merge sort hi ha bass merge sort me ek jagah ek line add karni ha
// jisse haam count karege
// see striver video (you tube)

   static long count = 0;
    public static long inversionCount(long arr[], long n){
        long[] temp = new long[(int)n];
        int idx = 0;
        for(long x : arr) temp[idx++] = x;
        
        count = 0;
        mergesort(temp, 0, (int)n-1);
        
        // for(long x : temp) System.out.print(x + " ");
        return count;
    }
    
    public static void mergesort(long[] arr, int si, int ei){
        if(si >= ei) return;
        int mid = si + (ei-si)/2;
        
        mergesort(arr, si, mid);
        mergesort(arr, mid+1, ei);
        
        mergeTwoSortedArray(arr, si, mid, ei);
    }
    
    public static void mergeTwoSortedArray(long[] arr, int si, int mid, int ei){
        
        long[] left = new long[mid-si+1];
        int idx = 0;
        for(int i = si; i <= mid; i++) left[idx++] = arr[i];
        
        long[] right = new long[ei-mid];
        idx = 0;
        for(int i = mid+1; i <= ei; i++) right[idx++] = arr[i];
        
        // mergeTwoSortedArray function
        int i = 0, j = 0;
        long[] temp = new long[ei-si+1];
        idx = 0;
        while(i != left.length && j != right.length){   // hamesha right.length ye sab hi use karo bajaye mid+1 ke ya ei+1 ke length nikalne ke liye              
            // System.out.println(i + " " + j);
            if(left[i] <= right[j]) temp[idx++] = left[i++];   // equal to vala case yah lagega 
            else if(left[i] > right[j]){
                count += (left.length-i);
                temp[idx++] = right[j++];
            }
        }
        
        while(i != left.length) temp[idx++] = left[i++]; 
        while(j != right.length) temp[idx++] = right[j++];
        
        for(int p = si, k = 0; k < temp.length; k++) arr[p++] = temp[k]; 
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