package tree.height;

import tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHeight {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        int result = height(root);
        System.out.println(result);
    }

    public static int height(Tree.Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static int findHeight(Tree.Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Tree.Node> q = new LinkedList<>();
        q.add(root);

        int levelCount = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Tree.Node node = q.remove();

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }

            levelCount++;
        }

        return levelCount;
    }

}
