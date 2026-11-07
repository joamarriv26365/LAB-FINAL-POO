import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TorreGT sistema = new TorreGT();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== TORRE GT =====");
            System.out.println("Hora: " + sistema.getHoraActual() + ":00 | Día: " + sistema.getDiaActual());
            System.out.println("1. Encender");
            System.out.println("2. Apagar");
            System.out.println("3. Ajustar Nivel");
            System.out.println("4. Registrar Lectura");
            System.out.println("5. Registrar Movimiento");
            System.out.println("6. Avanzar 1 Hora");
            System.out.println("7. Atender Alerta");
            System.out.println("8. Ver Reportes");
            System.out.println("9. Salir");
            System.out.println("10. Registrar Dispositivo");
            System.out.println("0. Registrar Oficina");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.print("Código de la nueva oficina: ");
                        String codOf = scanner.nextLine();
                        if (codOf.trim().isEmpty()) {
                            System.out.println("Error: Código vacío.");
                            break;
                        }
                        System.out.print("Nombre del inquilino: ");
                        String inq = scanner.nextLine();
                        sistema.registrarOficina(new Oficina(codOf, inq));
                        break;
                    case 10:
                        System.out.print("Código de la oficina donde se instalará: ");
                        String codOficinaDisp = scanner.nextLine();
                        if (sistema.buscarOficinaPorCodigo(codOficinaDisp) == null) {
                            System.out.println("Error: Oficina no existe.");
                            break;
                        }

                        System.out.println("Tipo: 1.Luminaria, 2.Climatizador, 3.Inverter, 4.SensorHumo, 5.Camara");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Código del dispositivo: ");
                        String codDisp = scanner.nextLine();
                        if (codDisp.trim().isEmpty()) {
                            System.out.println("Error: Código vacío.");
                            break;
                        }

                        System.out.print("Potencia (W): ");
                        double pot = scanner.nextDouble();
                        scanner.nextLine();
                        if (pot <= 0) {
                            System.out.println("Error: Potencia debe ser positiva.");
                            break;
                        }

                        Dispositivo nuevoDisp = null;
                        if (tipo == 1) {
                            nuevoDisp = new Luminaria(codDisp, codOficinaDisp, pot);
                        } else if (tipo == 2) {
                            nuevoDisp = new Climatizador(codDisp, codOficinaDisp, pot);
                        } else if (tipo == 3) {
                            nuevoDisp = new ClimatizadorInverter(codDisp, codOficinaDisp, pot);
                        } else if (tipo == 4) {
                            nuevoDisp = new SensorHumo(codDisp, codOficinaDisp, pot);
                        } else if (tipo == 5) {
                            nuevoDisp = new Camara(codDisp, codOficinaDisp, pot);
                        } else {
                            System.out.println("Tipo inválido.");
                            break;
                        }

                        sistema.registrarDispositivo(codOficinaDisp, nuevoDisp);
                        break;
                    case 1:
                        System.out.print("Código del dispositivo: ");
                        Dispositivo dEnc = sistema.buscarDispositivo(scanner.nextLine());
                        if (dEnc == null) {
                            System.out.println("Error: No existe.");
                            break;
                        }
                        dEnc.encender();
                        System.out.println("Encendido.");
                        break;
                    case 2:
                        System.out.print("Código del dispositivo: ");
                        Dispositivo dApa = sistema.buscarDispositivo(scanner.nextLine());
                        if (dApa == null) {
                            System.out.println("Error: No existe.");
                            break;
                        }
                        try {
                            dApa.apagar();
                            System.out.println("Apagado.");
                        } catch (UnsupportedOperationException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Código del dispositivo: ");
                        Dispositivo dReg = sistema.buscarDispositivo(scanner.nextLine());
                        if (dReg == null) {
                            System.out.println("Error: No existe.");
                            break;
                        }
                        if (!dReg.isEncendido()) {
                            System.out.println("Error: Está apagado.");
                            break;
                        }
                        if (!(dReg instanceof Regular)) {
                            System.out.println("Error: No es regulable.");
                            break;
                        }

                        System.out.print("Nuevo nivel: ");
                        int nivel = scanner.nextInt();
                        scanner.nextLine();

                        if (nivel <= 0) {
                            System.out.println("Error: Nivel debe ser positivo.");
                            break;
                        }

                        if (dReg instanceof Luminaria && (nivel < 10 || nivel > 100)) {
                            System.out.println("Error: Rango 10-100.");
                            break;
                        }
                        if (dReg instanceof ClimatizadorInverter && (nivel < 10 || nivel > 100)) {
                            System.out.println("Error: Rango 10-100.");
                            break;
                        }
                        if (dReg.getClass() == Climatizador.class && (nivel < 20 || nivel > 100)) {
                            System.out.println("Error: Rango 20-100.");
                            break;
                        }

                        ((Regular) dReg).ajustarNivel(nivel);
                        System.out.println("Nivel ajustado.");
                        break;
                    case 4:
                        System.out.print("Código del dispositivo: ");
                        Dispositivo dMed = sistema.buscarDispositivo(scanner.nextLine());
                        if (dMed == null) {
                            System.out.println("Error: No existe.");
                            break;
                        }
                        if (!dMed.isEncendido()) {
                            System.out.println("Error: Está apagado.");
                            break;
                        }
                        if (!(dMed instanceof Medir)) {
                            System.out.println("Error: No mide.");
                            break;
                        }

                        System.out.print("Valor de lectura: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        if (dMed instanceof Climatizador && (valor < -10 || valor > 60)) {
                            System.out.println("Error: Rango -10 a 60.");
                            break;
                        }
                        if (dMed instanceof SensorHumo && (valor < 0 || valor > 1000)) {
                            System.out.println("Error: Rango 0 a 1000.");
                            break;
                        }

                        Alerta a1 = ((Medir) dMed).medir(valor, sistema.getHoraActual(), sistema.getDiaActual(), sistema.getContadorAlertas());
                        sistema.agregarAlerta(a1);
                        System.out.println(a1 != null ? "¡ALERTA GENERADA!" : "Lectura normal.");
                        break;
                    case 5:
                        System.out.print("Código de la cámara: ");
                        Dispositivo dCam = sistema.buscarDispositivo(scanner.nextLine());
                        if (dCam == null) {
                            System.out.println("Error: No existe.");
                            break;
                        }
                        if (!dCam.isEncendido()) {
                            System.out.println("Error: Está apagada.");
                            break;
                        }
                        if (!(dCam instanceof Movimiento)) {
                            System.out.println("Error: No detecta movimiento.");
                            break;
                        }

                        Alerta a2 = ((Movimiento) dCam).detectarMovimiento(sistema.getHoraActual(), sistema.getDiaActual(), sistema.getContadorAlertas());
                        sistema.agregarAlerta(a2);
                        System.out.println(a2 != null ? "¡ALERTA GENERADA!" : "Sin novedades.");
                        break;
                    case 6:
                        sistema.avanzarHora();
                        System.out.println("Hora actual: " + sistema.getHoraActual() + ":00");
                        break;
                    case 7:
                        sistema.atenderAlerta();
                        break;
                    case 8:
                        System.out.println("\n--- DISPOSITIVOS ---");
                        for (Oficina o : sistema.getOficinas()) {
                            System.out.println("Oficina " + o.getCodigo() + " (" + o.getInquilino() + ")");
                            for (Dispositivo d : o.getDispositivos()) {
                                System.out.println("  - " + d.getCodigo() + " [" + (d.isEncendido() ? "ON" : "OFF") + "]");
                            }
                        }

                        System.out.println("\n--- BANDEJA ---");
                        if (sistema.getBandeja().isEmpty()) {
                            System.out.println("Vacía.");
                        } else {
                            Collections.sort(sistema.getBandeja());
                            for (Alerta a : sistema.getBandeja()) {
                                System.out.println("Alerta " + a.getCodigo() + " | " + a.getSeveridad().getNombre() + " | Disp: " + a.getCodigoDispositivo());
                            }
                        }

                        System.out.println("\n--- FACTURAS ---");
                        for (Oficina o : sistema.getOficinas()) {
                            for (Factura f : o.getFacturas()) {
                                System.out.println("Día " + f.getDia() + " | Oficina " + o.getCodigo() + " | Valle: Q" + String.format("%.2f", f.getTotalValle()) + " | Normal: Q" + String.format("%.2f", f.getTotalNormal()) + " | Punta: Q" + String.format("%.2f", f.getTotalPunta()) + " | Créditos: Q" + String.format("%.2f", f.getCreditosAplicados()) + " | Total: Q" + String.format("%.2f", f.getTotalPagar()));
                            }
                        }
                        break;
                    case 9:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Debe ingresar un número.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}