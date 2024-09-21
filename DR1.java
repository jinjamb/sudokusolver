package sudokusolver;
public class DR1 extends DeductionRule {
    public DR1(int[] grille) {
        super();
    }

    public boolean changement = false;

    public void rule(Grille sudoku){
        int i = 0;
        while (i<81) {
            System.out.print(i+" ");
            if (sudoku.choix[i].nb_choix==1 && sudoku.grid[i]==-1 ) {
                sudoku.set(sudoku.choix[i].liste[8], i);
                System.out.println(Math.round(i/9)+" "+i%9);
                i=0;
                continue;
            }
            i++;
        }
        sudoku.afficher();
        printarray(sudoku.choix[10].liste);
        System.out.println("impossible pour la dr1");
    }
}