package modelo;

import java.math.BigDecimal;

public class Dinheiro {

    // Transforma string em centavos ( "70,50" vira 7050)
    public static long parse(String textoValor) throws Validação {

        //caso o valor não for informado
        if (textoValor == null || textoValor.trim().isEmpty()) {
            throw new Validação("Informe um valor.");
        }
        String limpo = textoValor.trim();

        //caso o valor tenha virgula, subistitui ela por ponto
        if (limpo.contains(",")) {
            limpo = limpo.replace(".", "").replace(",", ".");

        }

        //tenta mover a virgula duas casa pra direita e transoformar o valor em long(não pode conter ponto)
        try {
            return new BigDecimal(limpo).movePointRight(2).longValueExact();

        //caso falhe dará esse erro
        } catch (NumberFormatException | ArithmeticException e) {
            throw new Validação("Valor inválido (use até 2 casas decimais)");
        }
    }

    // Transforma centavos em string (7050 vira "R$ 70,50")
    public static String formatar(long centavos) {
        String sinal = centavos < 0 ? "-" : "";
        long absoluto = Math.abs(centavos);
        return String.format("%sR$ %d,%02d", sinal, absoluto / 100, absoluto % 100);
    }
}
