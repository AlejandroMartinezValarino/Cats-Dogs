# Pet Gallery Frontend

Aplicación web desarrollada con **React 18**, **Vite**, y **Ant Design 6** para visualizar y filtrar imágenes de perros y gatos.

## 📝 Nota sobre el Desarrollo

Este frontend fue desarrollado con **asistencia de IA** para agilizar el proceso de implementación. El objetivo principal del proyecto es la práctica de arquitectura hexagonal y principios SOLID en el backend, por lo que se utilizó asistencia tecnológica para acelerar el desarrollo del frontend y permitir enfocar el esfuerzo principal en la arquitectura del backend.

## 🏗️ Arquitectura

El frontend sigue una arquitectura modular y componentizada:

```
src/
├── components/        # Componentes reutilizables
│   ├── ImageGallery.jsx          # Galería masonry de imágenes
│   ├── ImageGallery.css          # Estilos de la galería
│   ├── BreedSidebar.jsx          # Sidebar con árbol de razas
│   ├── BreedSidebar.css          # Estilos del sidebar
│   └── BreedDetailsDrawer.jsx    # Drawer con detalles de raza
├── pages/            # Páginas principales
│   └── HomePage.jsx              # Página principal con layout
├── services/         # Servicios API
│   ├── api.js                    # Cliente Axios configurado
│   ├── dogService.js             # Servicios específicos de perros
│   ├── catService.js             # Servicios específicos de gatos
│   └── petService.js             # Servicio unificado
├── utils/            # Utilidades
│   └── constants.js              # Constantes (PET_TYPES, API_BASE)
└── index.jsx         # Punto de entrada
```

## 🚀 Inicio Rápido

### Prerrequisitos

- Node.js 18+ y npm

### Instalación

1. **Instalar dependencias**:
```bash
npm install
```

2. **Configurar variables de entorno** (opcional):
```bash
# Crear archivo .env en la raíz del frontend
VITE_API_URL=http://localhost:8080/api
```

**Nota**: En Vite, las variables de entorno deben comenzar con `VITE_` para ser accesibles en el código del cliente. Si no se configura, el frontend usará `http://localhost:8080/api` por defecto.

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

### HomePage
Componente principal que contiene el layout de la aplicación:
- Header con selector de tipo de mascota (perros/gatos)
- Sidebar colapsable con árbol de razas
- Área de contenido con la galería de imágenes

### ImageGallery
Componente para mostrar la galería de imágenes:
- Layout masonry responsive usando CSS Grid
- Carga de imágenes aleatorias o filtradas por raza
- Estados de carga, error y vacío
- Preview de imágenes al hacer click
- Click en cards de gatos para ver detalles de raza

### BreedSidebar
Sidebar con árbol de razas y subrazas:
- Estructura de árbol usando Ant Design Tree
- Búsqueda de razas con autocompletado
- Soporte para subrazas (perros)
- Selección visual con color coral del tema
- Auto-expansión al buscar

### BreedDetailsDrawer
Drawer lateral para mostrar detalles de razas:
- Información completa de razas de gatos
- Características físicas y de comportamiento
- Enlaces a referencias externas (Wikipedia, etc.)
- Diseño responsive

## 🔌 Servicios API

### api.js
Cliente Axios configurado con:
- Base URL configurable mediante variable de entorno
- Timeout de 10 segundos
- Interceptores para manejo global de errores
- Headers por defecto

### dogService.js
Servicios específicos para perros:
- `getRandomDogImage()`: Imagen aleatoria
- `getRandomDogImages(limit)`: Múltiples imágenes aleatorias
- `getDogBreeds()`: Lista de razas
- `getDogImagesByBreed(breed)`: Imágenes por raza
- `getDogImagesBySubBreed(breed, subBreed)`: Imágenes por subraza

### catService.js
Servicios específicos para gatos:
- `getRandomCatImage()`: Imagen aleatoria
- `getRandomCatImages(limit)`: Múltiples imágenes aleatorias
- `getCatBreeds()`: Lista de razas
- `getCatImagesByBreed(breedId, limit)`: Imágenes por raza
- `getCatBreedById(breedId)`: Detalles de una raza

