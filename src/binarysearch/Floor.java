package binarysearch;

public class Floor {
    public static void main(String[] args) {
        int[] input = {1, 3, 5, 6};
        int result = findFloor(input, 7);
        System.out.println(result);
    }

    /*
        [1,3,5,6], target = 5
        result = 2 (index)

        [1,3,5,6], target = 2
        result = 0 (index)

        [1,3,5,6], target = 7
        result = 3 (index)

     */
    private static int findFloor(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }
}
