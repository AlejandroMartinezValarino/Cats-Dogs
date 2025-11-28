# Pet Gallery Backend

API REST desarrollada con **Spring Boot 3.2.0** y **Java 17**, siguiendo **Arquitectura Vertical (Screaming Architecture)** combinada con **Arquitectura Hexagonal** y principios SOLID.

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
│   │   ├── model/
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
│   │   ├── model/
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

- Java 17 o superior
- Maven 3.6+
- API Key de [The Dog API](https://thedogapi.com/)

### Configuración

1. **Clonar y configurar variables de entorno**:
```bash
cp .env.example .env
# Editar .env con tus API keys
```

2. **Compilar el proyecto**:
```bash
mvn clean install
```

3. **Ejecutar la aplicación**:
```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## 📡 Endpoints

### Imágenes

- `GET /api/images?type={dogs|cats}&limit={number}&page={number}`
  - Obtiene imágenes aleatorias de perros o gatos
  - Query params:
    - `type`: Tipo de mascota (dogs/cats)
    - `limit`: Número de imágenes (default: 10, max: 25)
    - `page`: Número de página (default: 0)

- `GET /api/images?type={dogs|cats}&breedId={breedId}`
  - Obtiene imágenes filtradas por raza
  - Query params:
    - `type`: Tipo de mascota (dogs/cats)
    - `breedId`: ID de la raza

### Razas

- `GET /api/breeds?type={dogs|cats}`
  - Obtiene todas las razas disponibles
  - Query params:
    - `type`: Tipo de mascota (dogs/cats)

- `GET /api/breeds/{breedId}?type={dogs|cats}`
  - Obtiene detalles de una raza específica
  - Path params:
    - `breedId`: ID de la raza
  - Query params:
    - `type`: Tipo de mascota (dogs/cats)

## 🔧 Configuración

### application.properties

```properties
# Server
server.port=${SERVER_PORT:8080}

# API Keys
dog.api.key=${DOG_API_KEY}
dog.api.url=https://api.thedogapi.com/v1
cat.api.key=${CAT_API_KEY:}
cat.api.url=https://api.publicapis.org/entries?category=animals

# CORS
cors.allowed.origins=${CORS_ALLOWED_ORIGINS:http://localhost:3000}
```

### Variables de Entorno

- `DOG_API_KEY` (requerido): API key de The Dog API
- `CAT_API_KEY` (opcional): API key de Cats API
- `SERVER_PORT` (opcional): Puerto del servidor (default: 8080)
- `CORS_ALLOWED_ORIGINS` (opcional): Orígenes permitidos para CORS

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

## 🎯 Principios SOLID Implementados

### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad:
- `DogController`: Solo maneja peticiones HTTP de perros
- `GetDogImagesService`: Solo implementa la lógica de obtener imágenes de perros
- `DogApiClient`: Solo se comunica con The Dog API

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

## 🚂 Despliegue en Railway

1. Conectar el repositorio a Railway
2. Seleccionar el directorio `backend/`
3. Railway detectará automáticamente Java/Maven
4. Configurar variables de entorno en Railway:
   - `DOG_API_KEY`
   - `CAT_API_KEY` (opcional)
   - `CORS_ALLOWED_ORIGINS` (URL del frontend)

Railway asignará automáticamente el puerto y la URL del servicio.

## 📝 Notas de Desarrollo

- El proyecto utiliza **Maven** como gestor de dependencias
- **Arquitectura Vertical**: Organización por features (dogs, cats) facilita el desarrollo y mantenimiento
- **Hexagonal dentro de cada feature**: Mantiene la separación de concerns y facilita el testing
- Los adaptadores pueden cambiarse sin afectar la lógica de negocio
- Las APIs externas están abstraídas mediante ports
- Cada feature es independiente: cambios en `dogs/` no afectan `cats/`
- El código compartido está en `shared/` para evitar duplicación

