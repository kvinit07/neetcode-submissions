class Solution {
    public List<String> generateParenthesis(int n) {
       List<String> result = new ArrayList<>();
        if (n == 0) return result;
        Stack<String> stack = new Stack<>();
        Stack<Integer> openStack = new Stack<>();
        Stack<Integer> closeStack = new Stack<>();
        stack.push("");
        openStack.push(0);
        closeStack.push(0); 
        while (!stack.isEmpty()) {
            String current = stack.pop();
            int open = openStack.pop();
            int close = closeStack.pop();
            
            if (current.length() == n * 2) {
                result.add(current);
                continue;
            }
            
            if (open < n) {
                stack.push(current + "(");
                openStack.push(open + 1);
                closeStack.push(close);
            }
            
            if (close < open) {
                stack.push(current + ")");
                openStack.push(open);
                closeStack.push(close + 1);
            }
        }
        
        return result; 
    }
}
