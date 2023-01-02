package tree;

public class SymmetricalTree {
    public static void main(String[] args) {
//        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});
        Tree.Node root = Tree.create(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        boolean result = isSymmetrical(root);
        System.out.println(result);
    }

    private static boolean isSymmetrical(Tree.Node root) {
        if (root == null) {
            return true;
        }

        return isSymmetrical(root.left, root.right);
    }

    private static boolean isSymmetrical(Tree.Node left, Tree.Node right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.value != right.value) {
            return false;
        }

        return isSymmetrical(left.left, right.right)
                && isSymmetrical(left.right, right.left);
    }

}
