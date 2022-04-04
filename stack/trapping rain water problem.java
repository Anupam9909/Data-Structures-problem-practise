// LC-42. Trapping Rain Water
 
// O(n) time & O(n) space
    // simple ha NGOR AND NGOL nikalne ki bhi jarurat ni ha (bina stack ke hi ho jata ha)
    // bus yaha ye concept lagao
    //  concept:
    // stand at the ith position
    // left se max building ki length le aao (say x)
    // right se max building ki length le aao (say y)
    // water level above the ith block is = arr[i] - [Math.abs(x-y)]
    // buss sab jagah calculate kar lo ye \
    
    public int trap(int[] arr) {
        int n = arr.length;
        int[] MGOL = new int[n];  // max greater on left
        int max = arr[0];
        for(int i = 0; i < n ;i++){
            max = Math.max(max, arr[i]);
            MGOL[i] = max;
        }
        
        int[] MGOR = new int[n];  // max greater on right
        max = arr[n-1];
        for(int i = n-1; i>=0 ; i--){
            max = Math.max(max, arr[i]);
            MGOR[i] = max;
        }
        
        // making ans
        int count = 0;
        for(int i = 0; i < n;i++){
            int val = Math.min(MGOL[i], MGOR[i]);
            int height = Math.abs(val-arr[i]);
            count += height;
        }
        
        return count;
    }
    

    // OPTIMISED VERSION:
    // O(n) time & o(1) space -> can ask in interview

    // yaha haam two pointer see bhi vahi kaam kar sakte ha ( where-> si = 0, ei = n-1)
    // concept: leftmax and rightmax le ke chalte ha haar itteration me
    // if(arr[si] < arr[ei]) // means haam si ke upar apna ans bana sakte ha as right me kitni bhi badi building ku na ho leftmax ki building se to pani nikal jayega
    // similarly with other condition i.e
    // if(arr[si] > arr[ei]) // means haam ei ke upar apna ans bana sakte ha as left me kitni bhi badi building ku na ho rightmax ki building se to pani nikal jayega
    public int trap(int[] arr){
        int n = arr.length;
        
        int maxleft = 0, maxright = 0;
        int si = 0, ei = n-1;
        int ans = 0;
        
        while(si <= ei){
            maxleft = Math.max(maxleft, arr[si]);  // update maxleft and maxright values
            maxright = Math.max(maxright, arr[ei]);
            
            if(arr[si] < arr[ei]){  // calculating the water above si building
                int height = Math.min(maxleft, maxright);
                ans += (height-arr[si]);
                si++; 
            }
            else if(arr[si] > arr[ei]){  // calculating the water above si building
                int height = Math.min(maxleft, maxright);
                ans += (height-arr[ei]);
                ei--;
            }
            
            
        }
        return ans;
    }
    
    