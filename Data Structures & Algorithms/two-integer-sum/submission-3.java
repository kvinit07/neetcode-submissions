class Solution {
    public int[] twoSum(int[] nums, int target) {
     Map<Integer, Integer> indexOf = new HashMap<>();
     for (int i = 0; i < nums.length; i++) {
        int x = nums[i];
        int complement = target - x;
        if (indexOf.containsKey(complement)) {
            return new int[] { indexOf.get(complement), i};
        }
        indexOf.put(x,i);
     }
     throw new IllegalArgumentException("No two sum solution exists for the given input.");
    }
}
