/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class code{
	
	public static void main (String[] args) throws java.lang.Exception
	{  
		int [][] arr = {{24},{3,6,11,14,22},{1,23,24},{0,6,14},{1,3,8,11,20}};
		int src = 11;
		int dest = 0;
		
		// int ans = meetingpoint(arr);
		
		System.out.println(mySqrt(13));
	}

	// find topological order
	public static void khanalgo(ArrayList<ArrayList<Integer>> adj){
		int vtx = adj.length;

		// build graph
		ArrayList<Integer>[] graph = new ArrayList[adj.length];
		for(int i = 0; i < graph.length; i++)  graph[i] = new ArrayList<>();
		for(ArrayList<Integer> x : adj){
			int u = x.get(0);
			int v = x.get(1);
			graph[u].add(v);
		}

		// khan's algo (iprwm->a*)
		int[] indegree = new int[vtx];
		
		// i
		for(int i = 0; i < vtx ; i++)
			for(Integer v : graph[i])
				indegree[v]++;

		
		 LinkedList<Integer> que = new LinkedList<>();		
		// p
		for(int i = 0; i < vtx; i++){
			if(indegree[i] == 0)  que.addLast(i);
		}

		// carefull BFS
		ArrayList<Integer> ans = new ArrayList<>();

		while(que.size() != 0){
			int rem = que.removeFirst(); // r
			ans.add(rem);  // w

			for(Integer v : graph[rem]){ // m->a*
				indegree[v]--;
				if(indegree[v] == 0){
					que.add(v);
				}
			}
		}
		return ans;
	}
    
	

	//=================================================================================================================
	//=================================================================================================================
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