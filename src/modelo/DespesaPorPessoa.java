package modelo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DespesaPorPessoa extends Despesa {
    private static final long serialVersionUID = 1L;

    public DespesaPorPessoa(String descricao, long valorCentavos, List<Participante> participantes)
            throws Validação {
        super(descricao, valorCentavos, participantes);
    }

    @Override
    public Map<Integer, Long> ratear() {
        Map<Integer, Long> rateio = new TreeMap<>();
        for (Participante p : getParticipantes()) {             // vai continuar enquanto o i for menor q o numero de participantes
            rateio.put(p.getCodigo(), getValorCentavos());      //adiciona o msm valor pra todos os participantes da despesa
        }
        return rateio;
    }
}