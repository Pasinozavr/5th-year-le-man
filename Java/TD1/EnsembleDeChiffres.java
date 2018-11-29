import java.util.Random;

public class EnsembleDeChiffres {
 private boolean[] present;
 private Random rnd;
 public EnsembleDeChiffres() {
  rnd = new Random();
  present = new boolean[10];
  for (int i = 0; i < 10; i++) {
   present[i] = false;
  }
 }
 public void ajoute(int i) {
  if (i >= 0 && i < 10) present[i - 1] = true;
  else System.out.println("Errored number");
 }
 public void retire(int i) {
  if (i >= 0 && i < 10) present[i - 1] = false;
  else System.out.println("Errored number");
 }
 public void tirerAuHasard() {
  for (int i = 0; i < 10; i++) {
   present[i] = rnd.nextBoolean();
  }
 }
 public boolean appartient(int i) {
  return present[i - 1];
 }
 public EnsembleDeChiffres intersectionAvec(EnsembleDeChiffres e) {
  for (int i = 0; i < 10; i++) {
   if (!this.present[i] || !e.present[i]) {
    this.present[i] = false;
   }
  }
  return this;
 }
 public EnsembleDeChiffres unionAvec(EnsembleDeChiffres e) {
  for (int i = 0; i < 10; i++) {
   if (!this.present[i] && !e.present[i]) {
    this.present[i] = false;
   } else {
    this.present[i] = true;
   }
  }
  return this;
 }
 public boolean estInclusDans(EnsembleDeChiffres e) {
  for (int i = 0; i < 10; i++) {
   if (e.present[i] != this.present[i]) {
    return false;
   }
  }
  return true;
 }
 public void affiche() {
  System.out.print("{ ");
  for (int i = 0; i < 10; i++) {
   if (this.present[i]) {
    System.out.print("" + i + " ");
   }
  }
  System.out.println("}");
 }
}