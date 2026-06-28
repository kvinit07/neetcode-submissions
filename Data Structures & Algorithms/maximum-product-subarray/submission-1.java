class Solution {
    public int maxProduct(int[] nums) {
      int maxProd = nums[0];
      int minProd = nums[0];
      int ans = nums[0];
      for (int i = 1; i < nums.length; i++) {
        int x = nums[i];
        if (x < 0) {
           int tmp = maxProd;
           maxProd = minProd;
           minProd = tmp;
        }
        maxProd = Math.max(x, maxProd * x);
        minProd = Math.min(x, minProd * x);
        ans = Math.max(ans, maxProd);
      }
      return ans;
    }
}
