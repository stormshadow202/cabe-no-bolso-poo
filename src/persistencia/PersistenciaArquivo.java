package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelo.Planejamento;
import modelo.Validação;

public class PersistenciaArquivo implements Persistencia {

    @Override
    public void salvar(Planejamento planejamento, File arquivo) throws Validação {
        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            saida.writeObject(planejamento);
        } catch (IOException e) {
            throw new Validação("Falha ao salvar o arquivo: " + e.getMessage());
        }
    }

    @Override
    public Planejamento carregar(File arquivo) throws Validação {
        if (arquivo == null || !arquivo.exists()) {
            throw new Validação("Arquivo não encontrado.");
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Planejamento) entrada.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new Validação("Arquivo inválido ou corrompido.");
        }
    }
}
