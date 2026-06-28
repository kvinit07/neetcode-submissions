class Solution {
    private static final String[] KEYPAD = {
      "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        StringBuilder current = new StringBuilder();
        backtrack(digits, 0, current, res);
        return res;
    }
    private void backtrack(String digits, int index, StringBuilder cur,
    List<String> res) {
        if (index == digits.length()) {
            res.add(cur.toString());
            return;
        }
        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            cur.append(letters.charAt(i));
            backtrack(digits, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
