public class Factura {
    private int dia;
    private double totalValle;
    private double totalNormal;
    private double totalPunta;
    private double creditosAplicados;
    private double totalPagar;

    public Factura(int dia, double valle, double normal, double punta, double creditos) {
        this.dia = dia;
        this.totalValle = valle;
        this.totalNormal = normal;
        this.totalPunta = punta;
        this.creditosAplicados = creditos;
        this.totalPagar = (valle + normal + punta) - creditos;
        if (this.totalPagar < 0) {
            this.totalPagar = 0;
        }
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public double getTotalValle() {
        return totalValle;
    }

    public void setTotalValle(double totalValle) {
        this.totalValle = totalValle;
    }

    public double getTotalNormal() {
        return totalNormal;
    }

    public void setTotalNormal(double totalNormal) {
        this.totalNormal = totalNormal;
    }

    public double getTotalPunta() {
        return totalPunta;
    }

    public void setTotalPunta(double totalPunta) {
        this.totalPunta = totalPunta;
    }

    public double getCreditosAplicados() {
        return creditosAplicados;
    }

    public void setCreditosAplicados(double creditosAplicados) {
        this.creditosAplicados = creditosAplicados;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
}