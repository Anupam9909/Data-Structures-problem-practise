// ese type of question me pata ni chalta ki binary search lagega 
// jab bhi ek (arr) array aur ek (k) value de rakhi ho question me and  
// usme monotonic(ya to sirf increasing(equal ho sakte ha) -ya- to sirf decreasing(equal ho sakte ha)] jese answer ban rahe ho 
// then samaj jao ki binary search lag sakta ha, 
// but NOTE : ye question hard problem ke hote ha so if question easy category me ha then vo greedy se solve ho raha hoga bajaye iss binary search ke concept ke

// ARTICLE:
// https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems

// LC-1011. Capacity To Ship Packages Within D Days

    public int shipWithinDays(int[] wt, int DAYS) {
        int max = wt[0];
        
        for(int x : wt) max = Math.max(x, max);
        
        int si = max, ei = (int)1e9;
        
        while(si <= ei){
            int mid = (si+ei)/2;
            
            int day = caldays(wt, mid);
            // System.out.println(mid + " " + day);
            if(day > DAYS) si = mid+1;
            else ei = mid-1;
        }
        return si;
    }
    
    public int caldays(int[] arr, int ocap){
        int count = 1, cap = ocap;
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > cap){
                count++;
                cap = ocap-arr[i];
            }else{
                cap = cap-arr[i];
            }
        }
        return count;
    }


//================================================================================
// YE QUESTION BHI SHIP WITHIN D DAYS VALA HI HA BILKUL(concept same ha)
// within D days === At most k operation 

// LC-1760. Minimum Limit of Balls in a Bag
 
    public int minimumSize(int[] nums, int maxop) {
        int si = 1, ei = 1000000000;
        
        while(si <= ei){
            int mid = si + (ei-si)/2;
            
            int op = operation(nums, mid);
            if(op > maxop) si = mid+1;
            else ei = mid-1;
        }
        
        return si;
    }
    
    public int operation(int[] nums, int x){
        int count = 0;
        
        for(int i = 0; i < nums.length; i++){
            int val = nums[i];
            // ye dry run karo pata lagega 
            if(val > x){
                if(val%x == 0) count += (val/x - 1);
                else count += (val/x);
            }
        }
        return count;
    }

            
//================================================================================

// LC-410. Split Array Largest Sum

    // same as ->  LC-1011. Capacity To Ship Packages Within D Days [Medium] 
    // vohi binary search ka advance vala concept use hoga 
    // bass long(1e14), (long)1e18 overflow ka bhi dhyan rakhna ha
    public int splitArray(int[] arr, int m) {
        int n = arr.length;
        long si = 0, ei = (long)1e14;
        
        while(si <= ei){
            long mid = si + (ei-si)/2;
            
            long val = split(arr, mid);
            if(val > m) si = mid+1;
            else  ei = mid-1;
        }
        
        return (int)si;
    }
    
    
    public long split(int[] arr, long x){
        long count = 1, temp = 0;
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > x) return (long)1e18;
            temp += arr[i];
            
            if(temp > x){
                count++;
                temp = arr[i];
            }
        }
        return count;
    }



//================================================================================

// LC-540. Single Element in a Sorted Array

    // O(log n) solution
    // binary search
    // Intution: keep dividing your array in two halves and check in which half there are odd number of elements...that will be your required part.
// jiss taraf odd length hogi uss taraf jana ha binary search me


    public int singleNonDuplicate(int[] arr){
        int n = arr.length;
        if(n == 1) return arr[0];
        
        // edge case check -> 0 index & last index 
        if(arr[0] != arr[1]) return arr[0];
        if(arr[n-1] != arr[n-2]) return arr[n-1];
        
        int si = 0, ei = n-1;
        
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] == arr[mid+1]){
                int leftsize = mid-si;
                
                if(leftsize%2 == 0) si = mid;    // len is even
                else ei = mid-1;  // len is odd
            }
            else if(arr[mid] == arr[mid-1]){
                int leftsize = mid-1-si;
                
                if(leftsize%2 == 0) si = mid+1;   // len is even 
                else ei = mid;    // len is odd
            }
            else{   // element found
                return arr[mid];
            }
        }
        
        // yaha kabhi ni ayega as given in question ans exist
        return 0;
    }


//================================================================================

