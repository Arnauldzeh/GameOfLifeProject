public class GameOfLife {

    static final int SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        int[][] grid = {
            {0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

        System.out.println("Jeu de la Vie - Grille initiale");
        printGrid(grid);

        for (int gen = 1; gen <= 5; gen++) {
            grid = nextGeneration(grid);
            System.out.println("\nGénération " + gen + ":");
            printGrid(grid);
            Thread.sleep(1000);
        }
    }

    static int[][] nextGeneration(int[][] grid) {
        int[][] next = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int aliveNeighbours = countNeighbours(grid, i, j);

                if (grid[i][j] == 1) {
                    next[i][j] = (aliveNeighbours == 2 || aliveNeighbours == 3) ? 1 : 0;
                } else {
                    next[i][j] = (aliveNeighbours == 3) ? 1 : 0;
                }
            }
        }
        return next;
    }

    static int countNeighbours(int[][] grid, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nx = x + i, ny = y + j;
                if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                    count += grid[nx][ny];
                }
            }
        }
        return count;
    }

    static void printGrid(int[][] grid) {
        String line = "+";
        for (int i = 0; i < SIZE; i++) line += "---+";

        for (int i = 0; i < SIZE; i++) {
            System.out.println(line);
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] == 1 ? " O " : "   ");
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(line);
    }
}