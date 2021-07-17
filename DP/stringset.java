import java.util.*;

public class stringset{
    // count subsequence
    public int subsequenceCount(String str){
        int ans = countsubsequence(str, 0);
        int ans = printsubsequence(str, 0, "");
    }

    public int countsubseq(String str, int idx, String psf){
        if(idx == str.length()){
            return 1;
        }
        int count = 0;
        int recans1 = countsubseq(str, idx+1);
        int recans2 = countsubseq(str, idx+1);
        
        count = recans1 + recans2;
        
        return count;
    }

    public int printsubseq(String str, int idx, String psf){
        if(idx == str.length()){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        int recans1 = countsubseq(str, idx+1, psf+str.charAt(idx)+"");
        int recans2 = countsubseq(str, idx+1, psf);

        count = recans1 + recans2;
        
        return count;
    }

    // LONGEST PALLINDROMIC SUBSEQUENCE

    // I. BY LAYMANN WAY (teeno calls laga di bina soche then baad me two condition dekhi bass)
    public int longestPalindromeSubseq(String s){
        if(s.length() == 0) return 0;
        int size = s.length();
        int[][] dp = new int[size+1][size+1];

        for(int i = 0; i < size; i++)  Arrays.fill(dp[i], -1);   // memo me -1 me to dikkat ni ha 
        return solve_memo(s, 0, size-1, dp);
         
        for(int i = 0; i < size; i++)  Arrays.fill(dp[i], 0);    // but tabu me to 0 hi karna hoga(-1 kiya to galat ans ayega)
        return solve_tabu(s, 0, size-1, dp);
    }
    
    // MEMOIZATION
    public int solve_memo(String str, int si, int sj, int[][] dp){
        if(si >= sj) return dp[si][sj] = (si == sj) ? 1 : 0;     
        
        if(dp[si][sj] != -1) return dp[si][sj];
        
        int x = solve_memo(str, si+1, sj, dp);
        int y = solve_memo(str, si +1 , sj-1, dp);  
        int z = solve_memo(str, si, sj-1, dp);
        
        if(str.charAt(si) == str.charAt(sj))
            return  dp[si][sj] = maxofthree(x, y+2, z);
        else
            return dp[si][sj] = maxofthree(x, y, z);
    }

    public int maxofthree(int x, int y, int z){
        return  Math.max(Math.max(x, y), z);
    }
    

    // TABULATION
    public int solve_tabu(String str, int SI, int SJ, int[][] dp){
        int size = str.length();
        for(int gap = 0; gap < size; gap++){
            for(int si = 0, sj = gap; si < size && sj < size; si++, sj++){
                if(si >= sj){
                    dp[si][sj] = si==sj ? 1 : 0;
                    continue;
                } 

                int x = dp[si+1][sj];   // solve(str, si+1, sj, dp);
                int y = dp[si+1][sj-1]; //solve(str, si +1 , sj-1, dp);  
                int z = dp[si][sj-1];   //solve(str, si, sj-1, dp);

                if(str.charAt(si) == str.charAt(sj))
                    dp[si][sj] = maxofthree(x, y+2, z);
                else
                    dp[si][sj] = maxofthree(x, y, z);
            }
        }
        return dp[SI][SJ];
    }
    

    // II. BY PS (PALLINDROMIC SUBSEQUENCE) WAY

    public int longestPalindromeSubseq(String str){
        if(str.length() == 0) return 0;
        int size = str.length();
        int[][] dp = new int[size+1][size+1];
        
        for(int i = 0; i < size; i++)   Arrays.fill(dp[i], -1);
        return solve_memo(str, 0, size-1, dp);

        // for(int i = 0; i < size; i++)   Arrays.fill(dp[i], 0);  // yaha bhi agar -1 value kiya to galat ans ayega   
        // return solve_tabu(str, 0, size-1, dp);

    }

    public int solve_memo(String str, int i, int j, int[][] dp){
        if(i >= j) return dp[i][j] = (i == j) ? 1 : 0;

        if(dp[i][j] != -1) return dp[i][j];
        
        int ans = 0;
        if(str.charAt(i) == str.charAt(j)){
            int recans = solve_memo(str, i+1, j-1, dp);
            ans = recans + 2;
        }else{
            int leftans = solve_memo(str, i+1, j, dp);
            int rightans = solve_memo(str, i, j-1, dp);

            ans = Math.max(leftans, rightans);
        }

        return dp[i][j] = ans;
    }

    // In short
    public int solve(String str, int i, int j){
        if(i >= j) return (i == j)? 1 : 0;

        if(str.charAt(i) == str.charAt(j)){
            return  solve(str, i+1, j-1) + 2;
        }else{
            return  Math.max(solve(str, i+1, j), solve(str, i, j-1));
        }
    }

    public int solve_tabu(String str, int I, int J, int[][] dp){
        int size = str.length();
        for(int gap = 0; gap < size; gap++){
            for(int i = 0, j = gap; (i < size && j < size); i++, j++){
                if(i >= j){
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                } 
                
                int ans = 0;
                if(str.charAt(i) == str.charAt(j)){
                    int recans = dp[i+1][j-1];  // solve_memo(str, i+1, j-1);
                    ans = recans + 2;
                }else{
                    int leftans = dp[i+1][j];  // solve_memo(str, i+1, j);
                    int rightans = dp[i][j-1];  // solve_memo(str, i, j-1);

                    ans = Math.max(leftans, rightans);
                }
                dp[i][j] = ans;
            }
        }
        return dp[I][J];
    }

}