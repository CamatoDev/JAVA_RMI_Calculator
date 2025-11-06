/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vortexmakers.java_rmi_v2;

/**
 *
 * @author mrcto
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface pour la calculatrice distante
 * Toutes les méthodes doivent déclarer RemoteException
 */
public interface Calculator extends Remote {
    
    // Opérations arithmétiques de base
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
    
    // Opérations avancées
    double power(double base, double exponent) throws RemoteException;
    double squareRoot(double a) throws RemoteException;
    double modulo(double a, double b) throws RemoteException;
    
    // Opérations scientifiques
    double sin(double angle) throws RemoteException;
    double cos(double angle) throws RemoteException;
    double tan(double angle) throws RemoteException;
    
    // Mémoire de la calculatrice
    void setMemory(double value) throws RemoteException;
    double getMemory() throws RemoteException;
    void clearMemory() throws RemoteException;
    
    // Information sur le serveur
    String getServerInfo() throws RemoteException;
}
