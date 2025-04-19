#!/bin/bash

# Compile the project using Maven
mvn clean package

# Check if argument is provided
if [ -z "$1" ]; then
    echo "Usage: ./run-multi.sh [initiator|responder]"
    exit 1
fi

# Run the application in multi-process mode
if [ "$1" = "initiator" ]; then
    java -cp target/players-communication-1.0-SNAPSHOT.jar com.example.Main multi initiator
elif [ "$1" = "responder" ]; then
    java -cp target/players-communication-1.0-SNAPSHOT.jar com.example.Main multi responder
else
    echo "Invalid argument. Use 'initiator' or 'responder'"
    exit 1
fi