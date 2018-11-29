import numpy as np
import matplotlib.pyplot as plt
import scipy.fftpack
from scipy.io import wavfile
from numpy.fft import fft
from numpy.random import uniform
from math import sin, pi
import matplotlib.pyplot as plt
from numpy import array, arange, abs as np_abs
from numpy.fft import rfft, rfftfreq
def fonct(a, f0, fi, t):
    return a*np.sin(2*np.pi*f0*t)

def main():
    exercice1()
    exercice2()
    exercice3()
    return
def exercice1():
    n=np.arange(1,100)
    print(n)
    print(n.dtype.name)
    print(n.shape)
    t=n
    t=np.linspace(0,1,99)
    print(t)
    print(n)
    print(np.argwhere(t==0.49999999999999994))

    x1=fonct(1.5,10,0,t)
    x2=fonct(1.5,20,0,t)
    print(t)

    plt.plot(t,x1)
    plt.xlabel('temps (s)')
    plt.ylabel('amplitude (UA)')
    plt.show()

    #plt.plot(t,x2)
    #plt.show()

    plt.plot(t[5:15],x1[5:15])
    #plt.show()
    Fe=1000
    t=np.linspace(0,1,Fe)

    x3=fonct(1.5,10,0,t)
    plt.plot(t[50:150],x3[50:150])
    plt.show()

    x4=fonct(1.5,300,0,t)
    x5=fonct(1.5,666,0,t)
    x6=fonct(1.5,990,0,t)

    wavfile.write('3.wav',Fe,x3)
    wavfile.write('4.wav',Fe,x4)
    wavfile.write('5.wav',Fe,x5)
    wavfile.write('6.wav',Fe,x6)
def sumpart(ak, k, f0, t, bk):
    return 0+ak*np.cos(2*np.pi*k*f0*t)+bk*np.sin(2*np.pi*k*f0*t)

def exercice2():
    t10=np.linspace(0,1,10)
    t1000=np.linspace(0,1,1000)
    carre10=0
    carre1000=0
    trian10=0
    trian1000=0
    dent10=0
    dent1000=0

    ar=[
        [1,2,3,4,5,6,7,8,9],
        [1,0,1./3,0,1./5,0,1./7,0,1./9],
        [1,0,-1./9,0,1./25,0,-1./49,0,1./81],
        [1,-1./2,1./3,-1./4,1./5,-1./6,1./7,-1./8,1./9],
        [1,0,1./3,0,1./5,0,1./7,0,1./9],
        [1,0,-1./9,0,1./25,0,-1./49,0,1./81],
        [1,-1./2,1./3,-1./4,1./5,-1./6,1./7,-1./8,1./9]
        ]
    for i in ar[0]:
        if(i%2==0):
            carre10+=sumpart(0,i,10,t10,0)
            carre1000+=sumpart(0,i,1000,t1000,0)
            trian10+=sumpart(0,i,10,t10,0)
            trian1000+=sumpart(0,i,1000,t1000,0)
            dent10+=sumpart(0,i,10,t10,-1./i)
            dent1000+=sumpart(0,i,1000,t1000,-1./i)
        else:
            dent10+=sumpart(0,i,10,t10,1./i)
            dent1000+=sumpart(0,i,1000,t1000,1./i)
            carre10+=sumpart(0,i,10,t10,1./i)
            carre1000+=sumpart(0,i,1000,t1000,1./i)
            if(i==5 | i==9):
                trian10+=sumpart(0,i,10,t10,(1./i)*(1./i))
                trian1000+=sumpart(0,i,1000,t1000,(1./i)*(1./i))
            else:
                trian10+=sumpart(0,i,10,t10,-(1./i)*(1./i))
                trian1000+=sumpart(0,i,1000,t1000,-(1./i)*(1./i))
    plt.plot(t10,carre10)
    #plt.show()
    plt.plot(t1000,carre1000)
    plt.show()
    plt.plot(t10,trian10)
    #plt.show()
    plt.plot(t1000,trian1000)
    plt.show()
    plt.plot(t10,dent10)
    #plt.show()
    plt.plot(t1000,dent1000)
    plt.show()

    Fe=1000
    wavfile.write('carre10.wav',Fe,carre10)
    wavfile.write('carre1000.wav',Fe,carre1000)
    wavfile.write('trian10.wav',Fe,trian10)
    wavfile.write('trian1000.wav',Fe,trian1000)
    wavfile.write('dent10.wav',Fe,dent10)
    wavfile.write('dent1000.wav',Fe,dent1000)

def exercice3():

    FD = 8000
    N = 2000
    pure_sig = array([1.5*sin(2.*pi*160.0*t/FD) for t in range(N)])
    noise = uniform(-50.,50., N)
    sig = pure_sig + noise + 2.0 
    spectrum = rfft(sig)


    plt.plot(arange(N)/float(FD), sig) 
    plt.plot(arange(N)/float(FD), pure_sig, 'r')
    plt.xlabel(u'Time, s')
    plt.ylabel(u'Voltage, mV')
    plt.title(u'Noisy signal and tone 160 Hz')
    plt.grid(True)
    plt.show()

    plt.plot(rfftfreq(N, 1./FD), np_abs(spectrum)/N)
    plt.xlabel(u'Frequency, Hz')
    plt.ylabel(u'Pressure')
    plt.title(u'Spectrum')
    plt.grid(True)
    plt.show()
    plt.show()

    sample_rate = 1024
    dt = 1.0/sample_rate
    t = np.arange(sample_rate)*dt
    freq = 5
    amp = 1.0
    sine1 = amp*np.sin(2*np.pi*freq*t)
    sine2 = .5*np.sin(2*np.pi*15*t)
    sinsum = sine1 + sine2

    plt.plot(sine1, t,'g')
    plt.plot(sine2, t,'r')
    plt.plot(sinsum,t,'b')
    plt.show()

    
    
if __name__=="__main__":
    main()
