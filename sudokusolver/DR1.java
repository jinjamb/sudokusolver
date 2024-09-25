package sudokusolver;
public class DR1 extends DeductionRule {
    public DR1() {
        super();
    }

    public void rule(Grille sudoku){ // si une case n'a qu'un seul choix possible, alors on lui affecte ce choix et on relance la boucle
        int i = 0; //indice qui parcours la grille
        while (i<81) {
            if (sudoku.choix[i].nb_choix==1 && sudoku.grid[i]==-1 ) {
                sudoku.set(sudoku.choix[i].liste[8], i); // 8 est le dernier élément de la liste donc le seul différent de -1
                i=0;
            continue;}
        i++;
        }
    sudoku.afficher(); }
}