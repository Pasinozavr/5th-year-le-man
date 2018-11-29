f1 x = x + 1

applyn f n x =
	if n == 0 then x else
		if n > 0 then f (applyn f (n - 1) x)
			else 1
pow x n =
	applyn (\p -> p * x) (n - 1) x

main = do
	print("Allyn pour f ^ n (x+1) =  " ++ show(applyn (\p->p+1) 5 10))
	print("2^10 = " ++ show(pow 2 10) ++ " = " ++ show(2**10))