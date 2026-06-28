class Solution {
    public boolean validTree(int n, int[][] edges) {
       if (edges.length != n - 1) return false;
       List<List<Integer>> graph = new ArrayList<>();
       for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
       for (int[] e : edges) {
         int u = e[0], v = e[1];
         graph.get(u).add(v);
         graph.get(v).add(u);
       }
       boolean[] visited  = new boolean[n];
       if (hasCycle(graph, 0, -1, visited)) return false;
       for (boolean seen : visited) {
         if (!seen) return false;
       }
       return true;
    }
    private boolean hasCycle(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
        if (visited[node]) return true;
        visited[node] = true;
        for (int nei : graph.get(node)) {
            if (nei == parent) continue;
            if (hasCycle(graph, nei, node, visited)) return true;
        }
        return false;
    }
}
