//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //Creación de estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante est1 = new Estudiante("53535", "Ana Gómez");
        Estudiante est2 = new Estudiante("50333", "Carlos López");
        Estudiante est3 = new Estudiante("50232", "Sofía Rodríguez");
        estudiantes.add(est1);
        estudiantes.add(est2);
        estudiantes.add(est3);

        //Creación del evento
        EventoUniversitario evento = new EventoUniversitario("EVT-01", "Jornada de IA y Desarrollo", 10000.0, false);

        //Asignación de sala
        Sala sala1 = new Sala(101, "Aula Magna - Bloque A");
        evento.asignarSala(sala1);

        //Creación de actividades
        evento.crearActividad(1, "Introducción a Deep Learning", 30, "Charla", "Dr. Pérez");
        evento.crearActividad(2, "Taller Práctico de Java", 20, "Taller", "true");

        // Inscripción de estudiantes
        Actividad charla = evento.getActividades().get(0);
        Actividad taller = evento.getActividades().get(1);

        charla.inscribir(est1);
        charla.inscribir(est2);

        taller.inscribir(est2);
        taller.inscribir(est3);

        evento.mostrarDatos();

        System.out.println("\n--- Detalle de Inscripciones ---");
        charla.mostrarInscripciones();
        taller.mostrarInscripciones();

        System.out.println("\nTotal de eventos universitarios creados en el sistema: " + EventoUniversitario.getCantidadEventos());
    }
}