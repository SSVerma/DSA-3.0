package tree.bst;

import tree.Tree;

public class Search {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{8, 5, 12, 4, 7, 10, 14, null, null, 6, null, null, null, 13, null});
        boolean result = doesExist(root, 5);
        System.out.println(result);
    }

    private static boolean doesExist(Tree.Node root, int target) {
        if (root == null) {
            return false;
        }

        if (root.value == target) {
            return true;
        }

        if (target < root.value) {
            return doesExist(root.left, target);
        }

        return doesExist(root.right, target);
    }

    private static boolean checkExist(Tree.Node root, int target) {
        Tree.Node current = root;
        while (current != null) {
            if (current.value == target) {
                return true;
            }
            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

}
