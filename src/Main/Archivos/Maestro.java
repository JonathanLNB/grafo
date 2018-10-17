package Main.Archivos;

import java.io.*;
import java.util.Scanner;

public class Maestro {
    private RandomAccessFile archivoL;
    private RandomAccessFile archivoE;
    private long lreg = 0, ultimo = 0;
    private int matriz[][], datos;

    public Maestro() {
        /*this.datos = (int) ((int) datos + datos * .4);
        matriz = new int[this.datos][this.datos];*/
    }

    public void escribirB(int llave, String nombre) {
        StringBuffer bf;
        int cont = 0;
        try {
            if (!nombre.contains("∨") || !nombre.contains("v")) {
                archivoE = new RandomAccessFile("maestroB.gsh", "rw");
                ultimo = archivoE.length();
                archivoE.seek(ultimo);
                archivoE.writeInt(llave);
                bf = new StringBuffer(nombre);
                bf.setLength(99);
                archivoE.writeChars(bf.toString());
                ultimo = archivoE.getFilePointer();
                archivoE.close();
            }
            else
                System.out.println("Esta regla no esta normalizada");
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
        long apActual, apFinal, salida = -1;
        char nombre[] = new char[99];
        try {
            archivoL = new RandomAccessFile("maestroB.gsh", "r");
            while ((apActual = archivoL.getFilePointer()) != (apFinal = archivoL.length())) {
                valor =archivoL.readInt();
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivoL.readChar();
                nom = new String(nombre).replace('\0', ' ');
                if(valor != 0) {
                    System.out.println("Llave: " + valor);
                    System.out.println("Regla: " + nom);
                    System.out.println("---------------------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(long posicion, boolean eliminar) {
        Scanner s = new Scanner(System.in);
        StringBuffer bf;
        String nom, nodos;
        int llave;
        long apActual, apFinal;
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
                    System.out.println("Cambiar de " + nom.trim() + " a la regla: ");
                    nom = s.nextLine();
                    archivoL.seek(posicion);
                    archivoL.writeInt(llave);
                    bf = new StringBuffer(nom);
                    bf.setLength(99);
                    archivoL.writeChars(bf.toString());
                    //escribirB(llave, nom);
                    archivoL.seek(posicion);
                }
                else {
                    archivoL.writeInt(0);
                    for (int i = 0; i < 99; i++)
                        archivoL.writeChar('0');
                    /*for (int i = 0; i < datos; i++)
                        archivoL.writeInt(0);
                    archivoL.seek(0);
                    while ((apActual = archivoL.getFilePointer()) != (apFinal = archivoL.length())) {
                        archivoL.readInt();
                        for (int e = 0; e < 99; e++)
                            archivoL.readChar();
                        for (int i = 0; i < datos; i++)
                            if (i == posicion / (34 + 4 * datos))
                                archivoL.writeInt(0);
                            else
                                archivoL.readInt();
                    }*/
                }
            } else
                System.out.println("Error, Ese registro no existe.");
            archivoL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDatos() {
        return datos;
    }
    public int getUltimo(){
        return (int) (ultimo-202);
    }
}
