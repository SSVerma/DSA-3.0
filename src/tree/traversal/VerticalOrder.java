package tree.traversal;

import tree.Tree;

import java.util.*;

public class VerticalOrder {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<List<Integer>> result = traverseUsingLevelOrder(root);
        System.out.println(result);
    }

    private static List<List<Integer>> traverse(Tree.Node root) {
        TreeMap<Integer, List<Integer>> mapResult = traverse(root, 0, new TreeMap<>());

        List<List<Integer>> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Integer>> entry : mapResult.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private static TreeMap<Integer, List<Integer>> traverse(
            Tree.Node node,
            int horizontalLine,
            TreeMap<Integer, List<Integer>> result
    ) {
        if (node == null) {
            return result;
        }

        List<Integer> nodes = result.get(horizontalLine);
        if (nodes == null) {
            nodes = new ArrayList<>();
            result.put(horizontalLine, nodes);
        }

        nodes.add(node.value);

        traverse(node.left, horizontalLine - 1, result);
        traverse(node.right, horizontalLine + 1, result);

        return result;
    }

    private static List<List<Integer>> traverseUsingLevelOrder(Tree.Node root) {
        Queue<NodeData> q = new LinkedList<>();
        q.add(new NodeData(root, 0));

        TreeMap<Integer, List<Integer>> resultMap = new TreeMap<>();

        while (!q.isEmpty()) {
            NodeData nodeData = q.remove();

            List<Integer> nodes = resultMap.get(nodeData.horizontalLine);
            if (nodes == null) {
                nodes = new ArrayList<>();
                resultMap.put(nodeData.horizontalLine, nodes);
            }

            nodes.add(nodeData.node.value);

            if (nodeData.node.left != null) {
                q.add(new NodeData(nodeData.node.left, nodeData.horizontalLine - 1));
            }

            if (nodeData.node.right != null) {
                q.add(new NodeData(nodeData.node.right, nodeData.horizontalLine + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Integer>> entry : resultMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }


    record NodeData(Tree.Node node, int horizontalLine) {
    }

}
