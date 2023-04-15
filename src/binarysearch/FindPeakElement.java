package binarysearch;

public class FindPeakElement {
    public static void main(String[] args) {
        int[] input = {1, 2, 1, 3, 5, 6, 4};
        int result = findPeakElementIndex(input);
        System.out.println(result);
    }

    /*
        [1,2,3,1], 3 (index 2)

        [1,2,1,3,5,6,4], 2 (index 1) or 6 (index 5)

        ...........
         a
       b /\c /\
        /  \/

        a -> got the element
        b -> ascending order - reduce search space by going upwards (move start boundary)
        c -> descending order - reduce search space by going upwards (move end boundary)
     */
    private static int findPeakElementIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid == start || mid == end) {
                if (arr[start] > arr[end]) {
                    return start;
                }
                return end;
            }

            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            if (arr[mid] > arr[mid - 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
