//  LC - 208. Implement Trie 

// NOTE: sirf concept ke liye striver ki video se dek lo   ->   https://www.youtube.com/watch?v=dBGUmUQhjaM
// and code ese hi karna ha aur kahi se ni yahi sabse simple and easy to code ha

public class TrieNode{
    TrieNode[] arr = new TrieNode[26];
    boolean flag = false;
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String str) {
        int n = str.length();
        TrieNode curr = root;
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(curr.arr[ch-'a'] == null){
                TrieNode temp = new TrieNode();
                curr.arr[ch-'a'] = temp;
                curr = temp;
            }else{
                curr = curr.arr[ch-'a'];
            }
        }
        curr.flag = true;
    }
    
    // search() similar sa hi ha insert() ke jesa ha 
    public boolean search(String str) {
        int n = str.length();
        TrieNode curr = root;
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(curr.arr[ch-'a'] == null){
                return false;
            }else{
                curr = curr.arr[ch-'a'];
            }
        }
        if(curr.flag == true) return true;
        else return false;
    }
    
    // ye startsWith() pura copy paste ha search() ke bass last me jo do line ha search() me uski jagah direct likh dena ha return true; because hame to bass yahi check karna tha ki startwith ha ki ni
    public boolean startsWith(String str) {
        int n = str.length();
        TrieNode curr = root;
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(curr.arr[ch-'a'] == null){
                return false;
            }else{
                curr = curr.arr[ch-'a'];
            }
        }
        return true;
    }
}


//===============================================================================================================

// LC - 211. Design Add and Search Words Data Structure
// just for practise the concept

// iss question ko karne ke liye trie ka concept pata hona chahiye tabhi ban payega 
// as haame trie me hi add() function me kuch update karna ha solution ho jayega
// concept : haam yaha add() me to 27 size ka le lege to add . letter also
// and basically search() function me haam recrssion use karege becuase jab bhi string me . dot mile to haam sare 27 character ko search kare ki string present ha ki nahi so recurssion lagao tasalli se ho jayega  
public class WordDictionary {

    public class TrieNode{
        TrieNode[] arr = new TrieNode[27];
        boolean flag = false;
    }

    public class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }

        public void add(String str){
            int n = str.length();
            TrieNode curr = root;
            for(int i = 0; i < n; i++){
                char ch = str.charAt(i);
                if(ch == '.'){
                    TrieNode nn = new TrieNode();
                    curr.arr[27] = nn;
                    curr = nn;
                }
                else if(curr.arr[ch-'a'] == null){
                    TrieNode nn = new TrieNode();
                    curr.arr[ch-'a'] = nn;
                    curr = nn;
                }
                else{
                    curr = curr.arr[ch-'a'];
                }
            }
            curr.flag = true;
        }

        public boolean search(String str){
            int n = str.length();
            return solve(str, root, 0);
        }

        public boolean solve(String str, TrieNode root, int idx){
            if(root == null){
                return false; 
            }
            if(idx == str.length() ){
                if(root.flag == true) return true;
                else return false;
            }
            
            char ch = str.charAt(idx);
            boolean ans = false;
            if(ch=='.'){
                for(int i = 0; i < 27; i++){
                    ans = ans || solve(str, root.arr[i], idx+1);
                }
            }
            else if(root.arr[ch-'a'] == null){
                return false;
            }else{
                ans = ans || solve(str, root.arr[ch-'a'], idx+1);
            }
            return ans;
        }
    }

    // baki iss niche to bass call lagani ha 
    Trie trie;
    public WordDictionary() {
        trie = new Trie();
    }
    
    public void addWord(String word) {
        trie.add(word);
    }
    
    public boolean search(String word) {
        return trie.search(word);
    }
}



//====================================================================================================================
