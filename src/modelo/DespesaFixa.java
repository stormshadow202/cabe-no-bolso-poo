package modelo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DespesaFixa extends Despesa {
    private static final long serialVersionUID = 1L;

    public DespesaFixa(String descricao, long valorCentavos, List<Participante> participantes)
            throws Validação {
        super(descricao, valorCentavos, participantes);
    }

    @Override
    public Map<Integer, Long> ratear() {

        // pega a lista de participantes q iriam paga a despesa
        List<Participante> lista = getParticipantes();

        // divide os centavos do valor da despesa e divide pelo tanto de participantes
        long base = getValorCentavos() / lista.size();

        // pega o resto da divisão de cima(se houver)
        long resto = getValorCentavos() % lista.size();

        // se houver resto, adiciona para os participantes na ordem de seus codigos
        Map<Integer, Long> rateio = new TreeMap<>();
        for (int i = 0; i < lista.size(); i++) {            // vai continuar enquanto o i for menor q o numero de participantes
            long parte = base + (i < resto ? 1 : 0);
            rateio.put(lista.get(i).getCodigo(), parte);
        }
        return rateio;
    }
}