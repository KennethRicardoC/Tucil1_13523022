#!/bin/bash
mkdir -p bin
/usr/bin/find ./src/program -name "*.java" > sources.txt
javac -d bin -sourcepath /src/program @sources.txt
rm sources.txt
echo "Seluruh program berhasil di Compile!"