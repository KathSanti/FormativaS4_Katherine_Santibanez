package Semana4Formativa;

import java.util.Scanner;

public class formativas4 {
    public static int op = 1; // Variable estática que controlará el bucle

    //Menú con ciclo for

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char confirma = 'S';

        for (int compraNumero = 1; confirma == 'S' || confirma == 's'; compraNumero++) {
            System.out.println("========================================================================");
            System.out.println("||                     BIENVENIDO AL TEATRO  MORO                     ||");
            System.out.println("========================================================================");
            System.out.println(" ");
            System.out.println("Por favor escoge una opción");
            System.out.println("1) Comprar entrada");
            System.out.println("2) Salir");

            System.out.print("Opción: ");
            String opcionMenu = sc.nextLine().trim();//entrada

            if (opcionMenu.equals("1")) {
                procesarCompra(sc);//llama a la clase procesar compra

                //sub preguntas después del proceso de compras

                if (compraNumero >= 1) {
                    System.out.print("¿Deseas realizar otra compra? (S/N): ");
                    confirma = sc.next().charAt(0);//entrada
                    sc.nextLine();

                    while (confirma != 'S' && confirma != 's' && confirma != 'N' && confirma != 'n') {
                        System.out.println("Opción no válida");//salida error
                        System.out.print("Ingrese una opción válida (S/N): ");
                        confirma = sc.next().charAt(0);//entrada error
                        sc.nextLine();
                    }

                    if (confirma == 'N' || confirma == 'n') {
                        System.out.println("Gracias por su compra. ¡Hasta pronto!");//salida
                        break;
                    }
                }

            } else if (opcionMenu.equals("2")) {
                System.out.println("Gracias por visitarnos");//salida
                break;
            } else {
                System.out.println("Opción no válida");
            }
        }
    }


    //llamar al proceso de compra para usar de puente en mi clase main se define pc como objeto utilizando scanner para que pueda ser usado


    public static void procesarCompra(Scanner sc) {
        procesarCompra pc = new procesarCompra();//llamamos
        pc.procesarCompra(sc);
    }
}