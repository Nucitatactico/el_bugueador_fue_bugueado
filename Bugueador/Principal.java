package Bugueador;

import java.time.LocalDateTime;
import Bugueador.modelo.BaseDatos;
import Bugueador.modelo.Bug;

public class Principal {
    public static void main(String[] args) {
        BaseDatos db = new BaseDatos();
        cargarBugsIniciales(db);
        Menu menu = new Menu(db);
        menu.iniciar();
    }

    private static void cargarBugsIniciales(BaseDatos db) {
        String[] titulos = {"Error de login", "Falla en conexion", "Boton roto", "Fuga de memoria", "Crash al exportar", "NullPointerException", "Timeout en query", "Problema de estilo", "Fallo de seguridad", "Demora en render"};
        String[] equipos = {"Ciberseguridad", "IT", "Diseño", "Desarrollador", "Tester", "NULL"};
        String[] categorias = {"Interfaz", "Lógica", "Rendimiento", "Seguridad"};

        for (int i = 0; i < 200; i++) {
            String titulo = titulos[i % titulos.length] + " " + i;
            int prioridad = (i % 3) + 1;
            int nivelSeguridad = (i % 3) + 1;
            String equipo = equipos[i % equipos.length];
            String categoria = categorias[i % categorias.length];
            int estado = (i % 5) + 1;
            LocalDateTime fecha = LocalDateTime.now().minusDays(i);

            Bug bug = new Bug(titulo, prioridad, nivelSeguridad, equipo, categoria, estado, fecha);
            db.registrar(bug);
        }
    }
}