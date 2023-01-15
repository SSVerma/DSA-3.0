package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class PostOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = postorder(root, new ArrayList<>());
        System.out.println(result);
    }

    public static List<Integer> traverse(Tree.Node node) {
        return postorder(node, new ArrayList<>());
    }

    private static List<Integer> postorder(Tree.Node node, List<Integer> result) {
        if (node == null) {
            return result;
        }

        postorder(node.left, result);
        postorder(node.right, result);

        result.add(node.value);

        return result;
    }
}
