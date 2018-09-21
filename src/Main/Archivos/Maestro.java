package Main.Archivos;

import java.io.*;
import java.util.Scanner;

public class Maestro {
    private RandomAccessFile archivoL;
    private RandomAccessFile archivoE;
    private long lreg = 0, ultimo = 0;
    private int matriz[][], datos;

    public Maestro(int datos) {
        this.datos = (int) ((int) datos + datos * .4);
        matriz = new int[datos][datos];
    }

    public void escribirB(int llave, String nombre, String nodos) {
        StringBuffer bf;
        String nodes[];
        int cont = 0;
        nodes = nodos.split(",");
        try {
            archivoE = new RandomAccessFile("maestroB.gsh", "rw");
            archivoE.seek(ultimo);
            archivoE.writeInt(llave);
            bf = new StringBuffer(nombre);
            bf.setLength(15);
            archivoE.writeChars(bf.toString());
            for (int i = 0; i < datos; i++) {
                if (cont < nodes.length) {
                    if (i == Integer.parseInt(nodes[cont].split(" ")[0]) - 1) {
                        archivoE.writeInt(Integer.parseInt(nodes[cont].split(" ")[1]));
                        cont++;
                    } else
                        archivoE.writeInt(0);
                } else
                    archivoE.writeInt(0);
            }
            ultimo = archivoE.getFilePointer();
            archivoE.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void leerB(long posicion) {
        String nom;
        char nombre[] = new char[15];
        try {
            if (posicion != -1) {
                archivoL = new RandomAccessFile("maestroB.gsh", "r");
                archivoL.seek(posicion);
                System.out.println("Llave: " + archivoL.readInt());
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivoL.readChar();
                nom = new String(nombre).replace('\0', ' ');
                System.out.println("Nombre: " + nom);
                System.out.println("Nodos: ");
                for (int i = 0; i < datos; i++)
                    System.out.println((i + 1) + ") " + archivoL.readInt());
            } else
                System.out.println("Error, Esa dirección no existe.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(long posicion, boolean eliminar) {
        Scanner s = new Scanner(System.in);
        String nom, nodos;
        int llave;
        long apActual, apFinal;
        char nombre[] = new char[15];
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
                    System.out.println("Cambiar de " + nom + " al nombre: ");
                    nom = s.nextLine();
                    System.out.println("Ingresa los nodos: ");
                    nodos = s.nextLine();
                    escribirB(llave, nom, nodos);
                    archivoL.seek(posicion);
                }
                archivoL.writeInt(0);
                for (int i = 0; i < 15; i++)
                    archivoL.writeChar('0');
                for (int i = 0; i < datos; i++)
                    archivoL.writeInt(0);
                archivoL.seek(0);
                while ((apActual = archivoL.getFilePointer()) != (apFinal = archivoL.length())) {
                    archivoL.readInt();
                    for (int e = 0; e < 15; e++)
                        archivoL.readChar();
                    for (int i = 0; i < datos; i++)
                        if (i == posicion / (34 + 4 * datos))
                            archivoL.writeInt(0);
                        else
                            archivoL.readInt();
                }
            } else
                System.out.println("Error, Esa dirección no existe.");
            archivoL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDatos() {
        return datos;
    }
}
