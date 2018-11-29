q1f1  a b c d =	
	if a==b && b==c && c==d then True else False

q1f2 a b c d = 
	if a>=b && a>=c && a>=d then a 
		else if b>=a && b>=c && b>=d then b
			else if c>=a && c>=b && c>=d then c
				else d

negat a = if a<0 then a*(-1) else a

aloigne a b c d = negat(a-b) + negat(a-c) + negat(a-d)

q1f3 a b c d = do
	let ae = aloigne a b c d
	let be = aloigne b a c d
	let ce = aloigne c a b d
	let de = aloigne d a b c
	let calc = q1f2 ae be ce de
	if calc == ae then a
		else if calc == be then b
			else if calc == ce then c
				else d


main = 	print (q1f3 12 3 25 1)