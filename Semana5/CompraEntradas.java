package Formativas_DuocUC.Semana5;

import java.util.Scanner;

public class CompraEntradas {

    // Variables optimizadas usando arrays
    static boolean[][] asientos = new boolean[5][6]; // 5 filas (A-E), 6 columnas (1-6)
    static int[] precios = {20000, 18000, 16000, 14000, 11000}; // Precios por fila


        public void CompraEntradas(Scanner sc) {
            MenuPrincipal.op = 1;




            while (MenuPrincipal.op == 1) {
                mostrarAsientos();

                System.out.print("Ingrese asiento (ej: A1, B5, E6): ");
                String codigo = sc.nextLine().trim().toUpperCase();

                if (!validarFormatoCodigo(codigo)) {
                    System.out.println("Código inválido. Formato correcto: Letra (A-E) + Número (1-6)");
                    continue;
                }

                char filaChar = codigo.charAt(0);
                int fila = filaChar - 'A'; // Convertir a índice (0-4)
                int columna = Integer.parseInt(codigo.substring(1)) - 1; // Convertir a índice (0-5)

                if (!validarRangoAsiento(fila, columna)) {
                    System.out.println("Asiento fuera de rango (fila A-E, columnas 1-6)");
                    continue;
                }

                if (asientos[fila][columna]) {
                    System.out.println("[x] Ese asiento ya está ocupado, elija otro.");
                    continue;
                }

                int precio = precios[fila];
                System.out.println("Asiento " + codigo + " - Precio: $" + precio);

                if (!confirmarReserva(sc)) {
                    System.out.println("Reserva cancelada");
                    continue;
                }

                // Reservar asiento
                asientos[fila][columna] = true;
                System.out.println("¡Reserva confirmada! Asiento " + codigo + " reservado.");
            }
        }

// para mostrar el estado de los asientos
        private void mostrarAsientos() {
            System.out.println("\n==========  RESERVA DE ASIENTOS  ==========");
            System.out.println(" ");
            System.out.println("   " + "  1    2    3    4    5    6");

            char[] letrasFila = {'A', 'B', 'C', 'D', 'E'};

            for (int i = 0; i < asientos.length; i++) {
                System.out.print(letrasFila[i] + " | ");
                for (int j = 0; j < asientos[i].length; j++) {
                    System.out.print(asientos[i][j] ? "[X]  " : "[ ]  ");
                }
                System.out.println("($" + precios[i] + ")");
            }
            System.out.println();
        }

        //  para validar formato del código
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
            String respuesta = sc.nextLine().trim().toUpperCase();
            return respuesta.equals("S") || respuesta.equals("SI");
        }
    }

