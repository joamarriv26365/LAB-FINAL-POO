public class Luminaria extends Dispositivo implements Regular {
    private int nivel;

    public Luminaria(String codigo, String oficina, double potencia) {
        super(codigo, oficina, potencia);
        this.nivel = 100;
    }

    @Override
    public double calcularConsumo(double horas) {
        if (!encendido) {
            return 0;
        }
        return potenciaNominal * ((double) nivel / 100.0) * horas;
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
}