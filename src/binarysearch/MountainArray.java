package binarysearch;

public class MountainArray {
    public static void main(String[] args) {
        int[] input = {1, 1, 1, 1, 1, 2, 2};
        int result = findPeakElementIndexWithDuplicates(input);
        System.out.println(result);
    }

    /*
        [1, 2, 3, 5, 6, 4, 2, 0]
        result = 4 (index of '6')

        [1, 2, 3, 4]
        result = 3

        [10, 7, 5, 4]
        result = 0

     */
    private static int findPeakElementIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid == 0) {
                return mid;
            }

            if (arr[mid] > arr[mid - 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    /*
        Duplicates
        [1, 1, 1, 1, 1, 2, 2]
        [2, 2, 2, 2, 2, 1, 1]

     */

    private static int findPeakElementIndexWithDuplicates(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid == 0) {
                return mid;
            }

            if (arr[mid] > arr[mid - 1]) {
                start = mid + 1;
            } else if (arr[mid] < arr[mid - 1]) {
                end = mid - 1;
            } else {
                // equal, decide which direction to move
                if (arr[start] >= arr[end]) {
                    // move towards start
                    end = mid - 1;
                } else {
                    // move towards end
                    start = mid + 1;
                }
            }
        }

        return end;
    }
}
