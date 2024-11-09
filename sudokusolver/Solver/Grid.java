package sudokusolver.Solver;

import sudokusolver.DeductionRules.DR1;
import sudokusolver.DeductionRules.DR2;
import sudokusolver.DeductionRules.DR3;

import java.io.FileNotFoundException;
import java.util.Arrays;

public final class Grid {
    private static Grid INSTANCE;
    public int[] grid = new int[81];
    public DR1 dr1 = new DR1();
    public DR2 dr2 = new DR2();
    public DR3 dr3 = new DR3();
    public Choix[] choix = new Choix[81];
    public int difficulte=1;

    private Grid(String filePath) throws FileNotFoundException {
        this.grid = Parser.parser(filePath);
        for (int i = 0; i < this.grid.length; i++) {
            this.choix[i] = new Choix(grid, i);
        }
    }

    public static Grid getInstance(String filePath) throws FileNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new Grid(filePath);
        }
        return INSTANCE;
    }

    public void set(int val, int indice){
        this.grid[indice]=val;
        for (int i : dr1.ligne(indice)) { this.choix[i].retirer(val); }
        for (int i : dr1.colomne(indice)) { this.choix[i].retirer(val); }
        for (int i : dr1.cube(indice)) { this.choix[i].retirer(val);}
    }

    public void retirer_choix(int indice, int[] choix){
        if (indice!=-1) {
            for (int i = 0; i < choix.length; i++) {
                if (choix[i]>0) {
                    if (this.choix[indice].liste[i] > 0 ) {
                        this.choix[indice].liste[choix[i]-1]=0;
                    }
                }
            }
        }
    }

    public boolean full(){
        for (int i : this.grid) {
            if (i<1){return false;}
        }
        return true;
    }

    public boolean doublon(int[] zone, int i){
        if (this.grid[i]<=0){ return false;}
        for (int val : zone) {
            if (val!=i && this.grid[val]==this.grid[i]) {
                System.out.println(val+" "+i); return true; }
        }
        return false;
    }

    public boolean erreur(){
        for (int i=0; i < choix.length; i++) {
            if (this.grid[i]>9){
                return true;
            }
            if (Arrays.equals((this.choix[i].liste), new int[]{0,0,0,0,0,0,0,0,0}) && this.grid[i]<1 ){
                return true;
            }
            if (this.grid[i]>=1){ // si grid[i] contient une valeur et qu'une autre contient aussi cette meme valeur, alors on a une erreur
                return (doublon(dr1.ligne(i), i) || doublon(dr1.colomne(i), i) || doublon(dr1.cube(i), i) );
            }
        }
        return false;
    }

    public void afficher(){
        for (int index = 0; index < 81; index++) {
            if (index%9==0) {  System.out.print("\n");
                if (index%27 < 9) { System.out.print("   ---------   ---------   --------- \n"); }}
            if (index%3==0 ) { System.out.print(" | ");}
            if (this.grid[index]!=(-1)) { System.out.print(" "+this.grid[index]+" "); }
            else System.out.print(" . ");
            if(index%9==8)System.out.print(" | ");
        }
        System.out.print("\n   ---------   ---------   --------- \n");
    }

    public Memento getMemento() { return new Memento(this); }

    public void getOldGrid(Memento backup){
        this.choix=backup.getChoix();
        this.grid=backup.getGrille();
        this.afficher();
    }

    public static class Memento {
        private final Choix[] choix;
        public final int[] grille;

        private Memento(Grid sudoku) {
            this.choix = sudoku.choix.clone();
            this.grille = sudoku.grid.clone();
        }

        private Choix[] getChoix(){ return choix; }
        private int[] getGrille(){ return grille; }
    }
}