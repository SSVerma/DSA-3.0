package graph.disjointset;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland2 {
    public static void main(String[] args) {
        List<Node> operations = new ArrayList<>();
        operations.add(new Node(0, 0));
        operations.add(new Node(0, 0));
        operations.add(new Node(1, 1));
        operations.add(new Node(1, 0));
        operations.add(new Node(0, 1));
        operations.add(new Node(0, 3));
        operations.add(new Node(1, 3));
        operations.add(new Node(0, 4));
        operations.add(new Node(3, 2));
        operations.add(new Node(2, 2));
        operations.add(new Node(1, 2));
        operations.add(new Node(0, 2));

        List<Integer> result = islandAfterEachOperation(4, 5, operations);
        System.out.println(result);
    }

    private static List<Integer> islandAfterEachOperation(
            int rowCount,
            int colCount,
            List<Node> operations
    ) {
        boolean[][] visited = new boolean[rowCount][colCount];

        int[] rowDeltas = {0, 1, 0, -1};
        int[] colDeltas = {1, 0, -1, 0};

        int islandNow = 0;

        DisjointSet disjointSet = new DisjointSet(rowCount * colCount);

        List<Integer> result = new ArrayList<>();

        for (Node operation : operations) {
            if (visited[operation.row][operation.col]) {
                result.add(islandNow);
                continue;
            }

            visited[operation.row][operation.col] = true;
            islandNow++;

            for (int d = 0; d < rowDeltas.length; d++) {
                int adjRow = operation.row + rowDeltas[d];
                int adjCol = operation.col + colDeltas[d];

                if (adjRow < 0 || adjCol < 0 || adjRow == rowCount || adjCol == colCount) {
                    continue;
                }

                int currentNode = operation.row * colCount + operation.col;
                int adjNode = adjRow * colCount + adjCol;

                if (!visited[adjRow][adjCol]) {
                    continue;
                }

                boolean isInSameComponent = disjointSet.find(currentNode) == disjointSet.find(adjNode);

                if (!isInSameComponent) {
                    islandNow--;
                    disjointSet.union(currentNode, adjNode);
                }
            }

            result.add(islandNow);
        }

        return result;
    }

    record Node(int row, int col) {
    }

}
