public class Severidad {
    private String nombre;
    private int nivelUrgencia;
    private int plazoHoras;
    private double creditoPorHora;

    public Severidad(String nombre, int nivelUrgencia, int plazoHoras, double creditoPorHora) {
        this.nombre = nombre;
        this.nivelUrgencia = nivelUrgencia;
        this.plazoHoras = plazoHoras;
        this.creditoPorHora = creditoPorHora;
    }

    public static final Severidad BAJA = new Severidad("BAJA", 1, 24, 0.50);
    public static final Severidad ALTA = new Severidad("ALTA", 2, 4, 2.00);
    public static final Severidad CRITICA = new Severidad("CRITICA", 3, 1, 5.00);

    public static Severidad obtenerPorNombre(String nombre) {
        if (nombre.equals("BAJA")) {
            return BAJA;
        }
        if (nombre.equals("ALTA")) {
            return ALTA;
        } else {
            return CRITICA;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(int nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    public int getPlazoHoras() {
        return plazoHoras;
    }

    public void setPlazoHoras(int plazoHoras) {
        this.plazoHoras = plazoHoras;
    }

    public double getCreditoPorHora() {
        return creditoPorHora;
    }

    public void setCreditoPorHora(double creditoPorHora) {
        this.creditoPorHora = creditoPorHora;
    }
}