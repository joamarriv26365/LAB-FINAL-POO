public class Alerta implements Comparable<Alerta> {
    private int codigo;
    private String codigoDispositivo;
    private int horaGeneracion;
    private int diaGeneracion;
    private Severidad severidad;
    private String mensaje;
    private int horaAtencion;

    public Alerta(int codigo, String disp, int hora, int dia, Severidad sev, String msg) {
        this.codigo = codigo;
        this.codigoDispositivo = disp;
        this.horaGeneracion = hora;
        this.diaGeneracion = dia;
        this.severidad = sev;
        this.mensaje = msg;
        this.horaAtencion = -1;
    }

    @Override
    public int compareTo(Alerta otra) {
        if (this.severidad.getNivelUrgencia() != otra.severidad.getNivelUrgencia()) {
            return otra.severidad.getNivelUrgencia() - this.severidad.getNivelUrgencia();
        }
        if (this.horaGeneracion != otra.horaGeneracion) {
            return this.horaGeneracion - otra.horaGeneracion;
        } else {
            return this.codigoDispositivo.compareTo(otra.codigoDispositivo);
        }
    }

    public void atender(int horaActual) {
        this.horaAtencion = horaActual;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public int getHoraGeneracion() {
        return horaGeneracion;
    }

    public void setHoraGeneracion(int horaGeneracion) {
        this.horaGeneracion = horaGeneracion;
    }

    public int getDiaGeneracion() {
        return diaGeneracion;
    }

    public void setDiaGeneracion(int diaGeneracion) {
        this.diaGeneracion = diaGeneracion;
    }

    public Severidad getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Severidad severidad) {
        this.severidad = severidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(int horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
}