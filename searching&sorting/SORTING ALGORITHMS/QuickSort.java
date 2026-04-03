    public void quickSort(int[] arr, int low, int high) {
        // code here
        if(low >= high) return;
        
        int pivot = partition(arr, low, high);
        
        quickSort(arr, low, pivot-1);
        quickSort(arr, pivot+1, high);
    }

    // [(element_less_than_pivot Region) (element_greater_than_pivot Region) (unexplored)(pivot)]

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int p1 = low, idx = low;   // p1: starting point of the region(number greater than pivot)
        
        while(idx < high){
            if(arr[idx] >= pivot){
                idx++;
            }else{
                int temp = arr[p1];
                arr[p1] = arr[idx];
                arr[idx] = temp;
                
                p1++; idx++;
            }
        }
        
        // swap with pivot
        int temp = arr[p1];
        arr[p1] = pivot;
        arr[high] = temp;
        
        return p1;
    }
