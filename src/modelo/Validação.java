package modelo;

public class Validação extends Exception {
    private static final long serialVersionUID = 1L;

    public Validação(String mensagem) {
        super(mensagem);
    }
}
// caso aconteça um erro lança essa exceção 