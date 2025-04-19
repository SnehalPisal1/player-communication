#!/bin/bash

# Use Maven's full path (adjust if different)
MAVEN_PATH="/c/Program Files/apache-maven-3.9.6/bin/mvn"

# Build
mvn clean package

# Run
java -cp target/players-communication-1.0-SNAPSHOT.jar com.example.Main