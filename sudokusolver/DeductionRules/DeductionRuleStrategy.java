package sudokusolver.DeductionRules;

import sudokusolver.Solver.Grid;

public interface DeductionRuleStrategy {
    int rule(Grid grille);
}
