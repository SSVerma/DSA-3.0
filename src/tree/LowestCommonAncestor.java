package tree;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, 5, 8, 9, null, null, 6, 7});

        Tree.Node result = lca(root, 6, 8);
        System.out.println(result.value);
    }

    private static Tree.Node lca(Tree.Node root, int first, int second) {
        if (root == null) {
            return null;
        }

        if (root.value == first || root.value == second) {
            return root;
        }

        Tree.Node left = lca(root.left, first, second);
        Tree.Node right = lca(root.right, first, second);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        return right;
    }

}
