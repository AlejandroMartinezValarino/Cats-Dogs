# Pet Gallery Backend

API REST desarrollada con **Spring Boot 3.2.0** y **Java 21**, siguiendo **Arquitectura Hexagonal (Ports & Adapters)** combinada con **Vertical Slicing (Screaming Architecture)** y principios **SOLID**.

## 🏗️ Arquitectura

El backend implementa una combinación de dos patrones arquitectónicos complementarios:

### Vertical Slicing (Screaming Architecture)
Organización por **features** (dogs, cats) en lugar de por capas horizontales. Cada feature está completamente encapsulada y contiene toda su lógica relacionada.

### Arquitectura Hexagonal (Ports & Adapters)
Dentro de cada feature, se mantiene la separación hexagonal:
- **Domain**: Núcleo del negocio (entidades, excepciones)
- **Application**: Casos de uso y servicios
- **Infrastructure**: Adaptadores (controllers, API clients)

**Ventajas de esta combinación**:
- ✅ **Alta cohesión**: Todo lo relacionado con una feature está junto
- ✅ **Baja acoplamiento**: Features independientes, cambios aislados
- ✅ **Escalabilidad**: Fácil agregar nuevas features sin afectar existentes
- ✅ **Testabilidad**: Dominio testeable sin dependencias externas
- ✅ **Mantenibilidad**: Código fácil de encontrar y modificar

### Estructura de Paquetes

```
com.pets
├── dogs/                     # Feature: Perros
│   ├── application/
│   │   ├── port/
│   │   │   ├── in/          # Casos de uso (interfaces)
│   │   │   │   ├── GetDogImagesUseCase
│   │   │   │   ├── GetDogBreedsUseCase
│   │   │   │   └── FilterDogImagesByBreedUseCase
│   │   │   └── out/         # Interfaces externas
│   │   │       └── DogApiPort
│   │   └── service/         # Implementación casos de uso
│   │       ├── GetDogImagesService
│   │       ├── GetDogBreedsService
│   │       └── FilterDogImagesByBreedService
│   ├── domain/              # Entidades y lógica de dominio
│   │   ├── entity/
│   │   │   ├── DogImage
│   │   │   └── DogBreed
│   │   └── exception/
│   │       ├── DogImageNotFoundException
│   │       ├── DogBreedNotFoundException
│   │       ├── DogApiException
│   │       └── DogApiConnectionException
│   └── infrastructure/
│       └── adapter/
│           ├── in/          # REST Controllers
│           │   └── DogController
│           └── out/         # API Clients
│               ├── DogApiClient
│               ├── DogApiResponse
│               └── DogApiListResponse
│
├── cats/                     # Feature: Gatos
│   ├── application/
│   │   ├── port/
│   │   │   ├── in/
│   │   │   │   ├── GetCatImagesUseCase
│   │   │   │   ├── GetCatBreedsUseCase
│   │   │   │   ├── FilterCatImagesByBreedUseCase
│   │   │   │   └── GetCatBreedDetailsUseCase
│   │   │   └── out/
│   │   │       └── CatApiPort
│   │   └── service/
│   │       ├── GetCatImagesService
│   │       ├── GetCatBreedsService
│   │       ├── FilterCatImagesByBreedService
│   │       └── GetCatBreedDetailsService
│   ├── domain/
│   │   ├── entity/
│   │   │   ├── CatImage
│   │   │   └── CatBreed
│   │   └── exception/
│   │       ├── CatImageNotFoundException
│   │       ├── CatBreedNotFoundException
│   │       ├── CatApiException
│   │       └── CatApiConnectionException
│   └── infrastructure/
│       └── adapter/
│           ├── in/
│           │   └── CatController
│           └── out/
│               └── CatApiClient
│
└── shared/                   # Código compartido entre features
    ├── config/              # Configuraciones globales
    │   ├── CorsConfig
    │   └── RestTemplateConfig
    └── exception/           # Excepciones globales
        └── ErrorResponse
```

## 🔄 Orden de Desarrollo (Arquitectura Hexagonal)

Al implementar una nueva feature, sigue este orden lógico de creación, **de adentro hacia afuera**:

### 1. Domain Layer (Núcleo del negocio)
- **Entidades de dominio** (`domain/entity/`):
  - Representan los conceptos del negocio
  - Ejemplo: `DogImage.java`, `DogBreed.java`
- **Excepciones de dominio** (`domain/exception/`):
  - Excepciones específicas del dominio
  - Ejemplo: `DogImageNotFoundException.java`

