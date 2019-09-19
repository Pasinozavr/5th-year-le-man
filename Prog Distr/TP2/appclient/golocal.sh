#!/bin/sh
CLASSPATH=".:compute.jar"
java -cp $CLASSPATH -Djava.security.policy=client.policy client.Client localhost $1 $2 $3 $4 $5