CREATE TABLE admin(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    usuario_id BIGINT NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    logradouro VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(100),

    PRIMARY KEY (id),
    CONSTRAINT FK_UsuarioAdmin FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);