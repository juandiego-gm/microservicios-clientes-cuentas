
# Sistema - Microservicios

Este proyecto implementa un sistema distribuido utilizando microservicios desarrollados con **Spring Boot**, desplegados con **Docker**, y respaldados por **PostgreSQL** y **RabbitMQ** para la mensajería asincrónica.

## Microservicios

- `personas-clientes-service`: Gestiona la creación y administración de personas y clientes.
- `cuentas-movimientos-service`: Gestiona cuentas bancarias y registra movimientos (depósitos, retiros).

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.5.4
- PostgreSQL 14
- RabbitMQ 3-management
- Docker y Docker Compose
- JPA / Hibernate
- Lombok
- Postman (para pruebas manuales)
- JUnit y Mockito (para pruebas automáticas)

## Despliegue con Docker

### 1. Clona el repositorio

```bash
git clone https://github.com/juandiego-gm/microservicios-clientes-cuentas.git
cd tu-repo
```

### 2. Construye los servicios

```bash
docker-compose build
```

### 3. Levanta los contenedores

```bash
docker-compose up
```

Esto levanta:

- PostgreSQL en `localhost:5433`
- RabbitMQ en `localhost:15672` (usuario: `admin`, clave: `admin`)
- `personas-clientes-service` en `localhost:8081`
- `cuentas-movimientos-service` en `localhost:8082`

### 4. Accede a RabbitMQ

- URL: [http://localhost:15672](http://localhost:15672)
- Usuario: `admin`
- Contraseña: `admin`

## Pruebas con Postman

Se incluyen pruebas manuales en formato JSON para Postman. Puedes importarlo en Postman para probar los endpoints:

- Crear, obtener, actualizar y eliminar clientes
- Crear cuentas bancarias
- Realizar depósitos y retiros
- Listar movimientos

## Pruebas automatizadas

Para ejecutar pruebas unitarias:

```bash
./gradlew test
```

Las pruebas incluyen:

- Servicios (negocio)
- Controladores REST
- Algunas pruebas de integración

## Estructura del proyecto

```
.
├── personas-clientes-service
│   ├── controller
│   ├── model
│   ├── repository
│   ├── service
│   ├── config
│   └── test
├── cuentas-movimientos-service
│   ├── controller
│   ├── model
│   ├── repository
│   ├── service
│   ├── sender
│   └── test
├── docker-compose.yml
└── README.md
```

## Endpoints disponibles

### Personas/Clientes (localhost:8081)

- `POST /clientes` - Crear cliente
- `GET /clientes` - Listar todos los clientes
- `GET /clientes/{id}` - Obtener cliente por ID
- `PUT /clientes/{id}` - Actualizar cliente
- `DELETE /clientes/{id}` - Eliminar cliente

### Cuentas/Movimientos (localhost:8082)

- `POST /cuentas` - Crear cuenta
- `GET /cuentas` - Listar cuentas
- `GET /cuentas/{id}` - Obtener cuenta por ID
- `PUT /cuentas/{id}` - Actualizar cuenta
- `DELETE /cuentas/{id}` - Eliminar cuenta
- `POST /movimientos` - Crear movimiento
- `GET /movimientos` - Listar movimientos

## Notas

- Los microservicios se comunican mediante RabbitMQ para validar la existencia del cliente antes de crear cuentas.
- Se sigue una arquitectura limpia y modular, separando las responsabilidades de forma clara.

---

## Autor

Juan Diego Góez

---
