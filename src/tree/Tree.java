package tree;

import java.util.Arrays;
import java.util.List;

public class Tree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
        [2, 1, 3, null, 4, null, 5]

                 2
                / \
               1   3
                \   \
                 4   5

     */
    public static Tree.Node create(List<Integer> nodes) {
        return create(nodes, 0);
    }

    public static Tree.Node create(Integer[] nodes) {
        return create(Arrays.asList(nodes), 0);
    }

    private static Tree.Node create(List<Integer> nodes, int index) {
        if (index >= nodes.size()) {
            return null;
        }

        if (nodes.get(index) == null) {
            return null;
        }

        Tree.Node node = new Tree.Node(nodes.get(index));

        node.left = create(nodes, 2 * index + 1);
        node.right = create(nodes, 2 * index + 2);

        return node;
    }

}
