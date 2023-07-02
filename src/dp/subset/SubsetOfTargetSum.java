package dp.subset;

public class SubsetOfTargetSum {
    // https://www.codingninjas.com/studio/problems/subset-sum-equal-to-k_1550954
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int target = 4;

//        Boolean[][] cache = new Boolean[a.length][target + 1];
//        boolean result = doesSubsetExistMemoization(a, 0, target, cache);
        boolean result = doesSubsetExistBottomUpSpaceOptimized(a, target);
        System.out.println(result);
    }

    /*
        a = [1, 2, 3, 4]
        target = 4

        ans => true, {1, 3}, {4} exists

     */
    private static boolean doesSubsetExist(
            int[] a,
            int index,
            int target
    ) {
        if (target == 0) {
            // target 0 (empty set) always exists even if no elements are given
            return true;
        }

        if (target < 0) {
            return false;
        }

        if (index == a.length) {
            return false;
        }

        boolean byPicking = doesSubsetExist(a, index + 1, target - a[index]);

        if (byPicking) {
            // if any one of the choice is returning true, immediately return true as it does not
            // matter 1 subset exists or multiple. At least one subset should exist.
            return true;
        }

        boolean byIgnoring = doesSubsetExist(a, index + 1, target);
        return byIgnoring;
    }

    private static boolean doesSubsetExistMemoization(
            int[] a,
            int index,
            int target,
            Boolean[][] cache
    ) {
        if (target == 0) {
            // target 0 (empty set) always exists even if no elements are given
            return true;
        }

        if (target < 0) {
            return false;
        }

        if (index == a.length) {
            return false;
        }

        if (cache[index][target] != null) {
            return cache[index][target];
        }

        boolean byPicking = doesSubsetExistMemoization(a, index + 1, target - a[index], cache);
        cache[index][target] = byPicking;

        if (byPicking) {
            // if any one of the choice is returning true, immediately return true as it does not
            // matter 1 subset exists or multiple. At least one subset should exist.
            return true;
        }

        boolean byIgnoring = doesSubsetExistMemoization(a, index + 1, target, cache);
        cache[index][target] = byIgnoring;

        return byIgnoring;
    }

    private static boolean doesSubsetExistBottomUp(int[] a, int target) {
        boolean[][] existence = new boolean[a.length][target + 1];

        for (int r = 0; r < a.length; r++) {
            existence[r][0] = true;
        }

        for (int t = 0; t <= target; t++) {
            // if only 1 element is given, then subset exists if target is zero or
            // target is equal to the given element
            existence[0][t] = t == 0 || t == a[0];
        }

        for (int i = 1; i < a.length; i++) {
            for (int t = 0; t <= target; t++) {
                boolean byPicking = t - a[i] >= 0 && existence[i - 1][t - a[i]];
                boolean byIgnoring = existence[i - 1][t];
                existence[i][t] = byPicking || byIgnoring;
            }
        }

        return existence[a.length - 1][target];
    }

    private static boolean doesSubsetExistBottomUpSpaceOptimized(int[] a, int target) {
        boolean[] existence = new boolean[target + 1];

        for (int t = 0; t <= target; t++) {
            // if only 1 element is given, then subset exists if target is zero or
            // target is equal to the given element
            existence[t] = t == 0 || t == a[0];
        }

        for (int i = 1; i < a.length; i++) {
            boolean[] current = new boolean[target + 1];

            for (int t = 0; t <= target; t++) {
                boolean byPicking = t - a[i] >= 0 && existence[t - a[i]];
                boolean byIgnoring = existence[t];
                current[t] = byPicking || byIgnoring;
            }

            existence = current;
        }

        return existence[target];
    }
}
