// NOTE : JAB BHI QUESTION ME CONSTRAINTS ME 10^9 JESA KUCH DIKHE SAMAJ 
// JANA KI BINARY SEARCH LAGG RAHA HA PAKKA.

// LC - 875. Koko Eating Bananas
 public int minEatingSpeed(int[] arr, int H) {
       int a = arr.length;
        int max = arr[0];
        for(int x : arr) max = Math.max(max, x);
        int si = 1, ei = max;
        
        while(si <= ei){
            int mid = (si+ei)/2;
            int time = totalTime(arr, mid);
            
            if(time < H) ei = mid-1;
            else if(time > H) si = mid+1;
            else if(time == H) ei = mid-1;
        }
        return si;
    }
    
    public int totalTime(int[] arr, int s){   // s : speed
        int t = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i]%s == 0){
                t += arr[i]/s;
            }else{
                t += ((arr[i]/s) + 1);
            }
        }
        return t;
    }


//===========================================================================







