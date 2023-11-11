import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class GUI extends javax.swing.JFrame {

    private String entradaProcesso = null;
    private String entradaSaida = null;
    private List<Processo> entrada;
    private List<String> logConsole;
    private RoundRobin roundrobin = null;
    private Prioridade prioridade = null;
    private FIFO fifo = null;
    private SJF sjf = null;

    public GUI() {
        initComponents();
        textoErroArquivoSaida.setVisible(false);
        textoErroArquivoProcessos.setVisible(false);
        textoErroCiclo.setVisible(false);
        textoErroQuantum.setVisible(false);
        painelTabela.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JScrollPane jScrollPane4 = new JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        selecionaArquivo = new javax.swing.JButton();
        arquivoCaminho = new javax.swing.JTextField();
        selecionarAlgoritmo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        iniciarSimulacao = new javax.swing.JButton();
        entradaCiclo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        entradaQuantum = new javax.swing.JTextField();
        legendaQuantum = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        saidaCaminho = new javax.swing.JTextField();
        selecionaSaida = new javax.swing.JButton();
        textoErroQuantum = new javax.swing.JLabel();
        textoErroCiclo = new javax.swing.JLabel();
        textoErroArquivoProcessos = new javax.swing.JLabel();
        textoErroArquivoSaida = new javax.swing.JLabel();
        painelTabela = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador de Escalonamento");

        selecionaArquivo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        selecionaArquivo.setText("Procurar");
        selecionaArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionaArquivoActionPerformed(evt);
            }
        });

        arquivoCaminho.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        arquivoCaminho.setText("Selecione o arquivo de processos");

        selecionarAlgoritmo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        selecionarAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Round Robin", "Prioridade", "FIFO", "SJF" }));
        selecionarAlgoritmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarAlgoritmoActionPerformed(evt);
            }
        });

        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel1.setText("Escolonador:");

        iniciarSimulacao.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
        iniciarSimulacao.setText("Simular");
        iniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSimulacaoActionPerformed(evt);
            }
        });

        entradaCiclo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        entradaCiclo.setText("1");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Tempo de cada ciclo:");

        entradaQuantum.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        entradaQuantum.setText("4");

        legendaQuantum.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        legendaQuantum.setText("Quantum:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 36)); // NOI18N
        jLabel4.setText("Simulador de Escalonamento");

        saidaCaminho.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        saidaCaminho.setText("Selecione localizacao do arquivo de saida");

        selecionaSaida.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        selecionaSaida.setText("Procurar");
        selecionaSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionaSaidaActionPerformed(evt);
            }
        });

        textoErroQuantum.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        textoErroQuantum.setForeground(new java.awt.Color(255, 0, 0));
        textoErroQuantum.setText("jLabel5");

        textoErroCiclo.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        textoErroCiclo.setForeground(new java.awt.Color(255, 0, 0));
        textoErroCiclo.setText("jLabel5");

        textoErroArquivoProcessos.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        textoErroArquivoProcessos.setForeground(new java.awt.Color(255, 0, 0));
        textoErroArquivoProcessos.setText("jLabel5");

        textoErroArquivoSaida.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 12)); // NOI18N
        textoErroArquivoSaida.setForeground(new java.awt.Color(255, 0, 0));
        textoErroArquivoSaida.setText("jLabel5");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "PID", "Duracao", "Chegada", "IO", "Espera"
                }));
        jScrollPane2.setViewportView(tabela);

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
                painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelTabelaLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
        painelTabelaLayout.setVerticalGroup(
                painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelTabelaLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(122, 122, 122)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(selecionarAlgoritmo,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 110,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(58, 58, 58)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(entradaCiclo,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        76,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(textoErroCiclo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                203,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(legendaQuantum)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(entradaQuantum,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        73,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(textoErroQuantum,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                180,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                        .addComponent(textoErroArquivoSaida,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(saidaCaminho,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                513, Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(selecionaSaida))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                        .addComponent(textoErroArquivoProcessos,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(arquivoCaminho,
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                513, Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(selecionaArquivo))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(224, 224, 224)
                                                .addComponent(jLabel4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(185, 185, 185)
                                                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(333, 333, 333)
                                                .addComponent(iniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(155, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(selecionarAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(entradaCiclo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(entradaQuantum, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(legendaQuantum))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textoErroQuantum)
                                        .addComponent(textoErroCiclo))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(arquivoCaminho)
                                        .addComponent(selecionaArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoErroArquivoProcessos)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saidaCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selecionaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(textoErroArquivoSaida)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(iniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        jScrollPane4.setViewportView(getContentPane());

        // Fazendo o JScrollPane preencher toda a área do JFrame
        jScrollPane4.setPreferredSize(getPreferredSize());

        // Adicionando o JScrollPane ao JFrame
        setContentPane(jScrollPane4);
    }

    private void selecionaArquivoActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setDialogTitle("Procurar arquivos");
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto", "txt");
        filechooser.setFileFilter(filter);
        int result = filechooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            arquivoCaminho.setText(selectedFile.getAbsolutePath());
            entradaProcesso = selectedFile.getAbsolutePath();

        }
    }

    private void selecionaSaidaActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setDialogTitle("Procurar arquivos");
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = filechooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            saidaCaminho.setText(selectedFile.getAbsolutePath());
            entradaSaida = selectedFile.getAbsolutePath();
        }
    }

    private void selecionarAlgoritmoActionPerformed(java.awt.event.ActionEvent evt) {
        if (selecionarAlgoritmo.getSelectedIndex() == 0 || selecionarAlgoritmo.getSelectedIndex() == 4) {
            legendaQuantum.setVisible(true);
            entradaQuantum.setVisible(true);
        } else {
            legendaQuantum.setVisible(false);
            entradaQuantum.setVisible(false);
        }

    }

    private int parseInteger(String entrada) {
        return Integer.parseInt(entrada);
    }

    private boolean isIntegerPositivo(String entrada) {
        try {
            int parsedInteger = parseInteger(entrada);
            return parsedInteger >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setTabela(List<Processo> processos) {
        DefaultTableModel Tprocesso = (DefaultTableModel) tabela.getModel();
        Tprocesso.setRowCount(0);
        double processoDuracao = 0;
        double processoChegada = 0;
        double processoEspera = 0;
        for (Processo processo : processos) {
            Object[] dados = { processo.getPID(), processo.getTempoTotal(), processo.getChegada(), processo.imprimeIO(),
                    processo.getEspera() };
            processoChegada += processo.getChegada();
            processoDuracao += processo.getTempoTotal();
            processoEspera += processo.getEspera();
            Tprocesso.addRow(dados);
        }
        Object[] dados = { "Total", processoDuracao / processos.size(), processoChegada / processos.size(), "",
                processoEspera / processos.size() };
        Tprocesso.addRow(dados);
        painelTabela.setVisible(true);
        tabela.setVisible(true);

    }

    private void cancelarProcesso() {
        try {
            if (roundrobin != null && !roundrobin.isDone()) {
                roundrobin.cancel(true);
                roundrobin = null;
            } else if (prioridade != null && !prioridade.isDone()) {
                prioridade.cancel(true);
                prioridade = null;
            } else if (fifo != null && !fifo.isDone()) {
                fifo.cancel(true);
                fifo = null;
            } else if (sjf != null && !sjf.isDone()) {
                sjf.cancel(true);
                sjf = null;
            }
        } catch (Exception e) {

        }

    }

    public void reset() {
        roundrobin = null;
        prioridade = null;
        fifo = null;
        sjf = null;
        painelTabela.setVisible(false);
        tabela.setVisible(false);
        console.setText("");

    }

    private void simulacao() {
        cancelarProcesso();
        List<Processo> processos = new ArrayList();
        int time = parseInteger(entradaCiclo.getText().toString());
        String outputDirectory = entradaSaida;
        reset();
        while (roundrobin != null || prioridade != null || fifo != null || sjf != null) {
            System.out.println("aqui");
        }
        if (selecionarAlgoritmo.getSelectedIndex() == 0) {
            roundrobin = new RoundRobin(entrada, parseInteger(entradaQuantum.getText().toString()),
                    time, outputDirectory, this);
            roundrobin.execute();

        } else if (selecionarAlgoritmo.getSelectedIndex() == 1) {
            prioridade = new Prioridade(entrada, time, outputDirectory, this);
            prioridade.execute();

        } else if (selecionarAlgoritmo.getSelectedIndex() == 2) {
            fifo = new FIFO(entrada, time, outputDirectory, this);
            fifo.execute();

        } else if (selecionarAlgoritmo.getSelectedIndex() == 3) {
            sjf = new SJF(entrada, time, outputDirectory, this);
            sjf.execute();
        }

    }

    public void atualizaConsole(String texto) {
        console.append(texto + "\n");
        console.setCaretPosition(console.getDocument().getLength());
    }

    private void iniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {
        Leitor leitor = new Leitor();
        if (isIntegerPositivo(entradaCiclo.getText().toString()) == true) {
            textoErroCiclo.setVisible(false);
            if (entradaProcesso == null) {
                textoErroArquivoProcessos.setText("Selecione um arquivo");
                textoErroArquivoProcessos.setVisible(true);
            } else {
                textoErroArquivoProcessos.setVisible(false);
                if (entrada != null) {
                    entrada.clear();
                }
                entrada = leitor.Processos(entradaProcesso);
                if (entrada == null) {
                    textoErroArquivoProcessos.setText("Arquivo Invalido");
                    textoErroArquivoProcessos.setVisible(true);
                } else {
                    if (entradaSaida == null) {
                        textoErroArquivoSaida.setText("Selecione um diretorio");
                        textoErroArquivoSaida.setVisible(true);
                    } else {
                        textoErroArquivoSaida.setVisible(false);
                        if (selecionarAlgoritmo.getSelectedIndex() == 0
                                || selecionarAlgoritmo.getSelectedIndex() == 4) {

                            if (isIntegerPositivo(entradaQuantum.getText().toString()) == false) {
                                textoErroQuantum.setText("Insira um valor inteiro positivo ou 0");
                                textoErroQuantum.setVisible(true);
                            } else {
                                textoErroQuantum.setVisible(false);
                                textoErroArquivoProcessos.setVisible(false);
                                simulacao();
                            }
                        } else {
                            textoErroArquivoProcessos.setVisible(false);
                            simulacao();
                        }
                    }
                }

            }

        } else {
            textoErroCiclo.setText("Insira um valor inteiro positivo ou 0");
            textoErroCiclo.setVisible(true);
        }

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });

        
    }

    private javax.swing.JTextField arquivoCaminho;
    private javax.swing.JTextArea console;
    private javax.swing.JTextField entradaCiclo;
    private javax.swing.JTextField entradaQuantum;
    private javax.swing.JButton iniciarSimulacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel legendaQuantum;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JTextField saidaCaminho;
    private javax.swing.JButton selecionaArquivo;
    private javax.swing.JButton selecionaSaida;
    private javax.swing.JComboBox<String> selecionarAlgoritmo;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel textoErroArquivoProcessos;
    private javax.swing.JLabel textoErroArquivoSaida;
    private javax.swing.JLabel textoErroCiclo;
    private javax.swing.JLabel textoErroQuantum;
}
