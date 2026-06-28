class Solution {
    public int uniquePaths(int m, int n) {
      int d = m - 1;
      int r = n - 1;
      int k = Math.min(d, r);
      int N = d + r;
      long res = 1L;
      for (int i = 1; i <= k; i++) {
        res = res * (N- k + i) / i;
      }
      return (int) res;
    }
}
