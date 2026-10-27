package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.componentes.Bug;
import modelo.componentes.Ordenamientos;

public class Prueba
{
    public static void main(String[] args)
    {
        ArrayList<Bug> lista = new ArrayList<Bug>();
        lista.add(new Bug("1.no se", "Baja", 3, "Ciberseguridad", "Redes", 2, LocalDateTime.of(2012,8,22,10,12)));
        lista.add(new Bug("Abecedario", "Alta", 1, "Lexica", "sintaxis", 0, LocalDateTime.of(2008,9,22,10,12)));
        lista.add(new Bug("2.Explota", "Media", 3, "IT", "hard", 1, LocalDateTime.of(2008,8,12,10,12)));
        lista.add(new Bug("18.no se", "Media", 2, "Ciberseguridad", "hard", 3, LocalDateTime.of(2015,3,20,10,12)));
        lista.add(new Bug("No se", "Baja", 1, "Diseño", "Redes", 2, LocalDateTime.of(2015,7,12,10,12)));
        lista.add(new Bug("A la no", "Alta", 2, "Lexica", "Sintaxis", 0, LocalDateTime.of(2012,8,21,10,12)));
        lista.add(new Bug("o", "Alta", 2, "Ciberseguridad", "n", 0, LocalDateTime.of(2012,8,21,10,12)));
    
    System.out.println("\n\tOrednar Fecha");
        for(Bug bug:Ordenamientos.ordenarFecha(lista))
            System.out.println(bug.toString());
    
    System.out.println("\n\tOrednar Equipo");
        for(Bug bug:Ordenamientos.ordenarEquipo(lista))
            System.out.println(bug.toString());
    
    System.out.println("\n\tOrednar Categoria");
        for(Bug bug:Ordenamientos.ordenarCategoria(lista))
            System.out.println(bug.toString());
    
    System.out.println("\n\tOrednar nivel seguridad");
        for(Bug bug:Ordenamientos.ordenarNivelSeguridad(lista))
            System.out.println(bug.toString());
            
    }
}