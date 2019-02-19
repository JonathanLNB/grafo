package Main.Archivos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Indexado {
    private RandomAccessFile leer;
    private long ultima = 0;

    public Indexado() {

    }

    public void escribirArchivo(int llave, long pos) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("indexB.gsh", "rw");
            ultima = archivo.length();
            archivo.seek(ultima);
            archivo.writeInt(llave);
            archivo.writeLong(pos);
            ultima = archivo.getFilePointer();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long leerArchivoSecuencial(int llave) {
        int valor;
        long apActual, apFinal, salida = -1;
        try {

            leer = new RandomAccessFile("indexB.gsh", "r");
            while ((apActual = leer.getFilePointer()) != (apFinal = leer.length())) {
                valor = leer.readInt();
                if (valor == llave) {
                    salida = leer.readLong();
                    break;
                }
                leer.readLong();
            }
            leer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return salida;
        }
    }

    public long eliminarArchivoSecuencial(int llave) {
        int valor;
        long apActual, apFinal, salida = -1;
        try {
            leer = new RandomAccessFile("indexB.gsh", "rw");
            while ((apActual = leer.getFilePointer()) != (apFinal = leer.length())) {
                valor = leer.readInt();
                if (valor == llave) {
                    salida = leer.readLong();
                    leer.seek(apActual);
                    leer.writeInt(0);
                    leer.writeLong(0);
                    break;
                }
                leer.readLong();
            }
            leer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return salida;
        }
    }

    public void mostrarTodo() {
        int auxl;
        long apActual, apFinal, auxd;
        try {
            leer = new RandomAccessFile("indexB.gsh", "r");
            while ((apActual = leer.getFilePointer()) != (apFinal = leer.length())) {
                auxl = leer.readInt();
                auxd = leer.readLong();
                if (auxl != 0) {
                    if (auxl > 0 && (auxd / 202 + 1) != 0)
                        System.out.println("Llave: " + auxl + " - Dirección: " + (auxd / 202 + 1));
                }
            }
            leer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
