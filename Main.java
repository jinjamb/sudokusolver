import sudokusolver.*;
public class Main {    
    public static void main(String[] args) {
        Grille sudoku = new Grille();
        sudoku.afficher();
        sudoku.dr3.rule(sudoku);
        sudoku.afficher();
    }
}