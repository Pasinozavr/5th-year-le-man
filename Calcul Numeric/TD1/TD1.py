import shutil

def main():
    #exercice2()
    #exercice31()
    #exercice32()
    #afficherligne("I am function, wow")
    #print(stokerligne("Alex; a l e x", stokerligne("Pasha; p a s h a", exercice32())))
    #afficherpron("play", "synpaflex-pronunciation-dictionary.txt")
    affichermots(" k u l e", "synpaflex-pronunciation-dictionary.txt")
    return

def affichermots(p, dicPron):
    n=0
    f=open(dicPron, "r", encoding="utf8")
    for line in f:
        if p == line.split(";")[1]:
            n=n+1
            print(str(n)+": "+line.split(';')[0])
    
def afficherpron(m, dicPron):
    f=open(dicPron, "r", encoding="utf8")
    for line in f:
        if m in line:
            print(m+" is reading like "+line.split(';')[2])

def afficherligne(line):
    print(line)
    
def stokerligne(line, dicPron):
    dicPron=dicPron+"\n"+line
    return dicPron
    
def exercice2():
    print("Exercice 2.1")
    var=input("Print anything and I'll reply it: ")
    print("It's what you writed: ", var)
    print("Exercice 2.2")
    f=open("fortd1.txt", "a", encoding="utf8")
    f.write("M1: première année de master\n")
    f=open("fortd1.txt", "r", encoding="utf8")
    for line in f:
        print(line)
    f.close()
    
def exercice31():
    print("Exercice 3")
    n=int(input("Number of lines to show: "))
    f=open("synpaflex-pronunciation-dictionary.txt", "r", encoding="utf8")
    i=1
    while(i<=n):
        print(f.readline())
        i=i+1
    f.close()
    
def exercice32():
    f=open("synpaflex-pronunciation-dictionary.txt", "r", encoding="utf8")
    s=f.read()
    s=s.strip("\n")
    #print(s)
    return s
    
if __name__=="__main__":
    main()

