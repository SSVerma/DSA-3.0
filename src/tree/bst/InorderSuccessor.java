package tree.bst;

import tree.Tree;

public class InorderSuccessor {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{5, 2, 7, 1, 4, 6, 9, null, null, 3, null, null, null, 8, 10});
        Tree.Node result = inorderSuccessor(root, 3, null);
        System.out.println(result == null ? null : result.value);
    }

    // Value just greater than the given target
    private static Tree.Node inorderSuccessor(Tree.Node root, int target, Tree.Node result) {
        if (root == null) {
            return result;
        }

        if (root.value > target) {
            result = root; // potential ans
            return inorderSuccessor(root.left, target, result);
        }

        return inorderSuccessor(root.right, target, result);
    }
}
