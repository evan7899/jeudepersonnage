public class Guerrier extends Personnage {
    protected int blocage;
    protected boolean enrager;

    public Guerrier(String n, int pv, int ff) {
        super(n, pv, ff);
        this.blocage = 15;
        this.enrager = false;
    }

    public Guerrier(int b, String n, int pv, int ff) {
        super(n, pv, ff);
        this.blocage = b;
        this.enrager = false;
    }

    public int getBlocage() {
        return blocage;
    }

    public void setBlocage(int blocage) {
        this.blocage = blocage;
    }

    public boolean isEnrager() {
        return enrager;
    }

    public void setEnrager(boolean enrager) {
        this.enrager = enrager;
    }

    public boolean berserk() {
            this.enrager = true;
        return true;
    }

    public boolean attaquer(Personnage p) {
        int degats = this.forceFrappe;
        int degatsberserk=this.forceFrappe*2;
        if (!this.enrager) {
            super.attaquer(p);
            System.out.println(this.nom + " attaque " + p.getNom() + " et inflige " + degats + " points de dégâts.");
        } else {
                p.recevoircoup(2*forceFrappe);
            System.out.println(this.nom + " deviens fou"+" et inflige "+degatsberserk+" à "+p.getNom());
        }
        return true;
    }
    public int recevoircoup(int d){
        int degatsreel= d-blocage;
        if(degatsreel<0)degatsreel=0;
        this.pointVie -= degatsreel;
        return degatsreel;
    }
    public String toString() {
        return "Guerrier qui s'appelle " + this.nom + ", qui a " + this.pointVie
                + "points de vie et qui tape à " + this.forceFrappe+" avec un bouclier de "+this.blocage;
    }
}
