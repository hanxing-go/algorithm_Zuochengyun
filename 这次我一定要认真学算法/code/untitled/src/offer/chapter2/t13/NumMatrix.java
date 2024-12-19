package offer.chapter2.t13;

public class NumMatrix {
    private int[][] sums;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        sums = new int[matrix.length + 1][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            int rowSum = 0;
            for (int j = 0 ; j < matrix[0].length; j++) {
                rowSum += matrix[i][j];// 计算当前行的前缀和
                sums[i + 1][j + 1] = sums[i][j + 1] + rowSum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2][col2] + sums[row1 - 1][col1 - 1] - sums[row2][col1 - 1] - sums[row1][col2 - 1];
    }

    public int sumRegion1(int matrix[][], int row1, int col1, int row2, int col2) {
        // 先计算子矩阵的和
        // 1. 先计算每一行的前缀和
        // 差点没有考虑下标可能为 -1的情况,可以整体往下 以及往 右边移
        int[][] sumRegion = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                sumRegion[i + 1][j + 1] = matrix[i][j] + sumRegion[i][j - 1];
            }
        }
        // 2. 再通过每一行的前缀和计算每一个位置的子矩阵和
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                sumRegion[i + 1][j + 1] += sumRegion[i - 1][j - 1];
            }
        }

        // 利用sumRegion来计算子矩阵和
        return sumRegion[row2][col2] + sumRegion[row1 - 1][col1 - 1] - sumRegion[row2][col1 - 1] - sumRegion[row1 - 1][col2];

    }
}
