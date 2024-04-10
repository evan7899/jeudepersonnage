public class Main {
    public static void main(String[] args) {
        Arme a1 = new Arme();
        Personnage p1 = new Personnage("kiki",200,160,a1);
        Personnage p2 = new Personnage("bob",150,12);
        Guerrier g1 = new Guerrier(50,"jean",200,50);
        Mage m1=new Mage("ulysse",150,5);

        System.out.println(p1+" avec son "+a1);
        System.out.println(g1);
        System.out.println(m1);

        p1.attaquer(g1);
        m1.lancersort(p1);
        System.out.println(p1);
        System.out.println(g1);
        System.out.println(m1);

        if (g1.getPointVie()<50){
            g1.berserk();
            g1.attaquer(p1);
        }else{
            g1.attaquer(p1);
        }





        System.out.println(p1);
        System.out.println(g1);
    }
}