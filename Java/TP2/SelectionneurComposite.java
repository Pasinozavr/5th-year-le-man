import java.util.List;
import java.util.ArrayList;

public class SelectionneurComposite implements Selectionneur {
 private List < Selectionneur > selectionneurs;
 public SelectionneurComposite() {
  selectionneurs = new ArrayList < Selectionneur > ();
 }
 public void ajoute(Selectionneur s) {
  selectionneurs.add(s);
 }
 public boolean isSatisfaitPar(Document d) {
  for (int i = 0; i < selectionneurs.size(); i++) {
   if (!selectionneurs.get(i).isSatisfaitPar(d)) return false;
  }
  return true;
 }

}