#!/bin/sh
CLASSPATH=".:../appclient/"
java -cp $CLASSPATH -Djava.security.policy=server.policy engine.ComputeEngineFactory
