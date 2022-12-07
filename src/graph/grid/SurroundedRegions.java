package graph.grid;

import java.util.Arrays;

public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] input = {
                {'x', 'o', 'x', 'o'},
                {'x', 'o', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'o', 'x', 'x'},
                {'x', 'x', 'o', 'o'}
        };

        char[][] result = replaceO(input);
        System.out.println(Arrays.deepToString(result));
    }

    /*
        {
          {'x', 'x', 'x', 'o'},
          {'x', 'o', 'x', 'x'},
          {'x', 'o', 'o', 'x'},
          {'x', 'o', 'x', 'x'},
          {'x', 'x', 'o', 'o'}
        }

        result:

        {
          {'x', 'x', 'x', 'o'},
          {'x', 'x', 'x', 'x'},
          {'x', 'x', 'x', 'x'},
          {'x', 'x', 'x', 'x'},
          {'x', 'x', 'o', 'o'}
        }

        => replace O's NOT starting with boundaries with 'x'
        using DFS / BFS

     */
    private static char[][] replaceO(char[][] input) {
        int rowCount = input.length;
        int colCount = input[0].length;

        boolean[][] visited = new boolean[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            if (input[r][0] == 'o') {
                dfs(r, 0, input, visited);
            }
            if (input[r][colCount - 1] == 'o') {
                dfs(r, colCount - 1, input, visited);
            }
        }

        for (int c = 0; c < colCount; c++) {
            if (input[0][c] == 'o') {
                dfs(0, c, input, visited);
            }
            if (input[rowCount - 1][c] == 'o') {
                dfs(colCount - 1, c, input, visited);
            }
        }

        char[][] result = new char[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (input[r][c] == 'o') {
                    if (visited[r][c]) {
                        result[r][c] = 'o';
                    } else {
                        result[r][c] = 'x';
                    }
                } else {
                    result[r][c] = 'x';
                }
            }
        }

        return result;
    }

    private static void dfs(int r, int c, char[][] input, boolean[][] visited) {
        if (r < 0 || c < 0 || r == input.length || c == input[0].length) {
            return;
        }

        if (input[r][c] == 'x') {
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
