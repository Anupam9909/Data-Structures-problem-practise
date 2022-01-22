// GREEDY TECHNIQUE -> generally [sorting] me ya [min/max] find karne me use karte ha greedy 
// generally interval vale question me haam finish time ke basis pe sort kar dete ha find the solution.




Min. Absolute Difference In Array
Send Feedback
Given an integer array A of size N, find and return the minimum absolute difference between any two elements in the array.
We define the absolute difference between two elements ai, and aj (where i != j ) is |ai - aj|.
Input format :
Line 1 : Integer N, Array Size
Line 2 : Array elements (separated by space)
Output Format :
Minimum difference
Constraints :
1 <= N <= 10^6
Sample Input :
5
2 9 0 4 5
Sample Input :
1



answer by anupam: 
public int solve(int[] arr){	
	Arrays.sort(arr);

	int mindiff = Math.abs(arr[0]-arr[1]);
	int n = arr.length;

	for(int i = 0; i < n-1 ; i++){
		mindiff = Math.min(arr[i], arr[i+1]);
	}
	return mindiff;
}


//=======================================================================

Nikunj and Donuts
Send Feedback
Nikunj loves donuts, but he also likes to stay fit. He eats n donuts in one sitting, and each donut has a calorie count, ci. After eating a donut with k calories, he must walk at least 2^j x k(where j is the number donuts he has already eaten) miles to maintain his weight.
Given the individual calorie counts for each of the n donuts, find and print a long integer denoting the minimum number of miles Nikunj must walk to maintain his weight. Note that he can eat the donuts in any order.
Input
The first line contains an integer, n, denoting the number of donuts. 
The second line contains n space-separated integers describing the respective calorie counts of each donut I, i.e ci.
Output
Print a long integer denoting the minimum number of miles Nikunj must walk to maintain his weight.
Constraints
1 ≤ n ≤ 40
1 ≤ ci ≤ 1000
Sample Input
3
1 3 2
Sample Output
11



Answer by anupam:

public int nikunjanddonuts(){
	int n;
	int[] arr;
	
	Scanner scn = new Scanner(System.in);
	n = scn.nextInt();
	for(int i = 0;i< n; i++) arr[i] = scn.nextInt();

	int ans = solve(arr);
	System.out.println(ans);
}

public int solve(int[] arr){
	Arrays.sort(arr);
	int n = arr.length, mul = 1, ans = 0;

	for(int i = n-1; i >= 0; i--){
		ans = ans + (mul*arr[i]);
		mul = mul*2;
	}

	return ans;
}

//====================================================================

Activity Selection
Send Feedback

You are given n activities with their start and finish times. 
Select the maximum number of activities that can be performed 
by a single person, assuming that a person can only work on a 
single activity at a time.

Input:
The first line of input contains one integer denoting N.
Next N lines contains two space separated integers denoting the 
start time and finish time for the ith activity.

Output:
Output one integer, the maximum number of activities that can be performed
Constraints
1 ≤ N ≤ 10^6
1 ≤ ai, di ≤ 10^9
Sample Input
6
1 2
3 4
0 6
5 7
8 9
5 9
Sample Output
4


answer by anupam:

public void main(String[] args){
	Scanner scn = new Scanner(System.in);
	int n = scn.nextInt();

	List<List<Integer>> arr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            List<Integer> al = new ArrayList<>();
	    st = scn.nextInt();
            end = scn.nextInt();
            al.add(st);
            al.add(end);
            arr.add(al);
        }
        
       // sort
       Collections.sort(arr, (a,b)->{
          return a.get(1)-b.get(1);  // this - other  = default (accending)
       });
        
        
        int j = 0, count = 0;
        
        for(int i = 0; i < n; i++){
            if(arr.get(i).get(0) > arr.get(j).get(1)){
                count++;
                j = i;
            }
        }
        
        if(count == 0) return 0;

        return (count+1);
}



