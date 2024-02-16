package ejercicio3;

import java.io.IOException;

import java.util.Locale;
import datos.DatosEjercicioInvestigadores;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	
	public static Integer getNumeroInvestigadores() {
		return DatosEjercicioInvestigadores.getNumeroInvestigadores();
	}
	
	public static Integer getNumeroEspecialidades() {
		return DatosEjercicioInvestigadores.getNumeroEspecialidades();
	}
	
	public static Integer getNumeroTrabajos() {
		return DatosEjercicioInvestigadores.getNumeroTrabajos();
	}
	
	public static Integer trabajadorEspecialidad(Integer i, Integer k) {
		return DatosEjercicioInvestigadores.trabajadorEspecialidad(i, k);
	}
	
	public static Integer diasDisponibles(Integer i) {
		return DatosEjercicioInvestigadores.diasDisponibles(i);
	}
	
	public static Integer diasNecesarios(Integer j, Integer k) {
		return DatosEjercicioInvestigadores.diasNecesarios(j, k);
	}
	
	public static Integer getCalidad(Integer j) {
		return DatosEjercicioInvestigadores.getCalidad(j);
	}
	
	public static Integer getMM() {
		return DatosEjercicioInvestigadores.getMM();
	}

	public static void ejercicio3_model() throws IOException {
		for(int i = 1; i < 4; i++) {
			System.out.println("\n\n#####################################################################################");
			System.out.println("FICHERO EJERCICIO 3 CON DATOS DE ENTRADA " + i);
			System.out.println("#####################################################################################\n");
			
			DatosEjercicioInvestigadores.iniDatos("ficheros/Ejercicio3DatosEntrada" + i + ".txt");
			
			
			AuxGrammar.generate(Ejercicio3PLE.class, "lsi_models/Ejercicio3.lsi", "gurobi_models/Ejercicio3-" + i + ".lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-" + i + ".lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}
	}

	public static void main(String[] args) throws IOException {
		ejercicio3_model();
	}

}
