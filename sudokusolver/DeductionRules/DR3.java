package sudokusolver.DeductionRules;
import sudokusolver.Solver.Choix;
import sudokusolver.Solver.Grid;

import java.util.Arrays;

public class DR3 extends DeductionRule implements DeductionRuleStrategy {
    public DR3(){super();}
    
    public boolean inclus(Choix A, Choix candidat){ // on check si candidat est un sousensemble de A
        int[][]choix= sous_ens(A.getListe());
        for (int[] sousens : choix) {
            if (Arrays.equals(sousens, candidat.getListe())) { return true; }
        }
        return false;
    }

    public int nblibre(int[] zone, Grid sudoku){
        int compteur=0;
        for (int i : zone) { if (sudoku.getGrid()[i]<1){ compteur++; }}
        return compteur;
    }

    public int parcours(int[] zone, Grid sudoku){
        int compteur; int[] sauf=zone;
        for (int i : zone) {
            if (sudoku.getGrid()[i]>0 || nblibre(zone,sudoku)==sudoku.getChoix()[i].getNb_choix()) { continue; } // si le nombre de cases vaut le nombre de choix de i ou que i est déja rempli, on n'a rien a appliquer pour i.
            compteur=0; sauf=Arrays.copyOf(zone, 9);
            for (int j = 0; j < zone.length; j++) {
                if (sudoku.getChoix()[zone[j]].getNb_choix() <= sudoku.getChoix()[i].getNb_choix()){
                    if (i==zone[j] || sudoku.getGrid()[zone[j]]>0) { sauf[j]=-1; continue;}
                    if ( inclus(sudoku.getChoix()[i], sudoku.getChoix()[zone[j]])) {
                        sauf[j]=-1; compteur++; }
                }
            }
            if (compteur+1==sudoku.getChoix()[i].getNb_choix() && compteur>0) { // si on a assez d'éléments inclus dans A on peut les retirer des autres choix
                for (int j : sauf) {
                    if (j!=-1) {
                        sudoku.retirer_choix(j, sudoku.getChoix()[i].getListe());
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public int rule(Grid sudoku){
        int agis=0;
        for (int i = 0; i < 9; i++) {
            agis = Math.max(Math.max(parcours(colomne(i), sudoku) , parcours(ligne(i*9), sudoku)) , parcours(cube((i*3/9)*27+(i*3)), sudoku));
        }
        return agis;
    }
}
