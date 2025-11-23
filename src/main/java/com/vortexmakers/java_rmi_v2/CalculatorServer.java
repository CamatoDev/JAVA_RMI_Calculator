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
import java.net.InetAddress;

public class CalculatorServer {
    private static final int RMI_PORT = 1099;
    private static final String SERVICE_NAME = "CalculatorService";

    public static void main(String[] args) {
        try {
            System.out.println("Démarrage du serveur Calculatrice RMI...");
            
            // Afficher l'adresse IP de la machine
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Adresse IP du serveur: " + ipAddress);
            System.out.println("Nom d'hôte: " + InetAddress.getLocalHost().getHostName());
            
            // Création de l'implémentation
            CalculatorImpl calculator = new CalculatorImpl();
            
            // Définir l'adresse IP pour RMI (important pour les connexions externes)
            System.setProperty("java.rmi.server.hostname", ipAddress);
            
            // Création du registry
            System.out.println("Démarrage du RMI Registry sur le port " + RMI_PORT + "...");
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(RMI_PORT);
                System.out.println("Nouveau RMI Registry créé");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(RMI_PORT);
                System.out.println("Connexion au RMI Registry existant");
            }

            // Enregistrement du service
            System.out.println("Enregistrement du service sous le nom: " + SERVICE_NAME);
            registry.rebind(SERVICE_NAME, calculator);

            System.out.println("Serveur Calculatrice RMI prêt!");
            System.out.println("Service disponible sur: rmi://" + ipAddress + ":" + RMI_PORT + "/" + SERVICE_NAME);
            System.out.println("Les clients peuvent se connecter avec cette adresse IP");
            System.out.println("️  Pour arrêter le serveur: Ctrl+C");

            keepServerAlive();

        } catch (Exception e) {
            System.err.println("Erreur du serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void keepServerAlive() {
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Serveur arrêté");
        }
    }
}