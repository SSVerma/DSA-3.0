package dp.subset;

public class CountSubsetOfTargetSum {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5};
        int target = 6;

//        Integer[][] cache = new Integer[numbers.length][target + 1];
//        int result = countSubsets(numbers, 0, target, cache);
        int result = countSubsets(numbers, target);

        System.out.println(result);
    }

    /*
        [1, 2, 3, 5]
        target = 5

        ans = 2 => [2, 3], [5]
     */
    private static int countSubsets(
            int[] numbers,
            int index,
            int target,
            Integer[][] cache
    ) {
        if (target == 0) {
            return 1; // empty set always exists
        }

        if (target < 0) {
            return 0;
        }

        if (index == numbers.length) {
            return 0;
        }

        if (cache[index][target] != null) {
            return cache[index][target];
        }

        int byPicking = countSubsets(numbers, index + 1, target - numbers[index], cache);
        int byIgnoring = countSubsets(numbers, index + 1, target, cache);

        int count = byPicking + byIgnoring;
        cache[index][target] = count;

        return count;
    }

    private static int countSubsets(
            int[] numbers,
            int target
    ) {
        int[][] partitions = new int[numbers.length][target + 1];

        for (int index = 0; index < numbers.length; index++) {
            partitions[index][0] = 1;
        }

        for (int t = 0; t <= target; t++) {
            // if target is zero, it always exists.
            // or if only one number is given, then target exists if number is
            // equal to the target itself.
            partitions[0][t] = (t == 0 || t == numbers[0]) ? 1 : 0;
        }

        for (int index = 1; index < numbers.length; index++) {
            for (int t = 1; t <= target; t++) {
                int byPicking = t - numbers[index] < 0
                        ? 0 : partitions[index - 1][t - numbers[index]];
                int byIgnoring = partitions[index - 1][t];

                partitions[index][t] = byPicking + byIgnoring;
            }
        }

        return partitions[numbers.length - 1][target];
    }
}
