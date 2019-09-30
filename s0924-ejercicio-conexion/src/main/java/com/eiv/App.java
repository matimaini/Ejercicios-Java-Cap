package com.eiv;

import java.util.Scanner;

public class App {

    private static final Scanner SCANNER = new Scanner(System.in);
    PersonasBusqueda personasBusqueda = new PersonasBusqueda();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {

        System.out.println("Ingrese un nombre: ");
        String nom = SCANNER.next();

        personasBusqueda.buscar(nom);
    }

}
