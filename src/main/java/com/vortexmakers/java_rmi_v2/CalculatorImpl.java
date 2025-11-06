/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vortexmakers.java_rmi_v2;

/**
 *
 * @author mrcto
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implémentation de la calculatrice distante
 * Doit étendre UnicastRemoteObject et implémenter notre interface
 */
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    
    private double memory;
    
    // Constructeur doit déclarer RemoteException
    public CalculatorImpl() throws RemoteException {
        super(); // Appelle le constructeur de UnicastRemoteObject
        this.memory = 0.0;
    }
    
    // === OPÉRATIONS DE BASE ===
    
    @Override
    public double add(double a, double b) throws RemoteException {
        System.out.println("Serveur: Addition " + a + " + " + b);
        return a + b;
    }
    
    @Override
    public double subtract(double a, double b) throws RemoteException {
        System.out.println("Serveur: Soustraction " + a + " - " + b);
        return a - b;
    }
    
    @Override
    public double multiply(double a, double b) throws RemoteException {
        System.out.println("Serveur: Multiplication " + a + " * " + b);
        return a * b;
    }
    
    @Override
    public double divide(double a, double b) throws RemoteException {
        System.out.println("Serveur: Division " + a + " / " + b);
        if (b == 0) {
            throw new RemoteException("Erreur: Division par zéro!");
        }
        return a / b;
    }
    
    // === OPÉRATIONS AVANCÉES ===
    
    @Override
    public double power(double base, double exponent) throws RemoteException {
        System.out.println("Serveur: Puissance " + base + " ^ " + exponent);
        return Math.pow(base, exponent);
    }
    
    @Override
    public double squareRoot(double a) throws RemoteException {
        System.out.println("Serveur: Racine carrée de " + a);
        if (a < 0) {
            throw new RemoteException("Erreur: Racine carrée d'un nombre négatif!");
        }
        return Math.sqrt(a);
    }
    
    @Override
    public double modulo(double a, double b) throws RemoteException {
        System.out.println("Serveur: Modulo " + a + " % " + b);
        if (b == 0) {
            throw new RemoteException("Erreur: Modulo par zéro!");
        }
        return a % b;
    }
    
    // === OPÉRATIONS SCIENTIFIQUES ===
    
    @Override
    public double sin(double angle) throws RemoteException {
        System.out.println("Serveur: Sinus de " + angle + " radians");
        return Math.sin(angle);
    }
    
    @Override
    public double cos(double angle) throws RemoteException {
        System.out.println("Serveur: Cosinus de " + angle + " radians");
        return Math.cos(angle);
    }
    
    @Override
    public double tan(double angle) throws RemoteException {
        System.out.println("Serveur: Tangente de " + angle + " radians");
        return Math.tan(angle);
    }
    
    // === GESTION DE LA MÉMOIRE ===
    
    @Override
    public void setMemory(double value) throws RemoteException {
        System.out.println("Serveur: Mémoire définie à " + value);
        this.memory = value;
    }
    
    @Override
    public double getMemory() throws RemoteException {
        System.out.println("Serveur: Lecture mémoire: " + memory);
        return this.memory;
    }
    
    @Override
    public void clearMemory() throws RemoteException {
        System.out.println("Serveur: Mémoire effacée");
        this.memory = 0.0;
    }
    
    // === INFORMATION SERVEUR ===
    
    @Override
    public String getServerInfo() throws RemoteException {
        return "Calculatrice RMI Server - " + new java.util.Date();
    }
}