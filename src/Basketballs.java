import java.util.Random;

public class Basketballs {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int basketball_WIDTH = 17;
    static final int basketball_HEIGHT = 14;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[basketball_HEIGHT][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }

        while (true) {
            // At each column on the top row, 1% chance to add a new image
            for (int x = 0; x < WIDTH - basketball_WIDTH; x++) {
                if (rand.nextDouble() < 0.0005) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(2); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;
            
        }
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        char[][] img;
        if (rand.nextBoolean()) {
            img = cat();
        } else {
            img = getCarrot();
        }
        for (int iy = 0; iy < basketball_HEIGHT; iy++) {
            for (int ix = 0; ix < basketball_WIDTH; ix++) {
                nextRows[iy][x + ix] = img[iy][ix];
            }
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }

    // rename your function here
    static char[][] cat() {
        char[][] img = new char[basketball_HEIGHT][basketball_WIDTH];
        // fill with empty space
        for (int y = 0; y < basketball_HEIGHT; y++) {
            for (int x = 0; x < basketball_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][9] = '_';
        img[0][10] = '_';
        img[0][11] = '-';
        img[0][12] = '-';
        img[0][13] = '_';
        img[1][7] = '/';
        img[1][8] = 't';
        img[1][11] = '\\';
        img[1][14] = 'b';
        img[1][15] = '-';
        img[2][3] = 'M';
        img[2][4] = 'V';
        img[2][7] = '/';
        img[2][9] = '\\';
        img[2][12] = '|';
        img[2][15] = 't';
        img[3][5] = '_';
        img[3][6] = '(';
        img[3][10] = '<';
        img[3][11] = '_';
        img[3][12] = '|';
        img[3][13] = '-';
        img[3][14] = '/';
        img[3][15] = '.';
        img[3][16] = '\\';
        img[4][4] = '/';
        img[4][6] = '|';
        img[4][7] = '-';
        img[4][8] = '_';
        img[4][12] = '|';
        img[4][15] = '.';
        img[4][16] = '|';
        img[5][3] = '/';
        img[5][4] = '[';
        img[5][6] = '(';
        img[5][9] = '-';
        img[5][10] = '-';
        img[5][11] = '_';
        img[5][12] = '+';
        img[5][13] = '-';
        img[5][14] = '-';
        img[5][15] = '=';
        img[5][16] = '|';
        img[6][3] = '/';
        img[6][5] = 'b';
        img[6][7] = '\\';
        img[6][11] = '_';
        img[6][12] = '/';
        img[6][13] = '_';
        img[6][14] = '.';
        img[6][15] = '.';
        img[6][16] = '/';
        img[7][2] = '/';
        img[7][5] = '|';
        img[7][6] = 'L';
        img[7][8] = '-';
        img[7][9] = '_';
        img[7][10] = '"';
        img[7][11] = '.';
        img[7][12] = '|';
        img[7][13] = '.';
        img[7][14] = '_';
        img[7][15] = '/';
        img[7][16] = '/';
        img[8][2] = '.';
        img[8][3] = '/';
        img[8][5] = '/';
        img[8][7] = 'k';
        img[8][10] = '-';
        img[8][11] = '-';
        img[8][12] = '=';
        img[8][13] = '≠';
        img[9][1] = '/';
        img[9][2] = '\"';
        img[9][4] = 'b';
        img[9][8] = '/';
        img[9][9] = '+';
        img[9][13] = '/';
        img[9][14] = 'l';
        img[10][1] = '/';
        img[10][3] = '/';
        img[10][5] = '\\';
        img[10][10] = '=';
        img[10][11] = '=';
        img[10][12] = '-';
        img[10][13] = '/';
        img[11][2] = '\\';
        img[11][6] = '-';
        img[11][7] = '\\';
        img[11][10] = '/';
        img[11][11] = '/';
        img[11][12] = '/';
        img[12][3] = '[';
        img[12][5] = '/';
        img[12][7] = 'J';
        img[12][8] = '/';
        img[12][9] = '/';
        img[13][4] = '\\';
        img[13][5] = 'a';
        img[13][7] = '/';
        



        return img;
    }

    static char[][] getCarrot() {
        char[][] img = new char[basketball_HEIGHT][basketball_WIDTH];
        // fill with empty space
        for (int y = 0; y < basketball_HEIGHT; y++) {
            for (int x = 0; x < basketball_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters

        

        return img;
    }

    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}    