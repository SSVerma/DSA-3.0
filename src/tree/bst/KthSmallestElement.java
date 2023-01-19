package tree.bst;

import tree.Tree;
import tree.traversal.InOrder;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElement {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{5, 3, 7, 1, 4, 6, 8, null, 2});
        System.out.println(InOrder.traverse(root));

        int result = findKthSmallestWithSideEffect(root, 3);
        System.out.println(result);
    }

    private static int findKthSmallest(Tree.Node root, int k) {
        List<Integer> result = findKthSmallest(root, new ArrayList<>());
        return result.get(k - 1);
    }

    private static List<Integer> findKthSmallest(Tree.Node root, List<Integer> result) {
        if (root == null) {
            return result;
        }

        findKthSmallest(root.left, result);

        result.add(root.value);

        return findKthSmallest(root.right, result);
    }

    private static int count = 0;

    // O(1) space
    private static int findKthSmallestWithSideEffect(Tree.Node root, int k) {
        if (root == null) {
            return -1;
        }

        int result = findKthSmallestWithSideEffect(root.left, k);

        if (result != -1) {
            return result;
        }

        if (++count == k) {
            return root.value;
        }

        return findKthSmallestWithSideEffect(root.right, k);
    }
}
