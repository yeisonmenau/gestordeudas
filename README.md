# Gestor de Deudas API

## Descripción
Sistema de gestión de deudas que permite administrar personas y sus deudas asociadas. Implementado con arquitectura hexagonal usando Spring Boot y base de datos relacional.

## Enfoque de Desarrollo: TDD (Test Driven Development)
Durante el desarrollo del proyecto Gestor de Deudas API, se aplicó la metodología TDD (Desarrollo Guiado por Pruebas), lo cual garantizó una alta cobertura de pruebas y diseño orientado al comportamiento.


## Requisitos del Sistema

### Requisitos Técnicos
- **Java**: Versión 21 o superior (Proyecto configurado para Java 21)
- **Framework**: Spring Boot 3.x
- **Base de Datos**: H2 / PostgreSQL si se desea
- **Build Tool**: Gradle
- **Documentación API**: Swagger/OpenAPI 3

### Requisitos Funcionales
- Gestión completa de personas (CRUD)
- Gestión completa de deudas (CRUD)
- Asociación de deudas a personas específicas
- Funcionalidad para saldar deudas
- Consulta de deudas por persona
- Validaciones de integridad de datos
- Manejo centralizado de excepciones

## Funcionalidades del Sistema

### Gestión de Personas
- **Registrar Persona**: Crear nueva persona con validaciones de campos obligatorios y cédula única
- **Consultar Personas**: Visualizar listado completo de personas con edad calculada automáticamente
- **Actualizar Persona**: Modificar datos de persona existente
- **Eliminar Persona**: Remover persona del sistema (considera integridad referencial)

### Gestión de Deudas
- **Registrar Deuda**: Crear nueva deuda asociada a una persona existente
- **Consultar Deudas**: Visualizar todas las deudas del sistema
- **Actualizar Deuda**: Modificar datos de deuda existente
- **Eliminar Deuda**: Remover deuda del sistema
- **Saldar Deuda**: Marcar deuda como pagada
- **Consultar Deudas por Persona**: Filtrar deudas de persona específica

## Arquitectura

### Estructura del Proyecto
```
src/main/java/com/yeisonmenau/gestordeudas/
├── application/
│   ├── exception/          # Excepciones de negocio
│   ├── service/           # Servicios de aplicación
│   └── usecase/           # Casos de uso (interfaces)
├── domain/
│   ├── persona/
│   │   ├── model/         # Modelo de dominio Persona
│   │   └── out/           # Puerto de salida PersonaRepository
│   └── deuda/
│       ├── model/         # Modelo de dominio Deuda
│       └── out/           # Puerto de salida DeudaRepository
├── infrastructure/
│   ├── adapter/           # Adaptadores JPA
│   ├── config/            # Configuración de beans
│   ├── controller/        # Controladores REST
│   ├── dto/               # DTOs de request/response
│   ├── entity/            # Entidades JPA
│   └── mapper/            # Mappers entre capas
└── GestordeudasApplication.java
```

### Patrones Implementados
- **Arquitectura Hexagonal**: Separación clara entre dominio, aplicación e infraestructura
- **Repository Pattern**: Abstracción de acceso a datos
- **DTO Pattern**: Transferencia de datos entre capas
- **Mapper Pattern**: Conversión entre objetos de diferentes capas

## Base de Datos

### Modelo de Datos

<img width="1008" height="653" alt="image" src="https://github.com/user-attachments/assets/1c782a50-6de1-4a18-81f0-aa1ad1315f10" />

### Restricciones
- Cédula de persona debe ser única
- Una deuda debe estar asociada a una persona existente
- Integridad referencial entre deudas y personas

## API Endpoints

### Personas

#### POST /persona
**Descripción**: Crear nueva persona  
**Request Body**:
```json
{
  "personaCedula": "string (requerido, no vacío)",
  "personaNombre": "string (requerido, no vacío)", 
  "personaFechaNacimiento": "date (requerido)"
}
```
**Response**: `201 CREATED`
```json
{
  "personaId": long,
  "personaCedula": "string",
  "personaNombre": "string", 
  "personaEdad": integer
}
```

#### GET /persona
**Descripción**: Obtener todas las personas  
**Response**: `200 OK`
```json
[
  {
    "personaId": long,
    "personaCedula": "string",
    "personaNombre": "string",
    "personaEdad": integer
  }
]
```

#### PUT /persona/{idPersona}
**Descripción**: Actualizar persona existente  
**Path Parameter**: `idPersona` (Long)  
**Request Body**: Mismo formato que POST  
**Response**: `200 OK` - Mismo formato que POST response

#### DELETE /persona/{idPersona}
**Descripción**: Eliminar persona  
**Path Parameter**: `idPersona` (Long)  
**Response**: `200 OK`
```json
"Persona con ID {id} eliminada correctamente."
```

### Deudas

#### POST /deuda
**Descripción**: Crear nueva deuda  
**Request Body**:
```json
{
  "personaId": long (requerido),
  "deudaValor": double (requerido),
  "deudaFecha": "date (requerido)",
  "deudaFechaMaximaPago": "date (requerido)"
}
```
**Response**: `201 CREATED`
```json
{
  "deudaId": long,
  "nombrePersona": "string",
  "cedulaPersona": "string",
  "deudaValor": double,
  "deudaFecha": "date",
  "deudaFechaMaximaPago": "date",
  "pagado": boolean
}
```

#### GET /deuda
**Descripción**: Obtener todas las deudas  
**Response**: `200 OK` - Array del formato response anterior

