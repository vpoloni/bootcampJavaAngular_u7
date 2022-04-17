package ej3;

/*
 * @author Veronika Polonchak
 */

/*Crea una base de datos de 10 artículos para controlar el
stock de productos de una tienda por medio de un diccionario
de datos articulo precio El usuario podrá añadir, por medio
de interfaz visual artículos nuevos y cantidades de estos El
usario podrá consultar la información almacenada en el
diccionario referente a un articulo concreto e incluso listar toda
la información en la terminal del programa*/

import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ej3App {

	public static void main(String[] args) {

		// hashtable para añadir los artículos al stock de la librería
		Hashtable<String, Integer> stock = new Hashtable<>();

		// añadimos los artículos a hashtable
		stock.put("La Rebelión de Atlas", 50);
		stock.put("El Lobo Estepario", 38);
		stock.put("El Idiota", 16);
		stock.put("Solaris", 20);
		stock.put("El Gran Gatsby", 12);
		stock.put("Germinal", 10);
		stock.put("Si Esto es un Hombre", 26);
		stock.put("El Niño con el Pijama de Rayas", 46);
		stock.put("Los Demonios", 1);
		stock.put("Quo Vadis", 19);

		// asignamos la opción retornada elegida por el usuario
		int opcionElegida = elegirOpcion();
		// invocamos el método que procesa cada caso
		realizarOpcion(stock, opcionElegida);

	}

	public static int elegirOpcion() {
		// obtenemos la opción que desea realizar el usuario
		String opcion = JOptionPane.showInputDialog("Elige la operación que deseas realizar: "
				+ "\n1. añadir un artículo nuevo"
				+ "\n2. consultar la cantidad del artículo concreto"
				+ "\n3. modificar la cantidad del artículo "
				+ "\n4. listar todos los productos");
		// convertimos el valor de entrada de String a int
		int opcionParseada = Integer.parseInt(opcion);
		// retornamos el valor de entrada parseado
		return opcionParseada;
	}

	public static void realizarOpcion(Hashtable<String, Integer> stock, int opcionParseada) {
		// swich para cada caso
		switch (opcionParseada) {

		case 1:

			String nombreArticuloNuevo = JOptionPane.showInputDialog("Introduce el nombre del atrtículo nuevo: ");
			// controlamos si el artículo introducido ya existe en el stock
			if (stock.containsKey(nombreArticuloNuevo)) {
				System.out.println("El artículo ya existe en el stock");
			} else {

				String cantidadArticuloNuevo = JOptionPane
						.showInputDialog("Introduce la cantidad del atrtículo nuevo: ");
				int cantidadArticuloNuevoParseada = Integer.parseInt(cantidadArticuloNuevo);
				// añadimos al stock(hashtable) el artículo nuevo 
				stock.put(nombreArticuloNuevo, cantidadArticuloNuevoParseada);

				System.out.println("La clave nueva: " + nombreArticuloNuevo);
				System.out.println("El valor nuevo: " + cantidadArticuloNuevoParseada);
			}
			break;

		case 2:
			String articuloConsultado = JOptionPane.showInputDialog("Introduce el artículo que deseas consultar: ");
			// controlamos si el artículo introducido ya existe en el stock
			if (stock.containsKey(articuloConsultado)) {
				System.out.println(
						"La cantidad de '" + articuloConsultado + "': " + stock.get(articuloConsultado) + " unidades");
			} else {
				System.out.println("Error: producto no encontrado en el stock");
			}
			break;

		case 3:

			String articuloModificado = JOptionPane.showInputDialog("Introduce el artículo que deseas cambiar: ");
			// controlamos si el artículo introducido ya existe en el stock
			if (stock.containsKey(articuloModificado)) {
				String cantidadModificada = JOptionPane.showInputDialog("Introduce la cantidad nueva del artículo: ");
				int cantidadModificadaParseada = Integer.parseInt(cantidadModificada);

				stock.put(articuloModificado, cantidadModificadaParseada);
			} else {
				System.out.println("Error: producto no encontrado en el stock");
			}
			System.out.println("La cantidad ha sido modificada a: " + stock.get(articuloModificado));
			break;

		case 4:
			// recorremos el conjunto de claves guardadas en hashtable
			for (String articulos : stock.keySet()) {
				// mostramos con get() cada clave de hashtable
				System.out.println("'" + articulos + "' - " + stock.get(articulos));
			}
			break;
		}

	}

}
