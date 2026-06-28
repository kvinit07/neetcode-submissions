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
    private int globalMax;
    public int maxPathSum(TreeNode root) {
      globalMax = Integer.MIN_VALUE;
      dfs(root);
      return globalMax;
    }
    private int dfs(TreeNode node) {
       if (node == null) return 0;
       int leftGain = Math.max(0, dfs(node.left));
       int rightGain = Math.max(0, dfs(node.right));
       int pathThrough = node.val + leftGain + rightGain;
       globalMax = Math.max(globalMax, pathThrough);
       return node.val + Math.max(leftGain, rightGain);
    }
}
