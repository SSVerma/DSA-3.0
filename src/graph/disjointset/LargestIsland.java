package graph.disjointset;

import java.util.HashSet;
import java.util.Set;

public class LargestIsland {
    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1}
        };

        int result = largestIsland(input);
        System.out.println(result);
    }

    /*
       Largest island by replacing single 0 with 1

       input = {
          {1, 1, 0, 1, 1},
          {1, 1, 0, 1, 1},
          {1, 1, 0, 1, 1},
          {0, 0, 1, 0, 0},
          {0, 0, 1, 1, 1},
          {0, 0, 1, 1, 1}
       }

     */
    private static int largestIsland(int[][] input) {
        int rowCount = input.length;
        int colCount = input[0].length;

        DisjointSet disjointSet = new DisjointSet(rowCount * colCount);

        int[] rowDeltas = {0, 1, 0, -1};
        int[] colDeltas = {1, 0, -1, 0};

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 0) {
                    continue;
                }

                int currentNode = r * colCount + c;

                for (int d = 0; d < rowDeltas.length; d++) {
                    int adjRow = r + rowDeltas[d];
                    int adjCol = c + colDeltas[d];

                    if (adjRow < 0 || adjCol < 0 || adjRow == rowCount || adjCol == colCount) {
                        continue;
                    }

                    if (input[adjRow][adjCol] == 0) {
                        continue;
                    }

                    int adjNode = adjRow * colCount + adjCol;
                    disjointSet.union(currentNode, adjNode);
                }
            }
        }

        int result = 0;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 1) {
                    continue;
                }

                Set<Integer> set = new HashSet<>();

                for (int d = 0; d < rowDeltas.length; d++) {
                    int adjRow = r + rowDeltas[d];
                    int adjCol = c + colDeltas[d];

                    if (adjRow < 0 || adjCol < 0 || adjRow == rowCount || adjCol == colCount) {
                        continue;
                    }

                    if (input[adjRow][adjCol] == 0) {
                        continue;
                    }

                    int adjNode = adjRow * colCount + adjCol;

                    int parent = disjointSet.parentOf(adjNode);
                    set.add(parent);
                }

                int sizeNow = 1;

                for (int node : set) {
                    int size = disjointSet.sizeOf(node);
                    sizeNow = sizeNow + size;
                }

                if (sizeNow > result) {
                    result = sizeNow;
                }
            }
        }

        return result;
    }

}
