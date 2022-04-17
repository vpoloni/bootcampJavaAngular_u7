package ej4;

/*
 * @author Veronika Polonchak
 */

/*Combina los métodos generados en las actividades 2 y 3
creando una aplicación que gestione ventas y control de stock en
una misma interfaz. Utiliza para ello las estructuras de datos que
creas conveniente.*/

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.JOptionPane;

public class Ej4App {

	public static void main(String[] args) {

		// pasamos el stock generado aleatoriamente por referencia
		Hashtable<String, ArrayList<Object>> miStock = stock();
		operacionStock(miStock, elegirOpcion());

	}

	public static Hashtable<String, ArrayList<Object>> stock() {

		// definimos hashtable para guardar stock (valor-clave) generado aleatoriamente
		Hashtable<String, ArrayList<Object>> stock = new Hashtable<String, ArrayList<Object>>();

		Random random = new Random();

		// rellenamos hashtable(stock) con 10 claves(nombres)-valores(precio, cantidad, IVA)
		for (int i = 0; i < 10; i++) {

			// definimos ArrayList para guardar los valores (precio, cantidad, IVA)
			ArrayList<Object> listaValoresArticulos = new ArrayList<>();

			// recorremos ArrayList con valores
			for (int y = 0; y < 1; y++) {

				// asignamos los valores aleatorios
				double precioAleatorio = random.nextDouble() * 100;
				int cantidadAleatoria = (int) (random.nextDouble() * 100);
				boolean ivaAleatoria = random.nextBoolean();

				double ivaTipo = 0.00;
				// comprobamos y asignamos el tipo de IVA
				if (ivaAleatoria) {
					ivaTipo = 1.21;
				} else {
					ivaTipo = 1.04;
				}
				// añadimos los valores aleatorios a ArrayList
				listaValoresArticulos.add(precioAleatorio);
				listaValoresArticulos.add(cantidadAleatoria);
				listaValoresArticulos.add(ivaTipo);
			}
			// rellenamos stock(hashtable) con claves y valores(generados previamente)
			stock.put("artículo " + i, listaValoresArticulos);
		}
		// retornamos stock (clave & valor)
		return stock;

	}

	public static int elegirOpcion() {
		// obtenemos la opción elegida por el usuario
		String opcion = JOptionPane
				.showInputDialog("Elige la operación que deseas realizar: " 
						+ "\n1. listar todos los productos"
						+ "\n2. consultar detalles de un producto"
						+ "\n3. añadir nuevo producto"
						+ "\n4. modificar un producto (precio, cantidad, IVA) " + "\n5. vender un producto");

		// convertimos el valor de entrada de String a int
		int opcionParseada = Integer.parseInt(opcion);
		// retornamos el valor de entrada parseado
		return opcionParseada;
	}

