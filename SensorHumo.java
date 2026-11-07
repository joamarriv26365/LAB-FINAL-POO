public class SensorHumo extends Dispositivo implements Medir {

    public SensorHumo(String codigo, String oficina, double potencia) {
        super(codigo, oficina, potencia);
        this.encendido = true;
    }

    @Override
    public double calcularConsumo(double horas) {
        return 0.0;
    }

    @Override
    public void apagar() {
        throw new UnsupportedOperationException("Error: El sensor de humo no se puede apagar.");
    }

    @Override
    public Alerta medir(double valor, int horaActual, int diaActual, int contador) {
        String nombreSev = null;
        if (valor >= 150 && valor < 300) {
            nombreSev = "ALTA";
        } else if (valor >= 300) {
            nombreSev = "CRITICA";
        }

        if (nombreSev != null) {
            return new Alerta(contador, this.codigo, horaActual, diaActual, Severidad.obtenerPorNombre(nombreSev), "Humedad: " + valor);
        }
        return null;
    }
}