package tree.width;

import tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidth {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, null, null, 5});
//        Tree.Node root = Tree.create(new Integer[]{1, 3, 2, 5, null, null, 9, 6, null, null, null, null, null, 7, null});

        int result = maximumWidth(root);
        System.out.println(result);
    }

    /*
     *
     *       1
     *     2   3
     *   4       5
     *
     *  width = 4
     *
     *       1
     *    2    3
     *  4
     *
     *  width = 2
     *
     * */
    private static int maximumWidth(Tree.Node root) {
        Queue<NodeInfo> q = new LinkedList<>();
        q.add(new NodeInfo(root, 0));

        int maxWidth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            int from = -1;
            int to = 0;

            for (int i = 0; i < size; i++) {
                NodeInfo nodeInfo = q.remove();

                if (from == -1) {
                    from = nodeInfo.number;
                } else {
                    to = nodeInfo.number;
                }

                if (nodeInfo.node.left != null) {
                    int number = 2 * nodeInfo.number + 1;
                    NodeInfo left = new NodeInfo(nodeInfo.node.left, number);
                    q.add(left);
                }

                if (nodeInfo.node.right != null) {
                    int number = 2 * nodeInfo.number + 2;
                    NodeInfo right = new NodeInfo(nodeInfo.node.right, number);
                    q.add(right);
                }
            }

            int width = to - from + 1;

            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        return maxWidth;
    }

    record NodeInfo(Tree.Node node, int number) {
    }
}
