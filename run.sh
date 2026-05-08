#!/bin/bash
# MediMarket - Script de ejecucion para Linux/macOS
# Requiere Java 17 o superior

cd "$(dirname "$0")"

if [ -f "MediMarket.jar" ]; then
    echo "Iniciando MediMarket desde JAR..."
    java -jar MediMarket.jar
elif [ -d "bin" ]; then
    echo "Iniciando MediMarket desde clases compiladas..."
    java -cp bin com.medimarket.Main
else
    echo "Compilando proyecto..."
    mkdir -p bin
    javac -d bin -sourcepath src $(find src -name "*.java")
    echo "Iniciando MediMarket..."
    java -cp bin com.medimarket.Main
fi
