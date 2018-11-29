ack 0 n = n + 1
ack m 0 = ack (m-1) 1
ack m n = ack(m - 1) (ack m (n - 1))

mcc n =
	if n > 100 then n - 10
		else mcc(mcc(n + 11))


main = do
	print("enter value for m (ackermann): ")
	input1 <- getLine
	let m = (read input1 :: Int)
	print("et n : ")
	input2 <- getLine
	let n = (read input2 :: Int)
	print("Fonction d'Ackermann pour ils = " ++ show(ack m n))
	print("enter value for z (mccarthy) : ")
	input3 <- getLine
	let z = (read input3 :: Int)
	print("Fonction de McCarthy pour " ++ show(z) ++ " = " ++ show(mcc z))