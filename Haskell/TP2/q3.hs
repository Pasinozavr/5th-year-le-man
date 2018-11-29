fac x = f x 1 where
	f 0 n = n
	f a n = f (a - 1) (a * n)

temp 0 n2 n = n
temp n1 0 n = n
temp n1 n2 n = temp (n1 - 1) (n2 - 1) (n + n2 + n1) 

melange2 n1 n2 = temp n1 n2 0

melange3 n1 n2 n3 = more n1 n2 n3 0 where
	more 0 n2 n3 n = temp n2 n3 n
	more n1 0 n3 n = temp n1 n3 n
	more n1 n2 0 n = temp n1 n2 n
	more n1 n2 n3 n = more (n1 - 1) (n2 - 1) (n3 - 1) (n + n3 + n2 + n1)

main = do
	print("enter value for n1 (sous-paquet P1): ")
	input1 <- getLine
	let n1 = (read input1 :: Int)
	print("enter value for n2 (sous-paquet P2): ")
	input2 <- getLine
	let n2 = (read input2 :: Int)
	print("Melange " ++ show(n1) ++ " | " ++ show(n2) ++ " = " ++ show(melange2 n1 n2))
	print("enter value for n3 (sous-paquet P3): ")
	input3 <- getLine
	let n3 = (read input3 :: Int)
	print("Melange " ++ show(n1) ++ " | " ++ show(n2) ++ " | " ++ show(n3) ++ " = " ++ show(melange3 n1 n2 n3))