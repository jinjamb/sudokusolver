package sudokusolver;
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
    public void parcours(int[] zone, Grille sudoku){
        int compteur; int[] sauf=zone;
        for (int i : zone) {
            if (Grille.grid[i]>0){continue;}
            compteur=0; sauf=Arrays.copyOf(zone, 9);
            for (int j = 0; j < zone.length; j++) {
                if (sudoku.choix[zone[j]].nb_choix <= sudoku.choix[i].nb_choix){
                    if (i==zone[j] || Grille.grid[zone[j]]>0) { sauf[j]=-1; continue;}
                    if ( inclus(sudoku.choix[i], sudoku.choix[zone[j]])) {
                        sauf[j]=-1;
                        compteur++; }
                }
            }
            if (compteur+1==sudoku.choix[i].nb_choix && compteur>0) { // si on a assez d'éléments inclus dans A on peut les retirer des autres choix
                for (int j : sauf) {
                    if (j!=-1) { sudoku.retirer_choix(j, sudoku.choix[i].liste);}
                }
            }
        }
    }
    
    public void rule(Grille sudoku){
        for (int i = 0; i < 9; i++) {
            parcours(ligne(i*9), sudoku);
            parcours(colomne(i), sudoku);
            parcours(cube((i*3/9)*27+(i*3)), sudoku);
        }
    }
}
