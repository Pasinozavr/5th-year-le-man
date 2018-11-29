public class Auteur {
 private String nom;
 private Date naiss, mort, temp;
 public Date getnaiss() {
  return naiss;
 }
 public Date getmort() {
  return mort;
 }
 public String getnom() {
  return nom;
 }
 public Auteur(String n, Date na, Date mo) {
  nom = n;
  naiss = na;
  mort = mo;
 }
 public boolean equals(Auteur b) {
  if (temp.compare(this.naiss, b.getnaiss()) != 0 && temp.compare(this.mort, b.getmort()) != 0 && this.nom != b.getnom()) {
   return false;
  } else return true;
 }
 public String toString() {
  if (mort != null) return nom + "'s (" + naiss.toString() + " - " + mort.toString() + ")";
  else return nom + "'s (" + naiss.toString() + " - now)";
 }
}