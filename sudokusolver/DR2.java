package sudokusolver;

public class DR2 extends DeductionRule{

    public DR2() {
        super();
    }

    public void rule(Grille sudoku) {
        for (int i = 0; i < 81; i++) {
            if (sudoku.grid[i] == -1){
                for (int numToPlace = 1; numToPlace <= 9; numToPlace++){
                    if (!isPresent(sudoku, i, numToPlace)){
                        sudoku.set(numToPlace, i);
                        break;
                    }
                }
            }
        }
        sudoku.afficher();
    }

    public boolean isPresent(Grille sudoku, int index, int numToPlace){
        int rowStart = 9*(index / 9);
        int colStart = index % 9;
        int boxStart = 27 * (index / 27) + 3 * ((index % 9) / 3);

        for (int ind = 0; ind < 9; ind++){
            if (sudoku.grid[rowStart + ind] == numToPlace || sudoku.grid[9 * ind + colStart] == numToPlace || sudoku.grid[boxStart + ind % 3 + 9 * (ind / 3)] == numToPlace){
                return true;
            }
        }
        return false;
    }
}
