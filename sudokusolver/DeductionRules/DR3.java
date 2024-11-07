package sudokusolver.DeductionRules;
import sudokusolver.Solver.Choix;
import sudokusolver.Solver.GridSingleton;

import java.util.Arrays;

public class DR3 extends DeductionRule {
    public DR3(){super();}
    
    public boolean inclus(Choix A, Choix candidat){ // on check si candidat est un sousensemble de A
        int[][]choix= sous_ens(A.liste);
        for (int[] sousens : choix) {
            if (Arrays.equals(sousens, candidat.liste)) { return true; }
        }
        return false;
    }

    public int nblibre(int[] zone, GridSingleton sudoku){
        int compteur=0;
        for (int i : zone) { if (GridSingleton.grid[i]<1){ compteur++; }}
        return compteur;
    }

    public int parcours(int[] zone, GridSingleton sudoku){
        int compteur; int[] sauf=zone;
        for (int i : zone) {
            if (GridSingleton.grid[i]>0 || nblibre(zone,sudoku)==sudoku.choix[i].nb_choix) { continue; } // si le nombre de cases vaut le nombre de choix de i ou que i est déja rempli, on n'a rien a appliquer pour i.
            compteur=0; sauf=Arrays.copyOf(zone, 9);
            for (int j = 0; j < zone.length; j++) {
                if (sudoku.choix[zone[j]].nb_choix <= sudoku.choix[i].nb_choix){
                    if (i==zone[j] || GridSingleton.grid[zone[j]]>0) { sauf[j]=-1; continue;}
                    if ( inclus(sudoku.choix[i], sudoku.choix[zone[j]])) {
                        sauf[j]=-1; compteur++; }
                }
            }
            if (compteur+1==sudoku.choix[i].nb_choix && compteur>0) { // si on a assez d'éléments inclus dans A on peut les retirer des autres choix
                for (int j : sauf) {
                    if (j!=-1) { sudoku.retirer_choix(j, sudoku.choix[i].liste);}
                }
            }
        }
        if (full(sauf)){return 3;}
        return 0;
    }
    
    public int rule(GridSingleton sudoku){
        for (int i = 0; i < 9; i++) {
            parcours(ligne(i*9), sudoku);
            parcours(colomne(i), sudoku);
            parcours(cube((i*3/9)*27+(i*3)), sudoku);
        }
        return 0;
    }
}
