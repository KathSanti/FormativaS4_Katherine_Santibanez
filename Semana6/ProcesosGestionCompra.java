package Formativas_DuocUC.Semana6;

import Formativas_DuocUC.Semana6.MenuTeatroMoro;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProcesosGestionCompra {

    static boolean[][] asientos = new boolean[5][6];
    static boolean [][] reservaPendiente = new boolean[5][6];
    static int[] preciosUnitarios = {20000, 18000, 16000, 14000, 11000};
    private Timer timerReserva;

    // Variables estáticas para almacenar información de compra
    static int totalCompra = 0;
    static int cantidadAsientosComprados = 0;
    static List<String> detallesAsientos = new ArrayList<>();


    public void ReservasEntradas(Scanner sc) {
        MenuTeatroMoro.opcioMenuTeatro = 1;
        boolean seguirReservando = true;
        timerReserva = new Timer(true);

        while (seguirReservando) {
            MostrarMapaAsientos();

            System.out.print("Ingrese asiento (ej: A1, B5, E6): ");
            String coordenadaAsiento = sc.nextLine().trim().toUpperCase();

            if (!validarFormatoCodigo(coordenadaAsiento)) {
                System.out.println("Código inválido. Formato correcto: Letra (A-E) + Número (1-6)");
                continue;
            }

            char filaChar = coordenadaAsiento.charAt(0);
            int fila = filaChar - 'A';
            int columna = Integer.parseInt(coordenadaAsiento.substring(1)) - 1;

            if (!validarRangoAsiento(fila, columna)) {
                System.out.println("Asiento fuera de rango (fila A-E, columnas 1-6)");
                continue;
            }

            if (asientos[fila][columna]) {
                System.out.println("[x] Ese asiento ya está ocupado, elija otro.");
                continue;
            }

            if (reservaPendiente[fila][columna]) {
                System.out.println("[R] Ese asiento ya está reservado, escoja otro o espere a que se libere");
                continue;
            }

            int precioAsiento = preciosUnitarios[fila];
            System.out.println("Asiento " + coordenadaAsiento + " - Precio: $" + precioAsiento);

            if (!confirmarReserva(sc)) {
                System.out.println("Reserva cancelada");
                continue;
            }

            // Reservar asiento , se inicializa temporizador
            reservaPendiente[fila][columna] = true; //Punto debug para verficar si el asiento selccionado por el usuario cambia a true
            iniciarTemporizadorRerserva(fila, columna,coordenadaAsiento);

            System.out.println("¡Reserva confirmada! Asiento " + coordenadaAsiento + " reservado. Valor: $" + precioAsiento);
            System.out.println("Tienes 3 min para confirmar la compra de tus asientos reservados");

            // Preguntar si quiere reservar otro asiento
            System.out.print("¿Deseas reservar otro asiento? (S/N): ");
            String respuestaReserva = sc.nextLine().trim().toUpperCase();

            if (!respuestaReserva.equals("S") && !respuestaReserva.equals("SI")) {
                // Preguntar si quiere comprar los asientos reservados
                System.out.print("¿Deseas confirmar la compra de tus asientos reservados? (S/N): ");
                String respuestaCompra = sc.nextLine().trim().toUpperCase();


                //DEBUG: para verficar si el asiento selccionado por el usuario cambia a ocupado - se cancela temprozador.

                if (respuestaCompra.equals("S") || respuestaCompra.equals("SI")) {
                    comprarAsientosReservados();
                    cancelarTemporizadores();
                } else {
                    System.out.println("Tus reservas se mantienen. Puedes comprarlas más tarde, ");
                    System.out.println("\n¡RECUERDA! que tienes 3 min para comprar tus asientos");
                }
                seguirReservando = false;
            }
        }
    }


    //Temprizador explicado en Taller 2

    private void iniciarTemporizadorRerserva(int fila, int columna, String coordenadaAsiento) {
        TimerTask taskTemporizadorRerserva = new TimerTask() {
            @Override
            public void run() {
                synchronized (ProcesosGestionCompra.this){
                    if(reservaPendiente[fila][columna] && !asientos[fila][columna]){
                        reservaPendiente[fila][columna] = false;
                        System.out.println("La reserva del asiento "+ coordenadaAsiento + " ha expirado de los 3 min y el asiento ha sido liberado");

                    }

                }
            }
        };

        timerReserva.schedule(taskTemporizadorRerserva, 180000);//3 min
    }

    private void cancelarTemporizadores() {
        if (timerReserva != null) {
            timerReserva.cancel();
            timerReserva.purge();
        }
    }

    public void comprarAsientosReservados() {
        totalCompra = 0; // Reiniciar valores
        cantidadAsientosComprados = 0;
        detallesAsientos.clear();

        for (int i = 0; i < reservaPendiente.length; i++) {
            for (int j = 0; j < reservaPendiente[i].length; j++) {
                if (reservaPendiente[i][j]) {
                    // Transferir de reserva a comprado
                    asientos[i][j] = true;
                    reservaPendiente[i][j] = false;

                    // Calcular total
                    totalCompra += preciosUnitarios[i];
                    cantidadAsientosComprados++;

                    // Guardar detalles del asiento
                    char fila = (char) ('A' + i);
                    int numero = j + 1;
                    String detalleAsiento = "Asiento " + fila + numero + " - $" + preciosUnitarios[i];
                    detallesAsientos.add(detalleAsiento);

                    System.out.println("✓ " + detalleAsiento);
                }
            }
        }

        if (cantidadAsientosComprados > 0) {
            System.out.println("====================================");
            System.out.println("¡COMPRA REALIZADA CON ÉXITO!");
            System.out.println("Total de asientos: " + cantidadAsientosComprados);
            System.out.println("Total a pagar: $" + totalCompra);
            System.out.println("====================================");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" Por favor dirigete al menú para imprimir tu boleta");

        } else {
            System.out.println("No tenías asientos reservados para comprar.");
        }
    }

    public void MostrarMapaAsientos() {


        System.out.println("\n==========  RESERVA DE ASIENTOS  ==========");
        System.out.println("\n   [ ]Libre - [R] Rerservado - [X] Ocupado ");
        System.out.println(" ");
        System.out.println("   " + "  1    2    3    4    5    6");

        char[] letrasFila = {'A', 'B', 'C', 'D', 'E'};

        for (int i = 0; i < asientos.length; i++) {
            System.out.print(letrasFila[i] + " | ");
            for (int j = 0; j < asientos[i].length; j++) {
                if  (asientos[i][j]) {
                    System.out.print("[X]  ");
                }else if (reservaPendiente[i][j]) {
                    System.out.print("[R]  ");
                }else{
                    System.out.print("[ ]  ");
                }

            }

            System.out.println("($" + "(" + preciosUnitarios[i] +")" );

        }
        System.out.println();
    }

    private boolean validarFormatoCodigo(String coodenadaAsiento) {
        return coodenadaAsiento.length() == 2 &&
                Character.isLetter(coodenadaAsiento.charAt(0)) && //verifica que el primer digito sea letra
                Character.isDigit(coodenadaAsiento.charAt(1)); //verifica que el segundo digito sea nro
    }

    private boolean validarRangoAsiento(int fila, int columna) {
        return fila >= 0 && fila < 5 && columna >= 0 && columna < 6;
    }

    private boolean confirmarReserva(Scanner sc) {
        System.out.print("¿Confirmar reserva (S/N)? ");
        String respuesta = sc.nextLine().trim().toUpperCase();//Variable Local
        return respuesta.equals("S") || respuesta.equals("SI")|| respuesta.equals("s");
    }




    public void ModificarReserva(Scanner sc) {

        MostrarMapaAsientos();

        // Variable local para Verificar si hay asientos ocupados
        boolean reservaEncontrada = false;

        for (int i = 0; i < reservaPendiente.length; i++) {
            for (int j = 0; j < reservaPendiente[i].length; j++) {
                if (reservaPendiente[i][j]) {
                    reservaEncontrada = true;
                    break;
                }
            }
            if (!reservaEncontrada) break;
        }

        if (!reservaEncontrada) {
            System.out.println("No hay reservas para eliminar.");
            return;
        }

        System.out.print("Ingrese su reserva a eliminar (ej: A1, B5): ");
        String asientoABuscar = sc.nextLine().trim().toUpperCase();

        // Validar formato
        if (!validarFormatoCodigo(asientoABuscar)) {
            System.out.println("Formato de asiento inválido.");
            return;
        }

        try {
            // Convertir asiento en coordenadas
            char filaChar = asientoABuscar.charAt(0);
            int fila = filaChar - 'A';
            int columna = Integer.parseInt(asientoABuscar.substring(1)) - 1;

            // Validar rango
            if (!validarRangoAsiento(fila, columna)) {
                System.out.println("Asiento fuera de rango.");
                return;
            }

            // Verificar si el asiento está ocupado
            if (reservaPendiente[fila][columna]) {
                reservaPendiente[fila][columna] = false; // Liberar el asiento
                System.out.println("Reserva eliminada correctamente. Asiento " + asientoABuscar + " liberado.");
            } else {
                System.out.println("No hay reserva en el asiento: " + asientoABuscar);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de asiento inválido CATCH.");
        }
    }

    public void imprimirDetalleBoleta() {
        // DEBUG: Verificar estado inicial - cantidadAsientosComprados = 0, no hay compras.
        System.out.println("DEBUG: Cantidad asientos = " + cantidadAsientosComprados + ", Total = $" + totalCompra);

        if (cantidadAsientosComprados == 0) {
            System.out.println("=====================================");
            System.out.println("       No hay compras realizadas.    ");
            System.out.println("=====================================");
            return;
        }

        // DEBUG: Verificar contenido de detallesAsientos antes de imprimir
        System.out.println("DEBUG: detallesAsientos = " + detallesAsientos);

        System.out.println("========== BOLETA DETALLE ==========");
        System.out.println("Ubicación asientos: " + detallesAsientos );
        System.out.println("Cantidad entradas:" + cantidadAsientosComprados);
        System.out.println("Total cancelado: $" + totalCompra);

        // DEBUG: Confirmar que se ejecutó el metodo de impresión
        System.out.println("DEBUG: Boleta impresa completamente");

        System.out.println("=====================================");
    }













}



