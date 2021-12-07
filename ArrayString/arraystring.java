import java.util.io;

public class arraystring{
    public void main(String[] args){

    }

    // lc-003 Longest substring without repeating character
    //O(n3) Solution
    public int lengthOfLongestSubstring(String s){
        int maxlen = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            for(int j = i ; j < n; j++){
                boolean ans = longestsubstring(s, i, j);
                if(ans)  maxlen = Math.max(maxlen, j-i+1);
            }
        }
        return maxlen;
    }

    public boolean longestsubstring(String s, int i, int j){
        boolean[] visited = new boolean[128];

        for(int k = i ; k <= j; k++){
            char ch = s.charAt(k);
            if(visited[ch] == true) return false;
            else{
                visited[ch] = true;
            }
        }
        return true;
    }

    //O(n2) Solution
    public int lengthOfLongestSubstring(String s){
        int maxlen = 0;
        int n = s.length();
        boolean[] visited = new boolean[128];

        for(int i = 0; i < n; i++){
            visited = new boolean[128];
            for(int j = i ; j < n; j++){
                char ch = s.charAt(j);
                if(visited[ch] == true) break;
                else{
                    visited[ch] = true;
                    maxlen = Math.max(maxlen, j-i+1);
                }
            }
        }
        return maxlen;
    }

    //O(n) solution
    public int lengthOfLongestSubstring(String s){
        int maxlen = 0;
        int n = s.length();
        int[] freq = new int[128];
        int si = 0, ei = 0;
        int count = 0;

        while(ei < n){
            // acquire
            if(freq[s.charAt(ei)] == 1 ) count++;
            freq[s.charAt(ei)]++;
            ei++;

            //release
            while(count != 0){
                if(freq[s.charAt(si)] == 2) count--;
                freq[s.charAt(si)]--;
                si++;
            }

            //calculate maxlen
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }

    // lc-76 Minimum Window Substring
    // O(n3)
    public String minWindow(String s, String t) {
        int si = 0, ei = 0;
        int n = s.length();
        int minlen = (int)1e8;
        int gsi = -1, gei = -1;
        
        for(int i = 0; i < n ; i++){
            for(int j = i ; j < n; j++){
                boolean ans = checkSubstring(s,t,i,j);
                if(ans){
                    if(minlen > (j-i+1)){
                        minlen = j-i+1;
                        gsi = i; gei = j;
                    }
                } 
            }
        }
        
        String ans = "";
        if(gsi == -1 && gei == -1) return "";
        for(int k = gsi ; k <= gei; k++){
            ans += s.charAt(k);
        }
        
        return ans;
    }
    

    public boolean checkSubstring(String s,String t, int i, int j){
        int[] freq = new int[128];

        for(int k = i; k <= j; k++)
            freq[s.charAt(k)]++;

        for(int k = 0; k < t.length(); k++)
            if(--freq[t.charAt(k)] < 0) return false;
        
        return true;

    }

    //O(n2)  - (264/266 test cases pass)
    public String minWindow(String s, String t) {
        int minlen = (int)1e9;
        int m = s.length(), n = t.length(); 
        int[] freq = new int[128];
        boolean update = false;
        String finalans = s+"a";
        
        for(int i = 0; i < m; i++){
            freq = new int[128];
            int count = fillFreq(t, freq);
            String temp = "";
            
            for(int j = i ; j < m; j++){
                temp += s.charAt(j);
                if(--freq[s.charAt(j)] == 0){
                    if(--count == 0){
                        minlen = Math.min(minlen, j-i+1);
                        update = true;
                        if(finalans.length() > temp.length())
                            finalans = temp;
                        
                        break;
                    } 
                }
            }
            
        }
        // System.out.println(minlen);
        return (update==true) ? finalans : "";
    }

    public int fillFreq(String t, int[] freq){
        int count = 0;
        for(int i = 0; i < t.length(); i++){
            if(freq[t.charAt(i)] == 0) count++;    
            freq[t.charAt(i)]++;
        }
        return count;
    }

    //O(n) Soution
    public String minWindow(String s, String t){
        int ns = s.length(), nt = t.length();
        int si= 0, ei = 0;
        
        int[] freq = new int[128];
        for(int i = 0; i < nt; i++){
            freq[t.charAt(i)]++;
        }
        
        int count = nt;
        int minlen = (int)1e9;
        int gsi = 0;
        
        while(ei < ns){
            // acquire
            if(freq[s.charAt(ei++)]-- > 0) count--;
            
            // release
            while(count == 0){
                if(minlen > ei-si){
                    minlen = ei-si;
                    gsi = si;
                }
                if(freq[s.charAt(si++)]++ == 0) count++;
            }  
        }
        
        return minlen==(int)1e9 ? "" : s.substring(gsi, gsi + minlen);
    
    }

    //gfg -  smallest window that contains all character of string itself

    // O(n3) Soution Brute force
    public String findSubString( String s) {
        int n = s.length();
        int minlen = (int)1e8;

        int count = 0, gsi = 0;
        int[] freq = new int[128];
        for(int i = 0;i < n; i++)  if(freq[s.charAt(i)]++ == 0) count++;

        for(int i = 0; i < n; i++){
            for(int j = i ; j < n; j++){
                // check each substring for required ans
                boolean check = checksubstring(s, i, j, count);
                if(check){
                    if(minlen > j-i+1){
                        // update
                        minlen = j-i+1;
                        gsi = i;
                    }
                }
            }
        }
        return minlen==(int)1e9 ? "" : s.substring(gsi, gsi+minlen);
    }

    public boolean checksubstring(String s, int si, int ei, int count){
        int[] freq = new int[128];
        int temp = 0; 
        for(int i = si; i <= ei; i++){
            if(freq[s.charAt(i)]++ == 0) temp++;
        }

        return (temp == count)? true: false;
    }


    // O(n2) Solution
    public String findSubString( String s){
        int count = 0, n = s.length();
        int[] freq = new int[128];
        
        for(int i = 0; i < n; i++) 
            if(freq[s.charAt(i)]++ == 0) 
                count++;
       
        int minlen = (int)1e8, temp = 0, gsi = 0;   
           
        for(int i = 0; i < n; i++){
            freq = new int[128]; 
            temp = 0;
             for(int j = i; j < n; j++){
                 if(freq[s.charAt(j)]++ == 0) temp++;
                 
                 if(temp == count){
                    if(minlen > j-i+1){
                        minlen = j-i+1;
                        gsi = i;
                    }
                    break;
                 }
             }
        } 
        return minlen==(int)1e8 ? "" : s.substring(gsi, gsi+minlen);
    }


    //O(n)     by anupam slight difference(dry run karo ko us time pe samaj me aye vo karo, concept samajna ha bass)
    public String findSubString( String s) {
        int si = 0, ei = 0, n = s.length();
        int[] freq = new int[128];

        int count = 0;
        for(int i = 0; i < n; i++ ){
            if(freq[s.charAt(i)]++ == 0) count++;
        }
        int minlen = (int)1e9;
        int gsi = 0;
        freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0 ) count--;

            while(count == 0){
                if(minlen > ei-si){
                    minlen = ei-si;
                    gsi = si;
                }

                if(freq[s.charAt(si++)]-- == 1) count++; 
            }
        }
        return (minlen == (int)1e9) ? "" : s.substring(gsi, gsi+minlen);
    }

    // by sir
    public  String smallestWindowOfItSelf(String str){
        if (str.length() <= 1)
            return str;
        int[] freq = new int[128];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (freq[str.charAt(i)] == 0) {
                freq[str.charAt(i)] = 1;
                count++;
            }
        }

        int actualLen = count;
        int si = 0, ei = 0, gsi = 0, len = (int) 1e9;

        while (ei < n) {
            if (freq[str.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }

            if (len == actualLen)
                break;
        }

        return str.substring(gsi, gsi + len);
    }

    // lc-159 longest substring with at most 2 distint characters 
    //o(n)
    public int lengthOfLongestSubstrigWithtwocharactes(String s){
        int si = 0, ei = 0;
        int n = s.length(), count = 0, maxlen = -(int)1e9;
        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;

            while(count > 2){  // count>= 2 (equal to nahi ayega yaha note it! imp.)
                if(freq[s.charAt(si++)]-- == 1) count--;
            }

            maxlen = Math.max(maxlen, ei-si);
        }

        // return maxlen == -(int)1e9 ? 1 : maxlen; 
        // update to ek baar pakka hua hoga with 1 size window
        return maxlen;
    }

    // lc-159 longest substring with at most k distint characters 
    //o(n)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int n = s.length();
        if(n == 0) return 0;
        int si = 0, ei = 0;
        int count = 0, maxlen = -(int)1e9;
        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;

            while(count > k){
                if(freq[s.charAt(si++)]-- == 1)
                    count--;
            }

            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }

    // Maximum number of vowels in a substring of given length 
    //o(n)
    public boolean checkvowel(char ch ){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    
    public int maxVowels(String s, int k) {
         int n = s.length(), si = 0, ei = 0, vcount = 0, maxvcount = 0;
        
         while(ei < n){
             if(checkvowel(s.charAt(ei++))) 
                 vcount++;
             
             if(ei-si > k) 
                 if(checkvowel(s.charAt(si++))) vcount--;
             
             maxvcount = Math.max(maxvcount, vcount);
         }
         return maxvcount;
    }



    //Question 1. imp.>  Count of all substrings with at most k different Integers

    // since yaha Integer vs Integer store karna ha to hame hashmap hi lena hoga!!
    public int countSubarraysWithAtMostKDistinct(int[] arr, int k) {
        int n = arr.length;
        int si = 0, ei = 0;
        int ans = 0;
        int count = 0;
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        while(ei < n){
            if(hm.getOrDefault(arr[ei], 0) == 0) count++;
            hm.put(arr[ei], hm.getOrDefault(arr[ei], 0) + 1);
            ei++;
            
            while(count > k){
                if(hm.getOrDefault(arr[si], 0) == 1) count--;
                hm.put(arr[si], hm.getOrDefault(arr[si], 0) - 1);
                si++;
            }
            
            ans = ans + (ei-si);
        }
        return ans;
    }

    // agar samaj ni aa raha aur hashmap me dikkat ho rahi ha to 
    // ese kar lo ki pehle string me convert kar lo then vohi kaam kar lo
    // but (hashmap se bhi ana chahiye simple hi ha)
    public int subarraysWithKDistinct(int[] arr , int k) {
        String st = "";
        for(int i = 0; i < arr.length; i++){
            st += arr[i];
        }
        return subarraysWithKDistinct(st, k);
    }
    
    public int subarraysWithKDistinct(String s , int k) {
        int n = s.length();
        int si = 0, ei = 0;
        int count = 0;
        int ans = 0;
        int[] freq = new int[128];
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;
            
            while(count > k){
                if(freq[s.charAt(si++)]-- == 1) count--;
            }
            
            ans = ans + (ei-si);
        }
        return ans;
    }

    // lc - 992  count all substring with k different Integer
    public int subarraysWithKDistinct(int[] arr, int k) {
          return countSubarrayAtMost(arr, k)-countSubarrayAtMost(arr, k-1);
    }
    
    public int countSubarrayAtMost(int[] arr, int k){
        int si = 0, ei = 0;
        int count = 0, n = arr.length;
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        int ans = 0;
        
        while(ei < n){
            if(hm.getOrDefault(arr[ei], 0) == 0) count++;
            hm.put(arr[ei], hm.getOrDefault(arr[ei], 0) + 1);
            ei++;
            
            while(count > k){
                if(hm.getOrDefault(arr[si], 0) == 1) count--;
                hm.put(arr[si], hm.getOrDefault(arr[si], 0) - 1);
                si++;
            }
            
            ans = ans + (ei-si);
        }
            
        return ans;   
    }


    // count number of nice substring
    public int numberOfSubarrays(int[] arr, int k){
        int x = countOfSubarrays(arr, k);
        int y = countOfSubarrays(arr, k-1);
        return (x-y);
    }
    
    public int countOfSubarrays(int[] arr, int k){
        int n = arr.length;    
        int si = 0, ei = 0;
        int count = 0, ans = 0;
        
        while(ei < n){
            if(arr[ei++]%2 != 0) count++;
           
            while(count > k){
                if(arr[si++]%2 != 0) count--;
            }
            ans = ans + (ei-si);
        }
        
        return ans;
    }

    // longest-k-unique-characters-substring
    // https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
    public int longestkSubstr(String s, int k) {
        int si = 0, ei = 0;
        int n = s.length();
        int[] freq = new int[128];
        // extra check 
        int temp = 0;
        for(int i = 0;i < s.length(); i++)
            if(freq[s.charAt(i)]++ == 0) temp++;
        if(temp < k) return -1;
        
        freq = new int[128];
        int maxlen = -(int)1e8;
        int count = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0) count++;
            
            while(count > k){
                if(freq[s.charAt(si++)]-- == 1) count--;
            }
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen==-(int)1e8 ? -1: maxlen;
    }

    // count of all unique substrings with non- repeating characters.
    public int countUniqueNonRepeatingCharacter(String s){
        int n = s.length();
        int si = 0, ei = 0;
        int[] freq = new int[128];
        int count = 0;// count : no of duplicate elements present in the substring
        int ans = 0;
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 1) count++;

            while(count > 0){
                if(freq[s.charAt(si++)]-- == 2) count--;
            }

            ans += (ei-si);
        }
        return ans;
    }

     
    // LC - 930. Binary Subarrays With Sum
    public int numSubarraysWithSum(int[] arr, int sum) {
        if(sum == 0) return AtMost(arr, sum);
        return AtMost(arr, sum) - AtMost(arr, sum-1);
    }
    
    public int AtMost(int[] arr, int sum){
        int si = 0,ei = 0, n = arr.length;
        int zerofreq = 0;
        int onefreq = 0;
        int ans = 0, count = 0;
        
        while(ei < n){
            if(arr[ei] == 1 && onefreq++ == sum) count++;
            ei++;
            
            while(count > 0){
                if(arr[si] == 1 && onefreq-- == sum+1) count--;
                si++;
            }
            
            ans += (ei-si);
        }

    }

    // LC : 485  Max Consecutive Ones 
    // do this way (because same hi concept(amr*u) pe based ha tarika ha issi se karo)
    // kissi aur method se karoge to ei-si vali chij shyd na kar pao
    public int MaxconsecutiveOnes(int[] arr){
        int si = 0, ei = 0, n = arr.length;
        int count = 0, maxlen = -(int)1e8;
        
        while(ei < n){
            if(arr[ei++] == 0) count++;
            
            while(count > 0){
                if(arr[si++] == 0) count--;
            }
            
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }

    // F.U : MAX consecutive zeros
    public int MaxConsecutiveZeros(int[] arr){
        int si = 0, ei = 0, n = arr.length;
        int count = 0, maxlen = -(int)1e8;
        
        while(ei < n){
            if(arr[ei++] == 0) count++;
            
            while(count > 0){
                if(arr[si++] == 0) count--;
            }
            
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }
    
    // F.U : count of subarrays of Ones 
    public int countofsubarraysOnes(int[] arr){
        int si = 0, ei = 0, n = arr.length;
        int count = 0, ans = 0;
        
        while(ei < n){
            if(arr[ei++] == 0) count++;
            
            while(count > 0){
                if(arr[si++] == 0) count--;
            }
            
            ans += ei-si;  // YE KAR DO BASS
        }
        return ans;
    }


    //  LC-487 LINTCODE SUBMITTED
    //O(n2) Solution
    public int findMaxConsecutiveOnes(int[] arr) {
        int maxlen = -(int)1e8;

        maxlen  = Math.max(maxlen , MaxConsecutiveOnes(arr));
        for(int i = 0;i <  arr.length; i++){
            if(arr[i] == 0){
                arr[i] = 1;
                maxlen  = Math.max(maxlen , MaxConsecutiveOnes(arr));
                arr[i] = 0;
            }
            
        }

        return maxlen;
    }

    public int MaxConsecutiveOnes(int[] arr){
        int n = arr.length, si = 0, ei = 0, count = 0;
        int maxlen = -(int)1e8;

        while(ei < n){
            if(arr[ei++] == 0) count++;

            while(count > 0){
                if(arr[si++] == 0 ) count--;
            }
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }

    //O(n) Solution 
    // think the solution in this way : since question me flip karne ke liye bola ha but haam 
    // ese soch sakte ha ki agar ek 0 allowed ho hamare substring me so sirf ek condition change hogi 
    // i.e    while(count > 1)  ab >1 ho jayega bass!!   simple!!

    public int findMaxConsecutiveOnes(int[] arr){
        int si = 0, ei = 0, n = arr.length;
        int count = 0, maxlen = -(int)1e8;
       
       while(ei < n ){
           if(arr[ei++] == 0) count++;

           while(count > 1){
               if(arr[si++] == 0) count--;
           }
           
           maxlen = Math.max(maxlen, ei-si);
       }
       return maxlen;
    }


    //LC : 1004. Max Consecutive Ones III
    public int longestOnes(int[] arr, int k) {
        int si = 0, ei = 0, n = arr.length;
        int count = 0, maxlen = -(int)1e8;
        
        while(ei < n){
            if(arr[ei++] == 0) count++;

            while(count > k)    
                if(arr[si++] == 0) count--;
            
            maxlen = Math.max(maxlen, ei-si);
        }
        return maxlen;
    }


//----------------------------------------------------------------------------------------
    
    // LC - 904 Fruits into baskets
    public int totalFruit(int[] arr) {
        // same concept pe ha At most 2 characters vala question ha
        int si = 0, ei = 0;
        int n = arr.length;
        int count = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        int maxlen = -(int)1e8;
        
        while(ei < n){
            if(hm.getOrDefault(arr[ei], 0) == 0) count++;
            hm.put(arr[ei], hm.getOrDefault(arr[ei], 0) + 1);
            ei++;
            
            while(count > 2){
                if(hm.getOrDefault(arr[si], 0) == 1) count--;
                hm.put(arr[si], hm.getOrDefault(arr[si], 0) - 1);
                si++;
            }
            
            maxlen = Math.max(maxlen, ei-si);
                
        }
        return maxlen;
    }
    
    
    //Note - ye array ko string me convert karke nahi kar sakte as jab double number ka integer ayega and vo convert hoga string me to farak ni pata chalega as [15,16] Integer value in string becomes "1516" which means ek string ha 5 characters ka 1,5,1,6 so it is wrong approch HashMap se hi karna hoga iss question ko
    // wrong approach 
    // [14,14,1,1,14,5,14,1,14,1,5,5,1,24,7,7,8,7,7,12,12,8,23,8,23,8,20,10,0,17] not works for this input
    
//      public int totalFruit(int[] arr){
//         String s = "";
//         for(int i = 0; i < arr.length; i++ ) s = s + arr[i];   
    
//         return totalFruit(s);
//     }
    
//     public int totalFruit(String s) {
//         // same concept pe ha At most 2 characters vala question ah
//         int si = 0, ei = 0;
//         int n = s.length();
//         int count = 0;
//         int[] freq = new int[128];
        
//         int maxlen = -(int)1e8;
//         while(ei < n){
//             if(freq[s.charAt(ei++)]++ == 0) count++;
            
//             while(count > 2){
//                 if(freq[s.charAt(si++)]-- == 1) count--;
//             }
            
//             maxlen = Math.max(maxlen, ei-si);
                
//         }
//         return maxlen;
//     }
    
    // approch -2   =>  since given in constaints that [ 1 <= tree.length <= 40000 ] so we can directly use a array and behave it as frequency map simply
    public int totalFruit(int[] arr) {
        // same concept pe ha At most 2 characters vala question ah
        int si = 0, ei = 0;
        int n = arr.length;
        int count = 0;
        int[] freq = new int[40000+1]; 
        // since given in constaints that [ 1 <= tree.length <= 40000 ]
        
        int maxlen = -(int)1e8;
        while(ei < n){
            if(freq[arr[ei++]]++ == 0) count++;
            
            while(count > 2){
                if(freq[arr[si++]]-- == 1) count--;
            }
            
            maxlen = Math.max(maxlen, ei-si);
                
        }
        return maxlen;
    }




    //----------------------------------------------------------------------------------------

    // LC 560. Subarray Sum Equals K
    public int subarraySumEqualsk(int[] arr, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);   // {sum , frequency}
        int psum = 0;
        int ans = 0;
        
        for(int i = 0 ; i < arr.length; i++){
            psum += arr[i];
            
            if(hm.containsKey(psum-k)){
                int count = hm.get(psum-k);
                ans = ans + count;
            }
            
            hm.put(psum, hm.getOrDefault(psum,0)+1);
        }
        
        return ans;
    }



    //LC 974. Subarray Sums Divisible by K

    // imp test case: of negetive numbers -> (rem jese hi -ve aye usme +k karke hm me add do bass)
    // [2,-6,3,1,2,8,2,1]  , k = 7

    // O(N) time 
    // O(N) space (of hashmap(worst case))
    public int subarraysDivByK(int[] arr, int k){
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0,1);   // {remainder , frequency}
        int psum = 0;  
        int ans = 0;   
        
        for(int i = 0; i < arr.length; i++){
            psum += arr[i];
            int rem = psum%k;
            if(rem < 0) rem += k;
            
            if(hm.containsKey(rem)){
                int count = hm.get(rem);
                ans += count;
            }
            
            hm.put(rem, hm.getOrDefault(rem, 0)+1);
        } 
        
        return ans;
    }

    // O(n2) Solution
    public int subarraysDivByK (int[]arr, int k)
    {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum = 0;
            for (int j = i; j < arr.length; j++)
            {
                sum += arr[j];
                if (sum % k == 0)
                count++;
            }
        }
        return count;
    }











































    // kadane's algorithm questions

    public int numSubmatrixSumTarget(int[][] grid, int target) {
       
    }



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //longest substring with At most two distict characters
    













    



    // LC - 152. Maximum Product Subarray
    // MAXIMUM PRODUCT SUBARRAY

    public int maxProduct(int[] nums){
        int prefix = 1;  // prefix product
        int suffix = 1;  // suffix product
        int n = nums.length; 
        int maxProduct = nums[0];
        
        for(int i = 0; i < n; i++){
            if(prefix == 0){
                prefix = nums[i];
            }else{
                prefix = prefix * nums[i];
            }
            
            if(suffix == 0){
                suffix = nums[n-1-i];
            }else{
                suffix = suffix * nums[n-1-i];
            }
            
            maxProduct = Math.max(maxProduct, prefix);
            maxProduct = Math.max(maxProduct, suffix);
        }
        return maxProduct;        
    }



    
    


}