// to paste
public static int activitySelection(int start[], int end[], int n)
    {
        // add your code here
        List<List<Integer>> arr = new ArrayList<>();
        for(int i = 0;i < n; i++){
            List<Integer> al = new ArrayList<>();
            al.add(start[i]);
            al.add(end[i]);
            arr.add(al);
        }
        
       // sort
       Collections.sort(arr, (a,b)->{
          return a.get(1)-b.get(1);  // (this - other)  = default (accending)
       });
        
        
        int j = 0, count = 0;
        
        for(int i = 0; i < n; i++){
            if(arr.get(i).get(0) > arr.get(j).get(1)){
                count++;
                j = i;
            }
        }
        
        return count==0 ? 1: count+1;
        
    }

//=========================================================================

LC-198. House Robber

 // greedy approach
    // i. using space 
    public int rob(int[] arr){
        int n = arr.length;
        int[][] profit = new int[2][n];
        // 0th row // robbed condition
        // 1th row // not robbed condition
        
        for(int i = 0; i < n; i++){
            if(i == 0){
                profit[0][i] = arr[i];
                profit[1][i] = 0;
            }else{
                profit[0][i] = profit[1][i-1] + arr[i];
                profit[1][i] = Math.max(profit[0][i-1], profit[1][i-1]);
            }
        }
        return Math.max(profit[0][n-1], profit[1][n-1]);
    }
    
    
    // ii. without using space (vese space leke jada clear hota ha)
//     public int rob(int[] arr){
//         int n =arr.length;
//         int robProfit = 0, notrobProfit = 0;
        
//         for(int i = 0;i < n; i++){
//             int prevrobProfit = robProfit;
//             int prevnotrobProfit = notrobProfit;
            
//             robProfit = prevnotrobProfit + arr[i];
//             notrobProfit = Math.max(prevrobProfit, prevnotrobProfit);
//         }
//         return Math.max(robProfit, notrobProfit);
//     }
    
//==============================================================

LC-213. House Robber II

 // since this is the update version of house robber I 
    // so this question can also be solved using greedy
    public int rob(int[] arr){
        if(arr.length == 1) return arr[0];
        if(arr.length == 2) return Math.max(arr[0], arr[1]);
        int n = arr.length-1;
        
        int a1 = solve(arr, 0, n-1);
        int a2 = solve(arr, 1, n);
        
        return Math.max(a1, a2);
    }
    
     public int solve(int[] arr, int si, int ei){
        int n = arr.length;
        int[][] profit = new int[2][n];
        // 0th row // robbed condition
        // 1th row // not robbed condition
        
        for(int i = si; i <= ei; i++){
            if(i == 0){
                profit[0][i] = arr[i];
                profit[1][i] = 0;
            }else{
                profit[0][i] = profit[1][i-1] + arr[i];
                profit[1][i] = Math.max(profit[0][i-1], profit[1][i-1]);
            }
        }
        return Math.max(profit[0][ei], profit[1][ei]);
    } 

//======================================================================

// LC-740. Delete and Earn

    // ye question bhi bilkul same hi ha house robber I ki tarah ha 
    // bass hame yaha arr(jo house robber me use hota ha yaaha banana padega)
    // freq array nikal lo and then
    // ek arr array bana lo (freq[i]*i) ka yahi pass kar do house robber ko and get ans
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int[] freq = new int[100005];
        
        for(int i = 0; i < n; i++){
            freq[nums[i]]++;
        }
        
        int[] arr = new int[100005];
        for(int i = 0; i < 100005; i++){
            arr[i] = freq[i]*i;
        }
        
        int ans = houseRobber(arr);
        return ans;
    }
    
    public int houseRobber(int[] arr){
        int n = arr.length;
        int[][] maxval = new int[2][n];
        
        for(int i = 0; i < n; i++){
            if(i == 0){
                maxval[0][i] = arr[i];
                maxval[1][i] = 0;
            }else{
                maxval[0][i] = maxval[1][i-1] + arr[i];
                maxval[1][i] = Math.max(maxval[1][i-1], maxval[0][i-1]);
            }
        }
        
        return Math.max(maxval[0][n-1], maxval[1][n-1]);
    }

