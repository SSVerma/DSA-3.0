package tree;

public class IdenticalTree {
    public static void main(String[] args) {
        Tree.Node root1 = Tree.create(new Integer[]{1, 2, 3, null, null, 4, 5});
        Tree.Node root2 = Tree.create(new Integer[]{1, 2, 3, null, null, 4, 5});
//        Tree.Node root2 = Tree.create(new Integer[]{1, 2, 3, null, null, 6, 5});
//        Tree.Node root2 = Tree.create(new Integer[]{1, 2, 3, null, 4, 5});

        boolean result = areTreeIdentical(root1, root2);
        System.out.println(result);
    }

    private static boolean areTreeIdentical(Tree.Node node1, Tree.Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.value != node2.value) {
            return false;
        }

        return areTreeIdentical(node1.left, node2.left)
                && areTreeIdentical(node1.right, node2.right);
    }

}
