package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "CONFIRMADA";
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
    public class TicketDeAcceso implements Serializable {
        private static final long serialVersionUID = 1L;
        private String ticketId;

        public TicketDeAcceso() {
            this.ticketId = "TCK-" + estudiante.getLegajo() + "-" + (int)(Math.random() * 9000 + 1000);
        }

        public String getTicketId() {
            return ticketId;
        }

        public void enviarTicket() {
            System.out.println("  --> [Ticket enviado] ID: " + ticketId + " | Alumno: " + estudiante.getNombre() + " (Fecha: " + fecha + ")");
        }
    }
}