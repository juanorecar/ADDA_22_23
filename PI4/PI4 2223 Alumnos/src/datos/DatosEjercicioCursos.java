package datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;

public class DatosEjercicioCursos {

	public static List<Curso> cursos;
	public static Integer maxCentros;

	public record Curso(Integer id, List<Integer> tematicas, Double precio, Integer centro) {

		public static int cont;

		public static Curso create(String linea) {

			List<Integer> aux = new ArrayList<>();
			String[] params = linea.split(":");
			String[] temas = params[0].substring(1, params[0].length() - 1).split(",");

			for (String str : temas) {
				aux.add(Integer.parseInt(str.trim()));
			}

			return new Curso(cont++, new ArrayList<>(aux), Double.parseDouble(params[1].trim()),
					Integer.parseInt(params[2].trim()));
		}
	}

	public static void iniDatos(String fich) {
		List<Curso> res = new ArrayList<>();
		Curso.cont = 0;

		List<String> lineas = Files2.linesFromFile(fich);
		maxCentros = Integer.parseInt(lineas.get(0).split("=")[1].trim());

		for (String st : lineas.subList(1, lineas.size())) {
			res.add(Curso.create(st));
		}

		cursos = new ArrayList<>(res);
		toConsole();
	}

	public static Integer getMaxCentros() {
		return maxCentros;
	}

	public static Integer getNumeroCursos() {
		return cursos.size();
	}

	public static List<Integer> getTematicas() {
		Set<Integer> s = new HashSet<>();
		for (Curso t : cursos) {
			s.addAll(t.tematicas());
		}

		return new ArrayList<>(s);
	}

	public static Integer getNumeroTematicas() {
		return getTematicas().size();
	}

	public static List<Integer> getTematicasCursos(Integer i) {
		return cursos.get(i).tematicas();
	}

	public static Integer getNumeroTematicasCursos(Integer i) {
		return getTematicasCursos(i).size();
	}

	public static Integer contieneTematica(Integer i, Integer j) {
		return cursos.get(i).tematicas().contains(getTematicas().get(j)) ? 1 : 0;
	}

	public static Double getPrecioCurso(Integer i) {
		return cursos.get(i).precio();
	}

	public static Integer getCentroCurso(Integer i) {
		return cursos.get(i).centro();
	}

	public static List<Integer> getCentros() {
		Set<Integer> s = new HashSet<>();
		for (Curso cu : cursos) {
			s.add(cu.centro());
		}

		return new ArrayList<>(s);
	}

	public static Integer getNumeroCentros() {
		return getCentros().size();
	}

	public static Integer ofreceCurso(Integer i, Integer k) {
		return cursos.get(i).centro().equals(getCentros().get(k)) ? 1 : 0;
	}

	public static void toConsole() {
		System.out.println("Maximo de centros selecionables: " + maxCentros + "\nCursos disponibles: " + cursos);
	}

	public static void main(String[] args) {
		for (int i = 1; i < 4; i++) {
			System.out.println("\n######################## DATOS FICHERO " + i + " ########################\n");
			String fich = "ficheros/Ejercicio2DatosEntrada" + i + ".txt";
			iniDatos(fich);
			System.out.println("\n");
		}
	}

}
