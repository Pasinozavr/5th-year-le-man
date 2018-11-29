somme 1 = 1
somme n =
	n + somme (n-1)

main :: IO()
main = do
	putStrLn "enter value for x: "
	input1 <- getLine
	let x = (read input1 :: Int)
	print (somme x)