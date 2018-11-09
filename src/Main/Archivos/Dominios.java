package Main.Archivos;

import Main.TDA.Dominio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Dominios {
    private RandomAccessFile archivo;
    private long ultimo = 0;

    public void escribirB(String llave, String nombre) {
        StringBuffer bf;
        try {
            archivo= new RandomAccessFile("dominios.gsh", "rw");
            ultimo = archivo.length();
            archivo.seek(ultimo);
            bf = new StringBuffer(nombre);
            bf.setLength(3);
            archivo.writeChars(bf.toString());
            bf = new StringBuffer(nombre);
            bf.setLength(200);
            archivo.writeChars(bf.toString());
            ultimo = archivo.getFilePointer();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Dominio leerB(long posicion) {
        Dominio d = null;
        String aux, aux1;
        char llave[] = new char[3];
        char nombre[] = new char[200];
        try {
            if (posicion != -1) {
                archivo = new RandomAccessFile("dominios.gsh", "r");
                archivo.seek(posicion);
                for (int i = 0; i < llave.length; i++)
                    llave[i] = archivo.readChar();
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivo.readChar();
                aux1 = new String(llave).replace('\0', ' ');
                aux = new String(nombre).replace('\0', ' ');
                d = new Dominio(aux1, aux.split(","));
                System.out.println("Regla: " + aux);
            } else
                System.out.println("Error, Esa dirección no existe.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return d;
        }
    }
    public ArrayList<Dominio> mostrarTodo() {
        String aux, aux1;
        long apActual, apFinal;
        char llave [] = new char[3];
        char nombre[] = new char[200];
        ArrayList <Dominio> dominios = new ArrayList<>();
        try {
            archivo = new RandomAccessFile("dominios.gsh", "r");
            while ((apActual = archivo.getFilePointer()) != (apFinal = archivo.length())) {
                for(int i = 0; i < llave.length; i++)
                    llave[i] = archivo.readChar();
                for (int i = 0; i < nombre.length; i++)
                    nombre[i] = archivo.readChar();
                aux = new String(nombre).replace('\0', ' ');
                aux1 = new String(llave).replace('\0', ' ');
                if (!llave.equals("0")) {
                    System.out.println("Llave: " + aux1);
                    System.out.println("Elementos: " + aux);
                    System.out.println("---------------------------------------------------");
                    dominios.add(new Dominio(aux1, aux.split(",")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return dominios;
        }
    }

    public void actualizar(Dominio d, long posicion, boolean eliminar) {
        Scanner s = new Scanner(System.in);
        StringBuffer bf;
        String aux = "";
        try {
            if (posicion != -1) {
                archivo = new RandomAccessFile("dominios.gsh", "rw");
                archivo.seek(posicion);
                if (!eliminar) {
                    bf = new StringBuffer(d.getLlave());
                    bf.setLength(3);
                    archivo.writeChars(bf.toString());
                    for (int i = 0; i< d.getElementos().length; i++) {
                        if(d.getElementos().length -1 == i)
                            aux += d.getElementos()[i]+",";
                        else
                            aux += d.getElementos()[i];
                    }
                    bf = new StringBuffer(aux);
                    bf.setLength(200);
                    archivo.writeChars(bf.toString());
                    archivo.seek(posicion);
                } else {
                    for (int i = 0; i < 3; i++)
                        archivo.writeChar('0');
                    for (int i = 0; i < 200; i++)
                        archivo.writeChar('0');
                }
            } else
                System.out.println("Error, Ese registro no existe.");
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getUltimo() {
        return (int) (ultimo - 202);
    }
}
