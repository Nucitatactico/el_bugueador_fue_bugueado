package modelo.componentes;

import java.util.ArrayList;
import modelo.componentes.Bug;
import modelo.componentes.BaseDatos;
import modelo.componentes.Ordenamientos;

public class Consulta
{
    BaseDatos db;

    public Consulta(BaseDatos db)
    {
        this.db = db;
    }

    public ArrayList<Bug> buscarTitulo(String titulo)
    {
        ArrayList<Bug> lista = db.obtenerTodos();
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(titulo, lista, Bug -> Bug.titulo);
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

/*  Revisar hash de base de datos
    public Bug buscarID(String ID)
    {

    }
*/

    public ArrayList<Bug> buscarCategoria(String categoria)
    {
        ArrayList<Bug> lista = db.obtenerTodos();
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(categoria, lista, Bug -> Bug.categoria);
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

    public ArrayList<Bug> buscarEquipo(String equipo)
    {
        ArrayList<Bug> lista = db.obtenerTodos();
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(equipo, lista, Bug -> Bug.equipo);
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

    public ArrayList<Bug> buscarNivelSeguridad(int nivelSeguridad)
    {
        ArrayList<Bug> lista = db.obtenerTodos();
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(nivelSeguridad, lista, Bug -> Bug.nivelSeguridad);
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;
    }


    interface cadenasBug
    {
        String obtener(Bug bug);
    }

    interface enterosBug
    {
        int obtener(Bug bug);
    }


    private int busquedaBinaria(String buscado, ArrayList<Bug> lista, cadenasBug x)
    {
        int izq = 0;
        int der = lista.size() - 1;
        int medio;

        while(izq <= der)
        {
            medio = (der + izq) / 2;

            if(buscado.compareToIgnoreCase( x.obtener(lista.get(medio)) ) == 0)
                return medio;
            else if(buscado.compareToIgnoreCase(x.obtener(lista.get(medio))) < 0 )
                der = medio - 1;
            else
                izq = medio + 1;
        }

        return -1;
    }

    private int busquedaBinaria(int buscado, ArrayList<Bug> lista, enterosBug x)
    {
        int izq = 0;
        int der = lista.size() - 1;
        int medio;

        while(izq <= der)
        {
            medio = (der + izq) / 2;

            if(x.obtener(lista.get(medio)) == buscado)
                return medio;
            else if(buscado < x.obtener(lista.get(medio)))
                der = medio - 1;
            else
                izq = medio + 1;
        }

        return -1;
    }
}