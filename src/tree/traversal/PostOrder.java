package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = postorder(root, new ArrayList<>());
        System.out.println(result);
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
