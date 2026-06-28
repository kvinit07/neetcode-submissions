class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int a = target[0], b = target[1], c = target[2];
        boolean hasA = false, hasB = false, hasC = false;
        for (int[] triple : triplets) {
            int x = triple[0], y = triple[1], z = triple[2];
            if (x > a || y > b || z > c) continue;
            if (x == a) hasA = true;
            if (y == b) hasB = true;
            if (z == c) hasC = true;
            if (hasA && hasB && hasC) return true;
        }
        return hasA && hasB && hasC;
    }
}
