package slidingwindow.fixed;

public class MaxSumSubArray {
    public static void main(String[] args) {
        int[] input = {1, 2, -1, 4, 5, 2, 4, 10, -6, 8};
        int result = findMaxSumSubArray(input, 3);
        System.out.println(result);
    }

    private static int findMaxSumSubArray(int[] input, int k) {
        int max;
        int sum = 0;

        // build initial window
        for (int i = 0; i < k; i++) {
            sum = sum + input[i];
        }

        // operation
        max = sum;

        // slide the window
        int start = 0;

        for (int i = k; i < input.length; i++) {
            sum = sum - input[start++] + input[i];
            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }

    private static int findMaxSumSubArray2(int[] input, int k) {
        int left = 0;
        int right = 0;

        int sum = 0;
        int max = 0;

        while (right < input.length) {
            if (right <= k) {
                sum = sum + input[right];
                right++;
                continue;
            }

            max = sum;
            sum = sum - input[left] + input[right];

            if (sum > max) {
                max = sum;
            }

            left++;
            right++;
        }

        return max;
    }
}
