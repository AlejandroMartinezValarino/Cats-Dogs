# Pet Gallery - Cats & Dogs

Aplicación web full-stack para visualizar y filtrar imágenes de perros y gatos utilizando APIs externas. Este proyecto fue desarrollado como práctica de **Arquitectura Hexagonal**, **Vertical Slicing (Screaming Architecture)**, principios **SOLID** y diseño de software orientado a objetos.

## 📋 Descripción

Pet Gallery es una aplicación que permite a los usuarios explorar una galería de imágenes de mascotas con las siguientes funcionalidades:

- **Visualización de imágenes aleatorias** de perros y gatos
- **Filtrado por raza** con soporte para subrazas (perros)
- **Búsqueda de razas** con autocompletado
- **Visualización de detalles** de razas de gatos con información completa
- **Navegación intuitiva** con sidebar colapsable y diseño responsive
- **Masonry gallery** para una visualización óptima de imágenes

## 🎯 Propósito del Proyecto

Este proyecto fue desarrollado con el objetivo principal de **practicar y demostrar**:

- **Arquitectura Hexagonal (Ports & Adapters)**: Separación clara entre lógica de negocio e infraestructura
- **Vertical Slicing (Screaming Architecture)**: Organización por features en lugar de capas horizontales
- **Principios SOLID**: Aplicación práctica de los cinco principios de diseño orientado a objetos
- **Clean Code**: Código mantenible, legible y bien estructurado
- **Desarrollo Full-Stack**: Integración entre backend Java y frontend React

### Nota sobre el Desarrollo

El **backend** fue desarrollado completamente de forma manual, siguiendo meticulosamente la arquitectura hexagonal y los principios SOLID. El **frontend** fue desarrollado con asistencia de IA para agilizar el proceso de implementación, permitiendo enfocar el esfuerzo principal en la arquitectura y diseño del backend, que es el foco principal de aprendizaje de este proyecto.

## 🏗️ Arquitectura

El proyecto está dividido en dos servicios independientes:

```
pet-gallery-project/
├── backend/          # API REST con Spring Boot (Arquitectura Hexagonal + Vertical Slicing)
├── frontend/         # Aplicación React con Ant Design 6
└── features/         # Especificaciones BDD en formato Gherkin
```

### Backend - Arquitectura Hexagonal + Vertical Slicing

El backend implementa una combinación de **Vertical Slicing** (organización por features) y **Arquitectura Hexagonal** (separación de concerns):

- **Vertical Slicing**: Cada feature (dogs, cats) está completamente encapsulada
- **Hexagonal dentro de cada feature**: Separación entre dominio, aplicación e infraestructura
- **Principios SOLID**: Aplicados en cada capa y componente

**Estructura de cada feature**:
```
feature/
├── domain/              # Núcleo del negocio (entidades, excepciones)
├── application/
│   ├── port/
│   │   ├── in/         # Casos de uso (interfaces)
│   │   └── out/        # Interfaces externas
│   └── service/        # Implementación de casos de uso
└── infrastructure/
    └── adapter/
        ├── in/         # REST Controllers
        └── out/        # API Clients
```

**Ventajas de esta arquitectura**:
- ✅ **Alta cohesión**: Todo lo relacionado con una feature está junto
- ✅ **Baja acoplamiento**: Features independientes, cambios aislados
- ✅ **Escalabilidad**: Fácil agregar nuevas features sin afectar existentes
- ✅ **Testabilidad**: Dominio testeable sin dependencias externas
- ✅ **Mantenibilidad**: Código fácil de encontrar y modificar

### Frontend - Arquitectura Componentizada

El frontend sigue una arquitectura modular basada en componentes React:

- **Componentes reutilizables**: Separación clara de responsabilidades
- **Servicios unificados**: Abstracción de llamadas API
- **Estado local**: Gestión de estado con hooks de React
- **UI Framework**: Ant Design 6 para componentes consistentes

## 📚 Especificaciones BDD (Behavior-Driven Development)

El proyecto incluye **8 features** definidas en formato Gherkin que sirvieron como guía durante el desarrollo:

