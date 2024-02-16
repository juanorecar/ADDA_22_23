package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import datos.DatosEjercicioCafes;
import datos.DatosEjercicioCafes.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	
	public static List<Integer> tipos;
	public static List<Variedad> variedades;

	
	public static Integer getNumeroTipos() {
		return DatosEjercicioCafes.getNumeroTipos();
	}

	public static Integer getNumeroVariedades() {
		return DatosEjercicioCafes.getNumeroVariedades();
	}

	public static Integer getCantidad(Integer j) {
		return DatosEjercicioCafes.getCantidad(j);
	}

	public static Integer getBeneficio(Integer i) {
		return DatosEjercicioCafes.getBeneficio(i);
	}

	public static Double getCantidadTipoVariedad(Integer j, Integer i) {
		return DatosEjercicioCafes.getCantidadTipoVariedad(j, i);
	}

	
	public static void ejercicio1_model() throws IOException {
		for(int i = 1; i < 4; i++) {
			System.out.println("\n\n#####################################################################################");
			System.out.println("FICHERO EJERCICIO 1 CON DATOS DE ENTRADA " + i);
			System.out.println("#####################################################################################\n");
			
			DatosEjercicioCafes.iniDatos("ficheros/Ejercicio1DatosEntrada" + i + ".txt");
			
			tipos = DatosEjercicioCafes.tipos;
			variedades = DatosEjercicioCafes.variedades;
			
			AuxGrammar.generate(Ejercicio1PLE.class, "lsi_models/Ejercicio1.lsi", "gurobi_models/Ejercicio1-" + i + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-" + i + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}
	}

	public static void main(String[] args) throws IOException {
		ejercicio1_model();
	}

}
