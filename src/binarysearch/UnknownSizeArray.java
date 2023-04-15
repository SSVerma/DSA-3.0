package binarysearch;

public class UnknownSizeArray {
    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[]{-1, 0, 3, 5, 9, 12});
        int result = search(reader, 5);
        System.out.println(result);
    }

    private static int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 1;

        while (reader.get(end) < target && reader.get(end) != Integer.MAX_VALUE) {
            start = end;
            end = end * 2;
        }

        // apply binary search from 'start' to 'end'

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (reader.get(mid) == target) {
                return mid;
            }

            if (reader.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static class ArrayReader {
        private int[] arr;

        ArrayReader(int[] input) {
            this.arr = input;
        }

        public int get(int index) {
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            }
            return arr[index];
        }
    }

}
