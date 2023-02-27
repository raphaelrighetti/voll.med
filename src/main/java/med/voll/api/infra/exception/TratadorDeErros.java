package med.voll.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import med.voll.api.validacao.exception.ValidacaoException;
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

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarValidacaoException(ValidacaoException e) {
        return ResponseEntity.badRequest().body(new ExceptionGenericaDTO(e.getMessage()));
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

    private record ExceptionGenericaDTO(String mensagem) {
    }
}
