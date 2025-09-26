package Formativas_DuocUC.Semana7;

import java.util.Scanner;

import Formativas_DuocUC.Semana7.GestionListDataJava;

public class TeatroMoroMenu {

    public static int InteractiveButton = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char confirma = 'S';
        char volverMenu = 'S';
        char salirMenu = 'N';

        do {
            System.out.println("========================================================================");
            System.out.println("||                     BIENVENIDO AL TEATRO  MORO                     ||");
            System.out.println("========================================================================");
            System.out.println(" ");
            System.out.println("Por favor escoge una opción");
            System.out.println("1) Venta de entradas");
            System.out.println("2) Resumen de Ventas");
            System.out.println("3) Generar Boleta");
            System.out.println("4) Salir");

            System.out.print("Opción: ");
            int opcionMenu = sc.nextInt();
            sc.nextLine();

            switch (opcionMenu) {
                case 1:
                    VentaEntradas(sc);

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

                    break;
                case 2:
                    GestionListDataJava resumen = new GestionListDataJava();
                    resumen.ResumenVentas();

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

                    break;
                case 3:
                    GestionListDataJava imprimir = new GestionListDataJava();
                    imprimir.imprimirBoletas();

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

                    break;
                case 4:
                    // Preguntar si esta seguro de salir
                    System.out.print("¿Estás seguro que deseas salir? (S/N): ");
                    salirMenu = sc.next().charAt(0);
                    sc.nextLine();

                    while (salirMenu != 'S' && salirMenu != 's' && salirMenu != 'N' && salirMenu != 'n') {
                        System.out.println("Opción no válida");
                        System.out.print("Ingrese una opción válida (S/N): ");
                        salirMenu = sc.next().charAt(0);
                        sc.nextLine();
                    }

                    if (salirMenu == 'N' || salirMenu == 'n') {
                        System.out.println("Volviendo al menú principal");
                    }
                    if (salirMenu == 'S' || salirMenu == 's') {
                        System.out.println("Gracias por visitarnos. ¡Hasta pronto! ");
                    }

                    break;

                default:
                    System.out.println("Opción no válida");
            }



        }while ((salirMenu != 'S' && salirMenu != 's') && (volverMenu == 'S' || volverMenu == 's'));




    }

    public static void VentaEntradas(Scanner sc) {
        GestionListDataJava Shopping = new GestionListDataJava();
        Shopping.VentaEntradas(sc);
    }
}
