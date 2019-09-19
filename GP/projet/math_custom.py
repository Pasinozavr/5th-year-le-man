import random as rnd

def easy():
	var1, var2 = rnd.randrange(100), rnd.randrange(100)
	return(var1,var2,var1+var2)

def medium():
	var1, var2 = rnd.randrange(100), rnd.randrange(100)
	return(var1,var2,var1-var2)

	
def hard():
	var1, var2 = rnd.randrange(100), rnd.randrange(100)
	return(var1,var2,var1*var2)
	