from sklearn import datasets
from sklearn.metrics import confusion_matrix
import numpy as np
import seaborn as sns; sns.set(style="ticks", color_codes=True)
iris =datasets.load_iris()
Ciris = np.c_[iris.data.reshape(len(iris.data), -1), iris.target.reshape(len(iris.target), -1)]
np.random.seed(987654321)
np.random.shuffle(Ciris)
shuffledIrisData = Ciris[ :, :iris.data.size//len(iris.data)].reshape(iris.data.shape)
shuffledIrisClass = Ciris[ :, iris.data.size//len(iris.data) :].reshape(iris.target.shape)
irisAffichage = sns.load_dataset("iris")
g = sns.pairplot(irisAffichage, hue="species")
part1Data, part2Data, part3Data = shuffledIrisData[:100,:], shuffledIrisData[100:130,:], shuffledIrisData[130:150,:]
part1Class, part2Class, part3Class = shuffledIrisClass[:100], shuffledIrisClass[100:130], shuffledIrisClass[130:150]
apriori = [np.count_nonzero(part1Class == 0)/100, np.count_nonzero(part1Class == 1)/100, np.count_nonzero(part1Class == 2)/100]
tab0, tab1, tab2, part1, part2, part3 = [], [], [], [], [], []
for i in range(0, 100):
    if part1Class[i] == 0:
        tab0.append(part1Data[i,:])
    if part1Class[i] == 1:
        tab1.append(part1Data[i,:])
    if part1Class[i] == 2:
        tab2.append(part1Data[i,:])
av0, av1, av2 = np.mean(tab0,axis=0), np.mean(tab1,axis=0), np.mean(tab2,axis=0)
gap0, gap1, gap2 = np.std(tab0, axis=0), np.std(tab1, axis=0), np.std(tab2, axis=0)

def test(sheet, data, answers, type):
    errors = 0
    for i in range(0, answers.size):
        proba0=(1/(gap0[type]*np.sqrt(2*np.pi)))*np.exp(-0.5*(((data[i][type]-av0[type])/gap0[type])**2))*apriori[0]
        proba1=(1/(gap1[type]*np.sqrt(2*np.pi)))*np.exp(-0.5*(((data[i][type]-av1[type])/gap1[type])**2))*apriori[1]
        proba2=(1/(gap2[type]*np.sqrt(2*np.pi)))*np.exp(-0.5*(((data[i][type]-av2[type])/gap2[type])**2))*apriori[2]

        if proba0 > proba1 and proba0 > proba1:
            type = 0
        if proba1 > proba0 and proba1 > proba2:
            type = 1
        if proba2 > proba0 and proba2 > proba1:
            type = 2
        sheet.append(type)
        
        if type != answers[i]:
            errors += 1
    return errors

print("There're ", test(part2, part2Data, part2Class, 2), "dev errors and ", test(part3, part3Data, part3Class, 2), " test errors and confusion matrix looks like\n", str(confusion_matrix(part3Class, part3))[1:-1])
