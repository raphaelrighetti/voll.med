package med.voll.api.controller.consulta;

import med.voll.api.dto.consulta.ConsultaDTOAgendamento;
import med.voll.api.dto.consulta.ConsultaDTODetalhamento;
import med.voll.api.entity.consulta.ConsultaStatus;
import med.voll.api.entity.medico.MedicoEspecialidade;
import med.voll.api.service.consulta.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JacksonTester<ConsultaDTOAgendamento> consultaDTOAgendamentoJsonTester;

    @Autowired
    JacksonTester<ConsultaDTODetalhamento> consultaDTODetalhamentoJsonTester;

    @MockBean
    private ConsultaService service;

    @Test
    @DisplayName("Deveria devolver status code 403 quando usuário não estiver autenticado")
    public void agendarCenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deveria devolver status code 400 quando as informações estão inválidas")
    @WithMockUser
    public void agendarCenario2() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver JSON esperado quando informações forem passadas da maneira correta")
    @WithMockUser
    public void agendarCenario3() throws Exception {
        LocalDateTime data = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        ConsultaDTODetalhamento dtoEsperado = new ConsultaDTODetalhamento(
                1L,
                1L,
                MedicoEspecialidade.ORTOPEDIA,
                1L,
                data,
                ConsultaStatus.AGENDADA,
                null
        );

        when(service.agendar(any())).thenReturn(dtoEsperado);

        MockHttpServletResponse response = mvc.perform(
                    post("/consultas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(consultaDTOAgendamentoJsonTester.write(
                                    new ConsultaDTOAgendamento(1L, null, 1L, data)
                            ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getContentAsString())
                .isEqualTo(consultaDTODetalhamentoJsonTester.write(dtoEsperado).getJson());
    }
}
