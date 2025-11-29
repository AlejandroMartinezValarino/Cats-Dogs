# Pet Gallery Backend

API REST desarrollada con **Spring Boot 3.2.0** y **Java 21**, siguiendo **Arquitectura Vertical (Screaming Architecture)** combinada con **Arquitectura Hexagonal** y principios SOLID.

## 🏗️ Arquitectura

El backend utiliza una combinación de **Vertical Slicing (Screaming Architecture)** y **Arquitectura Hexagonal**:

- **Vertical Slicing**: Organización por features (dogs, cats) en lugar de por capas horizontales
- **Hexagonal dentro de cada feature**: Cada feature mantiene la separación hexagonal (ports, adapters, domain)

Esta combinación ofrece:
- ✅ **Mejor cohesión**: Todo lo relacionado con una feature está junto
- ✅ **Independencia**: Cada feature puede evolucionar independientemente
- ✅ **Escalabilidad**: Fácil agregar nuevas features sin afectar las existentes
- ✅ **Mantenibilidad**: Código más fácil de encontrar y modificar

### Estructura de Paquetes (Vertical Slicing + Hexagonal)

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
│   │       └── FilterDogImagesService
│   ├── domain/              # Entidades y lógica de dominio
│   │   ├── entity/
│   │   │   ├── DogImage
│   │   │   └── DogBreed
│   │   └── exception/
│   │       ├── DogImageNotFoundException
│   │       └── DogBreedNotFoundException
│   └── infrastructure/
│       └── adapter/
│           ├── in/          # REST Controllers
│           │   └── DogController
│           └── out/         # API Clients
│               └── DogApiClient
│
├── cats/                     # Feature: Gatos
│   ├── application/
│   │   ├── port/
│   │   │   ├── in/
│   │   │   │   ├── GetCatImagesUseCase
│   │   │   │   ├── GetCatBreedsUseCase
│   │   │   │   └── FilterCatImagesByBreedUseCase
│   │   │   └── out/
│   │   │       └── CatApiPort
│   │   └── service/
│   │       ├── GetCatImagesService
│   │       ├── GetCatBreedsService
│   │       └── FilterCatImagesService
│   ├── domain/
│   │   ├── entity/
│   │   │   ├── CatImage
│   │   │   └── CatBreed
│   │   └── exception/
│   │       ├── CatImageNotFoundException
│   │       └── CatBreedNotFoundException
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
        └── ApiException
```

## 🚀 Inicio Rápido

### Prerrequisitos

- Java 21 o superior
- Maven 3.6+
- No se requieren API keys (las APIs utilizadas son gratuitas)

### Configuración

1. **Compilar el proyecto**:
```bash
mvn clean install
```

2. **Ejecutar la aplicación**:
```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## 📡 Endpoints

### Perros (Dogs)

#### Imágenes
- `GET /api/dogs/images/random`
  - Obtiene una imagen aleatoria de perro
  - Respuesta: `{"url": "...", "breed": null}`

- `GET /api/dogs/images/random/list?limit=5`
  - Obtiene múltiples imágenes aleatorias
  - Query params:
    - `limit`: Número de imágenes (default: 10, max: 50)

- `GET /api/dogs/images?breed=afghan`
  - Obtiene imágenes filtradas por raza
  - Query params:
    - `breed`: Nombre de la raza (requerido)

- `GET /api/dogs/images?breed=airedale&subBreed=terrier`
  - Obtiene imágenes filtradas por subraza
  - Query params:
    - `breed`: Nombre de la raza (requerido)
    - `subBreed`: Nombre de la subraza (requerido)

#### Razas
- `GET /api/dogs/breeds`
  - Obtiene todas las razas disponibles
  - Respuesta: Lista de `DogBreed` con nombre y sub-razas

### Gatos (Cats)
- *(En desarrollo)*

## 🔧 Configuración

### application.properties

```properties
# Server
server.port=${SERVER_PORT:8080}

# API URLs (no se requieren API keys)
dog.api.url=https://dog.ceo/api/breeds
cat.api.url=https://api.thecatapi.com/v1

# CORS
cors.allowed.origins=${CORS_ALLOWED_ORIGINS:http://localhost:3000}
```

### Variables de Entorno

- `SERVER_PORT` (opcional): Puerto del servidor (default: 8080)
- `CORS_ALLOWED_ORIGINS` (opcional): Orígenes permitidos para CORS (default: http://localhost:3000)

**Nota**: Las APIs utilizadas ([Dog CEO API](https://dog.ceo/dog-api/) y [The Cat API](https://thecatapi.com/)) son gratuitas y no requieren API keys para uso básico.

## 🧪 Testing

Ejecutar tests:
```bash
mvn test
```

## 📦 Dependencias Principales

- **Spring Boot Starter Web**: Framework web y REST
- **Spring Boot Starter Validation**: Validación de datos
- **Lombok**: Reducción de boilerplate
- **Jackson**: Serialización/deserialización JSON
- **RestTemplate**: Cliente HTTP para comunicación con APIs externas

## 🎯 Principios SOLID Implementados

### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad:
- `DogController`: Solo maneja peticiones HTTP de perros
- `GetDogImagesService`: Solo implementa la lógica de obtener imágenes de perros
- `DogApiClient`: Solo se comunica con Dog CEO API

### Open/Closed Principle (OCP)
Las interfaces permiten extensión sin modificación:
- `DogApiPort` puede tener múltiples implementaciones
- Nuevas features pueden agregarse sin modificar código existente (dogs y cats son independientes)

### Liskov Substitution Principle (LSP)
Las implementaciones son intercambiables:
- Cualquier implementación de `DogApiPort` puede sustituirse sin romper el código
- Las features son intercambiables a nivel de arquitectura

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
  - Endpoints REST simples y directos

### The Cat API
- **URL**: https://thecatapi.com/
- **Tipo**: API gratuita (con opciones premium)
- **Características**:
  - Uso básico sin API key
  - Proporciona imágenes de gatos y datos detallados de razas
  - Información completa sobre características de razas

## 📝 Notas de Desarrollo

- El proyecto utiliza **Maven** como gestor de dependencias
- **Arquitectura Vertical**: Organización por features (dogs, cats) facilita el desarrollo y mantenimiento
- **Hexagonal dentro de cada feature**: Mantiene la separación de concerns y facilita el testing
- Los adaptadores pueden cambiarse sin afectar la lógica de negocio
- Las APIs externas están abstraídas mediante ports
- Cada feature es independiente: cambios en `dogs/` no afectan `cats/`
- El código compartido está en `shared/` para evitar duplicación
- **Manejo de excepciones**: Centralizado mediante `@ControllerAdvice` para respuestas HTTP consistentes

## 🚀 Estado del Proyecto

- ✅ Feature de perros: Completamente implementada y funcionando
- 🚧 Feature de gatos: Estructura creada, pendiente de implementación
- ✅ Manejo de excepciones: Implementado con `DogExceptionHandler`
- ✅ CORS configurado: Listo para integración con frontend
