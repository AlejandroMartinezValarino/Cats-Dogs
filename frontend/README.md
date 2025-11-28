# Pet Gallery Frontend

Aplicación web desarrollada con **React 18**, **Vite**, y **Ant Design 5** para visualizar y filtrar imágenes de perros y gatos.

## 🏗️ Arquitectura

El frontend sigue una arquitectura modular y componentizada:

```
src/
├── components/        # Componentes reutilizables
│   ├── ImageGallery/
│   ├── BreedFilter/
│   ├── BreedSearch/
│   └── BreedDetails/
├── pages/            # Páginas principales
│   └── HomePage/
├── services/         # Servicios API
│   └── api.js
├── hooks/            # Custom hooks
│   ├── useImages.js
│   ├── useBreeds.js
│   └── useBreedFilter.js
├── utils/            # Utilidades
│   ├── constants.js
│   └── helpers.js
└── types/            # TypeScript types (si se usa TS)
```

## 🚀 Inicio Rápido

### Prerrequisitos

- Node.js 18+ y npm

### Instalación

1. **Instalar dependencias**:
```bash
npm install
```

2. **Configurar variables de entorno**:
```bash
cp .env.example .env
# Editar .env con la URL del backend
```

3. **Ejecutar en desarrollo**:
```bash
npm run dev
```

La aplicación estará disponible en `http://localhost:3000`

## 📦 Scripts Disponibles

- `npm run dev`: Inicia el servidor de desarrollo con Vite
- `npm run build`: Construye la aplicación para producción
- `npm run preview`: Previsualiza la build de producción
- `npm run lint`: Ejecuta el linter de ESLint

## 🎨 Componentes Principales

### ImageGallery
Componente para mostrar la galería de imágenes con paginación.

### BreedFilter
Componente para filtrar imágenes por raza con selector de Ant Design.

### BreedSearch
Componente de búsqueda de razas con autocompletado.

### BreedDetails
Modal o panel para mostrar detalles de una raza específica.

## 🔌 Servicios API

El servicio `api.js` configura Axios con:
- Base URL configurable mediante variable de entorno
- Timeout de 10 segundos
- Interceptores para manejo global de errores
- Headers por defecto

### Uso

```javascript
import apiClient from './services/api';

// Ejemplo de uso
const fetchImages = async (type, page = 0, limit = 10) => {
  const response = await apiClient.get('/images', {
    params: { type, page, limit }
  });
  return response.data;
};
```

## 🎣 Custom Hooks

### useImages
Hook para gestionar el estado y la lógica de las imágenes:
- Carga de imágenes
- Paginación
- Filtrado por raza
- Manejo de estados de carga y error

### useBreeds
Hook para gestionar las razas:
- Carga de lista de razas
- Búsqueda de razas
- Filtrado local

### useBreedFilter
Hook para gestionar el filtro de raza:
- Estado del filtro activo
- Cambio de raza
- Limpieza del filtro

## 🔧 Configuración

### Variables de Entorno

Crear archivo `.env` en la raíz del frontend:

```env
VITE_API_URL=http://localhost:8080/api
```

**Nota**: En Vite, las variables de entorno deben comenzar con `VITE_` para ser accesibles en el código del cliente.

### Vite Config

El archivo `vite.config.js` incluye:
- Plugin de React
- Puerto 3000 por defecto
- Apertura automática del navegador en desarrollo
- Source maps en producción

## 📦 Dependencias Principales

### Producción
- **react**: ^18.2.0 - Biblioteca de UI
- **react-dom**: ^18.2.0 - Renderizado de React
- **react-router-dom**: ^6.20.0 - Enrutamiento
- **antd**: ^5.11.0 - Componentes UI
- **axios**: ^1.6.2 - Cliente HTTP
- **@ant-design/icons**: ^5.2.6 - Iconos de Ant Design

### Desarrollo
- **vite**: ^5.0.8 - Build tool y dev server
- **@vitejs/plugin-react**: ^4.2.0 - Plugin de React para Vite
- **eslint**: ^8.55.0 - Linter de código

## 🎨 Ant Design

El proyecto utiliza **Ant Design 5** como framework de UI:

- Configuración en español mediante `ConfigProvider`
- Componentes principales:
  - `Layout`: Estructura de página
  - `Card`: Tarjetas para imágenes
  - `Select`: Selector de razas
  - `Input`: Búsqueda
  - `Pagination`: Navegación de páginas
  - `Spin`: Indicadores de carga
  - `Modal`: Detalles de raza
  - `Typography`: Texto estilizado

## 🚂 Despliegue en Railway

1. Crear un nuevo servicio en Railway
2. Seleccionar el directorio `frontend/`
3. Railway detectará automáticamente Node.js
4. Configurar variables de entorno:
   - `VITE_API_URL`: URL del backend desplegado en Railway

Railway ejecutará automáticamente `npm install` y `npm run build`, y servirá los archivos estáticos.

## 📱 Responsive Design

La aplicación está diseñada para ser responsive:
- Desktop: Grid de imágenes con múltiples columnas
- Tablet: Grid adaptativo
- Mobile: Una columna con imágenes optimizadas

## 🎯 Mejores Prácticas

- **Componentes funcionales**: Todos los componentes usan funciones y hooks
- **Custom hooks**: Lógica reutilizable extraída a hooks
- **Separación de concerns**: Servicios, componentes y lógica separados
- **Manejo de errores**: Try-catch y estados de error en todos los hooks
- **Loading states**: Indicadores de carga en todas las operaciones asíncronas
- **PropTypes/TypeScript**: Validación de tipos (recomendado implementar)

## 📝 Notas de Desarrollo

- El proyecto utiliza **Vite** en lugar de Create React App para mejor rendimiento
- Los componentes están diseñados para ser reutilizables y modulares
- El estado se gestiona principalmente mediante hooks de React
- Las llamadas a la API están centralizadas en el servicio `api.js`

