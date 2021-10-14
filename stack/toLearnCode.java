public class toLearnCode{
    // NGOR -> greater i.e bada vala chote vale ko pop karayega
    //      -> loop move from 0 to n-1
    public void NGOR(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, (int)1e9);

        for(int i = 0; i < arr.length; i++){
            while(st.size() != 0 && arr[i] > arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // NGOL -> greater i.e bada vala chote vale ko pop karayega
    //      -> loop move from n-1 to 0, i.e ulta
    public void NGOL(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);
        int n = arr.length;

        for(int i = n-1; i >= 0; i--){
            while(st.size() != 0 && arr[i] > arr[st.peek()]){
                ans[st.pop()] = i; 
            }
            st.push(i);
        }
    }

    //----------------------------------------------------------------

    // NSOR -> smaller i.e chotta vala bade vale ko pop karayega
    //      -> loop move from 0 to n-1
    public void NSOR(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(arr, (int)1e9);

        for(int i = 0 ; i < arr.length; i++){
            while(st.size() != 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // NSOL -> smaller i.e chotta vala bade vale ko pop karayega
    //      -> loop move from n-1 to 0 , i.e ulta
    public void NSOL(int[] arr, int[] ans){
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for(int i = arr.length-1; i >= 0; i--){
            while(st.size()!= 0 && arr[i] < arr[st.peek()]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }



}