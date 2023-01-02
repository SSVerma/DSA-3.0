package tree.view;

import tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BottomView {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});
//        Tree.Node root = Tree.create(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        List<Integer> result = bottomView(root);
        System.out.println(result);
    }

    private static List<Integer> bottomView(Tree.Node root) {
        TreeMap<Integer, Integer> resultMap = bottomView(root, 0, new TreeMap<>());

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private static TreeMap<Integer, Integer> bottomView(
            Tree.Node node,
            int horizontalLine,
            TreeMap<Integer, Integer> result
    ) {
        if (node == null) {
            return result;
        }

        result.put(horizontalLine, node.value);

        bottomView(node.left, horizontalLine - 1, result);
        bottomView(node.right, horizontalLine + 1, result);

        return result;
    }

}
