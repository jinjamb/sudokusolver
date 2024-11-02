import sudokusolver.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Grille sudoku = new Grille();
        sudoku.afficher();
        sudoku.dr1.rule(sudoku);
        //sudoku.dr2.rule(sudoku);
        //sudoku.dr3.rule(sudoku);
    }
}