package Main;

import Main.Archivos.Indexado;
import Main.Archivos.Maestro;
import Main.Archivos.Justificacion;
import Main.TDA.Premisa;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulador {
    private Maestro maestro;
    private Indexado index;
    private Justificacion justificacion;
    private ArrayList<String> datos;
    private ArrayList<Premisa> conjuntoC;
    private ArrayList<Premisa> nuevosHechos;

    public void configuracion() {
        String nom, nodos;
        Scanner s = new Scanner(System.in);
        int llave, aux = 0;
        maestro = new Maestro(this);
        index = new Indexado();
        justificacion = new Justificacion();

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
                    System.out.println("Ingresa la justificacion: ");
                    nom = s.nextLine();
                    justificacion.escribirArchivo(llave, nom);
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
                    justificacion.actualizar(llave, false);
                    System.out.println("Se actualizo correctamente :3");
                    break;
                case 4:
                    System.out.println("Ingresa la llave a eliminar: ");
                    llave = s.nextInt();
                    maestro.actualizar(index.eliminarArchivoSecuencial(llave), true);
                    justificacion.actualizar(llave, true);
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
                    conjuntoC = new ArrayList<>();
                    nuevosHechos = new ArrayList<>();
                    agregarHechos();
                    break;
                default:
                    System.out.println("Gracias :3");
                    break;
            }
        } while (aux < 8);
    }

    public String getJustificacion(int llave){
        return justificacion.buscarTextoRegla(llave);
    }

    public void agregarHechos() {
        Scanner s = new Scanner(System.in);
        String aux;
        System.out.println("--------------------------------------------");
        do {
            System.out.println("Ingresa un hecho (Ingresa 0 para dejar de ingresar hechos): ");
            aux = s.nextLine();
            if (!aux.equalsIgnoreCase("0") && aux.trim().length() != 0)
                datos.add(aux);
        } while (!aux.equalsIgnoreCase("0"));
        equiparar();
    }

    public void equiparar() {
        ArrayList<Premisa> reglas = maestro.obtenerReglas();
        for (int e = 0; e < datos.size(); e++) {
            for (int i = 0; i < reglas.size(); i++) {
                if (reglas.get(i).getRegla().split("-")[0].trim().toLowerCase().contains(datos.get(e).trim().toLowerCase())) {
                    agregarConjuntoConflicto(reglas.get(i));
                }
            }
        }
        inferir();
    }

    public void agregarConjuntoConflicto(Premisa aux) {
        int cont = 0;
        boolean agregar = true;
        for (int i = 0; i < conjuntoC.size(); i++) {
            if (conjuntoC.get(i).getRegla().trim().toLowerCase().equalsIgnoreCase(aux.getRegla().trim()))
                agregar = false;
        }
        if (agregar) {
            conjuntoC.add(aux);
            for (int i = 0; i < datos.size(); i++) {
                if (aux.getRegla().split("-")[0].trim().toLowerCase().contains(datos.get(i).trim().toLowerCase()))
                    cont += 1;
            }
            if (cont == aux.getRegla().split("-")[0].split("\\^").length) {
                nuevosHechos.add(aux);
                datos.add(aux.getRegla().split("-")[1].trim());
            }
        }
    }

    public void inferir() {
        Scanner s = new Scanner(System.in);
        String aux;
        String aux2[];
        int opc, elegido;
        boolean usado = false;
        if (nuevosHechos.size() > 0) {
        	for (int i = nuevosHechos.size(); i > 0; i--) {
        		System.out.println("Regla: " + nuevosHechos.get(nuevosHechos.size()-i).getRegla());
        		System.out.println("Justificacion: Su bebida es una " + getJustificacion(nuevosHechos.get(nuevosHechos.size()-i).getLlave()));
        		System.out.println("--------------------------------------------");
			}
        } else {
            if (conjuntoC.size() == 0)
                System.out.println("Esa bebida no esta entre mi conocimiento :(");
            else {
                for (int i = 0; i < datos.size(); i++) {
                    for (int a = 0; a < conjuntoC.size(); a++) {
                        if (!conjuntoC.get(a).getRegla().trim().toLowerCase().contains(datos.get(i).trim().toLowerCase())) {
                            conjuntoC.remove(a);
                            a--;
                        }
                    }
                }
                while (conjuntoC.size() > 1) {
                    elegido = ((int) (Math.random() * conjuntoC.size()));
                    aux2 = conjuntoC.get(elegido).getRegla().split("-")[0].split("\\^");
                    for (int i = aux2.length - 1; i >= 0; i--) {
                        usado = true;
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
                                    for (int a = 0; a < conjuntoC.size(); a++) {
                                        if (!conjuntoC.get(a).getRegla().trim().toLowerCase().contains(aux2[i].trim().toLowerCase())) {
                                            conjuntoC.remove(a);
                                            a--;
                                        }
                                    }
                                    datos.add(aux2[i]);
                                    break;
                                default:
                                    conjuntoC.remove(elegido);
                                    break;
                            }
                        }
                    }
                }
                if (conjuntoC.size() == 0)
                    System.out.println("Esa bebida no esta entre mi conocimiento :(");
                else
                	System.out.println("Regla: " + conjuntoC.get(0).getRegla());
        			System.out.println("Justificacion: Su bebida es una " + getJustificacion(conjuntoC.get(0).getLlave()));
                System.out.println("--------------------------------------------");
            }
        }
    }
}

