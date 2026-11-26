package modelo;

import java.io.Serializable;

public class Participante implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String nome;
    private long orcamentoCentavos;

    //construtor de um novo participante
    public Participante(int codigo, String nome, long orcamentoCentavos) throws Validação {
        validarNome(nome);
        validarOrcamento(orcamentoCentavos);
        this.codigo = codigo;
        this.nome = nome.trim();
        this.orcamentoCentavos = orcamentoCentavos;
    }

    // Altera nome e orçamento do participante
    public void alterar(String novoNome, long novoOrcamento) throws Validação {
        validarNome(novoNome);
        validarOrcamento(novoOrcamento);
        this.nome = novoNome.trim();
        this.orcamentoCentavos = novoOrcamento;
    }

    //se o nome for vazio, dará esse erro
    private static void validarNome(String nome) throws Validação {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Validação("O nome não pode ser vazio.");
        }
    }

    //se o orçamento for negativo, dará esse erro
    private static void validarOrcamento(long centavos) throws Validação {
        if (centavos < 0) {
            throw new Validação("O orçamento não pode ser negativo.");
        }
    }

    //retorna o codigo
    public int getCodigo() {
        return codigo;
    }

    //retorna o nome
    public String getNome() {
        return nome;
    }


    //retorna o orçamento em centavos
    public long getOrcamentoCentavos() {
        return orcamentoCentavos;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - " + Dinheiro.formatar(orcamentoCentavos);
    }
}