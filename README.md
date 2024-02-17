# Proyecto de Compras y Ventas de Productos - Microservicios

Este proyecto está enfocado en la implementación de un sistema de compras y ventas de productos basado en microservicios, utilizando tecnologías modernas y buenas prácticas de desarrollo. A continuación, se describen los microservicios principales y las tecnologías utilizadas en este proyecto.

## Microservicios

1. **Producto:** Gestiona la información de los productos disponibles para la venta.
2. **Compras:** Administra el proceso de compra de productos por parte de los usuarios.
3. **Ventas:** Controla el proceso de venta de productos y la generación de reportes.
4. **Usuario:** Gestiona la información de los usuarios del sistema.
5. **Proveedor:** Administra la información de los proveedores de productos.
6. **Config Server:** Servidor de configuración centralizada para los microservicios.
7. **Eureka Server:** Servidor de registro y descubrimiento para los microservicios.
8. **Keycloak:** Servidor de autenticación y autorización para garantizar la seguridad.
9. **API Gateway:** Encargado de recibir todas las peticiones externas y dirigirlas al microservicio correspondiente.

## Tecnologías Utilizadas

- **Java 17:** Lenguaje de programación utilizado para el desarrollo de los microservicios.
- **Spring Cloud:** Framework de Spring utilizado para la implementación de microservicios.
- **MongoDB:** Base de datos NoSQL utilizada para almacenar la información de los productos, compras, ventas, usuarios y proveedores.
- **Kafka:** Plataforma de mensajería distribuida utilizada para la comunicación entre los microservicios.
- **Programación Reactiva con WebFlux:** Implementación reactiva utilizada para mejorar la escalabilidad y la resiliencia de los microservicios.
- **Docker:** Tecnología de contenerización utilizada para desplegar y gestionar los microservicios de forma eficiente.
- **Resiliencia y Balanceador de Carga:** Implementación de patrones y herramientas para garantizar la resiliencia y el balanceo de carga en el sistema.
- **OpenAPI:** Especificación utilizada para describir y documentar los servicios de la API REST.
- **Lombok:** Biblioteca de Java que ayuda a reducir el código boilerplate en el desarrollo de Java.
- **MapStruct:** Framework utilizado para mapear objetos de un tipo a otro de forma declarativa y fácil. 
- **JUnit 5 y Mockito:** Utilizados para realizar pruebas unitarias en el proyecto.

## Ejecución del Proyecto

Para ejecutar el proyecto localmente, asegúrate de tener instalado Docker y Docker Compose. Luego, clona el repositorio y ejecuta el siguiente comando en la raíz del proyecto:

```bash
docker-compose up
