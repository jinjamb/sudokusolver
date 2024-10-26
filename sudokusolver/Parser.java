package sudokusolver;
import java.util.Scanner;

public class Parser {
    public static int[] parser(){
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<81; i++){
            int val = sc.nextInt();
            if (val==-1) {Grille.grid[i]=-1;}
            if (val==0 || val>9) {System.out.println("Valeur incorrecte, veuillez entrer une valeur entre 1 et 9"); i--;}
            else {Grille.grid[i]=val;}
        }
        return Grille.grid;
    }
}