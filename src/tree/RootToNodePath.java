package tree;

import java.util.ArrayList;
import java.util.List;

public class RootToNodePath {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{2, 1, 3, null, 4, null, 5});

        List<Integer> result = rootToNodePath(root, 5);
        System.out.println(result);
    }

    private static List<Integer> rootToNodePath(Tree.Node root, int target) {
        List<Integer> result = new ArrayList<>();

        rootToNodePath(root, target, result);
        return result;
    }

    private static boolean rootToNodePath(Tree.Node root, int target, List<Integer> result) {
        if (root == null) {
            return false;
        }

        result.add(root.value);

        if (root.value == target) {
            return true;
        }

        boolean onLeft = rootToNodePath(root.left, target, result);
        boolean onRight = rootToNodePath(root.right, target, result);

        if (onLeft || onRight) {
            return true;
        }

        result.remove(result.size() - 1);
        return false;
    }

}
