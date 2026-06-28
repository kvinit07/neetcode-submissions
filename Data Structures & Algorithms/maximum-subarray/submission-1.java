class Solution {
    public int maxSubArray(int[] nums) {
      int currentSum = nums[0];
      int sum = nums[0];
      for (int i = 1; i < nums.length; i++) {
         int x = nums[i];
         currentSum = Math.max(x, x + currentSum);
         sum = Math.max(sum, currentSum);
      }
      return sum;
    }
}
