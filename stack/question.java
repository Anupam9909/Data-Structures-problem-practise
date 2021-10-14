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






    // remove outermost paranthesis
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
























}