package Formativas_DuocUC.Semana5;

import java.util.Scanner;

public class MenuPrincipal {

    public static int op = 1; // Variable estática que controlará el bucle

    public static void main(String[] args) {


        //Menú con ciclo for.

            Scanner sc = new Scanner(System.in); //hola kkio+o
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
                    CompraEntradas(sc);//llama a la clase procesar compra

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


        public static void CompraEntradas(Scanner sc) {
            CompraEntradas pc = new CompraEntradas();//llamamos
            pc.CompraEntradas(sc);
        }
    }