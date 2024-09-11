package sudokusolver;
public class Main {
    public static void main(String[] args) {
        Grille grille = new Grille();
        grille.afficher();
        Grille.DRule.choix(grille, 19);
    }
}