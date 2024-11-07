package sudokusolver.DeductionRules;

import sudokusolver.Solver.GridSingleton;

public class DeductionRuleContext {
    private DeductionRuleStrategy strategy;

    public void setStrategy(DeductionRuleStrategy strategy) {
        this.strategy = strategy;
    }

    public void processRule(GridSingleton sudoku) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        strategy.rule(sudoku);
    }
}
