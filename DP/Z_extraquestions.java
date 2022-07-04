// LC - 139. Word Break

    // simple pehle recurssion se socho and hashset use hoga yaha
    // and finally dp use hogi yaha to avoid TLE
    // concept : haam given string me simply travese karege jab tak koi esa string ni milta jo ki present ho hashset agar mil gya then again recurrsion call se next substring ka ans le ayege
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hs = new HashSet<>();
        for(String x : wordDict) hs.add(x);
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        boolean ans = wordExist(s, 0, hs, dp);
        return ans;
        
    }
    
    public boolean wordExist(String s, int idx, HashSet<String> hs, int[] dp){
        if(idx == s.length()) return true;
        if(dp[idx] != -1) return dp[idx]==0?false : true;
        
        StringBuilder sb = new StringBuilder();
        while(idx != s.length()){
            sb.append(s.charAt(idx));
            idx++;
            if(hs.contains(sb.toString())){
                boolean recans = wordExist(s, idx, hs, dp);
                if(recans){
                    dp[idx] = 1;
                    return true;
                }else{
                    dp[idx] = 0;
                }
            }
        }
        dp[idx] = 0;
        return false;
    }

//==================================================================================================