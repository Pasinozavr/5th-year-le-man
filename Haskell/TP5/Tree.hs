--for lists
longueur xs = long_temp xs 0 where
  long_temp [] n = n
  long_temp (el:xs) n = long_temp xs (n + 1)

newel [] ins = [ins]
newel (el:xs) ins =
  if el >= ins then (ins:el:xs) else
    if el == ins then (el:ins:xs) else (el:newel xs ins)

conc [] x = x
conc x [] = x
conc (x:xs) y = x:(conc xs y)

tri xs = tri_it xs 0 where
  tri_it xs num =
    if num == (longueur xs) then xs
      else tri_it(tri_temp xs) (num + 1) where
        tri_temp (el1:el2:autre) =
          if el1 > el2 then el2:tri_temp(el1:autre)
            else el1:tri_temp(el2:autre)
        tri_temp (el) = (el)
--for trees
data Tree a = Node a (Tree a) (Tree a)
             | Leaf
               deriving Show

mymax a b = if a >= b then a else b

depth Leaf = 0
depth (Node _ gauche droite) = 1 + mymax (depth gauche) (depth droite)

numOfLeafs Leaf = 1
numOfLeafs (Node _ gauche droite) = numOfLeafs gauche + numOfLeafs droite

numOfNodes Leaf = 0
numOfNodes (Node _ gauche droite) = 1 + numOfNodes gauche + numOfNodes droite

summary Leaf = 0
summary (Node el gauche droite) = el + summary gauche + summary droite

find _ Leaf = False
find e (Node f g d) =
  if e == f then True
    else if e < f then find e g
      else find e d

insere e Leaf = Node e Leaf Leaf
insere e (Node f g d) =
  if e == f then Node f g d
    else if e < f then Node f (insere e g) d
      else Node f g (insere e d)


insere_pile e Leaf = Node e Leaf Leaf
insere_pile e (Node x d g) = if e == x then Node x d g
  else if e > x then Node x g (insere_pile e d)
    else Node x (insere_pile e g) d


list_to_tree (x:xs) = tree_temp xs (insere x Leaf) where
  tree_temp lst tr = case lst of
    {
      [] -> tr;
      v:r -> tree_temp r (insere v tr);
    }

tree_to_list tr = list_temp tr [] where
  list_temp tr lst = case tr of
  {
    (Node e Leaf Leaf) -> newel lst e;
    (Node e g Leaf) -> newel (list_temp g lst) e;
    (Node e Leaf d) -> newel (list_temp d lst) e;
    (Node e g d)-> tri(conc (list_temp d []) (newel (list_temp g lst) e));
  }

tree_to_list' Leaf = []
tree_to_list' (Node e g d) = tree_to_list' g ++ [e] ++ tree_to_list' d

tri_lt l = tree_to_list(list_to_tree l)

forall _ Leaf = True
forall cond (Node e g d) = cond e && forall cond g && forall cond d

bonne_forme Leaf = True
bonne_forme (Node e g d) = forall (< e) g && forall (> e) d && bonne_forme g && bonne_forme d

bien_forme_pile t = case t of
  {
  Leaf -> True;
  (Node x g d) -> bien_forme_pile g && bien_forme_aux x g && bien_forme_pile d && bien_forme_aux x d;
}

bien_forme_aux x a = case a of { Leaf -> True; (Node y _ _) -> x<=y; }

bien_forme_abr a = case a of
  {
  Leaf -> True;
  (Node x g d) -> bien_forme_abr g && superieur_arbre x g && bien_forme_abr d && inferieur_arbre x d;
}

superieur_arbre x a = case a of { Leaf -> True;  (Node y _ d) -> x>=y && superieur_arbre x d;}

inferieur_arbre x a = case a of { Leaf -> True; (Node y g _) -> x<=y && inferieur_arbre x g;}

treeExemple = Node 1 (Node 2 (Node 4 Leaf Leaf) (Node 5 (Node 7 Leaf Leaf) (Node 8 Leaf Leaf))) (Node 3 Leaf (Node 6 (Node 9 Leaf Leaf) Leaf))

abrExemple = Node 10 (Node 5 Leaf (Node 8 (Node 7 Leaf Leaf) (Node 9 Leaf Leaf))) (Node 20 (Node 15 (Node 12 Leaf Leaf) (Node 17 Leaf Leaf)) (Node 25 Leaf Leaf)) 

pileExemple = Node 8 (Node 20 (Node 21 (Node 90 Leaf Leaf) (Node 22 Leaf Leaf)) (Node 80 Leaf (Node 81 Leaf Leaf))) (Node 10 (Node 12 (Node 75 Leaf Leaf) (Node 20 Leaf Leaf)) (Node 11 (Node 80 Leaf Leaf) Leaf))

main = do
  print("Tree1 : " ++ show(treeExemple))
  print("Depth = " ++ show(depth treeExemple))
  print("numOfLeafs = " ++ show(numOfLeafs treeExemple))
  print("numofNodes = " ++ show(numOfNodes treeExemple))
  print("sum = " ++ show(summary treeExemple))
  print("Tree2 : " ++ show(abrExemple))
  print("Is 8 in abrExemple " ++ show(find  8 abrExemple))
  print("Is ex2 ABR ? " ++ show(bien_forme_abr abrExemple))
  print("Is ex3 pile ? " ++ show(bien_forme_pile pileExemple))
  print("ABR to list : " ++ show(tree_to_list' abrExemple))
  print("pile to list : " ++ show(tree_to_list' pileExemple))
  print("sort of 10,5,2,6,12,44,7 = " ++ show(tri_lt [10,5,2,6,12,44,7]))
  print("Adding 13 to abr : " ++ show(insere 13 abrExemple))
  print("Adding 13 to pile : " ++ show(insere 25 pileExemple))


