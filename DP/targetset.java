public class targetset{
    public void main(String[] args){
        
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

























} 