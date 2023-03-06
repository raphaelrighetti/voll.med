package med.voll.api.domain.genericos.exception;

public class AutorizacaoException extends RuntimeException {

    public AutorizacaoException() {
        super();
    }

    public AutorizacaoException(String mensagem) {
        super(mensagem);
    }
}
