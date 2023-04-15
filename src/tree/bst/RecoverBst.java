package tree.bst;

import tree.Tree;
import tree.traversal.LevelOrder;

public class RecoverBst {
    public static void main(String[] args) {
//        Tree.Node root = Tree.create(new Integer[]{1, 3, null, null, 2});
        Tree.Node root = Tree.create(new Integer[]{3, 1, 4, null, null, 2});
        recoverBst(root);
        System.out.println(LevelOrder.traverse(root));
    }


    /*
       [1, 3, 2, 4] -> [1, 2, 3, 4] (adjacent are in wrong order)
       [3, 2, 1] -> [1, 2, 3] (non adjacent are in wrong order)

     */

    private static void recoverBst(Tree.Node root) {
        NodeData data = new NodeData();
        inorder(root, data);

        if (data.first != null && data.third != null) {
            int temp = data.first.value;
            data.first.value = data.third.value;
            data.third.value = temp;
        } else if (data.first != null && data.second != null) {
            int temp = data.first.value;
            data.first.value = data.second.value;
            data.second.value = temp;
        }
    }

    private static void inorder(Tree.Node root, NodeData data) {
        if (root == null) {
            return;
        }

        inorder(root.left, data);

        if (data.prev != null && root.value < data.prev.value) {
            if (data.first == null) {
                data.first = data.prev;
                data.second = root;
            } else {
                data.third = root;
            }
        }

        data.prev = root;

        inorder(root.right, data);
    }

    public static class NodeData {
        Tree.Node prev;
        Tree.Node first;
        Tree.Node second;
        Tree.Node third;
    }

}
