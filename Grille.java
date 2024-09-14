package sudokusolver;

public class Grille {
    public int[] grid;
    public DeductionRule DRule = new DeductionRule();
    public DR1 dr1 = new DR1(grid);
    public Choix[] choix = new Choix[81];
    public Grille(){
        this.grid = new int[] {
            8,-1,-1, -1,-1,-1, -1,-1,-1,
           -1,-1, 3,  6,-1,-1, -1,-1,-1,
           -1, 7,-1, -1, 9,-1,  2,-1,-1,

           -1, 5,-1, -1,-1, 7, -1,-1,-1,
           -1,-1,-1, -1, 4, 5,  7,-1,-1,
           -1,-1,-1,  1,-1,-1, -1, 3,-1,

           -1,-1, 1, -1,-1,-1, -1, 6, 8,
           -1,-1, 8,  5,-1,-1, -1, 1,-1,
           -1, 9,-1, -1,-1,-1,  4,-1,-1};
 
        for (int i = 0; i < grid.length; i++) {
            this.choix[i] = new Choix(grid, i);
        }
    }

    public void set(int val, int indice){
        this.grid[indice]=val;
        this.choix[indice]=new Choix(this.grid, indice);
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