CREATE TABLE usuario(
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo TINYINT NOT NULL,
    autoridade VARCHAR(20),

    PRIMARY KEY (id)
);

CREATE TABLE medico(
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    usuario_id INTEGER NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    especialidade VARCHAR(20) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(100),

    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);