exemple_6_5 (i, j) = 
	if 1 <= i && i <= 6 && 1 <= j && j <= 5
		then
			(True, 2 * i + j)
			else
				(False, 0)

identite_4_4 (i, j) =
	if 1 <= i && i <= 4 && 1 <= j && j <= 4
		then
			(True, if i == j then 1 else 0)
			else
				(False, 0)

dim_aux legal index = if legal index then dim_aux legal (index + 1) else index
legali matr i = fst (matr (i, 1))
legalj matr j = fst (matr (1, j))
dims matr = ((dim_aux (legali matr) 1) -1, (dim_aux (legalj matr) 1) -1)

coef matr(i, j) = snd(matr(i,j))
add_matrix matA matB =
  let dimsA = dims matA in
  let dimsB = dims matB in
  if dims(matA) == dims(matB)
    then \i j -> if 1 <= i && i <= fst (dimsA) && 1 <= j && j <= snd (dimsA) 
      then (True, coef matA(i, j) + coef matB(i, j)) 
      else (False, 0)
    else error "Matrixes have different dimentions"



main = do
	print("size of 6x5 exemple = " ++ show(dims exemple_6_5))
	print("size of 4x4 exemple = " ++ show(dims identite_4_4))
	print("add 6x5 + 6x5")
	let good = add_matrix exemple_6_5 exemple_6_5
	print("element (1,1) = " ++ show(good 1 1))
	print("try to add 6x5 + 4x4")
	let bad = add_matrix exemple_6_5 identite_4_4
	print("element (1,1) = " ++ show(bad 1 1))
	