### petService.js
Servicio unificado que abstrae las llamadas a `dogService` y `catService`:
- `getRandomImages(petType, limit)`: Imágenes aleatorias según tipo
- `getBreeds(petType)`: Razas según tipo
- `getImagesByBreed(petType, breed, limit)`: Imágenes por raza
- `getBreedDetails(petType, breedId)`: Detalles de raza

## 🎨 Ant Design 6

El proyecto utiliza **Ant Design 6** como framework de UI:

### Configuración
- ConfigProvider con tema personalizado (color primario coral: #FF6B6B)
- Localización en español
- Componentes principales utilizados:
  - `Layout`, `Header`, `Sider`, `Content`: Estructura de página
  - `Tree`: Árbol de razas y subrazas
  - `Card`, `Image`: Tarjetas de imágenes
  - `Drawer`: Panel de detalles
  - `Input.Search`: Búsqueda de razas
  - `Radio.Group`: Selector de tipo de mascota
  - `Spin`: Indicadores de carga
  - `Empty`: Estados vacíos
  - `Descriptions`: Detalles de raza
  - `Tag`: Etiquetas de características

### Tema Personalizado
El color primario del tema está configurado en `index.jsx`:
```javascript
const theme = {
  token: {
    colorPrimary: '#FF6B6B',
  },
};
```

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
- Configuración de desarrollo optimizada

## 📦 Dependencias Principales

### Producción
- **react**: ^18.2.0 - Biblioteca de UI
- **react-dom**: ^18.2.0 - Renderizado de React
- **react-router-dom**: ^6.20.0 - Enrutamiento
- **antd**: ^6.0.0 - Framework de componentes UI
- **axios**: ^1.6.2 - Cliente HTTP
- **@ant-design/icons**: ^6.0.0 - Iconos de Ant Design
- **react-icons**: ^5.5.0 - Iconos adicionales (FaDog, FaCat)

### Desarrollo
- **vite**: ^5.0.8 - Build tool y dev server
- **@vitejs/plugin-react**: ^4.2.0 - Plugin de React para Vite
- **eslint**: ^8.55.0 - Linter de código

## 🎨 Características de UI/UX

### Diseño Responsive
- Layout adaptativo para desktop, tablet y mobile
- Sidebar colapsable en pantallas pequeñas
- Galería masonry que se ajusta al tamaño de pantalla

### Estados de la Aplicación
- **Loading**: Indicadores de carga durante peticiones
- **Error**: Mensajes de error claros con opción de reintentar
- **Empty**: Estados vacíos informativos

### Interacciones
- Hover effects en cards de imágenes
- Selección visual de razas en el sidebar
- Preview de imágenes al hacer click
- Drawer deslizable para detalles de raza

### Paleta de Colores
- Color primario: #FF6B6B (coral)
- Color hover: #FF5252
- Fondo: #FAFAFA
- Superficie: #FFFFFF
- Sidebar: #F8F9FA

## 🚀 Despliegue

### Build para Producción

```bash
npm run build
```

Esto generará una carpeta `dist/` con los archivos estáticos listos para desplegar.

### Despliegue en Railway / Vercel / Netlify

1. Conectar el repositorio
2. Configurar el directorio de build como `frontend/`
3. Configurar variables de entorno:
   - `VITE_API_URL`: URL del backend desplegado
4. El servicio ejecutará automáticamente `npm install` y `npm run build`

## 📝 Notas de Desarrollo

- El proyecto utiliza **Vite** en lugar de Create React App para mejor rendimiento
- Los componentes están diseñados para ser reutilizables y modulares
- El estado se gestiona principalmente mediante hooks de React (`useState`, `useEffect`, `useMemo`)
- Las llamadas a la API están centralizadas en servicios
- El manejo de errores está implementado en múltiples niveles (servicios, componentes)
- La aplicación es completamente responsive y funciona en todos los dispositivos

## 🐛 Solución de Problemas

### El frontend no se conecta al backend
- Verificar que el backend esté corriendo en `http://localhost:8080`
- Verificar la variable de entorno `VITE_API_URL`
- Revisar la consola del navegador para errores de CORS

### Las imágenes no cargan
- Verificar la conexión a internet
- Revisar la consola del navegador para errores de red
- Verificar que las APIs externas estén disponibles

### El sidebar no muestra las razas
- Verificar que el backend esté respondiendo correctamente
- Revisar la consola del navegador para errores
- Verificar que el formato de datos sea el esperado
