package binarysearch;

import java.util.ArrayList;
import java.util.List;

public class KClosestElement {
    public static void main(String[] args) {
        int[] input = {1, 2, 13, 14, 15};
        List<Integer> result = findKClosest(input, 4, 14);
        System.out.println(result);
    }

    /*
        [1,2,3,4,5], k = 4, x = 3
        result = [1, 2, 3, 4]

        [1,2,3,4,5], k = 4, x = -1
        result = [1, 2, 3, 4]

     */
    private static List<Integer> findKClosest(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length - 1;

        while (end - start + 1 > k) {
            if (Math.abs(arr[start] - x) <= Math.abs(arr[end] - x)) {
                end--;
            } else {
                start++;
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
