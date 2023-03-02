package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDTO>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldErrorDTO> dtoList = e.getFieldErrors().stream().map(FieldErrorDTO::new).toList();

        return ResponseEntity.badRequest().body(dtoList);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundException(EntityNotFoundException e) {
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
