package persistencia;

import java.io.File;
import modelo.Planejamento;
import modelo.Validação;

public interface Persistencia {
    void salvar(Planejamento planejamento, File arquivo) throws Validação;

    Planejamento carregar(File arquivo) throws Validação;
}