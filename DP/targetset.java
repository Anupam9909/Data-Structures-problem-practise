public class targetset{
    public void main(String[] args){
        int[] arr = new int[]{2,3,5,7};

        // by subsquence method
        ccp_infi(arr, target, 0);
        ccc_infi(arr, target, 0);
        ccc_single(arr, target, 0);
        
        boolean[] visited = new boolean[arr.length];
        ccp_single(arr, target, 0, visited);
        ccp_single(arr, target, 0) // -ve way
    }

    // BY SUBSEQUENCE METHOD

    // coin change permutation,  infinte coins
    public int ccp_infi(int[] arr, int target, int idx){
        if(idx == arr.length) return 0;
        if(target == 0) return 1;

        int count = 0;
        if(target-arr[idx] >= 0){
            count += ccp_infi(arr, target-arr[idx], 0)
        }
        count += ccp_infi(arr, target, idx+1);

        return count;
    }


    // coin change combination, infinite coins

    public int ccc_infi(int[] arr, int target, int idx){
        if(idx == arr.length) return 0;
        if(target == 0) return 1;

        int count = 0;
        if(target-arr[idx] >= 0){
            count += ccc_infi(arr, target-arr[idx], idx);
        }
        count += ccc_infi(arr, target, idx+1);

        return count;
    }

    // coin change combination, single coins
    public int ccc_single(int[] arr, int target, int idx){
        if(idx == arr.length) return 0;
        if(target == 0) return 1;

        int count = 0;
        if(target-arr[idx] >= 0){
            count += ccc_single(arr, target-arr[idx], idx+1);
        }
        count += ccc_single(arr, target, idx);

        return count;
    }

    // coin change permutation, single coin
    public int ccp_single(int[] arr, int target, int idx, boolean[] visited){
        if(idx == arr.length) return 0;
        if(target == 0) return 1;

        int count = 0;
        // ayega 
        if(target-arr[idx] >= 0 && visited[idx] != true){
            visited[idx] = true;
            count += ccp_single(arr, target-arr[idx], 0, visited);
            visited[idx] = false;
        }

        // nahi ayega
        count += ccp_single(arr, target, idx+1, visited);

        return count;
    }

    // ccp_ single different way i.e without visited(works only for +ve integer in array)
    public int ccp_single(int[] arr, int target, int idx){
        if(idx == arr.length) return 0;
        if(target == 0) return 1;

        int count = 0;
        if(target-arr[idx] >= 0){
            int temp = arr[idx];
            arr[idx] = -arr[idx];
            count += ccp_single(arr, target-temp, 0); 
            arr[idx] = -arr[idx];
        }

        count += ccp_single(arr, target, idx+1);

        return count;
    } 






} 