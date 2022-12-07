package graph.shortestpath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryMaze {
    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        };

        int result = shortestPath(input, 0, 1, 2, 2);
        System.out.println(result);
    }

    private static int shortestPath(
            int[][] input,
            int srcRow,
            int srcCol,
            int destRow,
            int destCol
    ) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(srcRow, srcCol));

        int rowCount = input.length;
        int colCount = input[0].length;

        int[][] distance = new int[rowCount][colCount];
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        distance[srcRow][srcCol] = 0;

        int[] rowDelta = {0, 1, 0, -1};
        int[] colDelta = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            Node node = q.remove();

            for (int direction = 0; direction < rowDelta.length; direction++) {
                int adjacentRow = node.row + rowDelta[direction];
                int adjacentCol = node.col + colDelta[direction];

                if (adjacentRow < 0 || adjacentCol < 0 || adjacentRow == rowCount || adjacentCol == colCount) {
                    continue;
                }

                if (input[adjacentRow][adjacentCol] == 0) {
                    continue;
                }

                int distanceNow = distance[node.row][node.col] + 1;

                if (distanceNow < distance[adjacentRow][adjacentCol]) {
                    distance[adjacentRow][adjacentCol] = distanceNow;
                    q.add(new Node(adjacentRow, adjacentCol));
                }

                if (adjacentRow == destRow && adjacentCol == destCol) {
                    return distanceNow;
                }
            }
        }

        return -1;
    }

    record Node(int row, int col) {

    }

}
