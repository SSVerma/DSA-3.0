package tree.bst;

import tree.Tree;
import tree.traversal.LevelOrder;

public class Deletion {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{5, 3, 6, 2, 4, null, 7});
        Tree.Node result = delete(root, 3);
        System.out.println(LevelOrder.traverse(result));
    }

    private static Tree.Node delete(Tree.Node root, int target) {
        if (root == null) {
            return null;
        }

        if (root.value == target) {
            if (isLeafNode(root)) {
                return null;
            }

            if (isSingleChildNode(root)) {
                return root.left == null ? root.right : root.left;
            }

            Tree.Node maxNode = findMax(root.left);
            root.value = maxNode.value;
            root.left = delete(root.left, maxNode.value);
        } else if (root.value > target) {
            root.left = delete(root.left, target);
        } else {
            root.right = delete(root.right, target);
        }

        return root;
    }

    private static Tree.Node findMax(Tree.Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private static boolean isLeafNode(Tree.Node node) {
        return node.left == null && node.right == null;
    }

    private static boolean isSingleChildNode(Tree.Node node) {
        return node.left != null || node.right != null;
    }
}
