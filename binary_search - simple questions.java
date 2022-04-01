// INBUILT
int idx = Arrays.binarySearch(arr, key);     ||     int idx = Collections.binarySearch(arr, key); // for arraylist
// if key present in arr ->  idx is the position at which key present
// if key not present in arr then it returns ->   -(x)-1     ,where x = insertion point

// so if we want to retrieve x(insertion point) then do this
int insertpos = -idx-1;


// QUESTIONS :-

// LC-153. Find Minimum in Rotated Sorted Array
// using binary search
    // ham yaha (mid) & (ei) ko dek ke decision lege
    // dry run it and then code
    // NOTE: [ei = mid] ayega and == equal to vala case bhi ese hi likhna padega nahi to ni chalega
    // ditto esa hi karna ha (chahe to raat lo)
    public int findMin(int[] arr) {
        int n = arr.length, si = 0, ei = n-1;  
        
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] < arr[ei]) ei = mid;
            else if(arr[mid] > arr[ei]) si = mid+1;
            else return arr[mid];
        }
        return 0;
    }

//================================================================================

// LC-1011. 33. Search in Rotated Sorted Array

    // concept : firstly we will find the minimum number index using binary search i.e using SAME LOGIC APPLIED IN LC-153. Find Minimum in Rotated Sorted Array
    // so we will get the break point 
    // NOW APPLY BS IN [0,break_pt-1] range and 
    // APPLY BS IN [break_pt, n-1] range 
    // and return the ans
    public int search(int[] arr, int target) {
        int break_pt = bs(arr);
        int n = arr.length;
        // search in left sorted array
        int si = 0, ei = break_pt-1;
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] < target) si = mid+1;
            else if(arr[mid] > target) ei = mid-1;
            else return mid;
        }
        
        
        // searching in the right soted array
        si = break_pt; ei = n-1;
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] < target) si = mid+1;
            else if(arr[mid] > target) ei = mid-1;
            else return mid;
        }
        return -1;
    }
    
    public int bs(int[] arr){
        int n = arr.length, si = 0, ei = n-1;
        
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] < arr[ei]) ei = mid;
            else if(arr[mid] > arr[ei]) si = mid+1;
            else return mid;
        }
        return 0;
    }