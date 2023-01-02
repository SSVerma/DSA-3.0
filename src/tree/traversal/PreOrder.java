package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = preOrder(root, new ArrayList<>());
        System.out.println(result);
    }

    public static List<Integer> preOrder(Tree.Node node, List<Integer> result) {
        if (node == null) {
            return result;
        }

        result.add(node.value);

        preOrder(node.left, result);
        preOrder(node.right, result);

        return result;
    }

    private static List<Integer> preOrder(Tree.Node root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<Tree.Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Tree.Node node = stack.pop();
            if (node == null) {
                continue;
            }

            result.add(node.value);

            stack.push(node.right);
            stack.push(node.left);
        }

        return result;
    }

}
