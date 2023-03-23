package med.voll.api.domain.genericos.exception;

public class ValidacaoConsultaException extends RuntimeException {

	public ValidacaoConsultaException() {
		super();
	}
	
	public ValidacaoConsultaException(String mensagem) {
		super(mensagem);
	}
}
