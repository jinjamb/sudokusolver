package sudokusolver.DeductionRules;

public class Notify {
    String[] difficulte = new String[]{"facile", "moyenne", "difficile"};
    public Notify(){}
    public void modif(int val, int index ){
        System.out.println("le solver a placé "+(val)+" en ( "+(index%9+1)+" , "+(index/9+1)+" ).");
    }
    public void fin(int diff){
         System.out.print("Le solver a terminé la grille, elle est ");
         if (diff>4){ System.out.print("très difficile");}
         else System.out.println(this.difficulte[diff-1]);
    }
}
