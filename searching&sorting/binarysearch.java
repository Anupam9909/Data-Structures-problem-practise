import java.util.*;
public class binarysearch{
    public static void main(String[]  args){
        int [] arr = new int[]{ 2,2,2,8,8,8,8,8,9,10,12 };
        int data = 8;
        // int x = function(arr, si , ei);
        int x = lastOcc(arr, data);
        int y = firstocc(arr, data);
        System.out.println("lastocc :" + x + "   ,   firstocc : " + y);
    }

    public int binarysearch(int[] arr, int data){
        int si = 0, ei = arr.length-1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == data) return mid;
            else if(arr[mid] < data) si = mid+1;
            else   ei = mid-1;
        }

        return -1;
    }

    public int insertPosition(int[] arr, int data){
        int si = 0, ei = arr.length-1;
        // Note: hamara ans iss loop ke ander kabhi nahi milega hamare logic se 
        // hamara ans iss loop ke pura chalne ke baad jab si & ei cross kar jayege
        // then hamara Ans automatically [si] pointer pe rakha hoga pakka haar condition me haar case me(checked 100%)   
        while(si <= ei){
            int mid = (si+ei)/2;
            
            if(arr[mid] <= data){
                si = mid + 1;
            } else{
                ei = mid - 1;
            }
        }
        
        // insert position : si 
        return si;
    }

    // since in this above logic at the end of the loop when si and ei crosses each other then,
    // insert position of given data is on : (si)   location
    // last occurance of given data is on  : (si-1) or (ei)   location

    // Q.> finding last occurance of the data given 
    public static int lastOcc(int[] arr, int data){
        int si = 0, ei = arr.length-1;
        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] <= data){
                si = mid+1;
            }else{
                ei = mid-1;
            }
        }
        // last occurance of the given data is at location : (si-1) or (ei);
        // Note : ei last occurance to deta ha but ye ni batata ki element actually 
        // me given array me  present ha ki nahi , so ek check lagana padega!

        if(ei == -1 || arr[ei] != data) return -1;  // means element not found
        return ei;

    }



    // we can also find the first occurance by simply changing one condition i.e 
    // jab bhi hamara arr[mid] is equal to data then haam (ei) ko (mid-1) pe rakh de to hame 
    // agar data nahi mila to ye insert position ki tarah kaam karega but if data present then it will find firstocc 
    public static int firstocc(int[] arr, int data){
        int si = 0,n = arr.length, ei = n-1;

        while(si <= ei){
            int mid = (si + ei)/2;
            if(arr[mid] >= data) ei = mid-1;
            else si = mid+1;
        }
        
        System.out.println( "si : " + si + " " + "ei : " + ei);
        return (si == n && arr[si] != data)? -1 : si ;
    }

    

}