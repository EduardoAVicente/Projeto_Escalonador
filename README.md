# Projeto_Escalonador

Simulador de Escalonamento
Para realizar a execução da simulação de escalonamento, é possível utilizar arquivos de atalho .bat, conforme exemplificado abaixo: Observação: Todos os arquivos ".class" serão armazenados na pasta "bin", criada automaticamente pelo arquivo ".bat" para evitar poluição visual na pasta "Escalonador".
Requisitos:
1.	Estar dentro da pasta “Escalonador”.
2.	Nenhum dos diretório do caminho dos arquivos .bat pode conter o caractere espaço “ “.
3.	Para checar o caminho até os arquivos use o comado “pwd” no PowerShell ou “echo %cd%” no CMD.
Comandos de execuções:
1.	Executar programa completo com GUI:
.\GUI.bat

2.	Executar diretamente o algoritmo RoundRobin:
.\RoundRobin.bat

3.	Executar diretamente o algoritmo FIFO:
.\FIFO

4.	Executar diretamente o algoritmo SJF:
.\SJF

5.	Executar diretamente o algoritmo Prioridade:
.\Prioridade

Execução alternativa
Caso seu computador não aceite os comandos acima é possível realizar execução manual através de arquivos “.java”.
1.	Executar programa completo com GUI:
MainGUI.java

2.	Executar o algoritmo RoundRobin:
MainRoundRobin.java

3.	Executar o algoritmo FIFO:
MainFIFO.java

4.	Executar o algoritmo SJF:
MainSJF.java

5.	Executar o algoritmo Prioridade:
MainPrioridade.java


É importante observar que a execução direta dos algoritmos não possibilita a visualização da tabela de processos, e a velocidade de execução ocorre em tempo real. O arquivo utilizado durante a execução direta é denominado 'arquivoProcessos.txt', localizado na pasta 'Escalonador'.



















Execução do programa completo pela GUI
1.	Ao realizar a execução pela GUI através do “.\GUI.bat”, o usuário irá se deparar com esta página.

 










2.	A tela de saída será similar a imagem abaixo. OBS: Talvez seja necessário rolar a página para baixo para ver todas as informações.
 

 

Comandos adicionais:
1.	Limpeza de arquivos temporários ou desnecessários:
.\Limpeza.bat

2.	Copilar programa (útil para identificar erros ou problemas de compilação) :
.\Compilar

Especificidade de algoritmos:
Muitos dos algoritmos disponíveis carecem de distinções significativas entre si, operando de maneira genérica, com a exceção do algoritmo de Prioridade, que concede prioridade a processos com menor volume de I/O.
