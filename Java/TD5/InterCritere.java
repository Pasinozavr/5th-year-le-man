import java.util.List;
import java.util.ArrayList;

public class InterCritere implements Critere {
 private List < Critere > selectionneurs;
 public InterCritere() {
  selectionneurs = new ArrayList < Critere > ();
 }
 public void ajoute(Critere s) {
  selectionneurs.add(s);
 }
 public boolean correspond(Voiture d) {
  for (int i = 0; i < selectionneurs.size(); i++) {
   if (!selectionneurs.get(i).correspond(d)) return false;
  }
  return true;
 }

}