// =====================================================================

// LC- 56. Merge Intervals

// ye basically greedy ka question ha jisme stack ka use hua ha
    // sort kar do on basis of starting intervals
    // stack ki help se merge karte jao 
    
    public int[][] merge(int[][] arr){
        Arrays.sort(arr, (a,b)->{
           return a[0]-b[0];  // this-other
        });
        
        int n = arr.length;
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{-1,-1});
        
        for(int[] x : arr){
            // System.out.println(x);
            if(x[0] > st.peek()[1]){
                st.push(new int[]{x[0], x[1]});
            }
            else if(x[0] >= st.peek()[0] && x[0] <= st.peek()[1] && x[1] > st.peek()[1]){
                int[] y = st.pop();
                int[] ar = new int[2];
                ar[0] = Math.min(x[0], y[0]);
                ar[1] = Math.max(x[1], y[1]);
                st.push(ar);
            }else{
                
            }
        }
        
            // System.out.println(st);
        int[][] ans = new int[st.size()-1][2];
        int idx = 0;
        while(st.size() != 1){
            int[] x = st.pop();
            
            ans[idx][0] = x[0];
            ans[idx][1] = x[1];
            idx++;
        }
        
        return ans;
    }


// 452. Minimum Number of Arrows to Burst Balloons
class Solution {
//     Test case
// [[-2147483646,-2147483645],[2147483646,2147483647]]

// Arrays.sort(intervals,(a,b)->a[1]-b[1]);
// If you are using subtraction for compartor, it will cause Integer overflow & cause sort to behave in unexpected ways.

// Instead use Arrays.sort(points, (a, b) -> Integer.compare(a[1],b[1]));
    
    
    public int findMinArrowShots(int[][] arr){
        // Arrays.sort(arr, (a,b)->{     // gives overflow for this -ve compararison
        //     return  a[1]-b[1];
        // });
        
        Arrays.sort(arr, (a,b)->{
           return Integer.compare(a[1],b[1]); 
        });
        
        int n = arr.length;
        long t = arr[0][0], p = arr[0][1];
        int count = 1;  // total bust balloon count
        for(int i = 0; i < n; i++){     // i = 0 se start kar lo ya i = 1 se no problem
            if(p >= arr[i][0]){
                if(p == arr[i][0]){
                    t = p;
                }else{
                    t = arr[i][0];
                }
            }else{
                count++;
                t = arr[i][0];
                p = arr[i][1];
            }
        }
        
        return count;
    }
}

// 435. Non-overlapping Intervals

    public int eraseOverlapIntervals(int[][] arr) {
        Arrays.sort(arr, (a,b)->{  // finish time ke basis pe sort kiya 
           return a[1]-b[1]; 
        });
        
        int t = arr[0][0], p = arr[0][1], count = 0, n = arr.length;
        for(int i = 1; i < n; i++){  // i = 1 se hi chalana padega as yaha t,p i = 0 se kar raha ha initialse
            if(p > arr[i][0]){  // means ith interval overlap kar raha ha
                count++;
            }else{
                t = arr[i][0];
                p = arr[i][1];
            }
        }
        
        return count;
    }


// COMMON TEMPLATE CAN BE WRITEEN AS:-
// Greedy  || 452. Minimum Number of Arrows to Burst Balloons
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        int count = 0;      // results
        // minEnd : Key parameter "active set" for overlapping intervals, 
        // e.g. the minimum ending point among all overlapping intervals;
        int minEnd = Integer.MAX_VALUE;     
        Arrays.sort(points, (a,b) -> (a[0] - b[0]));   // Sorting the intervals/pairs in ascending order of its starting point
        for (int[] in : points) {
            // If the changing some states, record some information, and start a new active set "new arrow"
            if (in[0] > minEnd) {
                count++;
                minEnd = in[1];
            } else {
                // renew key parameters of the active set
                minEnd = Math.min(minEnd, in[1]);
            }
        }
        return count + 1;
    }