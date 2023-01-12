package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TimeToBurnTree {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, null, 5, 6, null, 7});
        Tree.Node target = findNode(root, 3);
        if (target == null) {
            return;
        }
        int result = timeToBurnTree(target, findParentOfNodes(root), new HashMap<>());
        System.out.println(result);
    }

    private static Tree.Node findNode(Tree.Node node, int target) {
        if (node == null) {
            return null;
        }

        if (node.value == target) {
            return node;
        }

        Tree.Node left = findNode(node.left, target);
        if (left != null) {
            return left;
        }

        return findNode(node.right, target);
    }

    // basically number of levels or height from the given target node
    private static int timeToBurnTree(Tree.Node root, Tree.Node target) {
        Map<Tree.Node, Tree.Node> parentByNode = findParentOfNodes(root);

        Queue<Tree.Node> q = new LinkedList<>();
        q.add(target);

        Map<Tree.Node, Boolean> visited = new HashMap<>();
        visited.put(target, true);

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Tree.Node node = q.remove();

                if (node.left != null && visited.get(node.left) == null) {
                    q.add(node.left);
                    visited.put(node.left, true);
                }

                if (node.right != null && visited.get(node.right) == null) {
                    q.add(node.right);
                    visited.put(node.right, true);
                }

                Tree.Node parent = parentByNode.get(node);

                if (parent != null && visited.get(parent) == null) {
                    q.add(parentByNode.get(node));
                    visited.put(parent, true);
                }
            }

            level++;
        }

        return level - 1;
    }

    // Using DFS - find height
    private static int timeToBurnTree(
            Tree.Node node,
            Map<Tree.Node, Tree.Node> parentByNode,
            Map<Tree.Node, Boolean> visited
    ) {
        if (node == null) {
            return 0;
        }

        if (visited.get(node) != null) {
            return 0;
        }

        visited.put(node, true);

        int left = timeToBurnTree(node.left, parentByNode, visited);
        int right = timeToBurnTree(node.right, parentByNode, visited);
        int upwards = timeToBurnTree(parentByNode.get(node), parentByNode, visited);

        int max = Math.max(left, right);
        max = Math.max(max, upwards);

        return 1 + max;
    }

    private static Map<Tree.Node, Tree.Node> findParentOfNodes(Tree.Node root) {
        Queue<Tree.Node> q = new LinkedList<>();
        q.add(root);

        Map<Tree.Node, Tree.Node> parentByNode = new HashMap<>();

        while (!q.isEmpty()) {
            Tree.Node node = q.remove();

            if (node.left != null) {
                parentByNode.put(node.left, node);
                q.add(node.left);
            }

            if (node.right != null) {
                parentByNode.put(node.right, node);
                q.add(node.right);
            }
        }

        return parentByNode;
    }
}
