class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = next(n);
        while (slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return slow == 1;
    }
    private int next(int x) {
        int sum = 0;
        while (x > 0) {
         int d = x % 10;
         sum += d * d;
         x /= 10;
        }
        return sum;
    }
}
