package ejercicio2;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import datos.DatosEjercicioCursos;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	public static Integer getMaxCentros() {
		return DatosEjercicioCursos.getMaxCentros();
	}

	public static Integer getNumeroCursos() {
		return DatosEjercicioCursos.getNumeroCursos();
	}

	public static List<Integer> getTematicas() {
		return DatosEjercicioCursos.getTematicas();
	}

	public static Integer getNumeroTematicas() {
		return DatosEjercicioCursos.getNumeroTematicas();
	}

	public static List<Integer> getTematicasCursos(Integer i) {
		return DatosEjercicioCursos.getTematicasCursos(i);
	}

	public static Integer getNumeroTematicasCursos(Integer i) {
		return DatosEjercicioCursos.getNumeroTematicasCursos(i);
	}

	public static Integer contieneTematica(Integer i, Integer j) {
		return DatosEjercicioCursos.contieneTematica(i, j);
	}

	public static Double getPrecioCurso(Integer i) {
		return DatosEjercicioCursos.getPrecioCurso(i);
	}

	public static Integer getCentroCurso(Integer i) {
		return DatosEjercicioCursos.getCentroCurso(i);
	}

	public static List<Integer> getCentros() {
		return DatosEjercicioCursos.getCentros();
	}

	public static Integer getNumeroCentros() {
		return DatosEjercicioCursos.getNumeroCentros();
	}

	public static Integer ofreceCurso(Integer i, Integer k) {
		return DatosEjercicioCursos.ofreceCurso(i, k);
	}

	public static void ejercicio2_model() throws IOException {
		for (int i = 1; i < 4; i++) {
			System.out.println(
					"\n\n#####################################################################################");
			System.out.println("FICHERO EJERCICIO 2 CON DATOS DE ENTRADA " + i);
			System.out
					.println("#####################################################################################\n");

			DatosEjercicioCursos.iniDatos("ficheros/Ejercicio2DatosEntrada" + i + ".txt");

			AuxGrammar.generate(Ejercicio2PLE.class, "lsi_models/Ejercicio2.lsi",
					"gurobi_models/Ejercicio2-" + i + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-" + i + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}
	}

	public static void main(String[] args) throws IOException {
		ejercicio2_model();
	}

}
