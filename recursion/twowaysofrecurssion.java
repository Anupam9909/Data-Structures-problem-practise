// leetcode-90   :  subset II  problem ka post ha ye kissi ka

// Each recursion level focuses on all the following elements. We scan through all the following elements and decide whether to choose or not choose that element. (Every level split into N branches.)

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for(int i=pos;i<nums.length;i++) {
            if(i>pos&&nums[i]==nums[i-1]) continue;
            ls.add(nums[i]);
            helper(res,ls,nums,i+1);     
            ls.remove(ls.size()-1);
        }
    }
}

// Each recursion level focuses on one element, we need to decide choose or not choose this element. (Every level split into 2 branches.)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<>(),nums,0,false);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos, boolean choosePre) {
        if(pos==nums.length) {
            res.add(new ArrayList<>(ls));
            return;
        }
        helper(res,ls,nums,pos+1,false);
        if(pos>=1&&nums[pos]==nums[pos-1]&&!choosePre) return;
        ls.add(nums[pos]);
        helper(res,ls,nums,pos+1,true);
        ls.remove(ls.size()-1);
    }
}