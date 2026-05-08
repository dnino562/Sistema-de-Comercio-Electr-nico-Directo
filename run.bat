@echo off
REM MediMarket - Script de ejecucion para Windows
REM Requiere Java 17 o superior

cd /d "%~dp0"

if exist MediMarket.jar (
    echo Iniciando MediMarket desde JAR...
    java -jar MediMarket.jar
) else if exist bin (
    echo Iniciando MediMarket desde clases compiladas...
    java -cp bin com.medimarket.Main
) else (
    echo Compilando proyecto...
    if not exist bin mkdir bin
    dir /s /b src\*.java > sources.txt
    javac -d bin -sourcepath src @sources.txt
    del sources.txt
    echo Iniciando MediMarket...
    java -cp bin com.medimarket.Main
)
pause
