package slidingwindow.fixed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FirstNegativeInSubArrayOfSizeK {
    public static void main(String[] args) {
        int[] input = {10, -1, -3, 8, -5, 4, 6, 7};
        int k = 3;

        int[] result = findFirstNegative(input, k);
        System.out.println(Arrays.toString(result));
    }

    /*
        [10, -1, -3, 8, -5, 4, 6, 7], k = 3
        -----------
              --------
                   -------
                     --------
                         -------
                             -------

        [-1, -1, -3, -5, -5, 0] first negative is every sub array of size k otherwise 0

     */
    private static int[] findFirstNegative(int[] input, int k) {
        int left = 0;
        int right = 0;

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[input.length - k + 1];
        int index = 0;

        while (right < input.length) {
            int ws = right - left + 1;

            // build window of size k
            if (ws < k) {
                int element = input[right];
                if (element < 0) {
                    q.add(element);
                }
                right++;
                continue;
            }

            if (q.isEmpty()) {
                result[index++] = 0;
            } else {
                result[index++] = q.peek();
                if (input[left] == q.peek()) {
                    q.remove();
                }
            }

            if (input[right] < 0) {
                q.add(input[right]);
            }

            // maintain window of size k
            left++;
            right++;
        }

        return result;
    }

}
