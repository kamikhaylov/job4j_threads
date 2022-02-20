package concurrent.pools;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return rowSum + ", " + colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = new Sums(getRowSum(matrix, i), getColSum(matrix, i));
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            int finalI = i;
            int row = CompletableFuture.supplyAsync(() -> getRowSum(matrix, finalI)).get();
            int col = CompletableFuture.supplyAsync(() -> getColSum(matrix, finalI)).get();
            sums[i] = new Sums(row, col);
        }
        return sums;
    }

    private static int getRowSum(int[][] matrix, int index) {
        int rsl = 0;
        for (int i = 0; i < matrix.length; i++) {
            rsl += matrix[index][i];
        }
        return rsl;
    }

    private static int getColSum(int[][] matrix, int index) {
        int rsl = 0;
        for (int i = 0; i < matrix.length; i++) {
            rsl += matrix[i][index];
        }
        return rsl;
    }
}