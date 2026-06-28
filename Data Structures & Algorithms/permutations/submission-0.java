class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      if (nums == null || nums.length == 0) return res;
      boolean[] used = new boolean[nums.length];
      List<Integer> path = new ArrayList<>();
      dfs(nums, used, path, res);
      return res;
    }
    private void dfs(int[] nums, boolean[] used, List<Integer> path,
    List<List<Integer>> res) {
       if (path.size() == nums.length) {
         res.add(new ArrayList<>(path));
         return;
       }
       for (int i = 0; i < nums.length; i++) {
         if (used[i]) continue;
         used[i] = true;
         path.add(nums[i]);
         dfs(nums, used, path, res);
         path.remove(path.size() - 1);
         used[i] = false;
       }
    }
}
