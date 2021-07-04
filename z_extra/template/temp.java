
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
    
   
 // Use this to input code since it is faster than a Scanner
    static class FastScanner {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}

