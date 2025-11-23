/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.java_rmi_v2;

/**
 *
 * @author mrcto
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    private static final int PORT = 1099;
    private static final String SERVICE_NAME = "CalculatorService";
    
    // Variables configurables
    private static String HOST = "localhost"; // Valeur par défaut

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Demander l'adresse du serveur si non fournie en argument
        if (args.length > 0) {
            HOST = args[0];
        } else {
            System.out.print("Entrez l'adresse IP du serveur [localhost]: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                HOST = input;
            }
        }

        try {
            System.out.println("Connexion au serveur Calculatrice RMI...");
            System.out.println("Tentative de connexion à: " + HOST + ":" + PORT);
            
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);

            System.out.println("Recherche du service: " + SERVICE_NAME);
            Calculator calculator = (Calculator) registry.lookup(SERVICE_NAME);

            System.out.println("Connecté au serveur Calculatrice!");
            System.out.println("Informations serveur: " + calculator.getServerInfo());
            System.out.println("Adresse du serveur: " + HOST);

            runCalculator(calculator, scanner);

        } catch (Exception e) {
            System.err.println("Erreur client: " + e.getMessage());
            System.err.println("Vérifiez que:");
            System.err.println("   - Le serveur est démarré sur " + HOST);
            System.err.println("   - Le port " + PORT + " est accessible");
            System.err.println("   - Les firewalls permettent la communication");
        } finally {
            scanner.close();
            System.out.println("Client fermé");
        }
    }
    
    /**
     * Boucle interactive de la calculatrice
     */
    private static void runCalculator(Calculator calculator, Scanner scanner) {
        boolean running = true;
        
        System.out.println("\n CALCULATRICE RMI - Menu Principal");
        System.out.println("====================================");
        
        while (running) {
            showMenu();
            System.out.print("\n️ Choisissez une opération: ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:  performOperation(calculator, scanner, "Addition", 2); break;
                    case 2:  performOperation(calculator, scanner, "Soustraction", 2); break;
                    case 3:  performOperation(calculator, scanner, "Multiplication", 2); break;
                    case 4:  performOperation(calculator, scanner, "Division", 2); break;
                    case 5:  performOperation(calculator, scanner, "Puissance", 2); break;
                    case 6:  performOperation(calculator, scanner, "Racine carrée", 1); break;
                    case 7:  performOperation(calculator, scanner, "Modulo", 2); break;
                    case 8:  performScientificOperation(calculator, scanner, "Sinus"); break;
                    case 9:  performScientificOperation(calculator, scanner, "Cosinus"); break;
                    case 10: performScientificOperation(calculator, scanner, "Tangente"); break;
                    case 11: handleMemoryOperations(calculator, scanner); break;
                    case 0:  running = false; break;
                    default: System.out.println("Option invalide!");
                }
                
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
                scanner.nextLine(); // Vider le buffer
            }
        }
    }
    
    /**
     * Affiche le menu des opérations
     */
    private static void showMenu() {
        System.out.println("\n--- Opérations de base ---");
        System.out.println("1. Addition (+)");
        System.out.println("2. Soustraction (-)");
        System.out.println("3. Multiplication (×)");
        System.out.println("4. Division (÷)");
        System.out.println("5. Puissance (x^y)");
        System.out.println("6. Racine carrée (√)");
        System.out.println("7. Modulo (%)");
        
        System.out.println("\n--- Fonctions scientifiques ---");
        System.out.println("8. Sinus");
        System.out.println("9. Cosinus");
        System.out.println("10. Tangente");
        
        System.out.println("\n--- Mémoire ---");
        System.out.println("11. Gestion mémoire");
        
        System.out.println("\n0. Quitter");
    }
    
    /**
     * Exécute une opération à 1 ou 2 opérandes
     */
    private static void performOperation(Calculator calculator, Scanner scanner, 
                                       String operationName, int operandCount) {
        try {
            double a = 0, b = 0;
            double result = 0;
            
            if (operandCount >= 1) {
                System.out.print("Entrez le premier nombre: ");
                a = scanner.nextDouble();
            }
            
            if (operandCount >= 2) {
                System.out.print("Entrez le deuxième nombre: ");
                b = scanner.nextDouble();
            }
            
            // Appel RMI selon l'opération
            switch (operationName) {
                case "Addition": result = calculator.add(a, b); break;
                case "Soustraction": result = calculator.subtract(a, b); break;
                case "Multiplication": result = calculator.multiply(a, b); break;
                case "Division": result = calculator.divide(a, b); break;
                case "Puissance": result = calculator.power(a, b); break;
                case "Racine carrée": result = calculator.squareRoot(a); break;
                case "Modulo": result = calculator.modulo(a, b); break;
            }
            
            System.out.println("Résultat " + operationName + ": " + result);
            
        } catch (Exception e) {
            System.err.println("Erreur lors de " + operationName + ": " + e.getMessage());
        }
    }
    
    /**
     * Exécute une opération scientifique (trigonométrie)
     */
    private static void performScientificOperation(Calculator calculator, Scanner scanner, 
                                                 String operationName) {
        try {
            System.out.print("Entrez l'angle en radians: ");
            double angle = scanner.nextDouble();
            
            double result = 0;
            switch (operationName) {
                case "Sinus": result = calculator.sin(angle); break;
                case "Cosinus": result = calculator.cos(angle); break;
                case "Tangente": result = calculator.tan(angle); break;
            }
            
            System.out.println("" + operationName + "(" + angle + ") = " + result);
            
        } catch (Exception e) {
            System.err.println("Erreur lors du calcul: " + e.getMessage());
        }
    }
    
    /**
     * Gère les opérations sur la mémoire
     */
    private static void handleMemoryOperations(Calculator calculator, Scanner scanner) {
        System.out.println("\n Gestion de la mémoire:");
        System.out.println("1. Stocker dans mémoire");
        System.out.println("2. Lire mémoire");
        System.out.println("3. Effacer mémoire");
        System.out.print("Choisissez: ");
        
        try {
            int memChoice = scanner.nextInt();
            
            switch (memChoice) {
                case 1:
                    System.out.print("Entrez la valeur à stocker: ");
                    double value = scanner.nextDouble();
                    calculator.setMemory(value);
                    System.out.println("Valeur stockée: " + value);
                    break;
                    
                case 2:
                    double memValue = calculator.getMemory();
                    System.out.println("Mémoire actuelle: " + memValue);
                    break;
                    
                case 3:
                    calculator.clearMemory();
                    System.out.println("Mémoire effacée");
                    break;
                    
                default:
                    System.out.println("Option mémoire invalide!");
            }
            
        } catch (Exception e) {
            System.err.println("Erreur mémoire: " + e.getMessage());
        }
    }
}