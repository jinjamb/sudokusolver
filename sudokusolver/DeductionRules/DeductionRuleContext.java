package sudokusolver.DeductionRules;

import sudokusolver.Solver.Grid;

public class DeductionRuleContext {
    private DeductionRuleStrategy strategy;

    public void setStrategy(DeductionRuleStrategy strategy) {
        this.strategy = strategy;
    }

    public int processRule(Grid sudoku) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        return strategy.rule(sudoku);
    }
}
