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

delete el [] = []
delete el (x:xs) =
	if x == el then delete el xs
		else x:(delete el xs)

max_un (el:[]) = el
max_un (el:xs) = 
	if el > max_un xs then el
		else max_un xs

max_deux xs =
	let max1 = max_un xs in
	let del = delete max1 xs in
	(max1, max_un del)

max_trois xs =
	let max2 = max_deux xs in
	let del1 = delete (fst max2) xs in
	let del2 = delete (snd max2) del1 in
	(fst max2, snd max2, max_un del2)

intervalle_asc :: Int->Int->[Int]
intervalle_asc a b = intervalle_temp a b [] where
	intervalle_temp a b c = 
		if a == b then c else intervalle_temp (a+1) b (newel c a)

intervalle_desc a b = inverse (intervalle_asc b a)

lastintlist [x] = x
lastintlist (_:xs) = lastintlist xs

deletelast xs = delete (lastintlist xs) xs

prefixes xs = prefixes_temp xs [] where
	prefixes_temp [] res = res
	prefixes_temp xs [] = prefixes_temp xs [xs]
	prefixes_temp xs res = prefixes_temp (deletelast xs) ((deletelast xs):res)

suffixes xs = inverse (prefixes xs)

suffixes1 xs = suffixes1_temp xs [] where
	suffixes1_temp [] res = inverse(res)
	suffixes1_temp xs [] = suffixes1_temp xs [xs]
	suffixes1_temp (xs:xl) res = suffixes1_temp xl (xl:res)

conjugue mot num = 
	if num > longueur mot then error "life is too cruel"
		else conjugue_temp mot num where
			conjugue_temp mot num = if num <= longueur mot then  [nthelement mot num] ++ conjugue_temp (delete (nthelement mot num) mot) num
				else mot

inferieur a b =  a <= b 

nthelement xs num = el_temp xs num 1 where
	el_temp [] num temp = error "life is cruel"
	el_temp (el:xs) num temp =
		if num == temp then el else el_temp xs num (temp+1)

lyndon_extra mot = if nthelement mot (longueur mot) == 0 then False else True

lyndon_temp mot num = if num <= longueur mot then
		inferieur mot (conjugue mot num) && lyndon_temp mot (num+1)
		else True

lyndon mot = lyndon_extra mot && lyndon_temp mot 2
		
consist ar el = consist_temp ar el 1 where
	consist_temp ar el num = if num <= longueur ar then
		el == nthelement ar num || consist_temp ar el (num+1)
		else False 

fusion_liste l1 l2 = fusion_temp l1 l2 [] where
	fusion_temp [] b res = tri(res)
	fusion_temp (a:as) b res = b_temp a b 1 res where
		b_temp a b temp res =
			if temp <= longueur b then
				let one = a++ nthelement b temp in
				if lyndon one && not (consist res one) then b_temp a b (temp+1) (one:res)
					else b_temp a b (temp+1) res
			else fusion_temp as b res

insere_liste l1 l2 = insere_temp l1 l2 [] where
	insere_temp [] [] res = res
	insere_temp (x:xs) [] res =
		if consist res x then insere_temp xs [] res
			else insere_temp xs [] (x:res)
	insere_temp [] (y:ys) res =
		if consist res y then insere_temp ys [] res
			else insere_temp ys [] (y:res)
	insere_temp (x:xs) (y:ys) res = 
		if consist res x && consist res y then insere_temp xs ys res
			else if not (consist res x) && not (consist res y) then insere_temp xs ys (y:(x:res))
				else if not (consist res x) && consist res y then insere_temp xs ys (x:res)
					else insere_temp xs ys (y:res)

genere n = if n == 1 then [[0],[1]] else if n > 1 then 
	insere_liste
	(fusion_liste (genere 1) (genere (n - 1))) 
	(fusion_liste (genere (n - 1)) (genere 1)) 
	else error "auch"

main = do
	let liste = [1, 3, 4, 2, 5, 12]
	--print(liste)
	--let listesum = somme liste
	--let listeprod = produit liste
	--let listelen = longueur liste
	--print("sum = " ++ show(listesum))
	--print("prod = " ++ show(listeprod))
	--print("length = " ++ show(listelen))
	--let sortedliste= tri liste
	--print("sorted = " ++ show(tri sortedliste))
	--let newlist = newel sortedliste 15
	--print("added 15 = " ++ show(newlist))
	--let newnewlist = newel newlist 2
	--print("added 2 = " ++ show(newnewlist))
	--let concatedlist = conc liste newnewlist
	--print(show(liste) ++ " @ " ++ show(newnewlist) ++ " = " ++ show(concatedlist))
	--let inve = inverse liste
	--print("inverse for " ++ show(liste) ++ " = " ++ show(inve))
	--print("max1 in list = " ++ show(max_un liste))
	--print("max2 in list = " ++ show(max_deux liste))
	--print("max3 in list = " ++ show(max_trois liste))
	--print("enter value for inf (intervalle_asc): ")
	--input1 <- getLine
	--let a = (read input1 :: Int)
	--print("enter value for sup (intervalle_asc): ")
	--input2 <- getLine
	--let b = (read input2 :: Int)
	--print("liste asc " ++ show(a) ++ "->" ++ show (b) ++ " = " ++ show (intervalle_asc a (b+1)))
	--print("liste desc " ++ show(b) ++ "->" ++ show (a) ++ " = " ++ show (intervalle_desc (b+1) a))
	--let liste = "abc"
	--let liste1 = [1,2,3,4,5]
	--let liste2 = [[1,2],[3,4],[5,6]]
	--print("Prefixes = " ++ show(prefixes liste)) 
	--print("Suffixes1 = " ++ show(suffixes1 liste))
	--print("Prefixes = " ++ show(prefixes liste1)) 
	--print("Suffixes1 = " ++ show(suffixes1 liste1))
	--print("Prefixes = " ++ show(prefixes liste2)) 
	--print("Suffixes1 = " ++ show(suffixes1 liste2))
	--print("bierre < bonbon? " ++ show(inferieur ['b', 'i', 'e', 'r', 'e'] ['b', 'o', 'n', 'b', 'o', 'n']))
	--print("cierre < bonbon? " ++ show(inferieur ['c', 'i', 'e', 'r', 'e'] ['b', 'o', 'n', 'b', 'o', 'n']))
	--print("3rd el of abc = " ++ show (nthelement liste 3))
	--print(show("lyndon of aaab? " ++ show(lyndon ['a','a','a','b'])))
	--print(show("lyndon of abab? " ++ show(lyndon ['a','b','a','b'])))
	--print(show("lyndon of baab? " ++ show(lyndon ['b','a','a','b'])))
	--print(show("lyndon of aaba? " ++ show(lyndon ['a','a','b','a'])))
	--print("consist for qwe w =" ++ show(consist ['q','w', 'e'] 'w'))
	--print("insere1 = " ++ show(insere_liste [['0', '1'], ['1', '0']] [['0','0','1'],['0','1']]))
	--print("insere2 = " ++ show(insere_liste [['0'], ['1']] [['0','0','1'],['0','1']]))
	--print("conjugure for a b c d 5 = " ++ show (conjugue ['a', 'b', 'c', 'd'] 5))
	print("genere 1 : " ++ show(genere 1))
	print("genere 2 : " ++ show(genere 2))
	print("genere 3 : " ++ show(genere 3))
	print("genere 4 : " ++ show(genere 4))
	print("genere 5 : " ++ show(genere 5))