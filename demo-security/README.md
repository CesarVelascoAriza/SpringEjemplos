# demo-security

Ejemplo de autenticación stateless con Spring Security y tokens JWT, usando usuarios y autoridades almacenados en una base H2 en memoria.

## Funcionalidades

- Autenticación de usuario mediante JWT.
- Filtro propio para validar el token en cada petición protegida.
- API de autenticación y endpoint protegido de prueba.
- Consola H2 habilitada para inspeccionar la base en memoria.
- Inicialización del esquema y datos desde `schema.sql` y `data.sql`.

## Endpoints

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `POST` | `/Authenticated` | Público | Recibe `username` y `password` y devuelve un JWT. |
| `GET` | `/pruebas` | JWT o autenticación configurada | Devuelve `pruebas`. |
| `GET` | `/h2-console` | Desarrollo | Consola web de H2. |

Ejemplo de autenticación:

```bash
curl -X POST http://localhost:8080/Authenticated \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin","password":"to_be_encoded"}'
```

Usa el token devuelto así:

```bash
curl http://localhost:8080/pruebas -H 'Authorization: Bearer <TOKEN>'
```

Los datos SQL son de demostración y contienen contraseñas de marcador (`to_be_encoded`). La configuración usa `NoOpPasswordEncoder`, por lo que no debe reutilizarse en producción.

## Configuración H2

- URL JDBC: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: `password`
- Consola: `http://localhost:8080/h2-console`
- JDBC URL de la consola: `jdbc:h2:mem:testdb`

La base desaparece al detener la aplicación.

## Tecnologías

- Java 25
- Spring Boot 4.1.0
- Spring Security
- Spring Data JPA
- JJWT 0.11.5
- H2
- Gradle Wrapper 9.5.1

## Ejecutar

Desde este directorio:

```bash
./gradlew bootRun
```

En Windows:

```bat
gradlew.bat bootRun
```

## Compilar y probar

```bash
./gradlew clean test
```

## Estructura principal

```text
src/main/java/com/example/example/demo/security/
├── config/          # JWT, filtro y seguridad
├── controller/      # Autenticación y DTOs
├── entities/        # Entidades de dominio
├── repository/      # Acceso a usuarios
└── service/         # Manejo de errores de autenticación
src/main/resources/
├── application.yml
├── schema.sql
└── data.sql
build.gradle
```
