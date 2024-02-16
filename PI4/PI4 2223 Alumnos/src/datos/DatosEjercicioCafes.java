package datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import us.lsi.common.Files2;

public class DatosEjercicioCafes {

	public static List<Integer> tipos;
	public static List<Variedad> variedades;

	public record Variedad(int id, Integer beneficio, List<Double> mezcla) { // RECORD PARA LAS VARIEDADES DE CAFE

		public static int cont;
		public static Variedad create(String linea) {

			List<Double> mezcla = new ArrayList<>();

			for (int j = 0; j < tipos.size(); j++) {
				mezcla.add(0.);
			}

			String[] var = linea.split(";");
			Integer benef = Integer.parseInt(var[0].split("=")[1].replace(";", "").trim());
			String[] comps = var[1].split("=")[1].trim().split(",");

			for (int j = 0; j < comps.length; j++) {
				String[] porcen = comps[j].replace("(C", "").replace(")", "").split(":");
				Integer tipo = Integer.parseInt(porcen[0].trim()) - 1;
				Double porcentaje = Double.parseDouble(porcen[1].trim());
				mezcla.set(tipo, porcentaje);
			}

			return new Variedad(cont++, benef, new ArrayList<>(mezcla));
		}
	}

	public static void iniDatos(String fich) { // LECTURA DE LOS DATOS

		Variedad.cont = 0;
		List<String> lineas = Files2.linesFromFile(fich);
		int pos = lineas.indexOf("// VARIEDADES");
		List<String> tiposCafe = lineas.subList(1, pos);
		List<String> variedadesCafe = lineas.subList(pos + 1, lineas.size());
		List<Integer> aux = new ArrayList<>();

		for (int i = 0; i < tiposCafe.size(); i++) {
			Integer valor = Integer.parseInt(tiposCafe.get(i).split("=")[1].replace(";", "").trim());
			aux.add(valor);
		}

		tipos = new ArrayList<>(aux);
		variedades = new ArrayList<>();

		for (int i = 0; i < variedadesCafe.size(); i++) {
			variedades.add(Variedad.create(variedadesCafe.get(i))); // HACEMOS USO DEL RECORD ANTERIOR
		}
		toConsole();
	}

	public static Integer getNumeroTipos() {
		return tipos.size();
	}

	public static Integer getNumeroVariedades() {
		return variedades.size();
	}

	public static Integer getCantidad(Integer j) {
		return tipos.get(j);
	}

	public static Integer getBeneficio(Integer i) {
		return variedades.get(i).beneficio();
	}

	public static Double getCantidadTipoVariedad(Integer j, Integer i) {
		return variedades.get(i).mezcla().get(j);
	}

	public static List<Variedad> getVariedades() {
		return new ArrayList<>(variedades);
	}

	public static Integer getCantidadMaxima(Integer i) {
		List<Double> lsMax = new ArrayList<>();

		for (int j = 0; j < tipos.size(); j++) {
			lsMax.add(getCantidad(j) / getCantidadTipoVariedad(j, i));
		}

		lsMax.sort(Comparator.naturalOrder());
		return lsMax.get(0).intValue();
	}

	private static void toConsole() {
		System.out.println("Cantidad disponible tipo - " + tipos + "\nVariedad disponible - " + variedades);
	}

	public static void main(String[] args) {
		for (int i = 1; i < 4; i++) {
			System.out.println("\n######################## DATOS FICHERO " + i + " ########################\n");
			String fich = "ficheros/Ejercicio1DatosEntrada" + i + ".txt";
			iniDatos(fich);
			System.out.println("\n");
		}
	}

}
