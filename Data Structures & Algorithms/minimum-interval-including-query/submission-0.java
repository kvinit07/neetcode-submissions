
public class Solution {
    /**
     * Returns an array where ans[j] is the length of the shortest interval that covers queries[j],
     * or -1 if no interval covers queries[j].
     *
     * Time:  O(n log n + m log m + (n + m) log n) ≈ O((n + m) log n)
     * Space: O(n + m)
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        int m = queries.length;
        int[] ans = new int[m];

        // 1) Sort intervals by left (start)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 2) Pair queries with original indices and sort by query value
        int[][] qPairs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qPairs[i][0] = queries[i]; // query value
            qPairs[i][1] = i;          // original index
        }
        Arrays.sort(qPairs, (a, b) -> Integer.compare(a[0], b[0]));

        // 3) Min-heap of "eligible intervals" for the current query q.
        // Store {length, right}. Ordered by smallest 'length' first.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (x, y) -> Integer.compare(x[0], y[0])
        );

        // Pointer to scan intervals by left
        int i = 0;

        // 4) Process queries in increasing order
        for (int[] qp : qPairs) {
            int q = qp[0];
            int idx = qp[1];

            // Add to heap all intervals whose left <= q (they might cover q)
            while (i < n && intervals[i][0] <= q) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int length = right - left + 1;
                minHeap.offer(new int[]{length, right});
                i++;
            }

            // Remove intervals that cannot cover q (right < q)
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }

            // Top of heap is the shortest interval that covers q, if any
            ans[idx] = minHeap.isEmpty() ? -1 : minHeap.peek()[0];
        }

        return ans;
    }
}
