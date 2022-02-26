// HASHSET :

// HashSet<int[]> hs = new HashSet<>();   -> eg added in hs {2,3}
// for(int i = 0; i < obst.length; i++){
//     hs.add(new int[]{obst[i][0], obst[i][1]});
// }
// boolean ans =  hs.contains(new int[]{2,3});   // wrong


 /|\
  |
to do this 

HashSet<List<Integer>> hs = new HashSet<>();
for(int[] x : obst){
    hs.add(List.of(x[0],x[1]));
}

boolean ans = hs.contains(List.of(x,y));


=========================================================

// RANDOM IN JAVA

// I way: simple 
Random r = new Random();
int rand = r.nextInt(n);   // gives integer value form 0 to n-1
  here,
 0 <= rand < n

// II way : 
int max = 10;
int min = 1;
int range = max - min + 1;

int rand = (int)(Math.random(n)*range) + min;

  here, 
  min <= rand <= max

=========================================================
// simple way
class Solution {
    int[] arr;
    
    public Solution(int[] nums) {
        arr = nums;    
    }
    
    public int pick(int target) {
        Random r = new Random();
        int n = arr.length;
        int ans = -1;
        
        while(ans == -1){
            int rand = r.nextInt(n);
            
            if(arr[rand] == target){
                ans = rand;
            } 
        }
        return ans;
    }
}

// probability solution important i.e by 
// Reservoir Sampling solution

public class Solution {
    int[] nums;
    Random rand;
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    public int pick(int target) {
        int totaltargetnumbers = 0;
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // randomly select an int from 0 to the nums of target. If x equals 0, set the ans as the current index. The probability is always 1/nums for the latest appeared number. For example, 1 for 1st num, 1/2 for 2nd num, 1/3 for 3nd num (1/2 * 2/3 for each of the first 2 nums).
                totaltargetnumbers++;
                int x = rand.nextInt(totaltargetnumbers); 
                if(x == 0) ans = i;
            }
        }
        return ans;
    }
}
 
//===================================================================

String str = "[1,2,3,4, 5,6 ,7,8 ,9]";

String ans = str.toString().replace(" ","").replace(",", "").replace("[", "").replace("]", "");


System.out.println(ans);


// or 

int[] arr = new int[]{1,2,3,4,5};
String ans = Arrays.toString(arr).replace(" ","").replace(",", "").replace("[", "").replace("]", "");

System.out.println(ans);
