# language: es

@E2E-001
Característica: Flujo critico completo del MVP
  Como docente nuevo
  Quiero registrarme, crear un curso, agregar un estudiante, definir un programa evaluativo,
  registrar notas y exportar el boletin
  Para verificar el flujo completo de la aplicacion

  Escenario: Flujo completo desde registro hasta exportacion de boletin en PDF
    Dado que el docente se registra con usuario "docente_test" y contrasena "Pass1234"
    Y es redirigido al dashboard
    Cuando crea el curso "Fisica 201"
    Y agrega al estudiante con ID "5551234567" nombre "Carlos Gomez" y correo "carlos@correo.com"
    Y define el programa evaluativo con las actividades:
      | nombre | porcentaje |
      | P1     | 30         |
      | P2     | 30         |
      | Final  | 40         |
    Y registra las siguientes notas para el estudiante "Carlos Gomez":
      | actividad | nota |
      | P1        | 4.0  |
      | P2        | 3.5  |
      | Final     | 4.8  |
    Entonces el promedio ponderado del estudiante "Carlos Gomez" debe ser "4.17"
    Cuando exporta el boletin del estudiante "Carlos Gomez" en formato "PDF"
    Entonces se muestra el mensaje de exportacion exitosa en formato "PDF"
