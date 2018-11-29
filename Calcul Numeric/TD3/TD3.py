import numpy.random as rnd
import matplotlib.pyplot as plt
import numpy as np
from scipy.io import wavfile
import math
import random
import wave
def pre():
    L=[]
    N=100
    for i in range(N):
        L.append(rnd.random()*10)
    #print(L)
    y_uni=rnd.uniform(0,10,N)
    y_norm=rnd.normal(0,10,N)
    y_trian=rnd.triangular(0,4,9,N)
    y_exp=rnd.exponential(4,N)
    compare(N,y_uni)
    compare(N,y_norm)
    compare(N,y_trian)
    compare(N,y_exp)
    x=list(range(N))
    plt.plot(x,y_uni,x,y_norm,x,y_trian,x,y_exp)
    plt.show()
    wavfile.write('uni.wav',1000,y_uni)
    wavfile.write('norm.wav',1000,y_norm)
    wavfile.write('trian.wav',1000,y_trian)
    wavfile.write('exp.wav',1000,y_exp)
    wavfile.write('uni_stereo.wav',1000,y_uni/np.max(y_uni))
    wavfile.write('norm_stereo.wav',1000,y_norm/np.max(y_norm))
    wavfile.write('trian_stereo.wav',1000,y_trian/np.max(y_trian))
    wavfile.write('exp_stereo.wav',1000,y_exp/np.max(y_exp))

def aft():
    L = []
    x=np.linspace(-2,2,10000)
    for i in x:
        a = rnd.normal()
        L.append(a)
    L.sort()
    plt.plot(L,x)
    T=[]
    y=np.linspace(-2,2,100)
    for i in y:
        a = rnd.normal()
        T.append(a)
    T.sort()
    plt.plot(L,x, T, y)
    plt.show()
    L = [random.gauss(0.,1.) for i in range(100000)]
    plt.hist(L,100)
    x = [i/100. for i in range(-200,200)]
    y = [math.exp(-s**2 / 2)/(math.sqrt( 2 * math.pi)) for s in x]
    plt.plot(x,y)
    plt.show()

def compare(N, y):
    x=list(range(N))
    plt.plot(x,y)
    plt.plot(x, sorted(y))
    plt.show()
    plt.hist(y,20)
    plt.show()

pre()
    
fs, stereo=wavfile.read('td3.wav')

mono=stereo/np.max(stereo)

wavfile.write('mono.wav', 45000, mono)
mononoise=mono+rnd.uniform(-0.05,0.05)
wavfile.write('mono_noise.wav', 45000, mononoise)
mononoisenew=mono+random.gauss(1,0.01)
wavfile.write('mono_noise_new.wav', 45000, mononoise)


aft()
