class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      List<List<Integer>> graph = new ArrayList<>();
      for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
      int[] inDegree = new int[numCourses];
      for (int[] p : prerequisites) {
        graph.get(p[1]).add(p[0]);
        inDegree[p[0]]++;
      } 
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int c = 0; c < numCourses; c++) if (inDegree[c] == 0) pq.offer(c);
      int[] order = new int[numCourses];
      int index = 0;
      while (!pq.isEmpty()) {
        int cur = pq.poll();
        order[index++] = cur;
        for (int nxt : graph.get(cur)) {
            if (--inDegree[nxt] == 0) pq.offer(nxt);
        }
      }
      return index == numCourses ? order : new int[0];
    }
}
