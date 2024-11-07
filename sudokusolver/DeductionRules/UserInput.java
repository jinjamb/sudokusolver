package sudokusolver.DeductionRules;

import sudokusolver.Solver.GridSingleton;

import java.util.Scanner;

public class UserInput extends DeductionRule{
    public UserInput() {super();}

    @Override
    public int rule(GridSingleton grille) {
        Scanner sc = new Scanner(System.in);
        int correct=0;
        System.out.print("Notre solver n'est pas en mesure de résoudre cette grille, veuillez entrer une valeur entre 1 et 9: ");
        int val = sc.nextInt();
        System.out.print("Veuillez ensuite entrer le numéro de la ligne concernée (de 1 à 9: ");
        int x = sc.nextInt();
        System.out.print("Veuiller ensuite entrer le numéro de la colomne concernée (de 1 à 9: ");
        int y = sc.nextInt();
        System.out.print("Vous voulez entrer "+val+" à ");
        sc.close();
        return super.rule(grille);
    }
}
