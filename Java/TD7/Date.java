public class Date implements java.util.Comparator {
 private int jour, mois, annee;
 public int getjour() {
  return jour;
 }
 public int getmois() {
  return mois;
 }
 public int getannee() {
  return annee;
 }
 public Date() {
  jour = 1;
  mois = 1;
  annee = 2000;
 }
 public String toString() {
  return "" + ((jour > 9) ? jour : ("0" + jour)) + "/" + ((mois > 9) ? mois : ("0" + mois)) + "/" + annee;
 }
 public Date(int j, int m, int a) {
   jour = j;
   mois = m;
   annee = a;
 }
 public int compare(Object o1, Object o2) {
  return compare((Date) o1, (Date) o2);
 }
 private int compare(Date d1, Date d2) {
  if (d1 == null) return -d2.annee * 365 - d2.mois * 30 - d2.jour;
  else if (d2 == null) return d1.annee * 365 + d1.mois * 30 + d1.jour;
  else return (d1.annee - d2.annee) * 365 + (d1.mois - d2.mois) * 30 + (d1.jour - d2.jour);
 }
}
