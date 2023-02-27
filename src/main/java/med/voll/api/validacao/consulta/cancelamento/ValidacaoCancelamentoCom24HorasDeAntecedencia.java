package med.voll.api.validacao.consulta.cancelamento;

import med.voll.api.dto.consulta.ConsultaDTOCancelamento;
import med.voll.api.entity.consulta.Consulta;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.validacao.consulta.abstracao.ValidacaoCancelamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoCancelamentoCom24HorasDeAntecedencia implements ValidacaoCancelamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(ConsultaDTOCancelamento dados) {
        Consulta consulta = repository.getReferenceById(dados.id());

        if (LocalDateTime.now().plusHours(24).isAfter(consulta.getData())) {
            throw new ValidacaoException("Consultas só podem ser canceladas com, no mínimo, 24 horas de antecedência");
        }
    }
}
