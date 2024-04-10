public class Mage extends Personnage{
    protected int ptMana;
    protected int ptManaMax;
    protected int puissanceSort;

    public int getPtMana() {return ptMana;}

    public void setPtMana(int ptMana) {this.ptMana = ptMana;}

    public int getPtManaMax() {return ptManaMax;}

    public void setPtManaMax(int ptManaMax) {this.ptManaMax = ptManaMax;}

    public int getPuissanceSort() {return puissanceSort;}

    public void setPuissanceSort(int puissanceSort) {this.puissanceSort = puissanceSort;}

    public Mage(String n , int pv, int ff){
        super(n,pv,ff);
        this.ptMana = 100;
        this.ptManaMax = 100;
        this.puissanceSort = 50;
    }
    public Mage(int m , int ma , int p){
        this.ptMana = m;
        this.ptManaMax = ma;
        this.puissanceSort = p;
    }
    public void lancersort(Personnage cible) {
        int degatsSort = this.puissanceSort;  // Dégâts du sort par défaut égaux à la puissance du mage

        if (this.ptMana >= degatsSort) {
            System.out.println(this.nom + " lance un sort sur " + cible.getNom() + " et inflige " + degatsSort + " points de dégâts.");
            this.ptMana -= degatsSort;
            int degatsReçus = cible.recevoircoup(degatsSort);
            System.out.println(cible.getNom() + " reçoit " + degatsReçus + " points de dégâts.");
        } else {
            System.out.println(this.nom + " n'a pas suffisamment de mana pour lancer un sort.");
        }
    }
    public String toString() {
        return "Mage qui s'appelle " + this.nom + ", qui a " + this.pointVie
                + "points de vie et qui tape à " + this.forceFrappe+" et a une puissance de sort de "+this.puissanceSort+" et un mana de "+this.ptMana;
    }
}
