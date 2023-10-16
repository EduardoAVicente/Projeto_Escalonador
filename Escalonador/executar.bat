@echo off
for %%I in ("%CD%") do (
    set "caminho=%%~I"
)
echo %caminho%
if not exist "%~dp0bin" (
    mkdir "%~dp0bin"
)
javac *.java    
move *.class bin
cls
java -classpath %caminho%/bin Main.java