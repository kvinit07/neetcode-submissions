class Solution {
    public int countComponents(int n, int[][] edges) {
       List<List<Integer>> graph = new ArrayList<>();
       for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
       for (int[] e : edges) {
         int u = e[0], v = e[1];
         graph.get(u).add(v);
         graph.get(v).add(u);
       }
       boolean[] visited = new boolean[n];
       int components = 0;
       for (int i = 0; i < n; i++) {
         if (!visited[i]) {
            components++;
            dfs(graph,i,visited);
         }
       }
       return components;
    }
    private void dfs(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        for (int nei : graph.get(start)) {
            if (!visited[nei]) dfs(graph, nei, visited);
        }
    }
}
