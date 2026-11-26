import controlador.Controlador;
import persistencia.PersistenciaArquivo;
import visão.Ui;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador(new PersistenciaArquivo());
        new Ui(controlador);
    }
}