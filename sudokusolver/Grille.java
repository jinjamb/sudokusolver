package sudokusolver;

import java.io.FileNotFoundException;

public class Grille {
    public static int[] grid = new int[81];
    public DR1 dr1 = new DR1();
    public DR2 dr2 = new DR2();
    public DR3 dr3 = new DR3();
    public Choix[] choix = new Choix[81];

    public Grille() throws FileNotFoundException { // initialisation de la grille des remplissage des tableaux de choix
        Grille.grid = Parser.parser("./sudokusolver/test0.txt");
        for (int i = 0; i < grid.length; i++) {
            this.choix[i] = new Choix(grid, i); 
            dr1.printarray(dr1.cube(i));       
        }
    }

    public void set(int val, int indice){
        Grille.grid[indice]=val;
        for (int i : dr1.ligne(indice)) { this.choix[i].retirer(val); }
        for (int i : dr1.colomne(indice)) { this.choix[i].retirer(val); }
        for (int i : dr1.cube(indice)) { System.out.println(indice+" "+i);this.choix[i].retirer(val);}
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
            if (Grille.grid[index]!=(-1)) { System.out.print(" "+Grille.grid[index]+" "); }
            else System.out.print(" . ");
            if(index%9==8)System.out.print(" | ");
        }
        System.out.print("\n   ---------   ---------   --------- \n");
    }
}