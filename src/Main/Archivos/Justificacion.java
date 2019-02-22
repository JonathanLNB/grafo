package Main.Archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

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
    public String buscarTextoRegla(int llave) {
        String[] parts;
        String cadena, aux = null;
        try {
            FileReader f = new FileReader("justificacion.txt");
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                parts = cadena.split(";");
                if (parts != null) {
                    if (parts[0].equals("" + llave)) {
                        aux = parts[1];
                        break;
                    }
                } else
                    aux = "No tiene justificación";
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println( aux);
        return aux;
    }

    public void actualizar(int llave, boolean eliminar) {
        Scanner s = new Scanner(System.in);
        StringBuffer bf;
        RandomAccessFile archivoL;
        String aux, op;
        int cont = 0;
        try {
            archivoL = new RandomAccessFile("justificacion.txt", "rw");
            while ((aux = archivoL.readLine()) != null) {
                cont++;
                if (aux.split(";")[0].equals("" + llave))
                    break;
            }
            archivoL = new RandomAccessFile("justificacion.txt", "rw");
            for (int i = 0; i < cont - 1; i++)
                archivoL.readLine();
            if (eliminar) {
                for (int e = 0; e < aux.length(); e++)
                    archivoL.writeBytes(" ");
                archivoL.writeBytes("\n");
            }
            else{
                System.out.println("Usted cambiara la justificación de la regla: " + aux.split(";")[0]);
                System.out.println(aux.split(";")[1]);
                System.out.println("¿Desea cambiar la justificación? \n     1) Si\n     2) No");
                op = s.nextLine();
                if (op.equals("1")) {
                    System.out.println("Ingresa la justificación: ");
                    op = s.nextLine();
                    archivoL.writeBytes(llave + ";" + op + "\n");
                }
            }
            archivoL.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
