<div align="center">

# 🚀 ForoHub

### API REST para administrar tópicos de un foro educativo

<p>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.3.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 3.3.1">
  <img src="https://img.shields.io/badge/MySQL-8+-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/License-Educational-blue?style=for-the-badge" alt="Licencia educativa">
</p>

<p>
  <strong>Challenge ForoHub de Alura + Oracle</strong>
</p>

</div>

<br>

## 📌 Índice

- [📖 Sobre el proyecto](#-sobre-el-proyecto)
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologías utilizadas](#️-tecnologías-utilizadas)
- [📂 Estructura del proyecto](#-estructura-del-proyecto)
- [⚙️ Requisitos previos](#️-requisitos-previos)
- [🚀 Instalación y configuración](#-instalación-y-configuración)
- [▶️ Ejecución](#️-ejecución)
- [🔐 Autenticación](#-autenticación)
- [📡 Endpoints principales](#-endpoints-principales)
- [📚 Documentación de la API](#-documentación-de-la-api)
- [🧪 Pruebas](#-pruebas)
- [👤 Autor](#-autor)

## 📖 Sobre el proyecto

**ForoHub** es una API REST desarrollada con Spring Boot para gestionar tópicos de un foro de aprendizaje. El proyecto permite consultar, registrar, actualizar y eliminar tópicos, además de proteger las operaciones mediante autenticación basada en tokens **JWT**.

La aplicación fue creada como parte del challenge de **Alura y Oracle Next Education**, aplicando buenas prácticas de desarrollo backend, separación por capas, validación de datos, persistencia con JPA y migraciones controladas con Flyway.

## ✨ Funcionalidades

- 🔑 Inicio de sesión con email y contraseña.
- 🪪 Generación y validación de tokens JWT con expiración de 2 horas.
- 📝 Registro de nuevos tópicos.
- 📋 Listado paginado de tópicos ordenados por título.
- 🔎 Consulta del detalle de un tópico.
- ✏️ Actualización de tópicos existentes.
- 🗑️ Eliminación de tópicos.
- ✅ Validación de campos obligatorios.
- 🚫 Detección de tópicos duplicados.
- 🗃️ Creación y carga inicial de la base de datos mediante Flyway.
- 📘 Documentación interactiva con Swagger/OpenAPI.
- 🛡️ Manejo centralizado de errores y respuestas de validación.

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| **Java 17** | Lenguaje principal |
| **Spring Boot 3.3.1** | Framework base de la aplicación |
| **Spring Web** | Construcción de la API REST |
| **Spring Data JPA / Hibernate** | Persistencia y mapeo objeto-relacional |
| **Spring Security** | Autenticación y autorización |
| **JWT Auth0** | Creación y validación de tokens |
| **MySQL** | Base de datos relacional |
| **Flyway** | Control de migraciones de base de datos |
| **Bean Validation** | Validación de solicitudes |
| **Lombok** | Reducción de código repetitivo |
| **SpringDoc OpenAPI** | Documentación de endpoints |
| **Maven** | Gestión de dependencias y ejecución |

## 📂 Estructura del proyecto

```text
src/
├── main/
│   ├── java/com/si3ri/ForoHub/
│   │   ├── controller/       # Controladores REST
│   │   ├── domain/
│   │   │   ├── dto/          # Objetos de entrada y salida
│   │   │   ├── entity/       # Entidades JPA
│   │   │   └── service/      # Lógica de negocio
│   │   └── infra/
│   │       ├── config/       # Seguridad y OpenAPI
│   │       ├── error/        # Manejo de excepciones
│   │       ├── repository/   # Acceso a datos
│   │       ├── security/     # Filtro JWT
│   │       └── validations/  # Validaciones de dominio
│   └── resources/
│       ├── db/migration/     # Migraciones Flyway
│       └── application.properties
└── test/                     # Pruebas automatizadas
```

## ⚙️ Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- [Java JDK 17](https://adoptium.net/)
- [MySQL 8 o superior](https://dev.mysql.com/downloads/mysql/)
- [Git](https://git-scm.com/)

La base de datos utilizada por defecto es `ForoHubDB`. Puedes crearla manualmente con:

```sql
CREATE DATABASE ForoHubDB;
```

Las tablas y los datos iniciales se crean automáticamente al iniciar la aplicación mediante las migraciones de Flyway.

## 🚀 Instalación y configuración

1. Clona el repositorio:

   ```bash
   git clone https://github.com/MarioSi3ri/ForoHub.git
   cd ForoHub
   ```

2. Configura las variables de entorno de conexión a MySQL:

   ```bash
   export DB_HOST=localhost
   export DB_USER_MS=tu_usuario_mysql
   export DB_PASSWORD=tu_contraseña_mysql
   export JWT_SECRET=una_clave_secreta_segura
   ```

   En Windows PowerShell:

   ```powershell
   $env:DB_HOST="localhost"
   $env:DB_USER_MS="tu_usuario_mysql"
   $env:DB_PASSWORD="tu_contraseña_mysql"
   $env:JWT_SECRET="una_clave_secreta_segura"
   ```

3. Verifica que MySQL esté iniciado y ejecuta la aplicación:

   ```bash
   ./mvnw spring-boot:run
   ```

   En Windows:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

> 💡 `JWT_SECRET` es opcional porque existe un valor predeterminado para desarrollo, pero se recomienda definir una clave propia y segura en cualquier entorno real.

## ▶️ Ejecución

Por defecto, la API queda disponible en:

```text
http://localhost:8080
```

También puedes generar el archivo ejecutable y arrancarlo con:

```bash
./mvnw clean package
java -jar target/ForoHub-0.0.1-SNAPSHOT.jar
```

## 🔐 Autenticación

Todos los endpoints de tópicos requieren un token JWT. Primero autentica un usuario registrado en la tabla `usuarios`:

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@correo.com",
    "password": "tu_contraseña"
  }'
```

La respuesta contiene el token:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Después, envía el token en el encabezado `Authorization`:

```text
Authorization: Bearer <tu_token>
```

> ℹ️ El proyecto incluye migraciones para crear la tabla de usuarios, pero no registra usuarios automáticamente. Debes insertar un usuario con una contraseña almacenada con BCrypt o adaptar el flujo de registro antes de autenticarte.

## 📡 Endpoints principales

### Autenticación

| Método | Endpoint | Descripción | Autenticación |
|---|---|---|---|
| `POST` | `/login` | Autentica un usuario y genera un JWT | No |

### Tópicos

| Método | Endpoint | Descripción | Autenticación |
|---|---|---|---|
| `GET` | `/topicos` | Lista tópicos de forma paginada | Sí |
| `GET` | `/topicos/{id}` | Consulta un tópico por ID | Sí |
| `POST` | `/topicos` | Registra un nuevo tópico | Sí |
| `PUT` | `/topicos/{id}` | Actualiza un tópico existente | Sí |
| `DELETE` | `/topicos/{id}` | Elimina un tópico | Sí |

### Registrar un tópico

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <tu_token>" \
  -d '{
    "titulo": "¿Cómo utilizar Spring Security?",
    "mensaje": "Necesito ayuda para configurar la autenticación JWT.",
    "autor": "Mario Si3ri",
    "curso": "Spring Boot"
  }'
```

### Listar tópicos con paginación

```bash
curl "http://localhost:8080/topicos?page=0&size=10" \
  -H "Authorization: Bearer <tu_token>"
```

Los parámetros `page`, `size` y `sort` son compatibles con la paginación de Spring Data. El listado se ordena por título de forma ascendente por defecto.

## 📚 Documentación de la API

Con la aplicación en ejecución, accede a la documentación interactiva:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Especificación OpenAPI:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Desde Swagger UI puedes consultar los contratos de la API y autorizar solicitudes protegidas con el esquema `Bearer JWT`.

## 🧪 Pruebas

Para ejecutar las pruebas automatizadas:

```bash
./mvnw test
```

## 👤 Autor

Desarrollado por **[MarioSi3ri](https://github.com/MarioSi3ri)** 💻

Repositorio: [ForoHub](https://github.com/MarioSi3ri/ForoHub)

## 📄 Licencia

Este proyecto se distribuye bajo la **Licencia MIT**. Consulta el archivo [LICENSE](LICENSE) para conocer los términos completos.

---

<div align="center">

Si este proyecto te resulta útil, ¡considera dejar una ⭐ en el repositorio!

</div>
