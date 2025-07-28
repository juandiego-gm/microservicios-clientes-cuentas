-- ========================
-- CREACIÓN DE LA BASE DE DATOS
-- ========================

-- CREATE DATABASE bancadb;

-- \c bancadb; 

-- ========================
CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10),
    edad INTEGER,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

-- ========================
-- TABLA: cliente
-- ========================

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    persona_id INTEGER NOT NULL,
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_persona FOREIGN KEY (persona_id) REFERENCES persona(id)
);

-- ========================
-- TABLA: cuenta
-- ========================

CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20),
    saldo_inicial NUMERIC(15,2) DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- ========================
-- TABLA: movimiento
-- ========================

CREATE TABLE movimiento (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP NOT NULL DEFAULT NOW(),
    tipo_movimiento VARCHAR(20) NOT NULL, -- Ej: 'RETIRO', 'DEPOSITO'
    valor NUMERIC(15,2) NOT NULL,
    saldo NUMERIC(15,2),
    cuenta_id INTEGER NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);
