package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.componentes.BaseDatos;
import modelo.componentes.Bug;
import modelo.componentes.Consulta;
import modelo.componentes.Ordenamientos;

public class Menu {
    private BaseDatos db;
    private Consulta consulta;
    private Scanner scanner;

    public Menu(BaseDatos db) {
        this.db = db;
        this.consulta = new Consulta(db);
        this.scanner = new Scanner(System.in);
    }
    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("*******************************");
            System.out.println("     ADMINISTRADOR DE BUGS     ");
            System.out.println("*******************************");
            System.out.println("1.- Ingresar bug");
            System.out.println("2.- Eliminara bug");
            System.out.println("3.- Buscar bug");
            System.out.println("4.- Buscar bug");
            System.out.println("5.- Listar bugs");
            System.out.println("6.- Cantidad de bugs");
            System.out.println("7.- Salir");
            System.out.println("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    ingresarBug();
                    break;
                case 2:
                    eliminarBug();
                    break;
                case 3:
                    modificarBug();
                    break;
                case 4:
                    menuBuscar();
                    break;
                case 5:
                    menuListar();
                    break;
                case 6:
                    menuCantidad();
                    break;
                case 7:
                    System.out.println("Byebye");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while(opcion != 7);
    }
            
    private void ingresarBug() {
        System.out.println("\nINGRESAR BUG");
        System.out.println("-> Título: ");
        String titulo = scanner.nextLine();

        System.out.println("-> Prioridad");
        System.out.println("1 Baja\n2 Media\n3 Alta");
        System.out.println("Ingresa el número: ");
        int prioridad = scanner.nextInt();

        System.out.println("-> Nivel de seguridad");
        System.out.println("1 Baja\n2 Media\n3 Alta");
        System.out.println("Ingresa el número: ");
        int nivelSeguridad = scanner.nextInt();
        
        System.out.println("-> Equipos");
        System.out.println("1 Ciberseguridad\n2 IT\n3 Diseño\n4 Desarrollador\n5 Tester\n6 NULL");
        System.out.println("Ingresa el número: ");
        int opEquipo = scanner.nextInt();
        String equipo;
        switch(opEquipo) {
            case 1 -> equipo = "Ciberseguridad";
            case 2 -> equipo = "IT";
            case 3 -> equipo = "Diseño";
            case 4 -> equipo = "Desarrollador";
            case 5 -> equipo = "Tester";
            default -> equipo = "NULL";
        }
        
        System.out.println("-> Categoría");
        System.out.println("1 Interfaz\n2 Lógica\n3 Rendimiento\n4 Seguridad");
        System.out.println("Ingresa el número: ");
        int opCategoria = scanner.nextInt();
        String categoria;
        switch(opCategoria) {
            case 1 -> categoria = "Interfaz";
            case 2 -> categoria = "Lógica";
            case 3 -> categoria = "Rendimiento";
            case 4 -> categoria = "Seguridad";
        }
        
        System.out.println("-> Estado");
        System.out.println("1 Registrado\n2 En desarrollo\n3 En revisión\n4 Concluido\n5 No se va a arreglar");
        int estado = scanner.nextInt();
        
        Bug nuevoBug = new Bug(titulo, prioridad, nivelSeguridad, equipo, categoria, estado, LocalDateTime.now());
        db.registrar(nuevoBug);
        System.out.println("Bug con ID: " + nuevoBug.getID());
    }
    
    private void eliminarBug() {
        System.out.println("\nELIMINAR BUG");
        System.out.println("Ingrese el ID del bug a eliminar: ");
        String id = scanner.nextLine();
        boolean eliminado = db.eliminar(id);
        if (eliminado)
            System.out.println("Bug eliminado");
        else
            System.out.println("No se encontro el bug");
    }
    
    private void modificarBug() {
        System.out.println("\nMODIFICAR BUG");
        System.out.println("Ingrese el ID del bug a modificar: ");
        String id = scanner.nextLine();
        Bug existente = consulta.buscarID(id);
        if (existente == null) {
            System.out.println("Bug no encontrado");
            return;
        }
        
        System.out.println("Nuevo titulo: ");
        String titulo = scanner.nextLine();
        
        System.out.println("Nueva prioridad");
        System.out.println("1 Baja\n2 Media\n3 Alta");
        System.out.println("Ingresa el número: ");
        int prioridad = scanner.nextInt();
        
        System.out.println("Nuevo nivel de seguridad");
        System.out.println("1 Baja\n2 Media\n3 Alta");
        System.out.println("Ingresa el número: ");
        int nivelSeguridad = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Nuevo equipo");
        System.out.println("1 Ciberseguridad\n2 IT\n3 Diseño\n4 Desarrollador\n5 Tester\n6 NULL");
        System.out.println("Ingresa el número: ");
        int opEquipo = scanner.nextInt();
        String equipo;
        switch(opEquipo) {
            case 1 -> equipo = "Ciberseguridad";
            case 2 -> equipo = "IT";
            case 3 -> equipo = "Diseño";
            case 4 -> equipo = "Desarrollador";
            case 5 -> equipo = "Tester";
            default -> equipo = "NULL";
        }
        
        System.out.println("Nueva categoria");
        System.out.println("1 Interfaz\n2 Lógica\n3 Rendimiento\n4 Seguridad");
        System.out.println("Ingresa el número: ");
        int opCategoria = scanner.nextInt();
        String categoria;
        switch(opCategoria) {
            case 1 -> categoria = "Interfaz";
            case 2 -> categoria = "Lógica";
            case 3 -> categoria = "Rendimiento";
            case 4 -> categoria = "Seguridad";
        }
        
        System.out.println("Nuevo estado");
        System.out.println("1 Registrado\n2 En desarrollo\n3 En revisión\n4 Concluido\n5 No se va a arreglar");
        int estado = scanner.nextInt();
        scanner.nextLine();
        
        db.eliminar(id);
        Bug bugModificado = new Bug(titulo, prioridad, nivelSeguridad, equipo, categoria, estado, LocalDateTime.now());
        boolean modificado = db.modificar(bugModificado);
        if (modificado)
            System.out.println("Bug modificado exitosamente.\nNuevo ID: " + bugModificado.getID());
        else
            System.out.println("No se pudo modificar");
    }
    
