import sudokusolver.*;
import java.lang.Math.*;
import java.util.*;
public class Main {    
    public static void main(String[] args) {
        Grille sudoku = new Grille();
        DeductionRule dr= new DeductionRule();
        sudoku.afficher();
        sudoku.dr1.rule(sudoku);

        
    }
}

// mtn qu'on a la liste des sous éléments faudra virer 000 et garder ceux de taille n-1 uniquement