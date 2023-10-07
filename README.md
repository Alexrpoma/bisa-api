## BISA API
### Descripcion:
Desarrollo de una API para la creacion, registro, agregar referencias personales y administracion de clientes y personas.
Este es un proyecto para la evaluacion de conocimientos en el desarrollo de software.
### Tencologias utilizadas:
- Java 17
- Maven 3.9.2
- Spring Boot 3.1.4
- Spring Data JPA
- Spring Web
- H2 Database
- Lombok
- Swagger
### Descripcion breve sobre las tecnologias utilizadas:
- Java 17: Es un lenguaje de programacion orientado a objetos, de proposito general, concurrente, basado en clases, que fue disenado especificamente para tener tan pocas dependencias de implementacion como fuera posible.
- Maven 3.9.2: Es una herramienta de software para la gestion y construccion de proyectos Java creada por Jason van Zyl, de Sonatype, en 2002. Se basa en el concepto de un modelo de objeto de proyecto (POM), que describe la construccion de un proyecto en terminos de dependencias y de un conjunto de plugins y objetivos asociados a ellos.
- Spring Boot 3.1.4: Es un framework para el desarrollo de aplicaciones y contenedor de inversion de control, de codigo abierto para el lenguaje de programacion Java. Fue creado por Rod Johnson en 2003.
- Spring Data JPA: Es un framework de persistencia que facilita el acceso a bases de datos relacionales desde aplicaciones Java, proporcionando un nivel de abstraccion adicional sobre las capas de persistencia y subyacente.
- Spring Web: Es un framework de codigo abierto para el desarrollo de aplicaciones web en el lenguaje de programacion Java bajo la plataforma Java EE.
- H2 Database: Es un sistema de gestion de base de datos relacional escrito en Java. Puede ser envebido en aplicaciones Java o ejecutarse en el modo de servidor.
- Lombok: Es una biblioteca Java que se conecta automaticamente a su editor y herramientas de compilacion, insertando codigo generado, como metodos getter / setter, constructores, etc.
- Swagger: Es un framework de codigo abierto respaldado por un gran ecosistema de herramientas que ayuda a los desarrolladores a disenar, crear, documentar y consumir servicios web RESTful.
### Instalacion:
- Clonar el repositorio: `git clone https://github.com/Alexrpoma/bisa-api.git`
- Abrir el proyecto en un IDE de su preferencia.
- Ejecutar el proyecto.
- Abrir el navegador y acceder a la URL: `http://localhost:8090/swagger-ui.html`
- Probar los servicios.
- Para acceder a la base de datos, acceder a la URL: `http://localhost:8090/h2-console`
- Ingresar los siguientes datos:
  - JDBC URL: `jdbc:h2:mem:db`
  - User Name: `admin`
  - Password: `adm-bisa`
### endpoints:
- /api/v1/cliente (GET) : Obtiene todos los clientes registrados por accesibilidad.
- /api/v1/cliente/detalle (GET) : Obtiene todos los clientes registrados a detalle.
- /api/v1/cliente/{cliente_id} (GET) : Obtiene un cliente por su id.
- /api/v1/cliente (POST) : Registra un cliente.
- /api/v1/cliente/{cliente_id}/referencias (PUT) : Registra las referencias de un cliente.
- /api/v1/cliente/{cliente_id}/referencias (DELETE) : Elimina las referencias de un cliente.
- /api/v1/persona (GET) : Obtiene todas las personas registradas.
- /api/v1/persona/{persona_id} (GET) : Obtiene una persona por su id.
### Ejemplo de registro de cliente:
- URL: `http://localhost:8090/api/v1/cliente`
- Metodo: `POST`
- Body:
```json
{
  "informacionPersonal": {
    "nombre": "John",
    "apellidoPaterno": "Doe",
    "apellidoMaterno": "Garden",
    "fechaDeNacimiento": "1990-01-01",
    "direccion": {
      "ubicacionGeografica": "La Paz - Bolivia",
      "zona": "Miraflores",
      "barrio": "La fuente",
      "calle": "Av. 6 de Agosto",
        "numeroDeDomicilio": 2045
    },
    "carnetDeIdentidad": 7058374
  },
  "email": "john.garden@gmail.com",
  "telefono": 73012345,
  "ocupacion": "Ingeniero de sistemas"
}
```
- Response:
```json
{
    "id": "db0aa3b2-c177-4c09-aafe-b3118b1be388",
    "estado": "CREADO",
    "email": "john.garden@gmail.com",
    "telefono": "73012345",
    "ocupacion": "Ingeniero de sistemas"
}
```
### Ejemplo de registro de persona:
- URL: `http://localhost:8090/api/v1/persona`
- Metodo: `POST`
- Body:
```json
{
  "nombre": "Maria",
  "apellidoPaterno": "Torres",
  "apellidoMaterno": "Flores",
  "fechaDeNacimiento": "2000-02-01",
  "direccion": {
    "ubicacionGeografica": "La Paz - Bolivia",
    "zona": "Miraflores",
    "barrio": "La fuente",
    "calle": "Av. 6 de Agosto",
      "numeroDeDomicilio": 2045
  },
  "carnetDeIdentidad": 7058374
}
```
### Ejemplo de registro de referencias:
- URL: `http://localhost:8090/api/v1/cliente/db0aa3b2-c177-4c09-aafe-b3118b1be388/referencias`
- Metodo: `PUT`
- Body:
```json
{
    "referenciaId": "cbe9440f-0ab2-4c23-aecb-8c9ca3dd6b92"
}
```
### Ejemplo de eliminar referencias:
- URL: `http://localhost:8090/api/v1/cliente/db0aa3b2-c177-4c09-aafe-b3118b1be388/referencias`
- Metodo: `DELETE`
- Body:
```json
{
    "referenciaId": "cbe9440f-0ab2-4c23-aecb-8c9ca3dd6b92"
}
```