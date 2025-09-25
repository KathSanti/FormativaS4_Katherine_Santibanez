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

                    break;
                case 2:



                    break;
                case 3:


                    break;
                case 4:

                    break;

                default:
                    System.out.println("Opción no válida");
            }



        }while ((salirMenu != 'S' && salirMenu != 's') && (volverMenu == 'S' || volverMenu == 's'));




    }
}
