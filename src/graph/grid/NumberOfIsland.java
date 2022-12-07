package graph.grid;

public class NumberOfIsland {
    public static void main(String[] args) {
        int[][] input = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1}
        };

        int result = countIsland(input);
        System.out.println(result);
    }

    /*
       {
         {0, 1, 1, 0},
         {0, 1, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 0, 0},
         {1, 1, 0, 1}
       }

     */
    private static int countIsland(int[][] input) {
        int rowCount = input.length;
        int colCount = input[0].length;

        boolean[][] visited = new boolean[rowCount][colCount];

        int result = 0;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (!visited[r][c] && input[r][c] == 1) {
                    dfs(input, r, c, visited);
                    result++;
                }
            }
        }

        return result;
    }

    /*
         c - 1, c + 1
         r - 1, r + 1
     */
    private static void dfs(int[][] input, int r, int c, boolean[][] visited) {
        int rowCount = input.length;
        int colCount = input[0].length;

        visited[r][c] = true;

        for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
            for (int colDelta = -1; colDelta <= 1; colDelta++) {
                int nr = r + rowDelta;
                int nc = c + colDelta;

                if (nr < 0 || nc < 0 || nr == rowCount || nc == colCount) {
                    continue;
                }

                if (!visited[nr][nc] && input[nr][nc] == 1) {
                    dfs(input, nr, nc, visited);
                }
            }
        }
    }
}
