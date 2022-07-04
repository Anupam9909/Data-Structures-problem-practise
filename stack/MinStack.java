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