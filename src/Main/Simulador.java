package Main;

import Main.Archivos.Indexado;
import Main.Archivos.Maestro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Simulador {
    private Maestro maestro;
    private Indexado index;
    private ArrayList<String> datos;
    private ArrayList<String> consuntoC;
    private ArrayList<String> nuevosHechos;

    public void configuracion() {
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
                    datos = new ArrayList<>();
                    consuntoC = new ArrayList<>();
                    nuevosHechos = new ArrayList<>();
                    agregarHechos();
                    break;
                default:
                    System.out.println("Gracias :3");
                    break;
            }
        } while (aux < 8);
    }

    public void agregarHechos(){
        Scanner s = new Scanner(System.in);
        String aux;
        System.out.println("--------------------------------------------");
        do {
            System.out.println("Ingresa un hecho (Ingresa 0 para dejar de ingresar hechos): ");
            aux = s.nextLine();
            if (!aux.equalsIgnoreCase("0") && aux.trim().length()!=0)
                datos.add(aux);
        } while (!aux.equalsIgnoreCase("0"));
        equiparar();
    }

    public void equiparar(){
        ArrayList<String> reglas = maestro.obtenerReglas();
        for (int e = 0; e < datos.size(); e++) {
            for (int i = 0; i < reglas.size(); i++) {
                if (reglas.get(i).split("-")[0].trim().toLowerCase().contains(datos.get(e).trim().toLowerCase())) {
                    agregarConjuntoConflicto(reglas.get(i).trim());
                }
            }
        }
        inferir();
    }

    public void agregarConjuntoConflicto(String aux){
        boolean agregar = true;
        for(int i = 0; i < consuntoC.size(); i++){
            if(consuntoC.get(i).trim().toLowerCase().equalsIgnoreCase(aux))
               agregar = false;
        }
        if(agregar)
            consuntoC.add(aux);
        agregar = true;
        for(int i = 0; i < datos.size(); i++) {
            if (!aux.split("-")[0].trim().toLowerCase().contains(datos.get(i).trim().toLowerCase()))
                agregar = false;
        }
        if(agregar) {
            nuevosHechos.add(aux.split("-")[1].trim());
            datos.add(aux.split("-")[1].trim());
        }
    }

    public void inferir(){
        if(nuevosHechos.size()>0)
            System.out.println("Subebida es una "+nuevosHechos.get(0));
        else
            System.out.println("Esa bebida no esta entre mi conocimiento :(");
        System.out.println("--------------------------------------------");
    }
/*
    public void equiparar() {
        maestro = new Maestro();
        Scanner s = new Scanner(System.in);
        ArrayList<String> reglas = maestro.obtenerReglas();
        ArrayList<String> reglasAux = maestro.obtenerReglas();
        ArrayList<String> datos = new ArrayList<>();
        String aux;
        String aux2[];
        int opc, elegido;
        boolean usado = false, no = false;
        do {
            System.out.println("--------------------------------------------");
            do {
                System.out.println("Ingresa un hecho (Ingresa 0 para dejar de ingresar hechos): ");
                aux = s.nextLine();
                if (!aux.equalsIgnoreCase("0"))
                    datos.add(aux);
            } while (!aux.equalsIgnoreCase("0"));
            for (int e = 0; e < datos.size(); e++) {
                for (int i = 0; i < reglasAux.size(); i++) {
                    if (!reglasAux.get(i).trim().toLowerCase().contains(datos.get(e).trim().toLowerCase())) {
                        reglasAux.remove(i);
                        i--;
                    }
                }
            }

            if (reglasAux.size() > 1) {
                elegido = ((int) (Math.random() * reglasAux.size()));
                aux2 = reglas.get(elegido).split("-")[0].split("\\^");
                for (int i = aux2.length - 1; i >= 0; i--) {
                    usado = true;
                    no = false;
                    for (int e = 0; e < datos.size(); e++) {
                        if (datos.get(e).trim().toLowerCase().equalsIgnoreCase(aux2[i].trim().toLowerCase())) {
                            usado = false;
                        }
                    }
                    if (usado) {
                        System.out.println("¿Su bebida tiene " + aux2[i] + "?");
                        System.out.println("    1) Si");
                        System.out.println("    2) No");
                        opc = s.nextInt();
                        switch (opc) {
                            case 1:
                                for (int a = 0; a < reglasAux.size(); a++) {
                                    if (!reglasAux.get(a).trim().toLowerCase().contains(aux2[i].trim().toLowerCase())){
                                        reglasAux.remove(a);
                                        a--;
                                    }
                                }
                                datos.add(aux2[i]);
                                break;
                            default:
                                reglasAux.remove(elegido);
                                no = true;
                                break;
                        }
                    }
                    if (no) {
                        s.nextLine();
                        break;
                    }
                }
            }
        } while (reglasAux.size() > 1);
        if (reglasAux.size() == 0) {
            System.out.println("Esa bebida no esta entre mi conocimiento :(");

        } else {
            System.out.println("Esa bebida es: " + reglas.get(0).split("-")[1]);
            datos.add(reglas.get(0).split("-")[1]);
            inferir(datos);
        }
        System.out.println("--------------------------------------------");
    }

    public void inferir(ArrayList<String> hechos) {

    }
    */
}
