package tree.bst;

import tree.Tree;

public class Ceil {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{8, 5, 12, 4, 7, 9, 14, null, null, 6, null, null, null, 13, null});
        int result = findCeil(root, 10);
        System.out.println(result);
    }

    private static int findCeil(Tree.Node root, int target) {
        int ceil = Integer.MAX_VALUE;

        while (root != null) {
            if (root.value < ceil && root.value >= target) {
                ceil = root.value;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return ceil == Integer.MAX_VALUE ? -1 : ceil;
    }
}
