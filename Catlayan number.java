public class Main{
    
// CATLAYAN NUMBER SEQUENCE:
// 1 1 2 5 14 

// O(n^2) time 
    public int catlayan(int n){
        int[] arr = new int[n+1];
        arr[0] = arr[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                arr[i] += (arr[j]*arr[i-j-1]);
            }
        }
        
        return arr[n];
    }
	
	
// 	    For i = 4
        	
//             	c(0) * c(3)
            	
//             	     +
            	     
//             	c(1) * c(2)
            	
//             	     +
            	     
//             	c(2) * c(1)
            	
//             	     +
            	     
//             	c(0) * c(0)
            	     
//             	     ||
//             	     \/
//          ans will be stored in arr[i]  
	     

//=====================================================================

// QUESTION --> NUMBER OF BST's

// LC-96. Unique Binary Search Trees

    // NOTE : It's a standard problem for Catalan numbers, like Fibonacci is for recursion, similarly this problem is for Catalan numbers
    
    public int numTrees(int n) {
        int ans = catlayan(n);
        return ans;
    }
    
    public int catlayan(int n){
        int[] arr = new int[n+1];
        arr[0] = arr[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                arr[i] += (arr[j]*arr[i-j-1]);
            }
        }
        
        return arr[n];
    }
	
}