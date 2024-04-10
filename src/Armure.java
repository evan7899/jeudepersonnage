public class Armure {
    protected String nom;
    protected int points;
    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public int getPoints() {return points;}

    public void setPoints(int points) {this.points = points;}

    public Armure(){
        this.nom = "armure commune";
        this.points = 5;
    }
    public Armure(String n, int p){
        this.nom = n;
        this.points = p;
    }
}
