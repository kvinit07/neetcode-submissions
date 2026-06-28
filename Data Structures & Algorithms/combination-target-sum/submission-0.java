class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
      List<List<Integer>> res = new ArrayList<>();
      if (nums == null || nums.length == 0) return res;
      Arrays.sort(nums);
      List<Integer> path = new ArrayList<>();
      dfs(nums, 0, target, path, res);
      return res; 
    }
    private void dfs(int[] nums, int start, int remain, 
                    List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
           res.add(new ArrayList<>(path));
           return;
        }
        for (int i = start; i < nums.length; i++) {
            int pick = nums[i];
            if (pick > remain) break;
            path.add(pick);
            dfs(nums, i, remain - pick, path, res);
            path.remove(path.size() - 1);
        }
    }
}
