package med.voll.api.controller.medico;

import med.voll.api.dto.endereco.EnderecoDTOCadastro;
import med.voll.api.dto.endereco.EnderecoDTODetalhe;
import med.voll.api.dto.medico.MedicoDTOCadastro;
import med.voll.api.dto.medico.MedicoDTODetalhe;
import med.voll.api.entity.medico.MedicoEspecialidade;
import med.voll.api.repository.medico.MedicoRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MedicoDTOCadastro> medicoDTOCadastroJsonTester;

    @Autowired
    private JacksonTester<MedicoDTODetalhe> medicoDTOListagemJsonTester;

    @MockBean
    private MedicoRepository repository;

    private final MedicoDTOCadastro medicoDTOCadastro = new MedicoDTOCadastro(
            "Medico",
            "medico@voll.med",
            "11912345678",
            "123456",
            MedicoEspecialidade.ORTOPEDIA,
            new EnderecoDTOCadastro(
                    "logradouro",
                    "bairro",
                    "12345678",
                    "cidade",
                    "uf",
                    1,
                    "complemento"
            )
    );

    @Test
    @DisplayName("Deveria retornar codigo 403 quando usuário não estiver autenticado")
    public void cadastrarCenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("Deveria retornar codigo 400 quando informações estiverem inválidas")
    @WithMockUser
    public void cadastrarCenario2() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar JSON esperado quando informações estiverem corretas")
    @WithMockUser
    public void cadastrarCenario3() throws Exception {
        MedicoDTODetalhe dtoEsperado = new MedicoDTODetalhe(
                null,
                "Medico",
                "medico@voll.med",
                "11912345678",
                "123456",
                MedicoEspecialidade.ORTOPEDIA,
                new EnderecoDTODetalhe(
                        "logradouro",
                        "bairro",
                        "12345678",
                        "cidade",
                        "uf",
                        1,
                        "complemento"
                ),
                true
        );

        MockHttpServletResponse response = mvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoDTOCadastroJsonTester.write(
                                medicoDTOCadastro
                        ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo(medicoDTOListagemJsonTester.write(dtoEsperado).getJson());
    }
}
