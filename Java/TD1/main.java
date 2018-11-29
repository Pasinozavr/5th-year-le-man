public class main {
 public static void main(String[] args) {
  System.out.println("TD1 Exercice 1");
  EnsembleDeChiffres exemple1 = new EnsembleDeChiffres(),
   exemple2 = new EnsembleDeChiffres(),
   exemple3 = new EnsembleDeChiffres();
  exemple1.tirerAuHasard();
  exemple2.tirerAuHasard();
  exemple3.tirerAuHasard();
  System.out.println("1-e ensemble:");
  exemple1.affiche();
  System.out.println("2-m ensemble:");
  exemple2.affiche();
  System.out.println("3-m ensemble:");
  exemple3.affiche();
  System.out.println("1=1^2:");
  exemple1.intersectionAvec(exemple2);
  exemple1.affiche();
  System.out.println("1=1v3");
  exemple1.unionAvec(exemple3);
  exemple1.affiche();
  System.out.println("TD1 Exercice 2");
  // 1/2 + 1/3 = 5/6
  Rationnel x = new Rationnel(1, 2),
   y = new Rationnel(1, 3),
   z;
  z = x.somme(y);
  System.out.println(z.num() + "/" + z.den());
  // 5/6 -1/6 = 4/6 = 2/3
  z = z.difference(new Rationnel(1, 6));
  System.out.println(z.num() + "/" + z.den());
 }

}