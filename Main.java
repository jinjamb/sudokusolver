import sudokusolver.DeductionRules.DeductionRuleContext;
import sudokusolver.DeductionRules.Editeur;
import sudokusolver.DeductionRules.Notify;
import sudokusolver.Solver.Grid;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DeductionRuleContext drContext = new DeductionRuleContext();
        String filePath = args[0];
        Grid sudoku = Grid.getInstance(filePath);
        Editeur editeur = new Editeur(sudoku);
        Notify notify =new Notify();

        drContext.setStrategy(sudoku.getDr1());
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
                    drContext.setStrategy(sudoku.getDr1());
                }
                else {
                    if (sudoku.getDifficulte() % 3 == 1) {
                        drContext.setStrategy(sudoku.getDr2()); }
                    else if (sudoku.getDifficulte() % 3 == 2) { drContext.setStrategy(sudoku.getDr3()); }
                    else {
                        drContext.setStrategy(sudoku.getDr1());
                        editeur.input();
                    }
                    sudoku.setDifficulte(sudoku.getDifficulte()+1);
                }
            }
            else { break; }
        }
        
        editeur.closeSc();
        sudoku.afficher();
        notify.fin(sudoku.getDifficulte());
    }
}