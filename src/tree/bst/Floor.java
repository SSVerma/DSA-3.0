package tree.bst;

import tree.Tree;

public class Floor {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{8, 5, 12, 4, 7, 9, 14, null, null, 6, null, null, null, 13, null});
        int result = findFlor(root, 10);
        System.out.println(result);
    }

    private static int findFlor(Tree.Node root, int target) {
        int floor = Integer.MIN_VALUE;

        while (root != null) {
            if (root.value > floor && root.value <= target) {
                floor = root.value;
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return floor == Integer.MIN_VALUE ? -1 : floor;
    }
}