#### PUT /deuda/{idDeuda}
**Descripción**: Actualizar deuda existente  
**Path Parameter**: `idDeuda` (Long)  
**Request Body**: Mismo formato que POST  
**Response**: `200 OK` - Mismo formato que POST response

#### DELETE /deuda/{idDeuda}
**Descripción**: Eliminar deuda  
**Path Parameter**: `idDeuda` (Long)  
**Response**: `200 OK`
```json
"Deuda con ID {id} eliminada correctamente."
```

#### PUT /deuda/saldar/{idDeuda}
**Descripción**: Saldar deuda (marcar como pagada)  
**Path Parameter**: `idDeuda` (Long)  
**Response**: `200 OK`
```json
"Deuda de {nombrePersona} con ID {id} por un valor de ${valor} saldada correctamente."
```

#### GET /deuda/persona/{idPersona}
**Descripción**: Obtener deudas de una persona específica  
**Path Parameter**: `idPersona` (Long)  
**Response**: `200 OK` - Array del formato response de deudas

## Manejo de Errores

### Códigos de Respuesta
- `400 BAD REQUEST`: Datos inválidos, violación de restricciones
- `404 NOT FOUND`: Recurso no encontrado
- `500 INTERNAL SERVER ERROR`: Error interno del servidor

### Formato de Errores
```json
{
  "mensaje": "Descripción del error"
}
```

### Validaciones
- **Campos obligatorios**: Cédula y nombre de persona no pueden estar vacíos
- **Cédula única**: No se permite cédula duplicada
- **Integridad referencial**: Deuda debe asociarse a persona existente

## Casos de Uso

### CU001 - Registrar Persona
**Actor**: Usuario del sistema  
**Descripción**: Permite registrar una nueva persona en el sistema  
**Flujo Principal**: Usuario proporciona datos → Sistema valida → Persona creada  
**Flujo Alternativo**: Si cédula existe, retorna error "No se permite cédula repetida"

### CU002 - Consultar Personas
**Actor**: Usuario del sistema  
**Descripción**: Permite visualizar todas las personas registradas  
**Funcionalidad**: Retorna lista con edad calculada automáticamente

### CU003 - Actualizar Persona
**Actor**: Usuario del sistema  
**Descripción**: Permite modificar datos de una persona existente  
**Flujo Alternativo**: Si ID no existe, sistema retorna "Persona no encontrada"

### CU004 - Eliminar Persona
**Actor**: Usuario del sistema  
**Descripción**: Permite eliminar una persona del sistema  
**Consideración**: Evalúa integridad referencial con deudas

### CU005 - Registrar Deuda
**Actor**: Usuario del sistema  
**Descripción**: Permite registrar una nueva deuda asociada a una persona  
**Validación**: Persona debe existir en el sistema

### CU006 - Consultar Deudas
**Actor**: Usuario del sistema  
**Descripción**: Permite visualizar todas las deudas registradas  
**Información**: Incluye datos de la persona asociada

### CU007 - Actualizar Deuda
**Actor**: Usuario del sistema  
**Descripción**: Permite modificar datos de una deuda existente  
**Flujo Alternativo**: Si ID no existe, retorna "Deuda no encontrada"

### CU008 - Eliminar Deuda
**Actor**: Usuario del sistema  
**Descripción**: Permite eliminar una deuda del sistema  
**Validación**: Deuda debe existir

### CU009 - Saldar Deuda
**Actor**: Usuario del sistema  
**Descripción**: Permite marcar una deuda como pagada  
**Funcionalidad**: Cambia estado 'pagado' a true

### CU010 - Consultar Deudas por Persona
**Actor**: Usuario del sistema  
**Descripción**: Permite consultar todas las deudas de una persona específica  
**Filtro**: Por ID de persona

## Configuración e Inicio del Proyecto

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+
- IDE compatible con Spring Boot


### Configuración de Base de Datos
Para desarrollo, el proyecto usa H2 en memoria. Para cambiarlo a Postgres, configurar en `application.properties`:

### Acceso a la Aplicación
- **API Base URL**: `http://localhost:8080`
- **Documentación Swagger**: `http://localhost:8080/documentacion`
- **Base de Datos H2 Console**: `http://localhost:8080/h2-console`

### Credenciales H2 (si se usa):

<img width="450" height="314" alt="image" src="https://github.com/user-attachments/assets/77b871cd-8601-46af-9bc7-17b6192b4b96" />

## Swagger/OpenAPI

La documentación interactiva de la API está disponible en `/documentacion` una vez iniciada la aplicación. Permite:
- Explorar todos los endpoints
- Probar las funcionalidades directamente
- Ver modelos de request/response
- Entender códigos de respuesta

## Testing

El proyecto incluye tests unitarios para controladores usando:
- **JUnit 5**: Framework de testing
- **Mockito**: Mocking de dependencias
- **Spring Boot Test**: Integración con Spring

## Tecnologías Utilizadas

- **Spring Boot 3.x**: Framework principal
- **Spring Data JPA**: Persistencia de datos  
- **Spring Web**: API REST
- **H2 Database**: Base de datos en memoria (desarrollo)
- **Swagger/OpenAPI**: Documentación API
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestión de dependencias
- **JUnit 5 + Mockito**: Testing

## Arquitectura Hexagonal

El proyecto implementa arquitectura hexagonal con:
- **Dominio**: Lógica de negocio pura (Persona, Deuda)
- **Aplicación**: Casos de uso y servicios
- **Infraestructura**: Adaptadores, controladores, persistencia

Esta estructura asegura:
- Separación de responsabilidades
- Testabilidad
- Flexibilidad para cambios tecnológicos
- Mantenibilidad del código