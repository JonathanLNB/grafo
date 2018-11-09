package Main.Archivos;

import java.util.Scanner;

public class Simulador {
    public void configuracion(){
        String nom, nodos;
        Scanner s = new Scanner(System.in);
        int llave, aux = 0;
        Maestro maestro = new Maestro();
        Indexado index = new Indexado();
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
                    System.out.println("Ingresa la llave: ");
                    llave = s.nextInt();
                    s.nextLine();
                    System.out.println("Ingresa la regla (Normalizada): ");
                    nom = s.nextLine();
                    maestro.escribirB(llave, nom);
                    index.escribirArchivo(llave, maestro.getUltimo());
                    break;
                case 2:
                    System.out.println("Ingresa la llave a buscar: ");
                    llave = s.nextInt();
                    maestro.leerB(index.leerArchivoSecuencial(llave));
                    break;
                case 3:
                    System.out.println("Ingresa la llave a actualizar: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.leerArchivoSecuencial(llave), false);
                    System.out.println("Se actualizo correctamente :3");
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
        } while (aux < 7);
    }
}
