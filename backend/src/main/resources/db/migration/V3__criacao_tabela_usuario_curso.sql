CREATE TABLE IF NOT EXISTS usuario_curso (
    id_usuario BIGINT,
    id_curso BIGINT,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id) ON DELETE CASCADE
);
