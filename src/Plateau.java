import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Plateau{
    
    protected int nbLignes;
    protected int nbColonnes;
    protected int pourcentageDeBombes;
    protected int nbBombes;
    private List<CaseIntelligente> lePateau;

    public Plateau(int nbLignes, int nbColonnes, int pourcentageDeBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.pourcentageDeBombes = pourcentageDeBombes;
        this.nbBombes = 0;
        this.lePateau = new ArrayList<>();
    }

    public void rendLesCasesIntelligentes() {
        for(int x = 0; x < this.getNbLignes(); x++) {
            for(int y = 0; y < this.getNbColonnes(); y++) {
                if(x > 0) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x-1, y));
                }
                if(y > 0) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x, y-1));
                }
                if(x > 0 && y > 0) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x-1, y-1));
                }
                if(x < this.getNbLignes()) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x+1, y));
                }
                if(y < this.getNbColonnes()) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x, y+1));
                }
                if(x < this.getNbLignes() && y < this.getNbColonnes()) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x+1, y+1));
                }
                if(x > 0 && y < this.getNbColonnes()) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x-1, y+1));
                }
                if(y > 0 && x < this.getNbLignes()) {
                    this.lePateau.get(x*this.getNbColonnes()+y).ajouteVoisine(this.getCase(x+1, y-1));
                }
            }
        }
    }

    public void creerLesCasesVide() {
        for(int i = 0; i < this.getNbLignes()*this.getNbColonnes(); i++) {
            lePateau.add(new CaseIntelligente());
        }
    }

    protected void poseDesBombesAleatoirement(){
        Random generateur = new Random();
        for (int x = 0; x < this.getNbLignes(); x++){
            for (int y = 0; y < this.getNbColonnes(); y++){
                if (generateur.nextInt(100)+1 < this.pourcentageDeBombes){
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
        return this.lePateau.get(numLigne*this.getNbColonnes()+numColonne);
    }

    public int getNbCasesMarquees() {
        int NbCasesMarquees = 0;
        for(Case c:this.lePateau) {
            if(c.estMarquee()) {
                NbCasesMarquees += 1;
            }
        }
        return NbCasesMarquees;
    }

    public void poseBombe(int x, int y) {
        if(x + 1 * y + 1 > this.lePateau.size() && !(this.getCase(x, y).contientUneBombe())) {
            this.lePateau.get(x*this.getNbColonnes()+y).setContientUneBombe(true);
        }
    }

    public void reset() {
        for(Case c:this.lePateau) {
            c.reset();
        }
    }

}
