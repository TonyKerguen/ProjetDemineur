import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Plateau{
    
    protected int nbLignes;
    protected int nbColonnes;
    protected int pourcentageDeBombes;
    protected int nbBombes;
    private CaseIntelligente[][] lePateau;

    public Plateau(int nbLignes, int nbColonnes, int pourcentageDeBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.pourcentageDeBombes = pourcentageDeBombes;
        creerLesCasesVide();
        this.nbBombes = 0;
        poseDesBombesAleatoirement();
    }

    private void creerLesCasesVide() {
        lePateau = new CaseIntelligente[nbLignes][nbColonnes];
        for(int x = 0; x < nbLignes; x++) {
            for(int y = 0; y < nbLignes; y++) {
                lePateau[x][y] = new CaseIntelligente();
            }
        }
        rendLesCasesIntelligentes();
    }

    private void rendLesCasesIntelligentes() {
        for(int x = 0; x < nbLignes; x++) {
            for(int y = 0; y < nbLignes; y++) {
                for(int dx = -1; dx <= 1; dx++) {
                    for(int dy = -1; dy <= 1; dy++) {
                        try {
                            lePateau[x][y].ajouteVoisine(lePateau[x + dx][y + dy]);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }
    }

    protected void poseDesBombesAleatoirement(){
        Random generateur = new Random();
        for (int x = 0; x < this.getNbLignes(); x++){
            for (int y = 0; y < this.getNbColonnes(); y++){
                if (generateur.nextInt(100)+1 < this.pourcentageDeBombes && !lePateau[x][y].contientUneBombe()){
                    this.poseBombe(x, y);
                    this.nbBombes = this.nbBombes + 1;
                }
            }
        }
    }

    public int getNbLignes() {
        return this.nbLignes;
    }

    public int getNbColonnes() {
        return this.nbColonnes;
    }

    public int getNbTotalBombes() {
        return this.nbBombes;
    }

    public CaseIntelligente getCase(int numLigne, int numColonne) {
        return lePateau[numLigne][numColonne];
    }

    public int getNbCasesMarquees() {
        int NbCasesMarquees = 0;
        for(int x = 0; x < nbLignes; x++) {
            for(int y = 0; y < nbLignes; y++) {
                if(lePateau[x][y].estMarquee()) {
                    NbCasesMarquees += 1;
                }
            }
        }
        return NbCasesMarquees;
    }

    public int getNbCasesRevele() {
        int NbCasesRevele = 0;
        for(int x = 0; x < nbLignes; x++) {
            for(int y = 0; y < nbLignes; y++) {
                if(lePateau[x][y].estDecouverte()) {
                    NbCasesRevele += 1;
                }
            }
        }
        return NbCasesRevele;
    }

    public void poseBombe(int x, int y) {
        if(x <= nbLignes && y <= nbColonnes && !(this.getCase(x, y).contientUneBombe())) {
            lePateau[x][y].setContientUneBombe(true);
        }
    }

    public void reset() {
        for(int x = 0; x < nbLignes; x++) {
            for(int y = 0; y < nbLignes; y++) {
                lePateau[x][y].reset();
            }
        }
    }

}
