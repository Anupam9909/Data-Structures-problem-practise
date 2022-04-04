// LC-263. Ugly Number

    // yaha vo method kaam nahi karega i.e vo Nth ugly number vala
    // because of this =>   -231 <= n <= 231 - 1
    // and itna bada array to ni bana sakte
    
    public boolean isUgly(int n) {
        if(n <= 0) return false; // ugly number hamesha 1 se bade hote ha(or equal to 1)
        int val = n;
        while(val%2 == 0) val = val/2;   // sare 2 ke factor khatam kar do val me se
        while(val%3 == 0) val = val/3;   // sare 3 ke factor khatam kar do val me se
        while(val%5 == 0) val = val/5;   // sare 5 ke factor khatam kar do val me se
        
        // last me agar 1 hoga that means sare factors 2, 3, 5 hi the pakka tabhi sare khatam ho gye
        if(val == 1) return true;  
        else return false;  
        // agar 1 ni ha that means pakka koi aur factor bacha hoga i.e 7,13 etc so return false
       
    }


//=========================================================================================

// LC-264. Ugly Number II

    // see from 9:08 in the you tube video
    // https://www.youtube.com/watch?v=Lj68VJ1wu84
    // jese fibonaci ha vese hi ha 
    // three pointer ka khel ha
    // raat lo
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;  // three pointer initially pointing at 0 index
        
        for(int i = 1; i < n; i++){
            int a = arr[p2]*2;
            int b = arr[p3]*3;
            int c = arr[p5]*5;
            
            int min = Math.min(Math.min(a,b), c);
            arr[i] = min;
            
            if(arr[p2]*2 == min) p2++;
            if(arr[p3]*3 == min) p3++;
            if(arr[p5]*5 == min) p5++;
        }
        
        return arr[n-1];
    }