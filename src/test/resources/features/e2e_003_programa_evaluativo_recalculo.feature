# language: es

@E2E-003
Característica: Programa evaluativo con recalculo
  Como docente
  Quiero modificar el programa evaluativo y verificar que los promedios se recalculan
  Para asegurar la integridad de las calificaciones

  Escenario: Recalculo de promedios al modificar el programa evaluativo
    Dado que el docente se registra con usuario "docente_programa" y contrasena "Prog7890"
    Y es redirigido al dashboard
    Y crea el curso "Algebra Lineal"
    Y agrega al estudiante con ID "5559876543" nombre "Ana Torres" y correo "ana.torres@correo.com"
    Y define el programa evaluativo con las actividades:
      | nombre | porcentaje |
      | P1     | 30         |
      | P2     | 30         |
      | Final  | 40         |
    Y registra las siguientes notas para el estudiante "Ana Torres":
      | actividad | nota |
      | P1        | 5.0  |
      | P2        | 3.0  |
      | Final     | 4.0  |
    Entonces el promedio ponderado del estudiante "Ana Torres" debe ser "4.00"
    Cuando edita el programa evaluativo con las actividades:
      | nombre | porcentaje |
      | P1     | 20         |
      | P2     | 20         |
      | Final  | 60         |
    Entonces el promedio ponderado del estudiante "Ana Torres" debe ser "4.00"
    Cuando edita el programa evaluativo eliminando "P2" y redistribuyendo:
      | nombre | porcentaje |
      | P1     | 40         |
      | Final  | 60         |
    Entonces el promedio ponderado del estudiante "Ana Torres" debe ser "4.40"
