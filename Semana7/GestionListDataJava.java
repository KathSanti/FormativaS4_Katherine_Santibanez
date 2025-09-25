package Formativas_DuocUC.Semana7;

import Formativas_DuocUC.Semana5.CompraEntradas;
import Formativas_DuocUC.Semana7.TeatroMoroMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class GestionListDataJava {

    static boolean[][] asientos = new boolean[5][6];
    static int[] preciosUnitarios = {20000, 18000, 16000, 14000, 11000};
    static String[] RowEntryType = {"VIP", "Platea Alta", "Platea Baja", "Palcos", "Balcon"};
    static ArrayList<Boleta> boletas = new ArrayList<>();


    // Variables estáticas para almacenar información de compra
    static int totalCompra = 0;
    static int cantidadAsientosComprados = 0;
    static List<String> detallesAsientos = new ArrayList<>();

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


    public class VentaEntradas {
        TeatroMoroMenu.InteractiveButton = 1;
        boolean ContinueShopping = true;

        while (ContinueShopping){
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
            System.out.println("Asiento " + coordenadaAsiento + " - Precio: $" + precioAsiento + "Zona: "+ zonaAsiento);

            if (!confirmarCompra(sc)) {
                System.out.println("Reserva cancelada");
                continue;
            }

            ContinueShopping = false;


        }//Fin While






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

                }else{
                    System.out.print("[ ]  ");
                }

            }

            System.out.println("($" + "(" + preciosUnitarios[i] +")" + "Zona: " + RowEntryType[i] );

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

    private boolean confirmarCompra(Scanner sc) {
        System.out.print("¿Deseas confirmar tu compra (S/N)? ");
        String respuesta = sc.nextLine().trim().toUpperCase();//Variable Local
        return respuesta.equals("S") || respuesta.equals("SI")|| respuesta.equals("s");
    }





}
