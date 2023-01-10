package tree;

import java.util.*;

public class NodesAtDistanceK {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        Tree.Node target = findNode(root, 5);
        if (target == null) {
            return;
        }
        List<Integer> result = findNodesAtKDistance(root, target, 2);
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

    private static List<Integer> findNodesAtKDistance(Tree.Node root, Tree.Node target, int k) {
        Map<Tree.Node, Tree.Node> parentByNode = findParentOfNodes(root);

        Queue<Tree.Node> q = new LinkedList<>();
        q.add(target);

        Map<Tree.Node, Boolean> visited = new HashMap<>();
        visited.put(target, true);

        while (!q.isEmpty() && k > 0) {
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

            // one level visited
            k--;
        }

        List<Integer> result = new ArrayList<>();

        for (Tree.Node node : q) {
            result.add(node.value);
        }

        return result;
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
