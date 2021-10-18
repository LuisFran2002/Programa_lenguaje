/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.programa_lenguaje;

import com.unicahiccpmp.utilities.layout;
import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class main {
    public static void main (String[] args) {
        layout.printHeader("Lenguajes App v1.0");
        String OpcionMenu = "";
    
    Scanner entradaTeclado = new Scanner(System.in);
    Aplicacion lenguajes = new Aplicacion(entradaTeclado);
    
    
    while (!(OpcionMenu.toUpperCase().equals("S"))) {
            layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();

            System.out.println("Texto ingresado es igual a " + OpcionMenu);
            // Verificar las Opciones
            lenguajes.activarEvento(OpcionMenu);

        }
    }
}