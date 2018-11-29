somme xs = sum_temp xs 0 where
	sum_temp [] n = n
	sum_temp (el:xs) n = sum_temp xs (n + el)

produit xs = prod_temp xs 1 where
	prod_temp [] n = n
	prod_temp (el:xs) n = prod_temp xs (n * el)

longueur xs = long_temp xs 0 where
	long_temp [] n = n
	long_temp (el:xs) n = long_temp xs (n + 1)

tri xs = tri_it xs 0 where
	tri_it xs num =
		if num == (longueur xs) then xs
			else tri_it(tri_temp xs) (num + 1) where
				tri_temp (el1:el2:autre) =
					if el1 > el2 then el2:tri_temp(el1:autre)
						else el1:tri_temp(el2:autre)
				tri_temp (el) = (el)

newel [] ins = [ins]
newel (el:xs) ins =
	if el >= ins then (ins:el:xs) else
		if el == ins then (el:ins:xs) else (el:newel xs ins)

conc [] x = x
conc x [] = x
conc (x:xs) y = x:(conc xs y)

inverse (el:xs) = reverse_temp (el:xs) [] where
	reverse_temp [] res = res
	reverse_temp (el:xs) res = reverse_temp xs (el:res)


main = do
	let liste = [3, 2, 4, 1]
	print(liste)
	let listesum = somme liste
	let listeprod = produit liste
	let listelen = longueur liste
	print("sum = " ++ show(listesum))
	print("prod = " ++ show(listeprod))
	print("length = " ++ show(listelen))
	let sortedliste= tri liste
	print("sorted = " ++ show(tri sortedliste))
	let newlist = newel sortedliste 15
	print("added 15 = " ++ show(newlist))
	let newnewlist = newel newlist 2
	print("added 2 = " ++ show(newnewlist))
	let concatedlist = conc liste newnewlist
	print(show(liste) ++ " @ " ++ show(newnewlist) ++ " = " ++ show(concatedlist))
	let inve = inverse liste
	print("inverse for " ++ show(liste) ++ " = " ++ show(inve))