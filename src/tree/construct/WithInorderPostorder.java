package tree.construct;

import tree.Tree;
import tree.traversal.PostOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WithInorderPostorder {
    public static void main(String[] args) {
        int[] inorder = {40, 20, 50, 10, 60, 30};
        int[] postorder = {40, 50, 20, 60, 30, 10};

        Tree.Node root = construct(inorder, postorder);

        List<Integer> result = PostOrder.traverse(root);
        System.out.println(result);
    }

    /*
       inorder = {40, 20, 50, 10, 60, 30}
       postorder = {40, 50, 20, 60, 30, 10}
     */
    private static Tree.Node construct(
            int[] inorder,
            int[] postorder
    ) {
        Map<Integer, Integer> inorderIndexByValue = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexByValue.put(inorder[i], i);
        }

        return construct(
                inorderIndexByValue,
                postorder,
                0,
                inorder.length - 1,
                0,
                postorder.length - 1
        );
    }

    private static Tree.Node construct(
            Map<Integer, Integer> inorderMap,
            int[] postOrder,
            int inStart,
            int inEnd,
            int postStart,
            int postEnd
    ) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        Tree.Node root = new Tree.Node(postOrder[postEnd]);

        int inorderRoot = inorderMap.get(root.value);
        int inorderLeftCount = inorderRoot - inStart;

        root.left = construct(
                inorderMap,
                postOrder,
                inStart,
                inorderRoot - 1,
                postStart,
                postStart + inorderLeftCount - 1
        );

        root.right = construct(
                inorderMap,
                postOrder,
                inorderRoot + 1,
                inEnd,
                postStart + inorderLeftCount,
                postEnd - 1
        );

        return root;
    }
}
