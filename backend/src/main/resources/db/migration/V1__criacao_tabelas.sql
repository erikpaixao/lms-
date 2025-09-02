CREATE TABLE IF NOT EXISTS permissao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    primeiro_nome VARCHAR(255) NOT NULL,
    ultimo_nome VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario_permissao (
    id_usuario BIGINT,
    id_permissao BIGINT,
    PRIMARY KEY (id_usuario, id_permissao),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_permissao) REFERENCES permissao(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS curso (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL UNIQUE,
    data_inicio DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tarefa (
    id_usuario BIGINT,
    id_curso BIGINT,
    id_categoria BIGINT,
    descricao VARCHAR(255),
    tempo_gasto TIME, 
    data_tarefa TIMESTAMP NOT NULL, 
    PRIMARY KEY (id_usuario, id_curso, id_categoria, data_tarefa),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_curso) REFERENCES curso(id),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);
