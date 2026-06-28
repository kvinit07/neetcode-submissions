class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;            
        UnionFind uf = new UnionFind(n + 1); 
        int[] redundant = new int[2];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (uf.find(u) == uf.find(v)) {
                redundant = e;             
            } else {
                uf.union(u, v);
            }
        }
        return redundant;
    }
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[rb] < rank[ra]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}
