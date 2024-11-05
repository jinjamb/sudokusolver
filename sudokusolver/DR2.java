package sudokusolver;

public class DR2 extends DeductionRule{

    public DR2() {
        super();
    }

    public int rule(Grille sudoku) {
        for (int num = 1; num <= 9; num++) {
              for (int i = 0; i < 81; i++) {
                if (Grille.grid[i] == -1) {
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
                    if (full(Grille.grid)) {return 2;}
                }
            }
            
        }
        sudoku.afficher();
        return 0;
    }

    private int findUniqueInRow(Grille sudoku, int index, int num) {
        int stRow = index - (index % 9);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = stRow; i < stRow + 9; i++) {
            if (sudoku.grid[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private int findUniqueInCol(Grille sudoku, int index, int num) {
        int stCol = index % 9;
        int count = 0;
        int uniqueIndex = -1;

        for (int i = stCol; i < 81; i += 9) {
            if (sudoku.grid[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private int findUniqueInBox(Grille sudoku, int index, int num) {
        int stBox = (index / 27) * 27 + (index % 9) - (index % 3);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentIndex = stBox + j + i * 9;
                if (sudoku.grid[currentIndex] == -1 && canPlaceNumber(sudoku, currentIndex, num)) {
                    count++;
                    uniqueIndex = currentIndex;
                }
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private boolean canPlaceNumber(Grille sudoku, int index, int num) {
        return !isInRow(sudoku, index, num) && !isInCol(sudoku, index, num) && !isInBox(sudoku, index, num);
    }

    private boolean isInRow(Grille sudoku, int index, int num) {
        int stRow = index - (index % 9);
        for (int i = stRow; i < stRow + 9; i++) {
            if (sudoku.grid[i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(Grille sudoku, int index, int num) {
        int stCol = index % 9;
        for (int i = stCol; i < 81; i += 9) {
            if (sudoku.grid[i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(Grille sudoku, int index, int num) {
        int stBox = (index / 27) * 27 + (index % 9) - (index % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku.grid[stBox + j + i * 9] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
