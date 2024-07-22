# retail-price-service

Una API integral para acceder a información de productos y precios de Inditex.

Acepta como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena. Devuelve como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
- **Documentación OpenAPI 3.2**:
    - URL de acceso: `http://localhost:8080/swagger-ui/index.html`
- **Tecnologías usadas**:
    - Java 17
    - Spring Boot 3.3.1 gestionado con Maven
    - Arquitectura hexagonal para desacoplar el dominio y el negocio
    - Base de datos H2 con Flyway para gestionar las tablas y la estructura de datos
    - JPA con hibernate para acceder a la base de datos
    - Configuración en `application.properties`
    - MapStruct para mapear entidad a DTO de respuesta, añadiendo más desacoplamiento
    - Controller advice para capturar las excepciones generadas en la API y traducir la respuesta
    - Custom converter (`StringToLocalDateTimeConverter`) para convertir a `LocalDateTime` el formato `yyyy-MM-dd HH:mm:ss` en el input
    
- **Testing**:
    - Tests de integración y unitarios para probar la API
    - Tests de integración replican el contexto levantando una instancia H2 de la BBDD de la API apuntando al `application-it.properties`

- **Endpoint generado**: `http://localhost:8080/v1/prices`
    - Parámetros obligatorios:
        - `brandId`: Integer representando el ID de la BBDD
        - `applicationDate`: LocalDateTime que debe estar en rango del priceList deseado (formato: `yyyy-MM-dd HH:mm:ss`)
        - `productId`: Integer representando el ID de la BBDD

    - **Ejemplo de llamada válida**:
      ```
      http://localhost:8080/v1/prices?productId=35455&applicationDate=2020-06-14 00:00:00&brandId=1
      ```

- **Contenerización**:
    - La aplicación ha sido contenerizada con Docker y existe un `docker-compose.yml` en la raíz del proyecto.
    - Por defecto, el contenedor expone al puerto 8080, pero se puede cambiar a discreción.

Para cualquier comentario critica o sugerencia por favor podeis poneros en contacto conmigo a traves de este email **radax20003@gmail.com**
Muchas gracias.
