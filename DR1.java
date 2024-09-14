package sudokusolver;
public class DR1 extends DeductionRule {
    public DR1(int[] grille) {
        super();
    }

    public boolean changement = false;

    public void rule(Grille sudoku){
        int i = 0;
        while (i<81) {
            if (sudoku.choix[i].nb_choix==1) {
                sudoku.set(sudoku.choix[i].liste[8], i);
                sudoku.afficher();
                i=0;
                continue;
            }
            
            System.out.print(i);
            printarray(sudoku.choix[i].liste);
            i++;
        }
        System.out.println("impossible pour la dr1");
    }
}