package tree.height;

import tree.Tree;

import java.util.concurrent.atomic.AtomicInteger;

public class MaxPathSum {
    public static void main(String[] args) {
        Tree.Node root = Tree.create(new Integer[]{-10, 9, 20, null, null, 15, 7});

        int result = maxPathSum(root);
        System.out.println(result);
    }

    private static int maxPathSum(Tree.Node root) {
        AtomicInteger sum = new AtomicInteger();
        maxPathSum(root, sum);
        return sum.get();
    }

    private static int maxPathSum(Tree.Node node, AtomicInteger sum) {
        if (node == null) {
            return 0;
        }

        int leftSum = maxPathSum(node.left, sum);
        if (leftSum < 0) {
            leftSum = 0;
        }

        int rightSum = maxPathSum(node.right, sum);
        if (rightSum < 0) {
            rightSum = 0;
        }

        int sumNow = node.value + leftSum + rightSum;

        if (sumNow > sum.get()) {
            sum.set(sumNow);
        }

        return node.value + Math.max(leftSum, rightSum);
    }

}
