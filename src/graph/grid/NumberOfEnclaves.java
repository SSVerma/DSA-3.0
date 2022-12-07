package graph.grid;

public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] input = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        int result = countEnclave(input);
        System.out.println(result);
    }

    /*
        {
          {0, 0, 0, 0},
          {1, 0, 1, 0},
          {0, 1, 1, 0},
          {0, 0, 0, 0}
        }

        0 = land
        1 = water

        result = 3 (Number of ones enclosed by 0)

     */
    private static int countEnclave(int[][] input) {
        int rowCount = input.length;
        int colCount = input[0].length;

        boolean[][] visited = new boolean[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            // first column
            if (input[r][0] == 1) {
                dfs(r, 0, input, visited);
            }

            // last column
            if (input[r][colCount - 1] == 1) {
                dfs(r, colCount - 1, input, visited);
            }
        }

        for (int c = 0; c < colCount; c++) {
            // first row
            if (input[0][c] == 1) {
                dfs(0, c, input, visited);
            }

            // last row
            if (input[rowCount - 1][c] == 1) {
                dfs(rowCount - 1, c, input, visited);
            }
        }

        int count = 0;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 1 && !visited[r][c]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(int r, int c, int[][] input, boolean[][] visited) {
        if (r < 0 || c < 0 || r == input.length || c == input[0].length) {
            return;
        }

        if (input[r][c] == 0) {
            return;
        }

        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;

        dfs(r, c + 1, input, visited);
        dfs(r + 1, c, input, visited);
        dfs(r, c - 1, input, visited);
        dfs(r - 1, c, input, visited);
    }

}
