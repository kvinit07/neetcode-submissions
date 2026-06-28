class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] buf = new char[2 * n];         
        backtrack(res, buf, 0, 0, 0, n);     
        return res;
    }

    private void backtrack(List<String> res, char[] buf, int i, int open, int close, int n) {
    
        if (i == 2 * n) {
            res.add(new String(buf)); 
            return;
        }

        if (open < n) {
            buf[i] = '(';                                
            backtrack(res, buf, i + 1, open + 1, close, n);
        }

        if (close < open) {
            buf[i] = ')';                                
            backtrack(res, buf, i + 1, open, close + 1, n);
        }
    }
}