### 2. Ports Out (Interfaces de salida)
- **Interfaces para servicios externos** (`application/port/out/`):
  - Contratos para interactuar con APIs externas
  - Ejemplo: `DogApiPort.java`

### 3. Adapters Out (Implementaciones de salida)
- **Clientes de APIs externas** (`infrastructure/adapter/out/`):
  - Implementan las interfaces de `port/out`
  - Ejemplo: `DogApiClient.java`
- **DTOs de respuesta**:
  - Clases para mapear respuestas de APIs externas
  - Ejemplo: `DogApiResponse.java`

### 4. Ports In (Casos de uso)
- **Interfaces de casos de uso** (`application/port/in/`):
  - Definen los casos de uso de la aplicación
  - Ejemplo: `GetDogImagesUseCase.java`

### 5. Services (Implementaciones de casos de uso)
- **Servicios de aplicación** (`application/service/`):
  - Implementan las interfaces de `port/in`
  - Delegan a `port/out` para obtener datos
  - Ejemplo: `GetDogImagesService.java`

### 6. Adapters In (Controladores REST)
- **REST Controllers** (`infrastructure/adapter/in/`):
  - Exponen endpoints HTTP
  - Usan los casos de uso (`port/in`)
  - Ejemplo: `DogController.java`

### 7. Exception Handlers (Manejo de errores)
- **Manejadores globales de excepciones**:
  - Centralizan el manejo de excepciones
  - Convierten excepciones de dominio a respuestas HTTP

**Principio clave**: Siempre desarrollar de adentro hacia afuera. El dominio no debe conocer nada de infraestructura, pero la infraestructura depende del dominio.

## 🚀 Inicio Rápido

### Prerrequisitos

- Java 21 o superior
- Maven 3.6+
- No se requieren API keys (las APIs utilizadas son gratuitas)

### Configuración

**Importante**: Este proyecto requiere **Java 21**. Si tu sistema tiene múltiples versiones de Java instaladas, asegúrate de que Maven use Java 21.

#### Opción 1: Usar el script wrapper (Recomendado)
El proyecto incluye un script `mvnw` que configura automáticamente Java 21:

1. **Compilar el proyecto**:
```bash
./mvnw clean install
```

2. **Ejecutar la aplicación**:
```bash
./mvnw spring-boot:run
```

#### Opción 2: Configurar JAVA_HOME manualmente
Si prefieres usar `mvn` directamente, configura `JAVA_HOME` antes de ejecutar:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH=$JAVA_HOME/bin:$PATH
mvn clean install
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## 📡 Endpoints

### Perros (Dogs)

#### Imágenes
- `GET /api/dogs/images/random`
  - Obtiene una imagen aleatoria de perro
  - Respuesta: `{"url": "...", "breed": null}`

- `GET /api/dogs/images/random/{limit}`
  - Obtiene múltiples imágenes aleatorias
  - Parámetros:
    - `limit`: Número de imágenes (1-50)

- `GET /api/dogs/breeds/{breed}`
  - Obtiene imágenes filtradas por raza
  - Parámetros:
    - `breed`: Nombre de la raza (ej: "bulldog")

- `GET /api/dogs/breeds/{breed}/{subBreed}`
  - Obtiene imágenes filtradas por subraza
  - Parámetros:
    - `breed`: Nombre de la raza (ej: "bulldog")
    - `subBreed`: Nombre de la subraza (ej: "boston")

#### Razas
- `GET /api/dogs/breeds`
  - Obtiene todas las razas disponibles
  - Respuesta: Lista de `DogBreed` con nombre y sub-razas

### Gatos (Cats)

#### Imágenes
- `GET /api/cats/images/random`
  - Obtiene una imagen aleatoria de gato
  - Respuesta: `CatImage` con URL y metadatos

- `GET /api/cats/images/random/{limit}`
  - Obtiene múltiples imágenes aleatorias
  - Parámetros:
    - `limit`: Número de imágenes (1-50)

- `GET /api/cats/breeds/{breedId}`
  - Obtiene imágenes filtradas por raza
  - Parámetros:
    - `breedId`: ID de la raza (ej: "abys")

#### Razas
- `GET /api/cats/breeds`
  - Obtiene todas las razas disponibles
  - Respuesta: Lista de `CatBreed` con información completa

- `GET /api/cats/breeds/{breedId}/details`
  - Obtiene detalles completos de una raza
  - Respuesta: `CatBreed` con todas las características

## 🔧 Configuración

### application.properties

