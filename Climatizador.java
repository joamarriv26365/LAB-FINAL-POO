public class Climatizador extends Dispositivo implements Regular, Medir {
    protected int nivel;

    public Climatizador(String codigo, String oficina, double potencia) {
        super(codigo, oficina, potencia);
        this.nivel = 50;
    }

    @Override
    public double calcularConsumo(double horas) {
        if (!encendido) {
            return 0;
        }
        return potenciaNominal * (0.30 + 0.70 * ((double) nivel / 100.0)) * horas;
    }

    @Override
    public void ajustarNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public Alerta medir(double valor, int horaActual, int diaActual, int contador) {
        String nombreSev = null;
        if (valor >= 28 && valor < 32) {
            nombreSev = "BAJA";
        } else if (valor >= 32 && valor < 40) {
            nombreSev = "ALTA";
        } else if (valor >= 40) {
            nombreSev = "CRITICA";
        }

        if (nombreSev != null) {
            return new Alerta(contador, this.codigo, horaActual, diaActual, Severidad.obtenerPorNombre(nombreSev), "Temperatura: " + valor);
        }
        return null;
    }
}