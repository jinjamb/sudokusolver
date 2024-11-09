package sudokusolver.DeductionRules;

import sudokusolver.Solver.Grid;

import java.util.Scanner;

public class Editeur {
    Grid grid;
    Grid.Memento memento;
    public Scanner sc = new Scanner(System.in);

    public Editeur(Grid grid) {
        this.grid=grid;
        this.memento=grid.getMemento();
    }

    public void input() throws IllegalArgumentException{
        grid.afficher();
        System.out.print("Notre solver n'est pas en mesure de résoudre cette grille, veuillez entrer une valeur entre 1 et 9: "); int val = sc.nextInt();
        System.out.print("Veuillez ensuite entrer le numéro de la colomne concernée (de 1 à 9): "); int x = sc.nextInt();
        System.out.print("Veuiller ensuite entrer le numéro de la ligne concernée (de 1 à 9): "); int y = sc.nextInt();
        grid.set(val , (x-1)+(y-1)*9);
    }

    public void reset(){
        this.grid.getOldGrid(this.memento);
    }

}