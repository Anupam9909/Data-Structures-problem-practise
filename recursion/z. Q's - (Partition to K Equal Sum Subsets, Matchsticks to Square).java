// LC - 698. Partition to K Equal Sum Subsets

    // yahi method use karo to solve  -> aur kahi se solution mat dekna confusion hogi just see this solution and make logic
    // => yaha haam haar element ko choice de rahe ha ki vale bucketSum me ana chahta ha
    // and then at last jab ek bucket bhar jayega then haame base case me dubara dusre bucket ko bharne ke liye bej dege till hamara Kth bucket na bhaar jaye target ke equal 
    
    // NOTE 2. yaha vo vala recurssion lagega jaha 2 base case hote ha and 2nd base case me dubara recurssion call lagti ha 
    public boolean canPartitionKSubsets(int[] arr, int k){
        int n = arr.length, sum = 0;
        for(int x : arr) sum += x;
        
        if(sum%k != 0) return false;
        int target = sum/k;
        
        boolean[] vis = new boolean[n];
        int bucketNum = 1, bucketSum = 0;
        
        boolean ans = solve(arr, 0, vis, bucketNum, k, bucketSum, target);
        return ans;
    }
    
    public boolean solve(int[] arr, int idx, boolean[] vis, int bucketNum, int k, int bucketSum, int target){
        if(idx >= arr.length) return false;
        if(bucketNum == k) return true;  
        if(bucketSum == target) return solve(arr, 0, vis, bucketNum+1, k, 0, target);
        
        boolean ans = false;
        for(int i = idx; i < arr.length; i++){
            if(!vis[i] && bucketSum+arr[i] <= target){  //  (bucketSum+arr[i] <= target) ye imp. ha nahi to TLE ayega
                vis[i] = true;
                ans = ans || solve(arr, i+1, vis, bucketNum, k, bucketSum+arr[i], target);
                vis[i] = false;
            }
        } 
        
        return ans;
    }
    


//======================================================================================================================================



// LC - 473. Matchsticks to Square

// haame array ko divide karna ha 4 parts me taki charo equal ho jaye (tabhi to square banega)
    // pura yahi question ha bass yaha k = 4 fix ho jayega baki exact same logic ha
    // so recurssion ka question ha ye ussi se hoga
    public boolean makesquare(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, sum = 0;
        int k = 4;
        for(int x : arr) sum += x;
        if(sum%k != 0) return false;
        int target = sum/k;
        
        boolean[] vis = new boolean[n];
        int bucketNumber = 1, bucketSum = 0;
        
        boolean ans = solve(arr, 0, vis, bucketNumber, k, bucketSum, target);
        return ans;       
    }
    
    // ye start_idx -> arr ke elements ke liye ha
    public boolean solve(int[] arr, int idx, boolean[] vis, int bucketNum, int k, int bucketSum, int target){
        if(idx >= arr.length) return false;
        if(bucketNum == k) return true;  
        if(bucketSum == target) return solve(arr, 0, vis, bucketNum+1, k, 0, target);
        
        boolean ans = false;
        for(int i = idx; i < arr.length; i++){
            if(!vis[i] && bucketSum+arr[i] <= target){  //  (bucketSum+arr[i] <= target) ye imp. ha nahi to TLE ayega
                vis[i] = true;
                ans = ans || solve(arr, i+1, vis, bucketNum, k, bucketSum+arr[i], target);
                vis[i] = false;
            }
        } 
        
        return ans;
    }

    

//======================================================================================================================================


