package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.genericos.exception.AutorizacaoException;
import med.voll.api.domain.genericos.exception.ValidacaoConsultaException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class TratadorException {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Void> nullPointerException() {
		return ResponseEntity.notFound().build();
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDTO>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldErrorDTO> dtoList = e.getFieldErrors().stream().map(FieldErrorDTO::new).toList();

        return ResponseEntity.badRequest().body(dtoList);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> entityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErroGenericoDTO> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ErroGenericoDTO(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroGenericoDTO> httpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(new ErroGenericoDTO("Requisição precisa de um corpo"));
    }

    @ExceptionHandler(AutorizacaoException.class)
    public ResponseEntity<Void> autorizacaoException() {
        return ResponseEntity.status(403).build();
    }
    
    @ExceptionHandler(ValidacaoConsultaException.class)
    public ResponseEntity<ErroGenericoDTO> validacaoConsultaException(ValidacaoConsultaException e) {
    	return ResponseEntity.badRequest().body(new ErroGenericoDTO(e));
    }

    private record FieldErrorDTO(String campo, String mensagem) {
        public FieldErrorDTO(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    private record ErroGenericoDTO(String mensagem) {
        public ErroGenericoDTO(Exception e) {
            this(e.getMessage());
        }
    }
}
