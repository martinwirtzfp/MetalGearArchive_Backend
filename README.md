# MetalGearArchive — Backend

> Proyecto de Fin de Ciclo · Desarrollo de Aplicaciones Multiplataforma (DAM)

API REST desarrollada con **Spring Boot** que actúa como capa de datos para la aplicación **MetalGearArchive**, una enciclopedia interactiva de la saga *Metal Gear Solid*.

---

## Descripción del proyecto

**MetalGearArchive** es una aplicación multiplataforma (web y móvil) que permite consultar información detallada sobre la franquicia *Metal Gear Solid*: personajes, juegos, localizaciones, organizaciones y eventos históricos de la saga.

El proyecto está dividido en dos repositorios:

| Repositorio | Tecnología | Descripción |
|---|---|---|
| `MetalGearArchive_Backend` | Spring Boot (Java 21) | API REST + base de datos |
| `MetalGearArchive_Frontend` | Vue 3 + Ionic + Capacitor | Interfaz multiplataforma |

---

## Tecnologías utilizadas

| Capa | Tecnología | Versión |
|---|---|---|
| Lenguaje | Java | 21 |
| Framework | Spring Boot | 4.0.4 |
| Persistencia | Spring Data JPA / Hibernate | — |
| Base de datos | H2 (en memoria) | — |
| Generación de código | Lombok | — |
| Build | Maven | — |

---

## Arquitectura

El backend sigue una arquitectura en capas clásica:

```
Controller  →  Service  →  Repository  →  Entity (JPA)
```

### Estructura de paquetes

```
com.metalgeararchive.backend
├── BackendApplication.java       # Punto de entrada
├── config/
│   └── CorsConfig.java           # Configuración CORS
├── controller/
│   ├── CharacterController.java
│   ├── EventController.java
│   ├── GameController.java
│   ├── LocationController.java
│   └── OrganizationController.java
├── data/
│   └── DataInitializer.java      # Seed data inicial
├── entity/
│   ├── Character.java
│   ├── Event.java
│   ├── Game.java
│   ├── Location.java
│   └── Organization.java
├── repository/
│   ├── CharacterRepository.java
│   ├── EventRepository.java
│   ├── GameRepository.java
│   ├── LocationRepository.java
│   └── OrganizationRepository.java
└── service/
    ├── CharacterService.java
    ├── EventService.java
    ├── GameService.java
    ├── LocationService.java
    └── OrganizationService.java
```

---

## Modelo de datos

### `Game` — Juegos
| Campo | Tipo | Descripción |
|---|---|---|
| id | Long | Clave primaria |
| name | String | Título del juego (único) |
| releaseYear | Integer | Año de lanzamiento |
| platforms | String | Plataformas en formato JSON |
| synopsis | Text | Sinopsis |
| imageUrl | String | URL de la imagen |
| videoUrl | String | Enlace a YouTube |

### `Character` — Personajes
| Campo | Tipo | Descripción |
|---|---|---|
| id | Long | Clave primaria |
| name | String | Nombre (único) |
| role | String | Rol (Protagonist, Antagonist…) |
| nationality | String | Nacionalidad |
| age | String | Rango de edad |
| gender | String | Género |
| description | Text | Descripción |
| imageUrl | String | URL de la imagen |
| appearances | Set&lt;Game&gt; | Juegos en los que aparece (ManyToMany) |

### `Location` — Localizaciones
| Campo | Tipo | Descripción |
|---|---|---|
| id | Long | Clave primaria |
| name | String | Nombre (único) |
| region | String | Región geográfica |
| description | Text | Descripción |
| imageUrl | String | URL de la imagen |
| coordinates | String | Coordenadas opcionales |

### `Organization` — Organizaciones
| Campo | Tipo | Descripción |
|---|---|---|
| id | Long | Clave primaria |
| name | String | Nombre (único) |
| type | String | Tipo (Military, Government…) |
| description | Text | Descripción |
| imageUrl | String | URL de la imagen |
| headquarters | String | Sede central |

### `Event` — Eventos
| Campo | Tipo | Descripción |
|---|---|---|
| id | Long | Clave primaria |
| name | String | Nombre del evento (único) |
| codeName | String | Nombre en clave de la operación |
| eventYear | Integer | Año del evento |
| description | Text | Descripción |
| imageUrl | String | URL de la imagen |
| location | Location | Localización asociada (ManyToOne) |
| outcome | String | Resultado (Success, Failure…) |

