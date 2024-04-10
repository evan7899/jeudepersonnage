public class Personnage {

    protected String nom;
    protected int id;
    protected static int CPT = 0;
    protected int pointVie;
    protected int forceFrappe;
    protected int pvMax;
    protected Arme arme;
    protected Armure armure;

    public Personnage() {
        this.id = ++CPT;
        this.nom = "John Doe";
        this.pointVie = 100;
        this.forceFrappe = 15;
        this.pvMax = this.pointVie;
        this.arme = null;
        this.armure = null;
    }

    public Personnage(String n, int pv, int ff) {
        this.id = ++CPT;
        this.nom = n;
        this.pointVie = pv;
        this.forceFrappe = ff;
        this.pvMax = this.pointVie;
        this.arme = null;
        this.armure = null;
    }

    public Personnage(String n, int pv, int ff, Arme a) {
        this.id = ++CPT;
        this.nom = n;
        this.pointVie = pv;
        this.forceFrappe = ff;
        this.pvMax = this.pointVie;
        this.arme = new Arme();
        this.armure = null;
    }

    public Personnage(String n, int pv, int ff, Arme a, Armure ar) {
        this.id = ++CPT;
        this.nom = n;
        this.pointVie = pv;
        this.forceFrappe = ff;
        this.pvMax = this.pointVie;
        this.arme = new Arme();
        this.armure = new Armure();
    }
    public Personnage(String n, int pv, int ff, Armure ar) {
        this.id = ++CPT;
        this.nom = n;
        this.pointVie = pv;
        this.forceFrappe = ff;
        this.pvMax = this.pointVie;
        this.arme = null;
        this.armure = new Armure();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String n) {
        this.nom = n;
    }

    public int getPointVie() {
        return pointVie;
    }

    public void setPointVie(int pointVie) {
        this.pointVie = pointVie;
    }

    public int getForceFrappe() {
        return forceFrappe;
    }

    public void setForceFrappe(int forceFrappe) {
        this.forceFrappe = forceFrappe;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public Armure getArmure() {
        return armure;
    }

    public void setArmure(Armure a) {
        this.armure = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCPT() {
        return CPT;
    }

    public static void setCPT(int CPT) {
        Personnage.CPT = CPT;
    }

    public Arme getArme() {
        return this.arme;
    }

    public void setArme(Arme a) {
        arme = a;
    }

    @Override
    public String toString() {
        return "Personnage qui s'appelle " + this.nom + ", qui a " + this.pointVie
                + "points de vie et qui tape à " + this.forceFrappe;
    }

    public boolean attaquer(Personnage p) {
        int degats = this.forceFrappe;

        if (this.arme != null) {
            degats += this.arme.getDegat();
            System.out.println(this.nom + " attaque " + p.getNom() + " avec " + this.arme.getNom() + " et inflige " + degats + " points de dégâts.");
        } else {
            System.out.println(this.nom + " attaque " + p.getNom() + " et inflige " + degats + " points de dégâts.");
        }

        int degatsrecu = p.recevoircoup(degats);

        System.out.println(p.getNom() + " reçoit " + degatsrecu + " points de dégâts.");

        return true;
    }

    public int recevoircoup(int c) {
        if (c > 0) {
            pointVie -= c;
            if (pointVie < 0) {
                pointVie = 0;

            }
        }
                return c;
    }
}

