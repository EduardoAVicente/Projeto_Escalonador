@echo off
setlocal enabledelayedexpansion

rem Lista os arquivos .bat no diretório atual, exceto teste.bat e compilar.bat
set /a count=0
for %%f in (*.bat) do (
    if not "%%~nxf"=="executar.bat" (
        if not "%%~nxf"=="compilar.bat" (
            set /a count+=1
            for %%A in ("%%f") do (
                echo !count!. %%~nA
            )
        )
    )
)

rem Pede ao usuário para inserir o número correspondente ao arquivo .bat desejado
set /p input=Insira o numero do algoritmo para executar: 

rem Executa o arquivo .bat correspondente ao número inserido
set /a i=0
for %%f in (*.bat) do (
    if not "%%~nxf"=="executar.bat" (
        if not "%%~nxf"=="compilar.bat" (
            set /a i+=1
            if !i! equ %input% (
                call "%%f"
                exit /b
            )
        )
    )
)

rem Se o número inserido não corresponder a nenhum arquivo .bat, exibe uma mensagem de erro
echo Numero invalido. Nao foi possivel encontrar o arquivo correspondente.
pause
