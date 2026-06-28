public class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        int total = m + n - 2;            // total moves (D + R)
        int r = Math.min(m - 1, n - 1);   // choose the smaller of D or R
        long res = 1L;

        // Compute C(total, r) via multiplicative formula with GCD reduction
        // C(total, r) = product_{i=1..r} (total - r + i) / i
        for (int i = 1; i <= r; i++) {
            long num = total - r + i; // numerator term
            long den = i;             // denominator term

            // Reduce num/den by their GCD first
            long g1 = gcd(num, den);
            num /= g1;
            den /= g1;

            // Further reduce res and den to avoid overflow in res *= num
            long g2 = gcd(res, den);
            res /= g2;
            den /= g2;

            // Now safe to multiply
            res *= num; // den should be 1 here
        }

        return (int) res; // problem guarantees fits in 32-bit int
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
