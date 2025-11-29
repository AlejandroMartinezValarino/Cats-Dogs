# Pet Gallery - Cats & Dogs

Aplicación web full-stack para visualizar y filtrar imágenes de perros y gatos utilizando APIs externas. El proyecto está construido con **Java Spring Boot** (backend) y **React con Ant Design** (frontend), siguiendo principios **SOLID** y arquitectura hexagonal con **Vertical Slicing**.

## 📋 Descripción

Pet Gallery es una aplicación que permite a los usuarios:
- Ver imágenes aleatorias de perros y gatos
- Filtrar imágenes por raza
- Buscar razas específicas
- Ver detalles de cada raza
- Navegar entre múltiples imágenes con paginación

## 🏗️ Arquitectura

El proyecto está dividido en dos servicios independientes:

```
pet-gallery-project/
├── backend/          # API REST con Spring Boot (Arquitectura Hexagonal + Vertical Slicing)
├── frontend/         # Aplicación React con Ant Design
└── features/         # Especificaciones BDD en formato Gherkin (uso interno)
```

### Backend
- **Tecnología**: Java 21 + Spring Boot 3.2.0
- **Arquitectura**: Hexagonal (Ports & Adapters) + Vertical Slicing (Screaming Architecture)
- **Principios**: SOLID
- **APIs Externas**: 
  - [Dog CEO API](https://dog.ceo/dog-api/) - API gratuita y open source para imágenes de perros
  - [The Cat API](https://thecatapi.com/) - API para imágenes y datos de gatos

### Frontend
- **Tecnología**: React 18 + Vite
- **UI Framework**: Ant Design 5
- **HTTP Client**: Axios
- **Routing**: React Router DOM

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
mvn clean install
mvn spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

3. **Configurar Frontend**:
```bash
cd frontend
npm install
npm run dev
```

El frontend estará disponible en `http://localhost:3000`

## 📁 Estructura del Proyecto

### Backend (Arquitectura Hexagonal + Vertical Slicing)

El backend utiliza una combinación de **Vertical Slicing** (organización por features) y **Arquitectura Hexagonal** (separación de concerns):

```
backend/
├── src/main/java/com/pets/
│   ├── dogs/                     # Feature: Perros
│   │   ├── application/
│   │   │   ├── port/
│   │   │   │   ├── in/          # Casos de uso (interfaces)
│   │   │   │   └── out/         # Interfaces externas
│   │   │   └── service/         # Implementación casos de uso
│   │   ├── domain/
│   │   │   ├── entity/          # Entidades de dominio
│   │   │   └── exception/       # Excepciones de dominio
│   │   └── infrastructure/
│   │       └── adapter/
│   │           ├── in/          # REST Controllers
│   │           └── out/         # API Clients
│   ├── cats/                     # Feature: Gatos
│   │   └── [misma estructura]
│   └── shared/                   # Código compartido
│       └── config/              # Configuraciones globales
└── src/main/resources/
    └── application.properties
```

**Ventajas de esta arquitectura**:
- ✅ **Mejor cohesión**: Todo lo relacionado con una feature está junto
- ✅ **Independencia**: Cada feature puede evolucionar sin afectar a otras
- ✅ **Escalabilidad**: Fácil agregar nuevas features
- ✅ **Mantenibilidad**: Código más fácil de encontrar y modificar

### Frontend

```
frontend/
├── src/
│   ├── components/           # Componentes reutilizables
│   ├── pages/                # Páginas principales
│   ├── services/             # Servicios API
│   ├── hooks/                # Custom hooks
│   ├── utils/                # Utilidades
│   └── types/                # TypeScript types
├── public/
└── package.json
```

## 🎯 Principios SOLID Aplicados

- **S (Single Responsibility)**: Cada clase tiene una única responsabilidad
- **O (Open/Closed)**: Abierto para extensión, cerrado para modificación
- **L (Liskov Substitution)**: Las implementaciones son intercambiables
- **I (Interface Segregation)**: Interfaces segregadas (ports in/out)
- **D (Dependency Inversion)**: Dependencias hacia abstracciones

## 📡 Endpoints del Backend

### Perros (Dogs)
- `GET /api/dogs/images/random` - Obtiene una imagen aleatoria
- `GET /api/dogs/images/random/list?limit=5` - Obtiene múltiples imágenes aleatorias
- `GET /api/dogs/breeds` - Obtiene todas las razas disponibles
- `GET /api/dogs/images?breed=afghan` - Obtiene imágenes por raza
- `GET /api/dogs/images?breed=airedale&subBreed=terrier` - Obtiene imágenes por subraza

### Gatos (Cats)
- *(En desarrollo)*

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
- **Ant Design 5** - Framework de componentes UI
- **Axios** - Cliente HTTP
- **React Router DOM** - Enrutamiento

## 📚 Documentación Adicional

- [README del Backend](./backend/README.md) - Documentación técnica detallada del backend
- [README del Frontend](./frontend/README.md) - Documentación técnica del frontend

## 🧪 Testing

El proyecto incluye especificaciones de comportamiento en formato Gherkin (BDD) para guiar el desarrollo y las pruebas.

## 🚀 Estado del Proyecto

- ✅ Backend: Feature de perros implementada y funcionando
- 🚧 Frontend: Estructura base creada, pendiente de implementación
- 🚧 Backend: Feature de gatos pendiente de implementación

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👤 Autor

Desarrollado como proyecto de portfolio para demostrar habilidades en:
- Arquitectura de software (Hexagonal + Vertical Slicing)
- Principios SOLID
- Desarrollo full-stack (Java + React)
- Buenas prácticas de desarrollo
