import java.util.Scanner;

/* ================= LOWER TRIANGULAR MATRIX ================= */
class LowerTriangular {
    int n;
    int[] oneD;
    int[][] jagged;

    LowerTriangular(int n) {
        this.n = n;
        oneD = new int[n * (n + 1) / 2];
        jagged = new int[n][];
        for (int i = 0; i < n; i++)
            jagged[i] = new int[i + 1];
    }

    void read(Scanner sc) {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int val = sc.nextInt();
                oneD[idx++] = val;
                jagged[i][j] = val;
            }
        }
    }

    int get(int i, int j) {
        return (i >= j) ? jagged[i][j] : 0;
    }

    void print() {
        System.out.println("Lower Triangular Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(get(i, j) + " ");
            System.out.println();
        }
    }

    /* Determinant */
    int determinant() {
        int det = 1;
        for (int i = 0; i < n; i++)
            det *= jagged[i][i];
        return det;
    }

    /* Addition (LTM + LTM) */
    LowerTriangular add(LowerTriangular B) {
        LowerTriangular C = new LowerTriangular(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                C.jagged[i][j] = this.jagged[i][j] + B.jagged[i][j];
        return C;
    }

    /* Multiplication (LTM × LTM → LTM) */
    LowerTriangular multiply(LowerTriangular B) {
        LowerTriangular C = new LowerTriangular(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = j; k <= i; k++)
                    C.jagged[i][j] += this.get(i, k) * B.get(k, j);
            }
        }
        return C;
    }
}

/* ================= UPPER TRIANGULAR MATRIX ================= */
class UpperTriangular {
    int n;
    int[] oneD;
    int[][] jagged;

    UpperTriangular(int n) {
        this.n = n;
        oneD = new int[n * (n + 1) / 2];
        jagged = new int[n][];
        for (int i = 0; i < n; i++)
            jagged[i] = new int[n - i];
    }

    void read(Scanner sc) {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int val = sc.nextInt();
                oneD[idx++] = val;
                jagged[i][j - i] = val;
            }
        }
    }

    int get(int i, int j) {
        return (i <= j) ? jagged[i][j - i] : 0;
    }

    void print() {
        System.out.println("Upper Triangular Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(get(i, j) + " ");
            System.out.println();
        }
    }

    /* Determinant */
    int determinant() {
        int det = 1;
        for (int i = 0; i < n; i++)
            det *= jagged[i][0];
        return det;
    }

    /* Addition (UTM + UTM) */
    UpperTriangular add(UpperTriangular B) {
        UpperTriangular C = new UpperTriangular(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < jagged[i].length; j++)
                C.jagged[i][j] = this.jagged[i][j] + B.jagged[i][j];
        return C;
    }

    /* Multiplication (UTM × UTM → UTM) */
    UpperTriangular multiply(UpperTriangular B) {
        UpperTriangular C = new UpperTriangular(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k <= j; k++)
                    C.jagged[i][j - i] += this.get(i, k) * B.get(k, j);
            }
        }
        return C;
    }
}

/* ================= MAIN CLASS ================= */
public class Assignment3 {

    static int[][] multiplyLU(LowerTriangular L, UpperTriangular U) {
        int n = L.n;
        int[][] R = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k <= Math.min(i, j); k++)
                    R[i][j] += L.get(i, k) * U.get(k, j);
        return R;
    }

    static int[][] multiplyUL(UpperTriangular U, LowerTriangular L) {
        int n = U.n;
        int[][] R = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = Math.max(i, j); k < n; k++)
                    R[i][j] += U.get(i, k) * L.get(k, j);
        return R;
    }

    static void printMatrix(int[][] M) {
        for (int[] r : M) {
            for (int x : r)
                System.out.print(x + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter order of matrix: ");
        int n = sc.nextInt();

        LowerTriangular L1 = new LowerTriangular(n);
        LowerTriangular L2 = new LowerTriangular(n);
        UpperTriangular U1 = new UpperTriangular(n);
        UpperTriangular U2 = new UpperTriangular(n);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Read LTM-1");
            System.out.println("2. Read LTM-2");
            System.out.println("3. Read UTM-1");
            System.out.println("4. Read UTM-2");
            System.out.println("5. LTM + LTM");
            System.out.println("6. UTM + UTM");
            System.out.println("7. LTM × LTM");
            System.out.println("8. UTM × UTM");
            System.out.println("9. LTM × UTM");
            System.out.println("10. UTM × LTM");
            System.out.println("11. Determinants");
            System.out.println("12. Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> L1.read(sc);
                case 2 -> L2.read(sc);
                case 3 -> U1.read(sc);
                case 4 -> U2.read(sc);
                case 5 -> L1.add(L2).print();
                case 6 -> U1.add(U2).print();
                case 7 -> L1.multiply(L2).print();
                case 8 -> U1.multiply(U2).print();
                case 9 -> printMatrix(multiplyLU(L1, U1));
                case 10 -> printMatrix(multiplyUL(U1, L1));
                case 11 -> {
                    System.out.println("Det(LTM) = " + L1.determinant());
                    System.out.println("Det(UTM) = " + U1.determinant());
                }
                case 12 -> System.exit(0);
                default -> System.out.println("Invalid Choice");
            }
        }
    }
}
