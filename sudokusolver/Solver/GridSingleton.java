package sudokusolver.Solver;

import sudokusolver.DeductionRules.DR1;
import sudokusolver.DeductionRules.DR2;
import sudokusolver.DeductionRules.DR3;

import java.io.FileNotFoundException;

public final class GridSingleton {
    private static GridSingleton INSTANCE;
    public static int[] grid = new int[81];
    public DR1 dr1 = new DR1();
    public DR2 dr2 = new DR2();
    public DR3 dr3 = new DR3();
    public Choix[] choix = new Choix[81];

    private GridSingleton() throws FileNotFoundException {
        GridSingleton.grid = Parser.parser("sudokusolver/GridFiles/test0.txt");
        for (int i = 0; i < grid.length; i++) {
            this.choix[i] = new Choix(grid, i);
        }
    }

    public static GridSingleton getInstance() throws FileNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new GridSingleton();
        }
        return INSTANCE;
    }

    public void set(int val, int indice){
        GridSingleton.grid[indice]=val;
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

    public void afficher(){
        for (int index = 0; index < 81; index++) {
            if (index%9==0) {  System.out.print("\n");
                if (index%27 < 9) { System.out.print("   ---------   ---------   --------- \n"); }}
            if (index%3==0 ) { System.out.print(" | ");}
            if (GridSingleton.grid[index]!=(-1)) { System.out.print(" "+GridSingleton.grid[index]+" "); }
            else System.out.print(" . ");
            if(index%9==8)System.out.print(" | ");
        }
        System.out.print("\n   ---------   ---------   --------- \n");
    }
}