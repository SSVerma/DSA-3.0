package binarysearch;

public class RotatedSorted {
    public static void main(String[] args) {
        int[] input = {4, 5, 6, 7, 1, 2, 3};
        int result = findTargetIndex(input, 5);
        System.out.println(result);
    }

    /*
        [4, 5, 6, 7, 1, 2, 3]
        max = 7
        maxIndex = 3

        [1, 2, 3, 4, 5, 6, 7]
        max = 7
        maxIndex = 6
     */
    private static int findMaxElementIndex(int[] input, int start, int end) {
        if (start > end) {
            return -1;
        }

        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;

        if (input[mid] > input[mid + 1]) {
            return mid;
        }

        if (input[mid] > input[start]) {
            return findMaxElementIndex(input, mid + 1, end);
        }

        return findMaxElementIndex(input, start, mid - 1);
    }

    /*
        [4, 5, 6, 7, 1, 2, 3]
        target = 6
        result = 2 (index of 6)

        target = 10
        result = -1 (target does not exist)
     */
    private static int findTargetIndex(int[] a, int target) {
        int start = 0;
        int end = a.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == target) {
                return mid;
            }

            if (a[mid] >= a[start]) {
                // start to mid is sorted
                if (target >= a[start] && target < a[mid]) {
                    // target is withing the sorted range
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // mid to end is sorted
                if (target > a[mid] && target < a[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

}

