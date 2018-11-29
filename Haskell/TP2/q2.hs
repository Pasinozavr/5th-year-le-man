factnorm 0 = 1
factnorm n = n * factnorm (n-1)

facttail x = f x 1 where
	f 0 n = n
	f a n = f (a - 1) (a * n)

fibnorm 0 = 1
fibnorm 1 = 1
fibnorm n = fibnorm (n - 1) + fibnorm (n - 2)

fibtail n = fib' 0 1 n where
	fib' a b 0 = b
	fib' a b n = fib' b (a + b) (n - 1)

fac_cpt 0 = 1
fac_cpt n = 1 + fac_cpt (n - 1)

fac2_cpt x = f' x 1 where
	f' 0 n = 1
	f' a n = f' (a - 1) a + 1

fib_cpt 0 = 1
fib_cpt 1 = 1
fib_cpt n = fib_cpt(n - 1) + fib_cpt(n - 2) + 1

fib2_cpt n = cpt' 0 1 n where
	cpt' a b 0 = n
	cpt' a b n = cpt' 1 (a + b) (n - 1)

pow2_test n = 
	if n `mod` 2 == 1 then False
		else if n == 2 then True
			else pow2_test (n `div` 2)

main = do
	print("enter value for x (factorielle): ")
	input1 <- getLine
	let x = (read input1 :: Int)
	print ("Avec factorille normalle nous avons result: " ++ show(factnorm x) ++ " en " ++ show(fac_cpt x) ++ " steps.")
	print ("Avec factorille taile nous avons result: " ++ show(facttail x) ++ " en " ++ show(fac2_cpt x) ++ " steps.")
	print("enter value for y (fibonacci): ")
	input2 <- getLine
	let y = (read input2 :: Int)
	print("Fibonacci pour " ++ show(y) ++ " = " ++ show(fibnorm y) ++ " en " ++ show(fib_cpt x) ++ " steps.")
	print("Fibonacci pour " ++ show(y) ++ " = " ++ show(fibtail y) ++ " en " ++ show(fib2_cpt x) ++ " steps.")
	print("enter value for z (est ce est puissance de 2?): ")
	input3 <- getLine
	let z = (read input3 :: Int)
	print(pow2_test z)

