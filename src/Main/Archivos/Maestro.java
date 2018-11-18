package Main.Archivos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Maestro {
    private RandomAccessFile archivoL;
    private RandomAccessFile archivoE;
    private long ultimo = 0;


    public Maestro() {
    }

    public void escribirB(int llave, String nombre) {
        StringBuffer bf;
        try {
            if ((!nombre.contains("∨") || !nombre.contains("v")) && nombre.contains("-")) {
                archivoE = new RandomAccessFile("maestroB.gsh", "rw");
                ultimo = archivoE.length();
                archivoE.seek(ultimo);
                archivoE.writeInt(llave);
                bf = new StringBuffer(nombre);
                bf.setLength(99);
                archivoE.writeChars(bf.toString());
                ultimo = archivoE.getFilePointer();
                archivoE.close();
            } else {
                if ((nombre.contains("v") || nombre.contains("∨")) && nombre.contains("¬")) {
                    if (nombre.split("¬").length > 1) {
                        archivoE = new RandomAccessFile("maestroB.gsh", "rw");
                        ultimo = archivoE.length();
                        archivoE.seek(ultimo);
                        archivoE.writeInt(llave);
                        bf = new StringBuffer(nombre);
                        bf.setLength(99);
                        archivoE.writeChars(bf.toString());
                        ultimo = archivoE.getFilePointer();
                        archivoE.close();
                    } else
                        System.out.println("Esta regla no esta normalizada");
                }
                else
                    System.out.println("Esta regla no esta normalizada");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void leerB(long posicion) {
        String nom;
        char nombre[] = new char[99];
        try {
            if (posicion != -1) {
                archivoL = new RandomAccessFile("maestroB.gsh", "r");
                archivoL.seek(posicion);
                System.out.println("Llave: " + archivoL.readInt());
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivoL.readChar();
                nom = new String(nombre).replace('\0', ' ');
                System.out.println("Regla: " + nom);
            } else
                System.out.println("Error, Esa dirección no existe.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarTodo() {
        String nom;
        int valor;
        long apActual, apFinal;
        char nombre[] = new char[99];
        try {
            archivoL = new RandomAccessFile("maestroB.gsh", "r");
            while ((apActual = archivoL.getFilePointer()) != (apFinal = archivoL.length())) {
                valor = archivoL.readInt();
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivoL.readChar();
                nom = new String(nombre).replace('\0', ' ');
                if (valor != 0) {
                    System.out.println("Llave: " + valor);
                    System.out.println("Regla: " + nom);
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> obtenerReglas() {
        String nom;
        int valor;
        long apActual, apFinal;
        char nombre[] = new char[99];
        ArrayList<String> reglas = new ArrayList<>();
        try {
            archivoL = new RandomAccessFile("maestroB.gsh", "r");
            while ((apActual = archivoL.getFilePointer()) != (apFinal = archivoL.length())) {
                valor = archivoL.readInt();
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivoL.readChar();
                nom = new String(nombre).replace('\0', ' ');
                if (valor != 0) {
                    reglas.add(nom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return reglas;
        }
    }

    public void actualizar(long posicion, boolean eliminar) {
        Scanner s = new Scanner(System.in);
        StringBuffer bf;
        String nom;
        int llave;
        char nombre[] = new char[99];
        try {
            if (posicion != -1) {
                archivoL = new RandomAccessFile("maestroB.gsh", "rw");
                archivoL.seek(posicion);
                if (!eliminar) {
                    llave = archivoL.readInt();
                    System.out.println("Su llave es: " + llave);
                    for (int i = 0; i < nombre.length; i++)
                        nombre[i] = archivoL.readChar();
                    nom = new String(nombre).replace('\0', ' ');
                    System.out.println("Cambiar de " + nom.trim() + " a la regla (Normalizado): ");
                    nom = s.nextLine();
                    archivoL.seek(posicion);
                    archivoL.writeInt(llave);
                    bf = new StringBuffer(nom);
                    bf.setLength(99);
                    archivoL.writeChars(bf.toString());
                    archivoL.seek(posicion);
                } else {
                    archivoL.writeInt(0);
                    for (int i = 0; i < 99; i++)
                        archivoL.writeChar('0');
                }
            } else
                System.out.println("Error, Ese registro no existe.");
            archivoL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getUltimo() {
        return (int) (ultimo - 202);
    }
}
