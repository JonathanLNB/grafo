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
        for(int i = 0; i < aux; i++) {
            System.out.println("Ingresa la llave: ");
            llave = s.nextInt();
            s.nextLine();
            System.out.println("Ingresa la nombre: ");
            nom = s.nextLine();
            System.out.println("Ingresa los nodos: ");
            nodos = s.nextLine();
            maestro.escribirB(llave, nom, nodos);
            index.escribirArchivo(llave, i*(34+4*maestro.getDatos()));
            ultimo = i;
        }
        ultimo+=1;
        do{
            System.out.println("Ingresa la opción deseada");
            System.out.println("    1)Ingresar Nodo");
            System.out.println("    2)Leer Nodo");
            System.out.println("    3)Actualizar Nodo");
            System.out.println("    4)Eliminar Nodo");
            System.out.println("    5)Salir");
            aux = s.nextInt();
            switch (aux){
                case 1:
                    System.out.println("Ingresa la llave: ");
                    llave = s.nextInt();
                    s.nextLine();
                    System.out.println("Ingresa la nombre: ");
                    nom = s.nextLine();
                    System.out.println("Ingresa los nodos: ");
                    nodos = s.nextLine();
                    maestro.escribirB(aux, nom, nodos);
                    ultimo+=1;
                    break;
                case 2:
                    System.out.println("Ingresa la direccion logica: ");
                    llave = s.nextInt();
                    maestro.leerB(index.leerArchivoSecuencial(llave));
                    break;
                case 3:
                    System.out.println("Ingresa la direccion logica: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.eliminarArchivoSecuencial(llave), false);
                    index.escribirArchivo(llave, ultimo*(34+4*maestro.getDatos()));
                    ultimo++;
                    break;
                case 4:
                    System.out.println("Ingresa la direccion logica: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.eliminarArchivoSecuencial(llave), true);
                    break;
                default:
                    System.out.println("Bye");
                    break;
            }
        }while(aux!=5);
    }
}
