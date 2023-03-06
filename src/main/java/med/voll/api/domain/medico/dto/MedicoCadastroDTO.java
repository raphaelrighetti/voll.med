package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.EnderecoCadastroDTO;
import med.voll.api.domain.genericos.UsuarioCadastro;
import med.voll.api.domain.genericos.annotation.Crm;
import med.voll.api.domain.genericos.annotation.Telefone;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.security.autorizacao.Autoridades;

public record MedicoCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank @Telefone
        String telefone,
        @NotBlank @Crm
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        EnderecoCadastroDTO endereco
) implements UsuarioCadastro {

        @Override
        public String getEmail() {
                return email;
        }

        @Override
        public Autoridades getAutoridade() {
                return Autoridades.ROLE_MEDICO;
        }
}
