Este repositorio contiene la solución del Trabajo Práctico N° 1. El objetivo del sistema es gestionar Eventos Universitarios, administrando la asignación de salas, la creación de actividades (que pueden ser charlas y talleres) y la inscripción de estudiantes a estas.

Conceptos de POO Aplicados:

Encapsulamiento: control de acceso a datos mediante calificadores (private, public, final).
Herencia y clases abstractas: la clase base Actividad define comportamientos genéricos compartidos por las subclases Charla y Taller.
Polimorfismo: tratamiento unificado de diferentes tipos de actividades al calcular costos y mostrar identificaciones dinámicamente.
Relaciones entre objetos:
Asociación / Inscripción: entre Actividad, Inscripcion y Estudiante.
Agregación: la clase Sala existe de forma independiente a EventoUniversitario.
Composición: las actividades forman parte de la vida útil del EventoUniversitario.


IMAGENES:
Salida de consola:
![img.png](img.png)
Diagrama de memoria Heap & Stack:
![img_1.png](img_1.png)