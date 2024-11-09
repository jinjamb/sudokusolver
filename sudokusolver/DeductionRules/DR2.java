package sudokusolver.DeductionRules;

import sudokusolver.Solver.Grid;

public class DR2 extends DeductionRule implements DeductionRuleStrategy{

    public DR2() {
        super();
    }

    public int rule(Grid sudoku) {
        int agis=0;
        for (int num = 1; num <= 9; num++) {
            for (int i = 0; i < 81; i++) {
                if (sudoku.getGrid()[i] == -1) {
                    int rowIndex = findUniqueInRow(sudoku, i, num);
                    int colIndex = findUniqueInCol(sudoku, i, num);
                    int subgridIndex = findUniqueInBox(sudoku, i, num);
                    if (rowIndex != -1) {
                        sudoku.set(num, rowIndex); agis=1;
                        this.notify.modif(num,i);
                    } else if (colIndex != -1) {
                        sudoku.set(num, colIndex); agis=1;
                        this.notify.modif(num,i);
                    } else if (subgridIndex != -1) {
                        sudoku.set(num, subgridIndex); agis=1;
                        this.notify.modif(num,i);
                    }
                }
            }
        }
        return agis;
    }

    private int findUniqueInRow(Grid sudoku, int index, int num) {
        int stRow = index - (index % 9);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = stRow; i < stRow + 9; i++) {
            if (sudoku.getGrid()[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private int findUniqueInCol(Grid sudoku, int index, int num) {
        int stCol = index % 9;
        int count = 0;
        int uniqueIndex = -1;

        for (int i = stCol; i < 81; i += 9) {
            if (sudoku.getGrid()[i] == -1 && canPlaceNumber(sudoku, i, num)) {
                count++;
                uniqueIndex = i;
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private int findUniqueInBox(Grid sudoku, int index, int num) {
        int stBox = (index / 27) * 27 + (index % 9) - (index % 3);
        int count = 0;
        int uniqueIndex = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentIndex = stBox + j + i * 9;
                if (sudoku.getGrid()[currentIndex] == -1 && canPlaceNumber(sudoku, currentIndex, num)) {
                    count++;
                    uniqueIndex = currentIndex;
                }
            }
        }
        return count == 1 ? uniqueIndex : -1;
    }

    private boolean canPlaceNumber(Grid sudoku, int index, int num) {
        return !isInRow(sudoku, index, num) && !isInCol(sudoku, index, num) && !isInBox(sudoku, index, num);
    }

    private boolean isInRow(Grid sudoku, int index, int num) {
        int stRow = index - (index % 9);
        for (int i = stRow; i < stRow + 9; i++) {
            if (sudoku.getGrid()[i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(Grid sudoku, int index, int num) {
        int stCol = index % 9;
        for (int i = stCol; i < 81; i += 9) {
            if (sudoku.getGrid()[i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(Grid sudoku, int index, int num) {
        int stBox = (index / 27) * 27 + (index % 9) - (index % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku.getGrid()[stBox + j + i * 9] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
