
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        FastScanner fs=new FastScanner();
        int t=fs.nextInt();
        while(t-->0) {
            int n=fs.nextInt();
            int[] arr = new int[n];
            int sum = 0;
            for(int i = 0; i < n; i++){
                arr[i] = fs.nextInt();
                sum += arr[i];
            }

            if(sum == n){
                System.out.println(0);
                return ;
            }
            if(sum >n){
                System.out.println(sum-n);
            }else{
                System.out.println(1);
            }
            System.out.println();
        }
    }
    
   
 
}

