echo "Start client1"
start clientTest.bat machine1 login1 app1 journal_topic

echo "Start client2"
start clientTest.bat machine2 login2 app2 journal_topic

echo "Start controleur"
start controleur.bat journal_topic controler

echo "Start client3"
start clientTest.bat machine3 login3 app3 journal_topic

echo "Start client4"
start clientTest.bat machine4 login4 app4 journal_topic

echo "Start client5"
start clientTest.bat machine5 login5 app5 journal_topic
