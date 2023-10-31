@echo off
setlocal enabledelayedexpansion
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
set /p input=Insira a classe principal: 
set "jarname=%input%.jar"
set "counter=1"
:LOOP
if exist "!jarname!" (
    set "jarname=%input%(!counter!).jar"
    set /A counter+=1
    goto :LOOP
)
echo Main-Class: %input%> bin\Manifest.txt

jar cfm "!jarname!" bin\Manifest.txt bin\*.class
echo O .jar foi criado com sucesso como "!jarname!"
del /f /q bin\Manifest.txt
del /f /q bin\*.class
rd /s /q bin
