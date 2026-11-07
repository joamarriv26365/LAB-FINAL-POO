public class Camara extends Dispositivo implements Movimiento {

    public Camara(String codigo, String oficina, double potencia) {
        super(codigo, oficina, potencia);
    }

    @Override
    public double calcularConsumo(double horas) {
        if (!encendido) {
            return 0;
        }
        return potenciaNominal * horas;
    }

    @Override
    public Alerta detectarMovimiento(int horaActual, int diaActual, int contador) {
        if (horaActual < 7 || horaActual >= 19) {
            return new Alerta(contador, this.codigo, horaActual, diaActual, Severidad.ALTA, "Movimiento fuera de horario.");
        }
        return null;
    }
}
