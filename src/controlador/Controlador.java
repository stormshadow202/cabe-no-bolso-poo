package controlador;

import java.io.File;
import java.util.Collection;
import java.util.List;
import modelo.Despesa;
import modelo.DespesaFixa;
import modelo.DespesaPorPessoa;
import modelo.Dinheiro;
import modelo.Participante;
import modelo.Planejamento;
import modelo.Validação;
import persistencia.Persistencia;

public class Controlador {
    private Planejamento planejamento = new Planejamento();
    private final Persistencia persistencia;

    public Controlador(Persistencia persistencia) {
        this.persistencia = persistencia;
    }

    // salva um novo participante
    public void salvarParticipante(String codigo, String nome, String orcamento) throws Validação {
        planejamento.salvarParticipante(lerCodigo(codigo), nome, Dinheiro.parse(orcamento));
    }

    // edita um participante ja existente
    // a diferença de salvar partipante é q lá eee recusa se tentar colocar um
    // codigo q já exista, enquanto esse deixa esceve por cima
    public void editarParticipante(String codigo, String nome, String orcamento) throws Validação {
        planejamento.editarParticipante(lerCodigo(codigo), nome, Dinheiro.parse(orcamento));
    }

    // salva uma despesa
    public void salvarDespesa(String descricao, String valor, boolean porPessoa, List<Participante> selecionados)
            throws Validação {
        planejamento.salvarDespesa(criarDespesa(descricao, valor, porPessoa, selecionados));
    }

    // edita despessa
    // mesma logica de editar participante
    public void editarDespesa(int indice, String descricao, String valor, boolean porPessoa,
            List<Participante> selecionados) throws Validação {
        planejamento.editarDespesa(indice, criarDespesa(descricao, valor, porPessoa, selecionados));
    }

    // exclui despesa
    public void excluirDespesa(int indice) throws Validação {
        planejamento.excluirDespesa(indice);
    }

    // salva em arquivo
    public void salvar(File arquivo) throws Validação {
        persistencia.salvar(planejamento, arquivo);
    }

    // carrega um arquivo
    public void carregar(File arquivo) throws Validação {
        Planejamento carregado = persistencia.carregar(arquivo);
        this.planejamento = carregado;
    }

    // retorna a coleção com todos os particpantes salvos em ordem
    public Collection<Participante> getParticipantes() {
        return planejamento.getParticipantes();
    }

    // retorna a lista de despesas
    public List<Despesa> getDespesas() {
        return planejamento.getDespesas();
    }

    // retorna o custo total das despesas em centavos
    public long getCustoTotal() {
        return planejamento.getCustoTotal();
    }

    // retorna quanto cada um dos partipantes vão pagar idividualmente
    public long getTotalIndividual(Participante p) {
        return planejamento.getTotalIndividual(p);
    }

    // retorna o excedente de dinheiro(caso tenha)
    public long getExcedente(Participante p) {
        return planejamento.getExcedente(p);
    }

    // monta a despesa depedendo se é o do tipo fixa ou por pessoa
    private Despesa criarDespesa(String descricao, String valor, boolean porPessoa,
            List<Participante> selecionados) throws Validação {
        long centavos = Dinheiro.parse(valor);
        if (porPessoa) {
            return new DespesaPorPessoa(descricao, centavos, selecionados);
        } else {
            return new DespesaFixa(descricao, centavos, selecionados);
        }
    }

    //converte a string em int
    //caso falhe dá o erro de codigo invalido
    private int lerCodigo(String texto) throws Validação {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new Validação("Código inválido.");
        }
    }
}