package sudokusolver.DeductionRules;

import sudokusolver.Solver.Grid;

public class DR1 extends DeductionRule implements DeductionRuleStrategy  {
    public DR1() {
        super();
    }

    public int rule(Grid sudoku){ // si une case n'a qu'un seul choix possible, alors on lui affecte ce choix et on relance la boucle
        int i = 0; //indice qui parcours la grille
        int element=0;
        while (i<81) {
            if (sudoku.getChoix()[i].getNb_choix()==1 && sudoku.getGrid()[i]<1 ) {// 8 est le dernier élément de la liste donc le seul différent de -1
                while (sudoku.getChoix()[i].getListe()[element]==0){element++;}
                sudoku.set(sudoku.getChoix()[i].getListe()[element], i);
                this.notify.modif(element+1,i); element=0;
                i=0;
            continue;}
        i++;
        }
        if (sudoku.full()) {return 1; }
        return 0;
    }
}