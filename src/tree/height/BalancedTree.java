package tree.height;

import tree.Tree;

public class BalancedTree {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{3, 9, 20, null, null, 15, 7});
//        Tree.Node root = Tree.create(new Integer[]{1, 3, 2, 5, 4, null, null, 7, 6, null, null});

        boolean result = isTreeBalanced(root);
        System.out.println(result);
    }

    /*
        height difference of left and right subtree
        for every node should be at max 1.

     */
    private static boolean isBalancedTree(Tree.Node node) {
        if (node == null) {
            return true;
        }

        int leftHeight = TreeHeight.height(node.left);
        int rightHeight = TreeHeight.height(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalancedTree(node.left) && isBalancedTree(node.right);
    }

    private static boolean isTreeBalanced(Tree.Node node) {
        return height(node) != -1;
    }

    private static int height(Tree.Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = height(node.right);

        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

}
