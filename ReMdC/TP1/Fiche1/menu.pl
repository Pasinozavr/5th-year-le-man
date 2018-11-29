
entree(crudites).
entree(terrine).
entree(melon).


viande(steack).
viande(poulet).
viande(gigot).


poisson(bar).
poisson(saumon).


dessert(sorbet).
dessert(creme).
dessert(tarte).


menu_simple(E, P, D) :- entree(E), plat(P), dessert(D).

plat(P) :- viande(P).
plat(P) :- poisson(P).