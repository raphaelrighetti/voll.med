CREATE TABLE consulta(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT,
    dia_consulta_id BIGINT NOT NULL,
    data DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_MedicoConsulta FOREIGN KEY (medico_id) REFERENCES medico(id),
    CONSTRAINT FK_PacienteConsulta FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT FK_DiaConsulta FOREIGN KEY (dia_consulta_id) REFERENCES dia_consulta(id)
);