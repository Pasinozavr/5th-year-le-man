public class Document {
 private Auteur aut;
 private String titre;
 private boolean atbiblioteque;
 public void changeplace() {
  if (atbiblioteque) atbiblioteque = false;
  else atbiblioteque = true;
 }
 public Auteur getauteur() {
  return aut;
 }
 public String gettitre() {
  return titre;
 }
 public boolean getatbibl() {
  return atbiblioteque;
 }
 public String toString() {
  return aut.toString() + " '" + titre + "'";
 }
 public Document(Auteur a, String t) {
  aut = a;
  titre = t;
  atbiblioteque = true;
 }
 public boolean equals(Document b) {
  if (this.aut.equals(b.getauteur()) && this.titre == b.gettitre()) {
   return true;
  } else return false;
 }
}