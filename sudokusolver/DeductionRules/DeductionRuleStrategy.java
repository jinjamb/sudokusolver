package sudokusolver.DeductionRules;

import sudokusolver.Solver.GridSingleton;

public interface DeductionRuleStrategy {
    int rule(GridSingleton grille);
}
