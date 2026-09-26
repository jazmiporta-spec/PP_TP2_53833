package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n[HILO SECUNDARIO] === Iniciando envío masivo de tickets de acceso ===");
        for (Actividad act : evento.getActividades()) {
            for (Inscripcion ins : act.getInscripciones()) {
                if ("CONFIRMADA".equalsIgnoreCase(ins.getEstado())) {
                    Inscripcion.TicketDeAcceso ticket = ins.new TicketDeAcceso();
                    ticket.enviarTicket();
                    try {
                        Thread.sleep(800); // Simula envío concurrente
                    } catch (InterruptedException e) {
                        System.err.println("Hilo interrumpido.");
                    }
                }
            }
        }
        System.out.println("[HILO SECUNDARIO] === Finalizó el envío masivo de tickets ===\n");
    }
}