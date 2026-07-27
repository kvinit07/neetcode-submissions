class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;
        for (int num: nums) {
            set.add(num);
        }
        for (int num: nums) {
        if (!set.contains(num-1)) {
        int current = num;
        int largest = 1;
        while (set.contains(current+1)) {
        current += 1;
        largest += 1;
        }
        longest = Math.max(longest,largest);
        }
        }
        return longest;
    }
}