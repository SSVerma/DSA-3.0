package tree;

import tree.traversal.PreOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeDeserialize {
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{1, 2, 13, null, null, 4, 5});
        String serialized = serialize(root);
        System.out.println(serialized);

        Tree.Node deserialized = deserialize(serialized);
        List<Integer> result = PreOrder.traverse(deserialized);
        System.out.println(result);
    }

    private static String serialize(Tree.Node root) {
        if (root == null) {
            return null;
        }

        Queue<Tree.Node> q = new LinkedList<>();
        q.add(root);

        StringBuilder builder = new StringBuilder();

        while (!q.isEmpty()) {
            Tree.Node node = q.remove();

            if (node == null) {
                builder.append("NULL");
            } else {
                builder.append(node.value);
                q.add(node.left);
                q.add(node.right);
            }
            builder.append(DELIMITER);
        }

        return builder.toString();
    }

    private static Tree.Node deserialize(String input) {
        if (input == null) {
            return null;
        }

        String[] nodes = input.split(DELIMITER);
        Queue<Tree.Node> q = new LinkedList<>();

        Tree.Node root = new Tree.Node(Integer.parseInt(nodes[0]));
        q.add(root);

        int index = 1;

        while (!q.isEmpty()) {
            Tree.Node parent = q.remove();

            if (index < nodes.length && !nodes[index].equals("NULL")) {
                parent.left = new Tree.Node(Integer.parseInt(nodes[index]));
                q.add(parent.left);
            }

            index++;

            if (index < nodes.length && !nodes[index].equals("NULL")) {
                parent.right = new Tree.Node(Integer.parseInt(nodes[index]));
                q.add(parent.right);
            }

            index++;
        }

        return root;
    }

}
