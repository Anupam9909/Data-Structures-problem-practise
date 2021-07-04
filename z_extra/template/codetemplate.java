/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class codetemplate{
	
    public static void main (String[] args) throws java.lang.Exception
	{
		FastScanner fs=new FastScanner();
		int t=fs.nextInt();
		while(t-->0) {
			int n=fs.nextInt();
			long r=fs.nextLong();
			int arr[]=fs.readArray(n);
			int brr[]=fs.readArray(n);
			long curten=brr[0];
			long ans=brr[0];
			for(int i=1;i<n;i++) {
				curten-= r*(arr[i]-arr[i-1]);
				curten=Math.max(0, curten);
				curten+=brr[i];
				ans=Math.max(ans, curten);
			}
			System.out.println(ans);
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


    // mysort function
    static void mysort(int[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n), temp=a[i];
			a[i]=a[oi];
			a[oi]=temp;
		}
		
		//then sort
		Arrays.sort(a);
	}
 
}