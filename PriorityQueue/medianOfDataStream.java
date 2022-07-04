class MedianFinder {
    PriorityQueue<Integer> maxpq;
    PriorityQueue<Integer> minpq;
    public MedianFinder() {
        maxpq = new PriorityQueue<>((a,b)->{  // max pq  -> which hold left half of the numbers
            return b-a; 
        });   
        minpq = new PriorityQueue<>();    // min pq  -> which hold right half of the numbers
    }
    
    public void addNum(int num) {
        if(maxpq.size() == 0 || num <= maxpq.peek()){
            maxpq.add(num);
        }else{
            minpq.add(num);
        }
        
        // balance both the PQ  -> v.imp ha karna tabhi to haam kissi bhi samaye median nikal sakte ha O(1) me
        if(maxpq.size() > minpq.size()+1){
            int temp = maxpq.remove();
            minpq.add(temp);
        }
        else if(maxpq.size()+1 < minpq.size()){
            int temp = minpq.remove();
            maxpq.add(temp);
        }
    }
    
    public double findMedian() {
        if(maxpq.size() == minpq.size()){  // even number of elements present ->so, avg of two terms lena hoga
            double ans = (double)(maxpq.peek() + minpq.peek())/2;
            return ans;
        }
        else{  // odd number of element present -> so jisme bhi jada element hoga means vohi median hoga pakka
            if(maxpq.size() > minpq.size()){
                return maxpq.peek();
            }else{
                return minpq.peek();
            }
        }
    }
}

