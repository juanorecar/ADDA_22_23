package datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.common.Files2;

public class DatosEjercicioInvestigadores {

	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;

	public record Investigador(Integer id, Integer capacidad, Integer especialidad) {

		public static int cont;
		public static Investigador create(String linea) {
			String[] investig = linea.split(";");
			Integer capacidad = Integer.parseInt(investig[0].trim().split("=")[1].trim());
			Integer especialidad = Integer.parseInt(investig[1].trim().split("=")[1].trim());
			return new Investigador(cont++, capacidad, especialidad);
		}
	}

	public record Trabajo(Integer id, Integer calidad, List<Integer> dias) {

		public static int cont;
		public static Trabajo create(String linea) {
			String[] trabaj = linea.split(";");
			Integer calid = Integer.parseInt(trabaj[0].trim().split("=")[1].trim());
			String[] repart = trabaj[1].trim().split("=")[1].trim().split(",");
			List<Integer> dias = new ArrayList<>();
			for (String str : repart) {
				str = str.replace("(", "").replace(")", "").trim();
				dias.add(Integer.parseInt(str.split(":")[1].trim()));
			}
			return new Trabajo(cont++, calid, dias);
		}
	}

	public static void iniDatos(String fich) {
		Investigador.cont = 0;
		Trabajo.cont = 0;
		
		investigadores = new ArrayList<>();
		trabajos = new ArrayList<>();
		
		List<String> lineas = Files2.linesFromFile(fich);
		List<String> investis = lineas.subList(1, lineas.indexOf("// TRABAJOS"));
		List<String> trabajs = lineas.subList(lineas.indexOf("// TRABAJOS") + 1, lineas.size());
		
		for(String i : investis) {
			investigadores.add(Investigador.create(i));
		}
		
		for (String t : trabajs) {
			trabajos.add(Trabajo.create(t));
		}
		
		toConsole();
	}
	
	public static Integer getNumeroInvestigadores() {
		return investigadores.size();
	}
	
	public static Integer getNumeroEspecialidades() {
		return trabajos.get(0).dias().size();
	}
	
	public static Integer getNumeroTrabajos() {
		return trabajos.size();
	}
	
	public static Integer trabajadorEspecialidad(Integer i, Integer k) {
		return investigadores.get(i).especialidad().equals(k) ? 1 : 0;
				}
	
	public static Integer diasDisponibles(Integer i) {
		return investigadores.get(i).capacidad();
	}
	
	public static Integer diasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).dias().get(k);
	}
	
	public static Integer getCalidad(Integer j) {
		return trabajos.get(j).calidad();
	}
	
	public static Integer getMM() {
		return investigadores.stream().map(i -> i.capacidad()).max(Comparator.naturalOrder()).get() + 1;
	}
	
	private static void toConsole() {
		System.out.println(investigadores);
		System.out.println(trabajos);
	}

	public static void main(String[] args) {
		for (int i = 1; i < 4; i++) {
			System.out.println("\n######################## DATOS FICHERO " + i + " ########################\n");
			String fich = "ficheros/Ejercicio3DatosEntrada" + i + ".txt";
			iniDatos(fich);
			System.out.println("\n");
		}
	}
}
