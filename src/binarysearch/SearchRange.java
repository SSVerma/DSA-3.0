package binarysearch;

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        int[] input = {5, 7, 7, 8, 8, 8, 10};
        int[] result = findRange(input, 8);
        System.out.println(Arrays.toString(result));
    }

    /*
        {5, 7, 7, 8, 8, 8, 10}, target = 8
        result = [3, 5] (indices)

        {5, 7, 7, 8, 8, 8, 10}, target = 6
        result = [-1, -1] (not found)

     */
    private static int[] findRange(int[] arr, int target) {
        int start = findRangeStartIndex(arr, target);
        int end = findRangeEndIndex(arr, target);
        return new int[]{start, end};
    }

    private static int findRangeStartIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                if (mid == 0) {
                    return mid;
                }
                if (arr[mid] != arr[mid - 1]) {
                    return mid;
                }
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    private static int findRangeEndIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                if (mid == end) {
                    return mid;
                }
                if (arr[mid] != arr[mid + 1]) {
                    return mid;
                }
                start = mid + 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
