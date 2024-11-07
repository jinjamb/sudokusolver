package sudokusolver.Solver;

import sudokusolver.DeductionRules.DR1;
import sudokusolver.DeductionRules.DeductionRule;

public class Choix {
    public int[] liste;
    public int nb_choix=0;
    public DR1 dominiquerepublicaine= new DR1();
    Choix(int[] grille, int x){ 
        this.liste=new int[]{0,0,0,0,0,0,0,0,0};
        if ( grille[x]<1 ) {
            int[] ligne= this.ligne(grille, x), col= this.colomne(grille, x), cube = this.cube(grille, x);
            this.liste = this.fusion(this.fusion(col, ligne), cube);
        }

        int nb_choix=0;
        for (int i = 0; i < 9; i++) {
            if (this.liste[i]>0){nb_choix++;}
        }
        this.nb_choix=nb_choix;
    }

    public void retirer(int x){ 
        //System.out.println("je retire "+x);
        for (int i = 8; i >= 0; i--) {
            if (this.liste[i]==x) {
                this.liste[i]=0;
                this.nb_choix--;
                break;
            }
        }
    }
    public int[] ligne(int[] grille, int x){
        int debligne = x-(x%9); //Cela nous donne le premier élément de la "ligne" du sudoku puisque ce sont des multiples de 9 (ex: 19-(19%9)=18 donc sa ligne commence à l'élément 18)
        int[] ligne = new int[9];
        for (int i = 0; i < 9; i++) { ligne[i]= grille[i+debligne];}
        return tri(ligne);
    }

    public int[] colomne(int[] grille, int x){
        int debcol = x%9; //Cela nous donne le premier élément de la "colomne" du sudoku puisque ce sont des multiples de 9 (ex: 19%9=1 donc sa colomne commence à l'élément 1)
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) { col[i]= grille[i*9+debcol];   }
        return tri(col);
    }

    public int[] cube(int[] grille, int x){
        int debcube = (x/27)*27+(x%9)-(x%3);// Cela nous donne le premier élément du carré contenant notre élément du sudoku puisque ce sont des multiples de 3 sur une ligne d'un multiple de 27 (ex: (19%27)+(18%9)%3 donc son cube commence à l'élément 0)
        int[] cube = new int[9];            // (x%9) retiens le décalage horizontal de x et -x%3 l'aligne sur le coin le plus proche puisque le coin est multiple de 3
        System.out.print(x+"_"+debcube+" : ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {System.out.print((debcube+j+i*9)+" ");
                cube[i*3+j] = grille[debcube+j+i*9];
            }
        }

        this.dominiquerepublicaine.printarray(cube);
        return tri(cube);
    }

    public int[] tri(int[] tab){ // pour ranger les nombres de 1 à 9 avec les -1 marquant leur absence
        int[]res={1,2,3,4,5,6,7,8,9};
            for (int i = 1; i <= 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tab[j]==i) {
                        res[i-1]=0;
                    }
                }
            }
        return res;
    }
    
    public int[] fusion(int[] A, int[] B){ // pour fusionner les listes de chiffres disponibles
        
        int[] resultat= A;
        for (int index = 0; index < A.length; index++) {
            if (B[index]<1) { resultat[index]=B[index]; }
        }
        return resultat;
    }
}
