import java.util.Scanner;

public class Demineur extends Plateau{

    private boolean gameOver = false;
    private boolean estGagnee = false;
    private boolean estPerdue = false;
    private int score;

    public Demineur(int nbLignes, int nbColonnes, int pourcentageDeBombes) {
        super(nbLignes, nbColonnes, pourcentageDeBombes);
        this.score = 0;
    }

    public int getScore(){
        return this.score;
    }

    public void reveler(int x, int y) {
        super.getCase(x, y).setEstDecouverte(true);
        if (super.getCase(x, y).contientUneBombe()) {
            this.estPerdue = true;
            this.gameOver = true;
        }
    }

    public int marquer(int x, int y) {
        super.getCase(x, y).marquer();
        return 10;
    }

    public void affiche(){
        System.out.println("JEU DU DEMINEUR");
        // affichage de la bordure supérieure
        System.out.print("  ");
        for (int j=0; j<this.getNbColonnes(); j++){
            System.out.print("  "+j+" ");
        }
        System.out.print(" \n");
        
        // affichage des numéros de ligne + cases
        System.out.print("  ┌");
        for (int j=0; j<this.getNbColonnes()-1; j++){
                System.out.print("───┬");
        }
        System.out.println("───┐");
        
        // affichage des numéros de ligne + cases
        for (int i = 0; i<this.getNbLignes(); i++){
            System.out.print(i+" ");
            for (int j=0; j<this.getNbColonnes(); j++){
                System.out.print("│ "+this.getCase(i, j).toString() + " ");
            }
            System.out.print("│\n");
            
            if (i!=this.getNbLignes()-1){
                // ligne milieu
                System.out.print("  ├");
                for (int j=0; j<this.getNbColonnes()-1; j++){
                        System.out.print("───┼");
                }
                System.out.println("───┤");
            }
        }

        // affichage de la bordure inférieure
        System.out.print("  └");
        for (int j=0; j<this.getNbColonnes()-1; j++){
                System.out.print("───┴");
        }
        System.out.println("───┘");
        
        // affichage des infos 
        System.out.println("Nombres de bombes à trouver : " + this.getNbTotalBombes());
        System.out.println("Nombres de cases marquées : " + this.getNbCasesMarquees());
        System.out.println("Score : " + this.getScore());
    }

    
    public void nouvellePartie(){
        this.reset();
        this.poseDesBombesAleatoirement();
        this.affiche();
        Scanner scan = new Scanner(System.in).useDelimiter("\n");

        while (!this.estPerdue || this.estGagnee){
            System.out.println("Entrer une instruction de la forme R 3 2 ou M 3 2\npour Révéler/Marquer la case à la ligne 3 et à la colonne 2");
            String [] s = scan.nextLine().split(" ");
            String action = s[0];
            int x = Integer.valueOf(s[1]);
            int y = Integer.valueOf(s[2]);
            if (action.equals("M") || action.equals("m"))
                this.marquer(x, y);
            else if (action.equals("R") || action.equals("r"))
                this.reveler(x, y);
            this.affiche();
            System.out.println(super.getNbCasesMarquees());
            System.out.println(super.getNbCasesRevele());
            System.out.println(super.getNbColonnes()*super.getNbLignes());
            if(super.getNbCasesMarquees() + super.getNbCasesRevele() == super.getNbColonnes()*super.getNbLignes()) {
                this.estGagnee = true;
            }
            
        }
        
        if (this.gameOver){
            System.out.println("Oh !!! Vous avez perdu !");
        }
        else{
            System.out.println("Bravo !! Vous avez gagné !");
        }
        this.score+=10;
        scan.close();
    }

    public boolean estPerdue(){
        return this.estPerdue;
    }

    public boolean estGagnee(){
        return this.estGagnee;
    }
}
