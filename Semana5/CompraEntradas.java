package Formativas_DuocUC.Semana5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompraEntradas {

    // Variables optimizadas usando arrays
    static boolean[][] asientos = new boolean[5][6]; // 5 filas (A-E), 6 columnas (1-6)
    static int[] precios = {20000, 18000, 16000, 14000, 11000};
    static String[] NombreEntradas = {"VIP","Platea Baja","Platea Baja","Palcos","Tribunas"};
    static ArrayList<Boleta> boletas = new ArrayList<>();

    class Boleta {
        String asiento;
        int precioOriginal;
        double descuento;
        double totalPagar;
        int edadCliente;

        public Boleta(String asiento, int precioOriginal, double descuento, double totalPagar, int edadCliente) {
            this.asiento = asiento;
            this.precioOriginal = precioOriginal;
            this.descuento = descuento;
            this.totalPagar = totalPagar;
            this.edadCliente = edadCliente;
        }
    }

        // Precios por fila

        public void CompraEntradas(Scanner sc) {
            MenuPrincipal.op = 1;
            boletas.clear();


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

                int precioAsiento = precios[fila];
                System.out.println("Asiento " + codigo + " - Precio: $" + precioAsiento);

                if (!confirmarReserva(sc)) {
                    System.out.println("Reserva cancelada");
                    continue;
                }


                // Reservar asiento
                asientos[fila][columna] = true;
                System.out.println("¡Reserva confirmada! Asiento " + codigo + " reservado.");

                // Llamar descuento y obtener los valores
                Boleta boleta = descuento(sc, precioAsiento, codigo);
                boletas.add(boleta); //  Guardar boleta

                // Reservar asiento
                asientos[fila][columna] = true;
                System.out.println("¡Reserva confirmada! Asiento " + codigo + " reservado.");

                // Preguntar si quiere comprar otro asiento
                System.out.print("¿Desea comprar otro asiento? (S/N): ");
                String respuesta = sc.nextLine().trim().toUpperCase();
                if (!respuesta.equals("S") && !respuesta.equals("SI")) {
                    break;
                }

                imprimirBoletas();

            }
        }

        // para mostrar el estado de los asientos
        public void mostrarAsientos() {
            System.out.println("\n==========  RESERVA DE ASIENTOS  ==========");
            System.out.println(" ");
            System.out.println("   " + "  1    2    3    4    5    6");

            char[] letrasFila = {'A', 'B', 'C', 'D', 'E'};

            for (int i = 0; i < asientos.length; i++) {
                System.out.print(letrasFila[i] + " | ");
                for (int j = 0; j < asientos[i].length; j++) {
                    System.out.print(asientos[i][j] ? "[X]  " : "[ ]  ");
                }
                System.out.println("($" + precios[i] + ")" + "-" + NombreEntradas[i]);

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


        private Boleta descuento (Scanner sc, int precioAsiento, String codigoAsiento){

            int edad = 0;
            boolean edadValida = false;
            double descuentoaplicado= 0;

            while (!edadValida) {
                try {
                    System.out.print("Por favor ingrese su edad: ");
                    edad = sc.nextInt();
                    sc.nextLine();

                    if (edad <= 0) {
                        System.out.println("Error: La edad no puede ser negativa o cero.");
                    } else if (edad > 120) {
                        System.out.println("Error: La edad no puede ser mayor a 120 años.");
                    } else {
                        edadValida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Debe ingresar un número entero válido.");
                    sc.nextLine();
                }// Calcular descuento

                if (edad >= 60) {
                    descuentoaplicado = 0.15;
                    System.out.println(" " );
                    System.out.println("Descuento del 15% aplicado (adulto mayor)");
                    System.out.println(" " );

                } else if (edad<=25) {
                    descuentoaplicado = 0.10;
                    System.out.println(" " );
                    System.out.println("Descuento del 10% aplicado (Estudiante)");
                    System.out.println(" " );


                } else {
                    descuentoaplicado = 0;
                    System.out.println("Sin descuento aplicado");
                }


            }

            // Cálculos
            double descuento = precioAsiento * descuentoaplicado;
            double totalapagar = precioAsiento - descuento;

            System.out.println("========= RESUMEN DE COMPRA =========" );
            System.out.println("Precio original: $" + precioAsiento);
            System.out.println("Descuento: $" + descuento);
            System.out.println("Total a pagar: $" + totalapagar);
            System.out.println("=====================================" );
            System.out.println(" " );

            return new Boleta(codigoAsiento, precioAsiento, descuento, totalapagar, edad);

        }

    public void imprimirBoletas() {
        System.out.println("\n==========  BOLETAS EMITIDAS  ==========");
        double totalGeneral = 0;

        for (int i = 0; i < boletas.size(); i++) {
            Boleta boleta = boletas.get(i);
            System.out.println("\n--- Boleta #" + (i + 1) + " ---");
            System.out.println("Asiento: " + boleta.asiento);
            System.out.println("Edad cliente: " + boleta.edadCliente);
            System.out.println("Precio original: $" + boleta.precioOriginal);
            System.out.println("Descuento: $" + boleta.descuento);
            System.out.println("Total a pagar: $" + boleta.totalPagar);
            totalGeneral += boleta.totalPagar;
        }

        System.out.println("\n=======================================");
        System.out.println("TOTAL GENERAL: $" + totalGeneral);
        System.out.println("Cantidad de boletas: " + boletas.size());
        System.out.println("=======================================");
    }

}

