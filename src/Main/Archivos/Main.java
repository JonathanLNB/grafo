package Main.Archivos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String nom, nodos;
        Scanner s = new Scanner(System.in);
        int llave, ultimo = 0, aux = 0;
        Maestro maestro = new Maestro();
        Indexado index = new Indexado();
        /*for (int i = 0; i < aux; i++) {
            System.out.println("Ingresa la llave: ");
            llave = s.nextInt();
            s.nextLine();
            System.out.println("Ingresa la Regla: ");
            nom = s.nextLine();
            maestro.escribirB(llave, nom);
            index.escribirArchivo(llave, i * (34 + 4 * maestro.getDatos()));
            ultimo = i;
        }
        ultimo += 1;*/
        do {
            System.out.println("Ingresa la opción deseada");
            System.out.println("    1)Ingresar Regla");
            System.out.println("    2)Leer Regla");
            System.out.println("    3)Actualizar Regla");
            System.out.println("    4)Eliminar Regla");
            System.out.println("    5)Mostrar Maestro");
            System.out.println("    6)Mostrar Indice");
            System.out.println("    7)Salir");
            aux = s.nextInt();
            switch (aux) {
                case 1:
                    //if (ultimo < maestro.getDatos()) {
                        System.out.println("Ingresa la llave: ");
                        llave = s.nextInt();
                        s.nextLine();
                        System.out.println("Ingresa la regla: ");
                        nom = s.nextLine();
                        maestro.escribirB(llave, nom);
                        index.escribirArchivo(llave, maestro.getUltimo());
                        ultimo += 1;
                    /*} else
                        System.out.println("El archivo maestro necesita mantenimiento :(");*/
                    break;
                case 2:
                    System.out.println("Ingresa la llave a buscar: ");
                    llave = s.nextInt();
                    maestro.leerB(index.leerArchivoSecuencial(llave));
                    break;
                case 3:
                    //if (ultimo < maestro.getDatos()) {
                        System.out.println("Ingresa la llave a actualizar: ");
                        llave = s.nextInt();
                        maestro.actualizar(index.leerArchivoSecuencial(llave), false);
                        System.out.println("Se actualizo correctamente :3");
                    /*} else
                        System.out.println("El archivo maestro necesita mantenimiento :(");*/
                    break;
                case 4:
                    System.out.println("Ingresa la llave a eliminar: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.eliminarArchivoSecuencial(llave), true);
                    System.out.println("Se elimino correctamente :3");
                    break;
                case 5:
                    maestro.mostrarTodo();
                    break;
                case 6:
                    index.mostrarTodo();
                    break;
                default:
                    System.out.println("Gracias :3");
                    break;
            }
        } while (aux != 7);
    }
}
