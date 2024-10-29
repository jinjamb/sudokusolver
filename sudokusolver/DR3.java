package sudokusolver;

public class DR3 extends DeductionRule {
    public DR3(){super();}
    
    public boolean inclus(int[] A, int[] candidat){ // on check si candidat est un sousensemble de A
        int[][]choix= sous_ens(A);
        for (int i = 0; i < choix.length; i++) {
            if (candidat.equals(choix[i])) { return true; }
        } return false;
    }
    public void parcours(int[] zone, Grille sudoku){
        int compteur; Choix A; int[] sauf;
        for (int i : zone) {
            compteur=0; A=sudoku.choix[i]; sauf=new int[9];
            for (int j = 0; j < zone.length; j++) {
                if (inclus(A.liste, sudoku.choix[j].liste)) { compteur++; sauf[j]=j; } 
            }
            if (compteur==A.nb_choix) { // si on a assez d'éléments inclus dans A on peut les retirer des autres choix
                for (int j : zone) {
                    for (int k : sauf) { if (i!=k) { sudoku.retirer_choix(j, A.liste);} }
                }
            }
        }
    }
    
    public void rule(Grille sudoku){
        for (int i = 0; i < 81; i++) {
            parcours(ligne(i), sudoku);
            parcours(colomne(i), sudoku);
            parcours(cube(i), sudoku);
        }      
    }
}
