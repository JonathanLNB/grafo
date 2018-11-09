package Main;

import Main.Archivos.Indexado;
import Main.Archivos.Maestro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simulador {
    private Maestro maestro;
    private Indexado index;
    public void configuracion(){
        String nom, nodos;
        Scanner s = new Scanner(System.in);
        int llave, aux = 0;
        maestro = new Maestro();
        index = new Indexado();
        do {
            System.out.println("Ingresa la opción deseada");
            System.out.println("    1)Ingresar Regla");
            System.out.println("    2)Leer Regla");
            System.out.println("    3)Actualizar Regla");
            System.out.println("    4)Eliminar Regla");
            System.out.println("    5)Mostrar Maestro");
            System.out.println("    6)Mostrar Indice");
            System.out.println("    7)Sistema Experto");
            System.out.println("    8)Salir");
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
                case 7:
                    equiparar();
                    break;
                default:
                    System.out.println("Gracias :3");
                    break;
            }
        } while (aux < 8);
    }
    public void equiparar(){
        maestro = new Maestro();
        Scanner s = new Scanner(System.in);
        ArrayList<String> reglas = maestro.obtenerReglas();
        ArrayList<String> datos = new ArrayList<>();
        String aux;
        String aux2[];
        int opc, elegido;
        boolean usado = false, no = false;
        while(reglas.size()>1){
            System.out.println("--------------------------------------------");
            System.out.println("Ingresa un hecho: ");
            aux = s.nextLine();
            for(int i = 0; i < reglas.size(); i++){
                if(!reglas.get(i).trim().toLowerCase().contains(aux.trim().toLowerCase())){
                    reglas.remove(i);
                    i--;
                }
            }
            datos.add(aux);
            if(reglas.size()>1){
                elegido = ((int)(Math.random()*reglas.size()));
                aux2 = reglas.get(elegido).split("-")[0].split("\\^");
                for(int i = aux2.length-1; i > 0; i --){
                    usado = true;
                    no = false;
                    for(int e = 0; e < datos.size(); e++){
                        if(datos.get(e).trim().toLowerCase().equalsIgnoreCase(aux2[i].trim().toLowerCase())){
                            usado = false;
                        }
                    }
                    if(usado){
                        System.out.println("¿Su bebida tiene "+aux2[i]+"?");
                        System.out.println("    1) Si");
                        System.out.println("    2) No");
                        opc = s.nextInt();
                        switch (opc){
                            case 1:
                                for(int a = 0; a < reglas.size(); a++){
                                    if(!reglas.get(a).trim().toLowerCase().contains(aux2[i].trim().toLowerCase())){
                                        reglas.remove(a);
                                        a--;
                                    }
                                }
                                datos.add(aux2[i]);
                                break;
                            default:
                                reglas.remove(elegido);
                                no = true;
                                break;
                        }
                    }
                    if(no) {
                        s.nextLine();
                        break;
                    }
                }
            }
        }
        if(reglas.size() == 0)
            System.out.println("Esa bebida no esta entre mi conocimiento :(");
        else
            System.out.println("Esa bebida es: "+reglas.get(0).split("-")[1]);
    }
}
