package graph.grid;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        int[][] input = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int result = minTimeToRotten(input);
        System.out.println(result);
    }

    /*
        {
          {2, 1, 1},
          {1, 1, 0},
          {0, 1, 1}
        }

          0: empty cell
          1: fresh orange
          2: rotten orange

     */
    private static int minTimeToRotten(int[][] input) {
        Queue<Node> q = new LinkedList<>();

        int rowCount = input.length;
        int colCount = input[0].length;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 2) {
                    q.add(new Node(r, c, 0));
                }
            }
        }

        int[] rowDelta = {0, 1, 0, -1};
        int[] colDelta = {1, 0, -1, 0};

        int result = 0;

        while (!q.isEmpty()) {
            Node node = q.remove();
            result = Math.max(result, node.time);

            for (int deltaIndex = 0; deltaIndex < rowDelta.length; deltaIndex++) {
                int nr = node.row + rowDelta[deltaIndex];
                int nc = node.col + colDelta[deltaIndex];

                if (nr < 0 || nc < 0 || nr == rowCount || nc == colCount) {
                    continue;
                }

                if (input[nr][nc] != 1) {
                    continue;
                }

                input[nr][nc] = 2;
                q.add(new Node(nr, nc, node.time + 1));
            }
        }

        return result;
    }

    record Node(int row, int col, int time) {
    }
}
