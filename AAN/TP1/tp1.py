import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

def betas(X, Y):
    mean_x = np.mean(X)
    mean_y = np.mean(Y)
    m = len(X)
    numer = 0
    denom = 0
    for i in range(m):
        numer += (X[i] - mean_x) * (Y[i] - mean_y)
        denom += (X[i] - mean_x) ** 2
    b1 = numer / denom
    b0 = mean_y - (b1 * mean_x)
    print('b1 ', b1, 'b0 ', b0)
    return b0, b1

def polyfit(x, y, degree):
    results = {}
    coeffs = np.polyfit(x, y, degree)
    p = np.poly1d(coeffs)
    yhat = p(x)
    ybar = np.sum(y)/len(y)
    ssreg = np.sum((yhat-ybar)**2)
    sstot = np.sum((y - ybar)**2)
    return ssreg / sstot

def rmse(pred, y):
    return np.sqrt(np.mean((pred-y)**2))

    
houses_train = pd.read_csv('train.csv')
print(houses_train.head(5))

houses_test= pd.read_csv('test.csv')
print(houses_test.head(5))

houses_train.get_dtype_counts()
print(houses_train.describe())

df=pd.DataFrame(houses_train)
corrforsales=df.corr().tail(1).T
corrforsales = corrforsales[corrforsales['SalePrice']!=corrforsales['SalePrice'].max()]
print(corrforsales)
mostcorr1=corrforsales['SalePrice'].max()

corrforsales = corrforsales[corrforsales['SalePrice']!=corrforsales['SalePrice'].max()]
mostcorr2=corrforsales['SalePrice'].max()
print('two most correlated variables: ', mostcorr1, mostcorr2)


df.plot(kind='scatter', x='OverallQual',y='SalePrice', color='red')
plt.title("Distribution of Sale Price as function of OverallQual")
plt.ylabel("Sale Price (in dollar)")

df.plot(kind='scatter', x='GrLivArea',y='SalePrice', color='green')
plt.title("Distribution of Sale Price as function of GrLivArea")
plt.ylabel("Sale Price (in dollar)")
plt.show()

x1=houses_train['OverallQual'].values
x2=houses_train['GrLivArea'].values
y=houses_train['SalePrice'].values
print('for OverallQual')
b10, b11 = betas(x1,y)
print('R2', polyfit(x1,y,2))
print('for GrLivArea')
b20, b21 = betas(x2,y)
print('R2', polyfit(x2,y,2))

predict1=[]
predict2=[]

for d in x1:
    predict1.append(b10+b11*d)

for d in x2:
    predict2.append(b20+b21*d)

plt.plot(x1, predict1, x1, y, 'ro')
plt.show()

plt.plot(x2, predict2, x2, y, 'ro')
plt.show()

monrmse1=rmse(predict1, y)
monrmse2=rmse(predict2, y)
print('my RMSEs: ', monrmse1, monrmse2)