---

## API REST — Endpoints

Todos los endpoints están bajo el prefijo `/api`.

### Juegos — `/api/games`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/games` | Obtener todos los juegos |
| GET | `/api/games/{id}` | Obtener juego por ID |
| POST | `/api/games` | Crear nuevo juego |
| PUT | `/api/games/{id}` | Actualizar juego |
| DELETE | `/api/games/{id}` | Eliminar juego |

### Personajes — `/api/characters`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/characters` | Obtener todos los personajes |
| GET | `/api/characters/{id}` | Obtener personaje por ID |
| POST | `/api/characters` | Crear nuevo personaje |
| PUT | `/api/characters/{id}` | Actualizar personaje |
| DELETE | `/api/characters/{id}` | Eliminar personaje |

### Localizaciones — `/api/locations`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/locations` | Obtener todas las localizaciones |
| GET | `/api/locations/{id}` | Obtener localización por ID |
| POST | `/api/locations` | Crear nueva localización |
| PUT | `/api/locations/{id}` | Actualizar localización |
| DELETE | `/api/locations/{id}` | Eliminar localización |

### Organizaciones — `/api/organizations`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/organizations` | Obtener todas las organizaciones |
| GET | `/api/organizations/{id}` | Obtener organización por ID |
| POST | `/api/organizations` | Crear nueva organización |
| PUT | `/api/organizations/{id}` | Actualizar organización |
| DELETE | `/api/organizations/{id}` | Eliminar organización |

### Eventos — `/api/events`
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/events` | Obtener todos los eventos |
| GET | `/api/events/{id}` | Obtener evento por ID |
| POST | `/api/events` | Crear nuevo evento |
| PUT | `/api/events/{id}` | Actualizar evento |
| DELETE | `/api/events/{id}` | Eliminar evento |

---

## Datos de ejemplo (Seed Data)

Al arrancar la aplicación se carga automáticamente un conjunto de datos de ejemplo que incluye:

**Juegos:** Metal Gear Solid (1998), MGS2: Sons of Liberty (2001), MGS3: Snake Eater (2004)

**Personajes:** Solid Snake, Raiden, Naked Snake, The Boss

**Localizaciones:** Shadow Moses Island, Tanker Deck, Groznyj Grad

**Organizaciones:** FOXHOUND, SOCOM, DARPA

**Eventos:** Operation Intrude N313, Tanker Chapter, Operation Snake Eater

---

## Cómo ejecutar el proyecto

### Requisitos previos
- **Java 21** o superior
- **Maven** (incluido el wrapper `mvnw`)

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/martinwirtzfp/MetalGearArchive_Backend.git
cd MetalGearArchive_Backend

# 2. Ejecutar la aplicación
./mvnw spring-boot:run
# En Windows:
mvnw.cmd spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

### Consola H2

Durante el desarrollo se puede acceder a la consola web de H2 en:

```
http://localhost:8080/h2-console
```

Parámetros de conexión:
- **JDBC URL:** `jdbc:h2:mem:metalgeardb`
- **User:** `sa`
- **Password:** *(vacío)*

### Ejecutar tests

```bash
./mvnw test
```

---

## Configuración CORS

El backend acepta peticiones desde los siguientes orígenes (configuración para desarrollo local):

- `http://localhost:5173` (servidor de desarrollo Vite del frontend)
- `http://localhost:4173` (servidor de preview de Vite)

---

## Estado actual del proyecto

> **Fase:** Desarrollo inicial

| Funcionalidad | Estado |
|---|---|
| Entidades JPA (5 modelos) | ✅ Implementado |
| API REST CRUD completa (5 recursos) | ✅ Implementado |
| Base de datos H2 en memoria | ✅ Implementado |
| Seed data de ejemplo | ✅ Implementado |
| Configuración CORS para frontend | ✅ Implementado |
| Autenticación / Seguridad | ⏳ Pendiente |
| Base de datos persistente (PostgreSQL/MySQL) | ⏳ Pendiente |
| Paginación y filtros en la API | ⏳ Pendiente |
| Tests unitarios e integración | ⏳ Pendiente |
| Despliegue en producción | ⏳ Pendiente |

---

## Repositorio relacionado

- 🖥️ **Frontend:** [MetalGearArchive_Frontend](https://github.com/martinwirtzfp/MetalGearArchive_Frontend) — Vue 3 + Ionic + Capacitor
