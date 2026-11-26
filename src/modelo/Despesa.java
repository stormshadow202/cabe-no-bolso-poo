package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private long valorCentavos;
    private List<Participante> participantes;


    //construtor das despesas
    public Despesa(String descricao, long valorCentavos, List<Participante> participantes)
            throws Validação {

        //caso a despesa não tenha descrição, dará esse erro
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Validação("A descrição não pode ser vazia.");
        }

        //caso o o valor da despesa for negativo, dará esse erro
        if (valorCentavos <= 0) {
            throw new Validação("O valor da despesa deve ser maior que zero.");
        }

        //caso não tiver selecionado nenhum participante, dará esse erro
        if (participantes == null || participantes.isEmpty()) {
            throw new Validação("Selecione pelo menos um participante.");
        }

        this.descricao = descricao.trim();
        this.valorCentavos = valorCentavos;
        this.participantes = new ArrayList<>(participantes);          
        this.participantes.sort(Comparator.comparingInt(Participante::getCodigo)); 
    }


    public abstract Map<Integer, Long> ratear();

    public long getContribuicao(Participante p) {
        return ratear().getOrDefault(p.getCodigo(), 0L);
    }

    public long getCustoTotal() {
        long soma = 0;
        for (long parte : ratear().values()) soma += parte;
        return soma;
    }

    public String getDescricao() { return descricao; }
    public long getValorCentavos() { return valorCentavos; }
    public List<Participante> getParticipantes() { return new ArrayList<>(participantes); }
}