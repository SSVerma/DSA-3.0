package dp.subset;

public class PartitionEqualSubsetSum {

    // https://leetcode.com/problems/partition-equal-subset-sum/
    public static void main(String[] args) {
        int[] numbers = {1, 5, 11, 5};
//        int[] numbers = {1, 2, 3, 5};

        int sum = 0;

        for (int number : numbers) {
            sum = sum + number;
        }

        // if sum is odd, can't evenly divide into 2 sets of equal sum
        // as numbers are integers.
        if (sum % 2 != 0) {
            System.out.println(false);
            return;
        }

        int target = sum / 2;

        Boolean[][] cache = new Boolean[numbers.length][target + 1];

        boolean result = canPartition(numbers, 0, target, cache);
        System.out.println(result);
    }

    /*
        [1, 5, 11, 5]

        ans => true
        sum = 11, [1, 5, 5] & [11]

        ---------------------
        s1 == s2

        s1 + s2 = s
        => s1 + s1 = s
        => s1 = s / 2

        find a subset that has sum = s / 2

     */
    private static boolean canPartition(
            int[] numbers,
            int index,
            int target,
            Boolean[][] cache
    ) {
        if (target == 0) {
            return true;
        }

        if (target < 0) {
            return false;
        }

        if (index == numbers.length) {
            return false;
        }

        if (cache[index][target] != null) {
            return cache[index][target];
        }

        boolean byPicking = canPartition(numbers, index + 1, target - numbers[index], cache);
        cache[index][target] = byPicking;

        if (byPicking) {
            return true;
        }

        boolean byIgnoring = canPartition(numbers, index + 1, target, cache);
        cache[index][target] = byIgnoring;

        return byIgnoring;
    }
}
