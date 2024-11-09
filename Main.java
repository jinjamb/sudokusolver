import sudokusolver.DeductionRules.DeductionRuleContext;
import sudokusolver.DeductionRules.Editeur;
import sudokusolver.DeductionRules.Notify;
import sudokusolver.Solver.Grid;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DeductionRuleContext drContext = new DeductionRuleContext();
        Grid sudoku = Grid.getInstance("sudokusolver/GridFiles/test.txt");
        Editeur editeur = new Editeur(sudoku);
        Notify notify =new Notify();

        drContext.setStrategy(sudoku.dr1);
        int status;
        while (true){

            if (sudoku.erreur()){
                editeur.reset();
                System.out.println("Il y a eu une erreur dans l'une de vos entrées, l'algorithme va recommencer");
                continue;
            }

            status=drContext.processRule(sudoku);
            if(!sudoku.full()){
                if (status!=0){ // si une DR marche, on retourne à la DR1
                    drContext.setStrategy(sudoku.dr1);
                }
                else {
                    if (sudoku.difficulte % 3 == 1) {
                        drContext.setStrategy(sudoku.dr2); }
                    else if (sudoku.difficulte % 3 == 2) { drContext.setStrategy(sudoku.dr3); }
                    else {
                        drContext.setStrategy(sudoku.dr1);
                        editeur.input();
                    }
                    sudoku.difficulte++;
                }
            }
            else { break; }
        }
        editeur.sc.close();

        sudoku.afficher();
        notify.fin(sudoku.difficulte);
    }
}