package modelo.actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private int horas;

    public Curso(int id, String titulo, int cupoMaximo, int horas) {
        super(id, titulo, cupoMaximo);
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }

    @Override
    public double calcularCostoMateriales() {
        return 8000.00;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "CERTIFICADO DE ASISTENCIA (" + ENTIDAD_EMISORA + ")\n" +
                "Se certifica que " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "ha completado el Curso de " + horas + "hs: " + getTitulo();
    }
}