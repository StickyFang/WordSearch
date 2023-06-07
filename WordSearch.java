import java.util.*;

public class WordSearch {
    //Create 2 grids, one real WordSearch and the other will be used as a clone to make a solved key
    private static char[][] wordSearchGrid;
    private static char[][] wordSearchGrid2;
    private static Random random = new Random();

    public static void main(String[] args) {
        //main method used as a menu
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1: Generate");
            System.out.println("2: Show");
            System.out.println("3: Solve");
            System.out.println("4: End");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    generateWordSearch(scanner);
                    break;
                case 2:
                    showWordSearch();
                    break;
                case 3:
                    showSolvedWordSearch();
                    break;
                case 4:
                    System.out.println("Ending Program, Good-bye");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void generateWordSearch(Scanner scanner) {
        System.out.println("Enter words separated by spaces and use CAPS: ");
        String[] words = scanner.nextLine().split(" ");
        wordSearchGrid = new char[30][30];  // Create an empty grid

        // Try to place each word in the grid
        //will try to place up to 100 times, will randomly pick direction
        for (String word : words) {
            boolean placed = false;
            int attempts = 0;

            while (!placed && attempts < 100) {
                int row = random.nextInt(wordSearchGrid.length);
                int col = random.nextInt(wordSearchGrid[0].length);
                int direction = random.nextInt(3);  // 0 for horizontal, 1 for vertical, 2 for diagonal
            
                if (direction == 0 && col + word.length() <= wordSearchGrid[0].length) {  // if word fits horizontally
                    for (int i = 0; i < word.length(); i++) {
                        wordSearchGrid[row][col + i] = word.charAt(i);
                    }
                    placed = true;
                } else if (direction == 1 && row + word.length() <= wordSearchGrid.length) {  // if word fits vertically
                    for (int i = 0; i < word.length(); i++) {
                        wordSearchGrid[row + i][col] = word.charAt(i);
                    }
                    placed = true;
                } else if (direction == 2 && row + word.length() <= wordSearchGrid.length && col + word.length() <= wordSearchGrid[0].length) {  // if word fits diagonally
                    for (int i = 0; i < word.length(); i++) {
                        wordSearchGrid[row + i][col + i] = word.charAt(i);
                    }
                    placed = true;
                }
            
                attempts++;
            }
            
            if (!placed) {
                System.out.println("Could not place word: " + word);
            }
        }
        wordSearchGrid2 = new char[30][30];
        //generates clone of grid before filling rest of null values
            for (int i = 0; i < wordSearchGrid.length; i++) {
            wordSearchGrid2[i] = wordSearchGrid[i].clone();
        }


        // fill all null with random letters
        for (int i = 0; i < wordSearchGrid.length; i++) {
            for (int j = 0; j < wordSearchGrid[i].length; j++) {
                if (wordSearchGrid[i][j] == '\0') {  // If cell is null
                    wordSearchGrid[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
        //Fills second grid with 'X'
        for (int i = 0; i < wordSearchGrid2.length; i++) {
            for (int j = 0; j < wordSearchGrid2[i].length; j++) {
                if (wordSearchGrid2[i][j] == '\0') {  // If cell is null
                    wordSearchGrid2[i][j] = (char) ('X');
                }
            }
        }
        
    }

    private static void showWordSearch() {
        if (wordSearchGrid == null) {
            System.out.println("No word search generated yet.");
            return;
        }

        for (int i = 0; i < wordSearchGrid.length; i++) {
            for (int j = 0; j < wordSearchGrid[i].length; j++) {
                System.out.print(wordSearchGrid[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void showSolvedWordSearch() {
        if (wordSearchGrid2 == null) {
            System.out.println("No word search generated yet.");
            return;
        }

        for (int i = 0; i < wordSearchGrid2.length; i++) {
            for (int j = 0; j < wordSearchGrid2[i].length; j++) {
                System.out.print(wordSearchGrid2[i][j] + " ");
            }
            System.out.println();
        }
    }

}
