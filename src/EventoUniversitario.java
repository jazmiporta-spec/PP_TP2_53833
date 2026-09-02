import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    // Constructor principal
    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    // Constructor de copia
    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id + "_copia";
        this.titulo = otro.titulo + " (Copia)";
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double sumaMateriales = 0.0;
        for (Actividad act : actividades) {
            sumaMateriales += act.calcularCostoMateriales();
        }
        return (costoBase + sumaMateriales) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }


    public void crearActividad(int id, String titulo, int cupo, String tipo, String datoExtra) {
        if (tipo.equalsIgnoreCase("Charla")) {
            actividades.add(new Charla(id, titulo, cupo, datoExtra));
        } else if (tipo.equalsIgnoreCase("Taller")) {
            boolean requiereNotebook = Boolean.parseBoolean(datoExtra);
            actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public void mostrarDatos() {
        System.out.println("==========================================");
        System.out.println("Evento ID: " + id + " | Título: " + titulo);
        System.out.println("Es Gratuito: " + (gratuito ? "Sí" : "No"));
        System.out.println("Costo Estimado Final: $" + calcularCostoEstimado());
        System.out.println("Sala Asignada: " + (sala != null ? sala.getNombre() : "Sin Asignar"));
        System.out.println("--- Actividades Registradas ---");
        for (Actividad act : actividades) {
            act.mostrarIdentificacion(); // Uso polimórfico del método final
        }
        System.out.println("==========================================");
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }
}