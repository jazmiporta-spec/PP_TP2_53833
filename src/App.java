//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import excepciones.CupoExcedidoException;
import hilos.EnvioTicketsThread;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;
import certificacion.Certificable;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Datos de prueba
        Estudiante est1 = new Estudiante("53535", "Ana Gómez");
        Estudiante est2 = new Estudiante("50333", "Carlos López");

        EventoUniversitario evento = new EventoUniversitario("EVT-1", "Programación 1", 0.0, true);
        Sala sala1 = new Sala(1, "Sala 1");
        evento.asignarSala(sala1);

        // Crear actividades
        Charla charla = new Charla(1, "Bases de la programación", 10, "Dr. Pérez");
        Taller taller = new Taller(2, "Taller de Java", 1, true); // Cupo 1

        evento.agregarActividad(charla);
        evento.agregarActividad(taller);

        // Inscripciones y manejo de excepcion
        try {
            charla.inscribir(est1);
            taller.inscribir(est1);
            taller.inscribir(est2); // Lanza excepcion por cupo
        } catch (CupoExcedidoException e) {
            System.out.println("Error al inscribir: " + e.getMessage());
        }

        // Probar Hilo
        EnvioTicketsThread hilo = new EnvioTicketsThread(evento);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Guardar en archivo
        if (evento.persistirEvento()) {
            System.out.println("Se grabo correctamente\n");
        }

        // Mostrar datos del evento actual
        System.out.println("DATOS DEL EVENTO");
        evento.mostrarDatos();

        // Recuperar evento
        System.out.println("\nDatos del evento recuperado desde archivo:");
        EventoUniversitario recuperado = EventoUniversitario.recuperarEvento("EVT-1");
        if (recuperado != null) {
            recuperado.mostrarDatos();
        }
    }
}