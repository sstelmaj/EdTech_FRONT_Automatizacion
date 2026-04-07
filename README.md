# EdTech - Automatización E2E Frontend

Proyecto de pruebas automatizadas End-to-End para la aplicación web EdTech, una plataforma educativa donde los docentes pueden gestionar cursos, estudiantes, programas evaluativos, calificaciones y exportación de boletines.

## Flujos Automatizados

### E2E-001 — Flujo crítico completo

Registro de docente → Creación de curso → Agregar estudiante → Definir programa evaluativo → Registrar notas → Verificar promedio ponderado → Exportar boletín en PDF.

### E2E-002 — Autenticación y protección de rutas

Registro → Logout → Verificación de redireccionamiento a login al acceder a ruta protegida → Login → Verificación de dashboard con nombre de usuario.

### E2E-003 — Programa evaluativo con recálculo

Registro → Crear curso → Agregar estudiante → Definir programa evaluativo → Registrar notas → Verificar promedio → Editar porcentajes → Verificar recálculo → Eliminar actividad y redistribuir → Verificar nuevo promedio.

### E2E-004 — Notas vacías y exportación con advertencia

Registro → Crear curso → Agregar estudiante → Definir programa → Registrar notas parciales (dejar actividad sin nota) → Verificar celda "Sin nota" → Verificar promedio con ceros → Abrir modal de exportación → Verificar advertencia de notas vacías → Confirmar exportación → Verificar mensaje de éxito.

## Tecnologías y Herramientas

| Componente               | Tecnología                             | Versión  |
| ------------------------ | -------------------------------------- | -------- |
| Lenguaje                 | Java                                   | 21       |
| Build tool               | Gradle                                 | 9.0.0    |
| Framework de pruebas     | SerenityBDD                            | 5.3.3    |
| Patrón de diseño         | Screenplay                             | —        |
| BDD                      | Cucumber (Gherkin en español)          | 7.34.2   |
| Test runner              | JUnit Platform                         | 1.11.4   |
| Assertions               | AssertJ                                | 3.27.7   |
| Browser driver           | Chrome (WebDriver auto-download)       | —        |
| Logging                  | Logback                                | 1.5.20   |
| Reportes                 | Serenity Single Page HTML Report       | —        |

## Arquitectura del Proyecto

```
src/test/
├── java/org/sstelmaj/
│   ├── hooks/               # OpenBrowser (navegación inicial)
│   ├── interactions/        # Acciones reutilizables (waits, localStorage)
│   ├── questions/           # Preguntas Screenplay (textos, visibilidad, URLs)
│   ├── runners/             # Runners JUnit (AllScenarios, E2E001-E2E004)
│   ├── stepdefinitions/     # Definiciones de pasos Cucumber
│   │   └── hooks/           # Hook Cucumber (setup del Stage)
│   ├── tasks/               # Tareas Screenplay (RegisterUser, CreateCourse, etc.)
│   └── ui/                  # Page Objects (LoginPage, DashboardPage, modals, etc.)
└── resources/
    ├── features/            # Archivos .feature (Gherkin en español)
    ├── serenity.conf        # Configuración de SerenityBDD y WebDriver
    └── logback-test.xml     # Configuración de logging
```

## Prerrequisitos

- **Java 21** instalado y configurado en `JAVA_HOME`
- **Google Chrome** instalado (el WebDriver se descarga automáticamente)
- **Backend** corriendo en `http://localhost:8080`
- **Frontend** corriendo en `http://localhost:5173`

## Ejecución de Tests

### Ejecutar todos los escenarios

```bash
./gradlew clean test aggregate
```

En Windows:

```bash
.\gradlew.bat clean test aggregate
```

### Ejecutar un escenario específico por tag

El comando `--tests` sobreescribe el filtro por defecto y ejecuta únicamente el runner indicado:

```bash
./gradlew clean test --tests "org.sstelmaj.runners.E2E001Runner" aggregate
```

En Windows:

```bash
.\gradlew.bat clean test --tests "org.sstelmaj.runners.E2E001Runner" aggregate
```

Runners disponibles:

| Runner              | Tag       | Escenario                                 |
| ------------------- | --------- | ----------------------------------------- |
| `AllScenariosRunner`| Todos     | Ejecuta los 4 escenarios                  |
| `E2E001Runner`      | `@E2E-001`| Flujo crítico completo                    |
| `E2E002Runner`      | `@E2E-002`| Autenticación y protección de rutas       |
| `E2E003Runner`      | `@E2E-003`| Programa evaluativo con recálculo         |
| `E2E004Runner`      | `@E2E-004`| Notas vacías y exportación con advertencia|

### Reportes

Al finalizar la ejecución, los reportes Serenity se generan automáticamente en:

```
target/site/serenity/index.html
```

## Configuración

La configuración del entorno se encuentra en `src/test/resources/serenity.conf`:

- **`environments.default`**: URLs por defecto (`localhost:5173` para frontend, `localhost:8080` para backend)
- **`environments.staging`**: URLs de staging (configurable)
- **`webdriver`**: Chrome con auto-download, ventana 1920x1080, modo incógnito
- **`serenity.take.screenshots`**: Captura de pantalla en cada acción

Para cambiar de entorno:

```bash
./gradlew clean test aggregate -Denvironment=staging
```

## Notas Técnicas

- Los **nombres de usuario** se generan con un sufijo único por ejecución (`timestamp % 1000000`) para evitar colisiones entre re-ejecuciones.
- Los **IDs de estudiante** se generan con un contador atómico incremental para garantizar unicidad.
- El **registro de notas** utiliza inyección JavaScript (`nativeInputValueSetter`) para compatibilidad con inputs controlados de React.
- La **exportación de boletín** en E2E-004 se divide en dos pasos (abrir modal → confirmar) para permitir la verificación de la advertencia de notas vacías entre ambos.
