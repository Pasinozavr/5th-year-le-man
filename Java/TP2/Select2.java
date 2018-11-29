public class Select2 implements Selectionneur {
 private int a;
 public Select2(int annee) {
  a = annee;
 }
 public Select2() {
  a = 2000;
 }
 public boolean isSatisfaitPar(Document d) {
  if (d.getauteur().getmort() == null) return true;
  else return d.getauteur().getmort().getannee() > a;
 }
}