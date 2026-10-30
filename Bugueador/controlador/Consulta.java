package controlador;

import java.util.ArrayList;
import modelo.Bug;
import modelo.BaseDatos;
import controlador.Ordenamientos;

public class Consulta
{
    BaseDatos db;

    public Consulta(BaseDatos db)
    {
        this.db = db;
    }

    public ArrayList<Bug> busquedaBug(Bug bug)
    {
        ArrayList<Bug> encontrados = db.obtenerIndice(db.indice(bug.getID()));

        for(int i = 0; i < encontrados.size(); i++)
            if(!encontrados.get(i).equals(bug))
                encontrados.remove(i);

        return encontrados;
    }

    public ArrayList<Bug> buscarTitulo(String titulo)
    {
        ArrayList<Bug> lista = Ordenamientos.ordenarTitulo(db.obtenerTodos());
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(titulo, lista, Bug -> Bug.getTitulo());
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

    public ArrayList<Bug> buscarID(String ID)
    {
        ArrayList<Bug> encontrados = db.obtenerIndice(db.indice(ID));

        for(int i = 0; i < encontrados.size(); i++)
            if(encontrados.get(i)getID() != ID)
                encontrados.remove(i);

        return encontrados;
    }

    public ArrayList<Bug> buscarCategoria(String categoria)
    {
        ArrayList<Bug> lista = Ordenamientos.ordenarCategoria(db.obtenerTodos());
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(categoria, lista, Bug -> Bug.getCategoria());
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

    public ArrayList<Bug> buscarEquipo(String equipo)
    {
        ArrayList<Bug> lista = Ordenamientos.ordenarEquipo(db.obtenerTodos());
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(equipo, lista, Bug -> Bug.getEquipo());
            
            if(indice != -1)
                encontrados.add(lista.remove(indice));

        }while(indice != -1);

        return encontrados;

    }

    public ArrayList<Bug> buscarNivelSeguridad(int nivelSeguridad)
    {
        ArrayList<Bug> lista = Ordenamientos.ordenarNivelSeguridad(db.obtenerTodos());
        ArrayList<Bug> encontrados = new ArrayList<Bug>();
        int indice;

        do
        {
            indice = busquedaBinaria(nivelSeguridad, lista, Bug -> Bug.getNivelSeguridad());
            
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
