# DemoPrueba

Ejemplo mínimo de una aplicación Spring Boot con una separación sencilla entre aplicación, servicio y repositorio.

## Qué demuestra

- Arranque de Spring Boot mediante `DemoPruebaApplication`.
- Inyección de dependencias con `@Service`, `@Repository` y `@Autowired`.
- Flujo `IPersonaService` -> `IPersonaRepo`.
- Registro de mensajes en el log al iniciar la aplicación.

El repositorio es una implementación de ejemplo: `registrar(String nombre)` no persiste datos, únicamente escribe `Hola` y el nombre recibido en el log.

## Tecnologías

- Java 25
- Spring Boot 4.1.0
- Maven Wrapper

## Ejecutar

Desde este directorio:

```bash
./mvnw spring-boot:run
```

En Windows:

```bat
mvnw.cmd spring-boot:run
```

No expone endpoints HTTP. Al iniciar, `CommandLineRunner` registra `Hola mundo` y ejecuta el registro de `Cesar`.

## Compilar y probar

```bash
./mvnw clean test
```

El test incluido comprueba que el contexto de Spring Boot pueda arrancar.

## Estructura principal

```text
src/main/java/com/example/
├── DemoPruebaApplication.java
├── repository/       # Contrato e implementación del repositorio
└── service/          # Servicio de personas
src/test/             # Pruebas de contexto
pom.xml
```
