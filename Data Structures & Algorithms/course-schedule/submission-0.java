public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            graph.get(b).add(a);
            inDegree[a]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int c = 0; c < numCourses; c++) {
            if (inDegree[c] == 0) q.offer(c);
        }
        int processed = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            processed++;
            for (int next : graph.get(cur)) {
                if (--inDegree[next] == 0) q.offer(next);
            }
        }
        return processed == numCourses;
    }
}
