package sudokusolver;

public class DR2 extends DeductionRule{

    public DR2() {
        super();
    }

    public void rule(Grille sudoku) {
        for (int i = 0; i < 81; i++) {
            if (Grille.grid[i] == -1){
                for (int numToPlace = 1; numToPlace <= 9; numToPlace++){
                    int counter = isPresent(sudoku, i, numToPlace);
                    if (counter == 1){
                        sudoku.set(numToPlace, i);
                        break;
                    }
                }
            }
        }
        sudoku.afficher();
    }

    public int isPresent(Grille sudoku, int index, int numToPlace){
        int rowStart = 9*(index / 9);
        int colStart = index % 9;
        int boxStart = 27 * (index / 27) + 3 * ((index % 9) / 3);
        int counter = 0;

        for (int ind = 0; ind < 9; ind++){
            if (Grille.grid[rowStart + ind] == numToPlace || Grille.grid[9 * ind + colStart] == numToPlace || Grille.grid[boxStart + ind % 3 + 9 * (ind / 3)] == numToPlace){
                counter++;
            }
        }
        return counter;
    }
}
