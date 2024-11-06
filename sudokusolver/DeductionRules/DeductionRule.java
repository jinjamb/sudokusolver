package sudokusolver.DeductionRules;

import sudokusolver.Solver.GridSingleton;

public abstract class DeductionRule {

    public DeductionRule(){}
    public void rule(GridSingleton grille){}
   
    public void printarray(int[] A){ // a bouger dans une toolbox
        System.out.print("[");
        for (int i = 0; i < A.length; i++) {
            if (A[i]==-1) {System.out.print("_");}
            if (A[i]==0){System.out.print(" ");}
            else if (A[i]!=-1) {System.out.print(A[i]);}
            if (i< A.length-1){System.out.print(", ");}
            }
            System.out.print("]\n");
    }
    
    public boolean full(int[] Grille){
        for (int i : Grille) {
            if (i<1){return false;}
        }
        return true;
    }

    public int[] colomne(int x){
        int debcol = x%9; //Cela nous donne le premier élément de la "colomne" du sudoku puisque ce sont des multiples de 9 (ex: 19%9=1 donc sa colomne commence à l'élément 1)
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) { col[i]= i*9+debcol; }
        return col;
    }
    public int[] ligne(int x){
        int debligne = x-x%9; //Cela nous donne le premier élément de la "ligne" du sudoku puisque ce sont des multiples de 9 (ex: 19%9=1 donc sa colomne commence à l'élément 1)
        int[] ligne = new int[9];
        for (int i = 0; i < 9; i++) { ligne[i]= i+debligne; }
        return ligne;
    }
    public int[] cube(int x){
        int debcube = (Math.round(x/27))*27+(x%9)-(x%3);   // Cela nous donne le premier élément du carré contenant notre élément du sudoku puisque ce sont des multiples de 3 sur une ligne d'un multiple de 27 (ex: (19%27)+(18%9)%3 donc son cube commence à l'élément 0)
        int[] cube = new int[9];        // (x%9) retiens le décalage horizontal de x et -x%3 l'aligne sur le coin le plus proche puisque le coin est multiple de 3
        for (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) {
            cube[i*3+j] = debcube+j+i*9; }}
        return cube;
    }
    static int[][] sous_ens(int[] choix){ // pour avoir tous les sous-ensembles de notre tableau de choix
        int val=0; // pour affecter une valeur au tableau valeurs[]
        for (int i = 0; i < 3; i++) { if (choix[i]!=-1) { choix[val]=choix[i]; val++;} }
        int taille = choix.length;
        int[][] resultat = new int[taille+1][9];// tableau contenant les sous-ensembles de taille n-1 et n
        int[] subset; int nb_choix; // subset regroupe les sous-ensembles de choix[] et index est l'indice des valeurs affectées
        int set=0; // indice dans resultat
        for (int i = 0; i < (1 << taille); i++) {
            nb_choix=0; subset = new int[9];
            for (int j = 0; j < 9; j++) {
                if ((i & (1 << j)) !=0 ) { subset[j]=choix[j]; nb_choix++; }
                else { subset[j]=0; }
            }
            if (nb_choix==taille || nb_choix==taille-1) { resultat[set]=subset; set++;}
        }
        return resultat;
    }

}