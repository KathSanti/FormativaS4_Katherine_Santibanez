package Semana4Formativa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class procesarCompra {

    //variables taller 1 programación

    static Boolean A1 = false, A2 = false, A3 = false, A4 = false, A5 = false, A6 = false;
    static Boolean B1 = false, B2 = false, B3 = false, B4 = false, B5 = false, B6 = false;
    static Boolean C1 = false, C2 = false, C3 = false, C4 = false, C5 = false, C6 = false;
    static Boolean D1 = false, D2 = false, D3 = false, D4 = false, D5 = false, D6 = false;
    static Boolean E1 = false, E2 = false, E3 = false, E4 = false, E5 = false, E6 = false;

    public void procesarCompra(Scanner sc) {
        // Reiniciar op a 1 al comenzar el proceso
        formativas4.op = 1;

        while (formativas4.op == 1) {

            for (int i = 1; i <= 6; i++) {
                System.out.print("   " + i + " ");
            }
            System.out.println(" ");
            System.out.println("A | "
                    + (A1 ? "[X]" : "[ ]") + " "
                    + (A2 ? "[X]" : "[ ]") + " "
                    + (A3 ? "[X]" : "[ ]") + " "
                    + (A4 ? "[X]" : "[ ]") + " "
                    + (A5 ? "[X]" : "[ ]") + " "
                    + (A6 ? "[X]" : "[ ]") + " ($20.000)");
            System.out.println("B | "
                    + (B1 ? "[X]" : "[ ]") + " "
                    + (B2 ? "[X]" : "[ ]") + " "
                    + (B3 ? "[X]" : "[ ]") + " "
                    + (B4 ? "[X]" : "[ ]") + " "
                    + (B5 ? "[X]" : "[ ]") + " "
                    + (B6 ? "[X]" : "[ ]") + " ($18.000)");
            System.out.println("C | "
                    + (C1 ? "[X]" : "[ ]") + " "
                    + (C2 ? "[X]" : "[ ]") + " "
                    + (C3 ? "[X]" : "[ ]") + " "
                    + (C4 ? "[X]" : "[ ]") + " "
                    + (C5 ? "[X]" : "[ ]") + " "
                    + (C6 ? "[X]" : "[ ]") + " ($16.000)");
            System.out.println("D | "
                    + (D1 ? "[X]" : "[ ]") + " "
                    + (D2 ? "[X]" : "[ ]") + " "
                    + (D3 ? "[X]" : "[ ]") + " "
                    + (D4 ? "[X]" : "[ ]") + " "
                    + (D5 ? "[X]" : "[ ]") + " "
                    + (D6 ? "[X]" : "[ ]") + " ($14.000)");
            System.out.println("E | "
                    + (E1 ? "[X]" : "[ ]") + " "
                    + (E2 ? "[X]" : "[ ]") + " "
                    + (E3 ? "[X]" : "[ ]") + " "
                    + (E4 ? "[X]" : "[ ]") + " "
                    + (E5 ? "[X]" : "[ ]") + " "
                    + (E6 ? "[X]" : "[ ]") + " ($11.000)");

            System.out.print("Ingrese asiento (ej: A1, B5, E6): ");
            String codigo = sc.nextLine().trim().toUpperCase();

            if (codigo.length() < 2) {
                System.out.println("Código inválido");
                continue;
            }

            char fila = codigo.charAt(0);
            String numTxt = codigo.substring(1);
            int nro;

            try {
                nro = Integer.parseInt(numTxt);
            } catch (NumberFormatException e) {
                System.out.println("Número del asiento es inválido");
                continue;
            }

            if (fila < 'A' || fila > 'E' || nro < 1 || nro > 6) {
                System.out.println("Asientos fuera de rango (fila A-E, Columnas 1-6)");
                continue;
            }

            boolean ocupado = false;
            if (fila == 'A') {
                if (nro == 1) ocupado = A1;
                else if (nro == 2) ocupado = A2;
                else if (nro == 3) ocupado = A3;
                else if (nro == 4) ocupado = A4;
                else if (nro == 5) ocupado = A5;
                else ocupado = A6;
            }
            if (fila == 'B') {
                if (nro == 1) ocupado = B1;
                else if (nro == 2) ocupado = B2;
                else if (nro == 3) ocupado = B3;
                else if (nro == 4) ocupado = B4;
                else if (nro == 5) ocupado = B5;
                else ocupado = B6;
            }
            if (fila == 'C') {
                if (nro == 1) ocupado = C1;
                else if (nro == 2) ocupado = C2;
                else if (nro == 3) ocupado = C3;
                else if (nro == 4) ocupado = C4;
                else if (nro == 5) ocupado = C5;
                else ocupado = C6;
            }
            if (fila == 'D') {
                if (nro == 1) ocupado = D1;
                else if (nro == 2) ocupado = D2;
                else if (nro == 3) ocupado = D3;
                else if (nro == 4) ocupado = D4;
                else if (nro == 5) ocupado = D5;
                else ocupado = D6;
            }
            if (fila == 'E') {
                if (nro == 1) ocupado = E1;
                else if (nro == 2) ocupado = E2;
                else if (nro == 3) ocupado = E3;
                else if (nro == 4) ocupado = E4;
                else if (nro == 5) ocupado = E5;
                else ocupado = E6;
            }

            if (ocupado) {
                System.out.println("[x] Ese Asiento ya está Ocupado, Elegir Otro.");
                continue;
            }

            int precio;
            if (fila == 'A') precio = 20000;
            else if (fila == 'B') precio = 18000;
            else if (fila == 'C') precio = 16000;
            else if (fila == 'D') precio = 14000;
            else precio = 11000;

            System.out.println("Asiento " + codigo + "-Precio: $" + precio);
            System.out.print("¿Confirmar reserva (S/N)? ");
            String confirmar = sc.nextLine().trim().toUpperCase(); //entrada confirmar reserva

            if (!confirmar.equals("S")) {
                System.out.println("Reserva cancelada");//si cancela la reserva  vuelve al menú principal
                continue;
            }

            // Reservar asiento
            if (fila == 'A') {
                if (nro == 1) A1 = true;
                else if (nro == 2) A2 = true;
                else if (nro == 3) A3 = true;
                else if (nro == 4) A4 = true;
                else if (nro == 5) A5 = true;
                else A6 = true;
            }
            if (fila == 'B') {
                if (nro == 1) B1 = true;
                else if (nro == 2) B2 = true;
                else if (nro == 3) B3 = true;
                else if (nro == 4) B4 = true;
                else if (nro == 5) B5 = true;
                else B6 = true;
            }
            if (fila == 'C') {
                if (nro == 1) C1 = true;
                else if (nro == 2) C2 = true;
                else if (nro == 3) C3 = true;
                else if (nro == 4) C4 = true;
                else if (nro == 5) C5 = true;
                else C6 = true;
            }
            if (fila == 'D') {
                if (nro == 1) D1 = true;
                else if (nro == 2) D2 = true;
                else if (nro == 3) D3 = true;
                else if (nro == 4) D4 = true;
                else if (nro == 5) D5 = true;
                else D6 = true;
            }
            if (fila == 'E') {
                if (nro == 1) E1 = true;
                else if (nro == 2) E2 = true;
                else if (nro == 3) E3 = true;
                else if (nro == 4) E4 = true;
                else if (nro == 5) E5 = true;
                else E6 = true;
            }

            // Parametros para aplicar descuento

            int edad = 0;
            boolean edadValida = false;
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
                }
            }



            char verificadordescuento;// variable de entrada para verificar descuento estudiante y edad

            System.out.print("¿Eres estudiante? S/N: ");
            verificadordescuento = sc.next().charAt(0);//Entrada
            sc.nextLine();

            while (verificadordescuento != 'S' && verificadordescuento != 'N' && verificadordescuento != 's' && verificadordescuento != 'n') {
                System.out.println("Opción no válida");
                System.out.print("Ingrese una opción válida (S/N): ");
                verificadordescuento = sc.next().charAt(0);//Entrada error
                sc.nextLine();
            }

            // Calcular descuento
            double descuentoaplicado;
            if (edad >= 60) {
                descuentoaplicado = 0.15;
                System.out.println("Descuento del 15% aplicado (adulto mayor)");

            } else if (verificadordescuento == 'S' || verificadordescuento == 's') {
                descuentoaplicado = 0.10;
                System.out.println("Descuento del 10% aplicado");

            } else {
                descuentoaplicado = 0;
                System.out.println("Sin descuento aplicado");
            }

            //Calculo descuento y precio total a pagar

            double descuento = precio * descuentoaplicado;
            double totalapagar = precio - descuento;

            // Mostrar boleta
            System.out.println(" ");
            System.out.println("==== BOLETA DE RESERVA =====");
            System.out.println("Asiento: " + codigo);
            System.out.println("Fila: " + fila + " | Columna: " + nro);
            System.out.println("precio:  " + precio);
            System.out.println("Descuento: $" + descuento);
            System.out.println("Total a pagar: $" + totalapagar);
            System.out.println("============================");
            System.out.println(" ");

            break;
        }// Usar la variable estática de la clase Main


        boolean opcionValida = false;

        do {

            try {
                System.out.print("¿Desea realizar otra reserva? (1 = Sí, 0 = No): ");// Al final del proceso, preguntar si desea continuar
                formativas4.op = sc.nextInt();// Actualizar la variable estática
                sc.nextLine();// Limpiar el buffer

                if (formativas4.op !=1 |formativas4.op !=0) {
                    opcionValida = true;
                }else {
                    System.out.println("Opción no válida ingresa el número 1 o 0 ");
                }
            }catch (InputMismatchException e){
                System.out.println("Opción no válida ingresa el número 1 o 0 ");
                sc.nextLine();
            }

        } while (!opcionValida);//Fin while opción valida




            if (formativas4.op != 1) {
                System.out.println("Volviendo al menú principal...");
            }
        }
    }
