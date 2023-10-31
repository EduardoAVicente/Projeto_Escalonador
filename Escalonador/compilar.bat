del /f /q bin\*.class
for %%I in ("%CD%") do (
    set "caminho=%%~I"
)
if not exist "%~dp0bin" (
    mkdir "%~dp0bin"
)
cls
javac *.java
@echo off
move *.class bin > nul
