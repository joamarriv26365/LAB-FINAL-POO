public abstract class Dispositivo {
    protected String codigo;
    protected String codigoOficina;
    protected double potenciaNominal;
    protected boolean encendido;

    public Dispositivo(String codigo, String codigoOficina, double potenciaNominal) {
        this.codigo = codigo;
        this.codigoOficina = codigoOficina;
        this.potenciaNominal = potenciaNominal;
        this.encendido = false;
    }

    public abstract double calcularConsumo(double horas);

    public void encender() {
        this.encendido = true;
    }

    public void apagar() {
        this.encendido = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public double getPotenciaNominal() {
        return potenciaNominal;
    }

    public void setPotenciaNominal(double potenciaNominal) {
        this.potenciaNominal = potenciaNominal;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
}