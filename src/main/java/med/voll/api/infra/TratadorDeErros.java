package med.voll.api.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();
        List<CampoInvalidoDTO> camposInvalidos = erros.stream().map(CampoInvalidoDTO::new).toList();

        return ResponseEntity.badRequest().body(camposInvalidos);
    }

    private record CampoInvalidoDTO(String campo, String mensagem) {
        public CampoInvalidoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
