public class ClimatizadorInverter extends Climatizador {

    public ClimatizadorInverter(String codigo, String oficina, double potencia) {
        super(codigo, oficina, potencia);
    }

    @Override
    public double calcularConsumo(double horas) {
        return super.calcularConsumo(horas) * 0.80;
    }
}