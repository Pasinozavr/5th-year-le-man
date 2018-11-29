f1 n = n
f2 n = if (n `mod` 2) == 1 then n else 0
f3 n = if (n `mod` 2) == 0 then n * (-1) else n

somme_termes u n =
	if n == 0 then u(0)
	else u(n) + somme_termes u (n - 1)

inv100 x = somme_termes (\n -> (1 - x) ** n) 100

main = do
	print("a. Somme_termes reels pour " ++ show(100) ++ " = " ++ show(somme_termes f1 100))
	print("c. Somme_termes pour +- " ++ show(99) ++ " = " ++ show(somme_termes f3 99))
	print("b. Somme_termes pour impairs " ++ show(10) ++ " = " ++ show(somme_termes f2 10))
	print("d. inv100 pour " ++ show(0.5) ++ " = " ++ show(inv100 0.5))