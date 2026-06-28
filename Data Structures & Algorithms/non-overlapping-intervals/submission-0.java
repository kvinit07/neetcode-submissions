class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
      if (intervals == null || intervals.length <= 1) return 0;
      Arrays.sort(intervals, (a, b) -> {
        if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
        return Integer.compare(a[0],b[0]);
      });
      int removals = 0;
      int prevEnd = intervals[0][1];
      for (int i = 1; i < intervals.length; i++) {
         int currentStart = intervals[i][0];
         int currentEnd = intervals[i][1];
         if (currentStart >= prevEnd) {
            prevEnd = currentEnd;
         }
         else {
          removals++;
         }
      }
      return removals;
    }
}
