package ej1;

/*
 * @author Veronika Polonchak
 */

/*Crea una aplicaci�n que calcule la nota media de los
alumnos pertenecientes al curso de programaci�n Una vez
calculada la nota media se guardara esta informaci�n en un
diccionario de datos que almacene la nota media de cada
alumno Todos estos datos se han de proporcionar por
pantalla*/


import java.util.Hashtable;

/*Crea una aplicaci�n que calcule la nota media de los
alumnos pertenecientes al curso de programaci�n Una vez
calculada la nota media se guardara esta informaci�n en un
diccionario de datos que almacene la nota media de cada
alumno Todos estos datos se han de proporcionar por
pantalla*/

import javax.swing.JOptionPane;

public class Ej1App {

	public static void main(String[] args) {

		String cantidadAlumnos = JOptionPane.showInputDialog("Introduce la cantidad de alumnos: ");
		int cantidadAlumnosParseada = Integer.parseInt(cantidadAlumnos);

		String cantidadNotas = JOptionPane.showInputDialog("Introduce la cantidad de notas: ");
		int cantidadNotasParseada = Integer.parseInt(cantidadNotas);

		calcularNotaMedia(cantidadAlumnosParseada, cantidadNotasParseada);

	}

	public static void calcularNotaMedia(int cantidadAlumnos, int cantidadNotas) {

		// Creamos un objeto de la clase Hashtable
		Hashtable<String, Double> notasMediasAlumno = new Hashtable<String, Double>();

		// Recorremos la cantidad de alumnos obtenida por consola
		for (int i = 0; i < cantidadAlumnos; i++) {

			double sumaNotasAlumno = 0.00;
			String nombreAlumno = JOptionPane.showInputDialog("Introduce el nombre del alumno: ");

			// Recorremos las notas de cada alumno pedidas por consola
			for (int x = 0; x < cantidadNotas; x++) {

				String notaAlumno = JOptionPane.showInputDialog("Introduce la nota del alumno: " + nombreAlumno);

				// Convertimos la nota de String a double
				double notaAlumnoParseada = Double.parseDouble(notaAlumno);

				// Sumamos la nota a la variable iniciada en 0 en cada iteraci�n
				sumaNotasAlumno += notaAlumnoParseada;

			}

			// Calculamos la nota media del alumno
			double notaMediaAlumno = sumaNotasAlumno / cantidadNotas;

			// Insertamos las claves y los valores de cada iteraci�n al diccionario
			// hashtable
			notasMediasAlumno.put(nombreAlumno, notaMediaAlumno);

		}
		// Hacemos uso del m�todo .keySet() para ver las claves guardadas en hashtable
		for (String claveNombreAlumno : notasMediasAlumno.keySet()) {

			// Pasamos al m�todo .qet() la clave cuyo valor deseamos recuperar
			System.out.println(
					"La nota media de " + claveNombreAlumno + " es " + notasMediasAlumno.get(claveNombreAlumno));
		}

	}

}
