package tree;

import tree.traversal.LevelOrder;

import java.util.List;

public class ChildrenSumProperty {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{40, 10, 20, 2, 5, 30, 40});
        childrenSum(root);
        List<Integer> result = LevelOrder.traverse(root);
        System.out.println(result);
    }

    private static void childrenSum(Tree.Node root) {
        if (root == null) {
            return;
        }

        int left = root.left == null ? 0 : root.left.value;
        int right = root.right == null ? 0 : root.right.value;

        int sum = left + right;

        if (sum < root.value) {
            if (root.left != null) {
                root.left.value = root.value;
            }
            if (root.right != null) {
                root.right.value = root.value;
            }
        } else {
            root.value = sum;
        }

        childrenSum(root.left);
        childrenSum(root.right);

        left = root.left == null ? 0 : root.left.value;
        right = root.right == null ? 0 : root.right.value;

        if (left + right != 0) {
            root.value = left + right;
        }
    }

}
