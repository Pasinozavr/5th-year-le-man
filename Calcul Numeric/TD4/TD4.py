from math import *
import numpy as np
import matplotlib.pyplot as plt
import random as rnd
def rect(f,n):
    R=0
    t=0
    h=1/n
    while t<1: #on arrive à h
        R = R+h*f(t)
        t=t+h
    return R
def trap(f,n):
    T=0
    h=1/n
    L=np.linspace(0,1,n, endpoint=False) #sans le dernier element
    for t in L:
        T=T+(f(t)+f(t+h))*h/2
    return T

def simps(f,n):
    S=0
    h=1/n
    L=np.linspace(0,1,n, endpoint=False) #sans le dernier element
    for t in L:
        S=S+(f(t)+4*f(t+h/2)+f(t+h))*h/6
    return S

def f(x):
    return x**2+x/3
def fu(x):
    return 1/np.sqrt(1+x**2)
def ex1():
    for k in range(5):
        approxR=rect(f,10**k)
        approxT=trap(f,10**k)
        approxS=simps(f,10**k)
        print("k=%i, Ir=%f , It=%f , Is=%f" %(k,approxR,approxT,approxS))

    line=np.linspace(0,1,100,endpoint=False)
    res=[]
    for i in line:
        res.append(fu(i))
        
    plt.plot(line,res,color='red')
    plt.scatter(line,res,c='blue')
    plt.show()

def game():
    n=rnd.randint(0,100)
    nb=n+1
    while nb!=n:
        num = input("try:")
        nb=int(num)
        if(nb<n):
            print("try more")
        if(nb>n):
            print("try less")
        if(nb==n):
            print("yes!")
            break

def autogame():
    n=rnd.randint(0,100)
    ml, mr, num=0, 100,0
    m=rnd.randint(ml,mr)
    while m!=n:
        if(m<n):
            print("m=",m, "try more")
            ml=m
            m=rnd.randint(ml,mr)
            num+=1
        if(m>n):
            print("m=",m, "try less")
            mr=m
            m=rnd.randint(ml,mr)
            num+=1
        if(m==n):
            print("m=", m, " -> yes on try num", num)
            break

def f(x):
    return (np.log(x) + x**2)

def test():
    i=20
    while i>0:
        #print(i)
        a,b=rnd.randint(0,100),rnd.randint(0,100)
        if(not((a>b and f(a)>f(b)) or (a<b and f(a)<f(b)))):
            break
        else:
            i-=1
    if(i==1):
        print("bad func")
    else:
        print("good func")
        
def mon(a, b):
    return f(a)*f(b)

def diho(e):
    num,a,b=0,0,100
    while b-a>e:
        m=(a+b)/2
        if f(m)*f(a)>=0:
            a=m
            num+=1
        else:
            b=m
            num+=1
    print("sol ", m," found at try num ", num)
    
def ex2():
    game()
    autogame()
    test()
    diho(0.001)

def td():
    ex2()
    ex2()
    ex3()
    
def ex3():
    n=0.1+0.1+0.1
    m=0.3
    if(n==m):
        print("0.1+0.1+0.1==0.3")
    else:
        print("0.1+0.1+0.1!=0.3")

    c0=0*10**0+1*10**(-1)+2*10**(-2)+5*10**(-3)
    c1=0*10**0+1*10**(-1)
    c2=0*10**0+3*10**(-1)
    d=0.1
    print("0.125=",c0)
    print("0.1=",c1)
    print("0.3=",c2)
    print("0.1=%.2f and 0.1=%.1f and 0.1=%.10f" %(d,d,d))

td()














#ex1()
        
