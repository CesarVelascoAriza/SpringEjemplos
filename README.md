# Ejemplos de Spring Framework

Colección de proyectos independientes para practicar Spring Boot, persistencia, vistas web y seguridad. Cada subproyecto tiene su propia configuración y debe ejecutarse desde su directorio.

## Subproyectos

| Proyecto | Descripción | Build |
|---|---|---|
| [DemoPrueba](DemoPrueba/README.md) | Separación básica entre servicio y repositorio; muestra inyección de dependencias y logs al arrancar. | Maven |
| [demoWeb](demoWeb/README.md) | Aplicación web con Thymeleaf, API REST, JPA, PostgreSQL y HTTP Basic. | Maven |
| [demo-security](demo-security/README.md) | Autenticación stateless con JWT, Spring Security y H2 en memoria. | Gradle |
| [security-oauht2](security-oauht2/README.md) | Servidor de autorización OAuth2/OIDC con Spring Authorization Server. | Gradle |

## Requisitos

- Java 25, configurado en el `toolchain` o propiedad de cada proyecto.
- Acceso a Internet la primera vez que se descarguen dependencias y los wrappers.
- PostgreSQL accesible para [demoWeb](demoWeb/README.md).

Los proyectos incluyen Maven Wrapper o Gradle Wrapper, por lo que no es necesario instalar Maven o Gradle globalmente.

## Ejecutar un proyecto

Entra en el directorio del proyecto y usa el comando correspondiente:

```bash
cd demo-security
./gradlew bootRun
```

Para los proyectos Maven:

```bash
cd demoWeb
./mvnw spring-boot:run
```

Consulta el README de cada proyecto para conocer sus endpoints, credenciales de desarrollo, configuración de base de datos y pruebas.

## Compilar y probar

Cada subproyecto se compila y prueba por separado:

```bash
./mvnw clean test       # DemoPrueba o demoWeb
./gradlew clean test    # demo-security o security-oauht2
```

## Notas de seguridad

Las credenciales, secretos, contraseñas SQL y claves en memoria de estos ejemplos son únicamente material didáctico. No deben utilizarse tal cual en producción; cambia secretos, usa almacenamiento seguro de credenciales y aplica un encoder de contraseñas adecuado.

## Estructura del repositorio

```text
SpringEjemplos/
├── DemoPrueba/
├── demoWeb/
├── demo-security/
├── security-oauht2/
└── README.md
```
