package tree.view;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LeftView {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = leftView(root);
        System.out.println(result);
    }

    private static List<Integer> leftView(Tree.Node root) {
        TreeMap<Integer, Integer> resultMap = leftView(root, 0, new TreeMap<>());

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private static TreeMap<Integer, Integer> leftView(
            Tree.Node node,
            int level,
            TreeMap<Integer, Integer> result
    ) {
        if (node == null) {
            return result;
        }

        if (result.get(level) == null) {
            result.put(level, node.value);
        }

        leftView(node.left, level + 1, result);
        leftView(node.right, level + 1, result);

        return result;
    }

}
