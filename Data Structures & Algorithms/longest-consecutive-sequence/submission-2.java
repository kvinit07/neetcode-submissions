class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        HashSet<Integer> set = new HashSet<>(Math.max(16, (int)(n / 0.75f) + 1));
        for (int x : nums) set.add(x);

        int best = 0;
        for (int x : nums) {
            if (!set.contains(x)) continue;

            if (!set.contains(x - 1)) {
                int len = 0;
                int curr = x;
                while (set.remove(curr)) {
                    len++;
                    curr++;
                }
                if (len > best) best = len;
            }
        }
        return best;
    }
}