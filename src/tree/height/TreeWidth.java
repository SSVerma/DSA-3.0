package tree.height;

import tree.Tree;

import java.util.concurrent.atomic.AtomicInteger;

public class TreeWidth {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, 5});

        int result = findDiameter(root, 0);
        System.out.println(result);
    }

    /*
       max path b/w any 2 nodes.
     */
    private static int findDiameter(Tree.Node node, int diameter) {
        if (node == null) {
            return diameter;
        }

        int leftHeight = TreeHeight.height(node.left);
        int rightHeight = TreeHeight.height(node.right);

        int pathLengthNow = 1 + leftHeight + rightHeight;

        if (pathLengthNow > diameter) {
            diameter = pathLengthNow;
        }

        int left = findDiameter(node.left, diameter);
        int right = findDiameter(node.right, diameter);

        return Math.max(left, right);
    }

    private static int findDiameter(Tree.Node node) {
        AtomicInteger diameter = new AtomicInteger(0);
        height(node, diameter);
        return diameter.get();
    }

    private static int height(Tree.Node node, AtomicInteger diameter) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left, diameter);
        int rightHeight = height(node.right, diameter);

        int diameterNow = 1 + leftHeight + rightHeight;

        if (diameterNow > diameter.get()) {
            diameter.set(diameterNow);
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

}
