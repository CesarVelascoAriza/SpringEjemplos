# security-oauht2

Ejemplo de servidor de autorización OAuth 2.0 y OpenID Connect con Spring Authorization Server. El nombre del directorio conserva la denominación original del proyecto (`oauht2`).

## Qué demuestra

- Registro en memoria de un cliente OIDC llamado `oidc-client`.
- Flujos `authorization_code` y `refresh_token`.
- Consentimiento explícito del usuario.
- Login basado en formulario.
- Emisión y validación de JWT firmados con una clave RSA generada al arrancar.
- Usuario de demostración en memoria con rol `USER`.

## Datos de desarrollo

| Elemento | Valor |
|---|---|
| Cliente | `oidc-client` |
| Secreto | `secret` |
| Usuario | `user` |
| Contraseña | `password` |
| Redirección | `http://127.0.0.1:8080/login/oauth2/code/oidc-client` |

Son valores efímeros para aprendizaje. El cliente y la clave RSA se crean en memoria, por lo que se pierden al reiniciar la aplicación.

## Endpoints habituales

Spring Authorization Server expone, entre otros, los endpoints estándar:

- `/oauth2/authorize`
- `/oauth2/token`
- `/oauth2/jwks`
- `/userinfo`
- `/.well-known/openid-configuration`

El flujo de autorización requiere un cliente OIDC que use la URI de redirección registrada.

## Tecnologías

- Java 25
- Spring Boot 4.1.0
- Spring Authorization Server
- Spring Security OAuth2 / OIDC
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

La aplicación queda disponible normalmente en `http://localhost:8080`.

## Compilar y probar

```bash
./gradlew clean test
```

## Estructura principal

```text
src/main/java/com/example/security/security/
├── SecurityOauht2Application.java
└── config/
    └── SecurityConfig.java
src/main/resources/
└── application.properties
build.gradle
```
