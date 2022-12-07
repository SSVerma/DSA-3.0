package graph.grid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NearestNeighbourDistance {
    public static void main(String[] args) {
        int[][] input = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 1}
        };

        int[][] result = minDistance(input);
        System.out.println(Arrays.deepToString(result));
    }

    /*
       {
        {0, 0, 0},
        {0, 1, 0},
        {1, 0, 1}
       }

       find distance of the nearest 1 for all 0s.

       result:

       {
        {2, 1, 2},
        {1, 0, 1},
        {0, 1, 0}
       }

     */
    private static int[][] minDistance(
            int[][] input
    ) {
        int rowCount = input.length;
        int colCount = input[0].length;

        boolean[][] visited = new boolean[rowCount][colCount];
        int[][] result = new int[rowCount][colCount];

        Queue<Node> q = new LinkedList<>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 1) {
                    q.add(new Node(r, c, 0));
                    visited[r][c] = true;
                }
            }
        }

        int[] rowDelta = {0, 1, 0, -1};
        int[] colDelta = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            Node node = q.remove();
            result[node.row][node.col] = node.distance;

            for (int index = 0; index < rowDelta.length; index++) {
                int nr = node.row + rowDelta[index];
                int nc = node.col + colDelta[index];

                if (nr < 0 || nc < 0 || nr == rowCount || nc == colCount) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, node.distance + 1));
            }
        }

        return result;
    }


    record Node(int row, int col, int distance) {

    }

}
