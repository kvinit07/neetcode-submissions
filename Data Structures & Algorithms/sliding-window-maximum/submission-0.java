class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        if (k == 1) return nums.clone(); // each element is its own window

        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices, values decreasing

        for (int i = 0; i < n; i++) {
            // 1) Remove indices that are out of the current window [i-k+1, i]
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2) Maintain decreasing order: remove smaller/equal values from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3) Add current index
            dq.offerLast(i);

            // 4) Record the max for windows that have formed (i >= k-1)
            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}