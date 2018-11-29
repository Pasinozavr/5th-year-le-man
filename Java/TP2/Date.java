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
 public Date(int j, int m, int a) throws DateException {
  try {
   jour = j;
   mois = m;
   annee = a;
   if (m < 1 || m > 12 || j < 1 || j > 31) {
    throw new DateException("The date is unvalid", j, m);
   }
  } catch (DateException e) {
   System.out.println("Data was unvalid so it changed");
   if (m > 12 || m < 1) mois = 12;
   if (j > 31 || j < 1) jour = 30;
  }
  System.out.println("Date " + toString() + " created");
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

class DateException extends Exception {
 static final long serialVersionUID = 1L;
 private int month, day;
 public int getDay() {
  return day;
 }
 public int getMonth() {
  return month;
 }
 public DateException(String message, int d, int m) {
  super(message);
  month = m;
  day = d;
 }
}