1. **Visualización inicial de imágenes** - Carga de imágenes aleatorias al iniciar
2. **Cambio de tipo de mascota** - Switch entre perros y gatos
3. **Listado de razas disponibles** - Sidebar con árbol de razas y subrazas
4. **Filtrado de imágenes por raza** - Filtrado dinámico según selección
5. **Búsqueda de razas** - Búsqueda con autocompletado en el sidebar
6. **Visualización de detalles de raza** - Drawer con información detallada (gatos)
7. **Manejo de errores y estados de carga** - UX robusta con feedback visual
8. **Paginación de imágenes** - Carga optimizada de imágenes

Estas especificaciones fueron utilizadas como **guía de desarrollo** para:
- Definir los casos de uso del backend
- Estructurar los componentes del frontend
- Validar la implementación durante el desarrollo
- Documentar el comportamiento esperado del sistema

## 🚀 Inicio Rápido

### Prerrequisitos

- **Backend**:
  - Java 21 o superior
  - Maven 3.6+
  - No se requieren API keys (las APIs utilizadas son gratuitas)

- **Frontend**:
  - Node.js 18+ y npm

### Instalación Local

1. **Clonar el repositorio**:
```bash
git clone <repository-url>
cd Cats&Dogs
```

2. **Configurar Backend**:
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

3. **Configurar Frontend**:
```bash
cd frontend
npm install
npm run dev
```

El frontend estará disponible en `http://localhost:3000`

## 🎯 Principios SOLID Aplicados

### Single Responsibility Principle (SRP)
Cada clase tiene una única responsabilidad:
- `DogController`: Solo maneja peticiones HTTP
- `GetDogImagesService`: Solo implementa la lógica de obtener imágenes
- `DogApiClient`: Solo se comunica con la API externa
- `ImageGallery`: Solo renderiza la galería de imágenes

### Open/Closed Principle (OCP)
Abierto para extensión, cerrado para modificación:
- Interfaces (`port/in` y `port/out`) permiten nuevas implementaciones
- Nuevas features (ej: `birds/`) pueden agregarse sin modificar código existente
- Componentes React extensibles mediante props

### Liskov Substitution Principle (LSP)
Las implementaciones son intercambiables:
- Cualquier implementación de `DogApiPort` puede sustituirse
- Features independientes y reemplazables
- Componentes React con contratos de props consistentes

### Interface Segregation Principle (ISP)
Interfaces segregadas y específicas:
- `GetDogImagesUseCase` separado de `GetDogBreedsUseCase`
- `DogApiPort` separado de `CatApiPort`
- Cada feature tiene sus propias interfaces

### Dependency Inversion Principle (DIP)
Dependencias hacia abstracciones:
- Servicios dependen de interfaces (`port/in` y `port/out`)
- Controladores dependen de interfaces de casos de uso
- Implementaciones concretas inyectadas mediante Spring
- Frontend: servicios abstraen llamadas API

## 📡 APIs Externas Utilizadas

### Dog CEO API
- **URL**: https://dog.ceo/dog-api/
- **Tipo**: API gratuita y open source
- **Características**: 
  - No requiere API key
  - Proporciona imágenes de perros y listas de razas
  - Soporte para razas y subrazas

### The Cat API
- **URL**: https://thecatapi.com/
- **Tipo**: API gratuita (con opciones premium)
- **Características**:
  - Uso básico sin API key
  - Proporciona imágenes de gatos y datos detallados de razas
  - Información completa sobre características de razas

## 🔧 Tecnologías y Herramientas

### Backend
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.2.0** - Framework de aplicación
- **Maven** - Gestor de dependencias
- **Lombok** - Reducción de boilerplate
- **Jackson** - Serialización/deserialización JSON
- **RestTemplate** - Cliente HTTP

### Frontend
- **React 18** - Biblioteca de UI
- **Vite** - Build tool y dev server
- **Ant Design 6** - Framework de componentes UI
- **Axios** - Cliente HTTP
- **React Router DOM** - Enrutamiento
- **React Icons** - Iconos adicionales

## 📁 Estructura del Proyecto

