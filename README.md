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
- /api/v1/cliente/{cliente_id} (GET) : Obtiene un cliente a detalle por su id.
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
Para agregar referencias a un cliente, se necesita el referenceId de otro CLIENTE o PERSONA registrada, no se puede agregar referencias a un cliente que no este registrado.
Tampoco un cliente puede referenciarse a si mismo, ya sea por su misma Id de cliente o por su Id de persona.
Tampoco se puede agregar varias veces una misma referencia que ya este registrada.
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
### Ejemplo de obtener lista de clientes por accesibilidad:
En esta lista se puede ver los clientes con su respectiva accesibilidad, que es calculada en base a la cantidad de referencias personales y clientes que tiene.
- URL: `http://localhost:8090/api/v1/cliente`
- Metodo: `GET`
- Response:
```json
[
  {
  "id": "7cc1f64d-ef4e-48cc-871f-f4f3edf0cd15",
  "email": "john.garden@gmail.com",
  "telefono": "73012345",
  "ocupacion": "Ingeniero de sistemas",
  "accesibilidad": "BUENA"
  },
  {
  "id": "bd8fed10-44e6-4d80-bdc2-881e06452063",
  "email": "Emilia91@yahoo.com",
  "telefono": "7305793",
  "ocupacion": "International Paradigm Associate",
  "accesibilidad": "REGULAR"
  },
  {
  "id": "13603591-3675-4e01-9b46-46d103bc746b",
  "email": "Ivah73@yahoo.com",
  "telefono": "7305793",
  "ocupacion": "Regional Research Architect",
  "accesibilidad": "MALA"
  },
  {
  "id": "ae6ce832-2b50-46b4-a479-80c3fa382af9",
  "email": "Genesis.Runolfsdottir@gmail.com",
  "telefono": "7305793",
  "ocupacion": "International Directives Developer",
  "accesibilidad": "NULA"
  }
]
```
### Ejemplo de obtener un cliente a detalle:
Con este endpoint se puede obtener un cliente a detalle, con sus referencias personales y clientes.
Podemos ver a detalle una lista de los Ids de referencias personales y clientes, ademas de la cantidad total de referencias (persona, cliente).
Tambien podemos ver el estado y la accesisbilidad del cliente, que es calculada en base a la cantidad de referencias personales y clientes que tiene.

- URL: `http://localhost:8090/api/v1/cliente/7cc1f64d-ef4e-48cc-871f-f4f3edf0cd15`
- Metodo: `GET`
- Response:
```json
{
    "id": "7cc1f64d-ef4e-48cc-871f-f4f3edf0cd15",
    "informacionPersonal": {
        "id": "61577183-8ebd-4f2d-b7c9-af7e69871945",
        "nombre": "John",
        "apellidoPaterno": "Doe",
        "apellidoMaterno": "Garden",
        "fechaDeNacimiento": "1990-01-01",
        "direccion": {
            "id": "0590c4f9-b8e3-43be-ae2c-e66b35d4d1f1",
            "ubicacionGeografica": "La Paz - Bolivia",
            "zona": "Miraflores",
            "barrio": "La fuente",
            "calle": "Av. 6 de Agosto",
            "numeroDeDomicilio": 2045
        },
        "carnetDeIdentidad": 7058374
    },
    "email": "john.garden@gmail.com",
    "telefono": "73012345",
    "ocupacion": "Ingeniero de sistemas",
    "referenciasPersonales": {
        "id": "42d2cff9-cbd3-4ea1-807c-52f3029c5f33",
        "listReferenciaCliente": [
            "bd8fed10-44e6-4d80-bdc2-881e06452063"
        ],
        "listReferenciaPersona": [
            "14c1ab1f-16a3-44c6-ab3e-89a420dc1e69",
            "109d0455-fd6d-4b75-9aeb-7e368c2e69f7"
        ],
        "totalReferencias": 3,
        "referenciasClientes": 1
    },
    "estado": "ACTIVO",
    "accesibilidad": "BUENA"
}
```
### Manejo de excepciones:
- Cuando se registra un cliente, se valida que el email no esten registrados en otro cliente.
- Cuando se consulta un cliente por su id, se valida que el cliente exista.
- Cuando se consulta por la referencia de un cliente o persona por su id, se valida que la referencia exista.
- Cuando se registra una referencia, se valida que el cliente no se este referenciando a si mismo.
- Cuando se registra un cliente, se valida que su edad sea mayor a 20 anios.