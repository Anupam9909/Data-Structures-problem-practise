import java.util.*;

public class buysellstocks{
    public static void main(String[] args){
        int[] arr = {9,11,6,7,10,4,1,6,10,4};
        // onetransaction(arr);
        // onetransaction_opti(arr);
        infigtransaction(arr);
    }

    // O(n2) - one transaction allowed  (brute force)
    public static int onetransaction(int[] arr){
        int n = arr.length;
        int maxprofit = -(int)1e8, buy = -1, sell = -1 ;

        for(int i = 0; i < n ; i++){
            for(int j = i; j < n; j++){
                if(maxprofit < (arr[j]-arr[i])){
                    maxprofit = arr[j]-arr[i];
                    buy = arr[i];
                    sell = arr[j];
                }
            }
        }

        System.out.println("buy at :" + buy + " , " + "sell at :" + sell );
        System.out.println("maxprofit :" + maxprofit);
        return maxprofit;
    }

    // O(n) - optimized
    public static int onetransaction_opti(int[] arr){
        int n = arr.length;
        int maxprofit = -(int)1e8, buy = -1, sell = -1;
        int lsf = arr[0];  // lsf(least so far) : least price so far  i.e minimum value of stock till now 
        int pist = 0;  // pist : profit if sold today

        for(int i = 0; i < n; i++){
           if(lsf > arr[i])  lsf = arr[i];
            
           pist = arr[i] - lsf;  // today's  profit

           if(maxprofit < pist){
                maxprofit = pist;
                buy = lsf;
                sell = arr[i];
           }  
        }

        System.out.println("buy at :" + buy + " , " + "sell at :" + sell );
        System.out.println("maxprofit :" + maxprofit);
        return maxprofit;
    }

    

    // O(n2) - infinite transaction allowed wrong solution
    public static int infigtransaction(int[] arr){
        int n = arr.length;
        int totalprofit = 0;
        int maxprofit = 0;

        for(int i = 0; i < n; i++){
            maxprofit = 0;
            for(int j = i; j < n; j++){
                if(maxprofit < arr[j]-arr[i]){
                    maxprofit = arr[j]-arr[i];
                }
            }
            totalprofit += maxprofit ;
        }

        // System.out.println("buy at :" + buy + " , " + "sell at :" + sell );
        System.out.println("totalprofit :" + totalprofit);
        return maxprofit;
    }



}