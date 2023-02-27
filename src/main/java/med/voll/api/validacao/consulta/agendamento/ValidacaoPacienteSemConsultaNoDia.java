package med.voll.api.validacao.consulta.agendamento;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.entity.consulta.Consulta;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteSemConsultaNoDia implements ValidacaoAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(ConsultaDTOAgendamento dados) {
        Consulta consulta = repository.getConsultaComPacienteComConsultaAgendadaNoDia(dados.pacienteId(), dados.data());

        if (consulta != null) {
            throw new ValidacaoException("Paciente já possui consulta marcada no dia");
        }
    }
}
