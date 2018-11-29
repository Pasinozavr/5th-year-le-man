f s = do
	print(show(s))
	let frac = snd (properFraction s)
	let inte = fst (properFraction s)
	let s2 = inte `div` 2
	let rest = inte - s2 * 2
	let s1 = ((inte - s2 * 2) `div` 1)
	print (show(inte) ++ " is: " ++ show(s2) ++ " x 2 euro coins + " ++ show(s1) ++ " x 1 euro coins")
	
	let m50 = 
		if frac >= 0.5 
			then 1 
		else 0

	let rest= frac - m50 * 0.5

	let m25 = 
		if rest >= 0.25 
			then 1 
		else 0

	let rest = frac - m50 * 0.5 - m25 * 0.25

	let m10 =
		if rest >= 0.1 && rest < 0.2
			then 1
		else
			if rest < 0.1
				then 0
				else 2

	let rest = frac - m50 * 0.5 - m25 * 0.25 - m10 * 0.1

	let m5 = 
		if rest >= 0.05
			then 1
			else 0
	
	let rest = frac - m50 * 0.5 - m25 * 0.25 - m10 * 0.1 - m5 *0.05

	let m2 =
		if rest > 0.04
			then 2
			else if rest < 0.02
				then 0
				else 1

	let rest = frac - m50 * 0.5 - m25 * 0.25 - m10 * 0.1 - m5 *0.05 - m2 * 0.02

	let m1 =
		if rest < 0.01
			then 0
			else 1

	print (show(frac) ++ " is: " ++ show(m50) ++ " x 50 cent coins + " ++ show(m25) ++ " x 25 cent coins + " ++ show(m10) ++ " x 10 cent coins + " ++ show(m5) ++ " x 5 cent coins + " ++ show(m2) ++ " x 2 cent coins + " ++ show(m1) ++ " x 1 cent coins")

main = f 15.41