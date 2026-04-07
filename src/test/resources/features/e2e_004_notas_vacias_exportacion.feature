# language: es

@E2E-004
Característica: Notas vacias y exportacion con advertencia
  Como docente
  Quiero exportar el boletin de un estudiante con notas incompletas
  Para verificar que se muestra la advertencia y el calculo trata las vacias como cero

  Escenario: Exportacion con notas vacias muestra advertencia y calcula promedio con ceros
    Dado que el docente se registra con usuario "docente_parcial" y contrasena "Parcial321"
    Y es redirigido al dashboard
    Y crea el curso "Historia Universal"
    Y agrega al estudiante con ID "5554567890" nombre "Luis Martinez" y correo "luis.martinez@correo.com"
    Y define el programa evaluativo con las actividades:
      | nombre | porcentaje |
      | P1     | 30         |
      | P2     | 30         |
      | Final  | 40         |
    Y registra las siguientes notas para el estudiante "Luis Martinez":
      | actividad | nota |
      | P1        | 4.5  |
      | Final     | 3.8  |
    Entonces la celda de la actividad "P2" del estudiante "Luis Martinez" muestra "Sin nota"
    Y el promedio ponderado del estudiante "Luis Martinez" debe ser "2.87"
    Cuando abre el modal de exportacion del estudiante "Luis Martinez" en formato "JSON"
    Entonces se muestra la advertencia de notas vacias
    Cuando confirma la exportacion del boletin
    Entonces se muestra el mensaje de exportacion exitosa en formato "JSON"
