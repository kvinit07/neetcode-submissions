class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(s, 0, path, res);
        return res;
    }
    private void dfs(String s, int start, List<String> path, List<List<String>> res) {
        int n = s.length();
        if (start == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < n; end++) {
            if (isPalindrone(s, start, end)) {
               path.add(s.substring(start, end + 1));
               dfs(s, end + 1, path, res);
               path.remove(path.size()-1);
            }
        }
    }
    private boolean isPalindrone(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
         return true;
    }
}
