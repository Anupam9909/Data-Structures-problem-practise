public class question{
    // LC : 20 - valid parenthesis
    public boolean isValid(String s){
        if(s.length() == 0) return false;
                            
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                st.push(ch);
            }else{
                if(st.size() == 0) return false;
                
                if(ch == '}' && st.peek() == '{') st.pop();
                else if(ch == ']' && st.peek() == '[') st.pop();
                else if(ch == ')' && st.peek() == '(') st.pop();
                else return false;
            }
        }
        
        return st.size() > 0 ? false : true;
    }


    //LC-503. Next Greater Element II(circular way)
    // just (2*n) tak chala do loop
    public int[] nextGreaterElements(int[] arr){
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        
        Stack<Integer> st = new Stack<>(); 
        int n = arr.length; 
        
        for(int i = 0; i < 2*n; i++){
            int idx = i%n;
            while(st.size() != 0 && arr[idx] > arr[st.peek()]){
                ans[st.pop()] = arr[idx];
            }
            st.push(idx);
        }
        
        return ans;
    }

    
    //LC- 946. Validate Stack Sequences (pushed, popped)
    public boolean validateStackSequences(int[] pushed, int[] popped){
        Stack<Integer> st = new Stack<>();
        int j = 0;
        for(int i = 0; i < pushed.length; i++){
            st.push(pushed[i]);
            while(st.size() != 0 && st.peek() == popped[j]){
                st.pop();
                j++;
            }
        }
        return j < popped.length-1 ? false : true;
    }




    
    // lc - 1249. Minimum Remove to Make Valid Parentheses
    public String minRemoveToMakeValid(String s){
        ArrayList<Integer> arr = new ArrayList<>();
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            
            if(ch == ')' || ch == '('){
                if(ch == ')'){
                    if(arr.size() == 0) arr.add(i);
                    else if(s.charAt(arr.get(arr.size()-1)) == '(') arr.remove(arr.size()-1);
                    else  arr.add(i);
                }else{
                    arr.add(i);
                } 
            }else {
                // if an alhabet is present then not to do anything
            }
        }
        
        // invalid element are present in the stack
        
        StringBuilder ans  = new StringBuilder();  // string lege to jada time lagta ha compare to this stringbuilder
        int j = 0;
        for(int  i =  0; i < s.length(); i++){
            if( j < arr.size() && i == arr.get(j)){
                j++;
            }else{
                ans.append(s.charAt(i));
            }
        }
               
        return ans.toString();
    }




    // LC  - 1021 remove outermost paranthesis
    public String removeOuterMostParam(String str){
        if(str.length() == 0 ) return str;

        ArrayList<String> arr = new ArrayList<>();
        int count = 0;
        StringBuilder temp = new StringBuilder();

        for(int i = 0 ; i < str.length(); i++){
            char ch = str.charAt(i);
            temp += ch;
            if(ch == '(') count++;
            else if(ch == ')') count--;

            if(count == 0){
                arr.add(temp);
                temp = "";
            }
        }

        String ans = new String();
        for(int i = 0 ; i < arr.size() ; i++){
            int size = arr.get(i).length();
            String s = arr.get(i).substring(1, size-2);
            ans = ans + s;
        }

        return ans;

    }



    // LC - 32. Longest Valid Parentheses
    public int longestValidParentheses(String s){
        Stack<Integer> st = new Stack<>();
        int n = s.length();
        st.push(-1);
        
        int ans = 0;  // length of longest string
        
        for(int i = 0 ; i < n; i++){
            char ch = s.charAt(i);
            
            if(st.peek() != -1 && ch == ')' && s.charAt(st.peek()) == '('){
                st.pop();
                ans = Math.max(ans, i-st.peek());
            }else{
                st.push(i);
            }
        }
        return ans;
    }





















    // LC - 735. Asteroid Collision
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        
        for(int i = 0; i < n; i++){
            if(arr[i] > 0){
                st.push(arr[i]);
            }else{
                while(st.size()!= 0 && st.peek() > 0 && -arr[i] > st.peek()){
                    st.pop();
                }
                
                if(st.size() != 0 && st.peek()  == -arr[i]) st.pop();
                else if(st.size() != 0 && st.peek() > -arr[i]) continue;
                else st.push(arr[i]);
            }
        }
        
        int[] ans = new int[st.size()];
        for(int i = ans.length-1; i >= 0 ; i--){
            ans[i] = st.pop();
        }
        
        return ans;
    }




















}