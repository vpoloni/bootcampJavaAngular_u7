package ej2;

/*
 * @author Veronika Polonchak
 */

/*Crea una aplicación que gestione el flujo de ventas de una caja de supermercado. 
El programa guardara las cantidades del carrito de compra dentro de una lista. Mostrará por pantalla la siguiente 
informacion:
IVA aplicado 21 o 4
precio total bruto y precio mas IVA
Numero de artículos comprados
Cantidad pagada
Cambio a devolver al cliente*/

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ej2App {

	public static void main(String[] args) {

		// obtenemos la cantidad de artículos que el usuario desea añadir al carrito
		String cantidadArticulos = JOptionPane
				.showInputDialog("Introduce la cantidad de artículos que deseas añadir al carrito: ");
		// parseamos el input de String a int
		int cantidadArticulosParseada = Integer.parseInt(cantidadArticulos);

		// invocamos el método que muestra los detalles de compra pasándole cantidad de
		// artículos comprados
		mostrarFactura(cantidadArticulosParseada);

	}

	public static void mostrarFactura(int numeroArticulos) {
		// creamos Hashtable para insertar clave-valor
		Hashtable<String, Double> articuloComprado = new Hashtable<>();

		// creamos el array para guardar todos hashtables con clave-valor
		ArrayList<Hashtable<String, Double>> listaArticulos = new ArrayList<>();

		// recorremos la cantidad de artículos pasada como parámetro
		for (int i = 0; i < numeroArticulos; i++) {

			String nombreArticulo = JOptionPane.showInputDialog("Introduce el nombre del artículo: ");

			String precioArticulo = JOptionPane.showInputDialog("Introduce el precio del producto: ");
			double precioArticuloParseado = Double.parseDouble(precioArticulo);
			

			String ivaElegido = JOptionPane
					.showInputDialog("Indica el tipo de IVA a aplicar: 1(21%) o 2(4%): ");
			int ivaElegidoParseado = Integer.parseInt(ivaElegido);

			// añadimos clave-valor al hashtable
			articuloComprado.put("precio", precioArticuloParseado);
			articuloComprado.put("iva", indicarIVA(ivaElegidoParseado));

			// añadimos todos hashtables al array de hashtables
			listaArticulos.add(articuloComprado);

			System.out.println(nombreArticulo + articuloComprado);

		}

		String cantidadPagada = JOptionPane.showInputDialog("Introduce el importe a pagar: ");
		double cantidadPagadaParseada = Double.parseDouble(cantidadPagada);

		double totalCompra = 0.00;
		double precioBruto = 0.00;
		double precioConIVA = 0.00;

		// recorremos con for each el array de hashtables
		for (Hashtable<?, ?> articulo : listaArticulos) {

			precioBruto += (double) articulo.get("precio") / (double) articulo.get("iva");
			precioConIVA += (double) articulo.get("precio");

			// guardamos precio neto (con iva) para calcular el cambio a devolver al cliente
			totalCompra += (double) articulo.get("precio");
		}

		System.out.println("Precio bruto: " + String.format("%.2f", precioBruto));
		System.out.println("Precio con IVA: " + String.format("%.2f", precioConIVA));
		System.out.println("Cantidad pagada: " + String.format("%.2f", cantidadPagadaParseada));
		System.out.println("Cambio a devolver al cliente: "
				+ String.format("%.2f", (double) (cantidadPagadaParseada - totalCompra)));

	}

	// indicamos el iva al artículo
	public static double indicarIVA(int numeroIVA) {

		final double IVA_GENERAL = 1.21;
		final double IVA_REDUCIDO = 1.04;

		double ivaArticulo = 0.00;

		if (numeroIVA == 1) {

			ivaArticulo = IVA_GENERAL;
		} else {
			ivaArticulo = IVA_REDUCIDO;
		}

		return ivaArticulo;

	}

}
