package Main.Archivos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String nom, nodos;
        Scanner s = new Scanner(System.in);
        System.out.println("Ingresa la cantidad de nodos: ");
        int llave, ultimo = 0, aux = s.nextInt();
        Maestro maestro = new Maestro(aux);
        Indexado index = new Indexado();
        for (int i = 0; i < aux; i++) {
            System.out.println("Ingresa la llave: ");
            llave = s.nextInt();
            s.nextLine();
            System.out.println("Ingresa la nombre: ");
            nom = s.nextLine();
            System.out.println("Ingresa los nodos: ");
            nodos = s.nextLine();
            maestro.escribirB(llave, nom, nodos);
            index.escribirArchivo(llave, i * (34 + 4 * maestro.getDatos()));
            ultimo = i;
        }
        ultimo += 1;
        do {
            System.out.println("Ingresa la opción deseada");
            System.out.println("    1)Ingresar Nodo");
            System.out.println("    2)Leer Nodo");
            System.out.println("    3)Actualizar Nodo");
            System.out.println("    4)Eliminar Nodo");
            System.out.println("    5)Salir");
            aux = s.nextInt();
            switch (aux) {
                case 1:
                    if (ultimo < maestro.getDatos()) {
                        System.out.println("Ingresa la llave: ");
                        llave = s.nextInt();
                        s.nextLine();
                        System.out.println("Ingresa la nombre: ");
                        nom = s.nextLine();
                        System.out.println("Ingresa los nodos: ");
                        nodos = s.nextLine();
                        maestro.escribirB(aux, nom, nodos);
                        ultimo += 1;
                    } else
                        System.out.println("El archivo maestro necesita mantenimiento :(");
                    break;
                case 2:
                    System.out.println("Ingresa la llave a buscar: ");
                    llave = s.nextInt();
                    maestro.leerB(index.leerArchivoSecuencial(llave));
                    break;
                case 3:
                    if (ultimo < maestro.getDatos()) {
                        System.out.println("Ingresa la llave a actualizar: ");
                        llave = s.nextInt();
                        maestro.actualizar(index.eliminarArchivoSecuencial(llave), false);
                        index.escribirArchivo(llave, ultimo * (34 + 4 * maestro.getDatos()));
                        ultimo++;
                        System.out.println("Se actualizo correctamente :3");
                    } else
                        System.out.println("El archivo maestro necesita mantenimiento :(");
                    break;
                case 4:
                    System.out.println("Ingresa la llave a eliminar: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.eliminarArchivoSecuencial(llave), true);
                    System.out.println("Se elimino correctamente :3");
                    break;
                default:
                    System.out.println("Bye");
                    break;
            }
        } while (aux != 5);
    }
}
