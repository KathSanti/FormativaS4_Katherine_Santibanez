package Formativas_DuocUC.Semana5;

import java.util.Scanner;

public class MenuPrincipal {

    public static int op = 0; // Variable estática que controlará el bucle

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char confirma = 'S';       // Variable Local
        char volverMenu = 'S';     // Variable Local
        int compraNumero = 1;      // Variable Local

        do {
            System.out.println("========================================================================");
            System.out.println("||                     BIENVENIDO AL TEATRO  MORO                     ||");
            System.out.println("========================================================================");
            System.out.println(" ");
            System.out.println("Por favor escoge una opción");
            System.out.println("1) Comprar entrada");
            System.out.println("2) Mostrar mapa de asientos");
            System.out.println("3) Detalle compra");
            System.out.println("4) Eliminar entrada");
            System.out.println("5) Salir");

            System.out.print("Opción: ");
            String opcionMenu = sc.nextLine().trim();

            switch (opcionMenu) {
                case "1":
                    CompraEntradas(sc);//LLamar metodo para realizar el proceso de compras

                    if (compraNumero >= 1) {
                        System.out.print("¿Deseas volver al menú principal? (S/N): ");
                        confirma = sc.next().charAt(0);
                        sc.nextLine();

                        while (confirma != 'S' && confirma != 's' && confirma != 'N' && confirma != 'n') {
                            System.out.println("Opción no válida");
                            System.out.print("Ingrese una opción válida (S/N): ");
                            confirma = sc.next().charAt(0);
                            sc.nextLine();
                        }

                        if (confirma == 'N' || confirma == 'n') {
                            System.out.println("Gracias por su compra. ¡Hasta pronto!");
                            volverMenu = 'N';
                        }
                    }
                    break;

                case "2":
                    // Crear instancia
                    CompraEntradas compra = new CompraEntradas();
                    compra.mostrarAsientos();//LLamar metodo para Mostrar mapa con los asientos disponibles

                    // Preguntar si desea volver al menú
                    System.out.print("¿Deseas volver al menú principal? (S/N): ");
                    volverMenu = sc.next().charAt(0);
                    sc.nextLine();

                    while (volverMenu != 'S' && volverMenu != 's' && volverMenu != 'N' && volverMenu != 'n') {
                        System.out.println("Opción no válida");
                        System.out.print("Ingrese una opción válida (S/N): ");
                        volverMenu = sc.next().charAt(0);
                        sc.nextLine();
                    }

                    if (volverMenu == 'N' || volverMenu == 'n') {
                        System.out.println("Gracias por visitarnos. ¡Hasta pronto!");
                    }
                    break;

                case "3":
                    CompraEntradas imprimir = new CompraEntradas();
                    imprimir.imprimirBoletas();//LLamar metodo para imprimir todas las boletas emitidad

                    // Preguntar si desea volver al menú
                    System.out.print("¿Deseas volver al menú principal? (S/N): ");
                    volverMenu = sc.next().charAt(0);
                    sc.nextLine();

                    while (volverMenu != 'S' && volverMenu != 's' && volverMenu != 'N' && volverMenu != 'n') {
                        System.out.println("Opción no válida");
                        System.out.print("Ingrese una opción válida (S/N): ");
                        volverMenu = sc.next().charAt(0);
                        sc.nextLine();
                    }

                    if (volverMenu == 'N' || volverMenu == 'n') {
                        System.out.println("Gracias por visitarnos. ¡Hasta pronto!");
                    }
                    break;

                case "4":
                    CompraEntradas eliminar = new CompraEntradas();
                    eliminar.eliminarBoletaPorAsiento(sc);//LLamar metodo para eliminar un asiento

                    // Preguntar si desea volver al menú
                    System.out.print("¿Deseas volver al menú principal? (S/N): ");
                    volverMenu = sc.next().charAt(0);
                    sc.nextLine();

                    while (volverMenu != 'S' && volverMenu != 's' && volverMenu != 'N' && volverMenu != 'n') {
                        System.out.println("Opción no válida");
                        System.out.print("Ingrese una opción válida (S/N): ");
                        volverMenu = sc.next().charAt(0);
                        sc.nextLine();
                    }

                    if (volverMenu == 'N' || volverMenu == 'n') {
                        System.out.println("Gracias por visitarnos. ¡Hasta pronto!");
                    }
                    break;

                case "5":
                    System.out.println("Gracias por visitarnos");
                    volverMenu = 'N';
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (volverMenu != 'N' && volverMenu != 'n');

        sc.close();
    }

    public static void CompraEntradas(Scanner sc) {
        CompraEntradas pc = new CompraEntradas();
        pc.CompraEntradas(sc);
    }




}


