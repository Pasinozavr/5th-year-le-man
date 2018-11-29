public class Select3 implements Selectionneur {
 private String m;
 public Select3(String word) {
  m = word;
 }
 public Select3() {
  m = " ";
 }
 public boolean isSatisfaitPar(Document d) {
  return d.gettitre().contains(m);
 }
}