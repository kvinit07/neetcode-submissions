class Solution {

    public int uniquePaths(int m, int n) {
        // 1) Number of down moves and right moves
        int d = m - 1;               // e.g., m=3 => 2 downs
        int r = n - 1;               // e.g., n=6 => 5 rights

        // 2) We compute C(d + r, k) with k = min(d, r) for fewer iterations
        int k = Math.min(d, r);

        // 3) N is total steps; result in long to avoid intermediate overflow
        int N = d + r;               // total steps
        long res = 1L;               // running product (fits in long for given guarantees)

        // 4) Multiplicative formula:
        //    C(N, k) = Π_{i=1..k} (N - k + i) / i
        //    Doing division at each step keeps values small; division is exact.
        for (int i = 1; i <= k; i++) {
            res = res * (N - k + i) / i;
        }

        // 5) Problem guarantees result fits in 32-bit int.
        return (int) res;
    }
}