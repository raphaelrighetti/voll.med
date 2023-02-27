package med.voll.api.validacao.consulta.abstracao;

import med.voll.api.dto.consulta.ConsultaDTOCancelamento;

public interface ValidacaoCancelamentoConsulta {

    void validar(ConsultaDTOCancelamento dados);
}
