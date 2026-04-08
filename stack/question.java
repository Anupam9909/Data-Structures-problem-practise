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

//======================================================================================================


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


//======================================================================================================
    
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


//======================================================================================================

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


//======================================================================================================

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


//======================================================================================================

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


















//======================================================================================================



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

 
//======================================================================================================   

    // LC - 901. Online Stock Span
    class StockSpanner {
        Stack<int[]> st;
        int idx;
        public StockSpanner() {
            st = new Stack<>();
            st.push(new int[]{-1,-1});
            idx = 0;
        }
        
        public int next(int price) {
            int[] np = new int[]{idx, price};  // np : new pair
            
            while(st.peek()[1] != -1 && st.peek()[1] <= price){ //agar equal ha to bhi pop karna ha
                st.pop();
            }
            
            int ans = idx - st.peek()[0];
            st.push(np);
            idx++;
            return ans;
        }

    }


//======================================================================================================

    // LC - 84. Largest Rectangle in Histogram
    // EXAM TEST KE LIYE YAHI METHOD KARO SIMPLE HA
    // simple NSOL & NSOR nikal lo and for a particular element (difference = NSOR[i]-NSOL[i]-1)
    // will act as width so AREA = HEIGHT * WIDTH kar lo and find the maximum area 
    public int largestRectangleArea(int[] arr){
        int n = arr.length; 
        int[] NSOL  = nsol(arr);
        int[] NSOR  = nsor(arr);
        
        int ans = 0;
        
        for(int i = 0; i < n; i++){
            int height = arr[i];
            int width = NSOR[i]-NSOL[i]-1;
            int area = height*width;
            
            ans = Math.max(ans, area);
        }
        
        return ans;
    }
    
    public int[] nsor(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i < n; i++ ){
            while(st.size() != 0 && arr[i] <= arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }
    
    
    public int[] nsol(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        Stack<Integer> st = new Stack<>();
        
        for(int i = n-1; i >= 0; i--){
            while(st.size() != 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    //  II method approach [to impress interview]
    // NSOR concept lagega and here
    // jo banda pop karayega vo right boundry ka kaam karega for st.peek() element
    // and jo element pop hua ha uske just niche vala element left boundry ka kaam kar raha hoga
    // so area = height*width;  (height = arr[popedIdx]  ,   width = rb-lb-1) 
    public int largestRectangleArea(int[] arr){
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        st.push(-1);
        int ans = 0; // max area
        
        for(int i = 0; i < n; i++  ){
            
            while(st.peek() != -1 && arr[i] < arr[st.peek()] ){
                int idx  = st.pop();
                int lb = st.peek();       // lb : left boundry 
                int rb = i;               // rb : right boundry
                int width = rb-lb-1;
                int height = arr[idx];
                
                ans = Math.max(ans, height*width);
            }     
            
            st.push(i);
        }
        
        // now jo stack me bach gye elements unko bhi solve karna ha 
        while(st.peek() != -1 ){
                int idx  = st.pop();
                int lb = st.peek(); // lb: left boundry
                int rb = n;         // rb: right boundry (yaha right boundry hamesha [n] hogi)
                int width = rb-lb-1;
                int height = arr[idx];
                
                ans = Math.max(ans, height*width);
        }
        return ans;
    }
    

//======================================================================================================

    // LC - 85. Maximal Rectangle of 1's in a matrix[of 0 & 1]
    // EXAM TEST KE LIYE YAHI METHOD KARO SIMPLE HA
    // same hi question ha ye bilkul lc-84 jesa (just upar vala question)ha 
    // alag alag base ke liye alag alag histogram banege and find then find the maximum rectangle of histogram laga do(i.e lc-84)
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int[] arr = new int[matrix[0].length];
        Arrays.fill(arr,0);
        int ans = 0;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j  = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    arr[j]++;
                }else if(matrix[i][j] == '0'){
                    arr[j] = 0;
                }
            }
            ans = Math.max(ans, maxrectangle(arr));
        }
        
        return ans;
    }
    
    public int maxrectangle(int[] arr){
        if(arr.length == 0) return 0;
        int[] nsol = NSOL(arr);
        int[] nsor = NSOR(arr);
        int ans  = 0;
        
        for(int i = 0 ; i <arr.length; i++){
            int area = arr[i] * (nsor[i]-nsol[i]-1);
            ans = Math.max(ans,area );
        }
        
        return ans;
    }
    
    
    public int[] NSOR(int[] arr){
        Stack<Integer> st= new Stack<>();
        int[] ans  = new int[arr.length];
         Arrays.fill(ans,arr.length);
        
        for(int i = 0; i < arr.length; i++){
            while(st.size() != 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }
    
    public int[] NSOL(int[] arr){
        Stack<Integer> st= new Stack<>();
        int[] ans  = new int[arr.length];
        Arrays.fill(ans,-1);
        
        for(int i = arr.length-1; i >=0 ; i--){
            while(st.size() != 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }
    // iska II approach (to impress interview) vala bhi vese hi kar lege jese lc-84 kiyya ha baki so same hoga yaha ki har base ka histogram nikalana ha  


//======================================================================================================

    // LC - 221. Maximal Square(vese to dp ka question ha but yaha bhi solve ho jayega)
    // kuch ni karna bass lc-85 me ek line ka change karna ha bass
    // since yaha hame square chahiye so haam minimum of height and width le lege taki square hi mile 
    // Area = Math.min(width, height) * Math.min(width, height);

    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int[] arr = new int[matrix[0].length];
        Arrays.fill(arr,0);
        int ans = 0;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j  = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    arr[j]++;
                }else if(matrix[i][j] == '0'){
                    arr[j] = 0;
                }
            }
            ans = Math.max(ans, maxsquare(arr));
        }
        
        return ans;
    }
    
    public int maxsquare(int[] arr){
        if(arr.length == 0) return 0;
        int[] nsol = NSOL(arr);     // write the NSOL function
        int[] nsor = NSOR(arr);     // write the NSOR function
        int ans  = 0;
        
        for(int i = 0 ; i <arr.length; i++){
            int width = (nsor[i]-nsol[i]-1);
            int height = arr[i];
            int length = Math.min(width, height);
            int maxsquare_area = length*length;
            ans = Math.max(ans,maxsquare_area );
        }
        
        return ans;
    }

