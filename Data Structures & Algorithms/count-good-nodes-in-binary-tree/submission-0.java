/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, root.val);
    }
    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int good = (node.val >= maxSoFar) ? 1 : 0;   
        int newMax = Math.max(maxSoFar, node.val);    
        int leftCount = dfs(node.left, newMax);      
        int rightCount = dfs(node.right, newMax);     
        return good + leftCount + rightCount;        
    }
}

