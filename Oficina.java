import java.util.ArrayList;

public class Oficina {
    private String codigo;
    private String inquilino;
    private ArrayList<Dispositivo> dispositivos;
    private double kwhValle;
    private double kwhNormal;
    private double kwhPunta;
    private double creditosPendientes;
    private ArrayList<Factura> facturas;

    public Oficina(String codigo, String inquilino) {
        this.codigo = codigo;
        this.inquilino = inquilino;
        this.dispositivos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.kwhValle = 0;
        this.kwhNormal = 0;
        this.kwhPunta = 0;
        this.creditosPendientes = 0;
    }

    public void agregarDispositivo(Dispositivo d) {
        this.dispositivos.add(d);
    }

    public void acumularConsumo(double kwh, int hora) {
        if (hora >= 22 || hora < 6) {
            this.kwhValle += kwh;
        } else if (hora >= 6 && hora < 18) {
            this.kwhNormal += kwh;
        } else {
            this.kwhPunta += kwh;
        }
    }

    public void aplicarCredito(double monto) {
        this.creditosPendientes += monto;
    }

    public void cerrarDia(int diaActual) {
        double costoValle = kwhValle * 0.85;
        double costoNormal = kwhNormal * 1.40;
        double costoPunta = kwhPunta * 2.10;

        this.facturas.add(new Factura(diaActual, costoValle, costoNormal, costoPunta, creditosPendientes));

        this.kwhValle = 0;
        this.kwhNormal = 0;
        this.kwhPunta = 0;
        this.creditosPendientes = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public ArrayList<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(ArrayList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public double getKwhValle() {
        return kwhValle;
    }

    public void setKwhValle(double kwhValle) {
        this.kwhValle = kwhValle;
    }

    public double getKwhNormal() {
        return kwhNormal;
    }

    public void setKwhNormal(double kwhNormal) {
        this.kwhNormal = kwhNormal;
    }

    public double getKwhPunta() {
        return kwhPunta;
    }

    public void setKwhPunta(double kwhPunta) {
        this.kwhPunta = kwhPunta;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
}