    private void menuBuscar() {
        int opcion = 0;
        do {
            System.out.println("\nBUSCAR BUG");
            System.out.println("1.- Titulo");
            System.out.println("2.- ID");
            System.out.println("3.- Categoria");
            System.out.println("4.- Equipo");
            System.out.println("5.- Nivel de seguridad");
            System.out.println("6.- Regresar");
            System.out.print("Elige una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese titulo a buscar: ");
                    String t = scanner.nextLine();
                    ArrayList<Bug> resT = consulta.buscarTitulo(t);
                    System.out.println("Coincidencias: " + resT.size());
                    for (Bug b : resT) 
                        System.out.println(b);
                    break;
                    case 2:
                        System.out.print("Ingrese ID a buscar: ");
                        String id = scanner.nextLine();
                        Bug bID = consulta.buscarID(id);
                        if (bID != null) {
                            System.out.println("Coincidencias: 1");
                            System.out.println(bID);
                        }
                        else
                            System.out.println("Coincidencias: 0");
                        break;
                        case 3:
                            System.out.println("Seleccione categoria");
                            System.out.println("1 Interfaz\n2 Lógica\n3 Rendimiento\n4 Seguridad");
                            int cOp = scanner.nextInt();
                            scanner.nextLine();
                            String cat = "Interfaz";
                            switch (cOp) {
                                case 1 -> cat = "Interfaz";
                                case 2 -> cat = "Lógica";
                                case 3 -> cat = "Rendimiento";
                                case 4 -> cat = "Seguridad";
                            }
                            ArrayList<Bug> resC = consulta.buscarCategoria(cat);
                    System.out.println("Coincidencias: " + resC.size());
                    for (Bug b : resC)
                        System.out.println(b);
                    break;
                case 4:
                    System.out.println("Seleccione equipo");
                    System.out.println("1 Ciberseguridad\n2 IT\n3 Diseño\n4 Desarrollador\n5 Tester\n6 NULL");
                    int eOp = scanner.nextInt();
                    scanner.nextLine();
                    String eq;
                    switch (eOp) {
                        case 1 -> eq = "Ciberseguridad";
                        case 2 -> eq = "IT";
                        case 3 -> eq = "Diseño";
                        case 4 -> eq = "Desarrollador";
                        case 5 -> eq = "Tester";
                    }
                    ArrayList<Bug> resE = consulta.buscarEquipo(eq);
                    System.out.println("Coincidencias: " + resE.size());
                    for (Bug b : resE)
                        System.out.println(b);
                    break;
                case 5:
                    System.out.println("Seleccione nivel de seguridad");
                    System.out.println("1 Baja\n2 Media\n3 Alta");
                    int niv = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Bug> resN = consulta.buscarNivelSeguridad(niv);
                    System.out.println("Coincidencias: " + resN.size());
                    for (Bug b : resN) System.out.println(b);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }

    private void menuListar() {
        int opcion = 0;
        do {
            System.out.println("\nLISTAR BUGS");
            System.out.println("1.- Titulo");
            System.out.println("2.- ID");
            System.out.println("3.- Prioridad");
            System.out.println("4.- Equipo encargado");
            System.out.println("5.- Nivel de seguridad");
            System.out.println("6.- Categoria");
            System.out.println("7.- Estado");
            System.out.println("8.- Fecha");
            System.out.println("9.- Regresar");
            System.out.print("Elige una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Bug> lista = db.obtenerTodos();
            switch (opcion) {
                case 1:
                    lista = Ordenamientos.ordenarTitulo(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 2:
                    lista = Ordenamientos.ordenarID(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 3:
                    lista = Ordenamientos.ordenarPrioridad(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 4:
                    lista = Ordenamientos.ordenarEquipo(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 5:
                    lista = Ordenamientos.ordenarNivelSeguridad(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 6:
                    lista = Ordenamientos.ordenarCategoria(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 7:
                    lista = Ordenamientos.ordenarEstado(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 8:
                    lista = Ordenamientos.ordenarFecha(lista);
                    for (Bug b : lista)
                        System.out.println(b);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 10);
    }

    private void menuCantidad() {
        System.out.println("Cantidad de bugs: " + db.getTamano());
    }
}