// LC-287. Find the Duplicate Number

    // interview question ha ye bhi
    // same concept of linkedlist cycle II 
    // hame starting point of the cycle nikalna ha
    // O(n) time , O(1) space complexity

    public int findDuplicate(int[] arr){
         int slow = arr[0], fast = arr[0];
        
        while(true){
            slow = arr[slow];
            fast = arr[arr[fast]];
            if(slow == fast) break;
        }
        // System.out.println(slow + " " + fast);
        
        fast = arr[0];
        while(slow != fast){
            slow = arr[slow];
            fast = arr[fast];
        }
        
        return fast;
    }
    
    
    
    // brute force
    // n time , n space complexity
//     public int findDuplicate(int[] nums) {
//         int n = nums.length;
//         int[] freq = new int[n];
        
//         for(int i = 0; i < n ; i++){
//             freq[nums[i]]++;
//         }
        
//         for(int i = 0; i < n ;i++) 
//             if(freq[i] != 1 && freq[i] != 0) return i;

//         return 0;
//     }
    


//================================================================================

// concept : read this ->   https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems
// LC-1482. Minimum Number of Days to Make m Bouquets

   public int minDays(int[] arr, int m, int k) {
        int n = arr.length, si = 0, ei = 1000000000;
        if(n < m*k) return -1;
        
        while(si <= ei){
            int mid = si + (ei-si)/2;
            
            int day = solve(arr, mid, k);
            
            if(day < m) si = mid+1;
            else ei = mid-1;
        }
        
        return si;
    }
    
    public int solve(int[] arr, int mid, int k){
        int n = arr.length, count = 0, ans = 0;
        
        for(int i = 0; i < n; i++){
            if(arr[i]-mid <= 0) count++;
            else{
                ans = ans + (count/k);
                count = 0;
            }
        }
        ans = ans + (count/k);
        return ans;
    }

//================================================================================

// LC-668. Kth Smallest Number in Multiplication Table

// so, jab bhi kth vala question hame O(logn) me hi solve karna ho to use binary search vala method
     // ye question lagta ha ki heap ka ha but heap se nahi hoga TLE ayega ya MLE(list me add karege to space limit excedded ho jayega)
    // binary search approach se karo
    // since it has some monotonic concept so vohi binary search vali trick lagayege 
    
    // READ THIS ->  https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems
   
    // crux : yaha haam (number of element less than mid) or (number of element greater than mid) se calculate kar ke binary search lagate ha
    public int findKthNumber(int m, int n, int k) {
        int si = 1, ei = m*n;
        
        while(si <= ei){
            int mid = si + (ei-si)/2;
            
            int nums = solve(m, n, mid);  // solve() return total ele less than mid
            
            if(nums < k) si = mid+1; 
            else ei = mid-1;
        }
        
        return si;
    }
    
    public int solve(int row , int col , int data){
        int count = 0; 
        for(int i = 1; i <= row; i++){
            int t = (data/i);
            if(t > col) count += col;
            else count += t;
        }
        return count;
    }
    
    // concept for solve() function
    // note : haar row i ka table ha i.e 2nd row -> 2 ka table ha
    //                                   3rd row -> 3 ka table ha 
    //                                   4th row -> 4 ka table ha
    // so, number of element in a row less than mid = (mid/i);


//================================================================================

// LC-719. Find K-th Smallest Pair Distance
// yaha yahi solution sirf work karega pq ka solution will not work either it will give TLE or MLE

// O(nlogn) time solution 
    // using binary search
    public int smallestDistancePair(int[] arr, int k){
        int max = arr[0];
        for(int x : arr) max = Math.max(max, x);
        int si = 0, ei = max;  // max lena jaruri ha as yaha 1000000000 nahi chala TLE aya so this is new 
        Arrays.sort(arr);  // ye solve function me kaam ayega
        
        while(si <= ei){
            int mid = si + (ei-si)/2;
            
            int numele = solve(arr, mid);
            
            if(numele < k) si = mid+1;
            else ei = mid-1;
        }
        return si;
    }
    
    // jo bhi pair ek Math.abs() chote hoge data se vo count kar lege and break kar dege jese hi koi bada diff mile as sare sorted order me ha so aage to garanteed ha ki nahi milega so break is v.Imp
    public int solve(int[] arr, int data){
        int count = 0, n = arr.length;
        // arr sort to pehle hi kar diya
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int val = Math.abs(arr[i]-arr[j]);
                if(val <= data) count++;
                else break;
            }
        }
        return count;
    }
    

