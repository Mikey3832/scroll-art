import java.util.Random;

public class oneMillionCats {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int cat_WIDTH = 6;
    static final int cat_HEIGHT = 4;
    static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[cat_HEIGHT][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }

        while (true) {
            // At each column on the top row, 1% chance to add a new image
            for (int x = 0; x < WIDTH - cat_WIDTH; x++) {
                if (rand.nextDouble() < 0.02) {
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
        for (int iy = 0; iy < cat_HEIGHT; iy++) {
            for (int ix = 0; ix < cat_WIDTH; ix++) {
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
        char[][] img = new char[cat_HEIGHT][cat_WIDTH];
        // fill with empty space
        for (int y = 0; y < cat_HEIGHT; y++) {
            for (int x = 0; x < cat_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        img[0][1] = 'm';
        img[0][2] = 'v';
        img[0][5] = 'M';
        img[1][3] = '_';
        img[1][4] = '_';
        img[1][5] = 'w';
        img[2][0] = 'L';
        img[2][1] = '-';
        img[2][2] = '[';
        img[2][3] = '_';
        img[2][4] = '_';
        img[2][5] = '/';
        img[3][2] = 'L';
        img[3][4] = 'L';



        return img;
    }

    static char[][] getCarrot() {
        char[][] img = new char[cat_HEIGHT][cat_WIDTH];
        // fill with empty space
        for (int y = 0; y < cat_HEIGHT; y++) {
            for (int x = 0; x < cat_WIDTH; x++) {
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