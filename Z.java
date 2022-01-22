1015. Smallest Integer Divisible by K
 public int smallestRepunitDivByK(int k) {
        long num = 1;
        int ans = -1;
        for(int i = 1; i <= k; i++){
            if(num % k == 0){ 
                ans = i; 
                break;
            }
            num = num*10 + 1;
            
            num = num % k;            // IMPORTANT  LINE (NAHI TO OVERFLOW HO JAYEGA AS ANS TO 819 BHI AA SAKTA HA MATLAB BIG INTEGER BHI LE LEGE TO BHI KAAM PADEGA SO YAHI EK TARIKA HA KI  [ NUM = NUM % K ] KAR DO)
            
            // System.out.println(num);
            
        }
        return ans;
    }



1026. Maximum Difference Between Node and Ancestor

// hame sare ansestors ko dekne ha yaha
    // ek minimum ele ki list bana lo 
    // ek maximum ele ki list bann lo
    // haar level pe max ans nikalte chalo
    
//     static int ans = 0;
//     public int maxAncestorDiff(TreeNode root){
//         List<Integer> a = new ArrayList<>();   // a : minimum list
//         List<Integer> b = new ArrayList<>();   // b : maximum list
        
//         ans = -(int)1e9;
//         solve(root, a, b);
        
//         return ans;
//     }
    
//     public void solve(TreeNode root, List<Integer> a, List<Integer> b){
//         if(root == null) return ;
        
//         if(a.size() == 0 || a.get(a.size()-1) >= root.val) a.add(root.val);
//         if(b.size() == 0 || b.get(b.size()-1) <= root.val) b.add(root.val);
        
//         ans = Math.max(ans, Math.abs(a.get(a.size()-1) - b.get(b.size()-1)));
        
//         solve(root.left, a, b);
//         solve(root.right, a, b);
        
//         if(a.get(a.size()-1) == root.val) a.remove(a.size()-1);
//         if(b.get(b.size()-1) == root.val) b.remove(b.size()-1);
        
//     }
    
    
    // short way(optimize space)
    // ye vohi vala tarika ha bst me ek <min, max> vala 
    // but haam issko min max category me bhi use kar sakte ha BT ke
     public int maxAncestorDiff(TreeNode root){
         return solve(root, root.val, root.val);
     }
    
     public int solve(TreeNode root, int min , int max){
         if(root == null) return 0;
         
         min = Math.min(min, root.val);
         max = Math.max(max, root.val);
         int ans = 0;
         
         ans = Math.max(ans , solve(root.left, min, max));
         ans = Math.max(ans , solve(root.right, min, max));
         
         return Math.max(ans, Math.abs(min-max));
     }
    