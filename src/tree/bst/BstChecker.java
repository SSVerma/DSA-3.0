package tree.bst;

import tree.Tree;

public class BstChecker {
    public static void main(String[] args) {
//        Tree.Node root = Tree.create(new Integer[]{5, 1, 6, null, null, 4, 8});
        Tree.Node root = Tree.create(new Integer[]{5, 1, 7, null, null, 6, 8});

        boolean result = isBst(root);
        System.out.println(result);
    }

    private static boolean isBst(Tree.Node root) {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBst(Tree.Node root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.value < min || root.value > max) {
            return false;
        }

        boolean isBst = isBst(root.left, min, root.value);

        if (!isBst) {
            return false;
        }

        return isBst(root.right, root.value, max);
    }

    private static Tree.Node prev = null;

    // Inorder sorted
    private static boolean isBstWithSideEffect(Tree.Node root) {
        if (root == null) {
            return true;
        }

        boolean isBst = isBstWithSideEffect(root.left);

        if (!isBst) {
            return false;
        }

        if (prev != null && prev.value > root.value) {
            return false;
        }

        prev = root;

        return isBstWithSideEffect(root.right);
    }

}
