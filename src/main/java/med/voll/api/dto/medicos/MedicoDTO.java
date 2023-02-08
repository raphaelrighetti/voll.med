package med.voll.api.dto.medicos;

public record MedicoDTO(
        String nome,
        String email,
        Long crm,
        EspecialidadeDTO especialidade,
        EnderecoDTO endereco
    ) {
}