//======================================================================================================
    
 // LC-155. Min Stack (Easy)
    // MIN STACK VALA SOLUTION (CLASS QUESTION) VALE FOLDER ME HA as it is a question of class
    // LC - 155. Min Stack (easy)

class MinStack(){
    MinStack(){ } // initializes the stack object.(ye to hamare liye hi hota ha to initialise our variable jo hamne class me define kiye ha)
    void push(int val){ } // pushes the element val onto the stack.
    void pop(){ } // removes the element on the top of the stack.
    int top(){ } // gets the top element of the stack.
    int getMin(){ } // retrieves the minimum element in the stack.
}

// SOLUTION 1 MIN STACK  
// -> pair class bana lo {value, minValueTillNow} ka and push and pop me update kar do

class MinStack {
    public class pair{
        int val, min;
        pair(int v, int m){
            this.val = v;
            this.min = m;
        }
    }
    
    Stack<pair> st = new Stack<>();
    int min;
    public MinStack() {
        min = 0;
    }
    
    public void push(int val) {
        if(st.size() == 0){
            min = val;
            pair np = new pair(val, min);
            st.push(np);
        }else{
            min = Math.min(min, val);
            pair np = new pair(val, min);
            st.push(np);
        }
    }
    
    public void pop() {
        if(st.size() == 0) return;
        st.pop();
        // now, yaha update karna bhot jaruri ha min ko varna ans galat ayega
        if(st.size() != 0)  min = st.peek().min;
    }
    
