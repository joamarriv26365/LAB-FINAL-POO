import java.util.ArrayList;
import java.util.Collections;

public class TorreGT {
    private ArrayList<Oficina> oficinas;
    private ArrayList<Alerta> bandeja;
    private int horaActual;
    private int diaActual;
    private int contadorAlertas;

    public TorreGT() {
        this.oficinas = new ArrayList<>();
        this.bandeja = new ArrayList<>();
        this.horaActual = 0;
        this.diaActual = 1;
        this.contadorAlertas = 1;
        inicializarDatos();
    }

    private void inicializarDatos() {
        Oficina o1 = new Oficina("OF1", "Luis Miguel");
        Oficina o2 = new Oficina("OF2", "Charlie Kirk");
        Oficina o3 = new Oficina("OF3", "Barack Obama");

        registrarOficina(o1);
        registrarOficina(o2);
        registrarOficina(o3);

        Luminaria l1 = new Luminaria("L1", "OF1", 100);
        l1.encender();
        l1.ajustarNivel(80);

        Luminaria l2 = new Luminaria("L2", "OF2", 60);
        l2.encender();
        l2.ajustarNivel(50);

        ClimatizadorInverter inv1 = new ClimatizadorInverter("INV1", "OF1", 1500);
        inv1.encender();
        inv1.ajustarNivel(15);

        Climatizador c1 = new Climatizador("C1", "OF3", 1200);
        c1.encender();
        c1.ajustarNivel(60);

        SensorHumo s1 = new SensorHumo("S1", "OF1", 0);
        SensorHumo s2 = new SensorHumo("S2", "OF3", 0);

        Camara cam1 = new Camara("CAM1", "OF2", 50);
        cam1.encender();

        Camara cam2 = new Camara("CAM2", "OF3", 50);
        cam2.encender();

        vincularDispositivo("OF1", l1);
        vincularDispositivo("OF1", inv1);
        vincularDispositivo("OF1", s1);

        vincularDispositivo("OF2", l2);
        vincularDispositivo("OF2", cam1);

        vincularDispositivo("OF3", c1);
        vincularDispositivo("OF3", s2);
        vincularDispositivo("OF3", cam2);
    }

    private void vincularDispositivo(String codigoOficina, Dispositivo d) {
        Oficina o = buscarOficinaPorCodigo(codigoOficina);
        if (o != null) {
            o.agregarDispositivo(d);
        }
    }

    public void registrarOficina(Oficina o) {
        for (Oficina of : oficinas) {
            if (of.getCodigo().equals(o.getCodigo())) {
                System.out.println("Error: Oficina repetida.");
                return;
            }
        }
        oficinas.add(o);
    }

    public void registrarDispositivo(String codOficina, Dispositivo d) {
        Oficina o = buscarOficinaPorCodigo(codOficina);
        if (o != null) {
            for (Dispositivo disp : o.getDispositivos()) {
                if (disp.getCodigo().equals(d.getCodigo())) {
                    System.out.println("Error: Dispositivo repetido en esta oficina.");
                    return;
                }
            }
            o.agregarDispositivo(d);
            System.out.println("Dispositivo registrado exitosamente.");
        } else {
            System.out.println("Error: La oficina no existe.");
        }
    }

    public Oficina buscarOficinaPorCodigo(String codigo) {
        for (Oficina o : oficinas) {
            if (o.getCodigo().equals(codigo)) {
                return o;
            }
        }
        return null;
    }

    public Dispositivo buscarDispositivo(String codigo) {
        for (Oficina o : oficinas) {
            for (Dispositivo d : o.getDispositivos()) {
                if (d.getCodigo().equals(codigo)) {
                    return d;
                }
            }
        }
        return null;
    }

    public Oficina buscarOficinaDeDispositivo(String codigoDisp) {
        for (Oficina o : oficinas) {
            for (Dispositivo d : o.getDispositivos()) {
                if (d.getCodigo().equals(codigoDisp)) {
                    return o;
                }
            }
        }
        return null;
    }

    public void avanzarHora() {
        for (Oficina o : oficinas) {
            for (Dispositivo d : o.getDispositivos()) {
                double kwh = d.calcularConsumo(1.0) / 1000.0;
                o.acumularConsumo(kwh, horaActual);
            }
        }

        horaActual++;
        if (horaActual == 24) {
            horaActual = 0;
            for (Oficina o : oficinas) {
                o.cerrarDia(diaActual);
            }
            diaActual++;
            System.out.println(">>> DÍA " + (diaActual - 1) + " CERRADO. FACTURAS GENERADAS.");
        }
    }

    public void agregarAlerta(Alerta a) {
        if (a != null) {
            bandeja.add(a);
            contadorAlertas++;
        }
    }

    public void atenderAlerta() {
        if (bandeja.isEmpty()) {
            System.out.println("Error: La bandeja está vacía.");
            return;
        }

        Collections.sort(bandeja);

        Alerta urgente = bandeja.remove(0);
        urgente.atender(horaActual);

        int horasTranscurridas = (diaActual - urgente.getDiaGeneracion()) * 24 + (horaActual - urgente.getHoraGeneracion());
        int retraso = horasTranscurridas - urgente.getSeveridad().getPlazoHoras();

        if (retraso > 0) {
            double credito = retraso * urgente.getSeveridad().getCreditoPorHora();
            Oficina o = buscarOficinaDeDispositivo(urgente.getCodigoDispositivo());
            if (o != null) {
                o.aplicarCredito(credito);
            }
            System.out.println("Atendida con retraso. Crédito aplicado: Q" + String.format("%.2f", credito));
        } else {
            System.out.println("Atendida a tiempo.");
        }
    }

    public ArrayList<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(ArrayList<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public ArrayList<Alerta> getBandeja() {
        return bandeja;
    }

    public void setBandeja(ArrayList<Alerta> bandeja) {
        this.bandeja = bandeja;
    }

    public int getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(int horaActual) {
        this.horaActual = horaActual;
    }

    public int getDiaActual() {
        return diaActual;
    }

    public void setDiaActual(int diaActual) {
        this.diaActual = diaActual;
    }

    public int getContadorAlertas() {
        return contadorAlertas;
    }

    public void setContadorAlertas(int contadorAlertas) {
        this.contadorAlertas = contadorAlertas;
    }
}