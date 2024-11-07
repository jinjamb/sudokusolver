import sudokusolver.DeductionRules.DeductionRuleContext;
import sudokusolver.Solver.GridSingleton;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DeductionRuleContext drContext = new DeductionRuleContext();
        GridSingleton sudoku = GridSingleton.getInstance();
        sudoku.afficher();
        drContext.setStrategy(sudoku.dr1);
        drContext.processRule(sudoku);
        drContext.setStrategy(sudoku.dr2);
        drContext.processRule(sudoku);
        drContext.setStrategy(sudoku.dr3);
        drContext.processRule(sudoku);
        //sudoku.dr1.rule(sudoku);
        //sudoku.dr2.rule(sudoku);
        //sudoku.dr3.rule(sudoku);
    }
}