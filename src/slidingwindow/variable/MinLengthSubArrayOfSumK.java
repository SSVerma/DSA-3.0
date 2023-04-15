package slidingwindow.variable;

public class MinLengthSubArrayOfSumK {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6};
        int result = findMinLengthSubArrayOfSumAtLeastK(input, 7);
        System.out.println(result);
    }

    private static int findMinLengthSubArrayOfSumAtLeastK(int[] input, int k) {
        int sum = 0;
        int min = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;

        while (right < input.length) {
            sum = sum + input[right++];

            if (sum < k) {
                continue;
            }

            while (left < right && sum >= k) {
                sum = sum - input[left++];

                int windowSize = right - left + 1;

                if (windowSize < min) {
                    min = windowSize;
                }
            }
        }

        return min;
    }
}
