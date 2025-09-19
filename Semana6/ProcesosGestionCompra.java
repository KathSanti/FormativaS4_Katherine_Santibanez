package Formativas_DuocUC.Semana6;

import Formativas_DuocUC.Semana5.MenuPrincipal;
import java.util.Scanner;

public class ProcesosGestionCompra {

    static boolean[][] asientos = new boolean[5][6];
    static boolean reservaPendiente = false;
    static int[] preciosUnitarios = {20000, 18000, 16000, 14000, 11000};

    public void ReservasEntradas() {


        while (MenuPrincipal.op == 1) {

            Scanner sc = new Scanner(System.in);

            MostrarMapaAsientos();


            System.out.print("Ingrese asiento (ej: A1, B5, E6): ");
            String coodenadaAsiento = sc.nextLine().trim().toUpperCase(); //Variable local

            if (!validarFormatoCodigo(coodenadaAsiento)) {
                System.out.println("Código inválido. Formato correcto: Letra (A-E) + Número (1-6)");
                continue;
            }

            char filaChar = coodenadaAsiento.charAt(0);
            int fila = filaChar - 'A'; // Convertir a índice (0-4)//Variable local
            int columna = Integer.parseInt(coodenadaAsiento.substring(1)) - 1; // Convertir a índice (0-5) //Variable local

            if (!validarRangoAsiento(fila, columna)) {
                System.out.println("Asiento fuera de rango (fila A-E, columnas 1-6)");
                continue;
            }

            if (asientos[fila][columna]) {
                System.out.println("[x] Ese asiento ya está ocupado, elija otro.");
                continue;
            }

            int precioAsiento = preciosUnitarios[fila];
            System.out.println("Asiento " + coodenadaAsiento + " - Precio: $" + precioAsiento);

            if (!confirmarReserva(sc)) {
                System.out.println("Reserva cancelada");
                continue;
            }

            // Reservar asiento
            asientos[fila][columna] = true;
            System.out.println("¡Reserva confirmada! Asiento " + coodenadaAsiento + " reservado "+ "Valor: $" + preciosUnitarios [fila]);


            // Preguntar si quiere comprar otro asiento
            System.out.print("¿Deseas reservar otro asiento? (S/N): ");
            String respuestareserva = sc.nextLine().trim().toUpperCase();
            if (!respuestareserva.equals("S") && !respuestareserva.equals("SI")&& !respuestareserva.equals("s")) {
                break;
            }

        }


    }

    public void MostrarMapaAsientos() {


        System.out.println("\n==========  RESERVA DE ASIENTOS  ==========");
        System.out.println(" ");
        System.out.println("   " + "  1    2    3    4    5    6");

        char[] letrasFila = {'A', 'B', 'C', 'D', 'E'};

        for (int i = 0; i < asientos.length; i++) {
            System.out.print(letrasFila[i] + " | ");
            for (int j = 0; j < asientos[i].length; j++) {
                if  (asientos[i][j]) {
                    System.out.print("[X]  ");
                }else if (reservaPendiente) {
                    System.out.print("[R]  ");
                }else{
                    System.out.print("[ ]  ");
                }

            }

            System.out.println("($" + "(" + preciosUnitarios[i] +")" );

        }
        System.out.println();
    }

    private boolean validarFormatoCodigo(String codigo) {
        return codigo.length() == 2 &&
                Character.isLetter(codigo.charAt(0)) && //verifica que el primer digito sea letra
                Character.isDigit(codigo.charAt(1)); //verifica que el segundo digito sea nro
    }

    // para validar rango del asiento
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

        // Verificar si hay asientos ocupados
        boolean reservaEncontrada = false;
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (asientos[i][j]) {
                    reservaEncontrada = true;
                    break;
                }
            }
            if (reservaEncontrada) break;
        }

        if (!reservaEncontrada) {
            System.out.println("No hay boletas para eliminar.");
            return;
        }

        System.out.print("Ingrese el asiento de la boleta a eliminar (ej: A1, B5): ");
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
            if (asientos[fila][columna]) {
                asientos[fila][columna] = false; // Liberar el asiento
                System.out.println("Boleta eliminada correctamente. Asiento " + asientoABuscar + " liberado.");
            } else {
                System.out.println("No hay reserva en el asiento: " + asientoABuscar);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de asiento inválido.");
        }
    }

    public void ComprarEntradas(Scanner sc) {



    }











}



