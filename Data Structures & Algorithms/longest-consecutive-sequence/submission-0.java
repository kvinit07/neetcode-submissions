class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        int maxLen = 0;
        for (Integer ele : set) {
            int prev = ele-1;
            if (!set.contains(prev)) {
                int len = 1;
                int next = ele+1;
                while(set.contains(next)) {
                    len++;
                    next++;
                }
                maxLen = Math.max(maxLen,len);
            }
        }
        return maxLen;
    }
}
