package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZag {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, 5, null, 6, 7, 8});

        List<Integer> result = traverse(root);
        System.out.println(result);
    }

    private static List<Integer> traverse(Tree.Node root) {
        Queue<Tree.Node> q = new LinkedList<>();
        q.add(root);

        boolean isLeftToRight = true;

        List<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();

            LinkedList<Integer> levelNodes = new LinkedList<>();

            while (size-- > 0) {
                Tree.Node node = q.remove();

                if (isLeftToRight) {
                    levelNodes.add(node.value);
                } else {
                    levelNodes.addFirst(node.value);
                }

                if (node.left != null) {
                    q.add(node.left);
                }

                if (node.right != null) {
                    q.add(node.right);
                }
            }

            isLeftToRight = !isLeftToRight;
            result.addAll(levelNodes);
        }

        return result;
    }
}
