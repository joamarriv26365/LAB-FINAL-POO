# LAB-FINAL-POO


# TorreGT - Sistema de Oficinas Inteligentes

Aplicación de consola en Java (Laboratorio 4 de POO) que simula la administración de dispositivos, consumo eléctrico, facturación y alertas.

## Requisitos
*   Java JDK 8 o superior.
*   Terminal o IDE (IntelliJ, Eclipse, VS Code).

## Compilación y Ejecución (Terminal)
1. Abre la terminal en la carpeta con los archivos `.java`.
2. Compila todos los archivos:
   ```bash
   javac *.java
   ```
3. Ejecuta el programa:
   ```bash
   java Main
   ```

## Compilación y Ejecución (IDE)
1. Crea un proyecto Java e importa todos los archivos `.java` en la carpeta `src`.
2. Abre el archivo `Main.java` y ejecuta el método `main`.

## Uso del Menú
El programa inicia con datos de prueba en la hora 0 del día 1. Ingresa el número de la opción deseada y presiona Enter:

*   **1. Encender:** Enciende un dispositivo (ingresa código).
*   **2. Apagar:** Apaga un dispositivo (el sensor de humo no se puede apagar).
*   **3. Ajustar Nivel:** Cambia la intensidad de luces y aires (deben estar encendidos y en rango).
*   **4. Registrar Lectura:** Ingresa temperatura o humo (genera alertas si supera umbrales).
*   **5. Registrar Movimiento:** Simula detección en cámaras (alerta fuera de horario laboral).
*   **6. Avanzar 1 Hora:** Calcula consumos por franja y cierra el día automáticamente a medianoche.
*   **7. Atender Alerta:** Atiende la alerta más urgente y calcula créditos por retraso.
*   **8. Ver Reportes:** Muestra dispositivos, bandeja de alertas y facturas generadas.
*   **9. Salir:** Cierra el programa.
*   **10. Registrar Dispositivo:** Agrega un dispositivo a una oficina.
*   **0. Registrar Oficina:** Crea una nueva oficina.

## Manejo de Errores
*   Las entradas no numéricas no cierran el programa.
*   Las operaciones inválidas (códigos repetidos, dispositivos apagados, valores fuera de rango) son rechazadas con un mensaje y no alteran el estado del sistema.
