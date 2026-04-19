import java.util.*;

public class SpanningTrees {

    // Function to compute determinant of matrix
    static double determinant(double[][] matrix, int n) {
        double det = 0;

        if (n == 1)
            return matrix[0][0];

        double[][] temp = new double[n][n];
        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(matrix, temp, 0, f, n);
            det += sign * matrix[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    // Function to get cofactor matrix
    static void getCofactor(double[][] mat, double[][] temp,
                            int p, int q, int n) {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];

                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // Function to count spanning trees
    static int countSpanningTrees(int[][] graph, int n) {

        // Step 1: Degree matrix
        int[][] degree = new int[n][n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += graph[i][j];
            }
            degree[i][i] = sum;
        }

        // Step 2: Laplacian matrix L = D - A
        double[][] laplacian = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laplacian[i][j] = degree[i][j] - graph[i][j];
            }
        }

        // Step 3: Create cofactor (remove last row & column)
        double[][] cofactor = new double[n - 1][n - 1];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                cofactor[i][j] = laplacian[i][j];
            }
        }

        // Step 4: Determinant
        return (int)Math.round(determinant(cofactor, n - 1));
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {0, 0, 1, 0}
        };

        int n = graph.length;

        int result = countSpanningTrees(graph, n);
        System.out.println("Number of spanning trees: " + result);
    }
}