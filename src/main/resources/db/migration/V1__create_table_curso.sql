CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_curso VARCHAR(255) NOT NULL UNIQUE,
    categoria VARCHAR(255) NOT NULL
);
