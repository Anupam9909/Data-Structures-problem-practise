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


=================================================================================================
//TREESET IN JAVA
// TREESET is exactly same as HASHSET(duplicate ele ek hi baar ayega), with an extra property that 
// it stores the element in sorted order 

// sab same ha jese hashset ko access karte ha vese hi treeset ko hi access karege

    TreeSet<Integer> ts = new TreeSet<>();   // treeset is exactly a hashset with a extra feature that it store the element in sorted order
    ts.add(5);
    ts.add(2);
    ts.add(6);
    ts.add(9);
    ts.add(1);
    ts.add(5);
    ts.add(1);
    ts.add(2);

    System.out.println(ts);      // -> prints:  [1, 2, 5, 6, 9]
    
    for(int x : ts){
        System.out.println(x);
    }
    // it do iteration and print ele in sorted order
    // 1
    // 2
    // 5
    // 6
    // 9

    // NOTE : And since treeset me element sorted order me hota ha so,
    TREESET me ek extra function hota ha  -> (very usefull)
    Integer ans = ts.ceiling(num);   =>  gives the (value) of element which is just greater than (num) -> if not present it gives (null)
    Integer ans = ts.floor(num);     =>  gives the (value) of element which is just smaller than (num) -> if not present it gives (null)

    eg1
    ts : [1, 2, 5, 6, 9]
    Integer ans = ts.ceiling(3);   => gives ans = 5
    Integer ans = ts.ceiling(7);   => gives ans = 9
    Integer ans = ts.ceiling(11);   => gives ans = null


=========================================================
// similarly TreeMap<key, value> bhi ha jo ki exactly same as HASHSET(duplicate key ek hi baar ayega), with an extra property that 
// it stores the [keys] in sorted order 

    TreeMap<Integer, String> tm = new TreeSet<>();   // treeset is exactly a hashset with a extra feature that it store the element in sorted order
    tm.add(5, "anupam");
    tm.add(2, "akhil");
    tm.add(6, "naman");
    tm.add(9, "priya");
    tm.add(1, "pankaj");

    System.out.println(tm);      
    // -> prints:     [ 
                //      [1 - "pankaj"]
                //      [2 - "akhil"]
                //      [5 - "anupam"]
                //      [6 - "naman"]
                //      [9 - "pankaj"]
                //                    ]
        
    for(int x : tm.keySet()){
        System.out.println(x + "->" + tm.get(x));
    }
        // -> prints:     [ 
                //      [1 - "pankaj"]
                //      [2 - "akhil"]
                //      [5 - "anupam"]
                //      [6 - "naman"]
                //      [9 - "pankaj"]
                //                    ]

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



 
//===================================================================


// LC-1466. Reorder Routes to Make All Paths Lead to the City Zero

    // read the question carefully 
    // the question says that there is only one way to travel between two different cities which is very important. so ye tree ka question ha graph ka nahi(just to solve question)
    
    // NOTE : 
    // tree is a special type of graph -> in which every node has a unique path from root 
    
    // SOLUTION:
    // PEHLE TO APNA GRAPH BNA LO BIDIRECTIONAL & THEN
    // SIMPLE TRAVERSE KAR LO BFS SE YA DFS SE AND JO JO SAME DIRECTION HO COUNT++ DO IT AND RETURN COUNT;
    
    public int minReorder(int n, int[][] arr) {
        // build graph
        List<Integer>[] g = new ArrayList[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] x : arr){
            int u = x[0], v = x[1];
            g[u].add(v);
            g[v].add(u);
        }
        
        int count = 0;
        // NOTE : yaha freq table nahi bana sakte as -> 
        // 1-->0
        // 1-->2 hm me alag alag nahi atte
        
        // // making freq table
        // HashMap<Integer, Integer> hm = new HashMap<>();
        // for(int[] x : arr){
        //     int u = x[0], v = x[1];
        //     hm.put(u, v);
        // }
        
        // so ham esa kuch bana lege
        HashSet<Integer>[] map = new HashSet[n];
        for(int i = 0; i < n; i++) map[i] = new HashSet<>();
        for(int[] x : arr){
            int u = x[0], v = x[1];
            map[u].add(v);
        }
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        q.add(0);
        vis[0] = true;
        
        while(q.size() != 0){
            int s = q.size();
            while(s-- > 0){
                int rem = q.remove();
                // System.out.println("hello");
                for(int v : g[rem]){
                    if(vis[v] == false){
                        vis[v] = true;
                        q.add(v);
                        // increasing count ans
                        if(map[rem].contains(v)) count++;
                    }
                }
            }
        }
        
        return count;
    }


//=========================================================
        // System.out.println('e'-'a'); =>  gives the output as interger value -> 4
        // System.out.println('2'-'0'); =>  gives the output as interger value -> 2
        
    

//=========================================================
    
// TREEMAP (ordered map ha bass aur kuch ni ha)

// We have seen HashMap in java but we know it is not in the sorted form 
// so, to get the element in the sorted form we use (TreeMap) which insert
// the element and sort them on the basis of the key
// agar key interger me ha then integer sorting karega
// and if key string me ha then lexiographically sort karega

//Example : 1
TreeMap<String, Integer> users = new TreeMap<String, Integer>();
users.put("Bob", 1);
users.put("Alice", 2);
users.put("John", 3);

Output:
Alice (ID = 2)
Bob (ID = 1)
John (ID = 3)

//------------------

//Example : 2
TreeMap<Integer, String> tmap = new TreeMap<String, Integer>();
tmap.put(3, "Bob");
tmap.put(1, "Alice");
tmap.put(2, "John");

Output:
1 : Alice
2 : John
3 : Bob

//===============================================================