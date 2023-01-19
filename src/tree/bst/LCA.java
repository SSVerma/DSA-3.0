package tree.bst;

import tree.Tree;

public class LCA {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        Tree.Node result = findLCA(root, 0, 5);
        System.out.println(result == null ? null : result.value);
    }

    private static Tree.Node findLCA(Tree.Node root, int first, int second) {
        if (root == null) {
            return null;
        }

        if (root.value == first || root.value == second) {
            return root;
        }

        if (first < root.value && second > root.value) {
            return root;
        }

        if (first > root.value && second < root.value) {
            return root;
        }

        if (first < root.value && second < root.value) {
            return findLCA(root.left, first, second);
        }

        return findLCA(root.right, first, second);
    }
}
