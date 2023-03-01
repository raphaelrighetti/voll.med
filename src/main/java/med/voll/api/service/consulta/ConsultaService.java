package med.voll.api.service.consulta;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.dto.consulta.ConsultaDTOCancelamento;
import med.voll.api.dto.consulta.ConsultaDTODetalhamento;
import med.voll.api.entity.consulta.Consulta;
import med.voll.api.entity.consulta.ConsultaStatus;
import med.voll.api.entity.medico.Medico;
import med.voll.api.entity.paciente.Paciente;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.repository.paciente.PacienteRepository;
import med.voll.api.validacao.consulta.abstracao.ValidacaoAgendamentoConsulta;
import med.voll.api.validacao.consulta.abstracao.ValidacaoCancelamentoConsulta;
import med.voll.api.validacao.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidacaoAgendamentoConsulta> validacoesAgendamento;
    @Autowired
    private List<ValidacaoCancelamentoConsulta> validacoesCancelamento;

    public ConsultaDTODetalhamento agendar(ConsultaDTOAgendamento dados) {
        if (!pacienteRepository.existsById(dados.pacienteId())) {
            throw new ValidacaoException("Paciente com id informado não existe");
        }

        if (dados.medicoId() != null && !medicoRepository.existsById(dados.medicoId())) {
            throw new ValidacaoException("Médico com id informado não existe");
        }

        validacoesAgendamento.forEach(validacao -> validacao.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.pacienteId());
        Medico medico = escolherMedico(dados);
        Consulta consulta = new Consulta(
                null,
                medico,
                paciente,
                dados.data().withMinute(0),
                ConsultaStatus.AGENDADA,
                null
        );

        consultaRepository.save(consulta);

        return new ConsultaDTODetalhamento(consulta);
    }

    public void cancelar(ConsultaDTOCancelamento dados) {
        if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Consulta com id informado não existe");
        }

        validacoesCancelamento.forEach(validacao -> validacao.validar(dados));

        Consulta consulta = consultaRepository.getReferenceById(dados.id());

        consulta.cancelar(dados.motivo());
    }

    private Medico escolherMedico(ConsultaDTOAgendamento dados) {
        if (dados.medicoId() != null) {
            return medicoRepository.getReferenceById(dados.medicoId());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("O campo especialidade é obrigatório quando o médico não é informado");
        }

        Medico medico = medicoRepository.escolherMedicoPorEspecialidadeEDataDisponivel(dados.especialidade(), dados.data());

        if (medico == null) {
            throw new ValidacaoException("Não há médicos desta especialidade disponíveis nesta data");
        }

        return medico;
    }
}
