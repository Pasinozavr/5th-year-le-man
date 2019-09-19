import random as rnd

class Question:
    def __init__(self, difficulty, question, answer, propositions):
        self.difficulty=difficulty
        self.question = question
        self.answer = answer
        self.propositions = propositions

    def difficulty():
        return self.difficulty

    def question():
        return self.question

    def answer():
        return self.answer

    def propositions():
        return self.propositions



def select_aide_question():
    question1 = Question(1,"la personne dont je m'occupe fait un malaise, que dois-je faire ?","dormir",["l'aider","dormir","crier"])

    return question1
    
def q():
    a = select_aide_question()
    return(a.question, a.answer, a.propositions)

