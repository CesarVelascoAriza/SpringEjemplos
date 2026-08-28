# demoWeb

Aplicación web Spring Boot que combina una vista Thymeleaf, una API REST y persistencia JPA sobre PostgreSQL.

## Funcionalidades

- Vista web en `/` y `/listar` usando la plantilla `greeting.html`.
- API REST de personas en `/personas`.
- Modelo JPA `Persona` y `Usuario` dentro del esquema PostgreSQL `pruebaspring`.
- Autenticación HTTP Basic para todas las rutas.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/` | Muestra la vista de bienvenida y registra una persona de ejemplo. |
| `GET` | `/listar` | Muestra las personas en la vista Thymeleaf. |
| `GET` | `/personas` | Devuelve todas las personas en JSON. |
| `POST` | `/personas` | Guarda una persona enviada como parámetros/formulario. |

La aplicación requiere autenticación HTTP Basic. Las credenciales configuradas para desarrollo en `application.properties` son `cesar` / `Cesar`; deben cambiarse antes de cualquier uso real.

## Tecnologías

- Java 25
- Spring Boot 4.1.0
- Spring MVC y Thymeleaf
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven Wrapper
- Empaquetado WAR

## Configuración de base de datos

La configuración actual apunta a:

```text
jdbc:postgresql://192.168.131.129:5432/application
Usuario: java
Contraseña: java
Esquema: pruebaspring
```

Ajusta estas propiedades en `src/main/resources/application.properties` para usar otra instancia. La base de datos debe contener las tablas `Personas` y `Usuarios`, o permitir que Hibernate las cree/actualice según la configuración.

## Ejecutar

Desde este directorio:

```bash
./mvnw spring-boot:run
```

Después, abre `http://localhost:8080/` o consulta la API con credenciales:

```bash
curl -u cesar:Cesar http://localhost:8080/personas
```

## Compilar y probar

```bash
./mvnw clean test
```

## Estructura principal

```text
src/main/java/com/example/demo/
├── controler/       # Controladores de vistas
├── model/           # Entidades JPA
├── repo/            # Repositorios
├── rest/            # Controlador REST
└── SecurityConfig.java
src/main/resources/
└── templates/       # Vistas Thymeleaf
pom.xml
```
