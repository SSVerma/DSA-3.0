package tree.traversal;

import tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Boundary {
    public static void main(String[] args) {
//        Tree.Node root = Tree.create(new Integer[]{10, 5, 20, 3, 8, 18, 25, null, null, 7});
        Tree.Node root = Tree.create(
                new Integer[]{100, 50, 150, 25, 75, 140, 200, null, 30, 70, 80, null, null, null, null, null, null, null, 35}
        );


        List<Integer> result = traverse(root);
        System.out.println(result);
    }

    private static List<Integer> traverse(Tree.Node root) {
        List<Integer> leftBoundaryNodes = fetchLeftBoundaryNodes(root);
        List<Integer> leafNodes = fetchLeafNodes(root, new ArrayList<>());
        List<Integer> rightBoundaryNodes = fetchRightBoundaryNodes(root);

        List<Integer> result = new ArrayList<>();
        result.addAll(leftBoundaryNodes);
        result.addAll(leafNodes);
        result.addAll(rightBoundaryNodes);

        return result;
    }

    private static List<Integer> fetchLeftBoundaryNodes(Tree.Node root) {
        Tree.Node node = root;

        List<Integer> result = new ArrayList<>();

        while (node != null && !isLeaf(node)) {
            result.add(node.value);
            if (node.left == null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return result;
    }

    private static boolean isLeaf(Tree.Node node) {
        return node.left == null && node.right == null;
    }

    private static List<Integer> fetchLeafNodes(Tree.Node node, List<Integer> result) {
        if (node == null) {
            return result;
        }

        if (isLeaf(node)) {
            result.add(node.value);
        }

        fetchLeafNodes(node.left, result);
        fetchLeafNodes(node.right, result);

        return result;
    }

    private static List<Integer> fetchRightBoundaryNodes(Tree.Node root) {
        Tree.Node node = root.right;

        LinkedList<Integer> result = new LinkedList<>();

        while (node != null && !isLeaf(node)) {
            result.addFirst(node.value); // reverse order

            if (node.right == null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return result;
    }
}
