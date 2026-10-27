package modelo.componentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import modelo.componentes.Bug;

public abstract class Ordenamientos
{
    public static ArrayList<Bug> ordenarFecha(ArrayList<Bug> lista)
    {
        bubbleSort(lista, (b1, b2) -> b1.fecha.getDayOfMonth() > b2.fecha.getDayOfMonth());
        bubbleSort(lista, (b1, b2) -> b1.fecha.getMonthValue() > b2.fecha.getMonthValue());
        bubbleSort(lista, (b1, b2) -> b1.fecha.getYear() > b2.fecha.getYear());

        return lista;
    }

    public static ArrayList<Bug> ordenarEquipo(ArrayList<Bug> lista)
    {
        bubbleSort(lista, (b1, b2) -> b1.equipo.compareToIgnoreCase(b2.equipo) > 0);

        return lista;
    }

    public static ArrayList<Bug> ordenarCategoria(ArrayList<Bug> lista)
    {
        Bug[] ordenado = lista.toArray(new Bug[0]);

        mergeSort(ordenado, 0, ordenado.length-1, (b1, b2) -> b1.categoria.compareToIgnoreCase(b2.categoria) <= 0);
    
        return new ArrayList<Bug>(Arrays.asList(ordenado));
    }

    public static ArrayList<Bug> ordenarNivelSeguridad(ArrayList<Bug> lista)
    {
        return countigSort(lista, Bug -> Bug.nivelSeguridad);
    }


    interface Atributo
    {
        int obtener(Bug bug);
    }


    private static ArrayList<Bug> countigSort(ArrayList<Bug> lista, Atributo valor)
    {
        int[] count = new int[5];
        Bug[] ordenada = lista.toArray(new Bug[0]);

        for(Bug bug:lista)
            count[valor.obtener(bug) - 1]++;

        for(int i=1;i<count.length;i++)
            count[i] += count[i-1];

        for(Bug bug:lista)
        {
            int indice = valor.obtener(bug) - 1;
            ordenada[count[indice]-1] = bug;
            count[indice]--;
        }

        return new ArrayList<Bug>(Arrays.asList(ordenada));
    }

    private static void merge(Bug arr[], int left, int mid, int right, BiPredicate<Bug, Bug> comparacion) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Bug L[] = new Bug[n1];
        Bug R[] = new Bug[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (comparacion.test(L[i], R[j])) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void mergeSort(Bug arr[], int left, int right, BiPredicate<Bug, Bug> comparacion) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, comparacion);
            mergeSort(arr, mid + 1, right, comparacion);

            merge(arr, left, mid, right, comparacion);
        }
    } 

    private static void bubbleSort(ArrayList<Bug> lista, BiPredicate<Bug, Bug> condicion)
    {
        Bug aux;

        for(int i = lista.size() - 1; i > 0; i--)
            for(int j = 0; j < i; j++)
            {
                if(condicion.test(lista.get(j), lista.get(j+1)))
                {
                    aux = lista.get(j);

                    lista.set(j, lista.get(j+1));
                    lista.set(j+1, aux);
                }
            }
    }
}