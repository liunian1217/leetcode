package leetcodejava.top100likedquestions;

import org.junit.Assert;
import org.junit.Test;

/**
 * This is the solution of No.221 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/maximal-square/
 * <p>
 * The description of problem is as follow:
 * ==========================================================================================================
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
public class MaximalSquare221 {

    @Test
    public void maximalSquareTest() {
        char[][] chs =
                {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        int result = maximalSquare(chs);
        System.out.println(result);
        Assert.assertEquals(result, 4);

    }

    /**
     * 获取最大正方形
     *
     * @param matrix 二维数组
     * @return 正方形边个数
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int maxSquare = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int temp = getMaxSquare(matrix, i, j);
                maxSquare = Math.max(maxSquare, temp);
            }
        }
        return maxSquare;
    }

    /**
     * 获取最大正方形
     *
     * @param matrix   二维数组
     * @param rowIndex 行号
     * @param colIndex 列号
     * @return 最大正方形
     */
    int getMaxSquare(char[][] matrix, int rowIndex, int colIndex) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (matrix[rowIndex][colIndex] == '0') {
            return 0;
        }
        int length = 1;
        int maxSize = Math.min(row - rowIndex, col - colIndex);
        for (int size = 1; size < maxSize; size++) {
            int newCol = colIndex + size;
            int newRow = rowIndex + size;
            for (int i = rowIndex; i <= rowIndex + size; i++) {
                if (matrix[i][newCol] == '0') {
                    return length * length;
                }
            }
            for (int j = colIndex; j <= colIndex + size; j++) {
                if (matrix[newRow][j] == '0') {
                    return length * length;
                }
            }
            length++;
        }
        return length * length;
    }

    /**
     * 动态规划获取最大正方形
     *
     * @param matrix 二维数组
     * @return 最大正方形
     */
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide * maxSide;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
