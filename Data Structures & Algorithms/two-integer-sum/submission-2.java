class Solution {
    public int[] twoSum(int[] nums, int target) {
      Map<Integer, Integer> indexByValue = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];
        if (indexByValue.containsKey(need)) {
            int j = indexByValue.get(need);
            return new int[]{j,i};
        }
        indexByValue.put(nums[i], i);
      }
      throw new IllegalArgumentException("No valid pair found");
    }
}
