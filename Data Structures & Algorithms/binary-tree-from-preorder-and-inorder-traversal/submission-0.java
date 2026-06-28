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
    Map<Integer, Integer> inIndex;
    private int preIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndex.put(inorder[i], i);
        }
        preIndex = 0;
        return build(preorder, 0, inorder.length - 1);
    }
    private TreeNode build(int[] preorder, int l, int r) {
        if (l > r) return null;
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        int mid = inIndex.get(rootVal);
        root.left = build(preorder, l, mid - 1);
        root.right = build(preorder, mid + 1, r);
        return root;
    }
}
