package tree.construct;

import tree.Tree;
import tree.traversal.PreOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WithInorderPreorder {
    public static void main(String[] args) {
        int[] inorder = {40, 20, 50, 10, 60, 30};
        int[] preorder = {10, 20, 40, 50, 30, 60};

        Tree.Node root = construct(inorder, preorder);

        List<Integer> result = PreOrder.traverse(root);
        System.out.println(result);
    }


    /*
      inorder = [40, 20, 50, 10, 60, 30]
      preorder = [10, 20, 40, 50, 30, 60]
     */

    private static Tree.Node construct(int[] inorder, int[] preorder) {
        Map<Integer, Integer> inorderIndexByValue = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexByValue.put(inorder[i], i);
        }

        return construct(
                preorder,
                inorderIndexByValue,
                0,
                inorder.length - 1,
                0,
                preorder.length - 1
        );
    }

    private static Tree.Node construct(
            int[] preorder,
            Map<Integer, Integer> inorderIndexByValue,
            int inorderStartIndex,
            int inorderEndIndex,
            int preOrderStartIndex,
            int preOrderEndIndex
    ) {
        if (inorderStartIndex > inorderEndIndex) {
            return null;
        }

        if (preOrderStartIndex > preOrderEndIndex) {
            return null;
        }

        Tree.Node root = new Tree.Node(preorder[preOrderStartIndex]);

        int inorderRootIndex = inorderIndexByValue.get(root.value);
        int inorderLeftElementCount = inorderRootIndex - inorderStartIndex;
        int preOrderEndIndexNow = preOrderStartIndex + inorderLeftElementCount;

        root.left = construct(
                preorder,
                inorderIndexByValue,
                inorderStartIndex,
                inorderRootIndex - 1,
                preOrderStartIndex + 1,
                preOrderEndIndexNow
        );

        root.right = construct(
                preorder,
                inorderIndexByValue,
                inorderRootIndex + 1,
                inorderEndIndex,
                preOrderEndIndexNow + 1,
                preOrderEndIndex
        );

        return root;
    }

}
