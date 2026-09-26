package modelo.actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000.0 : 2000.0;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "CERTIFICADO DE APROBACIÓN (" + ENTIDAD_EMISORA + ")\n" +
                "Se certifica que " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "ha completado satisfactoriamente el Taller: " + getTitulo();
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }
}