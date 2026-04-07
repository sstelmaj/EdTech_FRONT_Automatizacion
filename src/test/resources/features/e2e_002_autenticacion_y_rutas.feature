# language: es

@E2E-002
Característica: Autenticacion y proteccion de rutas
  Como docente registrado
  Quiero verificar que las rutas protegidas redirigen al login sin sesion
  Para asegurar la seguridad de la aplicacion

  Escenario: Registro, logout y proteccion de rutas
    Dado que el docente se registra con usuario "docente_seguridad" y contrasena "Segura456"
    Y es redirigido al dashboard
    Y el dashboard muestra el nombre de usuario "docente_seguridad"
    Cuando el docente cierra sesion
    Entonces es redirigido a la pagina de login
    Cuando intenta acceder a la ruta protegida "/dashboard"
    Entonces es redirigido a la pagina de login
    Cuando inicia sesion con usuario "docente_seguridad" y contrasena "Segura456"
    Entonces es redirigido al dashboard
    Y el dashboard muestra el nombre de usuario "docente_seguridad"
