// LC-647. count all Palindromic Substrings

// ye basically dp ki problem ha hi ni
// steps:-
// 1. gap stratergy lagegi
// 2. sidha dp ka table banao and then condition lagao
// 1st condition : if(gap == 0) then yes pallindrome
// 2nd condition : if(gap == 1 && s[i] == s[j]) then yes palindrome
// 3rd condition : check for prev and i.e -> dp[i+1][j-1] === reccall(i+1, j-1)
    
// now jo bhi dp table me true hoge vo index (i,j) represent karega ki it is yes pallindrome
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        
        
        // palindromic substring
        
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; i < n && j < n; i++, j++){
                if(i == j) dp[i][j] = true;
                else if(gap == 1){
                    if(s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                    else dp[i][j] = false;
                }  
                else{
                    if(s.charAt(i) != s.charAt(j)) dp[i][j] = false;
                    else{
                        dp[i][j] = dp[i+1][j-1]; 
                    }
                }
            }
        }
        
        // counting total true in the dp for the count ans 
        int count = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(dp[i][j]) count++;
        
        
        return count;
        
    }


//==========================================================================

// LC-5. Longest Palindromic Substring

// since plaindrome in SUBSTRING ha means vohi dp direct dp tabu vala concept lagega 
    
    public String longestPalindrome(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int len = 0, si = 0, ei = 0;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; i < n && j < n; i++, j++){
                if(gap == 0) dp[i][j] = true; 
                else if(gap == 1) dp[i][j] = (s.charAt(i) == s.charAt(j)) ? true : false;
                else{
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? dp[i+1][j-1] : false;
                }
                
                // extra thing to do 
                if(dp[i][j] == true){
                    if(Math.abs(ei-si) < Math.abs(j-i)){
                        si = i;
                        ei = j;
                    }
                }
            }
        }
        
        
        return s.substring(si, ei+1);
    }
    
    
//========================================================================
// NOT A QUESTION BUT 
// AGAR SARE PALLINDROMIC SUBSTRING PRINT KARNE HA TO

// since plaindrome in SUBSTRING ha means vohi dp direct dp tabu vala concept lagega 
    
    public String longestPalindrome(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int len = 0, si = 0, ei = 0;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; i < n && j < n; i++, j++){
                if(gap == 0) dp[i][j] = true; 
                else if(gap == 1) dp[i][j] = (s.charAt(i) == s.charAt(j)) ? true : false;
                else{
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? dp[i+1][j-1] : false;
                }
            }
        }

	//  jiss jiss jagah true ha vaha print the answers
       for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j]){
                    System.out.println(s.substring(i,j+1));
                }
            }
        }

	return;
    }
    
    