public class Rationnel {
 private int num;
 private int den;
 public Rationnel(int p, int q) {
  num = p;
  den = q;
  simplifier();
 }
 public int num() {
  return num;
 }
 public int den() {
  return den;
 }
 private void simplifier() {
  if (num == 0) den = 1;
  else {
   int p = pgcd(num, den);
   num /= p;
   den /= p;
   if (den < 0) {
    num = -num;
    den = -den;
   }
  };
 }
 private static int pgcd(int a, int b) {
  while (a != b)
   if (a > b) {
    a = a - b;
   } else {
    b = b - a;
   };
  return (a);
 }
 public double toDouble() {
  return (double) num / (double) den;
 }
 public Rationnel somme(Rationnel r) {
  return new Rationnel(num * r.den + den * r.num, den * r.den);
 }
 public Rationnel difference(Rationnel r) {
  return new Rationnel(num * r.den - den * r.num, den * r.den);
 }
 public Rationnel produit(Rationnel r) {
  return new Rationnel(num * r.num, den * r.den);
 }
 public Rationnel quotient(Rationnel r) {
  return new Rationnel(num * r.den, den * r.num);
 }
 public int compareTo(Rationnel r) {
  if (num == r.num && den == r.den) {
   return 0;
  } else if (num * r.den > den * r.num) {
   return 1;
  } else {
   return -1;
  }
 }
 public Rationnel abs() {
  if (num >= 0) {
   return this;
  } else {
   return new Rationnel(-num, den);
  }
 }
}