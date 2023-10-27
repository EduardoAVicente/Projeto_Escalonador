@echo off
del /f /q bin\*.class
for %%I in ("%CD%") do (
    set "caminho=%%~I"
)
if not exist "%~dp0bin" (
    mkdir "%~dp0bin"
)
javac *.java    
move *.class bin
cls
java -classpath %caminho%/bin MainFIFO
