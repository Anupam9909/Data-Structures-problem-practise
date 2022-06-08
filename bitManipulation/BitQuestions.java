// LC-191. Number of 1 Bits

    // bit solution -> yaha haam direct number ko hi operation ke sath kelte ha (^,& etc) 
    // and issi ke through apna answer find karte ha

    public int hammingWeight(int n){
        int  count = 0;
        
        while(n != 0){
            int val = (n & 1);
            if(val == 1) count++;
            
            n = n>>>1;  // num ko hi right shift kar dege taki next itteration me n kam ho and (n & 1) karne pe sahi vala bit 1 ke sath AND ho
        }
        return count;
    }
    
    
    // basic way -> yaha pehle number ko pura convert karna padta ha binaryString me then 
    // uska use karte ha solution find karne me
    
//     public int hammingWeight(int n) {
//         String N = Integer.toBinaryString(n);
//         int count = 0;
        
//         for(int i = 0; i < N.length(); i++)  if(N.charAt(i)=='1')  count++;
        
//         return count;
//     }


