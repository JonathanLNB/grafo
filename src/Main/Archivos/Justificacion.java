package Main.Archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Justificacion {
    public Justificacion() {

    }

    public void escribirArchivo(int llave, String justificacion) {
        try {
            RandomAccessFile archivo = new RandomAccessFile("justificacion.txt", "rw");
            archivo.seek(archivo.length());
            archivo.writeBytes(llave + ";" + justificacion + "\n");
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Método BÚSQUEDA de texto de regla mediante el número de llave
    //Agregar RUTA completa de la ubicación del archivo ya creado para que pueda ser leido
    public String buscarTextoRegla() {
        int llave = 1;
        String[] parts;
        String cadena,aux=null;
        try {
            FileReader f = new FileReader("justificacion.txt");
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                parts = cadena.split(";");
                if (Integer.parseInt(parts[0]) == llave) {
                    aux=parts[1];
                    break;
                }
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println( aux);
        return aux;
    }
}
