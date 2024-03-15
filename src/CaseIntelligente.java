import java.util.ArrayList;
import java.util.List;

public class CaseIntelligente extends Case {
    
    private List<Case> lesVoisines;

    public CaseIntelligente() {
        super();
        this.lesVoisines = new ArrayList<>();
    }

    public void ajouteVoisine(Case uneCase) {
        this.lesVoisines.add(uneCase);
    }

    public int nombreBombesVoisines() {
        int nombreBombesVoisines = 0;
        for(Case c : this.lesVoisines) {
            if(c.contientUneBombe()) {
                nombreBombesVoisines += 1;
            }
        }
        return nombreBombesVoisines;
    }

    public String toString() {
        if(this.estDecouverte()){
            if(this.contientUneBombe()) {
                return "@";
            }
            else {
                return "" + this.nombreBombesVoisines();
            }
        }
        else if(this.estMarquee()){
            return "?";
        }
        else {
            return " ";
        }
    }
}
