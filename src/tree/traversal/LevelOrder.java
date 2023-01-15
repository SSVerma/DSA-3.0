package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = traverse(root);
        System.out.println(result);
    }

    public static List<Integer> traverse(Tree.Node root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<>();

        Queue<Tree.Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Tree.Node node = q.remove();
            result.add(node.value);

            if (node.left != null) {
                q.add(node.left);
            }

            if (node.right != null) {
                q.add(node.right);
            }
        }

        return result;
    }
}
