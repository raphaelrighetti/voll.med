package med.voll.api.repository.medico;

import med.voll.api.dto.endereco.EnderecoDTOCadastro;
import med.voll.api.dto.medico.MedicoDTOCadastro;
import med.voll.api.dto.paciente.PacienteDTOCadastro;
import med.voll.api.entity.consulta.Consulta;
import med.voll.api.entity.consulta.ConsultaMotivoCancelamento;
import med.voll.api.entity.consulta.ConsultaStatus;
import med.voll.api.entity.medico.Medico;
import med.voll.api.entity.medico.MedicoEspecialidade;
import med.voll.api.entity.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager em;

    private final LocalDateTime proximaSegundaFeiraAs10 = LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(10, 0);

    @Test
    @DisplayName("Médico escolhido deveria ser null quando não houver disponibilidade na data/hora")
    public void escolherMedicoPorEspecialidadeEDataDisponivelCenario1() {
        Medico medico = cadastrarMedico(medicoDTOCadastro(
                "Medico",
                "medico@voll.med",
                "123456",
                MedicoEspecialidade.ORTOPEDIA
        ));

        Paciente paciente = cadastrarPaciente(pacienteDTOCadastro(
                "Rodolfo",
                "rodolfo@teste.com",
                "12345678901"
        ));

        agendarConsulta(medico, paciente, proximaSegundaFeiraAs10);

        Medico medicoEscolhido = repository.escolherMedicoPorEspecialidadeEDataDisponivel(
                MedicoEspecialidade.ORTOPEDIA,
                proximaSegundaFeiraAs10
        );

        assertThat(medicoEscolhido).isNull();
    }

    @Test
    @DisplayName("Médico escolhido deveria ser igual ao médico cadastrado quando houver disponibilidade na data/hora")
    public void escolherMedicoPorEspecialidadeEDataDisponivelCenario2() {
        Medico medico = cadastrarMedico(medicoDTOCadastro(
                "Medico",
                "medico@voll.med",
                "123456",
                MedicoEspecialidade.ORTOPEDIA
        ));

        Medico medicoEscolhido = repository.escolherMedicoPorEspecialidadeEDataDisponivel(
                MedicoEspecialidade.ORTOPEDIA,
                proximaSegundaFeiraAs10
        );

        assertThat(medicoEscolhido).isEqualTo(medico);
    }

    @Test
    @DisplayName("Médico escolhido deveria ser igual ao médico 2 quando houver disponibilidade para ele na data/hora")
    public void escolherMedicoPorEspecialidadeEDataDisponivelCenario3() {
        Medico medico1 = cadastrarMedico(medicoDTOCadastro(
                "Medico",
                "medico@voll.med",
                "123456",
                MedicoEspecialidade.ORTOPEDIA
        ));

        Medico medico2 = cadastrarMedico(medicoDTOCadastro(
                "Medico2",
                "medico2@voll.med",
                "123457",
                MedicoEspecialidade.ORTOPEDIA
        ));

        Paciente paciente = cadastrarPaciente(pacienteDTOCadastro(
                "Rodolfo",
                "rodolfo@teste.com",
                "12345678901"
        ));

        agendarConsulta(medico1, paciente, proximaSegundaFeiraAs10);

        Medico medicoEscolhido = repository.escolherMedicoPorEspecialidadeEDataDisponivel(
                MedicoEspecialidade.ORTOPEDIA,
                proximaSegundaFeiraAs10
        );

        assertThat(medicoEscolhido).isEqualTo(medico2);
    }

    @Test
    @DisplayName("Médico escolhido deveria ser igual ao médico cadastrado quando a consulta no horário estiver cancelada")
    public void escolherMedicoPorEspecialidadeEDataDisponivelCenario4() {
        Medico medico = cadastrarMedico(medicoDTOCadastro(
                "Medico",
                "medico@voll.med",
                "123456",
                MedicoEspecialidade.ORTOPEDIA
        ));

        Paciente paciente = cadastrarPaciente(pacienteDTOCadastro(
                "Rodolfo",
                "rodolfo@teste.com",
                "12345678901"
        ));

        cadastrarConsultaCancelada(medico, paciente, proximaSegundaFeiraAs10);

        Medico medicoEscolhido = repository.escolherMedicoPorEspecialidadeEDataDisponivel(
                MedicoEspecialidade.ORTOPEDIA,
                proximaSegundaFeiraAs10
        );

        assertThat(medicoEscolhido).isEqualTo(medico);
    }

    private Medico cadastrarMedico(MedicoDTOCadastro dados) {
        Medico medico = new Medico(dados);
        em.persist(medico);

        return medico;
    }

    private Paciente cadastrarPaciente(PacienteDTOCadastro dados) {
        Paciente paciente = new Paciente(dados);
        em.persist(paciente);

        return paciente;
    }

    private void agendarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, ConsultaStatus.AGENDADA, null));
    }

    private void cadastrarConsultaCancelada(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, ConsultaStatus.CANCELADA, ConsultaMotivoCancelamento.PACIENTE_DESISTIU));
    }

    private MedicoDTOCadastro medicoDTOCadastro(String nome, String email, String crm, MedicoEspecialidade especialidade) {
        return new MedicoDTOCadastro(nome, email, "11912345678", crm, especialidade, enderecoDTOCadastro());
    }

    private PacienteDTOCadastro pacienteDTOCadastro(String nome, String email, String cpf) {
        return new PacienteDTOCadastro(nome, email, cpf, "11912345678", enderecoDTOCadastro());
    }

    private EnderecoDTOCadastro enderecoDTOCadastro() {
        return new EnderecoDTOCadastro(
                "logradouro",
                "bairro",
                "12345678",
                "cidade",
                "uf",
                1,
                "complemento"
        );
    }
}
