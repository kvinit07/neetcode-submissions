class Solution {
    public List<String> generateParenthesis(int n) {
               List<String> listAnswer = new ArrayList<>();
        generate(listAnswer, n, "(", 1, 0);
        return listAnswer;
    }
    private void generate (List<String> listAnswer, int n, String subAnswer, int open, int close) {
        if (subAnswer.length() == n * 2) {
            listAnswer.add(subAnswer);
            return;
        }
        
        if (open < n)
            generate(listAnswer, n, subAnswer + "(", open + 1, close);
            
        if (close < open)
            generate(listAnswer, n, subAnswer + ")", open, close + 1);
    }
}
