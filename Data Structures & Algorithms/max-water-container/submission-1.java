class Solution {
    public int maxArea(int[] heights) {
      int left = 0;
      int right = heights.length - 1;
      int maxArea = 0;
      while (left < right) {
        int width = right - left;
        int h = Math.min(heights[left],heights[right]);
        int Area = width * h;
        maxArea = Math.max(Area, maxArea);
        if (heights[left] < heights[right]) {
            left++;
        }
        else {
            right--;
        }
      }
      return maxArea;
    }
}
