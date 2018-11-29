somme_filtre f n = if n == 0 then 0 else
	if f n then n + (somme_filtre f (n - 1))
		else somme_filtre f (n - 1)

etre_pair n =
	if n `mod` 2 == 0 then True else False

etre_parfait n =
	2 * n == somme_filtre (\p -> n `mod` p == 0) n 

main = do
	print("Somme_filtres pour " ++ show(10) ++ " = " ++ show(somme_filtre etre_pair 10))
	print("Est-ce nombre 6 parfait? Et 5? - " ++ show(etre_parfait 6) ++ "  " ++  show(etre_parfait 5))