### Backend
```
backend/
├── src/main/java/com/pets/
│   ├── dogs/                     # Feature: Perros
│   │   ├── application/
│   │   │   ├── port/
│   │   │   │   ├── in/          # Casos de uso
│   │   │   │   └── out/         # Interfaces externas
│   │   │   └── service/         # Implementaciones
│   │   ├── domain/
│   │   │   ├── entity/          # Entidades
│   │   │   └── exception/       # Excepciones
│   │   └── infrastructure/
│   │       └── adapter/
│   │           ├── in/          # Controllers
│   │           └── out/         # API Clients
│   ├── cats/                     # Feature: Gatos
│   │   └── [misma estructura]
│   └── shared/                   # Código compartido
│       ├── config/              # Configuraciones
│       └── exception/           # Excepciones globales
└── src/main/resources/
    └── application.properties
```

### Frontend
```
frontend/
├── src/
│   ├── components/              # Componentes React
│   │   ├── ImageGallery.jsx     # Galería masonry
│   │   ├── BreedSidebar.jsx     # Sidebar con árbol de razas
│   │   └── BreedDetailsDrawer.jsx # Drawer de detalles
│   ├── pages/                   # Páginas
│   │   └── HomePage.jsx         # Página principal
│   ├── services/                # Servicios API
│   │   ├── api.js               # Cliente Axios
│   │   ├── dogService.js        # Servicios de perros
│   │   ├── catService.js        # Servicios de gatos
│   │   └── petService.js        # Servicio unificado
│   └── utils/                   # Utilidades
│       └── constants.js         # Constantes
├── public/
└── package.json
```

## 📡 Endpoints del Backend

### Perros (Dogs)
- `GET /api/dogs/images/random` - Imagen aleatoria
- `GET /api/dogs/images/random/{limit}` - Múltiples imágenes aleatorias
- `GET /api/dogs/breeds` - Lista de todas las razas
- `GET /api/dogs/breeds/{breed}` - Imágenes por raza
- `GET /api/dogs/breeds/{breed}/{subBreed}` - Imágenes por subraza

### Gatos (Cats)
- `GET /api/cats/images/random` - Imagen aleatoria
- `GET /api/cats/images/random/{limit}` - Múltiples imágenes aleatorias
- `GET /api/cats/breeds` - Lista de todas las razas
- `GET /api/cats/breeds/{breedId}` - Imágenes por raza
- `GET /api/cats/breeds/{breedId}/details` - Detalles de una raza

## 🚀 Estado del Proyecto

### ✅ Completado
- Backend: Features de perros y gatos completamente implementadas
- Backend: Arquitectura hexagonal con vertical slicing
- Backend: Manejo de excepciones centralizado
- Backend: CORS configurado
- Frontend: Interfaz completa con Ant Design 6
- Frontend: Galería masonry responsive
- Frontend: Sidebar con árbol de razas y búsqueda
- Frontend: Visualización de detalles de razas (gatos)
- Frontend: Manejo de estados de carga y error
- Frontend: Integración completa con backend

### 🔄 Mejoras Futuras
- Tests unitarios y de integración
- Paginación en el frontend
- Caché de imágenes
- Optimización de rendimiento
- Internacionalización (i18n)

## 📚 Documentación Adicional

- [README del Backend](./backend/README.md) - Documentación técnica detallada del backend, arquitectura hexagonal y principios SOLID
- [README del Frontend](./frontend/README.md) - Documentación técnica del frontend, componentes y servicios

## 🧪 Testing

El proyecto incluye especificaciones de comportamiento en formato Gherkin (BDD) ubicadas en la carpeta `features/`. Estas especificaciones sirvieron como guía durante el desarrollo y pueden utilizarse para:

- Validar el comportamiento del sistema
- Guiar la implementación de tests automatizados
- Documentar los casos de uso del sistema

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👤 Autor

Desarrollado como proyecto de portfolio para demostrar habilidades en:
- Arquitectura de software (Hexagonal + Vertical Slicing)
- Principios SOLID
- Desarrollo full-stack (Java + React)
- Clean Code y buenas prácticas
- Diseño orientado a objetos

---

**Nota**: Este proyecto fue desarrollado con fines educativos y de práctica. El backend fue implementado manualmente siguiendo meticulosamente los principios de arquitectura hexagonal y SOLID. El frontend fue desarrollado con asistencia de IA para agilizar el proceso, permitiendo enfocar el esfuerzo principal en la arquitectura del backend.
