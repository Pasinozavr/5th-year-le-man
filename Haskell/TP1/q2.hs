q2f1 a b = do
	let a1 = fst a
	let a2 = snd a
	let b1 = fst b
	let b2 = snd b
	if a1==b1 || a1==b2 || a2==b1 || a2==b2 then True 
		else False

q2f2 a b c = do
	let a1 = fst a
	let a2 = snd a
	let b1 = fst b
	let b2 = snd b
	let c1 = fst c
	let c2 = snd c
	if a1==b1 && (a2==c1 || a2==c2 || b2==c1 || b2==c2) then True
		else if a1==b2 && (a2==c1 || a2==c2 || b1==c1 || b1==c2) then True
			else if a2==b1 && (a1==c1 || a1==c2 || b2==c1 || b2==c2) then True
				else if a2==b2 && (a1==c1 || a1==c2 || b1==c1 || b1==c2) then True
					else False

main = print (q2f2 (2,3) (2,4) (1,2))