```properties
# Server
server.port=${SERVER_PORT:8080}

# CORS
cors.allowed.origins=${CORS_ALLOWED_ORIGINS:http://localhost:3000}

# Logging
logging.level.com.pets=INFO
logging.level.org.springframework.web=INFO
```

### Variables de Entorno

- `SERVER_PORT` (opcional): Puerto del servidor (default: 8080)
- `CORS_ALLOWED_ORIGINS` (opcional): Orígenes permitidos para CORS separados por coma (default: `http://localhost:3000,https://pet-gallery.alejandrotech.eu`)

**Nota**: Las APIs utilizadas ([Dog CEO API](https://dog.ceo/dog-api/) y [The Cat API](https://thecatapi.com/)) son gratuitas y no requieren API keys para uso básico.

## 🎯 Principios SOLID Implementados

### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad:
- `DogController`: Solo maneja peticiones HTTP de perros
- `GetDogImagesService`: Solo implementa la lógica de obtener imágenes
- `DogApiClient`: Solo se comunica con Dog CEO API
- `DogImage`: Solo representa una imagen de perro

### Open/Closed Principle (OCP)
Abierto para extensión, cerrado para modificación:
- Interfaces (`port/in` y `port/out`) permiten nuevas implementaciones
- Nuevas features pueden agregarse sin modificar código existente
- Ejemplo: Agregar feature `birds/` sin tocar `dogs/` o `cats/`

### Liskov Substitution Principle (LSP)
Las implementaciones son intercambiables:
- Cualquier implementación de `DogApiPort` puede sustituirse
- Las features son intercambiables a nivel de arquitectura
- Ejemplo: Mock de `DogApiPort` para testing

### Interface Segregation Principle (ISP)
Interfaces segregadas y específicas:
- `GetDogImagesUseCase` separado de `GetDogBreedsUseCase`
- `DogApiPort` separado de `CatApiPort`
- Cada feature tiene sus propias interfaces

### Dependency Inversion Principle (DIP)
Dependencias hacia abstracciones:
- Los servicios dependen de interfaces (`port/in` y `port/out`)
- Los controladores dependen de interfaces de casos de uso
- Las implementaciones concretas se inyectan mediante Spring
- Las features no dependen entre sí, solo de `shared`

## 🔌 APIs Externas Utilizadas

### Dog CEO API
- **URL**: https://dog.ceo/dog-api/
- **Tipo**: API gratuita y open source
- **Características**: 
  - No requiere API key
  - Proporciona imágenes de perros y listas de razas
  - Soporte para razas y subrazas
  - Endpoints REST simples

### The Cat API
- **URL**: https://thecatapi.com/
- **Tipo**: API gratuita (con opciones premium)
- **Características**:
  - Uso básico sin API key
  - Proporciona imágenes de gatos y datos detallados de razas
  - Información completa sobre características de razas
  - Metadatos extensos por imagen

## 📦 Dependencias Principales

- **Spring Boot Starter Web**: Framework web y REST
- **Spring Boot Starter Validation**: Validación de datos
- **Lombok**: Reducción de boilerplate
- **Jackson**: Serialización/deserialización JSON
- **RestTemplate**: Cliente HTTP para comunicación con APIs externas

## 🧪 Testing

Ejecutar tests:
```bash
./mvnw test
```

O con `mvn` (si JAVA_HOME está configurado):
```bash
mvn test
```

## 📝 Notas de Desarrollo

- El proyecto utiliza **Maven** como gestor de dependencias
- **Arquitectura Vertical**: Organización por features facilita el desarrollo y mantenimiento
- **Hexagonal dentro de cada feature**: Mantiene la separación de concerns y facilita el testing
- Los adaptadores pueden cambiarse sin afectar la lógica de negocio
- Las APIs externas están abstraídas mediante ports
- Cada feature es independiente: cambios en `dogs/` no afectan `cats/`
- El código compartido está en `shared/` para evitar duplicación
- **Manejo de excepciones**: Centralizado mediante `@ControllerAdvice` para respuestas HTTP consistentes
- **CORS**: Configurado para permitir comunicación con el frontend

## 🚀 Estado del Proyecto

- ✅ Feature de perros: Completamente implementada y funcionando
- ✅ Feature de gatos: Completamente implementada y funcionando
- ✅ Manejo de excepciones: Implementado con handlers globales
- ✅ CORS configurado: Listo para integración con frontend
- ✅ Arquitectura hexagonal: Implementada en ambas features
- ✅ Principios SOLID: Aplicados en toda la arquitectura
- ✅ Configuración Railway: Lista para despliegue
