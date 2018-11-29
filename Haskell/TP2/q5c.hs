pow a 1 = a
pow a b = a * pow a (b - 1)

level a 1 b = pow a b
level a n 0 = 1
level a n b = level a (n - 1) (level a n (b - 1))



main = do
	print("enter value for a : ")
	input1 <- getLine
	let a = (read input1 :: Int)
	print("et n : ")
	input2 <- getLine
	let n = (read input2 :: Int)
	print("et b: ")
	input3 <- getLine
	let b = (read input3 :: Int)
	print ("a ^n b = " ++ show (level a n b))
