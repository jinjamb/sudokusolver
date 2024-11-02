package sudokusolver;

public class DR2 extends DeductionRule{

    public DR2() {
        super();
    }

    public void rule(Grille sudoku) {
        for (int num = 1; num <= 9; num++) {
            for (int i = 0; i < 81; i++) {
                if (sudoku.grid[i] == -1) {
                    int rowIndex = findUniqueInRow(sudoku, i, num);
                    int colIndex = findUniqueInCol(sudoku, i, num);
                    int subgridIndex = findUniqueInBox(sudoku, i, num);

                    if (rowIndex != -1) {
                        sudoku.set(num, rowIndex);
                    } else if (colIndex != -1) {
                        sudoku.set(num, colIndex);
                    } else if (subgridIndex != -1) {
                        sudoku.set(num, subgridIndex);
                    }
                }
            }
        }
        sudoku.afficher();
    }

    public int findUniqueInRow(Grille sudoku, int row, int num){
        int debligne = row - (row % 9);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = debligne; i < debligne + 9; i++) {
            if (sudoku.grid[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }
    
    public int findUniqueInCol(Grille sudoku, int col, int num){
        int debcol = col % 9;
        int count = 0;
        int uniqueIndex = -1;

        for (int i = debcol; i < 81; i += 9) {
            if (sudoku.grid[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }
    
    public int findUniqueInBox(Grille sudoku, int box, int num){
        int debcube = (box / 27) * 27 + (box % 9) - (box % 3);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentIndex = debcube + j + i * 9;
                if (sudoku.grid[currentIndex] == -1 && canPlaceNumber(sudoku, currentIndex, num)) {
                    count++;
                    uniqueIndex = currentIndex;
                }
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    public boolean canPlaceNumber(Grille sudoku, int index, int num){return !isInRow(sudoku, index, num) && !isInCol(sudoku, index, num) && !isInBox(sudoku, index, num);}

    public boolean isInRow(Grille sudoku, int index, int num){
        int rowStart = 9 - (index % 9);
        for (int i = 0; i < 9; i++){
            if (sudoku.grid[rowStart + i] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isInCol(Grille sudoku, int index, int num){
        int colStart = index % 9;
        for (int i = 0; i < 9; i++){
            if (sudoku.grid[9 * i + colStart] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isInBox(Grille sudoku, int index, int num){
        int boxStart = (index / 27) * 27 + (index % 9) - (index % 3);
        for (int i = 0; i < 9; i++){
            if (sudoku.grid[boxStart + i % 3 + 9 * (i / 3)] == num){
                return true;
            }
        }
        return false;
    }
    /* public boolean isPresent(Grille sudoku, int index, int numToPlace){
        int rowStart = 9-(index % 9);
        int colStart = index % 9;
        int boxStart = (index / 27) * 27 + (index % 9) - (index % 3);
        int counter = 0;

        for (int ind = 0; ind < 9; ind++){
            if (sudoku.grid[rowStart + ind] == numToPlace || sudoku.grid[9 * ind + colStart] == numToPlace || sudoku.grid[boxStart + ind % 3 + 9 * (ind / 3)] == numToPlace){
                return true;
            }
        }
        return false;
    } */
}
