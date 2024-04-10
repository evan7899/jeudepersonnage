public class Arme {
        private String nom;
        private int degat;

        public Arme() {
            this.nom = "Marteau";
            this.degat = 10;
        }

        public Arme(String n, int d) {
            this.nom = n;
            this.degat = d;
        }

        public Arme(String n) {
            this.nom = n;
            this.degat = 5;
        }

        public String getNom() { return this.nom; }
        public void setNom(String n) { this.nom = n; }
        public int getDegat() { return degat; }
        public void setDegat(int degat) { this.degat = degat; }

        @Override
        public String toString() {
            return "Arme " + "qui s'appelle " + nom + " et qui fais " + degat + " de dégats";
        }
    }
