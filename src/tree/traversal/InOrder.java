package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class InOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = inorder(root, new ArrayList<>());
        System.out.println(result);
    }

    private static List<Integer> inorder(Tree.Node node, List<Integer> result) {
        if (node == null) {
            return result;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);

        return result;
    }
}
