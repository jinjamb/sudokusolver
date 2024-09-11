package sudokusolver;
public class DeductionRule {
    public int[] rule(){return new int[]{0};}

    public int[] ligne(Grille grille, int x){
        int debligne = x-(x%9); //Cela nous donne le premier élément de la "ligne" du sudoku puisque ce sont des multiples de 9 (ex: 19-(19%9)=18 donc sa ligne commence à l'élément 18)
        int[] ligne = new int[9];
        for (int i = 0; i < 9; i++) { ligne[i]= grille.grid[i+debligne];}
        return tri(ligne);
    }

    public int[] colomne(Grille grille, int x){        
        int debcol = Math.round(x/9)-1; //Cela nous donne le premier élément de la "colomne" du sudoku puisque ce sont des multiples de 9 (ex: 19%9=1 donc sa colomne commence à l'élément 1)
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) { col[i]= grille.grid[i*9+debcol];}
        return tri(col);
    }

    public int[] cube(Grille grille, int x){
        int debcube = Math.round(x/27)+(x%9-1)%3;   // Cela nous donne le premier élément du carré contenant notre élément du sudoku puisque ce sont des multiples de 3 sur une ligne d'un multiple de 27 (ex: (19%27)+(18%9)%3 donc son cube commence à l'élément 0)
        int[] cube = new int[9];        // (x%9) retiens le décalage horizontal de x et %3 l'aligne sur le coin le plus proche puisque le coin est multiple de 3
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[i*3+j] = grille.grid[debcube+j+i*9];
            }
        }
        return tri(cube);
    }

    public int[] tri(int[] tab){
        int[]res={-1,-1,-1,-1,-1,-1,-1,-1,-1};
            for (int i = 1; i <= 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tab[j]==i) {
                        res[i-1]=tab[j];
                    }
                }
            }
        return res;
    }
    public void printarray(int[] A){
    System.out.print("[");
    for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
            if (i< A.length-1){System.out.print(", ");}
        }
        System.out.print("]\n");
    }

    public int[] fusion(int[] A, int[] B){
        
        int[] resultat= A;
        for (int index = 0; index < A.length; index++) {
            if (B[index]!=-1) {
                resultat[index]=B[index];
            }
        }
        return resultat;
    }

    public int[] choix(Grille grille, int x){
        int[] choix = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        if (x!=-1){
            int[] ligne= ligne(grille, x), col= colomne(grille, x), cube = cube(grille, x);
            choix = fusion(fusion(col, ligne), cube);
        }
        printarray(choix);
        return choix;
    }
}
