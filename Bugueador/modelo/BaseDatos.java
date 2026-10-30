package modelo;

import java.util.ArrayList;

public class BaseDatos {

	private ArrayList<ArrayList<Bug>> slots;
	private int tamano;

	private static final int CAPACIDAD = 211;//Numero primo chavos

	public BaseDatos() {
		this.slots = new ArrayList<>(CAPACIDAD);
		for (int i = 0; i < CAPACIDAD; i++)
			this.slots.add(new ArrayList<>());
		
		this.tamano = 0;
	}

	private int indice(String id) {
		int hash = Bug.hashearID(id) & 0x7fffffff;//Es lo mismo que un absoluto pero mas chispa
		return hash % CAPACIDAD;
	}

	public void registrar(Bug bug) {
		int pos = indice(bug.ID);
		slots.get(pos).add(bug);
		tamano++;
	}

	public boolean modificar(Bug nuevo) {
		int pos = indice(nuevo.ID);
		ArrayList<Bug> slot = slots.get(pos);

		//Busqueda linea en slot por si hay colsiones
		for (int i = 0; i < slot.size(); i++)
			if (slot.get(i).ID.equals(nuevo.ID)){
				slot.set(i, nuevo);
				return true;
			}

		return false;
	}

	public boolean eliminar(String id) {
		int pos = indice(id);
		ArrayList<Bug> slot = slots.get(pos);

		//Busqueda lieal en slot por si hay colisiones
		for (int i = 0; i < slot.size(); i++)
			if (slot.get(i).ID.equals(id)){
				slot.remove(i);
				tamano--;
				return true;
			}

		return false;
	}

	public ArrayList<Bug> obtenerTodos() {
		ArrayList<Bug> todos = new ArrayList<>();

		for (ArrayList<Bug> slot : slots)
			todos.addAll(slot);

		return todos;
	}

	public ArrayList<Bug> obtenerIndice(int indice)
	{
		return slots.get(indice);
	}

	//si queremos manejar el numero para los ordenamietnos
	public int getTamano() {
		return tamano;
	}
}
