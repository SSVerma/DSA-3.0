package graph.grid;

import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1},
        };

        int[][] result = floodFill(input, 1, 1, 2);
        System.out.println(Arrays.deepToString(result));
    }

    private static int[][] floodFill(
            int[][] input,
            int sr,
            int sc,
            int color
    ) {
        int initialColor = input[sr][sc];
        return dfs(input, sr, sc, initialColor, color, input);
    }

    private static int[][] dfs(
            int[][] input,
            int r,
            int c,
            int initialColor,
            int colorToFill,
            int[][] result
    ) {
        int rowCount = input.length;
        int colCount = input[0].length;

        if (r < 0 || c < 0 || r == rowCount || c == colCount) {
            return result;
        }

        if (result[r][c] == colorToFill || result[r][c] != initialColor) {
            return result;
        }

        result[r][c] = colorToFill;

        dfs(input, r + 1, c, initialColor, colorToFill, result);
        dfs(input, r - 1, c, initialColor, colorToFill, result);
        dfs(input, r, c + 1, initialColor, colorToFill, result);
        dfs(input, r, c - 1, initialColor, colorToFill, result);

        return result;
    }

}
