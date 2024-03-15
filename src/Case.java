public class Case {
    protected boolean contientUneBombe;
    protected boolean estDecouverte;
    protected boolean estMarquee;

    public Case() {
        this.contientUneBombe = false;
        this.estDecouverte = false;
        this.estMarquee = false;
    }

    public boolean contientUneBombe() {
        return this.contientUneBombe;
    }

    public boolean estDecouverte() {
        return this.estDecouverte;
    }

    public boolean estMarquee() {
        return this.estMarquee;
    }

    public void setContientUneBombe(boolean b){
        this.contientUneBombe = b;
    }

    public void setEstDecouverte(boolean b){
        this.estDecouverte = b;
    }

    public void setEstMarquee(boolean b){
        this.estMarquee = b;
    }

    public void reset() {
        this.setContientUneBombe(false);;
        this.setEstDecouverte(false);;
        this.setEstMarquee(false);;
    }

    public void poseBombe() {
        this.setContientUneBombe(true);
    }

    public boolean reveler() {
        if(!this.estDecouverte()) {
            this.setEstDecouverte(true);
            return true;
        }
        else {
            return false;
        }
    }

    public void marquer() {
        this.setEstMarquee(!this.estMarquee());
    }
}