    public int top() {
        if(st.size() == 0) return -1;
        return st.peek().val;
    }
    
    public int getMin() {
        if(st.size() == 0) return -1;
        return st.peek().min;
    }
}


//================================================================================================================


// SOLUTION 2  MIN STACK
// hamm ek minimum element naam ka varaible le ke chalege i.e [minele] (which holds the min value till now)
// jab bhi naya element aya push hone ko we will update the min element
// if(minele > val) UPDATE minele AND JO PEHLE minele KI VALUE THI VO BHI PUSH KAR DO 
// SO THAT JAB POP HOGA TO DUBARA minele KO UPDATE KAR DEGE PURANI minele VALUE PE

class MinStack {
    int minele;
    Stack<Integer> st;
    
    public MinStack() {
        this.minele = 2147483647;  //   given in question :  -2^31 <= val <= 2^31 - 1
        st = new Stack<>();
    }
    
    public void push(int val) {
        if(minele > val){
            st.push(minele);
            minele = val;
        }else if(minele == val){    // equal element bhi posible ho sakte ha
            st.push(minele);        // (so if same ele aye then push them again (imp))
        }
        
        st.push(val);
    }
    
    public void pop() {
        if(minele == st.peek()){
            st.pop();
            minele = st.pop();
            return;
        }else{
            st.pop();
        }
    }
    
    public int top() {
        // if(st.size() == 0) // given in qust. [operation will only be called on non-empty stack]
        return st.peek();
    }
    
    public int getMin() {
        return minele;
    }
}

//======================================================================================================

    // LC - 402. Remove K Digits
    
    // concept : NSOL lagega till (k > 0)
    // NOTE : yaha 3 edge cases ha handle karna 
    // 1. after nsol loop if(k >0)
    // 2. edge case -> when "10" and k=1 then we have to return "0"
    // 3. removing the leading zeros
    public String removeKdigits(String str, int k){
        if(str.length() == k) return "0";
        Stack<Integer> st = new Stack<>();
        for(char ch : str.toCharArray()){
            while(st.size() != 0 && ch-'0' < st.peek() && k > 0){
                st.pop();
                k--;
            }
            
            st.push(ch-'0');
        }
        
        // imp case : (if k is > 0) then we have to just pop the rightmost k element
        while(k > 0 && st.size() != 0){
            k--;
            st.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        while(st.size() != 0){
            sb.append(st.pop()+"");
        }
        
        // edge case   -> when "10" and k=1 then we have to return "0"
        int count = 0;
        for(int i = 0; i < sb.length(); i++) if(sb.charAt(i) == '0') count++;
        if(sb.length() == count) return "0";
        
        // removing the leading zeros
        while(sb.length() != 0 && sb.charAt(sb.length()-1) == '0'){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.reverse().toString();
    }
    
    
    
//======================================================================================================
    

// LC-1190. Reverse Substrings Between Each Pair of Parentheses 

    // stack use hoga
    // sare element ko ek ek karke stack me dalte jao 
    // ek ) closing bracket comes then haam pop karke string bana lege which will be the reverse automatically and then dubara ussi stack me daal dege at the end we will get the final ans string in the stack so get it 
    public String reverseParentheses(String s){
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                StringBuilder sb = new StringBuilder();
                // sare middle element ko nikalo i.e ( ) inke bich ke
                while(st.peek() != '('){
                    sb.append(st.pop());
                }
                
                st.pop();  // remove:  ( this bracket
                
                // now jo reverse string bani ha vo simply stack me daal do
                for(int j = 0; j < sb.length(); j++){
                    char c = sb.charAt(j);
                    st.push(c);
                }
            }
            else{  // ch == '(' || ch == alphabet then add in st 
                st.push(ch);
            }
        }
        
        StringBuilder ans = new StringBuilder();
        while(st.size() != 0) ans.append(st.pop());
        
        return ans.reverse().toString();
    }
    
    
    
//======================================================================================================
    




}
