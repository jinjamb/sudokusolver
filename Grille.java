package sudokusolver;
public class Grille {
    int[] grid;
    static DeductionRule DRule = new DeductionRule();
    Grille(){
        this.grid = new int[] {
            8, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, 3, 6, -1, -1, -1, -1, -1,
            -1, 7, -1, -1, 9, -1, 2, -1, -1,
            -1, 5, -1, -1, -1, 7, -1, -1, -1,
            -1, -1, -1, -1, 4, 5, 7, -1, -1,
            -1, -1, -1, 1, -1, -1, -1, 3, -1,
            -1, -1, 1, -1, -1, -1, -1, 6, 8,
            -1, -1, 8, 5, -1, -1, -1, 1, -1,
            -1, 9, -1, -1, -1, -1, 4, -1, -1};
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