class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap by squared distance to origin
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Long.compare(dist2(b), dist2(a))
        );

        // Process each point
        for (int[] p : points) {
            maxHeap.offer(p); // Insert point
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove farthest point
            }
        }
        int[][] result = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            result[i++] = maxHeap.poll();
        }
        return result;
    }
    private long dist2(int[] p) {
        long x = p[0], y = p[1];
        return x * x + y * y;
    }
}