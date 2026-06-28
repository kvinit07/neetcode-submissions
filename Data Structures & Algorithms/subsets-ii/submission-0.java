class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       if (nums == null) return res;
       Arrays.sort(nums);
       List<Integer> path = new ArrayList<>();
       dfs(nums, 0, path, res);
       return res; 
    }
    private void dfs(int nums[], int start, List<Integer> path,
    List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
           if (i > start && nums[i] == nums[i - 1]) continue;
           path.add(nums[i]);
           dfs(nums, i + 1, path, res);
           path.remove(path.size() - 1);
        }
    }
}
