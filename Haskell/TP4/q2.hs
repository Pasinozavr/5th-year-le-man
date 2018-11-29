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

main = do
	let list = [23, 8, 7, 12, 20, 1]
	print("list = " ++ show(list))
	let maxim = max_un list
	print("max_un of list = " ++ show(maxim))
	print("max_deux = " ++ show(max_deux(list)))
	print("max_trois = " ++ show(max_trois(list)))