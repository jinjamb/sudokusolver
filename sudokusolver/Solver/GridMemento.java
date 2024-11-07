package sudokusolver.Solver;

public class GridMemento {
    private final Choix[] choix;
    private final int[] grille;

    private GridMemento(GridSingleton grid) {
        this.choix = grid.choix;
        this.grille = GridSingleton.grid;
    }

    private Choix[] getChoix(){ return choix; }
    private int[] getGrille(){ return grille; }
}
