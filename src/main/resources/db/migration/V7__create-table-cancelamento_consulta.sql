CREATE TABLE cancelamento_consulta(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    consulta_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    motivo VARCHAR(30) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_Consulta FOREIGN KEY (consulta_id) REFERENCES consulta(id),
    CONSTRAINT FK_Paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);