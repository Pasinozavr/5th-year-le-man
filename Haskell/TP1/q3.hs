tr n = case n of
	1 -> "un"
	2 -> "deux"
	3 -> "trois"
	4 -> "qua e"
	5 -> "cinq"
	6 -> "six"
	7 -> "sept"
	8 -> "huit"
	9 -> "neuf"
	10 -> "dix"
	11 -> "onze"
	12 -> "douze"
	13 -> "treize"
	14 -> "quatorze"
	15 -> "quinze"
	16 -> "seize"
	20 -> "vingt"
	30 -> "trente"
	40 -> "quarante"
	50 -> "cinquante"
	60 -> "soixante"
	70 -> "soixante-dix"
	80 -> "quatre-vingt"
	90 -> "quatre-vingte-dix"
	100 -> "cent"
	_ -> if n>16 && n<20 then tr 10 ++ "-" ++ tr(n-10)
		else if n>20 && n<80 then tr((n `div` 10)*10) ++ " et " ++ tr(n `rem` 10)
			else tr((n `div` 10)*10) ++ "-" ++ tr(n `rem` 10)

main = print (tr 99)