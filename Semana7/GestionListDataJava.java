package Formativas_DuocUC.Semana7;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class GestionListDataJava {

    static boolean[][] asientos = new boolean[5][6];
    static int[] preciosUnitarios = {20000, 18000, 16000, 14000, 11000};
    static String[] RowEntryType = {"VIP", "Platea Alta", "Platea Baja", "Palcos", "Balcon"};
    static ArrayList<Boleta> boletas = new ArrayList<>();


    // Clase para guardar los valores de compra

    class Boleta {
        String asiento;        // Variable de instancia
        String zona;           // Variable de instancia
        int precioOriginal;    // Variable de instancia
        double descuento;      // Variable de instancia
        double totalPagar;     // Variable de instancia
        int edadCliente;       // Variable de instancia

        public Boleta(String asiento, String zona, int precioOriginal, double descuento, double totalPagar, int edadCliente) {
            this.asiento = asiento;                // Variable de instancia
            this.zona = zona;                      // Variable de instancia
            this.precioOriginal = precioOriginal;  // Variable de instancia
            this.descuento = descuento;            // Variable de instancia
            this.totalPagar = totalPagar;          // Variable de instancia
            this.edadCliente = edadCliente;        // Variable de instancia
        }
    }


    public void VentaEntradas(Scanner sc) {
        TeatroMoroMenu.InteractiveButton = 1;
        boolean continuarComprando = true;

        while (continuarComprando) {
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

            int precioAsiento = preciosUnitarios[fila];
            String zonaAsiento = RowEntryType[fila];
            System.out.println("Asiento " + coordenadaAsiento + " - Precio: $" + precioAsiento + " - Zona: " + zonaAsiento);

            // Preguntar si quiere comprar este asiento
            System.out.print("¿Deseas comprar este asiento? (S/N): ");
            char respuestaCompra = sc.next().charAt(0);
            sc.nextLine();

            if (respuestaCompra != 'S' && respuestaCompra != 's'){
                System.out.println("Compra cancelada para este asiento.");
                continue;
            }

            // Procesar descuento y crear boleta
            Boleta boleta = Descuento(sc, precioAsiento, coordenadaAsiento, zonaAsiento);
            boletas.add(boleta);

            // Reservar asiento
            asientos[fila][columna] = true;
            System.out.println("¡Compra confirmada! Asiento " + coordenadaAsiento + " reservado.");

            // Preguntar si quiere comprar otro asiento
            System.out.print("¿Desea comprar otro asiento? (S/N): ");
            char respuestaContinuar = sc.next().charAt(0);
            sc.nextLine();


            if (respuestaContinuar == 'n' || respuestaContinuar == 'N' ) {
                continuarComprando = false;
                System.out.println("Gracias por su compra!");
            }
        }
    }

    public void MostrarMapaAsientos() {


        System.out.println("\n==========  RESERVA DE ASIENTOS  ==========");
        System.out.println("\n            [ ]Libre - [X] Ocupado         ");
        System.out.println(" ");
        System.out.println("   " + "  1    2    3    4    5    6");

        char[] letrasFila = {'A', 'B', 'C', 'D', 'E'};

        for (int i = 0; i < asientos.length; i++) {
            System.out.print(letrasFila[i] + " | ");
            for (int j = 0; j < asientos[i].length; j++) {
                if  (asientos[i][j]) {
                    System.out.print("[X]  ");

                }else{
                    System.out.print("[ ]  ");
                }

            }

            System.out.println("($" + preciosUnitarios[i] +")" + " Zona: " + RowEntryType[i] );

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


    private Boleta Descuento (Scanner sc, int precioAsiento, String codigoAsiento, String zonaAsiento){

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

        return new Boleta(codigoAsiento, zonaAsiento, precioAsiento, descuento, totalapagar, edad);

    }

    public void ResumenVentas() {

        if (boletas.size() == 0) {
            System.out.println("=====================================");
            System.out.println("    Aún no hay ventas realizadas.    ");
            System.out.println("=====================================");
            return;
        }
        System.out.println("\n==========  RESUMEN VENTAS  ==========");
        double totalGeneral = 0;
        double totalDescuento = 0;// Variable Local

        for (int i = 0; i < boletas.size(); i++) {
            GestionListDataJava.Boleta boleta = boletas.get(i);
            totalGeneral += boleta.totalPagar;
            totalDescuento += boleta.descuento;
        }

        System.out.println("TOTAL GENERAL: $" + totalGeneral);
        System.out.println("TOTAL DESCUENTOS: $" + totalDescuento);
        System.out.println("Cantidad de boletas: " + boletas.size());
        System.out.println("=======================================");
    }

    public void imprimirBoletas (){

        if (boletas.size() == 0) {
            System.out.println("=====================================");
            System.out.println("       No hay compras realizadas.    ");
            System.out.println("=====================================");
            return;
        }


        System.out.println("\n==========  Teatro Moro ==============");

        for (int i = 0; i < boletas.size(); i++) {
            GestionListDataJava.Boleta boleta = boletas.get(i);
            System.out.println("\n--- Boleta #" + (i + 1) + " ---");
            System.out.println("Asiento        : " + boleta.asiento);
            System.out.println("Zona           : " + boleta.zona);
            System.out.println("Edad cliente   : " + boleta.edadCliente);
            System.out.println("Precio original: $" + boleta.precioOriginal);
            System.out.println("Descuento      : $" + boleta.descuento);
            System.out.println("Total a pagar  : $" + boleta.totalPagar);


        }

        System.out.println("\n=======================================");

        System.out.println("\n¡Gracias por su visita al teatro Moro!");

        System.out.println("\n=======================================");

    }






}
