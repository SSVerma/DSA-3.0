package tree.bst;

import tree.Tree;
import tree.traversal.LevelOrder;

public class Insertion {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{4, 2, 7, 1, 3});
        insert(root, null, 5);
        System.out.println(LevelOrder.traverse(root));
        insert(root, null, 0);
        System.out.println(LevelOrder.traverse(root));
        insert(root, null, 9);
        System.out.println(LevelOrder.traverse(root));
    }

    private static void insert(Tree.Node root, Tree.Node parent, int target) {
        if (root == null) {
            if (target < parent.value) {
                parent.left = new Tree.Node(target);
            } else {
                parent.right = new Tree.Node(target);
            }
            return;
        }

        if (target < root.value) {
            insert(root.left, root, target);
        } else {
            insert(root.right, root, target);
        }
    }
}
