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

def select_word_question(difficulty):
    question1 = Question(1,"je ... une pomme","mange",["mange","bois"])
    question2 = Question(2,"je ... en cours","vais",["vais","vait","va"])
    question3 = Question(3,"cette question est ...","difficile",["facile","normale","difficile"])
    questions = [question1,question2,question3]
    for quest in questions:
        if quest.difficulty == difficulty:
    		return quest

def qfacile():
    q = select_word_question(1)
    return(q.question,q.answer,q.propositions)

def qmoyen():
    q = select_word_question(2)
    return(q.question,q.answer,q.propositions)

def qdiff():
    q = select_word_question(3)
    return(q.question,q.answer,q.propositions)

