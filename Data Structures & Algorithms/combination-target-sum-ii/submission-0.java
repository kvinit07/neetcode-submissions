class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        dfs(candidates, 0, target, path, res);
        return res;
    }
    private void dfs(int[] nums, int start, int remain, List<Integer> path, 
    List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
           if (i > start && nums[i] == nums[i - 1]) continue;
           int pick = nums[i];
           if (pick > remain) break;
           path.add(pick);
           dfs(nums, i + 1, remain - pick, path, res);
           path.remove(path.size() - 1);
        }
    }
}
