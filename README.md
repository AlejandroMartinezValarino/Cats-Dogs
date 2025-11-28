# Pet Gallery - Cats & Dogs

Aplicación web para visualizar y filtrar imágenes de perros y gatos utilizando APIs externas. El proyecto está construido con **Java Spring Boot** (backend) y **React con Ant Design** (frontend), siguiendo principios **SOLID** y arquitectura hexagonal.

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
├── backend/          # API REST con Spring Boot (Arquitectura Hexagonal)
├── frontend/         # Aplicación React con Ant Design
└── features/         # Especificaciones BDD en formato Gherkin
```

### Backend
- **Tecnología**: Java 17 + Spring Boot 3.2.0
- **Arquitectura**: Hexagonal (Ports & Adapters)
- **Principios**: SOLID
- **APIs Externas**: 
  - [The Dog API](https://docs.thedogapi.com/)
  - [Cats API](https://publicapi.dev/cats-api)

### Frontend
- **Tecnología**: React 18 + Vite
- **UI Framework**: Ant Design 5
- **HTTP Client**: Axios
- **Routing**: React Router DOM

## 🚀 Inicio Rápido

### Prerrequisitos

- **Backend**:
  - Java 17 o superior
  - Maven 3.6+
  - API Key de [The Dog API](https://thedogapi.com/)

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
# Copiar y configurar variables de entorno
cp .env.example .env
# Editar .env con tus API keys
mvn clean install
mvn spring-boot:run
```

3. **Configurar Frontend**:
```bash
cd frontend
# Copiar y configurar variables de entorno
cp .env.example .env
# Editar .env con la URL del backend
npm install
npm run dev
```

El backend estará disponible en `http://localhost:8080` y el frontend en `http://localhost:3000`.

## 🚂 Despliegue en Railway

El proyecto está configurado para desplegarse en Railway como dos servicios separados:

### Backend Service
1. Conectar el repositorio a Railway
2. Seleccionar el directorio `backend/`
3. Railway detectará automáticamente Java/Maven
4. Configurar variables de entorno:
   - `DOG_API_KEY`: Tu API key de The Dog API
   - `CAT_API_KEY`: (Opcional) API key de Cats API
   - `SERVER_PORT`: Puerto del servidor (Railway lo asigna automáticamente)
   - `CORS_ALLOWED_ORIGINS`: URL del frontend en Railway

### Frontend Service
1. Crear un nuevo servicio en Railway
2. Seleccionar el directorio `frontend/`
3. Railway detectará automáticamente Node.js
4. Configurar variables de entorno:
   - `VITE_API_URL`: URL del backend desplegado en Railway

**Nota**: Al modificar solo el frontend, Railway solo reconstruirá el servicio frontend, manteniendo el backend intacto.

## 📁 Estructura del Proyecto

### Backend (Arquitectura Hexagonal)

```
backend/
├── src/main/java/com/pets/
│   ├── application/          # Capa de aplicación
│   │   ├── port/
│   │   │   ├── in/           # Puertos de entrada (casos de uso)
│   │   │   └── out/          # Puertos de salida (interfaces externas)
│   │   └── service/          # Implementación de casos de uso
│   ├── domain/               # Capa de dominio
│   │   ├── model/            # Entidades de dominio
│   │   └── exception/        # Excepciones de dominio
│   └── infrastructure/       # Capa de infraestructura
│       ├── adapter/
│       │   ├── in/           # Adaptadores de entrada (REST Controllers)
│       │   ├── out/          # Adaptadores de salida (API Clients)
│       │   └── config/       # Configuraciones
│       └── client/            # Clientes HTTP
└── src/main/resources/
    └── application.properties
```

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

## 🧪 Testing

Las especificaciones de comportamiento están definidas en formato Gherkin en el directorio `features/`:

- `01_visualizacion_inicial_imagenes.feature`
- `02_cambio_tipo_mascota.feature`
- `03_listado_razas_disponibles.feature`
- `04_filtrado_imagenes_por_raza.feature`
- `05_busqueda_razas.feature`
- `06_visualizacion_detalles_raza.feature`
- `07_manejo_errores_estados_carga.feature`
- `08_paginacion_imagenes.feature`

## 📚 Documentación Adicional

- [README del Backend](./backend/README.md) - Documentación técnica del backend
- [README del Frontend](./frontend/README.md) - Documentación técnica del frontend

## 🔧 Variables de Entorno

### Backend
- `DOG_API_KEY`: API key de The Dog API (requerido)
- `CAT_API_KEY`: API key de Cats API (opcional)
- `SERVER_PORT`: Puerto del servidor (default: 8080)
- `CORS_ALLOWED_ORIGINS`: Orígenes permitidos para CORS

### Frontend
- `VITE_API_URL`: URL del backend (default: http://localhost:8080/api)

## 🎯 Principios SOLID Aplicados

- **S (Single Responsibility)**: Cada clase tiene una única responsabilidad
- **O (Open/Closed)**: Abierto para extensión, cerrado para modificación
- **L (Liskov Substitution)**: Las implementaciones son intercambiables
- **I (Interface Segregation)**: Interfaces segregadas (ports in/out)
- **D (Dependency Inversion)**: Dependencias hacia abstracciones

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👥 Contribución

Las contribuciones son bienvenidas. Por favor, asegúrate de seguir los principios SOLID y mantener la arquitectura hexagonal.

