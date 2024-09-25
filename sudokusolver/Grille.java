package sudokusolver;

public class Grille {
    public int[] grid;
    public DR1 dr1 = new DR1();
    public Choix[] choix = new Choix[81];
    public Grille(){ // initialisation de la grille des remplissage des tableaux de choix
        this.grid = new int[] {-1,-1,1,-1,-1,3,9,-1,2,-1,4,-1,-1,6,7,8,-1,-1,-1,-1,-1,9,-1,8,-1,-1,7,1,-1,2,-1,-1,-1,-1,-1,-1,5,9,8,6,2,-1,7,-1,-1,7,-1,4,3,-1,-1,-1,9,1,-1,-1,7,-1,-1,9,-1,8,6,9,1,-1,8,-1,6,3,-1,4,6,-1,-1,-1,4,2,1,-1,-1};
        for (int i = 0; i < grid.length; i++) {
            this.choix[i] = new Choix(grid, i);
        }
    }

    public void set(int val, int indice){
        this.grid[indice]=val;
        for (int i = indice %9; i < 81; i+=9) { this.choix[i].retirer(val); } // retrait du choix affecté sur toute la colomne de l'indice
        for (int i = Math.round(indice/9)*9; i < (Math.round(indice/9)+1)*9; i++) { this.choix[i].retirer(val);}
        for (int i = 0; i < 3; i++) { // retrait du choix affecté dans le carré 3*3 de l'indice
            for (int j = 0; j < 3; j++) {
                this.choix[(Math.round(indice/27))*27+(indice%9)-(indice%3)+i*9+j].retirer(val);
            }
        }
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
}