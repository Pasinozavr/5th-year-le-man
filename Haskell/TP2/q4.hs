mult x y =
	if x == 0 && y >= 0 then 0
		else if x > 0 && y >= 0 && (x `mod` 2) == 0 then (x `div` 2) * (2 * y)
			else (((x-1) `div` 2) * (2 * y)) + y

main = do
	print("enter value for x : ")
	input1 <- getLine
	let x = (read input1 :: Int)
	print("et y : ")
	input2 <- getLine
	let y = (read input2 :: Int)
	print("Ils multiplication russe = " ++ show(mult x y))