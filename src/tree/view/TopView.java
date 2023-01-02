package tree.view;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopView {
    public static void main(String[] args) {
//        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});
        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        List<Integer> result = topView(root);
        System.out.println(result);
    }

    private static List<Integer> topView(Tree.Node root) {
        TreeMap<Integer, Integer> resultMap = topView(root, 0, new TreeMap<>());

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private static TreeMap<Integer, Integer> topView(
            Tree.Node node,
            int horizontalLine,
            TreeMap<Integer, Integer> result
    ) {
        if (node == null) {
            return result;
        }

        if (result.get(horizontalLine) == null) {
            result.put(horizontalLine, node.value);
        }

        topView(node.left, horizontalLine - 1, result);
        topView(node.right, horizontalLine + 1, result);

        return result;
    }

}
