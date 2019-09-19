#!/bin/sh

echo "Start client1"
sh ./clientTest.sh machine1 login1 app1 journal_topic &

echo "Start client2"
sh ./clientTest.sh machine2 login2 app2 journal_topic &

echo "Start controleur"
sh ./controleur.sh journal_topic controler &

echo "Start client3"
sh ./clientTest.sh machine3 login3 app3 journal_topic &

echo "Start client4"
sh ./clientTest.sh machine4 login4 app4 journal_topic &

echo "Start client5"
sh ./clientTest.sh machine5 login5 app5 journal_topic &
