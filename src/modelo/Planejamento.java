package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class Planejamento implements Serializable {
    private static final long serialVersionUID = 1L;

    //busca por codigo e já mantem os participantes ordenados por codigo
    private TreeMap<Integer, Participante> participantes = new TreeMap<>();
    private ArrayList<Despesa> despesas = new ArrayList<>();

    //salva um participante
    //caso o codigo sejá repetivo, dará esse erro
    public void salvarParticipante(int codigo, String nome, long orcamento) throws Validação {
        if (participantes.containsKey(codigo)) {
            throw new Validação("Já existe um participante com esse código.");
        }
        participantes.put(codigo, new Participante(codigo, nome, orcamento));
    }

    // Altera nome e orçamento de um participante já salvo
    //caso não exista esse participante com esse codigo, dará esse erro
    public void editarParticipante(int codigo, String novoNome, long novoOrcamento) throws Validação {
        Participante p = participantes.get(codigo);
        if (p == null) {
            throw new Validação("Participante não encontrado.");
        }
        p.alterar(novoNome, novoOrcamento);
    }

    
    public Collection<Participante> getParticipantes() {
        return java.util.Collections.unmodifiableCollection(participantes.values());
    }

    //salva a despesa
    public void salvarDespesa(Despesa d) throws Validação {
        validarParticipantes(d);
        despesas.add(d);
    }

    //
    public void editarDespesa(int indice, Despesa nova) throws Validação {
        if (indice < 0 || indice >= despesas.size()) {
            throw new Validação("Selecione uma despesa.");
        }
        validarParticipantes(nova);
        despesas.set(indice, nova);
    }

    // Confere se todos os participantes da despesa estão salvas
    private void validarParticipantes(Despesa d) throws Validação {
        for (Participante p : d.getParticipantes()) {
            if (!participantes.containsKey(p.getCodigo())) {
                throw new Validação("Participante não cadastrado: " + p.getNome());
            }
        }
    }


    public List<Despesa> getDespesas() {
        return new ArrayList<>(despesas);
    }

    public long getCustoTotal() {
        long soma = 0;
        for (Despesa d : despesas) soma += d.getCustoTotal();
        return soma;
    }

    public long getTotalIndividual(Participante p) {
        long soma = 0;
        for (Despesa d : despesas) soma += d.getContribuicao(p);
        return soma;
    }

    public long getExcedente(Participante p) {
        return Math.max(0, getTotalIndividual(p) - p.getOrcamentoCentavos());
    }

    public void excluirDespesa(int indice) throws Validação {
        if (indice < 0 || indice >= despesas.size()) {
            throw new Validação("Selecione uma despesa.");
        }
        despesas.remove(indice);
    }    
}