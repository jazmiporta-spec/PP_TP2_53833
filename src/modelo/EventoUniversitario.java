package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    // constructor principal
    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    // constructor de copia
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

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public void crearActividad(int id, String titulo, int cupo, String tipo, String datoExtra) {
        if (tipo.equalsIgnoreCase("Charla")) {
            actividades.add(new Charla(id, titulo, cupo, datoExtra));
        } else if (tipo.equalsIgnoreCase("Taller")) {
            boolean requiereNotebook = Boolean.parseBoolean(datoExtra);
            actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> filtradas = new ArrayList<>();
        for (Actividad act : actividades) {
            if (tipo.isInstance(act)) {
                filtradas.add(tipo.cast(act));
            }
        }
        return filtradas;
    }

    public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {
        double costoTotal = 0;
        for (Actividad act : listaActividades) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }

    public boolean persistirEvento() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("evento_" + id + ".ser"))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            System.err.println("Error de I/O al guardar el evento " + id + ": " + e.getMessage());
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("evento_" + id + ".ser"))) {
            return (EventoUniversitario) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("El archivo del evento no existe en disco.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error de compatibilidad de clase al deserializar: " + e.getMessage());
        }
        return null;
    }

    public void mostrarDatos() {
        System.out.println("==========================================");
        System.out.println("Evento ID: " + id + " | Título: " + titulo);
        System.out.println("Es Gratuito: " + (gratuito ? "Sí" : "No"));
        System.out.println("Costo Estimado Final: $" + calcularCostoEstimado());
        System.out.println("Sala Asignada: " + (sala != null ? sala.getNombre() : "Sin Asignar"));
        System.out.println("--- Actividades Registradas ---");
        for (Actividad act : actividades) {
            act.mostrarIdentificacion();
        }
        System.out.println("==========================================");
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}