	public static void operacionStock(Hashtable<String, ArrayList<Object>> stock, int opcionParseada) {

		// swich para evaluar cada operación que puede elegir el usuario
		switch (opcionParseada) {
		
		case 1:
			for (String articulos : stock.keySet()) {
				System.out.println(articulos + " - " + stock.get(articulos));
			}
			break;

		case 2:
			String articuloConsultado = JOptionPane
					.showInputDialog("Introduce el nombre del artículo que deseas consultar: ");

			if (stock.containsKey(articuloConsultado)) {
				System.out.println("Nombre: " + articuloConsultado);
				// accedemos a las posiciones de valor de hashtable
				System.out.println("Precio: " + stock.get(articuloConsultado).get(0));
				System.out.println("Cantidad: " + stock.get(articuloConsultado).get(1));
				System.out.println("IVA: " + stock.get(articuloConsultado).get(2));
			} else {
				System.out.println("Error: producto no encontrado en el stock");
			}
			break;

		case 3:
			String nombreArticuloNuevo = JOptionPane.showInputDialog("Introduce un nombre del atrtículo nuevo: ");

			// controlamos si el artículo itroducido ya existe en el stock
			if (stock.containsKey(nombreArticuloNuevo)) {
				System.out.println("El artículo introducido ya existe en el stock");
			} else {

				String precioArticuloNuevo = JOptionPane.showInputDialog("Introduce un precio del atrtículo nuevo: ");
				int precioArticuloNuevoParseado = Integer.parseInt(precioArticuloNuevo);

				String cantidadArticuloNuevo = JOptionPane
						.showInputDialog("Introduce una cantidad del atrtículo nuevo: ");
				int cantidadArticuloNuevoParseada = Integer.parseInt(cantidadArticuloNuevo);

				String ivaTipoArticuloNuevo = JOptionPane
						.showInputDialog("Indica el número indicativo del tipo de IVA a aplicar: 1(21%) o 2(4%): ");
				int ivaTipoArticuloNuevoParseado = Integer.parseInt(ivaTipoArticuloNuevo);

				double ivaArticuloNuevo = 0.00;
				// comprobamos y asignamos el tipo de IVA del artículo nuevo
				if (ivaTipoArticuloNuevoParseado == 1) {
					ivaArticuloNuevo = 1.21;
				} else if (ivaTipoArticuloNuevoParseado == 2) {
					ivaArticuloNuevo = 1.04;
				}
				// creamos ArrayList del artículo nuevo (valor de hashtable-stock)
				ArrayList<Object> listaValoresAtriculoNuevo = new ArrayList<>();

				// añadimos valores al array (array que corresponde a valor de hashtable)
				listaValoresAtriculoNuevo.add(nombreArticuloNuevo);
				listaValoresAtriculoNuevo.add(precioArticuloNuevoParseado);
				listaValoresAtriculoNuevo.add(cantidadArticuloNuevoParseada);
				listaValoresAtriculoNuevo.add(ivaArticuloNuevo);

				// populamos el hashtable(stock) con clave-valor del artículo nuevo
				stock.put(nombreArticuloNuevo, listaValoresAtriculoNuevo);

				// mostramos clave-valor del artículo nuevo
				System.out.println("El artículo nuevo añadido al stock: " + listaValoresAtriculoNuevo);

			}
			break;
		case 4:
			String nombreArticuloModificado = JOptionPane
					.showInputDialog("Introduce el nombre del artículo que deseas modificar: ");

			// creamos ArrayList para guardar valores modificados (valor de hashtable)
			ArrayList<Object> valoresModificados = new ArrayList<>();

			// seguimos si el stock(hashtable) contiene el artículo itroducido por el
			// usuario
			if (stock.containsKey(nombreArticuloModificado)) {

				// obtenemos el valor que desea modificar el usuario
				String valorElegido = JOptionPane
						.showInputDialog("Indica qué deseas midificar: 1(precio), 2(cantidad), 3(IVA)");
				int valorElegidoParseado = Integer.parseInt(valorElegido);

				// evaluamos cada opción que puede eligir el usuario
				switch (valorElegidoParseado) {

				case 1:
					// mostramos por pantalla el precio anterior
					System.out.println(
							"El precio actual: " + String.format("%.2f", stock.get(nombreArticuloModificado).get(0)));
					String precioModificado = JOptionPane.showInputDialog("Introduce el precio nuevo: ");
					double precioModificadoParseado = Double.parseDouble(precioModificado);

					valoresModificados.add(precioModificadoParseado);
					valoresModificados.add(stock.get(nombreArticuloModificado).get(1));
					valoresModificados.add(stock.get(nombreArticuloModificado).get(2));

					stock.put(nombreArticuloModificado, valoresModificados);
					// mostramos por pantalla el precio modificado
					System.out.println(
							"El precio nuevo: " + String.format("%.2f", stock.get(nombreArticuloModificado).get(0)));

					break;

				case 2:
					// mostramos por pantalla la cantidad anterior
					System.out.println("La cantidad actual: " + stock.get(nombreArticuloModificado).get(1));
					String cantidadModificada = JOptionPane.showInputDialog("Introduce la nueva cantidad: ");
					int cantidadModificadaParseada = Integer.parseInt(cantidadModificada);

					// guardar en el array valores modificados y valores no modificados
					valoresModificados.add(stock.get(nombreArticuloModificado).get(0));
					valoresModificados.add(cantidadModificadaParseada);
					valoresModificados.add(stock.get(nombreArticuloModificado).get(2));

					stock.put(nombreArticuloModificado, valoresModificados);
					// mostramos por pantalla la cantidad modificada
					System.out.println("La cantidad modificada: " + stock.get(nombreArticuloModificado).get(1));
					break;

				case 3:
					// mostramos por pantalla el iva anterior
					System.out.println("El iva actual: " + stock.get(nombreArticuloModificado).get(2));
					String ivaTipoArticuloModificado = JOptionPane
							.showInputDialog("Indica el número indicativo del tipo de IVA a aplicar: 1(21%) o 2(4%): ");
					double ivaTipoArticuloModificadoParseado = Double.parseDouble(ivaTipoArticuloModificado);

					double ivaArticuloModificado = 0.00;
					// comprobamos y asignamos el tipo de IVA del artículo modificado
					if (ivaTipoArticuloModificadoParseado == 1) {
						ivaArticuloModificado = 1.21;
					} else if (ivaTipoArticuloModificadoParseado == 2) {
						ivaArticuloModificado = 1.04;
					}

					valoresModificados.add(stock.get(nombreArticuloModificado).get(0));
					valoresModificados.add(stock.get(nombreArticuloModificado).get(1));
					valoresModificados.add(ivaArticuloModificado);

					stock.put(nombreArticuloModificado, valoresModificados);
					// mostramos por pantalla el iva modificado
					System.out.println("El iva modificado: " + stock.get(nombreArticuloModificado).get(2));
					break;
				}

			} else {
				System.out.println("Error: producto no encontrado en el stock");
			}
			break;

		case 5:
			String articuloVendido = JOptionPane
					.showInputDialog("Introduce el nombre del artículo que deseas vender: ");
			// ArrayList para guardar la cantidad actual después de la venta
			ArrayList<Object> cantidadArticulosStock = new ArrayList<>();

			// seguimos si el artículo existe en el stock
			if (stock.containsKey(articuloVendido)) {

				int cantidadAntesDeVender = (int) stock.get(articuloVendido).get(1);
				System.out.println("Cantidad disponible: " + cantidadAntesDeVender);

				String cantidadVender = JOptionPane.showInputDialog("¿Cuantas unidades deseas vender? ");
				int cantidadVenderParseada = Integer.parseInt(cantidadVender);

				int cantidadDespuesDeVender = 0;

				// igualamos la cantidad disponiable y cantidad que el usuario desea vender
				if (cantidadVenderParseada > cantidadAntesDeVender) {
					System.out.println("Cantidad introducida que deseas vender: " + cantidadVenderParseada);
					System.out.println("No hay suficiente unidades");
				} else if (cantidadVenderParseada < cantidadAntesDeVender) {

					cantidadDespuesDeVender = cantidadAntesDeVender - cantidadVenderParseada;

					cantidadArticulosStock.add(stock.get(articuloVendido).get(0));
					cantidadArticulosStock.add(cantidadDespuesDeVender);
					cantidadArticulosStock.add(stock.get(articuloVendido).get(2));

					stock.put(articuloVendido, cantidadArticulosStock);
					System.out.println("Cantidad antes de vender: " + cantidadAntesDeVender);
					System.out.println("Cantidad vendida: " + cantidadVenderParseada);
					System.out.println("Cantidad después de vender: " + cantidadDespuesDeVender);
				}

			} else {

				System.out.println("Error: producto no encontrado en el stock");
			}

		}

	}

}
