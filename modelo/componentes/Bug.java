package modelo.componentes;

import java.time.LocalDateTime;

public class Bug {

	final String ID;
	String titulo;
	int prioridad;
	int nivelSeguridad;
	String equipo;
	String categoria;
	int estado;
	LocalDateTime fecha;

	public Bug(String titulo, int prioridad, int nivelSeguridad,
			String equipo, String categoria, int estado, LocalDateTime fecha) {

		this.titulo = titulo;
		this.prioridad = prioridad;
		this.nivelSeguridad = nivelSeguridad;
		this.equipo = equipo;
		this.categoria = categoria;
		this.estado = estado;
		this.fecha = (fecha != null) ? fecha : LocalDateTime.now();
		this.ID = generarID();
	}

	private String generarID() {
		int sumaTitulo = 0;
		for (int i = 0; i < titulo.length(); i++)
			sumaTitulo += titulo.charAt(i);

		long codigo = (long) sumaTitulo
			* fecha.getYear()
			* fecha.getMonthValue()
			* fecha.getDayOfMonth()
			* (fecha.getHour() + 1)
			* (fecha.getMinute() + 1)
			* (fecha.getSecond() + 1);

		return Long.toString(codigo, 36).toUpperCase();//Sistema alfanumerico
	}

	//Metodo de horner empleando primos
	int hashearID() {
		int hash = 7;
		for (int i = 0; i < ID.length(); i++) {
			hash = 31 * hash + ID.charAt(i);
		}
		return hash;
	}

	void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	void setEstado(int estado) {
		this.estado = estado;
	}

	void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public String toString() {
		return "Bug{" +
			"ID='" + ID + '\'' +
			", titulo='" + titulo + '\'' +
			", prioridad='" + prioridad + '\'' +
			", nivelSeguridad=" + nivelSeguridad +
			", equipo='" + equipo + '\'' +
			", categoria='" + categoria + '\'' +
			", estado=" + estado +
			", fecha=" + fecha +
			'}';
	}
}
