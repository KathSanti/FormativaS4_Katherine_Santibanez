package Formativas_DuocUC.Semana5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompraEntradas {

    // Variables optimizadas usando arrays
    static boolean[][] asientos = new boolean[5][6];              // Variable estática para definir el total de asientos en el teatro
    static int[] precios = {20000, 18000, 16000, 14000, 11000};  // Variable estática para definir precios por fila
    static String[] NombreEntradas = {"VIP","Platea Baja","Platea Baja","Palcos","Tribunas"}; // Variable estática para definir zonas por fila
    static ArrayList<Boleta> boletas = new ArrayList<>();       // Variable estática para almacenar la cantidad de boletas emitidas y detalle de compra

    class Boleta {
        String asiento;        // Variable de instancia
        int precioOriginal;    // Variable de instancia
        double descuento;      // Variable de instancia
        double totalPagar;     // Variable de instancia
        int edadCliente;       // Variable de instancia

        public Boleta(String asiento, int precioOriginal, double descuento, double totalPagar, int edadCliente) {
            this.asiento = asiento;                // Variable de instancia
            this.precioOriginal = precioOriginal;  // Variable de instancia
            this.descuento = descuento;            // Variable de instancia
            this.totalPagar = totalPagar;          // Variable de instancia
            this.edadCliente = edadCliente;        // Variable de instancia
        }
    }

        // Precios por fila

        public void CompraEntradas(Scanner sc) {
            MenuPrincipal.op = 1; //Variables estatica llamada desde la clase main
            boletas.clear();


            while (MenuPrincipal.op == 1) {
                mostrarAsientos();//Llamar metodo para mostrar mapa de asientos

                System.out.print("Ingrese asiento (ej: A1, B5, E6): ");
                String codigo = sc.nextLine().trim().toUpperCase(); //Variable local

                if (!validarFormatoCodigo(codigo)) {
                    System.out.println("Código inválido. Formato correcto: Letra (A-E) + Número (1-6)");
                    continue;
                }

                char filaChar = codigo.charAt(0);
                int fila = filaChar - 'A'; // Convertir a índice (0-4)//Variable local
                int columna = Integer.parseInt(codigo.substring(1)) - 1; // Convertir a índice (0-5) //Variable local

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
                if (!respuesta.equals("S") && !respuesta.equals("SI")&& !respuesta.equals("s")) {
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
            String respuesta = sc.nextLine().trim().toUpperCase();//Variable Local
            return respuesta.equals("S") || respuesta.equals("SI")|| respuesta.equals("s");
        }


        private Boleta descuento (Scanner sc, int precioAsiento, String codigoAsiento){

            int edad = 0;                   // Variable Local
            boolean edadValida = false;     // Variable Local
            double descuentoaplicado= 0;    // Variable Local

            while (!edadValida) {
                try {
                    System.out.print("\n");
                    System.out.print("================ PROMOCIONES ===============");
                    System.out.print("\n");
                    System.out.print("\na) Descuento del 10% por ser estudiantes ");
                    System.out.print("\nb) Descuento del 15% Adulto Mayor ");
                    System.out.print("\n");
                    System.out.print("\nPor favor ingrese su edad pata obtener tu descuento: ");
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
            double descuento = precioAsiento * descuentoaplicado;   // Variable Local
            double totalapagar = precioAsiento - descuento;         // Variable Local

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
        double totalGeneral = 0; // Variable Local

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

    public void eliminarBoletaPorAsiento(Scanner sc) {
        if (boletas.isEmpty()) {
            System.out.println("No hay boletas para eliminar.");
            return;
        }

        System.out.print("Ingrese el asiento de la boleta a eliminar (ej: A1, B5): ");
        String asientoABuscar = sc.nextLine().trim().toUpperCase();//Variable Local

        // Validar formato
        if (!validarFormatoCodigo(asientoABuscar)) {
            System.out.println("Formato de asiento inválido.");
            return;
        }

        try {
            // Convertir asiento en dos digitos como cordenas
            char filaChar = asientoABuscar.charAt(0);
            int fila = filaChar - 'A';//Variable Local
            int columna = Integer.parseInt(asientoABuscar.substring(1)) - 1;//Variable Local

            // Validar rango
            if (!validarRangoAsiento(fila, columna)) {
                System.out.println("Asiento fuera de rango.");
                return;
            }

            // Buscar y eliminar boleta
            boolean encontrada = false; // Variable Local
            for (int i = 0; i < boletas.size(); i++) {
                Boleta b = boletas.get(i);
                if (b.asiento.equals(asientoABuscar)) {
                    // Liberar asiento
                    asientos[fila][columna] = false;
                    // Eliminar boleta
                    boletas.remove(i);
                    System.out.println("Boleta eliminada correctamente. Asiento " + asientoABuscar + " liberado.");
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("No se encontró boleta para el asiento: " + asientoABuscar);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de asiento inválido.");
        }
    }

}

