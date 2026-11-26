
package visão;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import modelo.Participante;
import modelo.Dinheiro;
import modelo.Despesa;
import modelo.DespesaFixa;
import modelo.Validação;
import java.io.File;
import controlador.Controlador;

public class Ui implements ActionListener {

    private Controlador controlador;

    JFrame frame;
    JTextField textoCodigo, textoNome, textoOrcamento, textoDescrição, textoValor;
    JButton salvarParticipante, salvarDespesa, botaoCalcular, editarParticipante, botaoExcluir, editarDespesa,salvarArquivo, carregarArquivo, ajuda;
    JLabel labelCódigo, labelNome, labelOrçamento, labelDescricao, labelValor, labelTipo, labelParticipantes, labelDespesas;
    JScrollPane rolagemLista, rolagemDespesa;
    JComboBox<String> comboTipo;
    DefaultListModel<Participante> modeloLista;
    DefaultListModel<String> modeloDespesas;
    JList<Participante> listaParticipantes;
    JList<String> listaDespesas;

    public Ui(Controlador controlador) {
        this.controlador = controlador;
        frame = new JFrame("Cabe no bolso - Planejador de despesas compartilhadas ");
        textoCodigo = new JTextField();
        textoNome = new JTextField();
        textoOrcamento = new JTextField();
        salvarParticipante = new JButton("Salvar participante");
        labelCódigo = new JLabel("Código");
        labelNome = new JLabel("Nome");
        labelOrçamento = new JLabel("Orçamento");
        textoDescrição = new JTextField();
        textoValor = new JTextField();
        comboTipo = new JComboBox<>(new String[] { "Fixa", "Por pessoa" });
        modeloLista = new DefaultListModel<>();
        listaParticipantes = new JList<>(modeloLista);
        salvarDespesa = new JButton("Salvar despesa");
        modeloDespesas = new DefaultListModel<>();
        listaDespesas = new JList<>(modeloDespesas);
        rolagemLista = new JScrollPane(listaParticipantes);
        rolagemDespesa = new JScrollPane(listaDespesas);
        labelDescricao = new JLabel("Descrição");
        labelValor = new JLabel("Valor");
        labelTipo = new JLabel("Tipo");
        labelParticipantes = new JLabel("Participantes");
        botaoCalcular = new JButton("Calcular");
        editarParticipante = new JButton("Alterar participante");
        botaoExcluir = new JButton("Excluir despesa");
        editarDespesa = new JButton("Editar despesa");
        salvarArquivo = new JButton("Salvar arquivo");
        carregarArquivo = new JButton("Carregar arquivo");
        ajuda = new JButton("Ajuda");
        labelDespesas = new JLabel("Despesas");
        // criando os elementos da ui

        // CODIGO (Apenas numeros inteiros)
        textoCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        // NOME (Apenas letras e espaços)
        textoNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                // Permite letras (inclusive acentuadas), espaço e Backspace
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        // ORÇAMENTO (Apenas numeros, virgula e ponto)
        textoOrcamento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                // Permite numeros, virgula, ponto e Backspace
                if (!Character.isDigit(c) && c != ',' && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    evt.consume();
                }
            }
        });

        textoCodigo.setBounds(30, 100, 150, 30);
        labelCódigo.setBounds(30, 75, 150, 30);
        textoNome.setBounds(230, 100, 150, 30);
        labelNome.setBounds(230, 75, 150, 30);
        textoOrcamento.setBounds(430, 100, 150, 30);
        labelOrçamento.setBounds(430, 75, 150, 30);
        salvarParticipante.setBounds(630, 100, 150, 30);
        editarParticipante.setBounds(830, 100, 150, 30);

        rolagemLista.setBounds(30, 260, 350, 200);
        labelParticipantes.setBounds(30, 235, 150, 30); 
         
        comboTipo.setBounds(30, 180, 150, 30);
        labelTipo.setBounds(30, 155, 150, 30);    
        textoDescrição.setBounds(230, 180, 150, 30);
        labelDescricao.setBounds(230, 155, 150, 30);
        textoValor.setBounds(430, 180, 150, 30);
        labelValor.setBounds(430, 155, 150, 30);
        salvarDespesa.setBounds(630, 180, 150, 30);
        editarDespesa.setBounds(830, 180, 150, 30);   

        rolagemDespesa.setBounds(430, 260, 350, 200);
        labelDespesas.setBounds(430, 235, 150, 30);
        
        botaoCalcular.setBounds(830, 260, 150, 30);
        botaoExcluir.setBounds(830, 315, 150, 30);
        
        salvarArquivo.setBounds(830, 370, 150, 30);
        carregarArquivo.setBounds(830, 425, 150, 30);
        ajuda.setBounds(30, 30, 950, 30);
        // setando os tamanhos

        frame.add(textoCodigo);
        frame.add(textoNome);
        frame.add(textoOrcamento);
        frame.add(salvarParticipante);
        frame.add(labelCódigo);
        frame.add(labelNome);
        frame.add(labelOrçamento);
        frame.add(labelDescricao);
        frame.add(textoDescrição);
        frame.add(labelValor);
        frame.add(textoValor);
        frame.add(labelTipo);
        frame.add(comboTipo);
        frame.add(rolagemDespesa);
        frame.add(labelParticipantes);
        frame.add(rolagemLista);
        frame.add(salvarDespesa);
        frame.add(botaoCalcular);
        frame.add(editarParticipante);
        frame.add(botaoExcluir);
        frame.add(editarDespesa);
        frame.add(salvarArquivo);
        frame.add(carregarArquivo);
        frame.add(ajuda);
        frame.add(labelDespesas);
        // adicionando os elementos dentro do frame

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(1030, 525);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // configurando o frame

        salvarParticipante.addActionListener(this);
        salvarDespesa.addActionListener(ev -> criarDespesa());
        botaoCalcular.addActionListener(ev -> mostrarResumo());
        editarParticipante.addActionListener(ev -> editarParticipante());
        botaoExcluir.addActionListener(ev -> excluirDespesaSelecionada());
        editarDespesa.addActionListener(ev -> editarDespesaSelecionada());
        salvarArquivo.addActionListener(ev -> salvarEmArquivo());
        carregarArquivo.addActionListener(ev -> carregarDeArquivo());
        ajuda.addActionListener(ev -> mostrarAjuda());
        listaDespesas.addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()) {
                carregarDespesaSelecionada();
            }
        });
    }

    // metodo q pega os participante e os coloca na lista
    private void atualizarListaParticipantes() {
        modeloLista.clear();
        for (Participante p : controlador.getParticipantes()) {
            modeloLista.addElement(p);
        }
    }

    private void mostrarAjuda() {
        JOptionPane.showMessageDialog(frame,"Passo 1: Crie um participante, colocando código, nome e orçamento, e depois clique em salvar.\n"
                        + "Passo 2: Cadastre as despesas e escolha quem participa de cada uma.\n"
                        + "Passo 3: Clique em calcular para ver os totais.\n"
                        + "OBS.: SEGURE CTRL PARA SELECIONAR VÁRIOS.");
    }

    // metodo q limpa todos os textos
    private void limparCamposParticipante() {
        textoCodigo.setText("");
        textoNome.setText("");
        textoOrcamento.setText("");
        textoCodigo.requestFocus();
    }

    // salva o codigo, o participante e o seu orçamento
    public void actionPerformed(ActionEvent e) {
        try {
            controlador.salvarParticipante(textoCodigo.getText(), textoNome.getText(), textoOrcamento.getText());
            atualizarListaParticipantes();
            limparCamposParticipante();
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    // edita o partipante com base do seu codigo
    private void editarParticipante() {
        try {
            controlador.editarParticipante(textoCodigo.getText(), textoNome.getText(), textoOrcamento.getText());
            atualizarListaParticipantes();
            limparCamposParticipante();
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    // cria despesa
    private void criarDespesa() {
        try {
            controlador.salvarDespesa(textoDescrição.getText(), textoValor.getText(),
                    comboTipo.getSelectedIndex() == 1, listaParticipantes.getSelectedValuesList());
            atualizarPainelDespesa();
            limparCamposDespesa();
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    // edita a despesa
    private void editarDespesaSelecionada() {
        try {
            controlador.editarDespesa(listaDespesas.getSelectedIndex(), textoDescrição.getText(), textoValor.getText(),
                    comboTipo.getSelectedIndex() == 1, listaParticipantes.getSelectedValuesList());
            atualizarPainelDespesa();
            limparCamposDespesa();
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    // Preenche os campos da tela com os dados da despesa selecionada na lista
    private void carregarDespesaSelecionada() {
        int indice = listaDespesas.getSelectedIndex();
        if (indice < 0) {
            return;
        }
        Despesa d = controlador.getDespesas().get(indice);

        textoDescrição.setText(d.getDescricao());
        long c = d.getValorCentavos();
        textoValor.setText(String.format("%d,%02d", c / 100, c % 100));
        comboTipo.setSelectedIndex(d instanceof DespesaFixa ? 0 : 1);

        listaParticipantes.clearSelection();
        for (Participante p : d.getParticipantes()) {
            int posicao = modeloLista.indexOf(p);
            if (posicao >= 0) {
                listaParticipantes.addSelectionInterval(posicao, posicao);
            }
        }
    }

    // salva o planejamento em um arquivo
    private void salvarEmArquivo() {
        try {
            JFileChooser escolhedor = new JFileChooser();
            if (escolhedor.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
                return; // o usuário cancelou
            }
            File arquivo = escolhedor.getSelectedFile();
            if (!arquivo.getName().endsWith(".dat")) {
                arquivo = new File(arquivo.getPath() + ".dat");
            }
            controlador.salvar(arquivo);
            JOptionPane.showMessageDialog(frame, "Planejamento salvo");
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    private void carregarDeArquivo() {
        try {
            JFileChooser escolhedor = new JFileChooser();
            if (escolhedor.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            // só troca o planejamento em uso se o carregamento der certo
            controlador.carregar(escolhedor.getSelectedFile());

            atualizarListaParticipantes();
            atualizarPainelDespesa();
            limparCamposDespesa();
            JOptionPane.showMessageDialog(frame, "Planejamento carregado");
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    private void limparCamposDespesa() {
        textoDescrição.setText("");
        textoValor.setText("");
        listaParticipantes.clearSelection();
        listaDespesas.clearSelection();
        textoDescrição.requestFocus();
    }

    private void excluirDespesaSelecionada() {
        try {
            int indice = listaDespesas.getSelectedIndex(); // -1 se nada estiver selecionado
            if (indice >= 0) {
                int resposta = JOptionPane.showConfirmDialog(frame,
                        "Excluir a despesa selecionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (resposta != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            controlador.excluirDespesa(indice);
            atualizarPainelDespesa();
            limparCamposDespesa();
        } catch (Validação ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    private void atualizarPainelDespesa() {
        modeloDespesas.clear();
        for (Despesa d : controlador.getDespesas()) {
            StringBuilder sb = new StringBuilder();
            sb.append(d.getDescricao()).append(" - ")
                    .append(d.getClass().getSimpleName()).append(" - ")
                    .append(formatar(d.getValorCentavos())).append(" - ");
            for (Participante p : d.getParticipantes()) {
                sb.append(p.getNome()).append(" ");
            }
            modeloDespesas.addElement(sb.toString());
        }
    }

    // mostra o resumo do planejamento, com os totais de cada participante e se ele está dentro ou fora do orçamento
    private void mostrarResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Custo total: ").append(formatar(controlador.getCustoTotal())).append("\n\n");

        for (Participante p : controlador.getParticipantes()) {
            long total = controlador.getTotalIndividual(p);
            long excedente = controlador.getExcedente(p);

            sb.append(p.getNome()).append(" pagará: ")
                    .append(formatar(total)).append(" de ")
                    .append(formatar(p.getOrcamentoCentavos())).append(" - ")
                    .append(excedente == 0 ? "dentro do orçamento" : "excede em " + formatar(excedente))
                    .append("\n");
        }

        JTextArea area = new JTextArea(sb.toString(), 10, 40);
        area.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(area), "Resumo", JOptionPane.INFORMATION_MESSAGE);
    }

    private String formatar(long centavos) {
        return Dinheiro.formatar(centavos);
    }
}
