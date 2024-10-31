package sudokusolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    public static int[] parser(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));
        int[] grid = new int[81];
        int index = 0;

        while (sc.hasNextLine() && index < 81) {
            String[] values = sc.nextLine().split(",");
            for (String value : values) {
                int val = Integer.parseInt(value);
                if (val < 0 || val > 9) {
                    System.out.println("Valeur incorrecte");
                    return null;
                }
                grid[index++] = val == 0 ? -1 : val;
            }
        }
        sc.close();
        return grid;
    }
}