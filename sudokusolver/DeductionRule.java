package sudokusolver;

public class DeductionRule {

    public DeductionRule(){}
    public void rule(int[] grille){}
   
    public void printarray(int[] A){ // a bouger dans une toolbox
        System.out.print("[");
        for (int i = 0; i < A.length; i++) {
            if (A[i]==-1){System.out.print("_");}
            else {System.out.print(A[i]);}
            if (i< A.length-1){System.out.print(", ");}
            }
            System.out.print("]\n");
        }

}