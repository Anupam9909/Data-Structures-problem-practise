// 1047. Remove All Adjacent Duplicates In String   EASY CATEGORY LEETCODE  
public String removeDuplicates(String s) {
    Stack<Character> st = new Stack<>();
    
    for(int i = 0; i < s.length(); i++){
        char ch = s.charAt(i);
        
        if(st.size() != 0 && st.peek() == ch){
            st.pop();
        }else{
            st.push(ch);
        }
    }
    
    StringBuilder sb = new StringBuilder();
    while(st.size() != 0){
        sb.append(st.pop());
    }
        
    return sb.reverse().toString();
}


// 1209. Remove All Adjacent Duplicates in String II
// simply pair bana lo [char-freq] ka and use this in stack 
public class pair{
    char ch;
    int freq;
    pair(char c, int f){
        this.ch = c;
        this.freq = f;
    }
}

public String removeDuplicates(String s, int k) {
    Stack<pair> st = new Stack<>();
    int n = s.length();
    
    for(int i = 0 ; i < n; i++){
        char ch  = s.charAt(i);
        
        if(st.size() != 0 && st.peek().ch == ch ){
            st.peek().freq++;
            if(st.peek().freq == k) st.pop();
        }else{
            st.push(new pair(ch,1));
        }
    }
    
    StringBuilder sb = new StringBuilder();
    int size = st.size();
    
    for(int i = 0 ; i < size; i++){
        pair p = st.pop();
        char ch = p.ch;
        int f = p.freq;
        for(int j = 0; j < f; j++){
            sb.append(ch);
        }
    }
    
    return sb.reverse().toString();       
    
}


//==============================================================================


// acha ha impliment karna
// LC-316. Remove Duplicate Letters

// freq array bana lo 
    // stack bana lo 
    // and ek visited ka array bhi le lo
    // and just dry run karo 
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] freq = new int[26];
        
        for(char c : s.toCharArray()){
            freq[c-'a']++;
        }
        
        boolean[] vis = new boolean[26];
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            int idx = ch-'a';
            freq[idx]--;
            
            if(vis[idx] == false){ 
                vis[idx] = true;
                while(st.size() != 0 && freq[st.peek()-'a'] > 0 && st.peek() > ch){
                    char temp  = st.pop();
                    vis[temp-'a'] = false;
                }
                
                st.push(ch); 
            }            
            
        }
        
        // making ans
        StringBuilder sb = new StringBuilder();
        while(st.size() != 0){
            sb.append(st.pop());
        }
        
        return sb.reverse().toString();
    }